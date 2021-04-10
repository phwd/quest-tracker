package com.oculus.libraryapi;

import X.AnonymousClass006;
import android.content.ContentValues;
import android.content.Context;
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
import com.oculus.horizon.logging.LoggingKeys;
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
import java.util.concurrent.CountDownLatch;

public class OVRLibrary {
    public static final String ACTION_UPDATE_BROADCAST = "ovr.library.update_broadcast";
    public static final String EXTRA_ERROR_CODE = "errorCode";
    public static final String FACEBOOK_SYSTEM_INSTALLER = "com.facebook.system";
    public static final String FACEBOOK_SYSTEM_INSTALLER_DEV = "com.facebook.system.dev";
    public static final String PACKAGE_NAME_OCMS = "com.oculus.ocms";
    public static final int REFETCH_RESULT_FAILURE = 1;
    public static final int REFETCH_RESULT_OK = 0;
    public static final String TAG = "OVRLibrary";
    public static final int UNKNOWN_VERSION = Integer.MIN_VALUE;
    public final AppConverter mAppConverter;
    public final AssetConverter mAssetConverter;
    public final Context mContext;
    public final HashMap<DownloadListener, ArrayList<DownloadContentObserver>> mDownloadListeners = new HashMap<>();
    public final HashMap<OnChangeListener, ArrayList<LibraryContentObserver>> mEntitlementListeners = new HashMap<>();
    public final HashMap<InstallListener, ArrayList<InstallContentObserver>> mInstallListeners = new HashMap<>();

    /* renamed from: com.oculus.libraryapi.OVRLibrary$1  reason: invalid class name */
    public class AnonymousClass1 implements InstallerCallback {
        public final /* synthetic */ OVRLibrary this$0;
        public final /* synthetic */ CountDownLatch val$countDownLatch;
        public final /* synthetic */ InstallerResult[] val$referenceResult;
    }

    /* renamed from: com.oculus.libraryapi.OVRLibrary$2  reason: invalid class name */
    public class AnonymousClass2 implements InstallerCallback {
        public final /* synthetic */ OVRLibrary this$0;
        public final /* synthetic */ CountDownLatch val$countDownLatch;
        public final /* synthetic */ InstallerResult[] val$referenceResult;
    }

    /* renamed from: com.oculus.libraryapi.OVRLibrary$3  reason: invalid class name */
    public class AnonymousClass3 implements InstallerCallback {
        public final /* synthetic */ OVRLibrary this$0;
        public final /* synthetic */ CountDownLatch val$countDownLatch;
        public final /* synthetic */ InstallerResult[] val$referenceResult;
    }

    /* renamed from: com.oculus.libraryapi.OVRLibrary$4  reason: invalid class name */
    public class AnonymousClass4 implements InstallerCallback {
        public final /* synthetic */ OVRLibrary this$0;
        public final /* synthetic */ CountDownLatch val$countDownLatch;
        public final /* synthetic */ InstallerResult[] val$referenceResult;
    }

    /* renamed from: com.oculus.libraryapi.OVRLibrary$5  reason: invalid class name */
    public class AnonymousClass5 implements InstallerCallback {
        public final /* synthetic */ OVRLibrary this$0;
        public final /* synthetic */ CountDownLatch val$countDownLatch;
        public final /* synthetic */ InstallerResult[] val$referenceResult;
    }

    /* renamed from: com.oculus.libraryapi.OVRLibrary$6  reason: invalid class name */
    public class AnonymousClass6 implements InstallerCallback {
        public final /* synthetic */ OVRLibrary this$0;
        public final /* synthetic */ CountDownLatch val$countDownLatch;
        public final /* synthetic */ InstallerResult[] val$referenceResult;
    }

    /* renamed from: com.oculus.libraryapi.OVRLibrary$7  reason: invalid class name */
    public class AnonymousClass7 implements InstallerCallback {
        public final /* synthetic */ OVRLibrary this$0;
        public final /* synthetic */ CountDownLatch val$countDownLatch;
        public final /* synthetic */ InstallerResult[] val$referenceResult;
    }

    /* renamed from: com.oculus.libraryapi.OVRLibrary$8  reason: invalid class name */
    public class AnonymousClass8 implements InstallerCallback {
        public final /* synthetic */ OVRLibrary this$0;
        public final /* synthetic */ CountDownLatch val$countDownLatch;
        public final /* synthetic */ InstallerResult[] val$referenceResult;
    }

    public interface DownloadListener {
        void A5w(InstallerResult installerResult);
    }

    public interface InstallListener {
        void A6E(InstallerResult installerResult);
    }

    public static class LibraryContentObserver extends ContentObserver {
        public final Handler handler;
        public final OnChangeListener listener;
        public final Uri uri;
    }

    public interface OnChangeListener {
    }

    public final App A02(String str) {
        if (str == null) {
            Log.w(TAG, "getAppById was called with a null id");
        } else {
            App[] A08 = A08();
            for (App app : A08) {
                if (str.equals(app.id)) {
                    return app;
                }
            }
        }
        return null;
    }

