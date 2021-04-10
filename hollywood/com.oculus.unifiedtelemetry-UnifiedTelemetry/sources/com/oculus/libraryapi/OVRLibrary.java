package com.oculus.libraryapi;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.library.model.InstallerCallback;
import com.oculus.library.utils.app.AppConverter;
import com.oculus.library.utils.app.AssetConverter;
import java.util.ArrayList;
import java.util.HashMap;
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
    }

    public interface OnChangeListener {
    }

    public OVRLibrary(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mAppConverter = new AppConverter(applicationContext.getPackageManager());
        this.mAssetConverter = new AssetConverter();
    }
}
