package defpackage;

import android.util.SparseIntArray;

/* renamed from: OW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class OW {

    /* renamed from: a  reason: collision with root package name */
    public final SparseIntArray f8628a = new SparseIntArray();
    public final SparseIntArray b = new SparseIntArray();

    public int a(int i, int i2) {
        int c = c(i);
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            int c2 = c(i5);
            i3 += c2;
            if (i3 == i2) {
                i4++;
                i3 = 0;
            } else if (i3 > i2) {
                i4++;
                i3 = c2;
            }
        }
        return i3 + c > i2 ? i4 + 1 : i4;
    }

    public int b(int i, int i2) {
        int c = c(i);
        if (c == i2) {
            return 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int c2 = c(i4);
            i3 += c2;
            if (i3 == i2) {
                i3 = 0;
            } else if (i3 > i2) {
                i3 = c2;
            }
        }
        if (c + i3 <= i2) {
            return i3;
        }
        return 0;
    }

    public abstract int c(int i);
}
