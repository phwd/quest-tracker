package org.chromium.chrome.browser.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabSwitcherButtonView extends ListMenuButton {
    public C1410Xc1 P;

    public TabSwitcherButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.components.browser_ui.widget.listmenu.ListMenuButton
    public void onFinishInflate() {
        super.onFinishInflate();
        C1410Xc1 e = C1410Xc1.e(getContext(), false);
        this.P = e;
        setImageDrawable(e);
    }
}
