package ua.windriver.model.automation;

public enum FindOption implements WinDriverOption {

    FIND_FIRST("FindFirst"),
    FIND_ALL("FindAll");

    private String option;

    FindOption(String option) {
        this.option = option;
    }

    public String get() {
        return option;
    }
}
