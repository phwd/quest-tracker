package com.oculus.libraryapi;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.library.model.InstallerCallback;
import com.oculus.library.utils.app.AppConverter;
import com.oculus.library.utils.app.AssetConverter;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static class DownloadContentObserver extends ContentObserver {
        public final Handler handler;
        public final DownloadListener listener;
        public final Uri uri;
    }

    public interface DownloadListener {
    }

    public static class InstallContentObserver extends ContentObserver {
        public final Handler handler;
        public final InstallListener listener;
        public final Uri uri;
    }

    public interface InstallListener {
    }

    public static class LibraryContentObserver extends ContentObserver {
        public final Handler handler;
        public final OnChangeListener listener;
        public final Uri uri;

        public final void onChange(boolean z, Uri uri2) {
            String str;
            OnChangeListener onChangeListener = this.listener;
            if (uri2 != null) {
                List<String> pathSegments = uri2.getPathSegments();
                if (pathSegments == null || pathSegments.size() <= 1) {
                    str = null;
                } else {
                    str = pathSegments.get(1);
                }
                onChangeListener.A5w(str);
                return;
            }
            throw new IllegalArgumentException("invalid args");
        }

        public LibraryContentObserver(Uri uri2, OnChangeListener onChangeListener, Handler handler2) {
            super(handler2);
            this.uri = uri2;
            this.listener = onChangeListener;
            this.handler = handler2;
        }
    }

    public interface OnChangeListener {
        void A5w(String str);
    }

    public final void A01() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, A00(null));
        this.mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_REFETCH, (String) null, bundle);
    }

    public static ResultReceiver A00(ResultReceiver resultReceiver) {
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

    public OVRLibrary(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mAppConverter = new AppConverter(applicationContext.getPackageManager());
        this.mAssetConverter = new AssetConverter();
    }
}
