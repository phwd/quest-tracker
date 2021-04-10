package defpackage;

import android.view.View;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: T71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class T71 implements View.OnClickListener {
    public final C2475f81 F;

    public T71(C2475f81 f81) {
        this.F = f81;
    }

    public void onClick(View view) {
        C2475f81 f81 = this.F;
        Tab j = ((AbstractC0246Ea1) f81.e).j();
        if (j != null) {
            AbstractC2304e81 e81 = f81.d;
            List f = f81.f(j.getId());
            I61 i61 = ((O71) e81).N;
            if (i61 != null) {
                i61.e(f);
            }
            AbstractC3535lK0.a("TabGroup.ExpandedFromStrip.TabGridDialog");
        }
    }
}
