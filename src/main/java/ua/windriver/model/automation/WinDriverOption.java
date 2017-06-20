package ua.windriver.model.automation;

import com.fasterxml.jackson.annotation.JsonValue;

public interface WinDriverOption {

    @JsonValue
    public String get();
}
