package defpackage;

import android.graphics.Rect;
import java.util.Comparator;

/* renamed from: zR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5933zR implements Comparator {
    public final Rect F = new Rect();
    public final Rect G = new Rect();
    public final boolean H;
    public final C5073uM I;

    public C5933zR(boolean z, C5073uM uMVar) {
        this.H = z;
        this.I = uMVar;
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        Rect rect = this.F;
        Rect rect2 = this.G;
        this.I.a(obj, rect);
        this.I.a(obj2, rect2);
        int i = rect.top;
        int i2 = rect2.top;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int i3 = rect.left;
        int i4 = rect2.left;
        if (i3 < i4) {
            return this.H ? 1 : -1;
        }
        if (i3 > i4) {
            return this.H ? -1 : 1;
        }
        int i5 = rect.bottom;
        int i6 = rect2.bottom;
        if (i5 < i6) {
            return -1;
        }
        if (i5 > i6) {
            return 1;
        }
        int i7 = rect.right;
        int i8 = rect2.right;
        if (i7 < i8) {
            return this.H ? 1 : -1;
        }
        if (i7 > i8) {
            return this.H ? -1 : 1;
        }
        return 0;
    }
}
