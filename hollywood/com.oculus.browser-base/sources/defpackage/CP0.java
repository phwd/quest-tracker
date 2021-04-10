package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import java.util.Objects;

/* renamed from: CP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class CP0 implements Q31 {
    public final GP0 F;
    public final Context G;
    public final ViewGroup H;
    public final int I;

    public CP0(GP0 gp0, Context context, ViewGroup viewGroup, int i) {
        this.F = gp0;
        this.G = context;
        this.H = viewGroup;
        this.I = i;
    }

    @Override // defpackage.Q31
    public Object get() {
        GP0 gp0 = this.F;
        Context context = this.G;
        ViewGroup viewGroup = this.H;
        int i = this.I;
        Objects.requireNonNull(gp0);
        return new NP0(context, viewGroup, i, gp0.b);
    }
}
