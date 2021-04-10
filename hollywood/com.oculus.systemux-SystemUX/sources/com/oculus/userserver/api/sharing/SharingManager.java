package com.oculus.userserver.api.sharing;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.oculus.userserver.api.sharing.SharingManagerContract;

public class SharingManager {
    private static final String TAG = "SharingManager";
    private final Context mContext;

    public SharingManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public boolean getLibrarySharingEnabled() {
        Bundle call = this.mContext.getContentResolver().call(SharingManagerContract.getContentUri(), SharingManagerContract.METHOD_GET_LIBRARY_SHARING_ENABLED, (String) null, (Bundle) null);
        if (call != null) {
            return call.getBoolean(SharingManagerContract.ARGUMENT_IS_ENABLED, false);
        }
        Log.e(TAG, "Could not resolve provider!");
        return false;
    }

    public void setLibrarySharingEnabled(boolean z) throws SharingManagerException {
        Bundle call = this.mContext.getContentResolver().call(SharingManagerContract.getContentUri(), SharingManagerContract.METHOD_SET_LIBRARY_SHARING_ENABLED, (String) null, createArgsBundle(z));
        if (call != null) {
            SharingManagerContract.ResultCode resultCodeFromBundle = SharingManagerContract.getResultCodeFromBundle(call);
            if (!SharingManagerContract.ResultCode.RESULT_OK.equals(resultCodeFromBundle)) {
                throw new SharingManagerException(resultCodeFromBundle);
            }
            return;
        }
        throw new SharingManagerException(SharingManagerContract.ResultCode.CANT_CONNECT_TO_SERVICE);
    }

    private static Bundle createArgsBundle(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(SharingManagerContract.ARGUMENT_IS_ENABLED, z);
        return bundle;
    }

    public static class SharingManagerException extends Exception {
        private final SharingManagerContract.ResultCode mCode;

        public SharingManagerException(SharingManagerContract.ResultCode resultCode) {
            this.mCode = resultCode;
        }

        public SharingManagerContract.ResultCode getCode() {
            return this.mCode;
        }
    }
}
