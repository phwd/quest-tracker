package com.oculus.installer;

import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public class InstallIntegrityResult {
    final String appId;
    final Map<String, FileState> assetFileIntegrityResults;
    int assetsMismatchedChecksum = 0;
    int assetsMissing = 0;
    boolean hasAssets;
    boolean hasObb;
    FileIntegrityResult obbIntegrityResult;
    final String packageName;
    int requiredAssetsMissing = 0;
    final long startTime;
    final long versionCode;

    /* access modifiers changed from: package-private */
    public static class FileIntegrityResult {
        final String fileName;
        final FileState fileState;

        FileIntegrityResult(String str, FileState fileState2) {
            this.fileName = str;
            this.fileState = fileState2;
        }
    }

    InstallIntegrityResult(String str, String str2, long j, long j2) {
        this.appId = str;
        this.packageName = str2;
        this.versionCode = j;
        this.startTime = j2;
        this.assetFileIntegrityResults = new HashMap();
    }

    /* access modifiers changed from: package-private */
    public void addAssetIntegrityResult(String str, FileState fileState) {
        this.assetFileIntegrityResults.put(str, fileState);
        int i = AnonymousClass1.$SwitchMap$com$oculus$installer$FileState[fileState.ordinal()];
        if (i == 1) {
            this.assetsMismatchedChecksum++;
        } else if (i == 2) {
            this.requiredAssetsMissing++;
        } else if (i == 3) {
            this.assetsMissing++;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.installer.InstallIntegrityResult$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$installer$FileState = new int[FileState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.installer.FileState[] r0 = com.oculus.installer.FileState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.installer.InstallIntegrityResult.AnonymousClass1.$SwitchMap$com$oculus$installer$FileState = r0
                int[] r0 = com.oculus.installer.InstallIntegrityResult.AnonymousClass1.$SwitchMap$com$oculus$installer$FileState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.installer.FileState r1 = com.oculus.installer.FileState.CHECKSUM_MISMATCH     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.installer.InstallIntegrityResult.AnonymousClass1.$SwitchMap$com$oculus$installer$FileState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.installer.FileState r1 = com.oculus.installer.FileState.MISSING_REQUIRED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.installer.InstallIntegrityResult.AnonymousClass1.$SwitchMap$com$oculus$installer$FileState     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.installer.FileState r1 = com.oculus.installer.FileState.MISSING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.installer.InstallIntegrityResult.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void addOBBIntegrityResult(String str, FileState fileState) {
        this.obbIntegrityResult = new FileIntegrityResult(str, fileState);
    }
}
