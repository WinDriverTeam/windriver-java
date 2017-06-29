package ua.windriver.model.automation;

public enum StartMethodNameOption implements WinDriverOption {

    LAUNCH_BY_EXECUTABLE("LaunchByExecutable"),
    ATTACH_TO_PROCESS_BY_PROCESS_NAME("AttachToProcessByProcessName"),
    ATTACH_TO_PROCESS_BY_PROCESS_ID("AttachToProcessByProcessId"),
    ATTACH_OR_LAUNCH_BY_PROCESS_NAME("AttachOrLaunchByProcessName");

    private String name;

    StartMethodNameOption(String option) {
        this.name = option;
    }

    public String get() {
        return name;
    }
}
