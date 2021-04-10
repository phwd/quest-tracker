package defpackage;

import android.view.View;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: t81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnAttachStateChangeListenerC4866t81 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ TabImpl F;

    public View$OnAttachStateChangeListenerC4866t81(TabImpl tabImpl) {
        this.F = tabImpl;
    }

    public void onViewAttachedToWindow(View view) {
        TabImpl tabImpl = this.F;
        tabImpl.h0 = true;
        tabImpl.l0();
    }

    public void onViewDetachedFromWindow(View view) {
        TabImpl tabImpl = this.F;
        tabImpl.h0 = false;
        tabImpl.l0();
    }
}
