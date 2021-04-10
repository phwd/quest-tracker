package com.oculus.libraryapi;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.appmanager.installer.util.InstallConfigBundler;
import com.oculus.appmanager.model.UpdateConfig;
import com.oculus.library.model.App;
import com.oculus.library.model.AssetInfo;
import com.oculus.library.model.InstallerCallback;
import com.oculus.library.model.InstallerCallbackReceiver;
import com.oculus.library.utils.app.AppConverter;
import com.oculus.library.utils.app.AssetConverter;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nullable;

public class OVRLibrary {
    public static final String ACTION_UPDATE_BROADCAST = "ovr.library.update_broadcast";
    public static final String EXTRA_ERROR_CODE = "errorCode";
    public static final String FACEBOOK_SYSTEM_INSTALLER = "com.facebook.system";
    public static final String FACEBOOK_SYSTEM_INSTALLER_DEV = "com.facebook.system.dev";
    public static final String PACKAGE_NAME_OCMS = "com.oculus.ocms";
    public static final int REFETCH_RESULT_FAILURE = 1;
    public static final int REFETCH_RESULT_OK = 0;
    private static final String TAG = "OVRLibrary";
    public static final int UNKNOWN_VERSION = Integer.MIN_VALUE;
    private final AppConverter mAppConverter;
    private final AssetConverter mAssetConverter;
    protected final Context mContext;
    private final HashMap<DownloadListener, ArrayList<DownloadContentObserver>> mDownloadListeners = new HashMap<>();
    private final HashMap<OnChangeListener, ArrayList<LibraryContentObserver>> mEntitlementListeners = new HashMap<>();
    private final HashMap<InstallListener, ArrayList<InstallContentObserver>> mInstallListeners = new HashMap<>();

    public interface DownloadListener {
        void onDownloadComplete(InstallerResult installerResult);
    }

    public interface InstallListener {
        void onInstall(InstallerResult installerResult);
    }

    public interface OnChangeListener {
        void onChange(String str);
    }

    /* access modifiers changed from: private */
    public static class LibraryContentObserver extends ContentObserver {
        final Handler handler;
        final OnChangeListener listener;
        final Uri uri;

        public LibraryContentObserver(Uri uri2, OnChangeListener listener2, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = listener2;
            this.handler = handler2;
        }

        public void onChange(boolean selfChange, Uri uri2) {
            this.listener.onChange(OCMSLibraryContract.getPackageFromAppsUri(uri2));
        }
    }

    /* access modifiers changed from: private */
    public static class InstallContentObserver extends ContentObserver {
        final Handler handler;
        final InstallListener listener;
        final Uri uri;

        public InstallContentObserver(Uri uri2, InstallListener listener2, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = listener2;
            this.handler = handler2;
        }

        public void onChange(boolean selfChange, Uri uri2) {
            InstallerResult installerResult;
            String identifier = OCMSLibraryContract.getIdentifierFromInstallUri(uri2);
            int errorCode = OCMSLibraryContract.getErrorForInstallUri(uri2);
            long timestampMs = OCMSLibraryContract.getTimestampMsForInstallUri(uri2);
            if (errorCode == -1 || timestampMs == -1 || TextUtils.isEmpty(identifier)) {
                Log.w(OVRLibrary.TAG, "unknown content uri observed: " + uri2.toString());
                return;
            }
            if (errorCode == -2) {
                installerResult = InstallerResult.createForSuccess(identifier, timestampMs);
            } else {
                installerResult = InstallerResult.createForError(identifier, timestampMs, InstallerResultError.fromInt(errorCode));
            }
            this.listener.onInstall(installerResult);
        }
    }

    /* access modifiers changed from: private */
    public static class DownloadContentObserver extends ContentObserver {
        final Handler handler;
        final DownloadListener listener;
        final Uri uri;

        public DownloadContentObserver(Uri uri2, DownloadListener listener2, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = listener2;
            this.handler = handler2;
        }

