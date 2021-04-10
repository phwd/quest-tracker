package com.oculus.userserver.api.user;

import android.os.Bundle;
import com.oculus.userserver.api.user.SparseOculusUser;
import java.util.ArrayList;
import java.util.List;

public final class OculusUserBundler {
    private static final String KEY_CREATION_TIME = "creation_time";
    private static final String KEY_PICTURE_URI = "picture_uri";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";

    public static Bundle bundleUser(OculusUser user) {
        Bundle bundle = new Bundle();
        bundle.putInt("user_id", user.getUserId());
        bundle.putString("user_name", user.getUserName());
        bundle.putString(KEY_PICTURE_URI, user.getPictureUri());
        bundle.putLong(KEY_CREATION_TIME, user.getCreationTime());
        return bundle;
    }

    public static OculusUser unbundleUser(Bundle bundle) {
        if (!bundle.containsKey("user_id")) {
            throw new IllegalArgumentException("userId be specified");
        } else if (bundle.getString("user_name") == null) {
            throw new IllegalArgumentException("userName must not be null");
        } else if (bundle.getString(KEY_PICTURE_URI) == null) {
            throw new IllegalArgumentException("pictureUri must not be null");
        } else if (bundle.containsKey(KEY_CREATION_TIME)) {
            return new OculusUser(bundle.getInt("user_id"), bundle.getString("user_name"), bundle.getString(KEY_PICTURE_URI), bundle.getLong(KEY_CREATION_TIME));
        } else {
            throw new IllegalArgumentException("creationTime must not be null");
        }
    }

    public static List<OculusUser> unbundleUsers(List<Bundle> listBundles) {
        List<OculusUser> listUsers = new ArrayList<>(listBundles.size());
        for (Bundle bundle : listBundles) {
            listUsers.add(unbundleUser(bundle));
        }
        return listUsers;
    }

    public static Bundle bundleSparseUser(SparseOculusUser sparseUser) {
        Bundle bundle = new Bundle();
        if (sparseUser.getUserId() != null) {
            bundle.putInt("user_id", sparseUser.getUserId().intValue());
        }
        if (sparseUser.getUserName() != null) {
            bundle.putString("user_name", sparseUser.getUserName());
        }
        if (sparseUser.getPictureUri() != null) {
            bundle.putString(KEY_PICTURE_URI, sparseUser.getPictureUri());
        }
        return bundle;
    }

    public static SparseOculusUser unbundleSparseUser(Bundle bundle) {
        SparseOculusUser.Builder builder = new SparseOculusUser.Builder();
        if (bundle.containsKey("user_id")) {
            builder.setUserId(bundle.getInt("user_id"));
        }
        if (bundle.getString("user_name") != null) {
            builder.setUserName(bundle.getString("user_name"));
        }
        if (bundle.getString(KEY_PICTURE_URI) != null) {
            builder.setPictureUri(bundle.getString(KEY_PICTURE_URI));
        }
        return builder.build();
    }
}
