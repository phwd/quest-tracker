package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;

/* renamed from: Mz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnGlobalFocusChangeListenerC0790Mz implements ViewTreeObserver.OnGlobalFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View f8516a;
    public final /* synthetic */ ContextualSearchManager b;

    public ViewTreeObserver$OnGlobalFocusChangeListenerC0790Mz(ContextualSearchManager contextualSearchManager, View view) {
        this.b = contextualSearchManager;
        this.f8516a = view;
    }

    public void onGlobalFocusChanged(View view, View view2) {
        View view3 = this.f8516a;
        if (view3 != null && view3.hasFocus()) {
            this.b.i(0);
        }
    }
}
