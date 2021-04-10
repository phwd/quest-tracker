package com.oculus.userserver.api.user;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public final class OculusUserBundler {
    public static OculusUser unbundleUser(Bundle bundle) {
        if (!bundle.containsKey("user_id")) {
            throw new IllegalArgumentException("userId be specified");
        } else if (bundle.getString("user_name") == null) {
            throw new IllegalArgumentException("userName must not be null");
        } else if (bundle.getString("picture_uri") == null) {
            throw new IllegalArgumentException("pictureUri must not be null");
        } else if (bundle.containsKey("creation_time")) {
            return new OculusUser(bundle.getInt("user_id"), bundle.getString("user_name"), bundle.getString("picture_uri"), bundle.getLong("creation_time"));
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
}