        public void onChange(boolean selfChange, Uri uri2) {
            InstallerResult downloadResult;
            String identifier = OCMSLibraryContract.getIdentifierFromDownloadUri(uri2);
            int errorCode = OCMSLibraryContract.getErrorForDownloadUri(uri2);
            long timestampMs = OCMSLibraryContract.getTimestampMsForDownloadUri(uri2);
            if (errorCode == -1 || timestampMs == -1 || TextUtils.isEmpty(identifier)) {
                Log.w(OVRLibrary.TAG, "unknown content uri observed: " + uri2.toString());
                return;
            }
            if (errorCode == -2) {
                downloadResult = InstallerResult.createForSuccess(identifier, timestampMs);
            } else {
                downloadResult = InstallerResult.createForError(identifier, timestampMs, InstallerResultError.fromInt(errorCode));
            }
            this.listener.onDownloadComplete(downloadResult);
        }
    }

    public OVRLibrary(Context context) {
        this.mContext = context.getApplicationContext();
        this.mAppConverter = new AppConverter(this.mContext.getPackageManager());
        this.mAssetConverter = new AssetConverter();
    }

    public String getLibraryPackage() {
        return "com.oculus.ocms";
    }

    public void refetch() {
        refetch(null);
    }

    public void refetch(ResultReceiver resultReceiver) {
        Bundle extras = new Bundle();
        extras.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(resultReceiver));
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_REFETCH, (String) null, extras);
    }

    public App getApp(String packageName) {
        Uri uri = OCMSLibraryContract.uriForPackage(packageName);
        Cursor cursor = this.mContext.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            Log.w(TAG, "null cursor received for query " + uri.toString());
            return null;
        }
        try {
            App[] apps = this.mAppConverter.extractAllFromCursor(cursor);
            if (apps.length != 1) {
                Log.w(TAG, "unexpected number of results in cursor");
                return null;
            }
            App app = apps[0];
            cursor.close();
            return app;
        } finally {
            cursor.close();
        }
    }

    public App getAppById(String id) {
        if (id == null) {
            Log.w(TAG, "getAppById was called with a null id");
            return null;
        }
        App[] apps = getAllApps();
        for (App app : apps) {
            if (id.equals(app.id)) {
                return app;
            }
        }
        return null;
    }

    public App[] getAllApps() {
        Cursor cursor = this.mContext.getContentResolver().query(OCMSLibraryContract.uriForAllPackages(), null, null, null, null);
        try {
            return this.mAppConverter.extractAllFromCursor(cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public int save(App.Editor appEditor) {
        if (appEditor == null) {
            Log.w(TAG, "attempting to save null app");
            return 0;
        }
        ContentValues contentValues = this.mAppConverter.toContentValues(appEditor);
        this.mAppConverter.addContentValuePackingVersion(contentValues, 2);
        return this.mContext.getContentResolver().update(OCMSLibraryContract.uriForPackage(appEditor.packageName), contentValues, null, null);
    }

    public void downloadAndInstall(String packageName, RequestOrigin origin, InstallerCallback callback) {
        Bundle extras = new Bundle();
        if (callback != null) {
            extras.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(callback).getReceiver()));
        }
        if (origin != null) {
            extras.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, origin.name());
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_INSTALL_PACKAGE, packageName, extras);
    }

    public InstallerResult downloadAndInstallAwait(String packageName, RequestOrigin origin) {
        final InstallerResult[] referenceResult = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        downloadAndInstall(packageName, origin, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass1 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                referenceResult[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return referenceResult[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(packageName, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public void customDownloadAndInstallAsync(UpdateConfig apkConfig, @Nullable UpdateConfig obbConfig, @Nullable List<UpdateConfig> assetConfigs, @Nullable RequestOrigin requestOrigin, InstallerCallback callback) {
        Bundle extras = new Bundle();
        if (callback != null) {
            extras.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(callback).getReceiver()));
        }
        if (requestOrigin != null) {
            extras.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
        }
        extras.putBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_APK, InstallConfigBundler.toBundle(apkConfig));
        if (obbConfig != null) {
            extras.putBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_OBB, InstallConfigBundler.toBundle(obbConfig));
        }
        if (assetConfigs != null) {
            extras.putBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_ASSETS, InstallConfigBundler.toBundle(assetConfigs));
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_INSTALL_CUSTOM_PACKAGE, (String) null, extras);
    }

    public InstallerResult customDownloadAndInstall(UpdateConfig apkConfig, @Nullable UpdateConfig obbConfig, @Nullable List<UpdateConfig> assetConfigs, @Nullable RequestOrigin requestOrigin) {
        final InstallerResult[] referenceResult = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        customDownloadAndInstallAsync(apkConfig, obbConfig, assetConfigs, requestOrigin, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass2 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                referenceResult[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return referenceResult[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(apkConfig.identifier, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public boolean installExistingPackage(String packageName, RequestOrigin origin) {
        Bundle extras = new Bundle();
        if (origin != null) {
            extras.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, origin.name());
        }
        Bundle result = this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_INSTALL_EXISTING_PACKAGE, packageName, extras);
        if (result == null || !result.getBoolean(OCMSLibraryContract.EXTRA_INSTALL_EXISTING_PACKAGE_RESULT, false)) {
            return false;
        }
        return true;
    }

    public void uninstall(String packageName, RequestOrigin origin, InstallerCallback callback) {
        if (getPackageInfo(packageName) != null) {
            Bundle extras = new Bundle();
            if (callback != null) {
                extras.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(callback).getReceiver()));
            }
            if (origin != null) {
                extras.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, origin.name());
            }
            this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_UNINSTALL, packageName, extras);
        } else if (callback != null) {
            callback.onInstallerResult(InstallerResult.createForError(packageName, System.currentTimeMillis(), InstallerResultError.NOT_INSTALLED));
        }
    }

    public InstallerResult uninstallAwait(String packageName, RequestOrigin origin) {
        final InstallerResult[] referenceResult = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        uninstall(packageName, origin, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass3 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                referenceResult[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return referenceResult[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(packageName, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public void cancelDownload(String packageName, InstallerCallback callback) {
        Bundle extras = new Bundle();
        if (callback != null) {
            extras.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(callback).getReceiver()));
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_CANCEL_DOWNLOAD, packageName, extras);
    }

    public InstallerResult cancelDownloadAwait(String packageName) {
        final InstallerResult[] referenceResult = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        cancelDownload(packageName, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass4 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                referenceResult[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return referenceResult[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(packageName, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    private PackageInfo getPackageInfo(String packageName) {
        try {
            return this.mContext.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public String getDeviceId() {
        return this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_GET_DEVICE_ID, (String) null, (Bundle) null).getString(OCMSLibraryContract.EXTRA_DEVICE_ID);
    }

    public AssetInfo[] assetList(String packageName) {
        Cursor cursor = this.mContext.getContentResolver().query(OCMSLibraryContract.getAllAssetsUri(packageName), null, null, null, null);
        try {
            return this.mAssetConverter.extractFromCursor(cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public AssetInfo assetStatus(String packageName, long id) {
        Cursor cursor = this.mContext.getContentResolver().query(OCMSLibraryContract.getAssetByIdUri(packageName, id), null, null, null, null);
        try {
            AssetInfo[] result = this.mAssetConverter.extractFromCursor(cursor);
            if (result != null && result.length == 1) {
                return result[0];
            }
            Log.w(TAG, "unexpected number of results in cursor");
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public AssetInfo assetStatus(String packageName, String filename) {
        Cursor cursor = this.mContext.getContentResolver().query(OCMSLibraryContract.getAssetByFilenameUri(packageName, filename), null, null, null, null);
        try {
            AssetInfo[] result = this.mAssetConverter.extractFromCursor(cursor);
            if (result != null && result.length == 1) {
                return result[0];
            }
            Log.w(TAG, "unexpected number of results in cursor");
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void assetDownloadAndInstall(String packageName, long assetId, RequestOrigin origin, InstallerCallback callback) {
        Bundle extras = new Bundle();
        extras.putLong("asset_id", assetId);
        assetDownloadAndInstall(packageName, origin, callback, extras);
    }

    public void assetDownloadAndInstall(String packageName, String filename, RequestOrigin origin, InstallerCallback callback) {
        Bundle extras = new Bundle();
        extras.putString("asset_name", filename);
        assetDownloadAndInstall(packageName, origin, callback, extras);
    }

    private void assetDownloadAndInstall(String packageName, RequestOrigin origin, InstallerCallback callback, Bundle extras) {
        if (callback != null) {
            extras.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(callback).getReceiver()));
        }
        if (origin != null) {
            extras.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, origin.name());
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_INSTALL_ASSET, packageName, extras);
    }

    public InstallerResult assetDownloadAndInstallAwait(String packageName, long assetId, RequestOrigin origin) {
        final InstallerResult[] referenceResult = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        assetDownloadAndInstall(packageName, assetId, origin, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass5 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                referenceResult[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return referenceResult[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(packageName, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public InstallerResult assetDownloadAndInstallAwait(String packageName, String filename, RequestOrigin origin) {
        final InstallerResult[] referenceResult = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        assetDownloadAndInstall(packageName, filename, origin, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass6 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                referenceResult[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return referenceResult[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(packageName, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public void assetCancelDownload(String packageName, long assetId, InstallerCallback callback) {
        Bundle extras = new Bundle();
        extras.putLong("asset_id", assetId);
        if (callback != null) {
            extras.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(callback).getReceiver()));
        }
        assetCancelDownload(packageName, extras);
    }

    public void assetCancelDownload(String packageName, String filename, InstallerCallback callback) {
        Bundle extras = new Bundle();
        extras.putString("asset_name", filename);
        if (callback != null) {
            extras.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(callback).getReceiver()));
        }
        assetCancelDownload(packageName, extras);
    }

    private void assetCancelDownload(String packageName, Bundle extras) {
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_CANCEL_DOWNLOAD_ASSET, packageName, extras);
    }

    public InstallerResult assetCancelDownloadAwait(String packageName, long assetId) {
        final InstallerResult[] referenceResult = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        assetCancelDownload(packageName, assetId, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass7 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                referenceResult[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return referenceResult[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(packageName, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public InstallerResult assetCancelDownloadAwait(String packageName, String filename) {
        final InstallerResult[] referenceResult = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        assetCancelDownload(packageName, filename, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass8 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                referenceResult[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return referenceResult[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(packageName, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public boolean assetDelete(String packageName, long assetId) {
        Bundle extras = new Bundle();
        extras.putLong("asset_id", assetId);
        return assetDelete(packageName, extras);
    }

    public boolean assetDelete(String packageName, String filename) {
        Bundle extras = new Bundle();
        extras.putString("asset_name", filename);
        return assetDelete(packageName, extras);
    }

    private boolean assetDelete(String packageName, Bundle extras) {
        Bundle result = this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_UNINSTALL_ASSET, packageName, extras);
        if (result != null) {
            return result.getBoolean(OCMSLibraryContract.EXTRA_UNINSTALL_ASSET_RESULT, false);
        }
        return false;
    }

    public void registerInstallListener(InstallListener listener) {
        registerObserverForInstallUri(OCMSLibraryContract.uriForAllInstalls(), listener);
    }

    public synchronized void unregisterInstallListener(InstallListener listener) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        List<InstallContentObserver> contentObservers = this.mInstallListeners.remove(listener);
        if (contentObservers != null) {
            for (InstallContentObserver contentObserver : contentObservers) {
                contentResolver.unregisterContentObserver(contentObserver);
                if (contentObserver.handler != null) {
                    contentObserver.handler.removeCallbacksAndMessages(null);
                }
            }
        }
    }

    private synchronized void registerObserverForInstallUri(Uri uri, InstallListener listener) {
        ArrayList<InstallContentObserver> contentObservers = this.mInstallListeners.get(listener);
        if (contentObservers == null) {
            contentObservers = new ArrayList<>();
            this.mInstallListeners.put(listener, contentObservers);
        }
        Iterator<InstallContentObserver> it = contentObservers.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().uri.equals(uri)) {
                    break;
                }
            } else {
                InstallContentObserver contentObserver = new InstallContentObserver(uri, listener, new Handler());
                this.mContext.getContentResolver().registerContentObserver(uri, true, contentObserver);
                contentObservers.add(contentObserver);
                break;
            }
        }
    }

    public void registerDownloadListener(DownloadListener listener) {
        registerObserverForDownloadUri(OCMSLibraryContract.uriForAllDownloads(), listener);
    }

    public synchronized void unregisterDownloadListener(DownloadListener listener) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        List<DownloadContentObserver> contentObservers = this.mDownloadListeners.remove(listener);
        if (contentObservers != null) {
            for (DownloadContentObserver contentObserver : contentObservers) {
                contentResolver.unregisterContentObserver(contentObserver);
                if (contentObserver.handler != null) {
                    contentObserver.handler.removeCallbacksAndMessages(null);
                }
            }
        }
    }

    private synchronized void registerObserverForDownloadUri(Uri uri, DownloadListener listener) {
        ArrayList<DownloadContentObserver> contentObservers = this.mDownloadListeners.get(listener);
        if (contentObservers == null) {
            contentObservers = new ArrayList<>();
            this.mDownloadListeners.put(listener, contentObservers);
        }
        Iterator<DownloadContentObserver> it = contentObservers.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().uri.equals(uri)) {
                    break;
                }
            } else {
                DownloadContentObserver contentObserver = new DownloadContentObserver(uri, listener, new Handler());
                this.mContext.getContentResolver().registerContentObserver(uri, true, contentObserver);
                contentObservers.add(contentObserver);
                break;
            }
        }
    }

    public void registerAppsChangeListener(OnChangeListener listener) {
        registerObserverForAppChangeUri(OCMSLibraryContract.uriForAllPackages(), listener);
    }

    public void registerAppChangeListener(String packageName, OnChangeListener listener) {
        registerObserverForAppChangeUri(OCMSLibraryContract.uriForPackage(packageName), listener);
    }

    public synchronized void unregisterAppChangeListener(OnChangeListener listener) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        List<LibraryContentObserver> contentObservers = this.mEntitlementListeners.remove(listener);
        if (contentObservers != null) {
            for (LibraryContentObserver contentObserver : contentObservers) {
                contentResolver.unregisterContentObserver(contentObserver);
                if (contentObserver.handler != null) {
                    contentObserver.handler.removeCallbacksAndMessages(null);
                }
            }
        }
    }

    private synchronized void registerObserverForAppChangeUri(Uri uri, OnChangeListener listener) {
        ArrayList<LibraryContentObserver> contentObservers = this.mEntitlementListeners.get(listener);
        if (contentObservers == null) {
            contentObservers = new ArrayList<>();
            this.mEntitlementListeners.put(listener, contentObservers);
        }
        Iterator<LibraryContentObserver> it = contentObservers.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().uri.equals(uri)) {
                    break;
                }
            } else {
                LibraryContentObserver contentObserver = new LibraryContentObserver(uri, listener, new Handler());
                this.mContext.getContentResolver().registerContentObserver(uri, true, contentObserver);
                contentObservers.add(contentObserver);
                break;
            }
        }
    }

    private ResultReceiver prepareResultReceiverForIPC(ResultReceiver resultReceiver) {
        if (resultReceiver == null) {
            return null;
        }
        Parcel out = Parcel.obtain();
        resultReceiver.writeToParcel(out, 0);
        out.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(out);
        out.recycle();
        return resultReceiver2;
    }
}
