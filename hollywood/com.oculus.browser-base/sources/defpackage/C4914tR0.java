package defpackage;

import android.view.View;
import org.chromium.chrome.browser.ntp.snippets.SectionHeaderView;

/* renamed from: tR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4914tR0 extends V80 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1237Ug f11342a;

    public C4914tR0(SectionHeaderView sectionHeaderView, C1237Ug ug) {
        this.f11342a = ug;
    }

    @Override // defpackage.V80
    public C1237Ug a() {
        return this.f11342a;
    }

    @Override // defpackage.V80
    public C4390qK0 b(View view) {
        ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 fv1 = new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(view);
        fv1.L = true;
        fv1.e(0, 0, 0, 0);
        return fv1;
    }
}
