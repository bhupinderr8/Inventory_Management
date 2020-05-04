package com.example.inventory.Register;

public class RegisterEvent {

    public static int onSignUpSuccess = 0;
    public static int onSignUpError = 1;
    private int eventType;

    public RegisterEvent(int type) {
        if (type == onSignUpSuccess) {
            eventType = onSignUpSuccess;
        } else {
            eventType = onSignUpError;
        }
    }

    public int getEventType() {
        return eventType;
    }
}
