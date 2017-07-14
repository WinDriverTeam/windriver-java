package ua.windriver.model.automation;

import com.fasterxml.jackson.annotation.JsonCreator;
import ua.windriver.core.WinDriverElementController;
import ua.windriver.core.WinDriverService;
import ua.windriver.util.PropertyConditions;

import java.util.List;

public class WinDriverElement {

    protected WinDriverElementController controller;
    protected AutomationElementWrapper element;

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

    public <T extends WinDriverElement> T findOne(PropertyConditions conditions) {
        return controller.findOne(conditions);
    }

    public <T extends WinDriverElement> List<T> findAll(PropertyConditions conditions) {
        return controller.findAll(conditions);
    }

    public <T extends WinDriverElement> List<T> findAllChildrenItems() {
        return controller.findAllChildrenItems();
    }

    public WinDriverElement click() {
        controller.click();
        return this;
    }

    public WinDriverElement doubleClick() {
        controller.doubleClick();
        return this;
    }

    public WinDriverElement clickAtClickablePoint() {
        controller.clickAtClickablePoint();
        return this;
    }

    public WinDriverElement sendKeys(String value) {
        controller.sendKeys(value);
        return this;
    }

    public String getText() {
        return String.valueOf(controller.getText().getEntity());
    }

    public WinDriverElement moveMouse() {
        controller.moveMouse();
        return this;
    }

    public WinDriverElement rightClick() {
        controller.rightClick();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WinDriverElement element1 = (WinDriverElement) o;

        return element != null ? element.equals(element1.element) : element1.element == null;

    }

    @Override
    public int hashCode() {
        return element != null ? element.hashCode() : 0;
    }
}
