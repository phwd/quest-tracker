package com.oculus.horizon.cloudstorage2.task;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0D3;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0JA;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.AnonymousClass1Q9;
import X.AnonymousClass1ZY;
import X.AnonymousClass1kJ;
import X.AnonymousClass1kK;
import X.C08780ya;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.database.sqlite.SqlExpression;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.horizon.api.ApiRequestManager;
import com.oculus.horizon.api.cloudstorage2.RemoveUserCloudFile;
import com.oculus.horizon.api.cloudstorage2.UploadAndRemoveUserCloudFilesRequest;
import com.oculus.horizon.api.cloudstorage2.UploadAndRemoveUserCloudFilesResponse;
import com.oculus.horizon.api.cloudstorage2.UploadUserCloudFile;
import com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse;
import com.oculus.horizon.cloudstorage2.CloudStorageManager;
import com.oculus.horizon.cloudstorage2.TaskProgressReporter;
import com.oculus.horizon.cloudstorage2.TaskProgressReporterProvider;
import com.oculus.horizon.cloudstorage2.callable.UploadConflictDetector;
import com.oculus.horizon.cloudstorage2.callable.UploadConflictDetectorProvider;
import com.oculus.horizon.cloudstorage2.callable.UploadVerifier;
import com.oculus.horizon.cloudstorage2.callable.UploadVerifierProvider;
import com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFile;
import com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2ActionMap;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2FileAction;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2FileMetadata;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2Metadata;
import com.oculus.horizon.cloudstorage2.task.FileUploadTask;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.library.model.App;
import com.oculus.library.model.CloudStorageStatus;
import com.oculus.libraryapi.OVRLibrary;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_DownloadMetadataTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_DownloadWildcardsTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_UploadMutationTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_callable_UploadConflictDetectorProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_callable_UploadVerifierProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_callable_WriteMetadataToFileProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_FileUploadTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_processtokentracker_ProcessTokenTracker_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_TaskProgressReporterProvider_ULSEP_BINDING_ID"})
public class UploadSyncTask implements MasterTask {
    public static final Class<? extends MasterTask> TAG = UploadSyncTask.class;
    public AnonymousClass0QC _UL_mInjectionContext;
    public final String mAppId;
    public final C08780ya mGson = new C08780ya();
    public final HashMap<String, CloudStorageStatus> mInitialStatuses = new HashMap<>();
    public final TaskProgressReporter<Step> mReporter;
    public boolean mShouldRetryOnFailure = true;
    @Inject
    @Eager
    public final TaskProgressReporterProvider mTaskProgressReporterProvider;

    public enum Step {
        INIT,
        PREFLIGHT_CHECK,
        DOWNLOAD_META,
        NEW_CONFLICT_CHECK,
        DOWNLOAD_PATTERNS,
        READ_LOCAL_STATE,
        BUILD_ACTION_MAP,
        UPLOAD_FILES,
        SEND_MUTATION,
        DOWNLOAD_META_AGAIN,
        VALIDATE_UPLOAD,
        WRITE_LOCAL_META,
        DONE
    }

    public static class UploadSyncException extends Exception {
        public UploadSyncException(Step step, Throwable th) {
            super(StringFormatUtil.formatStrLocaleSafe("Upload sync failed during step %s: %s", step, th.getMessage()), th);
        }
    }

    public static void A00(UploadSyncTask uploadSyncTask, boolean z) {
        CloudStorageManager cloudStorageManager;
        CloudStorageStatus cloudStorageStatus;
        for (String str : uploadSyncTask.mInitialStatuses.keySet()) {
            if (!str.equals(uploadSyncTask.mAppId)) {
                cloudStorageManager = (CloudStorageManager) AnonymousClass0J2.A03(0, 73, uploadSyncTask._UL_mInjectionContext);
                CloudStorageStatus cloudStorageStatus2 = uploadSyncTask.mInitialStatuses.get(str);
                if (cloudStorageStatus2 != null) {
                    cloudStorageStatus = cloudStorageStatus2;
                } else {
                    throw new AssertionError("value set during PREFLIGHT_CHECK");
                }
            } else if (!z || !uploadSyncTask.mShouldRetryOnFailure) {
                cloudStorageManager = (CloudStorageManager) AnonymousClass0J2.A03(0, 73, uploadSyncTask._UL_mInjectionContext);
                cloudStorageStatus = CloudStorageStatus.ENABLED;
            } else {
                cloudStorageManager = (CloudStorageManager) AnonymousClass0J2.A03(0, 73, uploadSyncTask._UL_mInjectionContext);
                cloudStorageStatus = CloudStorageStatus.UPLOAD_SYNC_REQUIRED;
            }
            cloudStorageManager.A06(str, cloudStorageStatus);
        }
        uploadSyncTask.mInitialStatuses.clear();
    }

