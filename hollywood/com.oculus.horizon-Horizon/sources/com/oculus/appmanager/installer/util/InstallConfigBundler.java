package com.oculus.appmanager.installer.util;

import android.os.Bundle;
import com.oculus.appmanager.model.UpdateConfig;

public class InstallConfigBundler {
    public static final String TAG = "InstallConfigBundler";
    public static final long UNKNOWN_DOWNLOAD_SIZE = -1;

    public static Bundle A00(UpdateConfig updateConfig) {
        Bundle bundle = new Bundle();
        bundle.putString(UpdateConfigContract.KEY_IDENTIFIER, updateConfig.identifier);
        bundle.putLong(UpdateConfigContract.KEY_VERSION, updateConfig.versionCode);
        bundle.putString(UpdateConfigContract.KEY_TYPE, updateConfig.updateType.name());
        bundle.putString(UpdateConfigContract.KEY_DOWNLOAD_URL, updateConfig.downloadUrl);
        bundle.putLong(UpdateConfigContract.KEY_DOWNLOAD_SIZE, updateConfig.downloadSize);
        bundle.putBundle(UpdateConfigContract.KEY_EXTRAS, UpdateConfig.A00(updateConfig.extras));
        bundle.putBundle(UpdateConfigContract.KEY_REQUEST_HEADERS, UpdateConfig.A00(updateConfig.downloadHeaders));
        bundle.putString("key_id", updateConfig.oculusStoreId);
        bundle.putString(UpdateConfigContract.KEY_ACCESS_TOKEN, updateConfig.accessToken);
        bundle.putString(UpdateConfigContract.KEY_DOWNLOAD_NAME, updateConfig.downloadName);
        bundle.putString(UpdateConfigContract.KEY_DOWNLOAD_CHECKSUM, updateConfig.downloadChecksum);
        bundle.putString(UpdateConfigContract.KEY_DOWNLOAD_CHECKSUM_TYPE, updateConfig.downloadChecksumType);
        bundle.putString(UpdateConfigContract.KEY_SIGNATURE, updateConfig.signature);
        bundle.putString(UpdateConfigContract.KEY_EXT_SIGNATURE, updateConfig.externalSignatures);
        return bundle;
    }
}
