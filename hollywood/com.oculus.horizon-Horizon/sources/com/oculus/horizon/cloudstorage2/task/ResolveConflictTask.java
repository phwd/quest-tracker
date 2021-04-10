package com.oculus.horizon.cloudstorage2.task;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C08780ya;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.cloudstorage2.TaskProgressReporter;
import com.oculus.horizon.cloudstorage2.TaskProgressReporterProvider;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_DownloadMetadataToFileTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_DownloadWildcardsTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_DownloadSyncTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_TaskProgressReporterProvider_ULSEP_BINDING_ID"})
public class ResolveConflictTask implements MasterTask {
    public static final Class<? extends MasterTask> TAG = ResolveConflictTask.class;
    public AnonymousClass0QC _UL_mInjectionContext;
    public final String mAppId;
    public final C08780ya mGson = new C08780ya();
    public final CloudStorage2ResolutionPolicy mPolicy;
    public final TaskProgressReporter<Step> mReporter;
    @Inject
    @Eager
    public final TaskProgressReporterProvider mTaskProgressReporterProvider;

    public enum Step {
        INIT,
        PREFLIGHT_CHECK,
        DOWNLOAD_PATTERNS,
        EXISTING_CONFLICT_CHECK,
        EXECUTE_POLICY,
        DONE
    }

    /* renamed from: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask$6  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass6 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$cloudstorage2$model$CloudStorage2ResolutionPolicy;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy[] r0 = com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.AnonymousClass6.$SwitchMap$com$oculus$horizon$cloudstorage2$model$CloudStorage2ResolutionPolicy = r2
                com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy r0 = com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy.USE_REMOTE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy r0 = com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy.USE_LOCAL     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.task.ResolveConflictTask.AnonymousClass6.<clinit>():void");
        }
    }

    public static class ResolveConflictException extends Exception {
        public ResolveConflictException(Step step, Throwable th) {
            super(StringFormatUtil.formatStrLocaleSafe("Resolve conflict failed during step %s: %s", step, th.getMessage()), th);
        }
    }

    @Inject
    public ResolveConflictTask(AbstractC06640p5 r5, @Assisted String str, @Assisted CloudStorage2ResolutionPolicy cloudStorage2ResolutionPolicy) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r5);
        TaskProgressReporterProvider taskProgressReporterProvider = (TaskProgressReporterProvider) AnonymousClass117.A00(126, r5);
        this.mTaskProgressReporterProvider = taskProgressReporterProvider;
        this.mAppId = str;
        this.mPolicy = cloudStorage2ResolutionPolicy;
        this.mReporter = new TaskProgressReporter<>(taskProgressReporterProvider, ResolveConflictTask.class, str, Step.INIT);
    }
}
