package com.example.inventory.Login;

public class LoginEvent {
    public static int onSignInSuccess=0;
    public static int onSignInError=1;
    private int eventType;
    private String error;
    private String username;

    public LoginEvent(int type, String error, String username)
    {
        this.error = error;
        this.username = username;
        if(type==onSignInSuccess)
        {
            eventType=onSignInSuccess;
        }
        else
        {
            eventType=onSignInError;
        }
    }

    public int getEventType()
    {
        return eventType;
    }

    public String getError() {
        return null;
    }

    public String getUserName() {
        return username;
    }
}
