package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Objects;
import org.chromium.chrome.browser.tasks.tab_management.ClosableTabGridView;

/* renamed from: uv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5164uv extends AnimatorListenerAdapter {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ View G;
    public final /* synthetic */ View H;
    public final /* synthetic */ boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ ClosableTabGridView f11446J;

    public C5164uv(ClosableTabGridView closableTabGridView, boolean z, View view, View view2, boolean z2) {
        this.f11446J = closableTabGridView;
        this.F = z;
        this.G = view;
        this.H = view2;
        this.I = z2;
    }

    public void onAnimationEnd(Animator animator) {
        if (!this.F) {
            this.G.setVisibility(8);
        }
        ClosableTabGridView closableTabGridView = this.f11446J;
        WeakReference weakReference = ClosableTabGridView.I;
        Objects.requireNonNull(closableTabGridView);
    }
}
