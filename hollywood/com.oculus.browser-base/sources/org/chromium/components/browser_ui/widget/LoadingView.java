package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.firstrun.TosAndUmaFirstRunFragmentWithEnterpriseSupport;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LoadingView extends ProgressBar {
    public static final /* synthetic */ int F = 0;
    public long G = -1;
    public final List H = new ArrayList();
    public final Runnable I = new N90(this);

    /* renamed from: J  reason: collision with root package name */
    public boolean f10819J;
    public final Runnable K = new P90(this);

    public LoadingView(Context context) {
        super(context);
    }

    public void a() {
        removeCallbacks(this.I);
        removeCallbacks(this.K);
        this.f10819J = false;
        if (getVisibility() == 0) {
            postDelayed(this.K, Math.max(0L, (this.G + 500) - SystemClock.elapsedRealtime()));
        } else {
            b();
        }
    }

    public final void b() {
        setVisibility(8);
        for (Q90 q90 : this.H) {
            TosAndUmaFirstRunFragmentWithEnterpriseSupport tosAndUmaFirstRunFragmentWithEnterpriseSupport = (TosAndUmaFirstRunFragmentWithEnterpriseSupport) q90;
            Objects.requireNonNull(tosAndUmaFirstRunFragmentWithEnterpriseSupport);
            AbstractC3364kK0.k("MobileFre.CctTos.LoadingDuration", SystemClock.elapsedRealtime() - tosAndUmaFirstRunFragmentWithEnterpriseSupport.M0);
            boolean isAccessibilityFocused = tosAndUmaFirstRunFragmentWithEnterpriseSupport.H0.isAccessibilityFocused();
            tosAndUmaFirstRunFragmentWithEnterpriseSupport.H0.setVisibility(8);
            if (tosAndUmaFirstRunFragmentWithEnterpriseSupport.K0.get().booleanValue()) {
                tosAndUmaFirstRunFragmentWithEnterpriseSupport.m1(isAccessibilityFocused);
            } else {
                tosAndUmaFirstRunFragmentWithEnterpriseSupport.G0.setVisibility(0);
                tosAndUmaFirstRunFragmentWithEnterpriseSupport.l1(true);
                if (isAccessibilityFocused) {
                    tosAndUmaFirstRunFragmentWithEnterpriseSupport.B0.sendAccessibilityEvent(8);
                }
            }
        }
    }

    public void c() {
        removeCallbacks(this.I);
        removeCallbacks(this.K);
        this.f10819J = true;
        setVisibility(8);
        postDelayed(this.I, 500);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
