package org.chromium.chrome.browser.contextualsearch;

import J.N;
import java.util.regex.Pattern;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CtrSuppression extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public long f10644a = N.MCUfMprB(this);
    public final PU0 b = NU0.f8549a;

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return false;
    }

    @Override // defpackage.AbstractC5856yz
    public void c() {
        int MYeBiXTA = N.MYeBiXTA(this.f10644a, this);
        boolean z = false;
        if (this.b.f("contextual_search_current_week_number", 0) != MYeBiXTA) {
            this.b.n("contextual_search_current_week_number", MYeBiXTA);
            z = true;
        }
        if (z) {
            if (N.Mx0n4i9m(this.f10644a, this)) {
                int MpFnMiJA = N.MpFnMiJA(this.f10644a, this);
                Pattern pattern = AA.f7657a;
                AbstractC3364kK0.d("Search.ContextualSearchPreviousWeekImpressions", MpFnMiJA);
                AbstractC3364kK0.g("Search.ContextualSearchPreviousWeekCtr", (int) (N.M1uNswJh(this.f10644a, this) * 100.0f), 101);
            }
            if (N.McXNZl2s(this.f10644a, this)) {
                int MHNOba2r = N.MHNOba2r(this.f10644a, this);
                int M36jqK_X = (int) (N.M36jqK_X(this.f10644a, this) * 100.0f);
                Pattern pattern2 = AA.f7657a;
                AbstractC3364kK0.d("Search.ContextualSearchPrevious28DayImpressions", MHNOba2r);
                AbstractC3364kK0.g("Search.ContextualSearchPrevious28DayCtr", M36jqK_X, 101);
            }
        }
    }

    public final void clearNativePointer() {
        this.f10644a = 0;
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        if (N.Mx0n4i9m(this.f10644a, this)) {
            C4017oA oAVar = (C4017oA) hz;
            oAVar.b(8, Integer.valueOf(N.MpFnMiJA(this.f10644a, this)));
            oAVar.b(9, Integer.valueOf((int) (N.M1uNswJh(this.f10644a, this) * 100.0f)));
        }
        if (N.McXNZl2s(this.f10644a, this)) {
            C4017oA oAVar2 = (C4017oA) hz;
            oAVar2.b(10, Integer.valueOf(N.MHNOba2r(this.f10644a, this)));
            oAVar2.b(11, Integer.valueOf((int) (N.M36jqK_X(this.f10644a, this) * 100.0f)));
        }
    }

    @Override // defpackage.AbstractC5856yz
    public void g(boolean z, boolean z2) {
        if (z2) {
            N.MLHiFNw8(this.f10644a, this, z);
        }
    }

    public int readClicks(int i) {
        return this.b.e(AbstractC0533Is.f8253a.a(i));
    }

    public int readImpressions(int i) {
        return this.b.e(AbstractC0533Is.b.a(i));
    }

    public int readNewestWeek() {
        return this.b.f("contextual_search_newest_week", 0);
    }

    public int readOldestWeek() {
        return this.b.f("contextual_search_oldest_week", 0);
    }

    public void writeClicks(int i, int i2) {
        this.b.n(AbstractC0533Is.f8253a.a(i), i2);
    }

    public void writeImpressions(int i, int i2) {
        this.b.n(AbstractC0533Is.b.a(i), i2);
    }

    public void writeNewestWeek(int i) {
        this.b.n("contextual_search_newest_week", i);
    }

    public void writeOldestWeek(int i) {
        this.b.n("contextual_search_oldest_week", i);
    }
}
