package com.oculus.ocms.library.provider.contract;

import android.content.pm.Signature;
import android.net.Uri;
import android.text.TextUtils;
import com.oculus.appmanager.info.model.InstallerResult;
import java.util.List;

public class OCMSLibraryContract {
    public static final String APPS_PATH = "apps";
    public static final String ASSETS_PATH = "assets";
    public static final String ASSETS_PATH_BY_FILENAME = "filename";
    public static final String ASSETS_PATH_BY_ID = "id";
    public static final String AUTHORITY = "com.oculus.ocms.library";
    public static final String DEPLOYMENT_PATH = "deployment";
    public static final String DOWNLOAD_PATH = "download";
    public static final int ERROR_CODE_SUCCESS = -2;
    public static final String EXTRA_ASSET_ID = "asset_id";
    public static final String EXTRA_ASSET_NAME = "asset_name";
    public static final String EXTRA_CANCEL_RESULT = "extra_cancel_result";
    public static final String EXTRA_DEPLOYMENT_GK = "deployment_gk";
    public static final String EXTRA_DEVICE_ID = "extra_device_id";
    public static final String EXTRA_INSTALLER_UPDATE_RESULT_RECEIVER = "installer_update_result_receiver";
    public static final String EXTRA_INSTALL_EXISTING_PACKAGE_RESULT = "extra_install_existing_package_result";
    public static final String EXTRA_REQUEST_ORIGIN = "extra_request_origin";
    public static final String EXTRA_RESPONSE_CALLBACK_SUPPORTED = "extra_response_callback_supported";
    public static final String EXTRA_RESULT_RECEIVER = "result_receiver";
    public static final String EXTRA_UNINSTALL_ASSET_RESULT = "extra_uninstall_asset_result";
    public static final String EXTRA_UPDATE_CONFIG_APK = "extra_update_config_apk";
    public static final String EXTRA_UPDATE_CONFIG_ASSETS = "extra_update_config_assets";
    public static final String EXTRA_UPDATE_CONFIG_OBB = "extra_update_config_obb";
    public static final String INSTALL_PATH = "install";
    public static final long INVALID_ASSET_ID = -1;
    public static final int INVALID_ERROR_CODE = -1;
    public static final long INVALID_TIME = -1;
    public static final String MATCHER_PATH_APP = "apps/*";
    public static final String MATCHER_PATH_APPS = "apps";
    public static final String MATCHER_PATH_ASSETS = "assets/*";
    public static final String MATCHER_PATH_ASSET_BY_FILENAME = "assets/*/filename/*";
    public static final String MATCHER_PATH_ASSET_BY_ID = "assets/*/id/*";
    public static final String METHOD_CANCEL_DOWNLOAD = "cancel_download_package";
    public static final String METHOD_CANCEL_DOWNLOAD_ASSET = "cancel_download_asset";
    public static final String METHOD_DEPLOYMENT_GK = "deployment_gk";
    public static final String METHOD_GET_DEVICE_ID = "get_device_id";
    public static final String METHOD_INSTALL_ASSET = "install_asset";
    public static final String METHOD_INSTALL_CUSTOM_PACKAGE = "install_custom_package";
    public static final String METHOD_INSTALL_EXISTING_PACKAGE = "install_existing_package";
    public static final String METHOD_INSTALL_PACKAGE = "install_package";
    public static final String METHOD_REFETCH = "refetch";
    public static final String METHOD_UNINSTALL = "uninstall_package";
    public static final String METHOD_UNINSTALL_ASSET = "uninstall_asset";
    private static final String OCULUS_CERTIFICATE = "3082038030820268a00302010202045390c873300d06092a864886f70d0101050500308181310b3009060355040613024341311330110603550408130a43616c69666f726e6961310f300d06035504071306497276696e6531183016060355040a130f4f63756c75732056522c20496e632e31183016060355040b130f4f63756c75732056522c20496e632e311830160603550403130f4f63756c75732056522c20496e632e301e170d3134303630353139343334375a170d3431313032313139343334375a308181310b3009060355040613024341311330110603550408130a43616c69666f726e6961310f300d06035504071306497276696e6531183016060355040a130f4f63756c75732056522c20496e632e31183016060355040b130f4f63756c75732056522c20496e632e311830160603550403130f4f63756c75732056522c20496e632e30820122300d06092a864886f70d01010105000382010f003082010a0282010100810913c89c710f0c1baf44cbd759cb193a9ceacc3ff64f6c710519cdc30f55f913285c74e8fd3e3220e55524cb881921a4de9b68a2f6a55eedd1da6c7f65f11adb983831e87797d70f411fd654dffbec956308bd7bbd8fb64215f15f315831a869b3ebc90a268b069e4b15e5861cbe1ef2b82c543f394410324891f161e0cecd1544c2b3e4be220e61312d1169950b3fc7c9c76c5eb4b723033caf65f5a9aadcb817c7923daea4266d0e874fd1f82480090f70b631d2b4ee4704c406c30c0d31cd75a9159ba37002ccfdff6752d61543252d5030ec0fff14e505b514027b702641b0aa6e65e5a3b80c847fc3f866d936234e7b91f8129749ac5383067bd74dcb0203010001300d06092a864886f70d01010505000382010100457e0ddb298616761934a9ff2a91048872af6f004855a0e866f4ffea0a4dfb1ff0aa2cf4ffac55f87d2d8e8273a867d4ef63d2fecb468d081ad405b3359fc576eeac839bacebb111d65f39a6930509b2986268c6a65554dfbd3eb40904b9a1d0b476c3a94b128bd975cd285635e2a225c4c3f7664eb8b98962d45416705805e4434bb826c7cb095c7ddb22cf99f92b6bc4e1b2a7e93d0ecb8d9b4543efed428589c2d52b16cde1c52dade04ffddcd3aa70aacef2a5c8dd89961f7e2b425ed1400f9673278bee714777d6b681eebd176de9345be39c800a4f78c41714d61018dbd5f3750fc8fe7ba35c2172b796fb5abb6c2b61528d1cddc84d2e16b685d02e66";
    public static final Signature OCULUS_SIGNATURE = new Signature("3082038030820268a00302010202045390c873300d06092a864886f70d0101050500308181310b3009060355040613024341311330110603550408130a43616c69666f726e6961310f300d06035504071306497276696e6531183016060355040a130f4f63756c75732056522c20496e632e31183016060355040b130f4f63756c75732056522c20496e632e311830160603550403130f4f63756c75732056522c20496e632e301e170d3134303630353139343334375a170d3431313032313139343334375a308181310b3009060355040613024341311330110603550408130a43616c69666f726e6961310f300d06035504071306497276696e6531183016060355040a130f4f63756c75732056522c20496e632e31183016060355040b130f4f63756c75732056522c20496e632e311830160603550403130f4f63756c75732056522c20496e632e30820122300d06092a864886f70d01010105000382010f003082010a0282010100810913c89c710f0c1baf44cbd759cb193a9ceacc3ff64f6c710519cdc30f55f913285c74e8fd3e3220e55524cb881921a4de9b68a2f6a55eedd1da6c7f65f11adb983831e87797d70f411fd654dffbec956308bd7bbd8fb64215f15f315831a869b3ebc90a268b069e4b15e5861cbe1ef2b82c543f394410324891f161e0cecd1544c2b3e4be220e61312d1169950b3fc7c9c76c5eb4b723033caf65f5a9aadcb817c7923daea4266d0e874fd1f82480090f70b631d2b4ee4704c406c30c0d31cd75a9159ba37002ccfdff6752d61543252d5030ec0fff14e505b514027b702641b0aa6e65e5a3b80c847fc3f866d936234e7b91f8129749ac5383067bd74dcb0203010001300d06092a864886f70d01010505000382010100457e0ddb298616761934a9ff2a91048872af6f004855a0e866f4ffea0a4dfb1ff0aa2cf4ffac55f87d2d8e8273a867d4ef63d2fecb468d081ad405b3359fc576eeac839bacebb111d65f39a6930509b2986268c6a65554dfbd3eb40904b9a1d0b476c3a94b128bd975cd285635e2a225c4c3f7664eb8b98962d45416705805e4434bb826c7cb095c7ddb22cf99f92b6bc4e1b2a7e93d0ecb8d9b4543efed428589c2d52b16cde1c52dade04ffddcd3aa70aacef2a5c8dd89961f7e2b425ed1400f9673278bee714777d6b681eebd176de9345be39c800a4f78c41714d61018dbd5f3750fc8fe7ba35c2172b796fb5abb6c2b61528d1cddc84d2e16b685d02e66");
    public static final String PACKAGE_NAME = "com.oculus.ocms";

