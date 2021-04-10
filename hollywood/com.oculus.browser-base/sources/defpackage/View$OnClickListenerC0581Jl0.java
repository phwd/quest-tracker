package defpackage;

import android.view.ContextMenu;
import android.view.View;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Jl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC0581Jl0 implements View.OnClickListener, View.OnCreateContextMenuListener {
    public C0815Nh1 F;
    public Q31 G;

    public View$OnClickListenerC0581Jl0(C0815Nh1 nh1, Q31 q31) {
        this.F = nh1;
        this.G = q31;
    }

    public void onClick(View view) {
        EM0.g(this.F.f8567a.b.h(), 2, null, (Tab) this.G.get());
        AbstractC3535lK0.a("Suggestions.Tile.Tapped");
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
    }
}
