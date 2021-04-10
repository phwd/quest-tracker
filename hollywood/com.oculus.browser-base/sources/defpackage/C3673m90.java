package defpackage;

import android.widget.AbsListView;

/* renamed from: m90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3673m90 implements AbsListView.OnScrollListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC4186p90 f10401a;

    public C3673m90(AbstractC4186p90 p90) {
        this.f10401a = p90;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        boolean z = true;
        if (i == 1) {
            if (this.f10401a.g0.getInputMethodMode() != 2) {
                z = false;
            }
            if (!z && this.f10401a.g0.getContentView() != null) {
                AbstractC4186p90 p90 = this.f10401a;
                p90.c0.removeCallbacks(p90.Y);
                this.f10401a.Y.run();
            }
        }
    }
}
