package com.windriver.model.automation;

public enum Action implements WinDriverOption {
    CLICK("Click"),
    SEND_KEYS("SendKeys"),
    DOUBLE_CLICK("DoubleClick"),
    CLICK_AT_CLICKABLE_POINT("ClickAtClickablePoint"),
    GET_TEXT("GetText"),
    MOVE_MOUSE("MoveMouse"),
    RIGHT_CLICK("RightClick"),
    ;

    private String action;

    Action(String action) {
        this.action = action;
    }

    public String get() {
        return action;
    }
}
