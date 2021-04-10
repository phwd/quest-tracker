package com.facebook.imagepipeline.cache;

import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.imagepipeline.cache.MemoryCache;

public class NativeMemoryCacheTrimStrategy implements MemoryCache.CacheTrimStrategy {
    private static final String TAG = "NativeMemoryCacheTrimStrategy";

    /* renamed from: com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$memory$MemoryTrimType = new int[MemoryTrimType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.facebook.common.memory.MemoryTrimType[] r0 = com.facebook.common.memory.MemoryTrimType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy.AnonymousClass1.$SwitchMap$com$facebook$common$memory$MemoryTrimType = r0
                int[] r0 = com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy.AnonymousClass1.$SwitchMap$com$facebook$common$memory$MemoryTrimType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.facebook.common.memory.MemoryTrimType r1 = com.facebook.common.memory.MemoryTrimType.OnCloseToDalvikHeapLimit     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy.AnonymousClass1.$SwitchMap$com$facebook$common$memory$MemoryTrimType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.facebook.common.memory.MemoryTrimType r1 = com.facebook.common.memory.MemoryTrimType.OnAppBackgrounded     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy.AnonymousClass1.$SwitchMap$com$facebook$common$memory$MemoryTrimType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.facebook.common.memory.MemoryTrimType r1 = com.facebook.common.memory.MemoryTrimType.OnSystemMemoryCriticallyLowWhileAppInForeground     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy.AnonymousClass1.$SwitchMap$com$facebook$common$memory$MemoryTrimType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.facebook.common.memory.MemoryTrimType r1 = com.facebook.common.memory.MemoryTrimType.OnSystemLowMemoryWhileAppInForeground     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy.AnonymousClass1.$SwitchMap$com$facebook$common$memory$MemoryTrimType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.facebook.common.memory.MemoryTrimType r1 = com.facebook.common.memory.MemoryTrimType.OnSystemLowMemoryWhileAppInBackground     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache.CacheTrimStrategy
    public double getTrimRatio(MemoryTrimType memoryTrimType) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$common$memory$MemoryTrimType[memoryTrimType.ordinal()];
        if (i == 1) {
            return 0.0d;
        }
        if (i == 2 || i == 3 || i == 4 || i == 5) {
            return 1.0d;
        }
        FLog.wtf(TAG, "unknown trim type: %s", memoryTrimType);
        return 0.0d;
    }
}
