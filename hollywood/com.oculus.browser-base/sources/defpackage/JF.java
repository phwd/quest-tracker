package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* renamed from: JF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JF implements AbstractC4968tm0, AbstractC4371qE {
    public final Context F;
    public final AbstractC4448qj G;
    public final AbstractC2400ek H;
    public final CompositorViewHolder I;

    /* renamed from: J  reason: collision with root package name */
    public final C1595a3 f8279J;
    public final AbstractC0124Ca1 K;
    public int L;
    public AbstractC1305Vi0 M;
    public Runnable N;
    public DQ O;

    public JF(Context context, int i, AbstractC1305Vi0 vi0, Runnable runnable, AbstractC0124Ca1 ca1, DQ dq, AbstractC4448qj qjVar, AbstractC2400ek ekVar, CompositorViewHolder compositorViewHolder, C1595a3 a3Var) {
        this.F = context;
        this.L = i;
        this.M = vi0;
        this.N = runnable;
        this.K = ca1;
        this.O = dq;
        this.G = qjVar;
        this.H = ekVar;
        this.I = compositorViewHolder;
        this.f8279J = a3Var;
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
    }

    @Override // defpackage.AbstractC4968tm0
    public void s() {
        AppHooks.get().c();
    }
}
