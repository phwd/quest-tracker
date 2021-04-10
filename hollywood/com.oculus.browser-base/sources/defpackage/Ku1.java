package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2;

/* renamed from: Ku1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ku1 implements KK0 {
    public Ku1(ViewPager2 viewPager2) {
    }

    @Override // defpackage.KK0
    public void b(View view) {
        JK0 jk0 = (JK0) view.getLayoutParams();
        if (((ViewGroup.MarginLayoutParams) jk0).width != -1 || ((ViewGroup.MarginLayoutParams) jk0).height != -1) {
            throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
        }
    }

    @Override // defpackage.KK0
    public void d(View view) {
    }
}
