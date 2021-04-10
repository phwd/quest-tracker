package com.oculus.horizon.cloudstorage2.model;

import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.cloudstoragehelper.CloudStorageHelper;

public enum CloudStorage2ResolutionPolicy {
    USE_LOCAL,
    USE_REMOTE,
    USE_LATEST,
    MANUAL;

    /* renamed from: com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$cloudstoragehelper$CloudStorageHelper$ResolutionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.cloudstoragehelper.CloudStorageHelper$ResolutionType[] r0 = com.oculus.cloudstoragehelper.CloudStorageHelper.ResolutionType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy.AnonymousClass1.$SwitchMap$com$oculus$cloudstoragehelper$CloudStorageHelper$ResolutionType = r2
                com.oculus.cloudstoragehelper.CloudStorageHelper$ResolutionType r0 = com.oculus.cloudstoragehelper.CloudStorageHelper.ResolutionType.USE_LOCAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.cloudstoragehelper.CloudStorageHelper$ResolutionType r0 = com.oculus.cloudstoragehelper.CloudStorageHelper.ResolutionType.USE_REMOTE     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.model.CloudStorage2ResolutionPolicy.AnonymousClass1.<clinit>():void");
        }
    }

    public static CloudStorage2ResolutionPolicy from(CloudStorageHelper.ResolutionType resolutionType) {
        switch (resolutionType.ordinal()) {
            case 0:
                return USE_LOCAL;
            case 1:
                return USE_REMOTE;
            default:
                throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("Unconvertable ResolutionType %s", resolutionType));
        }
    }
}
