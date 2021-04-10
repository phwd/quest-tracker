package com.oculus.userserver.api.sharing;

public class SharingManagerContract {
    public static final String ARGUMENT_IS_ENABLED = "enabled";
    public static final String AUTHORITY = "com.oculus.userserver.sharingmanager";
    public static final String METHOD_GET_LIBRARY_SHARING_ENABLED = "get_library_sharing_enabled";
    public static final String METHOD_SET_LIBRARY_SHARING_ENABLED = "set_library_sharing_enabled";
    public static final String RESULT_CODE = "result_code";

    public enum ResultCode {
        ERROR_UNKNOWN(-1),
        RESULT_OK(0),
        CANT_CONNECT_TO_SERVICE(1),
        NETWORK_ERROR(2);
        
        public final int mCode;

        /* access modifiers changed from: public */
        ResultCode(int i) {
            this.mCode = i;
        }

        public static ResultCode fromCode(int i) {
            ResultCode[] values = values();
            for (ResultCode resultCode : values) {
                if (resultCode.mCode == i) {
                    return resultCode;
                }
            }
            return ERROR_UNKNOWN;
        }

        public int getCode() {
            return this.mCode;
        }
    }
}
