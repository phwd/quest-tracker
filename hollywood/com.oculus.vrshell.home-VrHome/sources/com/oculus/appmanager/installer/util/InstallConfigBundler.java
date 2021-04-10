package com.oculus.appmanager.installer.util;

import android.os.Bundle;
import android.util.Log;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.model.UpdateConfig;
import java.util.ArrayList;
import java.util.List;

public class InstallConfigBundler {
    private static final String TAG = InstallConfigBundler.class.getSimpleName();
    public static final long UNKNOWN_DOWNLOAD_SIZE = -1;

    public static Bundle toBundle(UpdateConfig updateConfig) {
        Bundle bundle = new Bundle();
        bundle.putString(UpdateConfigContract.KEY_IDENTIFIER, updateConfig.identifier);
        bundle.putLong(UpdateConfigContract.KEY_VERSION, updateConfig.versionCode);
        bundle.putString(UpdateConfigContract.KEY_TYPE, updateConfig.updateType.name());
        bundle.putString(UpdateConfigContract.KEY_DOWNLOAD_URL, updateConfig.downloadUrl);
        bundle.putLong(UpdateConfigContract.KEY_DOWNLOAD_SIZE, updateConfig.downloadSize);
        bundle.putBundle(UpdateConfigContract.KEY_EXTRAS, UpdateConfig.toBundle(updateConfig.extras));
        bundle.putBundle(UpdateConfigContract.KEY_REQUEST_HEADERS, UpdateConfig.toBundle(updateConfig.downloadHeaders));
        bundle.putString("key_id", updateConfig.oculusStoreId);
        bundle.putString(UpdateConfigContract.KEY_ACCESS_TOKEN, updateConfig.accessToken);
        bundle.putString(UpdateConfigContract.KEY_DOWNLOAD_NAME, updateConfig.downloadName);
        bundle.putString(UpdateConfigContract.KEY_DOWNLOAD_CHECKSUM, updateConfig.downloadChecksum);
        bundle.putString(UpdateConfigContract.KEY_DOWNLOAD_CHECKSUM_TYPE, updateConfig.downloadChecksumType);
        bundle.putString(UpdateConfigContract.KEY_SIGNATURE, updateConfig.signature);
        bundle.putString(UpdateConfigContract.KEY_EXT_SIGNATURE, updateConfig.externalSignatures);
        return bundle;
    }

    public static Bundle toBundle(List<UpdateConfig> updateConfigs) {
        Bundle bundle = new Bundle();
        for (int i = 0; i < updateConfigs.size(); i++) {
            bundle.putBundle(UpdateConfigContract.KEY_BUNDLE_ARRAY_PREFIX + i, toBundle(updateConfigs.get(i)));
        }
        return bundle;
    }

    public static List<UpdateConfig> toConfigList(Bundle bundle) {
        if (bundle == null) {
            Log.w(TAG, "null bundle");
            return null;
        }
        List<UpdateConfig> configs = new ArrayList<>();
        for (int i = 0; bundle.containsKey(UpdateConfigContract.KEY_BUNDLE_ARRAY_PREFIX + i); i++) {
            UpdateConfig config = toConfig(bundle.getBundle(UpdateConfigContract.KEY_BUNDLE_ARRAY_PREFIX + i));
            if (config == null) {
                return null;
            }
            configs.add(config);
        }
        return configs;
    }

    public static UpdateConfig toConfig(Bundle bundle) {
        if (bundle == null) {
            Log.w(TAG, "null bundle");
            return null;
        } else if (!bundle.containsKey(UpdateConfigContract.KEY_IDENTIFIER) || !bundle.containsKey(UpdateConfigContract.KEY_VERSION) || !bundle.containsKey(UpdateConfigContract.KEY_TYPE) || !bundle.containsKey(UpdateConfigContract.KEY_DOWNLOAD_URL) || !bundle.containsKey(UpdateConfigContract.KEY_DOWNLOAD_SIZE) || !bundle.containsKey(UpdateConfigContract.KEY_EXTRAS) || !bundle.containsKey(UpdateConfigContract.KEY_REQUEST_HEADERS)) {
            Log.w(TAG, "missing required args in bundle");
            return null;
        } else {
            return new UpdateConfig(bundle.getString(UpdateConfigContract.KEY_IDENTIFIER), bundle.getLong(UpdateConfigContract.KEY_VERSION), ApkUpdateInfoContract.UpdateType.valueOf(bundle.getString(UpdateConfigContract.KEY_TYPE)), bundle.getString(UpdateConfigContract.KEY_DOWNLOAD_URL), bundle.getLong(UpdateConfigContract.KEY_DOWNLOAD_SIZE), bundle.getString("key_id", null), bundle.getString(UpdateConfigContract.KEY_ACCESS_TOKEN, null), bundle.getString(UpdateConfigContract.KEY_DOWNLOAD_NAME, null), bundle.getString(UpdateConfigContract.KEY_DOWNLOAD_CHECKSUM, null), bundle.getString(UpdateConfigContract.KEY_DOWNLOAD_CHECKSUM_TYPE, null), bundle.getString(UpdateConfigContract.KEY_SIGNATURE, null), bundle.getString(UpdateConfigContract.KEY_EXT_SIGNATURE, null), UpdateConfig.fromBundle(bundle.getBundle(UpdateConfigContract.KEY_EXTRAS)), UpdateConfig.fromBundle(bundle.getBundle(UpdateConfigContract.KEY_REQUEST_HEADERS)));
        }
    }
}
