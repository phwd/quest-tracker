package com.oculus.horizon.cloudstorage2;

import X.AnonymousClass0J2;
import X.AnonymousClass117;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.cloudstorage2.task.DownloadSyncTaskProvider;
import com.oculus.horizon.cloudstorage2.task.ResolveConflictTaskProvider;
import com.oculus.security.basecomponent.OculusPublicIntentService;
import com.oculus.signature.inject.SignatureChecker;

public class CloudStorageIntentService extends OculusPublicIntentService {
    public static final Class<?> TAG = CloudStorageIntentService.class;
    @Inject
    @Eager
    public CloudStorageLogger mCloudStorageLogger;
    @Inject
    @Eager
    public DownloadSyncTaskProvider mDownloadSyncTaskProvider;
    @Inject
    @Eager
    public ResolveConflictTaskProvider mResolveConflictTaskProvider;
    @Inject
    @Eager
    public SignatureChecker mSignatureChecker;

    /* renamed from: com.oculus.horizon.cloudstorage2.CloudStorageIntentService$5  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$cloudstoragehelper$CloudStorageHelper$RunType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.cloudstoragehelper.CloudStorageHelper$RunType[] r0 = com.oculus.cloudstoragehelper.CloudStorageHelper.RunType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizon.cloudstorage2.CloudStorageIntentService.AnonymousClass5.$SwitchMap$com$oculus$cloudstoragehelper$CloudStorageHelper$RunType = r2
                com.oculus.cloudstoragehelper.CloudStorageHelper$RunType r0 = com.oculus.cloudstoragehelper.CloudStorageHelper.RunType.LAUNCH_SYNC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.cloudstoragehelper.CloudStorageHelper$RunType r0 = com.oculus.cloudstoragehelper.CloudStorageHelper.RunType.RESOLVE_CONFLICT     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.CloudStorageIntentService.AnonymousClass5.<clinit>():void");
        }
    }

    public CloudStorageIntentService() {
        super(CloudStorageIntentService.class.getSimpleName());
    }

    @Override // X.AnonymousClass1U8, com.oculus.security.basecomponent.OculusPublicIntentService
    public final void onCreate() {
        super.onCreate();
        AnonymousClass0J2 r1 = AnonymousClass0J2.get(this);
        this.mDownloadSyncTaskProvider = (DownloadSyncTaskProvider) AnonymousClass117.A00(118, r1);
        this.mResolveConflictTaskProvider = (ResolveConflictTaskProvider) AnonymousClass117.A00(274, r1);
        this.mSignatureChecker = (SignatureChecker) AnonymousClass117.A00(63, r1);
        this.mCloudStorageLogger = (CloudStorageLogger) AnonymousClass117.A00(527, r1);
    }
}
