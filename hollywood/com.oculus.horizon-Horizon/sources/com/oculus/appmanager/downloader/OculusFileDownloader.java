package com.oculus.appmanager.downloader;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.base.Strings;
import com.oculus.appmanager.downloader.contract.errors.ErrorCategories;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.downloader.OculusDownloader;
import com.oculus.downloader.contract.DownloaderContract;
import com.oculus.downloader.dispatcher.OculusDownloadListenerDispatcher;
import com.oculus.downloader.extras.DownloadExtras;
import com.oculus.downloader.model.DownloadInfo;
import com.oculus.downloader.util.OculusDownloadManagerUtils;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.extras.Extras;
import com.oculus.extras.ExtrasBuilder;
import com.oculus.horizon.linkedaccounts.database.contract.LinkedAccountsSQLiteContract;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.http.useragent.UserAgentString;
import com.oculus.util.thread.ThreadUtils;
import java.util.HashMap;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID", "_UL__ULSEP_android_app_DownloadManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_downloader_extras_DownloadExtras_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_downloader_dispatcher_OculusDownloadListenerDispatcher_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OculusFileDownloader implements OculusDownloader {
    public static final String ACCEPT_BINARY_STREAM = "application/octet-stream";
    public static final String KEY_USER_CANCELLED = "key_user_cancelled";
    public static final Class<?> TAG = OculusFileDownloader.class;
    public static final long UNKNOWN_DOWNLOAD_SIZE = -1;
    public static volatile OculusFileDownloader _UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final OculusDownloadListenerDispatcher mDownloadListenerDispatcher;
    @Inject
    @Eager
    public final DownloadManager mDownloadManager;
    public final OculusDownloadManagerUtils mDownloadManagerUtils;
    public final OculusDownloadRequestFactory mDownloadRequestFactory;
    public final Object mExtrasLock = new Object();
    @Inject
    @Eager
    public final DownloadExtras mExtrasManager;
    public final Handler mHandler;
    @Inject
    @Eager
    public final InfoUtils mInfoUtils;
    @Inject
    @Eager
    @UserAgentString
    public final String mUserAgentString;

    public static class DownloadState {
        public long downloadedTimestamp;
        public boolean isNotificationSent;
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/database/Cursor;)Lcom/google/common/base/Optional<Lcom/oculus/downloader/model/DownloadInfo;>; */
    public static final Optional A01(OculusFileDownloader oculusFileDownloader, Cursor cursor) {
        long j;
        int i;
        int i2;
        String string;
        long j2;
        long j3;
        String string2;
        Extras A02;
        Optional present;
        synchronized (oculusFileDownloader.mExtrasLock) {
            int columnIndex = cursor.getColumnIndex(LinkedAccountsSQLiteContract.Columns._ID);
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
            A02 = A02(oculusFileDownloader, j);
        }
        if (string == null) {
            present = Absent.INSTANCE;
        } else {
            present = new Present(string);
        }
        return Optional.of(new DownloadInfo(j, i, i2, j2, j3, present, Optional.of(string2), A02));
    }

    public static final Extras A02(OculusFileDownloader oculusFileDownloader, long j) {
        Extras extras;
        synchronized (oculusFileDownloader.mExtrasLock) {
            DownloadExtras downloadExtras = oculusFileDownloader.mExtrasManager;
            synchronized (downloadExtras) {
                Cursor query = downloadExtras.mDownloadExtrasSQLHelper.getWritableDatabase().query(downloadExtras.mTableName, new String[]{downloadExtras.mKeyColumnName, downloadExtras.mValueColumnName}, AnonymousClass006.A05(downloadExtras.mIdColumnName, " = ?"), new String[]{Long.toString(j)}, null, null, null);
                if (query == null) {
                    extras = new Extras(new ExtrasBuilder().mData);
                } else if (!query.moveToFirst()) {
                    query.close();
                    extras = new Extras(new ExtrasBuilder().mData);
                } else {
                    HashMap hashMap = new HashMap();
                    int columnIndex = query.getColumnIndex(downloadExtras.mKeyColumnName);
                    int columnIndex2 = query.getColumnIndex(downloadExtras.mValueColumnName);
                    while (!query.isAfterLast()) {
                        String string = query.getString(columnIndex);
                        String string2 = query.getString(columnIndex2);
                        if (!(string == null || string2 == null)) {
                            hashMap.put(string, string2);
                        }
                        query.moveToNext();
                    }
                    query.close();
                    extras = new Extras(hashMap);
                }
            }
        }
        return extras;
    }

    @Inject
    public OculusFileDownloader(AbstractC06640p5 r4) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r4);
        this.mUserAgentString = UserAgentModule.A01(r4);
        this.mDownloadManager = (DownloadManager) AnonymousClass117.A00(552, r4);
        this.mExtrasManager = (DownloadExtras) AnonymousClass117.A00(319, r4);
        this.mDownloadListenerDispatcher = (OculusDownloadListenerDispatcher) AnonymousClass117.A00(48, r4);
        this.mInfoUtils = (InfoUtils) AnonymousClass117.A00(567, r4);
        OculusDownloadManagerUtils oculusDownloadManagerUtils = new OculusDownloadManagerUtils((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext));
        this.mDownloadManagerUtils = oculusDownloadManagerUtils;
        this.mDownloadRequestFactory = new OculusDownloadRequestFactory(oculusDownloadManagerUtils);
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    /* JADX WARN: Incorrect args count in method signature: (J)Lcom/google/common/base/Optional<Lcom/oculus/downloader/model/DownloadInfo;>; */
    public static final Optional A00(OculusFileDownloader oculusFileDownloader, long j) {
        Optional optional;
        ThreadUtils.A02();
        synchronized (oculusFileDownloader.mExtrasLock) {
            DownloadManager.Query query = new DownloadManager.Query();
            boolean z = true;
            query.setFilterById(j);
            optional = Absent.INSTANCE;
            if (((Context) AnonymousClass0J2.A03(0, 294, oculusFileDownloader._UL_mInjectionContext)).checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
                z = false;
            }
            if (!z) {
                ((IErrorReporter) AnonymousClass0J2.A03(2, 428, oculusFileDownloader._UL_mInjectionContext)).A96(ErrorCategories.INTERNET_PERMISSION_MISSING, "DownloadManager Query missing INTERNET permission");
            } else {
                Cursor query2 = oculusFileDownloader.mDownloadManager.query(query);
                if (query2 == null) {
                    ApkUpdateInfo A01 = oculusFileDownloader.mInfoUtils.A01(j);
                    if (j == 0) {
                        ((IErrorReporter) AnonymousClass0J2.A03(2, 428, oculusFileDownloader._UL_mInjectionContext)).A96(ErrorCategories.NULL_DOWNLOAD_ID, "Null download ID returned by DownloadManager");
                    } else if (A01 != null && A01.A02() == ApkUpdateInfoContract.UpdateState.DOWNLOADING) {
                        ((IErrorReporter) AnonymousClass0J2.A03(2, 428, oculusFileDownloader._UL_mInjectionContext)).A96(ErrorCategories.VALID_DOWNLOAD_ID_NOT_IN_DB, "DownloadID valid but missing in DownloadManager's DB");
                    } else if (A01 == null || A01.A02() == ApkUpdateInfoContract.UpdateState.DOWNLOADING) {
                        ((IErrorReporter) AnonymousClass0J2.A03(2, 428, oculusFileDownloader._UL_mInjectionContext)).A96(ErrorCategories.INVALID_DOWNLOAD_ID, "Invalid download ID returned by DownloadManager");
                    } else {
                        ((IErrorReporter) AnonymousClass0J2.A03(2, 428, oculusFileDownloader._UL_mInjectionContext)).A96(ErrorCategories.DOWNLOAD_ID_NOT_IN_DOWNLOADING_STATE, "DownloadID not in DOWNLOADING state in entitlements DB");
                    }
                } else {
                    try {
                        if (query2.moveToFirst()) {
                            optional = A01(oculusFileDownloader, query2);
                        }
                    } catch (Exception e) {
                        AnonymousClass0NO.A04(OculusFileDownloader.class, "Exception raised while querying download info: ", e);
                    } catch (Throwable th) {
                        query2.close();
                        throw th;
                    }
                    query2.close();
                }
            }
        }
        return optional;
    }

    public static final void A03(OculusFileDownloader oculusFileDownloader, long j, Extras extras) {
        ThreadUtils.A02();
        synchronized (oculusFileDownloader.mExtrasLock) {
            oculusFileDownloader.mExtrasManager.A00(j, extras);
        }
    }
}
