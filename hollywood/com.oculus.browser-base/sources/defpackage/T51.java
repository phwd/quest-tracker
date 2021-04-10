package defpackage;

import android.text.TextUtils;
import android.view.KeyEvent;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: T51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class T51 extends AbstractC0499Id1 implements WZ {
    public String G;

    public T51(Tab tab) {
        super(tab);
        tab.A(new S51(this));
    }

    public static T51 j(Tab tab) {
        T51 t51 = (T51) tab.M().c(T51.class);
        return t51 == null ? (T51) tab.M().e(T51.class, new T51(tab)) : t51;
    }

    public static String k(Tab tab) {
        T51 t51 = (T51) tab.M().c(T51.class);
        if (t51 != null) {
            return t51.G;
        }
        return null;
    }

    public static boolean l(Tab tab) {
        T51 t51 = (T51) tab.M().c(T51.class);
        if (t51 == null) {
            return false;
        }
        String packageName = ContextUtils.getApplicationContext().getPackageName();
        if (tab.F() != 1 || TextUtils.equals(t51.G, packageName)) {
            return false;
        }
        return true;
    }

    @Override // defpackage.WZ
    public void a() {
        this.G = null;
    }

    @Override // defpackage.AbstractC0499Id1
    public void c(WebContents webContents) {
    }

    @Override // defpackage.WZ
    public void d(KeyEvent keyEvent) {
    }

    @Override // defpackage.AbstractC0499Id1
    public void h(WebContents webContents) {
        ImeAdapterImpl.s0(webContents).N.add(this);
    }

    @Override // defpackage.WZ
    public void i(boolean z, boolean z2) {
    }
}
