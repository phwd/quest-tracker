package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.oculus.browser.R;
import org.chromium.chrome.browser.ntp.snippets.SectionHeaderView;

/* renamed from: rR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4574rR0 extends AnimatorListenerAdapter {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ SectionHeaderView G;

    public C4574rR0(SectionHeaderView sectionHeaderView, boolean z) {
        this.G = sectionHeaderView;
        this.F = z;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.F) {
            this.G.setBackgroundResource(R.drawable.f29410_resource_name_obfuscated_RES_2131230981);
        }
    }
}
