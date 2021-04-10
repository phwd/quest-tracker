package X;

import android.database.DataSetObserver;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;

/* renamed from: X.0rL  reason: invalid class name */
public class AnonymousClass0rL extends DataSetObserver implements AnonymousClass0Co, AnonymousClass0Cn {
    public int A00;
    public final /* synthetic */ PagerTitleStrip A01;

    public AnonymousClass0rL(PagerTitleStrip pagerTitleStrip) {
        this.A01 = pagerTitleStrip;
    }

    @Override // X.AnonymousClass0Cn
    public final void A5g(ViewPager viewPager, AbstractC00660Cc r3, AbstractC00660Cc r4) {
        this.A01.updateAdapter(r3, r4);
    }

    @Override // X.AnonymousClass0Co
    public final void A6P(int i, float f, int i2) {
        if (f > 0.5f) {
            i++;
        }
        this.A01.updateTextPositions(i, f, false);
    }

    @Override // X.AnonymousClass0Co
    public final void A6Q(int i) {
        if (this.A00 == 0) {
            PagerTitleStrip pagerTitleStrip = this.A01;
            pagerTitleStrip.updateText(pagerTitleStrip.mPager.mCurItem, null);
            float f = pagerTitleStrip.mLastKnownPositionOffset;
            float f2 = 0.0f;
            if (f >= 0.0f) {
                f2 = f;
            }
            pagerTitleStrip.updateTextPositions(pagerTitleStrip.mPager.mCurItem, f2, true);
        }
    }

    public final void onChanged() {
        PagerTitleStrip pagerTitleStrip = this.A01;
        pagerTitleStrip.updateText(pagerTitleStrip.mPager.mCurItem, null);
        float f = pagerTitleStrip.mLastKnownPositionOffset;
        float f2 = 0.0f;
        if (f >= 0.0f) {
            f2 = f;
        }
        pagerTitleStrip.updateTextPositions(pagerTitleStrip.mPager.mCurItem, f2, true);
    }

    @Override // X.AnonymousClass0Co
    public final void A6O(int i) {
        this.A00 = i;
    }
}
