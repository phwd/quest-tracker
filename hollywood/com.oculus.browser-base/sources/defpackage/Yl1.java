package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Yl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Yl1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2067cm1 f9295a;

    public Yl1(C2067cm1 cm1) {
        this.f9295a = cm1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2067cm1 cm1 = this.f9295a;
        Tab tab = (Tab) obj;
        Objects.requireNonNull(cm1);
        if (tab != null) {
            cm1.d();
            cm1.c(tab);
            cm1.a();
        }
    }
}