    @VisibleForTesting
    public static final void A01(Set<String> set, @Nullable String[] strArr) throws IllegalStateException {
        if (strArr != null) {
            for (String str : strArr) {
                if (!set.contains(str)) {
                    set.add(str);
                } else {
                    throw new IllegalStateException(StringFormatUtil.formatStrLocaleSafe("Duplicate file in upload list: %s", str));
                }
            }
        }
    }

    public final AnonymousClass0DC<Void> A02() {
        final App A02 = ((OVRLibrary) AnonymousClass0J2.A03(1, 569, this._UL_mInjectionContext)).A02(this.mAppId);
        final DownloadMetadataTaskProvider downloadMetadataTaskProvider = (DownloadMetadataTaskProvider) AnonymousClass0J2.A04(31, this._UL_mInjectionContext);
        final DownloadWildcardsTaskProvider downloadWildcardsTaskProvider = (DownloadWildcardsTaskProvider) AnonymousClass0J2.A04(270, this._UL_mInjectionContext);
        final UploadMutationTaskProvider uploadMutationTaskProvider = (UploadMutationTaskProvider) AnonymousClass0J2.A04(SqlExpression.SQLITE_MAXIMUM_PARAMETER_COUNT, this._UL_mInjectionContext);
        final UploadConflictDetectorProvider uploadConflictDetectorProvider = (UploadConflictDetectorProvider) AnonymousClass0J2.A04(575, this._UL_mInjectionContext);
        final UploadVerifierProvider uploadVerifierProvider = (UploadVerifierProvider) AnonymousClass0J2.A04(74, this._UL_mInjectionContext);
        final WriteMetadataToFileProvider writeMetadataToFileProvider = (WriteMetadataToFileProvider) AnonymousClass0J2.A04(444, this._UL_mInjectionContext);
        final AnonymousClass0D3 r16 = new AnonymousClass0D3();
        final AnonymousClass0D3 r20 = new AnonymousClass0D3();
        final AnonymousClass0D3 r15 = new AnonymousClass0D3();
        final AnonymousClass0D3 r14 = new AnonymousClass0D3();
        final AnonymousClass0D3 r23 = new AnonymousClass0D3();
        final AnonymousClass0D3 r5 = new AnonymousClass0D3();
        final AnonymousClass0D3 r4 = new AnonymousClass0D3();
        final AnonymousClass0DD r3 = new AnonymousClass0DD();
        AnonymousClass0DC.A01(AnonymousClass0DC.A07(new Callable<Void>() {
            /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass12 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final Void call() throws Exception {
                String str;
                UploadSyncTask.this.mReporter.A02(Step.PREFLIGHT_CHECK);
                App app = A02;
                if (app != null) {
                    UploadSyncTask.this.mReporter.A01(app);
                    UploadSyncTask uploadSyncTask = UploadSyncTask.this;
                    App app2 = A02;
                    CloudStorageStatus cloudStorageStatus = app2.cloudStorageStatus;
                    new Object[1][0] = cloudStorageStatus.name();
                    if (!CloudStorageStatus.isEnabled(cloudStorageStatus)) {
                        uploadSyncTask.mShouldRetryOnFailure = false;
                        str = "Cloud syncing is not enabled for this app!";
                    } else if (((CloudStorageManager) AnonymousClass0J2.A03(0, 73, uploadSyncTask._UL_mInjectionContext)).A08(app2)) {
                        str = "App process is currently running!";
                    } else if (((CloudStorageManager) AnonymousClass0J2.A03(0, 73, UploadSyncTask.this._UL_mInjectionContext)).A07()) {
                        Iterator<App> it = ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, UploadSyncTask.this._UL_mInjectionContext)).A04(A02).iterator();
                        while (it.hasNext()) {
                            App next = it.next();
                            CloudStorageStatus cloudStorageStatus2 = next.cloudStorageStatus;
                            if (CloudStorageStatus.isEnabled(cloudStorageStatus2)) {
                                UploadSyncTask.this.mInitialStatuses.put(next.id, cloudStorageStatus2);
                                ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, UploadSyncTask.this._UL_mInjectionContext)).A06(next.id, CloudStorageStatus.UPLOAD_SYNCING);
                            }
                        }
                        ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, UploadSyncTask.this._UL_mInjectionContext)).A05(A02);
                        return null;
                    } else {
                        UploadSyncTask.this.mShouldRetryOnFailure = false;
                        str = "External storage permission needs to be granted";
                    }
                    throw new IllegalStateException(str);
                }
                UploadSyncTask.this.mShouldRetryOnFailure = false;
                throw new IllegalArgumentException("App was not found in library!");
            }
        }, OculusThreadExecutor.A00(), null).A0D(new AnonymousClass0D4<Void, AnonymousClass0DC<UserCloudFilesResponse>>() {
            /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass11 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final AnonymousClass0DC<UserCloudFilesResponse> then(AnonymousClass0DC<Void> r5) throws Exception {
                UploadSyncTask.this.mReporter.A02(Step.DOWNLOAD_META);
                return new DownloadMetadataTask(downloadMetadataTaskProvider, A02.applicationGroupingId, UploadSyncTask.this.mReporter).A00();
            }
        }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<UserCloudFilesResponse, CloudStorage2Metadata>() {
            /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass10 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final CloudStorage2Metadata then(AnonymousClass0DC<UserCloudFilesResponse> r6) throws Exception {
                UploadSyncTask.this.mReporter.A02(Step.NEW_CONFLICT_CHECK);
                try {
                    return new UploadConflictDetector(uploadConflictDetectorProvider, A02, r6.A0G(), UploadSyncTask.this.mReporter).call();
                } catch (IllegalStateException e) {
                    UploadSyncTask.this.mShouldRetryOnFailure = false;
                    throw e;
                }
            }
        }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<CloudStorage2Metadata, AnonymousClass0DC<String[]>>() {
            /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass9 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final AnonymousClass0DC<String[]> then(AnonymousClass0DC<CloudStorage2Metadata> r5) throws Exception {
                UploadSyncTask.this.mReporter.A02(Step.DOWNLOAD_PATTERNS);
                r16.A00 = (T) UploadSyncTask.this.mGson.A06(r5.A0G());
                r20.A00 = (T) r5.A0G();
                return new DownloadWildcardsTask(downloadWildcardsTaskProvider, A02.applicationGroupingId, UploadSyncTask.this.mReporter).A00();
            }
        }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<String[], Void>() {
            /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass8 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final Void then(AnonymousClass0DC<String[]> r6) throws Exception {
                UploadSyncTask.this.mReporter.A02(Step.READ_LOCAL_STATE);
                r5.A00 = (T) r6.A0G();
                T t = (T) ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, UploadSyncTask.this._UL_mInjectionContext)).A03(A02, (String[]) r5.A00);
                r15.A00 = t;
                r14.A00 = (T) UploadSyncTask.this.mGson.A05(t, CloudStorage2Metadata.class);
                r23.A00 = (T) ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, UploadSyncTask.this._UL_mInjectionContext)).A01(A02);
                return null;
            }
        }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<Void, Void>() {
            /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass7 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final Void then(AnonymousClass0DC<Void> r4) throws Exception {
                UploadSyncTask.this.mReporter.A02(Step.BUILD_ACTION_MAP);
                T t = (T) UploadSyncTask.this.mGson.A05(PlatformPluginManager.nativeGetCloudStorage2SyncFileActions(r15.A00, r16.A00), CloudStorage2ActionMap.class);
                AnonymousClass006.A05("Action map: ", UploadSyncTask.this.mGson.A06(t));
                r4.A00 = t;
                return null;
            }
        }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<Void, AnonymousClass0DC<List<FileUploadTask.RemoteFile>>>() {
            /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass6 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final AnonymousClass0DC<List<FileUploadTask.RemoteFile>> then(AnonymousClass0DC<Void> r25) throws Exception {
                int i;
                int i2;
                AnonymousClass0DC<TResult> r0;
                UploadSyncTask.this.mReporter.A02(Step.UPLOAD_FILES);
                T t = r4.A00;
                String[] strArr = (String[]) t.get(CloudStorage2FileAction.REMOVE);
                int A00 = t.A00();
                if (strArr != null) {
                    i = strArr.length;
                } else {
                    i = 0;
                }
                if (A00 + i == 0) {
                    return AnonymousClass0DC.A06;
                }
                Object[] objArr = new Object[1];
                T t2 = r4.A00;
                String[] strArr2 = (String[]) t2.get(CloudStorage2FileAction.REMOVE);
                int A002 = t2.A00();
                if (strArr2 != null) {
                    i2 = strArr2.length;
                } else {
                    i2 = 0;
                }
                objArr[0] = Integer.valueOf(A002 + i2);
                UploadSyncTask uploadSyncTask = UploadSyncTask.this;
                App app = A02;
                T t3 = r4.A00;
                TaskProgressReporter<Step> taskProgressReporter = uploadSyncTask.mReporter;
                AnonymousClass0JA r5 = (AnonymousClass0JA) AnonymousClass0J2.A04(396, uploadSyncTask._UL_mInjectionContext);
                Map<String, CloudStorage2FileMetadata> A01 = r14.A00.A01();
                HashSet hashSet = new HashSet();
                UploadSyncTask.A01(hashSet, (String[]) t3.get(CloudStorage2FileAction.ADD));
                UploadSyncTask.A01(hashSet, (String[]) t3.get(CloudStorage2FileAction.UPDATE));
                String A02 = ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, uploadSyncTask._UL_mInjectionContext)).A02(app);
                ArrayList arrayList = new ArrayList();
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    File file = new File(A02, str);
                    CloudStorage2FileMetadata cloudStorage2FileMetadata = A01.get(str);
                    if (cloudStorage2FileMetadata != null) {
                        FileUploadTask fileUploadTask = new FileUploadTask(r5, file, cloudStorage2FileMetadata.sha1, taskProgressReporter);
                        if (fileUploadTask.mFile.length() == 0) {
                            r0 = AnonymousClass0DC.A04(new FileUploadTask.RemoteFile(fileUploadTask.mFile, null));
                        } else {
                            AnonymousClass0DD r9 = new AnonymousClass0DD();
                            AnonymousClass1Q9 r14 = new AnonymousClass1Q9(fileUploadTask.mFile, fileUploadTask.mSha1);
                            AnonymousClass1kJ r13 = new AnonymousClass1kJ(new AnonymousClass1kK(AnonymousClass1ZY.OCULUS_CLOUD_STORAGE));
                            FileUploadTask.AnonymousClass1 r1 = 
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00f2: CONSTRUCTOR  (r1v16 'r1' com.oculus.horizon.cloudstorage2.task.FileUploadTask$1) = (r11v2 'fileUploadTask' com.oculus.horizon.cloudstorage2.task.FileUploadTask), (r9v4 'r9' X.0DD) call: com.oculus.horizon.cloudstorage2.task.FileUploadTask.1.<init>(com.oculus.horizon.cloudstorage2.task.FileUploadTask, X.0DD):void type: CONSTRUCTOR in method: com.oculus.horizon.cloudstorage2.task.UploadSyncTask.6.then(X.0DC<java.lang.Void>):X.0DC<java.util.List<com.oculus.horizon.cloudstorage2.task.FileUploadTask$RemoteFile>>, file: classes2.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:157)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                                	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:239)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:67)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.horizon.cloudstorage2.task.FileUploadTask, state: GENERATED_AND_UNLOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                                	... 33 more
                                */
                            /*
                            // Method dump skipped, instructions count: 390
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass6.then(X.0DC):java.lang.Object");
                        }
                    }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<List<FileUploadTask.RemoteFile>, AnonymousClass0DC<UploadAndRemoveUserCloudFilesResponse>>() {
                        /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass5 */

                        /* Return type fixed from 'java.lang.Object' to match base method */
                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                        @Override // X.AnonymousClass0D4
                        public final AnonymousClass0DC<UploadAndRemoveUserCloudFilesResponse> then(AnonymousClass0DC<List<FileUploadTask.RemoteFile>> r15) throws Exception {
                            String str;
                            UploadSyncTask.this.mReporter.A02(Step.SEND_MUTATION);
                            UploadMutationTaskProvider uploadMutationTaskProvider = uploadMutationTaskProvider;
                            App app = A02;
                            String str2 = app.applicationGroupingId;
                            UploadSyncTask uploadSyncTask = UploadSyncTask.this;
                            ArrayList arrayList = new ArrayList();
                            Map<String, CloudStorage2FileMetadata> A01 = r23.A00.A01();
                            String A02 = ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, uploadSyncTask._UL_mInjectionContext)).A02(app);
                            for (FileUploadTask.RemoteFile remoteFile : r15.A0G()) {
                                String canonicalPath = remoteFile.file.getCanonicalPath();
                                if (canonicalPath.startsWith(A02)) {
                                    String substring = canonicalPath.substring(A02.length());
                                    CloudStorage2FileMetadata cloudStorage2FileMetadata = A01.get(substring);
                                    if (cloudStorage2FileMetadata != null) {
                                        str = cloudStorage2FileMetadata.sha1;
                                    } else {
                                        str = null;
                                    }
                                    arrayList.add(new UploadUserCloudFile(substring, remoteFile.uploadedHandle, str));
                                } else {
                                    throw new FileNotFoundException(StringFormatUtil.formatStrLocaleSafe("File is not within root sync directory! [%s, %s]", A02, canonicalPath));
                                }
                            }
                            UploadUserCloudFile[] uploadUserCloudFileArr = (UploadUserCloudFile[]) arrayList.toArray(new UploadUserCloudFile[arrayList.size()]);
                            ArrayList arrayList2 = new ArrayList();
                            String[] strArr = (String[]) r4.A00.get(CloudStorage2FileAction.REMOVE);
                            Map<String, CloudStorage2FileMetadata> A012 = r23.A00.A01();
                            if (strArr != null) {
                                for (String str3 : strArr) {
                                    CloudStorage2FileMetadata cloudStorage2FileMetadata2 = A012.get(str3);
                                    if (cloudStorage2FileMetadata2 != null) {
                                        arrayList2.add(new RemoveUserCloudFile(cloudStorage2FileMetadata2.relativePath, cloudStorage2FileMetadata2.sha1));
                                    } else {
                                        throw new IllegalStateException(StringFormatUtil.formatStrLocaleSafe("File to be removed was not found in metadata: %s", str3));
                                    }
                                }
                            }
                            UploadMutationTask uploadMutationTask = new UploadMutationTask(uploadMutationTaskProvider, str2, uploadUserCloudFileArr, (RemoveUserCloudFile[]) arrayList2.toArray(new RemoveUserCloudFile[arrayList2.size()]), UploadSyncTask.this.mReporter);
                            AnonymousClass0DD r5 = new AnonymousClass0DD();
                            ((ApiRequestManager) AnonymousClass0J2.A03(0, 407, uploadMutationTask._UL_mInjectionContext)).post(new UploadAndRemoveUserCloudFilesRequest(uploadMutationTask.mAppGroupingId, uploadMutationTask.mUploads, uploadMutationTask.mRemoves), 
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0105: INVOKE  
                                  (wrap: com.oculus.horizon.api.ApiRequestManager : 0x00f3: CHECK_CAST (r4v2 com.oculus.horizon.api.ApiRequestManager) = (com.oculus.horizon.api.ApiRequestManager) (wrap: java.lang.Object : 0x00ef: INVOKE  (r4v1 java.lang.Object) = 
                                  (0 int)
                                  (407 int)
                                  (wrap: X.0QC : 0x00ec: IGET  (r1v5 X.0QC) = (r8v1 'uploadMutationTask' com.oculus.horizon.cloudstorage2.task.UploadMutationTask) com.oculus.horizon.cloudstorage2.task.UploadMutationTask._UL_mInjectionContext X.0QC)
                                 type: STATIC call: X.0J2.A03(int, int, X.0QC):java.lang.Object))
                                  (wrap: com.oculus.horizon.api.cloudstorage2.UploadAndRemoveUserCloudFilesRequest : 0x00fd: CONSTRUCTOR  (r1v6 com.oculus.horizon.api.cloudstorage2.UploadAndRemoveUserCloudFilesRequest) = 
                                  (wrap: java.lang.String : 0x00f5: IGET  (r3v2 java.lang.String) = (r8v1 'uploadMutationTask' com.oculus.horizon.cloudstorage2.task.UploadMutationTask) com.oculus.horizon.cloudstorage2.task.UploadMutationTask.mAppGroupingId java.lang.String)
                                  (wrap: com.oculus.horizon.api.cloudstorage2.UploadUserCloudFile[] : 0x00f7: IGET  (r2v4 com.oculus.horizon.api.cloudstorage2.UploadUserCloudFile[]) = (r8v1 'uploadMutationTask' com.oculus.horizon.cloudstorage2.task.UploadMutationTask) com.oculus.horizon.cloudstorage2.task.UploadMutationTask.mUploads com.oculus.horizon.api.cloudstorage2.UploadUserCloudFile[])
                                  (wrap: com.oculus.horizon.api.cloudstorage2.RemoveUserCloudFile[] : 0x00f9: IGET  (r0v18 com.oculus.horizon.api.cloudstorage2.RemoveUserCloudFile[]) = (r8v1 'uploadMutationTask' com.oculus.horizon.cloudstorage2.task.UploadMutationTask) com.oculus.horizon.cloudstorage2.task.UploadMutationTask.mRemoves com.oculus.horizon.api.cloudstorage2.RemoveUserCloudFile[])
                                 call: com.oculus.horizon.api.cloudstorage2.UploadAndRemoveUserCloudFilesRequest.<init>(java.lang.String, com.oculus.horizon.api.cloudstorage2.UploadUserCloudFile[], com.oculus.horizon.api.cloudstorage2.RemoveUserCloudFile[]):void type: CONSTRUCTOR)
                                  (wrap: com.oculus.horizon.cloudstorage2.task.UploadMutationTask$1 : 0x0102: CONSTRUCTOR  (r0v19 com.oculus.horizon.cloudstorage2.task.UploadMutationTask$1) = (r8v1 'uploadMutationTask' com.oculus.horizon.cloudstorage2.task.UploadMutationTask), (r5v2 'r5' X.0DD) call: com.oculus.horizon.cloudstorage2.task.UploadMutationTask.1.<init>(com.oculus.horizon.cloudstorage2.task.UploadMutationTask, X.0DD):void type: CONSTRUCTOR)
                                 type: VIRTUAL call: com.oculus.horizon.api.ApiRequestManager.post(com.oculus.http.core.base.ApiRequest, com.oculus.http.core.base.ApiCallback):void in method: com.oculus.horizon.cloudstorage2.task.UploadSyncTask.5.then(X.0DC<java.util.List<com.oculus.horizon.cloudstorage2.task.FileUploadTask$RemoteFile>>):X.0DC<com.oculus.horizon.api.cloudstorage2.UploadAndRemoveUserCloudFilesResponse>, file: classes2.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0102: CONSTRUCTOR  (r0v19 com.oculus.horizon.cloudstorage2.task.UploadMutationTask$1) = (r8v1 'uploadMutationTask' com.oculus.horizon.cloudstorage2.task.UploadMutationTask), (r5v2 'r5' X.0DD) call: com.oculus.horizon.cloudstorage2.task.UploadMutationTask.1.<init>(com.oculus.horizon.cloudstorage2.task.UploadMutationTask, X.0DD):void type: CONSTRUCTOR in method: com.oculus.horizon.cloudstorage2.task.UploadSyncTask.5.then(X.0DC<java.util.List<com.oculus.horizon.cloudstorage2.task.FileUploadTask$RemoteFile>>):X.0DC<com.oculus.horizon.api.cloudstorage2.UploadAndRemoveUserCloudFilesResponse>, file: classes2.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                                	... 14 more
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.horizon.cloudstorage2.task.UploadMutationTask, state: GENERATED_AND_UNLOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                                	... 20 more
                                */
                            /*
                            // Method dump skipped, instructions count: 267
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass5.then(X.0DC):java.lang.Object");
                        }
                    }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<UploadAndRemoveUserCloudFilesResponse, AnonymousClass0DC<UserCloudFilesResponse>>() {
                        /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass4 */

                        /* Return type fixed from 'java.lang.Object' to match base method */
                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                        @Override // X.AnonymousClass0D4
                        public final AnonymousClass0DC<UserCloudFilesResponse> then(AnonymousClass0DC<UploadAndRemoveUserCloudFilesResponse> r5) throws Exception {
                            UploadSyncTask.this.mReporter.A02(Step.DOWNLOAD_META_AGAIN);
                            return new DownloadMetadataTask(downloadMetadataTaskProvider, A02.applicationGroupingId, UploadSyncTask.this.mReporter).A00();
                        }
                    }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<UserCloudFilesResponse, CloudStorage2Metadata>() {
                        /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass3 */

                        /* Return type fixed from 'java.lang.Object' to match base method */
                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                        @Override // X.AnonymousClass0D4
                        public final CloudStorage2Metadata then(AnonymousClass0DC<UserCloudFilesResponse> r7) throws Exception {
                            UploadSyncTask.this.mReporter.A02(Step.VALIDATE_UPLOAD);
                            return new UploadVerifier(uploadVerifierProvider, A02, r7.A0G(), (String[]) r5.A00, UploadSyncTask.this.mReporter).call();
                        }
                    }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<CloudStorage2Metadata, File>() {
                        /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass2 */

                        /* Return type fixed from 'java.lang.Object' to match base method */
                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                        @Override // X.AnonymousClass0D4
                        public final File then(AnonymousClass0DC<CloudStorage2Metadata> r6) throws Exception {
                            UploadSyncTask.this.mReporter.A02(Step.WRITE_LOCAL_META);
                            return new WriteMetadataToFile(writeMetadataToFileProvider, A02, r6.A0G(), UploadSyncTask.this.mReporter).call();
                        }
                    }, OculusThreadExecutor.A00()), new AnonymousClass0D4<File, Void>() {
                        /* class com.oculus.horizon.cloudstorage2.task.UploadSyncTask.AnonymousClass1 */

                        /* Return type fixed from 'java.lang.Object' to match base method */
                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                        @Override // X.AnonymousClass0D4
                        public final Void then(AnonymousClass0DC<File> r5) throws Exception {
                            String str;
                            TaskProgressReporter<Step> taskProgressReporter = UploadSyncTask.this.mReporter;
                            Step step = Step.DONE;
                            E e = taskProgressReporter.mProgress;
                            TaskProgressReporter.A00(taskProgressReporter, ((Enum[]) e.getClass().getEnumConstants()).length - 1, step);
                            Step step2 = (Step) e;
                            UploadSyncTask.A00(UploadSyncTask.this, r5.A0K());
                            if (r5.A0K()) {
                                UploadSyncException uploadSyncException = new UploadSyncException(step2, r5.A0F());
                                r3.A01(uploadSyncException);
                                UploadSyncTask.this.mReporter.A03(uploadSyncException);
                                return null;
                            }
                            r3.A02(null);
                            TaskProgressReporter<Step> taskProgressReporter2 = UploadSyncTask.this.mReporter;
                            if (r5.A0I()) {
                                str = "canceled";
                            } else {
                                str = "success";
                            }
                            taskProgressReporter2.A04(str);
                            return null;
                        }
                    }, OculusThreadExecutor.A00());
                    return r3.A00;
                }

                @Inject
                public UploadSyncTask(AbstractC06640p5 r5, @Assisted String str) {
                    this._UL_mInjectionContext = new AnonymousClass0QC(3, r5);
                    TaskProgressReporterProvider taskProgressReporterProvider = (TaskProgressReporterProvider) AnonymousClass117.A00(126, r5);
                    this.mTaskProgressReporterProvider = taskProgressReporterProvider;
                    this.mAppId = str;
                    this.mReporter = new TaskProgressReporter<>(taskProgressReporterProvider, UploadSyncTask.class, str, Step.INIT);
                }
            }
