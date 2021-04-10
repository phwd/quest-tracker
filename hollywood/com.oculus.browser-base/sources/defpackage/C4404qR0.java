package defpackage;

import android.animation.ValueAnimator;
import java.util.Objects;
import org.chromium.chrome.browser.ntp.snippets.SectionHeaderView;

/* renamed from: qR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4404qR0 implements ValueAnimator.AnimatorUpdateListener {
    public final SectionHeaderView F;

    public C4404qR0(SectionHeaderView sectionHeaderView) {
        this.F = sectionHeaderView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        SectionHeaderView sectionHeaderView = this.F;
        Objects.requireNonNull(sectionHeaderView);
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        sectionHeaderView.setPadding(intValue, sectionHeaderView.getPaddingTop(), intValue, sectionHeaderView.getPaddingBottom());
    }
}
