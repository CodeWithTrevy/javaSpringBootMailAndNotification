
package com.CodeWithTrevy.demo.exception;

public class UsernameAlreadyExistsException  extends RuntimeException{
    public UsernameAlreadyExistsException(String email){
        super("Username  already exists: " + email);
    }
}
