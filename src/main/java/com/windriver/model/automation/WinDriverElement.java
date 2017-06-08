package com.windriver.model.automation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.windriver.core.WinDriverClient;
import com.windriver.model.request.ActionControlRequest;
import com.windriver.model.request.ElementLocationControlRequest;
import com.windriver.model.response.ActionControlResponse;
import com.windriver.model.response.ElementLocationControlResponse;
import com.windriver.util.PropertyConditions;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WinDriverElement {

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

    private WinDriverClient winDriverClient;

    public WinDriverElement findOne(PropertyConditions conditions) {
        ElementLocationControlRequest request = new ElementLocationControlRequest();
        request.setTreeWalkerType("");
        request.setSearchScope(SearchScopeOption.SUBTREE);
        request.setFindOption(FindOption.FIND_FIRST);
        request.setParentWinDriverElementId(winDriverElementId);
        request.setConditionModels(conditions.getConditions());
        ElementLocationControlResponse response = winDriverClient.find(request);

        return response.getWinDriverElements().get(0);
    }

    public List<WinDriverElement> findAll(PropertyConditions conditions) {
        ElementLocationControlRequest request = new ElementLocationControlRequest();
        request.setTreeWalkerType("");
        request.setSearchScope(SearchScopeOption.SUBTREE);
        request.setFindOption(FindOption.FIND_ALL);
        request.setParentWinDriverElementId(winDriverElementId);
        request.setConditionModels(conditions.getConditions());
        return winDriverClient.find(request).getWinDriverElements();
    }

    public List<WinDriverElement> findAllChildrenItems() {
        ElementLocationControlRequest request = new ElementLocationControlRequest();
        request.setTreeWalkerType("");
        request.setSearchScope(SearchScopeOption.CHILDREN);
        request.setFindOption(FindOption.FIND_ALL);
        request.setParentWinDriverElementId(winDriverElementId);
        request.setConditionModels(new PropertyConditions(Property.TRUE_CONDITION, "").getConditions());
        return winDriverClient.find(request).getWinDriverElements();
    }

    public WinDriverElement click() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.CLICK);
        request.setWinDriverElementId(winDriverElementId);
        winDriverClient.interact(request);
        return this;
    }

    public WinDriverElement doubleClick() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.DOUBLE_CLICK);
        request.setWinDriverElementId(winDriverElementId);
        winDriverClient.interact(request);
        return this;
    }

    public WinDriverElement clickAtClickablePoint() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.CLICK_AT_CLICKABLE_POINT);
        request.setWinDriverElementId(winDriverElementId);
        winDriverClient.interact(request);
        return this;
    }

    public WinDriverElement sendKeys(String value) {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.SEND_KEYS);
        request.setValue(value);
        request.setWinDriverElementId(winDriverElementId);
        winDriverClient.interact(request);
        return this;
    }

    public String getText() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.GET_TEXT);
        request.setWinDriverElementId(winDriverElementId);
        ActionControlResponse response = winDriverClient.interact(request);
        return response.getEntity() != null ? response.getEntity().toString() : null;
    }

    public WinDriverElement moveMouse() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.MOVE_MOUSE);
        request.setWinDriverElementId(winDriverElementId);
        winDriverClient.interact(request);
        return this;
    }

    public WinDriverElement rightClick() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.RIGHT_CLICK);
        request.setWinDriverElementId(winDriverElementId);
        winDriverClient.interact(request);
        return this;
    }

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

    public void setWinDriverClient(WinDriverClient winDriverClient) {
        this.winDriverClient = winDriverClient;
    }

    public WinDriverClient getWinDriverClient() {
        return winDriverClient;
    }

    @Override
    public int hashCode() {
        return winDriverElementId != null ? winDriverElementId.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WinDriverElement that = (WinDriverElement) o;

        return nativeWindowHandle.equals(that.nativeWindowHandle);

    }

    @Override
    public String toString() {
        return "WinDriverElement{" +
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
