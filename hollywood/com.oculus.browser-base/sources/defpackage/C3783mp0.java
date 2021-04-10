package defpackage;

import android.graphics.Rect;

/* renamed from: mp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3783mp0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f10448a;
    public final int b;
    public final Rect c;
    public final int[] d;
    public final int[] e;
    public Rect f;

    public C3783mp0(int i, int i2, Rect rect, int[] iArr, int[] iArr2) {
        this.f10448a = i;
        this.b = i2;
        this.c = new Rect(rect.left, rect.top, i - rect.right, i2 - rect.bottom);
        int[] iArr3 = new int[iArr.length];
        this.d = iArr3;
        int[] iArr4 = new int[iArr2.length];
        this.e = iArr4;
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, iArr4, 0, iArr2.length);
        this.f = new Rect(iArr3[0], iArr4[0], iArr3[1], iArr4[1]);
    }
}
