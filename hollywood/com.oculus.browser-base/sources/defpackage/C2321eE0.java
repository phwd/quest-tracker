package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.widget.FrameLayout;
import java.util.HashMap;
import org.chromium.base.TraceEvent;
import org.chromium.base.UnguessableToken;

/* renamed from: eE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2321eE0 implements AD0 {

    /* renamed from: a  reason: collision with root package name */
    public final C3859nE0 f9842a;

    public C2321eE0(C3859nE0 ne0) {
        this.f9842a = ne0;
    }

    @Override // defpackage.AD0
    public void a(UnguessableToken unguessableToken, UnguessableToken[] unguessableTokenArr, int[] iArr, int[] iArr2, int[] iArr3, UnguessableToken[] unguessableTokenArr2, int[] iArr4) {
        int i;
        int i2;
        C3859nE0 ne0 = this.f9842a;
        boolean z = ne0.h;
        HashMap hashMap = new HashMap();
        for (int i3 = 0; i3 < unguessableTokenArr.length; i3++) {
            if (z) {
                i = 0;
            } else {
                i = iArr2[i3 * 2];
            }
            if (z) {
                i2 = 0;
            } else {
                i2 = iArr2[(i3 * 2) + 1];
            }
            int i4 = i3 * 2;
            hashMap.put(unguessableTokenArr[i3], new C1210Tv0(unguessableTokenArr[i3], iArr[i4], iArr[i4 + 1], i, i2));
        }
        int i5 = 0;
        for (int i6 = 0; i6 < unguessableTokenArr.length; i6++) {
            C1210Tv0 tv0 = (C1210Tv0) hashMap.get(unguessableTokenArr[i6]);
            int i7 = iArr3[i6];
            C1210Tv0[] tv0Arr = new C1210Tv0[i7];
            Rect[] rectArr = new Rect[i7];
            int i8 = 0;
            while (i8 < i7) {
                tv0Arr[i8] = (C1210Tv0) hashMap.get(unguessableTokenArr2[i5]);
                int i9 = i5 * 4;
                int i10 = iArr4[i9];
                int i11 = iArr4[i9 + 1];
                rectArr[i8] = new Rect(i10, i11, iArr4[i9 + 2] + i10, iArr4[i9 + 3] + i11);
                i8++;
                i5++;
            }
            tv0.d = tv0Arr;
            tv0.e = rectArr;
        }
        C1210Tv0 tv02 = (C1210Tv0) hashMap.get(unguessableToken);
        Context context = ne0.b;
        AbstractC5900zD0 zd0 = ne0.c;
        UnguessableToken unguessableToken2 = tv02.f8994a;
        int i12 = tv02.b;
        int i13 = tv02.c;
        int i14 = tv02.f;
        int i15 = tv02.g;
        C4372qE0 qe0 = ne0.f;
        C2151dE0 de0 = ne0.g;
        AbstractC3688mE0 me0 = ne0.i;
        me0.getClass();
        PD0 pd0 = new PD0(context, zd0, unguessableToken2, i12, i13, i14, i15, true, qe0, de0, new RunnableC3346kE0(me0));
        ne0.d = pd0;
        ne0.a(pd0, tv02);
        ne0.e.addView(ne0.d.d, new FrameLayout.LayoutParams(-1, -1));
        C4372qE0 qe02 = ne0.f;
        if (qe02 != null) {
            ne0.e.addView(qe02.f11125a);
        }
        TraceEvent.g0("paint_preview PlayerManager init", (long) ne0.hashCode());
        ne0.i.f();
    }
}
