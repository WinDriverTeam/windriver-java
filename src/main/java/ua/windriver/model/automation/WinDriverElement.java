package ua.windriver.model.automation;

import com.fasterxml.jackson.annotation.JsonCreator;
import ua.windriver.core.WinDriverElementController;
import ua.windriver.core.WinDriverService;
import ua.windriver.model.response.ActionControlResponse;
import ua.windriver.util.PropertyConditions;

import java.util.List;

public class WinDriverElement {

    private WinDriverElementController controller;
    private AutomationElementWrapper element;

    @JsonCreator
    public WinDriverElement(AutomationElementWrapper element) {
        this.element = element;
    }

    public void initController(WinDriverService service) {
        controller = new WinDriverElementController(service, element.getWinDriverElementId());
    }

    public WinDriverElementController getController() {
        return controller;
    }

    public AutomationElementWrapper getElement() {
        return element;
    }

    public void setController(WinDriverElementController controller) {
        this.controller = controller;
    }

    public void setElement(AutomationElementWrapper element) {
        this.element = element;
    }

    public WinDriverElement findOne(PropertyConditions conditions) {
        return controller.findOne(conditions).getWinDriverElements().get(0);
    }

    public List<WinDriverElement> findAll(PropertyConditions conditions) {
        return controller.findAll(conditions).getWinDriverElements();
    }

    public List<WinDriverElement> findAllChildrenItems() {
        return controller.findAllChildrenItems().getWinDriverElements();
    }

    public ActionControlResponse click() {
        return controller.click();
    }

    public ActionControlResponse doubleClick() {
        return controller.doubleClick();
    }

    public ActionControlResponse clickAtClickablePoint() {
        return controller.clickAtClickablePoint();
    }

    public ActionControlResponse sendKeys(String value) {
        return controller.sendKeys(value);
    }

    public String getText() {
        return String.valueOf(controller.getText().getEntity());
    }

    public ActionControlResponse moveMouse() {
        return controller.moveMouse();
    }

    public ActionControlResponse rightClick() {
        return controller.rightClick();
    }
}
