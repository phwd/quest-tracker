package defpackage;

import org.chromium.url.GURL;

/* renamed from: Ol0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0885Ol0 implements Runnable {
    public final C1190Tl0 F;
    public final GURL G;

    public RunnableC0885Ol0(C1190Tl0 tl0, GURL gurl) {
        this.F = tl0;
        this.G = gurl;
    }

    public void run() {
        C1190Tl0 tl0 = this.F;
        GURL gurl = this.G;
        AbstractC5531x31 x31 = tl0.c;
        String h = gurl.h();
        C2379ed edVar = (C2379ed) x31;
        if (!edVar.c0) {
            edVar.c0 = true;
            ((C3909na0) edVar.G).h(h);
        }
    }
}
