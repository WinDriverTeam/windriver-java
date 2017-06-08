package com.windriver.util;

import com.windriver.model.automation.Condition;
import com.windriver.model.automation.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyConditions {

    private List<Condition> conditions;

    public PropertyConditions() {
        conditions = new ArrayList<>();
    }

    public PropertyConditions(Property property, String value) {
        conditions = new ArrayList<>();
        conditions.add(new Condition(property, value));
    }

    public PropertyConditions add(Property property, String value) {
        conditions.add(new Condition(property, value));
        return this;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
