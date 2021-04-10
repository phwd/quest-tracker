package defpackage;

import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: eN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2344eN implements Runnable {
    public final /* synthetic */ LoadUrlParams F;
    public final /* synthetic */ C3198jN G;

    public RunnableC2344eN(C3198jN jNVar, LoadUrlParams loadUrlParams) {
        this.G = jNVar;
        this.F = loadUrlParams;
    }

    public void run() {
        AbstractC1652aN aNVar = this.G.f10201a;
        LoadUrlParams loadUrlParams = this.F;
        C2003cN cNVar = (C2003cN) aNVar;
        if (cNVar.m()) {
            cNVar.b.c(loadUrlParams);
        }
    }
}
