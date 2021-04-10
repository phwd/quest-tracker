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

    public String getLibraryPackage() {
        return "com.oculus.ocms";
    }

    /* access modifiers changed from: private */
    public static class LibraryContentObserver extends ContentObserver {
        final Handler handler;
        final OnChangeListener listener;
        final Uri uri;

        public LibraryContentObserver(Uri uri2, OnChangeListener onChangeListener, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = onChangeListener;
            this.handler = handler2;
        }

        public void onChange(boolean z, Uri uri2) {
            this.listener.onChange(OCMSLibraryContract.getPackageFromAppsUri(uri2));
        }
    }

    /* access modifiers changed from: private */
    public static class InstallContentObserver extends ContentObserver {
        final Handler handler;
        final InstallListener listener;
        final Uri uri;

        public InstallContentObserver(Uri uri2, InstallListener installListener, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = installListener;
            this.handler = handler2;
        }

        public void onChange(boolean z, Uri uri2) {
            InstallerResult installerResult;
            String identifierFromInstallUri = OCMSLibraryContract.getIdentifierFromInstallUri(uri2);
            int errorForInstallUri = OCMSLibraryContract.getErrorForInstallUri(uri2);
            long timestampMsForInstallUri = OCMSLibraryContract.getTimestampMsForInstallUri(uri2);
            if (errorForInstallUri == -1 || timestampMsForInstallUri == -1 || TextUtils.isEmpty(identifierFromInstallUri)) {
                Log.w(OVRLibrary.TAG, "unknown content uri observed: " + uri2.toString());
                return;
            }
            if (errorForInstallUri == -2) {
                installerResult = InstallerResult.createForSuccess(identifierFromInstallUri, timestampMsForInstallUri);
            } else {
                installerResult = InstallerResult.createForError(identifierFromInstallUri, timestampMsForInstallUri, InstallerResultError.fromInt(errorForInstallUri));
            }
            this.listener.onInstall(installerResult);
        }
    }

    /* access modifiers changed from: private */
    public static class DownloadContentObserver extends ContentObserver {
        final Handler handler;
        final DownloadListener listener;
        final Uri uri;

        public DownloadContentObserver(Uri uri2, DownloadListener downloadListener, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = downloadListener;
            this.handler = handler2;
        }

        public void onChange(boolean z, Uri uri2) {
            InstallerResult installerResult;
            String identifierFromDownloadUri = OCMSLibraryContract.getIdentifierFromDownloadUri(uri2);
            int errorForDownloadUri = OCMSLibraryContract.getErrorForDownloadUri(uri2);
            long timestampMsForDownloadUri = OCMSLibraryContract.getTimestampMsForDownloadUri(uri2);
            if (errorForDownloadUri == -1 || timestampMsForDownloadUri == -1 || TextUtils.isEmpty(identifierFromDownloadUri)) {
                Log.w(OVRLibrary.TAG, "unknown content uri observed: " + uri2.toString());
                return;
            }
            if (errorForDownloadUri == -2) {
                installerResult = InstallerResult.createForSuccess(identifierFromDownloadUri, timestampMsForDownloadUri);
            } else {
                installerResult = InstallerResult.createForError(identifierFromDownloadUri, timestampMsForDownloadUri, InstallerResultError.fromInt(errorForDownloadUri));
            }
            this.listener.onDownloadComplete(installerResult);
        }
    }

    public OVRLibrary(Context context) {
        this.mContext = context.getApplicationContext();
        this.mAppConverter = new AppConverter(this.mContext.getPackageManager());
        this.mAssetConverter = new AssetConverter();
    }

    public void refetch() {
        refetch(null);
    }

    public void refetch(ResultReceiver resultReceiver) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(resultReceiver));
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_REFETCH, (String) null, bundle);
    }

    public App getApp(String str) {
        Uri uriForPackage = OCMSLibraryContract.uriForPackage(str);
        Cursor query = this.mContext.getContentResolver().query(uriForPackage, null, null, null, null);
        if (query == null) {
            Log.w(TAG, "null cursor received for query " + uriForPackage.toString());
            return null;
        }
        try {
            App[] extractAllFromCursor = this.mAppConverter.extractAllFromCursor(query);
            if (extractAllFromCursor.length != 1) {
                Log.w(TAG, "unexpected number of results in cursor");
                return null;
            }
            App app = extractAllFromCursor[0];
            query.close();
            return app;
        } finally {
            query.close();
        }
    }

    public App getAppById(String str) {
        if (str == null) {
            Log.w(TAG, "getAppById was called with a null id");
            return null;
        }
        App[] allApps = getAllApps();
        for (App app : allApps) {
            if (str.equals(app.id)) {
                return app;
            }
        }
        return null;
    }

    public App[] getAllApps() {
        Cursor query = this.mContext.getContentResolver().query(OCMSLibraryContract.uriForAllPackages(), null, null, null, null);
        try {
            return this.mAppConverter.extractAllFromCursor(query);
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public int save(App.Editor editor) {
        if (editor == null) {
            Log.w(TAG, "attempting to save null app");
            return 0;
        }
        ContentValues contentValues = this.mAppConverter.toContentValues(editor);
        this.mAppConverter.addContentValuePackingVersion(contentValues, 2);
        return this.mContext.getContentResolver().update(OCMSLibraryContract.uriForPackage(editor.packageName), contentValues, null, null);
    }

    public void downloadAndInstall(String str, RequestOrigin requestOrigin, InstallerCallback installerCallback) {
        Bundle bundle = new Bundle();
        if (installerCallback != null) {
            bundle.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(installerCallback).getReceiver()));
        }
        if (requestOrigin != null) {
            bundle.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_INSTALL_PACKAGE, str, bundle);
    }

    public InstallerResult downloadAndInstallAwait(String str, RequestOrigin requestOrigin) {
        final InstallerResult[] installerResultArr = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        downloadAndInstall(str, requestOrigin, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass1 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                installerResultArr[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return installerResultArr[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(str, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public void customDownloadAndInstallAsync(UpdateConfig updateConfig, @Nullable UpdateConfig updateConfig2, @Nullable List<UpdateConfig> list, @Nullable RequestOrigin requestOrigin, InstallerCallback installerCallback) {
        Bundle bundle = new Bundle();
        if (installerCallback != null) {
            bundle.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(installerCallback).getReceiver()));
        }
        if (requestOrigin != null) {
            bundle.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
        }
        bundle.putBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_APK, InstallConfigBundler.toBundle(updateConfig));
        if (updateConfig2 != null) {
            bundle.putBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_OBB, InstallConfigBundler.toBundle(updateConfig2));
        }
        if (list != null) {
            bundle.putBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_ASSETS, InstallConfigBundler.toBundle(list));
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_INSTALL_CUSTOM_PACKAGE, (String) null, bundle);
    }

    public InstallerResult customDownloadAndInstall(UpdateConfig updateConfig, @Nullable UpdateConfig updateConfig2, @Nullable List<UpdateConfig> list, @Nullable RequestOrigin requestOrigin) {
        final InstallerResult[] installerResultArr = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        customDownloadAndInstallAsync(updateConfig, updateConfig2, list, requestOrigin, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass2 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                installerResultArr[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return installerResultArr[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(updateConfig.identifier, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public boolean installExistingPackage(String str, RequestOrigin requestOrigin) {
        Bundle bundle = new Bundle();
        if (requestOrigin != null) {
            bundle.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
        }
        Bundle call = this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_INSTALL_EXISTING_PACKAGE, str, bundle);
        if (call == null || !call.getBoolean(OCMSLibraryContract.EXTRA_INSTALL_EXISTING_PACKAGE_RESULT, false)) {
            return false;
        }
        return true;
    }

    public void uninstall(String str, RequestOrigin requestOrigin, InstallerCallback installerCallback) {
        if (getPackageInfo(str) != null) {
            Bundle bundle = new Bundle();
            if (installerCallback != null) {
                bundle.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(installerCallback).getReceiver()));
            }
            if (requestOrigin != null) {
                bundle.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
            }
            this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_UNINSTALL, str, bundle);
        } else if (installerCallback != null) {
            installerCallback.onInstallerResult(InstallerResult.createForError(str, System.currentTimeMillis(), InstallerResultError.NOT_INSTALLED));
        }
    }

    public InstallerResult uninstallAwait(String str, RequestOrigin requestOrigin) {
        final InstallerResult[] installerResultArr = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        uninstall(str, requestOrigin, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass3 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                installerResultArr[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return installerResultArr[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(str, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public void cancelDownload(String str, InstallerCallback installerCallback) {
        Bundle bundle = new Bundle();
        if (installerCallback != null) {
            bundle.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(installerCallback).getReceiver()));
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_CANCEL_DOWNLOAD, str, bundle);
    }

    public InstallerResult cancelDownloadAwait(String str) {
        final InstallerResult[] installerResultArr = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        cancelDownload(str, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass4 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                installerResultArr[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return installerResultArr[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(str, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    private PackageInfo getPackageInfo(String str) {
        try {
            return this.mContext.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public String getDeviceId() {
        return this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_GET_DEVICE_ID, (String) null, (Bundle) null).getString(OCMSLibraryContract.EXTRA_DEVICE_ID);
    }

    public AssetInfo[] assetList(String str) {
        Cursor query = this.mContext.getContentResolver().query(OCMSLibraryContract.getAllAssetsUri(str), null, null, null, null);
        try {
            return this.mAssetConverter.extractFromCursor(query);
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public AssetInfo assetStatus(String str, long j) {
        Cursor query = this.mContext.getContentResolver().query(OCMSLibraryContract.getAssetByIdUri(str, j), null, null, null, null);
        try {
            AssetInfo[] extractFromCursor = this.mAssetConverter.extractFromCursor(query);
            if (extractFromCursor != null && extractFromCursor.length == 1) {
                return extractFromCursor[0];
            }
            Log.w(TAG, "unexpected number of results in cursor");
            return null;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public AssetInfo assetStatus(String str, String str2) {
        Cursor query = this.mContext.getContentResolver().query(OCMSLibraryContract.getAssetByFilenameUri(str, str2), null, null, null, null);
        try {
            AssetInfo[] extractFromCursor = this.mAssetConverter.extractFromCursor(query);
            if (extractFromCursor != null && extractFromCursor.length == 1) {
                return extractFromCursor[0];
            }
            Log.w(TAG, "unexpected number of results in cursor");
            return null;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public void assetDownloadAndInstall(String str, long j, RequestOrigin requestOrigin, InstallerCallback installerCallback) {
        Bundle bundle = new Bundle();
        bundle.putLong("asset_id", j);
        assetDownloadAndInstall(str, requestOrigin, installerCallback, bundle);
    }

    public void assetDownloadAndInstall(String str, String str2, RequestOrigin requestOrigin, InstallerCallback installerCallback) {
        Bundle bundle = new Bundle();
        bundle.putString("asset_name", str2);
        assetDownloadAndInstall(str, requestOrigin, installerCallback, bundle);
    }

    private void assetDownloadAndInstall(String str, RequestOrigin requestOrigin, InstallerCallback installerCallback, Bundle bundle) {
        if (installerCallback != null) {
            bundle.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(installerCallback).getReceiver()));
        }
        if (requestOrigin != null) {
            bundle.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
        }
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_INSTALL_ASSET, str, bundle);
    }

    public InstallerResult assetDownloadAndInstallAwait(String str, long j, RequestOrigin requestOrigin) {
        final InstallerResult[] installerResultArr = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        assetDownloadAndInstall(str, j, requestOrigin, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass5 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                installerResultArr[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return installerResultArr[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(str, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public InstallerResult assetDownloadAndInstallAwait(String str, String str2, RequestOrigin requestOrigin) {
        final InstallerResult[] installerResultArr = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        assetDownloadAndInstall(str, str2, requestOrigin, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass6 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                installerResultArr[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return installerResultArr[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(str, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public void assetCancelDownload(String str, long j, InstallerCallback installerCallback) {
        Bundle bundle = new Bundle();
        bundle.putLong("asset_id", j);
        if (installerCallback != null) {
            bundle.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(installerCallback).getReceiver()));
        }
        assetCancelDownload(str, bundle);
    }

    public void assetCancelDownload(String str, String str2, InstallerCallback installerCallback) {
        Bundle bundle = new Bundle();
        bundle.putString("asset_name", str2);
        if (installerCallback != null) {
            bundle.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, prepareResultReceiverForIPC(new InstallerCallbackReceiver(installerCallback).getReceiver()));
        }
        assetCancelDownload(str, bundle);
    }

    private void assetCancelDownload(String str, Bundle bundle) {
        this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_CANCEL_DOWNLOAD_ASSET, str, bundle);
    }

    public InstallerResult assetCancelDownloadAwait(String str, long j) {
        final InstallerResult[] installerResultArr = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        assetCancelDownload(str, j, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass7 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                installerResultArr[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return installerResultArr[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(str, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public InstallerResult assetCancelDownloadAwait(String str, String str2) {
        final InstallerResult[] installerResultArr = new InstallerResult[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        assetCancelDownload(str, str2, new InstallerCallback() {
            /* class com.oculus.libraryapi.OVRLibrary.AnonymousClass8 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                installerResultArr[0] = installerResult;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            return installerResultArr[0];
        } catch (InterruptedException e) {
            Log.e(TAG, "interrupted!", e);
            return InstallerResult.createForError(str, System.currentTimeMillis(), InstallerResultError.UNKNOWN_ERROR);
        }
    }

    public boolean assetDelete(String str, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("asset_id", j);
        return assetDelete(str, bundle);
    }

    public boolean assetDelete(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("asset_name", str2);
        return assetDelete(str, bundle);
    }

    private boolean assetDelete(String str, Bundle bundle) {
        Bundle call = this.mContext.getContentResolver().call(OCMSLibraryContract.providerUri(), OCMSLibraryContract.METHOD_UNINSTALL_ASSET, str, bundle);
        if (call != null) {
            return call.getBoolean(OCMSLibraryContract.EXTRA_UNINSTALL_ASSET_RESULT, false);
        }
        return false;
    }

    public void registerInstallListener(InstallListener installListener) {
        registerObserverForInstallUri(OCMSLibraryContract.uriForAllInstalls(), installListener);
    }

    public synchronized void unregisterInstallListener(InstallListener installListener) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        ArrayList<InstallContentObserver> remove = this.mInstallListeners.remove(installListener);
        if (remove != null) {
            for (InstallContentObserver installContentObserver : remove) {
                contentResolver.unregisterContentObserver(installContentObserver);
                if (installContentObserver.handler != null) {
                    installContentObserver.handler.removeCallbacksAndMessages(null);
                }
            }
        }
    }

    private synchronized void registerObserverForInstallUri(Uri uri, InstallListener installListener) {
        ArrayList<InstallContentObserver> arrayList = this.mInstallListeners.get(installListener);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mInstallListeners.put(installListener, arrayList);
        }
        Iterator<InstallContentObserver> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().uri.equals(uri)) {
                return;
            }
        }
        InstallContentObserver installContentObserver = new InstallContentObserver(uri, installListener, new Handler());
        this.mContext.getContentResolver().registerContentObserver(uri, true, installContentObserver);
        arrayList.add(installContentObserver);
    }

    public void registerDownloadListener(DownloadListener downloadListener) {
        registerObserverForDownloadUri(OCMSLibraryContract.uriForAllDownloads(), downloadListener);
    }

    public synchronized void unregisterDownloadListener(DownloadListener downloadListener) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        ArrayList<DownloadContentObserver> remove = this.mDownloadListeners.remove(downloadListener);
        if (remove != null) {
            for (DownloadContentObserver downloadContentObserver : remove) {
                contentResolver.unregisterContentObserver(downloadContentObserver);
                if (downloadContentObserver.handler != null) {
                    downloadContentObserver.handler.removeCallbacksAndMessages(null);
                }
            }
        }
    }

    private synchronized void registerObserverForDownloadUri(Uri uri, DownloadListener downloadListener) {
        ArrayList<DownloadContentObserver> arrayList = this.mDownloadListeners.get(downloadListener);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mDownloadListeners.put(downloadListener, arrayList);
        }
        Iterator<DownloadContentObserver> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().uri.equals(uri)) {
                return;
            }
        }
        DownloadContentObserver downloadContentObserver = new DownloadContentObserver(uri, downloadListener, new Handler());
        this.mContext.getContentResolver().registerContentObserver(uri, true, downloadContentObserver);
        arrayList.add(downloadContentObserver);
    }

    public void registerAppsChangeListener(OnChangeListener onChangeListener) {
        registerObserverForAppChangeUri(OCMSLibraryContract.uriForAllPackages(), onChangeListener);
    }

    public void registerAppChangeListener(String str, OnChangeListener onChangeListener) {
        registerObserverForAppChangeUri(OCMSLibraryContract.uriForPackage(str), onChangeListener);
    }

    public synchronized void unregisterAppChangeListener(OnChangeListener onChangeListener) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        ArrayList<LibraryContentObserver> remove = this.mEntitlementListeners.remove(onChangeListener);
        if (remove != null) {
            for (LibraryContentObserver libraryContentObserver : remove) {
                contentResolver.unregisterContentObserver(libraryContentObserver);
                if (libraryContentObserver.handler != null) {
                    libraryContentObserver.handler.removeCallbacksAndMessages(null);
                }
            }
        }
    }

    private synchronized void registerObserverForAppChangeUri(Uri uri, OnChangeListener onChangeListener) {
        ArrayList<LibraryContentObserver> arrayList = this.mEntitlementListeners.get(onChangeListener);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mEntitlementListeners.put(onChangeListener, arrayList);
        }
        Iterator<LibraryContentObserver> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().uri.equals(uri)) {
                return;
            }
        }
        LibraryContentObserver libraryContentObserver = new LibraryContentObserver(uri, onChangeListener, new Handler());
        this.mContext.getContentResolver().registerContentObserver(uri, true, libraryContentObserver);
        arrayList.add(libraryContentObserver);
    }

    private ResultReceiver prepareResultReceiverForIPC(ResultReceiver resultReceiver) {
        if (resultReceiver == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }
}
