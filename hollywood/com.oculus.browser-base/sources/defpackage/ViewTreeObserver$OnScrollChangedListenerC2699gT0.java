package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: gT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnScrollChangedListenerC2699gT0 implements ViewTreeObserver.OnScrollChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View f10001a;
    public final /* synthetic */ View b;

    public ViewTreeObserver$OnScrollChangedListenerC2699gT0(View view, View view2) {
        this.f10001a = view;
        this.b = view2;
    }

    public void onScrollChanged() {
        if (!this.f10001a.canScrollVertically(-1)) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
        }
    }
}
