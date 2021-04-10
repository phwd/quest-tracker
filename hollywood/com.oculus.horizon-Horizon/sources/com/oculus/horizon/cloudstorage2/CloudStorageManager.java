package com.oculus.horizon.cloudstorage2;

import X.AbstractC06640p5;
import X.AnonymousClass04J;
import X.AnonymousClass0D4;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C08780ya;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Binder;
import android.os.Environment;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.INeedInit;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import com.oculus.library.model.App;
import com.oculus.library.model.CloudStorageStatus;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.processtokentracker.ProcessTokenTracker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_UploadSyncTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_DownloadSyncTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_ResolveConflictTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_processtokentracker_ProcessTokenTracker_ULSEP_BINDING_ID"})
@ApplicationScoped
@SuppressLint({"InstanceMethodCanBeStatic"})
public class CloudStorageManager implements INeedInit, ProcessTokenTracker.ProcessListener {
    public static final String FILE_NAME_BLACKLIST = "(CON|PRN|AUX|NUL|COM1|COM2|COM3|COM4|COM5|COM6|COM7|COM8|COM9|COM0|LPT1|LPT2|LPT3|LPT4|LPT5|LPT6|LPT7|LPT8|LPT9|LPT0)";
    public static final String FILE_NAME_BLACKLIST_CHAR_REGEX = ".*[\\\\/:\\*\\?\"<>\\|%].*";
    public static final String FILE_NAME_BLACKLIST_FORMAT_REGEX = ".*[ \\.]$";
    public static final String FILE_NAME_BLACKLIST_PREFIX_REGEX = "(CON|PRN|AUX|NUL|COM1|COM2|COM3|COM4|COM5|COM6|COM7|COM8|COM9|COM0|LPT1|LPT2|LPT3|LPT4|LPT5|LPT6|LPT7|LPT8|LPT9|LPT0)|(CON|PRN|AUX|NUL|COM1|COM2|COM3|COM4|COM5|COM6|COM7|COM8|COM9|COM0|LPT1|LPT2|LPT3|LPT4|LPT5|LPT6|LPT7|LPT8|LPT9|LPT0)\\..*";
    public static final Class<?> TAG = CloudStorageManager.class;
    public static volatile CloudStorageManager _UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageManager_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final CloudStorageLogger mCloudStorageLogger;
    public final C08780ya mGson = new C08780ya();
    @Inject
    @Eager
    public final OVRLibrary mLibrary;
    @Inject
    @Eager
    public final ProcessTokenTracker mProcessTokenTracker;

    /* renamed from: com.oculus.horizon.cloudstorage2.CloudStorageManager$2  reason: invalid class name */
    public class AnonymousClass2 implements AnonymousClass0D4<Void, Object> {
        public final /* synthetic */ CloudStorageManager this$0;
    }

    /* renamed from: com.oculus.horizon.cloudstorage2.CloudStorageManager$3  reason: invalid class name */
    public class AnonymousClass3 implements AnonymousClass0D4<Void, Object> {
        public final /* synthetic */ CloudStorageManager this$0;
    }

    /* renamed from: com.oculus.horizon.cloudstorage2.CloudStorageManager$4  reason: invalid class name */
    public class AnonymousClass4 implements AnonymousClass0D4<Void, Object> {
        public final /* synthetic */ CloudStorageManager this$0;
    }

    public static final String A00(CloudStorageManager cloudStorageManager, String str) throws IOException {
        File externalFilesDir = ((Context) AnonymousClass0J2.A03(0, 294, cloudStorageManager._UL_mInjectionContext)).getExternalFilesDir(null);
        if (externalFilesDir != null) {
            return new File(externalFilesDir, "cloud").getCanonicalPath().replace(((Context) AnonymousClass0J2.A03(0, 294, cloudStorageManager._UL_mInjectionContext)).getPackageName(), str);
        }
        throw new IOException("External Storage not mounted");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0058, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.oculus.horizon.cloudstorage2.model.CloudStorage2Metadata A01(com.oculus.library.model.App r8) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 115
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.CloudStorageManager.A01(com.oculus.library.model.App):com.oculus.horizon.cloudstorage2.model.CloudStorage2Metadata");
    }

    public final String A02(App app) throws IOException {
        return new File(A00(this, app.packageName), "data").getCanonicalPath();
    }

