package com.boilerman.signupV3;

public class EntryNotFoundException extends RuntimeException{

    EntryNotFoundException(Long id) {
        super("Entry not found");
    }
}
