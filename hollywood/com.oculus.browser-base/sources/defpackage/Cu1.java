package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.viewpager.widget.ViewPager;

/* renamed from: Cu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Cu1 extends C5349w {
    public final /* synthetic */ ViewPager d;

    public Cu1(ViewPager viewPager) {
        this.d = viewPager;
    }

    @Override // defpackage.C5349w
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        AbstractC0966Pv0 pv0;
        this.b.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName("androidx.viewpager.widget.ViewPager");
        AbstractC0966Pv0 pv02 = this.d.N;
        boolean z = true;
        if (pv02 == null || pv02.e() <= 1) {
            z = false;
        }
        accessibilityEvent.setScrollable(z);
        if (accessibilityEvent.getEventType() == 4096 && (pv0 = this.d.N) != null) {
            accessibilityEvent.setItemCount(pv0.e());
            accessibilityEvent.setFromIndex(this.d.O);
            accessibilityEvent.setToIndex(this.d.O);
        }
    }

    @Override // defpackage.C5349w
    public void d(View view, D d2) {
        this.b.onInitializeAccessibilityNodeInfo(view, d2.b);
        d2.b.setClassName("androidx.viewpager.widget.ViewPager");
        AbstractC0966Pv0 pv0 = this.d.N;
        d2.b.setScrollable(pv0 != null && pv0.e() > 1);
        if (this.d.canScrollHorizontally(1)) {
            d2.b.addAction(4096);
        }
        if (this.d.canScrollHorizontally(-1)) {
            d2.b.addAction(8192);
        }
    }

    @Override // defpackage.C5349w
    public boolean g(View view, int i, Bundle bundle) {
        if (super.g(view, i, bundle)) {
            return true;
        }
        if (i != 4096) {
            if (i != 8192 || !this.d.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager = this.d;
            viewPager.x(viewPager.O - 1);
            return true;
        } else if (!this.d.canScrollHorizontally(1)) {
            return false;
        } else {
            ViewPager viewPager2 = this.d;
            viewPager2.x(viewPager2.O + 1);
            return true;
        }
    }
}