    public static class DownloadContentObserver extends ContentObserver {
        public final Handler handler;
        public final DownloadListener listener;
        public final Uri uri;

        public final void onChange(boolean z, Uri uri2) {
            int i;
            long j;
            InstallerResultError fromInt;
            String A02 = OCMSLibraryContract.A02(uri2, 1);
            try {
                i = Integer.parseInt(OCMSLibraryContract.A02(uri2, 2));
            } catch (NumberFormatException unused) {
                i = -1;
            }
            try {
                j = Long.parseLong(OCMSLibraryContract.A02(uri2, 3));
            } catch (NumberFormatException unused2) {
                j = -1;
            }
            if (i == -1 || j == -1 || TextUtils.isEmpty(A02)) {
                Log.w(OVRLibrary.TAG, AnonymousClass006.A05("unknown content uri observed: ", uri2.toString()));
                return;
            }
            if (i == -2) {
                fromInt = null;
            } else {
                fromInt = InstallerResultError.fromInt(i);
            }
            this.listener.A5w(new InstallerResult(A02, j, fromInt));
        }

        public DownloadContentObserver(Uri uri2, DownloadListener downloadListener, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = downloadListener;
            this.handler = handler2;
        }
    }

    public static class InstallContentObserver extends ContentObserver {
        public final Handler handler;
        public final InstallListener listener;
        public final Uri uri;

        public final void onChange(boolean z, Uri uri2) {
            int i;
            long j;
            InstallerResultError fromInt;
            String A02 = OCMSLibraryContract.A02(uri2, 1);
            try {
                i = Integer.parseInt(OCMSLibraryContract.A02(uri2, 2));
            } catch (NumberFormatException unused) {
                i = -1;
            }
            try {
                j = Long.parseLong(OCMSLibraryContract.A02(uri2, 3));
            } catch (NumberFormatException unused2) {
                j = -1;
            }
            if (i == -1 || j == -1 || TextUtils.isEmpty(A02)) {
                Log.w(OVRLibrary.TAG, AnonymousClass006.A05("unknown content uri observed: ", uri2.toString()));
                return;
            }
            if (i == -2) {
                fromInt = null;
            } else {
                fromInt = InstallerResultError.fromInt(i);
            }
            this.listener.A6E(new InstallerResult(A02, j, fromInt));
        }

        public InstallContentObserver(Uri uri2, InstallListener installListener, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = installListener;
            this.handler = handler2;
        }
    }

