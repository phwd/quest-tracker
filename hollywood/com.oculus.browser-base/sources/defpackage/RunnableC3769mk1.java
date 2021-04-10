package defpackage;

import java.util.Objects;
import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: mk1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3769mk1 implements Runnable {
    public final Uk1 F;

    public RunnableC3769mk1(Uk1 uk1) {
        this.F = uk1;
    }

    public void run() {
        Uk1 uk1 = this.F;
        Objects.requireNonNull(uk1);
        TemplateUrlService a2 = AbstractC0444Hf1.a();
        Hk1 hk1 = new Hk1(uk1, a2);
        uk1.c0 = hk1;
        a2.b.b(hk1);
    }
}
