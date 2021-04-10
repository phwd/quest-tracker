package com.oculus.userserver.api.sharing;

import android.net.Uri;
import android.os.Bundle;
import com.facebook.common.util.UriUtil;

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

        private ResultCode(int i) {
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

    public static ResultCode getResultCodeFromBundle(Bundle bundle) {
        return ResultCode.fromCode(bundle.getInt("result_code", ResultCode.ERROR_UNKNOWN.getCode()));
    }

    public static void putResultCodeToBundle(Bundle bundle, ResultCode resultCode) {
        bundle.putInt("result_code", resultCode.getCode());
    }

    public static Uri getContentUri() {
        return new Uri.Builder().scheme(UriUtil.LOCAL_CONTENT_SCHEME).authority(AUTHORITY).build();
    }
}
