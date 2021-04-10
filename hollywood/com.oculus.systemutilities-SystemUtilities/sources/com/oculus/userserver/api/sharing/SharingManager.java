package com.oculus.userserver.api.sharing;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.oculus.userserver.api.sharing.SharingManagerContract;

public class SharingManager {
    private static final String TAG = SharingManager.class.getSimpleName();
    private final Context mContext;

    public SharingManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public boolean getLibrarySharingEnabled() {
        Bundle result = this.mContext.getContentResolver().call(SharingManagerContract.getContentUri(), "get_library_sharing_enabled", (String) null, (Bundle) null);
        if (result != null) {
            return result.getBoolean("enabled", false);
        }
        Log.e(TAG, "Could not resolve provider!");
        return false;
    }

    public void setLibrarySharingEnabled(boolean isLibrarySharingEnabled) throws SharingManagerException {
        Bundle result = this.mContext.getContentResolver().call(SharingManagerContract.getContentUri(), "set_library_sharing_enabled", (String) null, createArgsBundle(isLibrarySharingEnabled));
        if (result == null) {
            throw new SharingManagerException(SharingManagerContract.ResultCode.CANT_CONNECT_TO_SERVICE);
        }
        SharingManagerContract.ResultCode resultCode = SharingManagerContract.getResultCodeFromBundle(result);
        if (!SharingManagerContract.ResultCode.RESULT_OK.equals(resultCode)) {
            throw new SharingManagerException(resultCode);
        }
    }

    private static Bundle createArgsBundle(boolean isLibrarySharingEnabled) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("enabled", isLibrarySharingEnabled);
        return bundle;
    }

    public static class SharingManagerException extends Exception {
        private final SharingManagerContract.ResultCode mCode;

        public SharingManagerException(SharingManagerContract.ResultCode code) {
            this.mCode = code;
        }

        public SharingManagerContract.ResultCode getCode() {
            return this.mCode;
        }
    }
}
