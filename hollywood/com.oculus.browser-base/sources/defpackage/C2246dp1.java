package defpackage;

import android.content.Context;

/* renamed from: dp1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2246dp1 extends C2290e4 {
    public final boolean c;

    public C2246dp1(Context context) {
        super(context);
        this.c = j(context);
    }

    public static boolean j(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    @Override // defpackage.C2290e4
    public DialogC2461f4 a() {
        DialogC2461f4 a2 = super.a();
        Q7 a3 = a2.a();
        int i = this.c ? 2 : 1;
        LayoutInflater$Factory2C3156j8 j8Var = (LayoutInflater$Factory2C3156j8) a3;
        if (j8Var.w0 != i) {
            j8Var.w0 = i;
            j8Var.d();
        }
        return a2;
    }

    public C2246dp1(Context context, int i) {
        super(context, i);
        this.c = j(context);
    }
}
