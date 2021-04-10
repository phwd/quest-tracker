package defpackage;

import android.view.View;
import org.chromium.chrome.browser.tasks.tab_management.SelectableTabGridView;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: C71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C71 implements View.OnLongClickListener {
    public final UH0 F;
    public final int G;
    public final ViewLookupCachingFrameLayout H;

    public C71(UH0 uh0, int i, ViewLookupCachingFrameLayout viewLookupCachingFrameLayout) {
        this.F = uh0;
        this.G = i;
        this.H = viewLookupCachingFrameLayout;
    }

    public boolean onLongClick(View view) {
        UH0 uh0 = this.F;
        int i = this.G;
        ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = this.H;
        ((D91) uh0.g(AbstractC5106ub1.l)).a(i);
        return ((SelectableTabGridView) viewLookupCachingFrameLayout).onLongClick(viewLookupCachingFrameLayout);
    }
}
