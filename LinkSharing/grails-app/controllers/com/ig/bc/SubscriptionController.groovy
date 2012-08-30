package com.ig.bc

import org.springframework.dao.DataIntegrityViolationException

class SubscriptionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def subscriptionService
    def topicService
    def userService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        String currentLoggedInUserEmail = session.email
        params.max = Math.min(max ?: 10, 100)
        [subscriptionInstanceList: subscriptionService.getCurrentLoggedInUserSubscriptions(currentLoggedInUserEmail),
                subscriptionInstanceTotal: subscriptionService.countCurrentLoggedInUserTotalSubscriptions(currentLoggedInUserEmail)]
    }

    def create() {
        String currentLoggedInUserEmail = session.email
        User currentLoggedInUser = userService.getCurrentUser(currentLoggedInUserEmail)
        List<Topic> allPublicOrOwnedTopics = topicService.getCurrentLoggedInUserAllOwnedOrPublicTopics(currentLoggedInUserEmail)
        [subscriptionInstance: new Subscription(params), topicInstanceList: allPublicOrOwnedTopics, currentLoggedInUser: currentLoggedInUser]
    }

    def save() {
        def subscriptionInstance = new Subscription(params)
        if (!subscriptionInstance.save(flush: true)) {
            render(view: "create", model: [subscriptionInstance: subscriptionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscriptionInstance.id])
        redirect(action: "show", id: subscriptionInstance.id)
    }

    def show(Long id) {
        def subscriptionInstance = Subscription.get(id)
        if (!subscriptionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "list")
            return
        }

        [subscriptionInstance: subscriptionInstance]
    }

    def edit(Long id) {
        def subscriptionInstance = Subscription.get(id)
        if (!subscriptionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "list")
            return
        }

        [subscriptionInstance: subscriptionInstance]
    }

    def update(Long id, Long version) {
        def subscriptionInstance = Subscription.get(id)
        if (!subscriptionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (subscriptionInstance.version > version) {
                subscriptionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'subscription.label', default: 'Subscription')] as Object[],
                          "Another user has updated this Subscription while you were editing")
                render(view: "edit", model: [subscriptionInstance: subscriptionInstance])
                return
            }
        }

        subscriptionInstance.properties = params

        if (!subscriptionInstance.save(flush: true)) {
            render(view: "edit", model: [subscriptionInstance: subscriptionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscriptionInstance.id])
        redirect(action: "show", id: subscriptionInstance.id)
    }

    def delete(Long id) {
        def subscriptionInstance = Subscription.get(id)
        if (!subscriptionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "list")
            return
        }

        try {
            subscriptionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "show", id: id)
        }
    }
}