    public static Uri providerUri() {
        return new Uri.Builder().scheme("content").authority(AUTHORITY).build();
    }

    public static Uri uriForAllPackages() {
        return new Uri.Builder().scheme("content").authority(AUTHORITY).path("apps").build();
    }

    public static Uri uriForPackage(String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            return new Uri.Builder().scheme("content").authority(AUTHORITY).path("apps").appendPath(packageName).build();
        }
        throw new IllegalArgumentException("packageName cannot be null/empty");
    }

    public static Uri uriForAllDownloads() {
        return new Uri.Builder().scheme("content").authority(AUTHORITY).path(DOWNLOAD_PATH).build();
    }

    public static Uri uriForPackageDownload(InstallerResult installerResult) {
        return uriForPackageDownload(installerResult.installIdentifier, installerResult.error == null ? -2 : installerResult.error.code, installerResult.timestampMs);
    }

    public static Uri uriForPackageDownload(String installIdentifier, int errorCode, long timestampMs) {
        if (!TextUtils.isEmpty(installIdentifier)) {
            return new Uri.Builder().scheme("content").authority(AUTHORITY).path(DOWNLOAD_PATH).appendPath(installIdentifier).appendPath(Integer.toString(errorCode)).appendPath(Long.toString(timestampMs)).build();
        }
        throw new IllegalArgumentException("install cannot be null/empty");
    }

    public static Uri uriForAllInstalls() {
        return new Uri.Builder().scheme("content").authority(AUTHORITY).path(INSTALL_PATH).build();
    }

    public static Uri uriForPackageInstall(InstallerResult installerResult) {
        return uriForPackageInstall(installerResult.installIdentifier, installerResult.error == null ? -2 : installerResult.error.code, installerResult.timestampMs);
    }

    public static Uri uriForPackageInstall(String installIdentifier, int errorCode, long timestampMs) {
        if (!TextUtils.isEmpty(installIdentifier)) {
            return new Uri.Builder().scheme("content").authority(AUTHORITY).path(INSTALL_PATH).appendPath(installIdentifier).appendPath(Integer.toString(errorCode)).appendPath(Long.toString(timestampMs)).build();
        }
        throw new IllegalArgumentException("identifier cannot be null/empty");
    }

    public static String getPackageFromAppsUri(Uri uri) {
        return getUriPathSegment(uri, 1);
    }

    public static String getIdentifierFromDownloadUri(Uri uri) {
        return getUriPathSegment(uri, 1);
    }

    public static String getIdentifierFromInstallUri(Uri uri) {
        return getUriPathSegment(uri, 1);
    }

    public static Uri getAllAssetsUri(String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            return new Uri.Builder().scheme("content").authority(AUTHORITY).path("assets").appendPath(packageName).build();
        }
        throw new IllegalArgumentException("packageName cannot be null/empty");
    }

    public static Uri getAssetByIdUri(String packageName, long id) {
        if (!TextUtils.isEmpty(packageName)) {
            return new Uri.Builder().scheme("content").authority(AUTHORITY).path("assets").appendPath(packageName).appendPath("id").appendPath(Long.toString(id)).build();
        }
        throw new IllegalArgumentException("packageName cannot be null/empty");
    }

    public static Uri getAssetByFilenameUri(String packageName, String filename) {
        if (TextUtils.isEmpty(packageName)) {
            throw new IllegalArgumentException("packageName cannot be null/empty");
        } else if (!TextUtils.isEmpty(filename)) {
            return new Uri.Builder().scheme("content").authority(AUTHORITY).path("assets").appendPath(packageName).appendPath(ASSETS_PATH_BY_FILENAME).appendPath(filename).build();
        } else {
            throw new IllegalArgumentException("filename cannot be null/empty");
        }
    }

    public static String getPackageFromAssetsUri(Uri uri) {
        return getUriPathSegment(uri, 1);
    }

    public static long getIdFromAssetsUri(Uri uri) {
        String idString = getUriPathSegment(uri, 3);
        if (TextUtils.isEmpty(idString)) {
            throw new IllegalArgumentException("Missing or invalid id");
        }
        try {
            return Long.parseLong(idString);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getFilenameFromAssetsUri(Uri uri) {
        String filename = getUriPathSegment(uri, 3);
        if (!TextUtils.isEmpty(filename)) {
            return filename;
        }
        throw new IllegalArgumentException("Missing or invalid id");
    }

    public static int getErrorForInstallUri(Uri uri) {
        try {
            return Integer.parseInt(getUriPathSegment(uri, 2));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static long getTimestampMsForInstallUri(Uri uri) {
        try {
            return Long.parseLong(getUriPathSegment(uri, 3));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static int getErrorForDownloadUri(Uri uri) {
        try {
            return Integer.parseInt(getUriPathSegment(uri, 2));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static long getTimestampMsForDownloadUri(Uri uri) {
        try {
            return Long.parseLong(getUriPathSegment(uri, 3));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getUriPathSegment(Uri uri, int segment) {
        if (uri == null || segment < 0) {
            throw new IllegalArgumentException("invalid args");
        }
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments == null || pathSegments.size() <= segment) {
            return null;
        }
        return pathSegments.get(segment);
    }

    public static Uri queryForDeploymentGk() {
        return new Uri.Builder().scheme("content").authority(AUTHORITY).path(DEPLOYMENT_PATH).build();
    }
}
