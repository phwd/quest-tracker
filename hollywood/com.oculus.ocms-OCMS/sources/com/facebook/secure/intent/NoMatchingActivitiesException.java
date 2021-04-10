package com.facebook.secure.intent;

import java.util.Collections;
import java.util.List;

public class NoMatchingActivitiesException extends Throwable {
    private final List<String> mReportedErrors;

    public NoMatchingActivitiesException(List<String> list) {
        this.mReportedErrors = Collections.unmodifiableList(list);
    }

    public List<String> getReportedErrors() {
        return this.mReportedErrors;
    }
}
