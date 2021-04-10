package com.qualcomm.wfd;

public enum WfdEnums$WFDDeviceType {
    SOURCE(0),
    PRIMARY_SINK(1),
    SECONDARY_SINK(2),
    SOURCE_PRIMARY_SINK(3),
    UNKNOWN(4);
    
    private final int code;

    private WfdEnums$WFDDeviceType(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
