package com.oculus.appmanager.downloader.progress;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Optional;
import com.oculus.appmanager.downloader.OculusFileDownloader;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.appmanager.installer.broadcast.AssetBroadcastDispatch;
import com.oculus.appmanager.installer.events.DownloadResponse;
import com.oculus.appmanager.installer.events.DownloadingResponse;
import com.oculus.appmanager.installer.events.InstallCancelledResponse;
import com.oculus.appmanager.installer.events.InstallerEventBus;
import com.oculus.downloader.contract.DownloaderContract;
import com.oculus.downloader.extras.DownloadExtras;
import com.oculus.downloader.extras.contract.DownloadExtrasKeys;
import com.oculus.downloader.model.DownloadInfo;
import com.oculus.downloader.progress.DownloadProgressChangeListener;
import com.oculus.downloader.progress.DownloadProgressTracker;
import com.oculus.downloader.progress.model.DownloadProgressUnit;
import com.oculus.extras.Extras;
import com.oculus.extras.ExtrasBuilder;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.util.network.NetworkUtils;
import com.oculus.util.thread.ThreadUtils;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_broadcast_AssetBroadcastDispatch_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_downloader_extras_DownloadExtras_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_downloader_contract_DownloaderContract_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OculusDownloadProgressTracker implements DownloadProgressTracker {
    public static final String CALLING_PACKAGE = "calling_package";
    public static final Uri DOWNLOADS_CONTENT_URI = Uri.parse("content://downloads/my_downloads");
    public static final String DOWNLOADS_OBSERVE_THREAD = "downloads_observe_thread";
    public static final String DOWNLOAD_BINARY_TYPE = "download_binary_type";
    public static final String DOWNLOAD_CONNECTION_IS_ROAMING = "download_connection_is_roaming";
    public static final String DOWNLOAD_CONNECTION_SPEED = "download_connection_speed";
    public static final String DOWNLOAD_CONNECTION_TYPE = "download_connection_type";
    public static final String DOWNLOAD_ID = "download_id";
    public static final String DOWNLOAD_PROGRESS = "download_progress";
    public static final String DOWNLOAD_RESOURCE_NAME = "download_resource_name";
    public static final String DOWNLOAD_SCHEDULE_TIME_MS = "download_schedule_time_ms";
    public static final String DOWNLOAD_START_TIME_MS = "download_start_time_ms";
    public static final String DOWNLOAD_URI = "download_uri";
    public static final String EVENT_DOWNLOAD_PROGRESS = "moonlight_download_progress";
    public static final String EVENT_DOWNLOAD_STARTED = "moonlight_download_started";
    public static final long INVALID_DOWNLOAD_ID = -1;
    public static final String NUMBER_REGEX = "\\d+";
    public static final float[] PROGRESS_REPORT_THRESHOLD = {0.25f, 0.5f, 0.75f};
    public static final String TAG = "OculusDownloadProgressTracker";
    public static volatile OculusDownloadProgressTracker _UL__ULSEP_com_oculus_appmanager_downloader_progress_OculusDownloadProgressTracker_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final AssetBroadcastDispatch mAssetBroadcastDispatch;
    public final ContentResolver mContentResolver;
    @Inject
    @Eager
    public final DownloaderContract mDownloaderContract;
    public HandlerThread mDownloadsObserveThread;
    @Inject
    @Eager
    public final DownloadExtras mExtrasManager;
    @Inject
    @Eager
    public final InfoUtils mInfoUtils;
    @GuardedBy("mSync")
    public boolean mIsTrackingChanges;
    public final Set<DownloadProgressChangeListener> mListeners = new HashSet();
    @Inject
    @Eager
    public final OculusFileDownloader mOculusDownloader;
    public final Object mSync = new Object();
    @Inject
    @Eager
    public final ThreadUtils mThreadUtils;
    @GuardedBy("mSync")
    public final Map<String, DownloadProgressUnit> mUnCompletedDownloads = new HashMap();

    public class DownloadsObserver extends ContentObserver {
        public DownloadsObserver(Handler handler) {
            super(handler);
        }

        public final void onChange(boolean z) {
            onChange(z, null);
        }

        public final void onChange(boolean z, Uri uri) {
            DownloadInfo downloadInfo;
            String lastPathSegment = uri.getLastPathSegment();
            if (lastPathSegment.matches(OculusDownloadProgressTracker.NUMBER_REGEX)) {
                long parseLong = Long.parseLong(lastPathSegment);
                if (parseLong != -1) {
                    OculusDownloadProgressTracker oculusDownloadProgressTracker = OculusDownloadProgressTracker.this;
                    ThreadUtils.A02();
                    Optional A00 = OculusFileDownloader.A00(oculusDownloadProgressTracker.mOculusDownloader, parseLong);
                    if (A00.isPresent() && (downloadInfo = (DownloadInfo) A00.get()) != null) {
                        if (downloadInfo.status == 16) {
                            oculusDownloadProgressTracker.mDownloaderContract.A00(parseLong);
                        } else {
                            OculusDownloadProgressTracker.A03(oculusDownloadProgressTracker, downloadInfo);
                        }
                    }
                }
            }
        }
    }

    @Nullable
    private DownloadInfo A00(long j) {
        DownloadInfo downloadInfo;
        Optional A00 = OculusFileDownloader.A00(this.mOculusDownloader, j);
        if (A00.isPresent() && (downloadInfo = (DownloadInfo) A00.get()) != null) {
            return downloadInfo;
        }
        AnonymousClass0NO.A0E(TAG, "Failed to get download info for id: %s", Long.valueOf(j));
        return null;
    }

    private void A02(String str) {
        synchronized (this.mSync) {
            this.mUnCompletedDownloads.remove(str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:0x02fa A[SYNTHETIC, Splitter:B:109:0x02fa] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x030c  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0314  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x03f9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A03(com.oculus.appmanager.downloader.progress.OculusDownloadProgressTracker r29, com.oculus.downloader.model.DownloadInfo r30) {
        /*
        // Method dump skipped, instructions count: 1148
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.appmanager.downloader.progress.OculusDownloadProgressTracker.A03(com.oculus.appmanager.downloader.progress.OculusDownloadProgressTracker, com.oculus.downloader.model.DownloadInfo):boolean");
    }

    @Override // com.oculus.downloader.progress.DownloadProgressTracker
    public final void A9M() {
        InstallerEventBus installerEventBus;
        synchronized (this.mSync) {
            if (!this.mIsTrackingChanges) {
                this.mIsTrackingChanges = true;
                if (this.mDownloadsObserveThread == null) {
                    HandlerThread handlerThread = new HandlerThread(DOWNLOADS_OBSERVE_THREAD);
                    this.mDownloadsObserveThread = handlerThread;
                    handlerThread.start();
                }
                this.mContentResolver.registerContentObserver(DOWNLOADS_CONTENT_URI, true, new DownloadsObserver(new Handler(this.mDownloadsObserveThread.getLooper())));
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    /* class com.oculus.appmanager.downloader.progress.OculusDownloadProgressTracker.AnonymousClass1 */

                    public final void run() {
                        OculusDownloadProgressTracker oculusDownloadProgressTracker = OculusDownloadProgressTracker.this;
                        ThreadUtils.A02();
                        HashSet hashSet = new HashSet();
                        OculusFileDownloader oculusFileDownloader = oculusDownloadProgressTracker.mOculusDownloader;
                        Cursor query = oculusFileDownloader.mDownloadManager.query(new DownloadManager.Query());
                        ArrayList<DownloadInfo> arrayList = new ArrayList();
                        if (query != null) {
                            while (query.moveToNext()) {
                                try {
                                    Optional A01 = OculusFileDownloader.A01(oculusFileDownloader, query);
                                    if (A01.isPresent()) {
                                        arrayList.add(A01.get());
                                    }
                                } finally {
                                    query.close();
                                }
                            }
                        }
                        for (DownloadInfo downloadInfo : arrayList) {
                            if (OculusDownloadProgressTracker.A03(oculusDownloadProgressTracker, downloadInfo)) {
                                Optional<String> optional = downloadInfo.description;
                                if (optional.isPresent()) {
                                    hashSet.add(optional.get());
                                }
                            }
                        }
                        synchronized (oculusDownloadProgressTracker.mSync) {
                            oculusDownloadProgressTracker.mUnCompletedDownloads.keySet().retainAll(hashSet);
                        }
                    }
                });
                synchronized (InstallerEventBus.class) {
                    installerEventBus = InstallerEventBus.sInstance;
                    if (installerEventBus == null) {
                        installerEventBus = new InstallerEventBus();
                        InstallerEventBus.sInstance = installerEventBus;
                    }
                }
                if (ThreadUtils.A04()) {
                    installerEventBus.mUIBus.A04(this);
                } else {
                    installerEventBus.mNonUIBus.A04(this);
                }
            }
        }
    }

    @Subscribe
    public void onDownloadResponse(DownloadResponse downloadResponse) {
        A02(downloadResponse.installIdentifier);
    }

    @Subscribe
    public void onDownloadingResponse(DownloadingResponse downloadingResponse) {
        A02(downloadingResponse.installIdentifier);
    }

    @Subscribe
    public void onInstallCancelled(InstallCancelledResponse installCancelledResponse) {
        A02(installCancelledResponse.installIdentifier);
    }

    @Inject
    public OculusDownloadProgressTracker(AbstractC06640p5 r4) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r4);
        this.mInfoUtils = (InfoUtils) AnonymousClass117.A00(567, r4);
        this.mOculusDownloader = (OculusFileDownloader) AnonymousClass117.A00(429, r4);
        this.mAssetBroadcastDispatch = (AssetBroadcastDispatch) AnonymousClass117.A00(303, r4);
        this.mExtrasManager = (DownloadExtras) AnonymousClass117.A00(319, r4);
        this.mThreadUtils = ThreadUtils.A01(r4);
        this.mDownloaderContract = (DownloaderContract) AnonymousClass117.A00(571, r4);
        this.mContentResolver = ((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)).getContentResolver();
    }

    private void A01(long j, ApkUpdateInfo apkUpdateInfo, String str) {
        ThreadUtils.A02();
        DownloadInfo A00 = A00(j);
        if (A00 != null) {
            ExtrasBuilder extrasBuilder = new ExtrasBuilder(A00.extras.mData);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            extrasBuilder.A00(DownloadExtrasKeys.KEY_DOWNLOAD_START_TIME_MS, Long.toString(elapsedRealtime));
            Extras extras = new Extras(extrasBuilder.mData);
            ThreadUtils.A03("cannot be run on ui thread");
            this.mExtrasManager.A00(j, extras);
            Event A22 = ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A22("moonlight_download_started");
            A22.A1G();
            A22.A14("download_id", j);
            A22.A15(DOWNLOAD_RESOURCE_NAME, str);
            String str2 = apkUpdateInfo.downloadUrl;
            if (str2 == null) {
                str2 = "null";
            }
            A22.A15("download_uri", str2);
            A22.A15("download_connection_type", NetworkUtils.A02((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)));
            A22.A15("download_connection_speed", NetworkUtils.A01((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)));
            NetworkInfo A002 = NetworkUtils.A00((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext));
            boolean z = false;
            if (A002 != null && A002.isConnectedOrConnecting() && A002.isRoaming()) {
                z = true;
            }
            A22.A16("download_connection_is_roaming", z);
            A22.A14("download_schedule_time_ms", A00.extras.A00(DownloadExtrasKeys.KEY_DOWNLOAD_SCHEDULED_TIME_MS, -1));
            A22.A14("download_start_time_ms", elapsedRealtime);
            A22.A15(DOWNLOAD_BINARY_TYPE, InfoUtils.A00(apkUpdateInfo));
            String str3 = apkUpdateInfo.requestingPackage;
            if (str3 == null) {
                str3 = "null";
            }
            A22.A15("calling_package", str3);
            A22.A5L();
        }
    }
}