    public static void A00(OVRLibrary oVRLibrary, String str, RequestOrigin requestOrigin, InstallerCallback installerCallback, Bundle bundle) {
        ResultReceiver resultReceiver;
        if (installerCallback != null) {
            ResultReceiver resultReceiver2 = new InstallerCallbackReceiver(installerCallback).mReceiver;
            if (resultReceiver2 == null) {
                resultReceiver = null;
            } else {
                Parcel obtain = Parcel.obtain();
                resultReceiver2.writeToParcel(obtain, 0);
                obtain.setDataPosition(0);
                resultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
                obtain.recycle();
            }
            bundle.putParcelable("result_receiver", resultReceiver);
        }
        bundle.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
        oVRLibrary.mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_INSTALL_ASSET, str, bundle);
    }

    public final void A05(ResultReceiver resultReceiver) {
        ResultReceiver resultReceiver2;
        Bundle bundle = new Bundle();
        if (resultReceiver == null) {
            resultReceiver2 = null;
        } else {
            Parcel obtain = Parcel.obtain();
            resultReceiver.writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
            obtain.recycle();
        }
        bundle.putParcelable("result_receiver", resultReceiver2);
        this.mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_REFETCH, (String) null, bundle);
    }

    public final void A06(App.Editor editor) {
        ContentValues contentValues;
        if (TextUtils.isEmpty(editor.packageName)) {
            Log.e(AppConverter.TAG, "Invalid app editor received, returning a null content values pack");
            contentValues = new ContentValues();
        } else {
            contentValues = new ContentValues();
            contentValues.put(AppConverter.COL_PACKAGE_NAME, editor.packageName);
            if (editor.status.isPresent()) {
                contentValues.put(AppConverter.COL_STATUS, editor.status.get().name());
            }
            if (editor.cloudStorageStatus.isPresent()) {
                contentValues.put(AppConverter.COL_CLOUD_STORAGE_STATUS, editor.cloudStorageStatus.get().name());
            }
            if (editor.downloadedSizeBytes.isPresent()) {
                contentValues.put(AppConverter.COL_DOWNLOADED_SIZE, editor.downloadedSizeBytes.get());
            }
            if (editor.downloadSizeBytes.isPresent()) {
                contentValues.put(AppConverter.COL_INSTALLATION_SIZE, editor.downloadSizeBytes.get());
            }
            if (editor.appScopedUserId.isPresent()) {
                contentValues.put(AppConverter.COL_APP_SCOPED_USER_ID, editor.appScopedUserId.get());
            }
            if (editor.isUnseen.isPresent()) {
                contentValues.put(AppConverter.COL_UNSEEN, Integer.valueOf(editor.isUnseen.get().booleanValue() ? 1 : 0));
            }
            if (editor.recentActivityMs.isPresent()) {
                contentValues.put(AppConverter.COL_RECENT_ACTIVITY, editor.recentActivityMs.get());
            }
            if (editor.totalActivityMs.isPresent()) {
                contentValues.put(AppConverter.COL_TOTAL_ACTIVITY, editor.totalActivityMs.get());
            }
            if (editor.languagePack.isPresent()) {
                contentValues.put(AppConverter.COL_CURRENT_LANGUAGE_PACK, editor.languagePack.get());
            }
            if (editor.trustedBinaryStatus.isPresent()) {
                contentValues.put(AppConverter.COL_TRUSTED_BINARY_STATUS, editor.trustedBinaryStatus.get());
            }
        }
        contentValues.put(AppConverter.CONTENT_VALUE_VERSION, (Integer) 2);
        this.mContext.getContentResolver().update(OCMSLibraryContract.A01(editor.packageName), contentValues, null, null);
    }

    public final void A07(InstallListener installListener) {
        Uri build = new Uri.Builder().scheme(LoggingKeys.REFERRER_CONTENT).authority(OCMSLibraryContract.AUTHORITY).path("install").build();
        synchronized (this) {
            ArrayList<InstallContentObserver> arrayList = this.mInstallListeners.get(installListener);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.mInstallListeners.put(installListener, arrayList);
            }
            Iterator<InstallContentObserver> it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().uri.equals(build)) {
                        break;
                    }
                } else {
                    InstallContentObserver installContentObserver = new InstallContentObserver(build, installListener, new Handler());
                    this.mContext.getContentResolver().registerContentObserver(build, true, installContentObserver);
                    arrayList.add(installContentObserver);
                    break;
                }
            }
        }
    }

    public final App[] A08() {
        Cursor query = this.mContext.getContentResolver().query(new Uri.Builder().scheme(LoggingKeys.REFERRER_CONTENT).authority(OCMSLibraryContract.AUTHORITY).path("apps").build(), null, null, null, null);
        try {
            return this.mAppConverter.A00(query);
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public OVRLibrary(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mAppConverter = new AppConverter(applicationContext.getPackageManager());
        this.mAssetConverter = new AssetConverter();
    }

    public final App A01(String str) {
        Uri A01 = OCMSLibraryContract.A01(str);
        Cursor query = this.mContext.getContentResolver().query(A01, null, null, null, null);
        if (query == null) {
            Log.w(TAG, AnonymousClass006.A05("null cursor received for query ", A01.toString()));
            return null;
        }
        try {
            App[] A00 = this.mAppConverter.A00(query);
            if (A00.length != 1) {
                Log.w(TAG, "unexpected number of results in cursor");
                return null;
            }
            App app = A00[0];
            query.close();
            return app;
        } finally {
            query.close();
        }
    }

    public final AssetInfo A03(String str, long j) {
        if (!TextUtils.isEmpty(str)) {
            Cursor query = this.mContext.getContentResolver().query(new Uri.Builder().scheme(LoggingKeys.REFERRER_CONTENT).authority(OCMSLibraryContract.AUTHORITY).path("assets").appendPath(str).appendPath("id").appendPath(Long.toString(j)).build(), null, null, null, null);
            try {
                AssetInfo[] A00 = this.mAssetConverter.A00(query);
                if (A00 != null && A00.length == 1) {
                    return A00[0];
                }
                Log.w(TAG, "unexpected number of results in cursor");
                return null;
            } finally {
                if (query != null) {
                    query.close();
                    throw th;
                }
            }
        } else {
            throw new IllegalArgumentException("packageName cannot be null/empty");
        }
    }

    public final AssetInfo A04(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "packageName cannot be null/empty";
        } else if (!TextUtils.isEmpty(str2)) {
            Cursor query = this.mContext.getContentResolver().query(new Uri.Builder().scheme(LoggingKeys.REFERRER_CONTENT).authority(OCMSLibraryContract.AUTHORITY).path("assets").appendPath(str).appendPath(OCMSLibraryContract.ASSETS_PATH_BY_FILENAME).appendPath(str2).build(), null, null, null, null);
            try {
                AssetInfo[] A00 = this.mAssetConverter.A00(query);
                if (A00 != null && A00.length == 1) {
                    return A00[0];
                }
                Log.w(TAG, "unexpected number of results in cursor");
                return null;
            } finally {
                if (query != null) {
                    query.close();
                    throw th;
                }
            }
        } else {
            str3 = "filename cannot be null/empty";
        }
        throw new IllegalArgumentException(str3);
    }

    public final AssetInfo[] A09(String str) {
        if (!TextUtils.isEmpty(str)) {
            Cursor query = this.mContext.getContentResolver().query(new Uri.Builder().scheme(LoggingKeys.REFERRER_CONTENT).authority(OCMSLibraryContract.AUTHORITY).path("assets").appendPath(str).build(), null, null, null, null);
            try {
                return this.mAssetConverter.A00(query);
            } finally {
                if (query != null) {
                    query.close();
                    throw th;
                }
            }
        } else {
            throw new IllegalArgumentException("packageName cannot be null/empty");
        }
    }
}
