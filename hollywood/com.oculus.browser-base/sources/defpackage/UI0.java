package defpackage;

import org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: UI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UI0 implements C61 {

    /* renamed from: a  reason: collision with root package name */
    public final String f9021a;
    public final int b;

    public UI0(String str, int i) {
        this.f9021a = str;
        this.b = i;
    }

    @Override // defpackage.C61
    public AbstractC0133Cd1 a(Tab tab) {
        return new TI0(tab, this.f9021a, this.b);
    }

    @Override // defpackage.C61
    public AbstractC5818ym0 b(String str, AbstractC5818ym0 ym0, Tab tab) {
        return null;
    }

    @Override // defpackage.C61
    public C3198jN c(Tab tab) {
        return new C3198jN(new C2003cN(tab));
    }

    @Override // defpackage.C61
    public AbstractC2742gk d(Tab tab) {
        return null;
    }

    @Override // defpackage.C61
    public ContextMenuPopulatorFactory e(Tab tab) {
        return null;
    }
}
