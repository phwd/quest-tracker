package defpackage;

import java.util.Objects;

/* renamed from: XJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class XJ implements Runnable {
    public final YJ F;

    public XJ(YJ yj) {
        this.F = yj;
    }

    public void run() {
        YJ yj = this.F;
        Objects.requireNonNull(yj);
        AbstractC3535lK0.a("Omnibox.EditUrlSuggestion.Edit");
        ((C3909na0) yj.h).h(yj.m.h());
    }
}
