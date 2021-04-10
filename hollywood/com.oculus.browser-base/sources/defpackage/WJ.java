package defpackage;

import java.util.Objects;
import org.chromium.ui.base.Clipboard;

/* renamed from: WJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class WJ implements Runnable {
    public final YJ F;

    public WJ(YJ yj) {
        this.F = yj;
    }

    public void run() {
        YJ yj = this.F;
        Objects.requireNonNull(yj);
        AbstractC3535lK0.a("Omnibox.EditUrlSuggestion.Copy");
        Clipboard.getInstance().b(yj.m.h());
    }
}
