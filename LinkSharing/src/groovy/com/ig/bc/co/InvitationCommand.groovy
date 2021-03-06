package com.ig.bc.co

import org.codehaus.groovy.grails.validation.Validateable

@Validateable
class InvitationCommand {
    String email1
    String email2
    String email3
    String content
    static constraints = {
        email1(email: true)
        email2(email: true)
        email3(email: true)
    }
}