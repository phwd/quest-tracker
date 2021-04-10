package com.oculus.appmanager.downloader;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.facebook.common.android.AndroidModule;
import com.facebook.debug.log.BLog;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.oculus.appmanager.constants.OculusAppManagerErrorCodes;
import com.oculus.appmanager.downloader.OculusDownloaderModule;
import com.oculus.appmanager.downloader.contract.errors.ErrorCategories;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.downloader.OculusDownloadListener;
import com.oculus.downloader.OculusDownloader;
import com.oculus.downloader.contract.DownloaderContract;
import com.oculus.downloader.dispatcher.OculusDownloadListenerDispatcher;
import com.oculus.downloader.extras.DownloadExtras;
import com.oculus.downloader.extras.contract.DownloadExtrasKeys;
import com.oculus.downloader.model.DownloadConfig;
import com.oculus.downloader.model.DownloadInfo;
import com.oculus.downloader.util.OculusDownloadManagerUtils;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.extras.Extras;
import com.oculus.extras.ExtrasBuilder;
import com.oculus.http.headers.RequestHeaders;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.http.useragent.UserAgentString;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;
import com.oculus.util.thread.ThreadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID", "_UL__ULSEP_android_app_DownloadManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_downloader_extras_DownloadExtras_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_downloader_dispatcher_OculusDownloadListenerDispatcher_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OculusFileDownloader implements OculusDownloader {
    private static final String ACCEPT_BINARY_STREAM = "application/octet-stream";
    private static final String KEY_USER_CANCELLED = "key_user_cancelled";
    private static final Class<?> TAG = OculusFileDownloader.class;
    private static final long UNKNOWN_DOWNLOAD_SIZE = -1;
    private static volatile OculusFileDownloader _UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_INSTANCE;
    private InjectionContext _UL_mInjectionContext;
    @Inject
    @Eager
    private final OculusDownloadListenerDispatcher mDownloadListenerDispatcher;
    @Inject
    @Eager
    private final DownloadManager mDownloadManager;
    private final OculusDownloadManagerUtils mDownloadManagerUtils;
    private final OculusDownloadRequestFactory mDownloadRequestFactory;
    private final Object mExtrasLock = new Object();
    @Inject
    @Eager
    private final DownloadExtras mExtrasManager;
    private final Handler mHandler;
    @Inject
    @Eager
    private final InfoUtils mInfoUtils;
    @Inject
    @Eager
    @UserAgentString
    private final String mUserAgentString;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_downloader_OculusFileDownloader_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(OculusDownloaderModule.UL_id._UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final OculusFileDownloader _UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OculusFileDownloader) UL.factorymap.get(OculusDownloaderModule.UL_id._UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OculusFileDownloader _UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_INSTANCE == null) {
            synchronized (OculusFileDownloader.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_INSTANCE = new OculusFileDownloader(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_downloader_OculusFileDownloader_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(OculusDownloaderModule.UL_id._UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_BINDING_ID, injectorLike);
    }

    /* access modifiers changed from: private */
    public static class DownloadState {
        long downloadedTimestamp;
        boolean isNotificationSent;

        private DownloadState() {
        }
    }

    @Inject
    public OculusFileDownloader(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(3, injectorLike);
        this.mUserAgentString = UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_ACCESS_METHOD(injectorLike);
        this.mDownloadManager = AndroidModule._UL__ULSEP_android_app_DownloadManager_ULSEP_ACCESS_METHOD(injectorLike);
        this.mExtrasManager = DownloadExtras._UL__ULSEP_com_oculus_downloader_extras_DownloadExtras_ULSEP_ACCESS_METHOD(injectorLike);
        this.mDownloadListenerDispatcher = OculusDownloadListenerDispatcher._UL__ULSEP_com_oculus_downloader_dispatcher_OculusDownloadListenerDispatcher_ULSEP_ACCESS_METHOD(injectorLike);
        this.mInfoUtils = InfoUtils._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_ACCESS_METHOD(injectorLike);
        this.mDownloadManagerUtils = new OculusDownloadManagerUtils((Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID, this._UL_mInjectionContext));
        this.mDownloadRequestFactory = new OculusDownloadRequestFactory(this.mDownloadManagerUtils);
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void addListener(OculusDownloadListener oculusDownloadListener) {
        this.mDownloadListenerDispatcher.addListener(oculusDownloadListener);
    }

    public void addStrongListener(OculusDownloadListener oculusDownloadListener) {
        this.mDownloadListenerDispatcher.addStrongListener(oculusDownloadListener);
    }

    public void removeListener(OculusDownloadListener oculusDownloadListener) {
        this.mDownloadListenerDispatcher.removeListener(oculusDownloadListener);
    }

    @Override // com.oculus.downloader.OculusDownloader
    public long enqueue(DownloadConfig downloadConfig, Extras extras) {
        long enqueue;
        ThreadUtils.assertOnNonUiThread();
        BLog.d(TAG, "Download request: %s, extras: %s", downloadConfig, extras);
        DownloadManager.Request createAuthenticatedRequestWithDefaultConfig = this.mDownloadRequestFactory.createAuthenticatedRequestWithDefaultConfig(downloadConfig.uri);
        createAuthenticatedRequestWithDefaultConfig.addRequestHeader("Accept", ACCEPT_BINARY_STREAM);
        createAuthenticatedRequestWithDefaultConfig.addRequestHeader("User-Agent", this.mUserAgentString);
        for (Map.Entry<String, String> entry : RequestHeaders.DEFAULT_REQUEST_HEADERS.entrySet()) {
            createAuthenticatedRequestWithDefaultConfig.addRequestHeader(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry2 : downloadConfig.headers.entrySet()) {
            createAuthenticatedRequestWithDefaultConfig.addRequestHeader(entry2.getKey(), entry2.getValue());
        }
        createAuthenticatedRequestWithDefaultConfig.setAllowedNetworkTypes(2);
        createAuthenticatedRequestWithDefaultConfig.setAllowedOverRoaming(true);
        this.mDownloadManagerUtils.setAllowedOverMetered(createAuthenticatedRequestWithDefaultConfig, false);
        if ((downloadConfig.networks & 1) != 0) {
            createAuthenticatedRequestWithDefaultConfig.setAllowedNetworkTypes(3);
            this.mDownloadManagerUtils.setAllowedOverMetered(createAuthenticatedRequestWithDefaultConfig, true);
        }
        this.mDownloadManagerUtils.setNotificationVisibility(createAuthenticatedRequestWithDefaultConfig, downloadConfig.visibility);
        if (!Strings.isNullOrEmpty(downloadConfig.fileMimeType)) {
            createAuthenticatedRequestWithDefaultConfig.setMimeType(downloadConfig.fileMimeType);
        }
        if (!Strings.isNullOrEmpty(downloadConfig.title)) {
            createAuthenticatedRequestWithDefaultConfig.setTitle(downloadConfig.title);
        }
        if (!Strings.isNullOrEmpty(downloadConfig.description)) {
            createAuthenticatedRequestWithDefaultConfig.setDescription(downloadConfig.description);
        }
        createAuthenticatedRequestWithDefaultConfig.setNotificationVisibility(2);
        File file = new File(new File(DownloaderContract.EXTERNAL_DOWNLOAD_SUB_DIRECTORY), downloadConfig.fileUri);
        file.getParentFile().mkdirs();
        createAuthenticatedRequestWithDefaultConfig.setDestinationUri(Uri.fromFile(file));
        synchronized (this.mExtrasLock) {
            enqueue = this.mDownloadManager.enqueue(createAuthenticatedRequestWithDefaultConfig);
            ExtrasBuilder buildUpon = extras.buildUpon();
            buildUpon.putLong(DownloadExtrasKeys.KEY_DOWNLOAD_SCHEDULED_TIME_MS, ((Clock) FbInjector.lazyInstance(1, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).elapsedRealtime());
            buildUpon.putString(DownloadExtrasKeys.KEY_CURRENT_DOWNLOAD_URI, downloadConfig.uri);
            setDownloadExtras(enqueue, buildUpon.build());
        }
        BLog.d(TAG, "New download scheduled: %d", Long.valueOf(enqueue));
        return enqueue;
    }

    @Override // com.oculus.downloader.OculusDownloader
    public boolean remove(final long j) {
        int remove;
        ThreadUtils.assertOnNonUiThread();
        BLog.d(TAG, "Download remove request: %d", Long.valueOf(j));
        synchronized (this.mExtrasLock) {
            Extras downloadExtras = getDownloadExtras(j);
            if (downloadExtras != null) {
                ExtrasBuilder buildUpon = downloadExtras.buildUpon();
                buildUpon.putBoolean(KEY_USER_CANCELLED, true);
                setDownloadExtras(j, buildUpon.build());
            }
            remove = this.mDownloadManager.remove(j);
            AnonymousClass1 r3 = new Runnable() {
                /* class com.oculus.appmanager.downloader.OculusFileDownloader.AnonymousClass1 */

                public void run() {
                    OculusFileDownloader.this.mDownloadManager.remove(j);
                }
            };
            this.mHandler.postDelayed(r3, TimeUnit.SECONDS.toMillis(1));
            this.mHandler.postDelayed(r3, TimeUnit.SECONDS.toMillis(5));
        }
        if (remove > 0) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.downloader.OculusDownloader
    public int queryRunningDownloads() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterByStatus(7);
        Cursor query2 = this.mDownloadManager.query(query);
        if (query2 == null) {
            return 0;
        }
        int count = query2.getCount();
        query2.close();
        return count;
    }

    @Override // com.oculus.downloader.OculusDownloader
    public List<DownloadInfo> queryAll() {
        Cursor query = this.mDownloadManager.query(new DownloadManager.Query());
        ArrayList arrayList = new ArrayList();
        if (query == null) {
            return arrayList;
        }
        while (query.moveToNext()) {
            try {
                Optional<DownloadInfo> downloadInfo = toDownloadInfo(query);
                if (downloadInfo.isPresent()) {
                    arrayList.add(downloadInfo.get());
                }
            } finally {
                query.close();
            }
        }
        return arrayList;
    }

    @Override // com.oculus.downloader.OculusDownloader
    @Nullable
    public DownloadInfo query(long j) {
        Optional<DownloadInfo> downloadInfo = toDownloadInfo(j);
        if (downloadInfo.isPresent()) {
            return downloadInfo.get();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void onDownloadComplete(long j) {
        Extras downloadExtras;
        ThreadUtils.assertOnNonUiThread();
        BLog.d(TAG, "Download complete: %d", Long.valueOf(j));
        Optional<DownloadInfo> downloadInfo = toDownloadInfo(j);
        if (!downloadInfo.isPresent()) {
            BLog.d(TAG, "Download info not found: %d", Long.valueOf(j));
            synchronized (this.mExtrasLock) {
                downloadExtras = getDownloadExtras(j);
            }
            this.mDownloadListenerDispatcher.dispatchDownloadComplete(new DownloadInfo(j, 16, downloadExtras.getBoolean(KEY_USER_CANCELLED, false) ? OculusAppManagerErrorCodes.ERROR_USER_CANCELLED_DOWNLOAD : OculusAppManagerErrorCodes.ERROR_UNKNOWN, -1, 0, Optional.absent(), Optional.absent(), downloadExtras));
            return;
        }
        DownloadInfo downloadInfo2 = downloadInfo.get();
        BLog.d(TAG, "On download complete: %d, extras = %s", Long.valueOf(j), downloadInfo2.extras);
        DownloadState downloadState = getDownloadState(downloadInfo2.extras);
        if (!downloadState.isNotificationSent) {
            if (downloadInfo2.status == 8 || downloadInfo2.status == 16) {
                this.mDownloadListenerDispatcher.dispatchDownloadComplete(downloadInfo2);
                downloadState.isNotificationSent = true;
                downloadState.downloadedTimestamp = System.currentTimeMillis();
                synchronized (this.mExtrasLock) {
                    ExtrasBuilder builder = Extras.builder();
                    putDownloadState(downloadState, builder);
                    setDownloadExtras(j, builder.build());
                }
            }
        }
    }

    private DownloadState getDownloadState(Extras extras) {
        DownloadState downloadState = new DownloadState();
        downloadState.isNotificationSent = extras.getBoolean(DownloadExtrasKeys.KEY_IS_NOTIFICATION_SENT, false);
        downloadState.downloadedTimestamp = extras.getLong(DownloadExtrasKeys.KEY_EXPIRATION_TIMESTAMP, 0);
        return downloadState;
    }

    private void putDownloadState(DownloadState downloadState, ExtrasBuilder extrasBuilder) {
        extrasBuilder.putBoolean(DownloadExtrasKeys.KEY_IS_NOTIFICATION_SENT, downloadState.isNotificationSent).putLong(DownloadExtrasKeys.KEY_EXPIRATION_TIMESTAMP, downloadState.downloadedTimestamp);
    }

    public Optional<DownloadInfo> toDownloadInfo(long j) {
        ThreadUtils.assertOnNonUiThread();
        synchronized (this.mExtrasLock) {
            DownloadManager.Query query = new DownloadManager.Query();
            boolean z = true;
            query.setFilterById(j);
            Optional<DownloadInfo> absent = Optional.absent();
            if (((Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID, this._UL_mInjectionContext)).checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
                z = false;
            }
            if (!z) {
                ((IErrorReporter) FbInjector.lazyInstance(2, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.INTERNET_PERMISSION_MISSING, "DownloadManager Query missing INTERNET permission");
                return absent;
            }
            Cursor query2 = this.mDownloadManager.query(query);
            if (query2 == null) {
                ApkUpdateInfo update = this.mInfoUtils.getUpdate(j);
                if (j == 0) {
                    ((IErrorReporter) FbInjector.lazyInstance(2, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.NULL_DOWNLOAD_ID, "Null download ID returned by DownloadManager");
                } else if (update != null && update.getState() == ApkUpdateInfoContract.UpdateState.DOWNLOADING) {
                    ((IErrorReporter) FbInjector.lazyInstance(2, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.VALID_DOWNLOAD_ID_NOT_IN_DB, "DownloadID valid but missing in DownloadManager's DB");
                } else if (update == null || update.getState() == ApkUpdateInfoContract.UpdateState.DOWNLOADING) {
                    ((IErrorReporter) FbInjector.lazyInstance(2, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.INVALID_DOWNLOAD_ID, "Invalid download ID returned by DownloadManager");
                } else {
                    ((IErrorReporter) FbInjector.lazyInstance(2, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.DOWNLOAD_ID_NOT_IN_DOWNLOADING_STATE, "DownloadID not in DOWNLOADING state in entitlements DB");
                }
                return absent;
            }
            try {
                if (query2.moveToFirst()) {
                    absent = toDownloadInfo(query2);
                }
            } catch (Exception e) {
                BLog.w(TAG, "Exception raised while querying download info: ", e);
            } catch (Throwable th) {
                query2.close();
                throw th;
            }
            query2.close();
            return absent;
        }
    }

    public Optional<DownloadInfo> toDownloadInfo(Cursor cursor) {
        long j;
        int i;
        int i2;
        String string;
        long j2;
        long j3;
        String string2;
        Extras downloadExtras;
        synchronized (this.mExtrasLock) {
            int columnIndex = cursor.getColumnIndex("_id");
            int columnIndex2 = cursor.getColumnIndex("status");
            int columnIndex3 = cursor.getColumnIndex("reason");
            int columnIndex4 = cursor.getColumnIndex("local_uri");
            int columnIndex5 = cursor.getColumnIndex("total_size");
            int columnIndex6 = cursor.getColumnIndex("bytes_so_far");
            int columnIndex7 = cursor.getColumnIndex("description");
            j = cursor.getLong(columnIndex);
            i = cursor.getInt(columnIndex2);
            i2 = cursor.getInt(columnIndex3);
            string = cursor.getString(columnIndex4);
            j2 = cursor.getLong(columnIndex5);
            j3 = cursor.getLong(columnIndex6);
            string2 = cursor.getString(columnIndex7);
            if (!Strings.isNullOrEmpty(string) && string.contains(DownloaderContract.EXTERNAL_DOWNLOAD_SUB_DIRECTORY)) {
                string = string.substring(string.indexOf(DownloaderContract.EXTERNAL_DOWNLOAD_SUB_DIRECTORY));
            }
            downloadExtras = getDownloadExtras(j);
        }
        return Optional.of(new DownloadInfo(j, i, i2, j2, j3, Optional.fromNullable(string), Optional.of(string2), downloadExtras));
    }

    public Extras getDownloadExtras(long j) {
        Extras extras;
        synchronized (this.mExtrasLock) {
            extras = this.mExtrasManager.getExtras(j);
        }
        return extras;
    }

    public void setDownloadExtras(long j, Extras extras) {
        ThreadUtils.assertOnNonUiThread();
        synchronized (this.mExtrasLock) {
            this.mExtrasManager.setExtras(j, extras);
        }
    }
}