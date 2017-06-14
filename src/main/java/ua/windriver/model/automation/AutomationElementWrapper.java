package ua.windriver.model.automation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutomationElementWrapper {

    @JsonProperty("LocalizedControlType")
    private String localizedControlType;

    @JsonProperty("Orientation")
    private Long orientation;

    @JsonProperty("NativeWindowHandle")
    private Long nativeWindowHandle;

    @JsonProperty("AccessKey")
    private String accessKey;

    @JsonProperty("ProcessId")
    private Long processId;

    @JsonProperty("IsContentElement")
    private Boolean isContentElement;

    @JsonProperty("IsOffscreen")
    private Boolean isOffscreen;

    @JsonProperty("ItemType")
    private String itemType;

    @JsonProperty("ItemStatus")
    private String itemStatus;

    @JsonProperty("IsPassword")
    private Boolean isPassword;

    @JsonProperty("ControlType")
    private String controlType;

    @JsonProperty("HelpText")
    private String helpText;

    @JsonProperty("WinDriverElementId")
    private String winDriverElementId;

    @JsonProperty("IsEnabled")
    private Boolean isEnabled;

    @JsonProperty("FrameworkId")
    private String frameworkId;

    @JsonProperty("AcceleratorKey")
    private String acceleratorKey;

    @JsonProperty("BoundingRectangle")
    private String boundingRectangle;

    @JsonProperty("IsRequiredForForm")
    private Boolean isRequiredForForm;

    @JsonProperty("AutomationId")
    private String automationId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("HasKeyboardFocus")
    private Boolean hasKeyboardFocus;

    @JsonProperty("ClassName")
    private String className;

    @JsonProperty("IsKeyboardFocusable")
    private Boolean isKeyboardFocusable;

    @JsonProperty("IsControlElement")
    private Boolean isControlElement;

    public void setLocalizedControlType(String localizedControlType) {
        this.localizedControlType = localizedControlType;
    }

    public void setOrientation(Long orientation) {
        this.orientation = orientation;
    }

    public void setNativeWindowHandle(Long nativeWindowHandle) {
        this.nativeWindowHandle = nativeWindowHandle;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public void setContentElement(Boolean contentElement) {
        isContentElement = contentElement;
    }

    public void setOffscreen(Boolean offscreen) {
        isOffscreen = offscreen;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public void setPassword(Boolean password) {
        isPassword = password;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public void setWinDriverElementId(String winDriverElementId) {
        this.winDriverElementId = winDriverElementId;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public void setFrameworkId(String frameworkId) {
        this.frameworkId = frameworkId;
    }

    public void setAcceleratorKey(String acceleratorKey) {
        this.acceleratorKey = acceleratorKey;
    }

    public void setBoundingRectangle(String boundingRectangle) {
        this.boundingRectangle = boundingRectangle;
    }

    public void setRequiredForForm(Boolean requiredForForm) {
        isRequiredForForm = requiredForForm;
    }

    public void setAutomationId(String automationId) {
        this.automationId = automationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHasKeyboardFocus(Boolean hasKeyboardFocus) {
        this.hasKeyboardFocus = hasKeyboardFocus;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setKeyboardFocusable(Boolean keyboardFocusable) {
        isKeyboardFocusable = keyboardFocusable;
    }

    public void setControlElement(Boolean controlElement) {
        isControlElement = controlElement;
    }

    public String getLocalizedControlType() {
        return localizedControlType;
    }

    public Long getOrientation() {
        return orientation;
    }

    public Long getNativeWindowHandle() {
        return nativeWindowHandle;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public Long getProcessId() {
        return processId;
    }

    public Boolean getContentElement() {
        return isContentElement;
    }

    public Boolean getOffscreen() {
        return isOffscreen;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public Boolean getPassword() {
        return isPassword;
    }

    public String getControlType() {
        return controlType;
    }

    public String getHelpText() {
        return helpText;
    }

    public String getWinDriverElementId() {
        return winDriverElementId;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public String getFrameworkId() {
        return frameworkId;
    }

    public String getAcceleratorKey() {
        return acceleratorKey;
    }

    public String getBoundingRectangle() {
        return boundingRectangle;
    }

    public Boolean getRequiredForForm() {
        return isRequiredForForm;
    }

    public String getAutomationId() {
        return automationId;
    }

    public String getName() {
        return name;
    }

    public Boolean getHasKeyboardFocus() {
        return hasKeyboardFocus;
    }

    public String getClassName() {
        return className;
    }

    public Boolean getKeyboardFocusable() {
        return isKeyboardFocusable;
    }

    public Boolean getControlElement() {
        return isControlElement;
    }

    @Override
    public int hashCode() {
        return winDriverElementId != null ? winDriverElementId.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutomationElementWrapper that = (AutomationElementWrapper) o;

        return nativeWindowHandle.equals(that.nativeWindowHandle);

    }

    @Override
    public String toString() {
        return "AutomationElementWrapper{" +
                "localizedControlType='" + localizedControlType + '\'' +
                ", orientation='" + orientation + '\'' +
                ", nativeWindowHandle='" + nativeWindowHandle + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", processId='" + processId + '\'' +
                ", isContentElement='" + isContentElement + '\'' +
                ", isOffscreen='" + isOffscreen + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemStatus='" + itemStatus + '\'' +
                ", isPassword='" + isPassword + '\'' +
                ", controlType='" + controlType + '\'' +
                ", helpText='" + helpText + '\'' +
                ", winDriverElementId='" + winDriverElementId + '\'' +
                ", isEnabled='" + isEnabled + '\'' +
                ", frameworkId='" + frameworkId + '\'' +
                ", acceleratorKey='" + acceleratorKey + '\'' +
                ", boundingRectangle='" + boundingRectangle + '\'' +
                ", isRequiredForForm='" + isRequiredForForm + '\'' +
                ", automationId='" + automationId + '\'' +
                ", name='" + name + '\'' +
                ", hasKeyboardFocus='" + hasKeyboardFocus + '\'' +
                ", className='" + className + '\'' +
                ", isKeyboardFocusable='" + isKeyboardFocusable + '\'' +
                ", isControlElement='" + isControlElement + '\'' +
                '}';
    }
}
