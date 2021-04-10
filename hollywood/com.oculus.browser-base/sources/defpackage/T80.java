package defpackage;

import android.widget.PopupWindow;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* renamed from: T80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class T80 implements PopupWindow.OnDismissListener {
    public final ListMenuButton F;

    public T80(ListMenuButton listMenuButton) {
        this.F = listMenuButton;
    }

    public void onDismiss() {
        this.F.L = null;
    }
}
