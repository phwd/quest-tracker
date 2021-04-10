package defpackage;

import java.util.Comparator;

/* renamed from: cj1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2058cj1 implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        int i;
        int i2;
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = (byte[]) obj2;
        if (bArr == bArr2) {
            return 0;
        }
        if (bArr == null) {
            return -1;
        }
        if (bArr2 == null) {
            return 1;
        }
        int i3 = 0;
        while (true) {
            if (i3 < Math.min(bArr.length, bArr2.length)) {
                if (bArr[i3] != bArr2[i3]) {
                    i2 = bArr[i3];
                    i = bArr2[i3];
                    break;
                }
                i3++;
            } else if (bArr.length == bArr2.length) {
                return 0;
            } else {
                i2 = bArr.length;
                i = bArr2.length;
            }
        }
        return i2 - i;
    }
}
