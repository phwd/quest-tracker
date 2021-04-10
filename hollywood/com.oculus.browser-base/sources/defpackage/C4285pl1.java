package defpackage;

import android.animation.TimeAnimator;

/* renamed from: pl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4285pl1 implements TimeAnimator.TimeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4456ql1 f11085a;

    public C4285pl1(C4456ql1 ql1) {
        this.f11085a = ql1;
    }

    public void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2) {
        float f;
        C4456ql1 ql1 = this.f11085a;
        if (!AbstractC4089od0.a(ql1.H, ql1.K)) {
            C4456ql1 ql12 = this.f11085a;
            C3697mH0 mh0 = ql12.L;
            float f2 = ql12.K;
            float min = ((float) Math.min(j2, 50L)) * 0.001f;
            int width = this.f11085a.getWidth();
            int i = (f2 > 1.0f ? 1 : (f2 == 1.0f ? 0 : -1));
            if (i == 0) {
                f = min;
            } else {
                float f3 = mh0.b;
                f = Math.max(0.0f, Math.min(min, (-6.6666665f * f3) + ((float) Math.sqrt((double) ((22.222221f * f3 * f3) + ((f2 - mh0.f10411a) * 6.6666665f))))));
            }
            float f4 = min - f;
            if (f > 0.0f) {
                float f5 = (i == 0 ? 7.0f : 0.15f) * f;
                float f6 = mh0.f10411a;
                float f7 = mh0.b;
                mh0.f10411a = (((f5 * 0.5f) + f7) * f) + f6;
                mh0.b = f7 + f5;
            }
            if (f4 > 0.0f) {
                float f8 = -0.15f * f4;
                float f9 = mh0.f10411a;
                float f10 = mh0.b;
                mh0.f10411a = (((f8 * 0.5f) + f10) * f4) + f9;
                mh0.b = f10 + f8;
            }
            float min2 = Math.min(mh0.f10411a, f2);
            mh0.f10411a = min2;
            if (f2 - min2 < 0.5f / ((float) width)) {
                mh0.f10411a = f2;
                mh0.b = 0.0f;
            }
            float max = Math.max(mh0.f10411a, 0.0f);
            C4285pl1.super.a(max);
            C4456ql1 ql13 = this.f11085a;
            if (ql13.S != null) {
                this.f11085a.S.a(max * ((float) Math.abs(ql13.getDrawable().getBounds().right - this.f11085a.getDrawable().getBounds().left)));
            }
            if (AbstractC4089od0.a(this.f11085a.H, 1.0f)) {
                this.f11085a.e(true);
            }
        }
    }
}
