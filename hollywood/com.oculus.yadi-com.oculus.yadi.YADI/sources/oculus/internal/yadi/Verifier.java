package oculus.internal.yadi;

import android.content.pm.PackageInfo;
import com.oculus.os.yadi.RemoteResource;
import java.io.File;
import oculus.internal.PackageParserAdapter;

/* access modifiers changed from: package-private */
public class Verifier {

    /* access modifiers changed from: package-private */
    /* renamed from: oculus.internal.yadi.Verifier$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$yadi$RemoteResource$HashType = new int[RemoteResource.HashType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.os.yadi.RemoteResource$HashType[] r0 = com.oculus.os.yadi.RemoteResource.HashType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                oculus.internal.yadi.Verifier.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$RemoteResource$HashType = r0
                int[] r0 = oculus.internal.yadi.Verifier.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$RemoteResource$HashType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.os.yadi.RemoteResource$HashType r1 = com.oculus.os.yadi.RemoteResource.HashType.NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = oculus.internal.yadi.Verifier.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$RemoteResource$HashType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.os.yadi.RemoteResource$HashType r1 = com.oculus.os.yadi.RemoteResource.HashType.SHA256     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: oculus.internal.yadi.Verifier.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0062, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0063, code lost:
        r6.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0066, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean verifyContentDigest(java.io.File r6, com.oculus.os.yadi.RemoteResource r7) throws oculus.internal.yadi.InstallException {
        /*
        // Method dump skipped, instructions count: 115
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.yadi.Verifier.verifyContentDigest(java.io.File, com.oculus.os.yadi.RemoteResource):boolean");
    }

    public static PackageInfo verifyPackageInfo(File file, RemoteResource remoteResource) throws InstallException {
        try {
            return PackageParserAdapter.getPackageInfo(file);
        } catch (Exception e) {
            throw new InstallException("Package verification failed", remoteResource, e);
        }
    }
}
