package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

/* renamed from: Qv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1027Qv0 extends E80 {
    public final /* synthetic */ Tu1 q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1027Qv0(Tu1 tu1, Context context) {
        super(context);
        this.q = tu1;
    }

    @Override // defpackage.E80
    public float b(DisplayMetrics displayMetrics) {
        return 100.0f / ((float) displayMetrics.densityDpi);
    }

    @Override // defpackage.E80
    public int d(int i) {
        return Math.min(100, super.d(i));
    }

    @Override // defpackage.E80
    public void g(View view, VK0 vk0, TK0 tk0) {
        Tu1 tu1 = this.q;
        int[] b = tu1.b(tu1.f7818a.U, view);
        int i = b[0];
        int i2 = b[1];
        int c = c(Math.max(Math.abs(i), Math.abs(i2)));
        if (c > 0) {
            tk0.b(i, i2, c, this.j);
        }
    }
}
