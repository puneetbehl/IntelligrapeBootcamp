package com.ig.bc

class LoginController {
    static final dateFormat = 'yyyy-mm-dd'

    def beforeInterceptor = [action: this.&checkSession, except: 'logout']

    private checkSession() {
        boolean returnStatus
        if (session.email) {
            redirect(controller: "user", action: "dashboard")
            returnStatus = false
        } else {
            returnStatus = true
        }
        return returnStatus
    }

    def login() {
    }

    def register() {

    }

    def registrationHandler() {
        if (params) {
            redirect action: 'login'
        }
        params.dateOfBirth = Date.parse(dateFormat, params.dateOfBirth)
        User userInstance = new User(params);
        if (userInstance.save(failOnError: true))
            flash.message = "User registered successfully."
        else {
            flash.message = "Sorry for inconvineince. Please try again later."
        }
        render view: "/login/login"
    }

    def checkEmailUrl() {
        String renderStatus
        String emailId = params.email
        User user = User.findByEmail(emailId)
        if (user) {
            renderStatus = "false"
        }
        else
            renderStatus = "true"
        render(renderStatus)
    }

    def logout() {
        session.invalidate()
        redirect(action: 'login')
    }
}
