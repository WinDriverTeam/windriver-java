package com.windriver.model.automation;

public enum Property implements WinDriverOption{

    IS_CONTROL_ELEMENT_PROPERTY("IsControlElementProperty"),
    CONTROL_TYPE_PROPERTY("ControlTypeProperty"),
    IS_CONTENT_ELEMENT_PROPERTY("IsContentElementProperty"),
    LABELED_BY_PROPERTY_PROPERTY("LabeledByProperty"),
    NATIVE_WINDOW_HANDLE_PROPERTY("NativeWindowHandleProperty"),
    AUTOMATION_ID_PROPERTY("AutomationIdProperty"),
    ITEM_TYPE_PROPERTY("ItemTypeProperty"),
    IS_PASSWORD_PROPERTY("IsPasswordProperty"),
    LOCALIZED_CONTROL_TYPE_PROPERTY("LocalizedControlTypeProperty"),
    NAME_PROPERTY("NameProperty"),
    ACCELERATOR_KEY_PROPERTY("AcceleratorKeyProperty"),
    ACCESS_KEY_PROPERTY("AccessKeyProperty"),
    HAS_KEYBOARD_FOCUS_PROPERTY("HasKeyboardFocusProperty"),
    IS_KEYBOARD_FOCUSABLE_PROPERTY("IsKeyboardFocusableProperty"),
    IS_ENABLED_PROPERTY("IsEnabledProperty"),
    BOUNDING_RECTANGLE_PROPERTY("BoundingRectangleProperty"),
    PROCESS_ID_PROPERTY("ProcessIdProperty"),
    RUNTIME_ID_PROPERTY("RuntimeIdProperty"),
    CLASS_NAME_PROPERTY("ClassNameProperty"),
    HELP_TEXT_PROPERTY("HelpTextProperty"),
    CLICKABLE_POINT_PROPERTY("ClickablePointProperty"),
    CULTURE_PROPERTY("CultureProperty"),
    IS_OFFSCREEN_PROPERTY("IsOffscreenProperty"),
    ORIENTATION_PROPERTY("OrientationProperty"),
    FRAMEWORK_ID_PROPERTY("FrameworkIdProperty"),
    IS_REQUIRED_FOR_FORM_PROPERTY("IsRequiredForFormProperty"),
    ITEM_STATUS_PROPERTY("ItemStatusProperty"),
    TRUE_CONDITION("TrueCondition");

    private String property;

    Property(String property) {
        this.property = property;
    }

    public String get() {
        return property;
    }
}
