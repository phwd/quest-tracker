package com.oculus.userserver.api.user;

import android.os.Bundle;

public final class OculusUserBundler {
    public static final String KEY_CREATION_TIME = "creation_time";
    public static final String KEY_IS_SUSPENDED = "is_suspended";
    public static final String KEY_PICTURE_URI = "picture_uri";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "user_name";

    public static Bundle A00(OculusUser oculusUser) {
        Bundle bundle = new Bundle();
        bundle.putInt("user_id", oculusUser.mUserId);
        bundle.putString("user_name", oculusUser.mUserName);
        bundle.putString(KEY_PICTURE_URI, oculusUser.mPictureUri);
        bundle.putLong(KEY_CREATION_TIME, oculusUser.mCreationTime);
        bundle.putBoolean(KEY_IS_SUSPENDED, oculusUser.mIsSuspended);
        return bundle;
    }
}