    public final ArrayList<App> A04(App app) {
        App[] A08 = this.mLibrary.A08();
        ArrayList<App> arrayList = new ArrayList<>();
        for (App app2 : A08) {
            if (app.applicationGroupingId.equals(app2.applicationGroupingId)) {
                arrayList.add(app2);
            }
        }
        return arrayList;
    }

    public final void A06(String str, CloudStorageStatus cloudStorageStatus) {
        App A02 = this.mLibrary.A02(str);
        if (A02 != null) {
            OVRLibrary oVRLibrary = this.mLibrary;
            App.Editor editor = new App.Editor(A02.packageName);
            if (cloudStorageStatus != null) {
                editor.cloudStorageStatus = Optional.of(cloudStorageStatus);
                oVRLibrary.A06(editor);
                return;
            }
            throw new IllegalArgumentException("cloud storage status must not be null");
        }
    }

    public final boolean A07() {
        if (AnonymousClass04J.A01((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext), OVRMediaServiceManager.EXTERNAL_STORAGE_PERMISSION) == 0) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.processtokentracker.ProcessTokenTracker.ProcessListener
    public final void onDeath(String str, int i) {
        Map<Integer, ProcessTokenTracker.ProcessDeathRecipient> map = this.mProcessTokenTracker.mTokenRegistry.get(str);
        if (map != null) {
            map.size();
        }
        long clearCallingIdentity = Binder.clearCallingIdentity();
        App A01 = this.mLibrary.A01(str);
        Binder.restoreCallingIdentity(clearCallingIdentity);
        if (A01 != null && A01.cloudStorageStatus == CloudStorageStatus.UPLOAD_SYNC_REQUIRED) {
            CloudStorageBackgroundService.A00((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext), A01.id);
            this.mCloudStorageLogger.A00(A01.id, CloudStorageLogger.SERVICE_OPERATION_SCHEDULE_UPLOAD);
        }
    }

    @Inject
    public CloudStorageManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mLibrary = OVRLibraryModule.A00(r3);
        this.mCloudStorageLogger = (CloudStorageLogger) AnonymousClass117.A00(527, r3);
        this.mProcessTokenTracker = (ProcessTokenTracker) AnonymousClass117.A00(389, r3);
    }

