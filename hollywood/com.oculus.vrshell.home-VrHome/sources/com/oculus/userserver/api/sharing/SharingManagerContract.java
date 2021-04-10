package com.oculus.userserver.api.sharing;

import android.net.Uri;
import android.os.Bundle;

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
        
        private final int mCode;

        private ResultCode(int code) {
            this.mCode = code;
        }

        public static ResultCode fromCode(int code) {
            ResultCode[] values = values();
            for (ResultCode resultCode : values) {
                if (resultCode.mCode == code) {
                    return resultCode;
                }
            }
            return ERROR_UNKNOWN;
        }

        public int getCode() {
            return this.mCode;
        }
    }

    public static ResultCode getResultCodeFromBundle(Bundle result) {
        return ResultCode.fromCode(result.getInt(RESULT_CODE, ResultCode.ERROR_UNKNOWN.getCode()));
    }

    public static void putResultCodeToBundle(Bundle bundle, ResultCode resultCode) {
        bundle.putInt(RESULT_CODE, resultCode.getCode());
    }

    public static Uri getContentUri() {
        return new Uri.Builder().scheme("content").authority(AUTHORITY).build();
    }
}
