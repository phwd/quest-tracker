package defpackage;

import java.util.Comparator;

/* renamed from: NR  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NR implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        int i;
        int i2;
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = (byte[]) obj2;
        if (bArr.length != bArr2.length) {
            i2 = bArr.length;
            i = bArr2.length;
        } else {
            for (int i3 = 0; i3 < bArr.length; i3++) {
                if (bArr[i3] != bArr2[i3]) {
                    i2 = bArr[i3];
                    i = bArr2[i3];
                }
            }
            return 0;
        }
        return (i2 == 1 ? 1 : 0) - (i == 1 ? 1 : 0);
    }
}
