package com.oculus.userserver.api.sharing;

import android.content.Context;
import com.oculus.userserver.api.sharing.SharingManagerContract;

public class SharingManager {
    public static final String TAG = "SharingManager";
    public final Context mContext;

    public static class SharingManagerException extends Exception {
        public final SharingManagerContract.ResultCode mCode;

        public SharingManagerException(SharingManagerContract.ResultCode resultCode) {
            this.mCode = resultCode;
        }
    }
}
