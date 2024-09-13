/*
    1. A class Should have one and only one reason to change
    2. Class Must have only one responsibility
*/

class UserManager_ {
    public void authenticateUser(String email, String password) {
        // Authentication logic
    }

    public void sendEmailNotification(String email) {
        // Email sending logic
    }
}

/*
    1. Above code violates SRP as if we try to change authentication logic it may affect email sending logic
*/

class UserAuthenticator {
    public void authenticateUser(String email, String password) {
        // Authentication logic
    } 
}

class EmailSender {
    public void sendEmailNotification(String email) {
        // Email sending logic
    }
}