package ua.windriver.core;

import ua.windriver.model.automation.*;
import ua.windriver.model.request.ActionControlRequest;
import ua.windriver.model.request.ElementLocationControlRequest;
import ua.windriver.model.response.ActionControlResponse;
import ua.windriver.util.PropertyConditions;

import java.util.List;
import java.util.Map;

public class WinDriverElementController {

    private WinDriverService service;
    private String parentElementId;

    public WinDriverService getService() {
        return service;
    }

    public WinDriverElementController(WinDriverService service, String parentElementId) {
        this.service = service;
        this.parentElementId = parentElementId;
    }

    public <T extends WinDriverElement> T findOne(PropertyConditions conditions) {
        ElementLocationControlRequest request = new ElementLocationControlRequest();
        request.setTreeWalkerType("");
        request.setSearchScope(SearchScopeOption.SUBTREE);
        request.setFindOption(FindOption.FIND_FIRST);
        request.setParentWinDriverElementId(parentElementId);
        request.setConditionModels(conditions.getConditions());
        Map result = service.find(request).getPayload().get(0);
        return service.convertMapToWinDriverObject(result, service.getWinDriverElementType());
    }

    public <T extends WinDriverElement> List<T> findAll(PropertyConditions conditions) {
        ElementLocationControlRequest request = new ElementLocationControlRequest();
        request.setTreeWalkerType("");
        request.setSearchScope(SearchScopeOption.SUBTREE);
        request.setFindOption(FindOption.FIND_ALL);
        request.setParentWinDriverElementId(parentElementId);
        request.setConditionModels(conditions.getConditions());
        List<Map> resultSet = service.find(request).getPayload();
        return service.convertResultSet(resultSet, service.getWinDriverElementType());
    }

    public <T extends WinDriverElement> List<T> findAllChildrenItems() {
        ElementLocationControlRequest request = new ElementLocationControlRequest();
        request.setTreeWalkerType("");
        request.setSearchScope(SearchScopeOption.CHILDREN);
        request.setFindOption(FindOption.FIND_ALL);
        request.setParentWinDriverElementId(parentElementId);
        request.setConditionModels(new PropertyConditions(Property.TRUE_CONDITION, "").getConditions());
        List<Map> resultSet = service.find(request).getPayload();
        return service.convertResultSet(resultSet, service.getWinDriverElementType());
    }

    public ActionControlResponse click() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.CLICK);
        request.setWinDriverElementId(parentElementId);
        return service.interact(request);
    }

    public ActionControlResponse doubleClick() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.DOUBLE_CLICK);
        request.setWinDriverElementId(parentElementId);
        return service.interact(request);
    }

    public ActionControlResponse clickAtClickablePoint() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.CLICK_AT_CLICKABLE_POINT);
        request.setWinDriverElementId(parentElementId);
        return service.interact(request);
    }

    public ActionControlResponse sendKeys(String value) {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.SEND_KEYS);
        request.setValue(value);
        request.setWinDriverElementId(parentElementId);
        return service.interact(request);
    }

    public ActionControlResponse getText() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.GET_TEXT);
        request.setWinDriverElementId(parentElementId);
        return service.interact(request);
    }

    public ActionControlResponse moveMouse() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.MOVE_MOUSE);
        request.setWinDriverElementId(parentElementId);
        return service.interact(request);
    }

    public ActionControlResponse rightClick() {
        ActionControlRequest request = new ActionControlRequest();
        request.setAction(Action.RIGHT_CLICK);
        request.setWinDriverElementId(parentElementId);
        return service.interact(request);
    }
}
