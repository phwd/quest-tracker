package com.qualcomm.wfd;

public enum WfdEnums$ErrorType {
    UNKNOWN(-1),
    INVALID_ARG(-2),
    HDMI_CABLE_CONNECTED(-3),
    OPERATION_TIMED_OUT(-4),
    ALREADY_INITIALIZED(-10),
    NOT_INITIALIZED(-11),
    SESSION_IN_PROGRESS(-12),
    INCORRECT_STATE_FOR_OPERATION(-13),
    NOT_SINK_DEVICE(-14),
    NOT_SOURCE_DEVICE(-15),
    UIBC_NOT_ENABLED(-20),
    UIBC_ALREADY_ENABLED(-21);
    
    private final int code;

    private WfdEnums$ErrorType(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
