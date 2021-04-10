package X;

import android.view.View;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

/* renamed from: X.0Ce  reason: invalid class name */
public class AnonymousClass0Ce implements View.OnClickListener {
    public final /* synthetic */ PagerTabStrip A00;

    public AnonymousClass0Ce(PagerTabStrip pagerTabStrip) {
        this.A00 = pagerTabStrip;
    }

    public final void onClick(View view) {
        ViewPager viewPager = this.A00.mPager;
        viewPager.setCurrentItem(viewPager.mCurItem + 1);
    }
}
