package com.iver.blpslab1.config;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.security.Principal;
import java.util.Map;

public class CustomLoginModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> sharedState;
    private Map<String, ?> options;

    private Principal userPrincipal;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
//        if (callbackHandler == null) {
//            throw new LoginException("No callback handler is available");
//        }
//
//        NameCallback nameCallback = new NameCallback("Username:");
//        PasswordCallback passwordCallback = new PasswordCallback("Password:", false);
//
//        try {
//            callbackHandler.handle(new Callback[] { nameCallback, passwordCallback });
//        } catch (UnsupportedCallbackException ex) {
//            throw new LoginException(ex.getMessage());
//        } catch (Exception ex) {
//            throw new LoginException(ex.getMessage());
//        }
//
//        String username = nameCallback.getName();
//        char[] password = passwordCallback.getPassword();
//
//        // perform authentication here and set userPrincipal if authenticated
//        boolean authenticated = authenticateUser(username, password);
//        if (authenticated) {
//            userPrincipal = new CustomPrincipal(username);
//        } else {
//            throw new LoginException("Authentication failed");
//        }

        return true;
    }

    private boolean authenticateUser(String username, char[] password) {
        // perform authentication logic here
        // return true if authenticated, false otherwise
        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        if (userPrincipal == null) {
            return false;
        } else {
            subject.getPrincipals().add(userPrincipal);
            return true;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        return true;
    }

}