package com.oculus.horizon.cloudstorage2.task;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0D3;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C08780ya;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import com.facebook.FacebookSdk;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.oculus.appmanager.downloader.OculusFileDownloader;
import com.oculus.downloader.contract.DownloaderContract;
import com.oculus.downloader.dispatcher.OculusDownloadListenerDispatcher;
import com.oculus.downloader.extras.contract.DownloadExtrasKeys;
import com.oculus.downloader.model.DownloadConfig;
import com.oculus.downloader.model.DownloadInfo;
import com.oculus.downloader.util.OculusDownloadManagerUtils;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.extras.Extras;
import com.oculus.extras.ExtrasBuilder;
import com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse;
import com.oculus.horizon.cloudstorage2.CloudStorageManager;
import com.oculus.horizon.cloudstorage2.ConflictData;
import com.oculus.horizon.cloudstorage2.TaskProgressReporter;
import com.oculus.horizon.cloudstorage2.TaskProgressReporterProvider;
import com.oculus.horizon.cloudstorage2.callable.GetFileDownloadRequests;
import com.oculus.horizon.cloudstorage2.callable.GetFileDownloadRequestsProvider;
import com.oculus.horizon.cloudstorage2.callable.UpdateAndRemoveFiles;
import com.oculus.horizon.cloudstorage2.callable.UpdateAndRemoveFilesProvider;
import com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFile;
import com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFileProvider;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2ActionMap;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2FileAction;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2Metadata;
import com.oculus.horizon.cloudstorage2.task.FileDownloadTask;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.http.headers.RequestHeaders;
import com.oculus.library.model.App;
import com.oculus.library.model.CloudStorageStatus;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.util.thread.ThreadUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_DownloadMetadataTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_DownloadWildcardsTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_FileDownloadTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_callable_GetFileDownloadRequestsProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_callable_UpdateAndRemoveFilesProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_callable_WriteMetadataToFileProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_BuildConflictDataTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_UploadSyncTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_processtokentracker_ProcessTokenTracker_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_TaskProgressReporterProvider_ULSEP_BINDING_ID"})
public class DownloadSyncTask implements MasterTask {
    public static final Class<? extends MasterTask> TAG = DownloadSyncTask.class;
    public AnonymousClass0QC _UL_mInjectionContext;
    public final String mAppId;
    public ConflictData mConflictData;
    public final C08780ya mGson = new C08780ya();
    public Optional<CloudStorageStatus> mInitialStatus = Absent.INSTANCE;
    public final TaskProgressReporter<Step> mReporter;
    @Inject
    @Eager
    public final TaskProgressReporterProvider mTaskProgressReporterProvider;

    public enum Step {
        INIT,
        PREFLIGHT_CHECK,
        SYNCHRONIZE_APP_GROUP,
        DOWNLOAD_PATTERNS,
        READ_LOCAL_STATE,
        EXISTING_CONFLICT_CHECK,
        PREPARE_CONFLICT_DATA,
        DOWNLOAD_META,
        READ_REMOTE_STATE,
        BUILD_ACTION_MAP,
        DOWNLOAD_FILES,
        MOVE_FILES,
        WRITE_LOCAL_META,
        DONE
    }

    public static class DownloadSyncException extends Exception {
        public DownloadSyncException(Step step, Throwable th) {
            super(StringFormatUtil.formatStrLocaleSafe("Download sync failed during step %s: %s", step, th.getMessage()), th);
        }
    }

