package defpackage;

import android.media.MediaFormat;
import java.nio.ByteBuffer;

/* renamed from: ke0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3408ke0 {
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0175  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.media.MediaFormat r16, boolean r17) {
        /*
        // Method dump skipped, instructions count: 624
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC3408ke0.a(android.media.MediaFormat, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.media.MediaFormat b(java.lang.String r5, int r6, int r7, byte[][] r8, org.chromium.media.HdrMetadata r9, boolean r10) {
        /*
        // Method dump skipped, instructions count: 300
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC3408ke0.b(java.lang.String, int, int, byte[][], org.chromium.media.HdrMetadata, boolean):android.media.MediaFormat");
    }

    public static void c(MediaFormat mediaFormat, byte[][] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i].length != 0) {
                mediaFormat.setByteBuffer(AbstractC2531fV.w("csd-", i), ByteBuffer.wrap(bArr[i]));
            }
        }
    }
}