    public final String A03(App app, String[] strArr) throws IOException {
        Class<CloudStorageManager> cls;
        Object[] objArr;
        String str;
        String A02 = A02(app);
        HashSet hashSet = new HashSet();
        File file = new File(A02);
        if (file.isDirectory() && file.exists()) {
            ArrayDeque arrayDeque = new ArrayDeque();
            arrayDeque.add(file);
            while (!arrayDeque.isEmpty()) {
                File[] listFiles = ((File) arrayDeque.remove()).listFiles();
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        arrayDeque.add(file2);
                    } else {
                        String name = file2.getName();
                        if (name.matches(FILE_NAME_BLACKLIST_CHAR_REGEX)) {
                            cls = CloudStorageManager.class;
                            objArr = new Object[]{name};
                            str = "File was skipped due to blacklisted character: %s";
                        } else if (name.matches(FILE_NAME_BLACKLIST_PREFIX_REGEX)) {
                            cls = CloudStorageManager.class;
                            objArr = new Object[]{name};
                            str = "File was skipped due to blacklisted name: %s";
                        } else if (name.matches(FILE_NAME_BLACKLIST_FORMAT_REGEX)) {
                            cls = CloudStorageManager.class;
                            objArr = new Object[]{name};
                            str = "File was skipped due to blacklisted format: %s";
                        } else {
                            hashSet.add(file2);
                        }
                        AnonymousClass0NO.A06(cls, str, objArr);
                    }
                }
            }
        }
        File[] fileArr = (File[]) hashSet.toArray(new File[hashSet.size()]);
        int length = fileArr.length;
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            String canonicalPath = fileArr[i].getCanonicalPath();
            if (canonicalPath.startsWith(A02)) {
                strArr2[i] = canonicalPath.substring(A02.length());
            } else {
                throw new FileNotFoundException(StringFormatUtil.formatStrLocaleSafe("File is not within root sync directory! [%s, %s]", A02, canonicalPath));
            }
        }
        return PlatformPluginManager.nativeGetCloudStorage2MetadataJsonFromFiles(A02, PlatformPluginManager.nativeGetCloudStorage2MatchingFiles(strArr, strArr2));
    }

    public final void A05(App app) {
        if (A07()) {
            try {
                File file = new File(StringFormatUtil.formatStrLocaleSafe("%s/Oculus/apps/%s/%s/cloud/", Environment.getExternalStorageDirectory(), app.applicationGroupingId, app.appScopedUserId));
                if (file.exists() && !file.renameTo(new File(A00(this, app.packageName)))) {
                    file.delete();
                }
            } catch (IOException unused) {
            }
        }
    }

    public final boolean A08(App app) throws IllegalStateException {
        String str;
        ArrayList<App> A04 = A04(app);
        Iterator<App> it = A04.iterator();
        while (true) {
            if (it.hasNext()) {
                if (CloudStorageStatus.isSyncing(it.next().cloudStorageStatus)) {
                    str = "There is an existing sync running for an app in this group!";
                    break;
                }
            } else {
                Iterator<App> it2 = A04.iterator();
                while (it2.hasNext()) {
                    App next = it2.next();
                    if (!next.id.equals(app.id)) {
                        ProcessTokenTracker processTokenTracker = this.mProcessTokenTracker;
                        if (processTokenTracker.mTokenRegistry.containsKey(next.packageName)) {
                            str = "App in the group is already running!";
                        }
                    }
                }
                ProcessTokenTracker processTokenTracker2 = this.mProcessTokenTracker;
                return processTokenTracker2.mTokenRegistry.containsKey(app.packageName);
            }
        }
        throw new IllegalStateException(str);
    }

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        OculusThreadExecutor.A00().execute(new Runnable() {
            /* class com.oculus.horizon.cloudstorage2.CloudStorageManager.AnonymousClass1 */

            public final void run() {
                App[] A08 = CloudStorageManager.this.mLibrary.A08();
                for (App app : A08) {
                    CloudStorageStatus cloudStorageStatus = app.cloudStorageStatus;
                    if (CloudStorageStatus.isSyncing(cloudStorageStatus)) {
                        AnonymousClass0NO.A06(CloudStorageManager.class, "[%s] Application status is %s, resetting.", app.packageName, cloudStorageStatus.name());
                        if (cloudStorageStatus == CloudStorageStatus.UPLOAD_SYNCING) {
                            cloudStorageStatus = CloudStorageStatus.UPLOAD_SYNC_REQUIRED;
                        } else {
                            cloudStorageStatus = CloudStorageStatus.ENABLED;
                        }
                        OVRLibrary oVRLibrary = CloudStorageManager.this.mLibrary;
                        App.Editor editor = new App.Editor(app.packageName);
                        if (cloudStorageStatus != null) {
                            editor.cloudStorageStatus = Optional.of(cloudStorageStatus);
                            oVRLibrary.A06(editor);
                        } else {
                            throw new IllegalArgumentException("cloud storage status must not be null");
                        }
                    }
                    if (cloudStorageStatus == CloudStorageStatus.UPLOAD_SYNC_REQUIRED) {
                        CloudStorageBackgroundService.A00((Context) AnonymousClass0J2.A03(0, 294, CloudStorageManager.this._UL_mInjectionContext), app.id);
                    }
                }
            }
        });
    }

    @Override // com.oculus.processtokentracker.ProcessTokenTracker.ProcessListener
    public final void onRegister(String str, int i) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        App A01 = this.mLibrary.A01(str);
        if (A01 != null) {
            CloudStorageStatus cloudStorageStatus = A01.cloudStorageStatus;
            if (cloudStorageStatus == CloudStorageStatus.ENABLED) {
                OVRLibrary oVRLibrary = this.mLibrary;
                App.Editor editor = new App.Editor(A01.packageName);
                editor.cloudStorageStatus = Optional.of(CloudStorageStatus.UPLOAD_SYNC_REQUIRED);
                oVRLibrary.A06(editor);
            } else if (CloudStorageStatus.isSyncing(cloudStorageStatus)) {
                AnonymousClass0NO.A06(CloudStorageManager.class, "Eek, %s was started during a cloud storage sync!", str);
            }
        }
        Binder.restoreCallingIdentity(clearCallingIdentity);
    }
}
