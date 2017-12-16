package com.roman.core;

public class ProcessResponse {
    private boolean success;
    private boolean handled;

    public ProcessResponse(boolean success, boolean handled) {
        this.success = success;
        this.handled = handled;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean handled() {
        return handled;
    }

}
