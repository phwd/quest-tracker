package defpackage;

import android.view.View;
import org.chromium.chrome.browser.tasks.tab_management.SelectableTabGridView;

/* renamed from: T91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class T91 implements View.OnClickListener {
    public final UH0 F;
    public final int G;
    public final SelectableTabGridView H;

    public T91(UH0 uh0, int i, SelectableTabGridView selectableTabGridView) {
        this.F = uh0;
        this.G = i;
        this.H = selectableTabGridView;
    }

    public void onClick(View view) {
        UH0 uh0 = this.F;
        int i = this.G;
        SelectableTabGridView selectableTabGridView = this.H;
        ((D91) uh0.g(AbstractC5106ub1.l)).a(i);
        selectableTabGridView.f();
    }
}
