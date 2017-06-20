package ua.windriver.model.automation;

public enum SearchScopeOption implements WinDriverOption {
    AUTOMATION_ELEMENT("AutomationElement"),
    CHILDREN("Children"),
    DESCENDANTS("Descendants"),
    SUBTREE("Subtree"),
    PARENT("Parent"),
    ANCESTORS("Ancestors");

    private String option;

    SearchScopeOption(String option) {
        this.option = option;
    }

    public String get() {
        return option;
    }
}
