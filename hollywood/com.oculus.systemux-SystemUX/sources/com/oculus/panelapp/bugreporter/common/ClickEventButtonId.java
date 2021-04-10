package com.oculus.panelapp.bugreporter.common;

public enum ClickEventButtonId {
    OS_STEP_CANCEL("os_step_cancel"),
    OS_STEP_CONTINUE("os_step_continue"),
    OS_STEP_UPDATE("os_step_update"),
    DESCRIPTION_STEP_CANCEL("description_step_cancel"),
    DESCRIPTION_STEP_CONTINUE("description_step_continue"),
    DESCRIPTION_STEP_CATEGORY("description_step_category"),
    DESCRIPTION_STEP_INCLUDE_SCREENSHOT("description_step_screenshot"),
    MEDIA_STEP_BACK("media_step_back"),
    MEDIA_STEP_CANCEL("media_step_cancel"),
    MEDIA_STEP_CONTINUE("media_step_continue"),
    DATA_STEP_BACK("data_step_back"),
    DATA_STEP_CANCEL("data_step_cancel"),
    DATA_STEP_INCLUDE_LOGS("data_step_include_logs"),
    DATA_STEP_SUBMIT("data_step_submit"),
    CONFIRMATION_STEP_CLOSE("confirmation_step_close"),
    CANCEL_STEP_CANCEL("cancel_step_cancel"),
    CANCEL_STEP_CONFIRM("cancel_step_confirm"),
    BUG_REPORT_SUBMITTED("bug_report_submitted");
    
    private String mTelemetryButtonId;

    private ClickEventButtonId(String str) {
        this.mTelemetryButtonId = str;
    }

    public String getTelemetryButtonId() {
        return this.mTelemetryButtonId;
    }

    public ClickEventButtonId getSubButtonId(String str) {
        return valueOf(toString() + "_" + str);
    }
}
