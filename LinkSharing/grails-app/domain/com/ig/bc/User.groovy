package com.ig.bc

import com.ig.bc.enums.Seriousness

class User {
    String email
    String password
    String confirmPassword
    String fullName
    Date dateOfBirth
    Date dateCreated
    Date lastUpdated
    boolean isMale
    static hasMany = [topics: Topic, subscriptions: Subscription]
    static transients = ['confirmPassword']
    static constraints = {
        email(unique: true, blank: false, nullable: false, email: true)
        dateOfBirth(nullable: true)
        confirmPassword(bindable: true)
        topics(nullable: true)
        subscriptions(nullable: true)
        password(validator: {val, user ->
            if (val == user.confirmPassword)
                return true
            return false
        })
    }

    List<Topic> getAllVerySeriousTopics() {
        List<Topic> topics = Subscription.createCriteria().list {
            projections {
                property("topic")
            }
            eq("seriousness", Seriousness.VERY_SERIOUS)
            eq("subscriber", this)
        }
        return topics
    }

    List<Resource> getResources() {
        return Resource.findAllByUser(this)
    }

    List<DocumentResource> getDocumentResources() {
        List<Resource> resources = getResources()
        List<DocumentResource> documentResources = resources.collect {resource ->
            if(resource.instanceOf(DocumentResource))
                return resource
        }
    }

    List<ReadingItem> getReadingItems() {
        List<ReadingItem> readingItems = ReadingItem.findAllByUser(this)
        return readingItems
    }

    Integer getReadingItemsCount() {
        return ReadingItem.countByUser(this)
    }

    String toString() {
        fullName
    }
}