    public final AnonymousClass0DC<Void> A00(final boolean z) {
        final AnonymousClass0DD r13 = new AnonymousClass0DD();
        final App A02 = ((OVRLibrary) AnonymousClass0J2.A03(1, 569, this._UL_mInjectionContext)).A02(this.mAppId);
        AnonymousClass0J2.A04(294, this._UL_mInjectionContext);
        final DownloadMetadataTaskProvider downloadMetadataTaskProvider = (DownloadMetadataTaskProvider) AnonymousClass0J2.A04(31, this._UL_mInjectionContext);
        final DownloadWildcardsTaskProvider downloadWildcardsTaskProvider = (DownloadWildcardsTaskProvider) AnonymousClass0J2.A04(270, this._UL_mInjectionContext);
        final FileDownloadTaskProvider fileDownloadTaskProvider = (FileDownloadTaskProvider) AnonymousClass0J2.A04(553, this._UL_mInjectionContext);
        final GetFileDownloadRequestsProvider getFileDownloadRequestsProvider = (GetFileDownloadRequestsProvider) AnonymousClass0J2.A04(528, this._UL_mInjectionContext);
        final UpdateAndRemoveFilesProvider updateAndRemoveFilesProvider = (UpdateAndRemoveFilesProvider) AnonymousClass0J2.A04(FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE, this._UL_mInjectionContext);
        final WriteMetadataToFileProvider writeMetadataToFileProvider = (WriteMetadataToFileProvider) AnonymousClass0J2.A04(444, this._UL_mInjectionContext);
        final BuildConflictDataTaskProvider buildConflictDataTaskProvider = (BuildConflictDataTaskProvider) AnonymousClass0J2.A04(492, this._UL_mInjectionContext);
        final UploadSyncTaskProvider uploadSyncTaskProvider = (UploadSyncTaskProvider) AnonymousClass0J2.A04(184, this._UL_mInjectionContext);
        final AnonymousClass0D3 r18 = new AnonymousClass0D3();
        final AnonymousClass0D3 r3 = new AnonymousClass0D3();
        final AnonymousClass0D3 r17 = new AnonymousClass0D3();
        final AnonymousClass0D3 r16 = new AnonymousClass0D3();
        final AnonymousClass0D3 r15 = new AnonymousClass0D3();
        final AnonymousClass0D3 r21 = new AnonymousClass0D3();
        AnonymousClass0DC.A01(AnonymousClass0DC.A02(AnonymousClass0DC.A04(null), new AnonymousClass0D4<Object, AnonymousClass0DC<Void>>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass14 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final AnonymousClass0DC<Void> then(AnonymousClass0DC<Object> r7) throws Exception {
                String str;
                DownloadSyncTask.this.mReporter.A02(Step.PREFLIGHT_CHECK);
                App app = A02;
                if (app != null) {
                    DownloadSyncTask.this.mReporter.A01(app);
                    DownloadSyncTask downloadSyncTask = DownloadSyncTask.this;
                    App app2 = A02;
                    CloudStorageStatus cloudStorageStatus = app2.cloudStorageStatus;
                    new Object[1][0] = cloudStorageStatus.name();
                    if (!CloudStorageStatus.isEnabled(cloudStorageStatus)) {
                        str = "Cloud syncing is not enabled for this app!";
                    } else if (((CloudStorageManager) AnonymousClass0J2.A03(0, 73, downloadSyncTask._UL_mInjectionContext)).A08(app2)) {
                        return AnonymousClass0DC.A06;
                    } else {
                        if (((CloudStorageManager) AnonymousClass0J2.A03(0, 73, DownloadSyncTask.this._UL_mInjectionContext)).A07()) {
                            return null;
                        }
                        str = "External storage permission needs to be granted.";
                    }
                    throw new IllegalStateException(str);
                }
                throw new IllegalArgumentException("App was not found in library!");
            }
        }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<Void, AnonymousClass0DC<Void>>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass13 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final /* bridge */ /* synthetic */ AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r6) throws Exception {
                DownloadSyncTask.this.mReporter.A02(Step.SYNCHRONIZE_APP_GROUP);
                AnonymousClass0DC A04 = AnonymousClass0DC.A04(null);
                Iterator<App> it = ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, DownloadSyncTask.this._UL_mInjectionContext)).A04(A02).iterator();
                while (it.hasNext()) {
                    final App next = it.next();
                    if (CloudStorageStatus.isUploadSyncRequired(next.cloudStorageStatus)) {
                        A04 = A04.A0D(new AnonymousClass0D4<Void, AnonymousClass0DC<Void>>() {
                            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass13.AnonymousClass1 */

                            /* Return type fixed from 'java.lang.Object' to match base method */
                            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                            @Override // X.AnonymousClass0D4
                            public final AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r6) throws Exception {
                                AnonymousClass13 r4 = AnonymousClass13.this;
                                App app = next;
                                String str = app.id;
                                Object[] objArr = {str, app.applicationGroupingId};
                                return new UploadSyncTask(uploadSyncTaskProvider, str).A02();
                            }
                        }, AnonymousClass0DC.A0A);
                    }
                }
                return AnonymousClass0DC.A01(A04, new AnonymousClass0D4<Void, Void>() {
                    /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass13.AnonymousClass2 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                    @Override // X.AnonymousClass0D4
                    public final Void then(AnonymousClass0DC<Void> r4) throws Exception {
                        if (!r4.A0K()) {
                            return null;
                        }
                        new Object[1][0] = r4.A0F().toString();
                        return null;
                    }
                }, OculusThreadExecutor.A00());
            }
        }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<Void, AnonymousClass0DC<Void>>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass12 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r6) throws Exception {
                DownloadSyncTask downloadSyncTask = DownloadSyncTask.this;
                downloadSyncTask.mInitialStatus = Optional.of(A02.cloudStorageStatus);
                ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, downloadSyncTask._UL_mInjectionContext)).A06(downloadSyncTask.mAppId, CloudStorageStatus.DOWNLOAD_SYNCING);
                ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, DownloadSyncTask.this._UL_mInjectionContext)).A05(A02);
                return null;
            }
        }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<Void, AnonymousClass0DC<String[]>>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass11 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final AnonymousClass0DC<String[]> then(AnonymousClass0DC<Void> r5) throws Exception {
                DownloadSyncTask.this.mReporter.A02(Step.DOWNLOAD_PATTERNS);
                return new DownloadWildcardsTask(downloadWildcardsTaskProvider, A02.applicationGroupingId, DownloadSyncTask.this.mReporter).A00();
            }
        }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<String[], Void>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass10 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final Void then(AnonymousClass0DC<String[]> r4) throws Exception {
                DownloadSyncTask.this.mReporter.A02(Step.READ_LOCAL_STATE);
                r21.A00 = (T) r4.A0G();
                T t = (T) ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, DownloadSyncTask.this._UL_mInjectionContext)).A03(A02, (String[]) r21.A00);
                r17.A00 = t;
                r16.A00 = (T) DownloadSyncTask.this.mGson.A05(t, CloudStorage2Metadata.class);
                return null;
            }
        }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<Void, String[]>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass9 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final String[] then(AnonymousClass0DC<Void> r4) throws Exception {
                DownloadSyncTask.this.mReporter.A02(Step.EXISTING_CONFLICT_CHECK);
                if (z) {
                    return null;
                }
                return PlatformPluginManager.nativeGetCloudStorage2DownloadConflictFiles(DownloadSyncTask.this.mGson.A06(((CloudStorageManager) AnonymousClass0J2.A03(0, 73, DownloadSyncTask.this._UL_mInjectionContext)).A01(A02)), r17.A00);
            }
        }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<String[], AnonymousClass0DC<Void>>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass8 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final /* bridge */ /* synthetic */ AnonymousClass0DC<Void> then(AnonymousClass0DC<String[]> r6) throws Exception {
                DownloadSyncTask.this.mReporter.A02(Step.PREPARE_CONFLICT_DATA);
                String[] A0G = r6.A0G();
                if (A0G == null || A0G.length == 0) {
                    return null;
                }
                BuildConflictDataTask buildConflictDataTask = new BuildConflictDataTask(buildConflictDataTaskProvider, A02, r16.A00, DownloadSyncTask.this.mReporter);
                return AnonymousClass0DC.A01(new DownloadMetadataTask(buildConflictDataTask.mDownloadMetadataProvider, buildConflictDataTask.mApp.applicationGroupingId, buildConflictDataTask).A00().A0B(
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004c: RETURN  
                      (wrap: X.0DC : 0x0048: INVOKE  (r0v14 X.0DC) = 
                      (wrap: X.0DC<TContinuationResult> : 0x003b: INVOKE  (r2v2 X.0DC<TContinuationResult>) = 
                      (wrap: X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse> : 0x0032: INVOKE  (r1v4 X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse>) = 
                      (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask : 0x002f: CONSTRUCTOR  (r0v11 com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask) = 
                      (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTaskProvider : 0x0027: IGET  (r2v1 com.oculus.horizon.cloudstorage2.task.DownloadMetadataTaskProvider) = (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask) com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask.mDownloadMetadataProvider com.oculus.horizon.cloudstorage2.task.DownloadMetadataTaskProvider)
                      (wrap: java.lang.String : 0x002b: IGET  (r1v3 java.lang.String) = 
                      (wrap: com.oculus.library.model.App : 0x0029: IGET  (r0v10 com.oculus.library.model.App) = (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask) com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask.mApp com.oculus.library.model.App)
                     com.oculus.library.model.App.applicationGroupingId java.lang.String)
                      (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask)
                     call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.<init>(X.0p5, java.lang.String, com.oculus.horizon.cloudstorage2.Reporter):void type: CONSTRUCTOR)
                     type: VIRTUAL call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.A00():X.0DC)
                      (wrap: com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask$1 : 0x0038: CONSTRUCTOR  (r0v12 com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask$1) = (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask) call: com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask.1.<init>(com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask):void type: CONSTRUCTOR)
                     type: VIRTUAL call: X.0DC.A0B(X.0D4):X.0DC)
                      (wrap: com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$8$1 : 0x0041: CONSTRUCTOR  (r1v5 com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$8$1) = (r5v0 'this' com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$8 A[IMMUTABLE_TYPE, THIS]) call: com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.8.1.<init>(com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$8):void type: CONSTRUCTOR)
                      (wrap: com.oculus.executors.OculusThreadExecutor : 0x0044: INVOKE  (r0v13 com.oculus.executors.OculusThreadExecutor) =  type: STATIC call: com.oculus.executors.OculusThreadExecutor.A00():com.oculus.executors.OculusThreadExecutor)
                     type: STATIC call: X.0DC.A01(X.0DC, X.0D4, java.util.concurrent.Executor):X.0DC)
                     in method: com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.8.then(X.0DC<java.lang.String[]>):X.0DC<java.lang.Void>, file: classes2.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
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
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0048: INVOKE  (r0v14 X.0DC) = 
                      (wrap: X.0DC<TContinuationResult> : 0x003b: INVOKE  (r2v2 X.0DC<TContinuationResult>) = 
                      (wrap: X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse> : 0x0032: INVOKE  (r1v4 X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse>) = 
                      (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask : 0x002f: CONSTRUCTOR  (r0v11 com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask) = 
                      (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTaskProvider : 0x0027: IGET  (r2v1 com.oculus.horizon.cloudstorage2.task.DownloadMetadataTaskProvider) = (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask) com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask.mDownloadMetadataProvider com.oculus.horizon.cloudstorage2.task.DownloadMetadataTaskProvider)
                      (wrap: java.lang.String : 0x002b: IGET  (r1v3 java.lang.String) = 
                      (wrap: com.oculus.library.model.App : 0x0029: IGET  (r0v10 com.oculus.library.model.App) = (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask) com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask.mApp com.oculus.library.model.App)
                     com.oculus.library.model.App.applicationGroupingId java.lang.String)
                      (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask)
                     call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.<init>(X.0p5, java.lang.String, com.oculus.horizon.cloudstorage2.Reporter):void type: CONSTRUCTOR)
                     type: VIRTUAL call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.A00():X.0DC)
                      (wrap: com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask$1 : 0x0038: CONSTRUCTOR  (r0v12 com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask$1) = (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask) call: com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask.1.<init>(com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask):void type: CONSTRUCTOR)
                     type: VIRTUAL call: X.0DC.A0B(X.0D4):X.0DC)
                      (wrap: com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$8$1 : 0x0041: CONSTRUCTOR  (r1v5 com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$8$1) = (r5v0 'this' com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$8 A[IMMUTABLE_TYPE, THIS]) call: com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.8.1.<init>(com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$8):void type: CONSTRUCTOR)
                      (wrap: com.oculus.executors.OculusThreadExecutor : 0x0044: INVOKE  (r0v13 com.oculus.executors.OculusThreadExecutor) =  type: STATIC call: com.oculus.executors.OculusThreadExecutor.A00():com.oculus.executors.OculusThreadExecutor)
                     type: STATIC call: X.0DC.A01(X.0DC, X.0D4, java.util.concurrent.Executor):X.0DC in method: com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.8.then(X.0DC<java.lang.String[]>):X.0DC<java.lang.Void>, file: classes2.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:313)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                    	... 18 more
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003b: INVOKE  (r2v2 X.0DC<TContinuationResult>) = 
                      (wrap: X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse> : 0x0032: INVOKE  (r1v4 X.0DC<com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse>) = 
                      (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask : 0x002f: CONSTRUCTOR  (r0v11 com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask) = 
                      (wrap: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTaskProvider : 0x0027: IGET  (r2v1 com.oculus.horizon.cloudstorage2.task.DownloadMetadataTaskProvider) = (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask) com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask.mDownloadMetadataProvider com.oculus.horizon.cloudstorage2.task.DownloadMetadataTaskProvider)
                      (wrap: java.lang.String : 0x002b: IGET  (r1v3 java.lang.String) = 
                      (wrap: com.oculus.library.model.App : 0x0029: IGET  (r0v10 com.oculus.library.model.App) = (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask) com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask.mApp com.oculus.library.model.App)
                     com.oculus.library.model.App.applicationGroupingId java.lang.String)
                      (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask)
                     call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.<init>(X.0p5, java.lang.String, com.oculus.horizon.cloudstorage2.Reporter):void type: CONSTRUCTOR)
                     type: VIRTUAL call: com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.A00():X.0DC)
                      (wrap: com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask$1 : 0x0038: CONSTRUCTOR  (r0v12 com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask$1) = (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask) call: com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask.1.<init>(com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask):void type: CONSTRUCTOR)
                     type: VIRTUAL call: X.0DC.A0B(X.0D4):X.0DC in method: com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.8.then(X.0DC<java.lang.String[]>):X.0DC<java.lang.Void>, file: classes2.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                    	... 22 more
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0038: CONSTRUCTOR  (r0v12 com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask$1) = (r3v0 'buildConflictDataTask' com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask) call: com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask.1.<init>(com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask):void type: CONSTRUCTOR in method: com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.8.then(X.0DC<java.lang.String[]>):X.0DC<java.lang.Void>, file: classes2.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                    	... 28 more
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask, state: GENERATED_AND_UNLOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                    	... 34 more
                    */
                /*
                    this = this;
                    com.oculus.horizon.cloudstorage2.task.DownloadSyncTask r0 = com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.this
                    com.oculus.horizon.cloudstorage2.TaskProgressReporter<com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$Step> r1 = r0.mReporter
                    com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$Step r0 = com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.Step.PREPARE_CONFLICT_DATA
                    r1.A02(r0)
                    java.lang.Object r0 = r6.A0G()
                    java.lang.String[] r0 = (java.lang.String[]) r0
                    if (r0 == 0) goto L_0x004d
                    int r0 = r0.length
                    if (r0 == 0) goto L_0x004d
                    com.oculus.horizon.cloudstorage2.task.BuildConflictDataTaskProvider r4 = r5
                    com.oculus.library.model.App r2 = r12
                    X.0D3 r0 = r16
                    T r1 = r0.A00
                    com.oculus.horizon.cloudstorage2.model.CloudStorage2Metadata r1 = (com.oculus.horizon.cloudstorage2.model.CloudStorage2Metadata) r1
                    com.oculus.horizon.cloudstorage2.task.DownloadSyncTask r0 = com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.this
                    com.oculus.horizon.cloudstorage2.TaskProgressReporter<com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$Step> r0 = r0.mReporter
                    com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask r3 = new com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask
                    r3.<init>(r4, r2, r1, r0)
                    com.oculus.horizon.cloudstorage2.task.DownloadMetadataTaskProvider r2 = r3.mDownloadMetadataProvider
                    com.oculus.library.model.App r0 = r3.mApp
                    java.lang.String r1 = r0.applicationGroupingId
                    com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask r0 = new com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask
                    r0.<init>(r2, r1, r3)
                    X.0DC r1 = r0.A00()
                    com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask$1 r0 = new com.oculus.horizon.cloudstorage2.task.BuildConflictDataTask$1
                    r0.<init>()
                    X.0DC r2 = r1.A0B(r0)
                    com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$8$1 r1 = new com.oculus.horizon.cloudstorage2.task.DownloadSyncTask$8$1
                    r1.<init>()
                    com.oculus.executors.OculusThreadExecutor r0 = com.oculus.executors.OculusThreadExecutor.A00()
                    X.0DC r0 = X.AnonymousClass0DC.A01(r2, r1, r0)
                    return r0
                L_0x004d:
                    r0 = 0
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass8.then(X.0DC):java.lang.Object");
            }
        }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<Void, AnonymousClass0DC<UserCloudFilesResponse>>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass7 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final AnonymousClass0DC<UserCloudFilesResponse> then(AnonymousClass0DC<Void> r5) throws Exception {
                DownloadSyncTask.this.mReporter.A02(Step.DOWNLOAD_META);
                return new DownloadMetadataTask(downloadMetadataTaskProvider, A02.applicationGroupingId, DownloadSyncTask.this.mReporter).A00();
            }
        }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<UserCloudFilesResponse, UserCloudFilesResponse>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass6 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final UserCloudFilesResponse then(AnonymousClass0DC<UserCloudFilesResponse> r4) throws Exception {
                DownloadSyncTask.this.mReporter.A02(Step.READ_REMOTE_STATE);
                r3.A00 = (T) CloudStorage2Metadata.A00(r4.A0G());
                r18.A00 = (T) DownloadSyncTask.this.mGson.A06(r3.A00);
                return r4.A0G();
            }
        }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<UserCloudFilesResponse, FileDownloadTask.FileRequest[]>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass5 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final FileDownloadTask.FileRequest[] then(AnonymousClass0DC<UserCloudFilesResponse> r5) throws Exception {
                DownloadSyncTask.this.mReporter.A02(Step.BUILD_ACTION_MAP);
                T t = (T) ((CloudStorage2ActionMap) DownloadSyncTask.this.mGson.A05(PlatformPluginManager.nativeGetCloudStorage2SyncFileActions(r18.A00, r17.A00), CloudStorage2ActionMap.class));
                AnonymousClass006.A05("Action map: ", DownloadSyncTask.this.mGson.A06(t));
                r15.A00 = t;
                return new GetFileDownloadRequests(r5.A0G(), t, DownloadSyncTask.this.mReporter).call();
            }
        }, OculusThreadExecutor.A00()).A0D(new AnonymousClass0D4<FileDownloadTask.FileRequest[], AnonymousClass0DC<Map<String, String>>>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass4 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final AnonymousClass0DC<Map<String, String>> then(AnonymousClass0DC<FileDownloadTask.FileRequest[]> r24) throws Exception {
                long enqueue;
                DownloadSyncTask.this.mReporter.A02(Step.DOWNLOAD_FILES);
                FileDownloadTask.FileRequest[] A0G = r24.A0G();
                TaskProgressReporter<Step> taskProgressReporter = DownloadSyncTask.this.mReporter;
                new Object[1][0] = Integer.valueOf(A0G.length);
                FileDownloadTaskProvider fileDownloadTaskProvider = fileDownloadTaskProvider;
                App app = A02;
                FileDownloadTask fileDownloadTask = new FileDownloadTask(fileDownloadTaskProvider, StringFormatUtil.formatStrLocaleSafe("cloud_storage/%s/%s/", app.applicationGroupingId, app.appScopedUserId), A0G, taskProgressReporter);
                FileDownloadTask.CloudFileDownloadManager cloudFileDownloadManager = new FileDownloadTask.CloudFileDownloadManager();
                fileDownloadTask.downloadManager = cloudFileDownloadManager;
                ((OculusFileDownloader) AnonymousClass0J2.A03(1, 429, fileDownloadTask._UL_mInjectionContext)).mDownloadListenerDispatcher.mStrongListeners.add(cloudFileDownloadManager);
                FileDownloadTask.CloudFileDownloadManager cloudFileDownloadManager2 = fileDownloadTask.downloadManager;
                FileDownloadTask.FileRequest[] fileRequestArr = fileDownloadTask.mRequests;
                int length = fileRequestArr.length;
                if (length == 0) {
                    OculusDownloadListenerDispatcher oculusDownloadListenerDispatcher = ((OculusFileDownloader) AnonymousClass0J2.A03(1, 429, FileDownloadTask.this._UL_mInjectionContext)).mDownloadListenerDispatcher;
                    oculusDownloadListenerDispatcher.mDynamicListeners.remove(cloudFileDownloadManager2);
                    oculusDownloadListenerDispatcher.mStrongListeners.remove(cloudFileDownloadManager2);
                    cloudFileDownloadManager2.mCompletionSource.A02(new HashMap());
                } else {
                    for (FileDownloadTask.FileRequest fileRequest : fileRequestArr) {
                        cloudFileDownloadManager2.mRequestedFiles.add(fileRequest.id);
                    }
                    for (FileDownloadTask.FileRequest fileRequest2 : fileRequestArr) {
                        ExtrasBuilder extrasBuilder = new ExtrasBuilder();
                        extrasBuilder.A00("request_id", fileRequest2.id);
                        extrasBuilder.A00(FileDownloadTask.CloudFileDownloadManager.EXTRA_KEY_RELATIVE_PATH, fileRequest2.relativePath);
                        Extras extras = new Extras(extrasBuilder.mData);
                        String str = fileRequest2.cloudFileUri;
                        if (str == null) {
                            try {
                                DownloadInfo downloadInfo = new DownloadInfo(-1, 8, 0, 0, 0, Optional.of(File.createTempFile(Long.toString(new Random().nextLong()), ".temp", ((Context) AnonymousClass0J2.A03(0, 294, FileDownloadTask.this._UL_mInjectionContext)).getExternalCacheDir()).getAbsolutePath()), Absent.INSTANCE, extras);
                                cloudFileDownloadManager2.A5x(downloadInfo);
                                Object[] objArr = {Long.valueOf(downloadInfo.id), fileRequest2.relativePath};
                            } catch (IOException e) {
                                FileDownloadTask.CloudFileDownloadManager.A00(cloudFileDownloadManager2, e);
                            }
                        } else {
                            DownloadConfig downloadConfig = new DownloadConfig(str, AnonymousClass006.A05(FileDownloadTask.this.mPrependUri, fileRequest2.id));
                            OculusFileDownloader oculusFileDownloader = (OculusFileDownloader) AnonymousClass0J2.A03(1, 429, FileDownloadTask.this._UL_mInjectionContext);
                            ThreadUtils.A02();
                            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadConfig.uri));
                            request.setAllowedNetworkTypes(2);
                            request.setAllowedOverRoaming(false);
                            request.setAllowedOverMetered(false);
                            request.setVisibleInDownloadsUi(false);
                            request.setDestinationUri(null);
                            request.addRequestHeader("Accept", OculusFileDownloader.ACCEPT_BINARY_STREAM);
                            request.addRequestHeader("User-Agent", oculusFileDownloader.mUserAgentString);
                            for (Map.Entry<String, String> entry : RequestHeaders.DEFAULT_REQUEST_HEADERS.entrySet()) {
                                request.addRequestHeader(entry.getKey(), entry.getValue());
                            }
                            for (Map.Entry<String, String> entry2 : downloadConfig.headers.entrySet()) {
                                request.addRequestHeader(entry2.getKey(), entry2.getValue());
                            }
                            request.setAllowedNetworkTypes(2);
                            request.setAllowedOverRoaming(true);
                            request.setAllowedOverMetered(false);
                            if ((downloadConfig.networks & 1) != 0) {
                                request.setAllowedNetworkTypes(3);
                                request.setAllowedOverMetered(true);
                            }
                            OculusDownloadManagerUtils oculusDownloadManagerUtils = oculusFileDownloader.mDownloadManagerUtils;
                            int i = downloadConfig.visibility;
                            int checkCallingOrSelfPermission = oculusDownloadManagerUtils.mApplicationContext.checkCallingOrSelfPermission(OculusDownloadManagerUtils.PERMISSION_DOWNLOAD_WITHOUT_NOTIFICATION);
                            if (i == 2 && checkCallingOrSelfPermission != 0) {
                                i = 0;
                            }
                            request.setNotificationVisibility(i);
                            String str2 = downloadConfig.fileMimeType;
                            if (!Strings.isNullOrEmpty(str2)) {
                                request.setMimeType(str2);
                            }
                            String str3 = downloadConfig.title;
                            if (!Strings.isNullOrEmpty(str3)) {
                                request.setTitle(str3);
                            }
                            String str4 = downloadConfig.description;
                            if (!Strings.isNullOrEmpty(str4)) {
                                request.setDescription(str4);
                            }
                            request.setNotificationVisibility(2);
                            File file = new File(new File(DownloaderContract.EXTERNAL_DOWNLOAD_SUB_DIRECTORY), downloadConfig.fileUri);
                            file.getParentFile().mkdirs();
                            request.setDestinationUri(Uri.fromFile(file));
                            synchronized (oculusFileDownloader.mExtrasLock) {
                                enqueue = oculusFileDownloader.mDownloadManager.enqueue(request);
                                ExtrasBuilder extrasBuilder2 = new ExtrasBuilder(extras.mData);
                                extrasBuilder2.A00(DownloadExtrasKeys.KEY_DOWNLOAD_SCHEDULED_TIME_MS, Long.toString(SystemClock.elapsedRealtime()));
                                extrasBuilder2.A00(DownloadExtrasKeys.KEY_CURRENT_DOWNLOAD_URI, downloadConfig.uri);
                                OculusFileDownloader.A03(oculusFileDownloader, enqueue, new Extras(extrasBuilder2.mData));
                            }
                            Long valueOf = Long.valueOf(enqueue);
                            Object[] objArr2 = {valueOf, fileRequest2.relativePath};
                            cloudFileDownloadManager2.mRequestedDownloads.add(valueOf);
                        }
                    }
                }
                return fileDownloadTask.downloadManager.mCompletionSource.A00;
            }
        }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<Map<String, String>, Void>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass3 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final Void then(AnonymousClass0DC<Map<String, String>> r8) throws Exception {
                DownloadSyncTask.this.mReporter.A02(Step.MOVE_FILES);
                new UpdateAndRemoveFiles(updateAndRemoveFilesProvider, A02, r8.A0G(), (String[]) r15.A00.get(CloudStorage2FileAction.REMOVE), DownloadSyncTask.this.mReporter).call();
                return null;
            }
        }, OculusThreadExecutor.A00()).A0C(new AnonymousClass0D4<Void, File>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass2 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final File then(AnonymousClass0DC<Void> r6) throws Exception {
                DownloadSyncTask.this.mReporter.A02(Step.WRITE_LOCAL_META);
                return new WriteMetadataToFile(writeMetadataToFileProvider, A02, r3.A00, DownloadSyncTask.this.mReporter).call();
            }
        }, OculusThreadExecutor.A00()), new AnonymousClass0D4<File, AnonymousClass0DC<Void>>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadSyncTask.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
            @Override // X.AnonymousClass0D4
            public final AnonymousClass0DC<Void> then(AnonymousClass0DC<File> r7) throws Exception {
                String str;
                TaskProgressReporter<Step> taskProgressReporter = DownloadSyncTask.this.mReporter;
                Step step = Step.DONE;
                E e = taskProgressReporter.mProgress;
                TaskProgressReporter.A00(taskProgressReporter, ((Enum[]) e.getClass().getEnumConstants()).length - 1, step);
                Step step2 = (Step) e;
                DownloadSyncTask downloadSyncTask = DownloadSyncTask.this;
                App A02 = ((OVRLibrary) AnonymousClass0J2.A03(1, 569, downloadSyncTask._UL_mInjectionContext)).A02(downloadSyncTask.mAppId);
                if (A02 != null && downloadSyncTask.mInitialStatus.isPresent()) {
                    ((CloudStorageManager) AnonymousClass0J2.A03(0, 73, downloadSyncTask._UL_mInjectionContext)).A06(downloadSyncTask.mAppId, CloudStorageStatus.ENABLED);
                    A00(new File(DownloaderContract.EXTERNAL_DOWNLOAD_SUB_DIRECTORY, StringFormatUtil.formatStrLocaleSafe("cloud_storage/%s/%s/", A02.applicationGroupingId, A02.appScopedUserId)));
                }
                if (r7.A0K()) {
                    DownloadSyncException downloadSyncException = new DownloadSyncException(step2, r7.A0F());
                    r13.A01(downloadSyncException);
                    DownloadSyncTask.this.mReporter.A03(downloadSyncException);
                    return null;
                }
                r13.A02(null);
                TaskProgressReporter<Step> taskProgressReporter2 = DownloadSyncTask.this.mReporter;
                if (r7.A0I()) {
                    str = "canceled";
                } else {
                    str = "success";
                }
                taskProgressReporter2.A04(str);
                return null;
            }

            public static void A00(File file) {
                File[] listFiles;
                if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
                    for (File file2 : listFiles) {
                        if (file2.isDirectory()) {
                            A00(file2);
                        }
                        file2.delete();
                    }
                }
            }
        }, OculusThreadExecutor.A00());
        return r13.A00;
    }

    @Inject
    public DownloadSyncTask(AbstractC06640p5 r5, @Assisted String str) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r5);
        TaskProgressReporterProvider taskProgressReporterProvider = (TaskProgressReporterProvider) AnonymousClass117.A00(126, r5);
        this.mTaskProgressReporterProvider = taskProgressReporterProvider;
        this.mAppId = str;
        this.mReporter = new TaskProgressReporter<>(taskProgressReporterProvider, DownloadSyncTask.class, str, Step.INIT);
    }
}
