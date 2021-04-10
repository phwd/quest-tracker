package X;

import androidx.viewpager.widget.ViewPager;

/* renamed from: X.0Cj  reason: invalid class name and case insensitive filesystem */
public class RunnableC00690Cj implements Runnable {
    public static final String __redex_internal_original_name = "androidx.viewpager.widget.ViewPager$3";
    public final /* synthetic */ ViewPager A00;

    public RunnableC00690Cj(ViewPager viewPager) {
        this.A00 = viewPager;
    }

    public final void run() {
        ViewPager viewPager = this.A00;
        viewPager.setScrollState(0);
        viewPager.populate();
    }
}
