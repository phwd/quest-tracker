package org.chromium.chrome.browser.firstrun;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.policy.EnterpriseInfo;
import org.chromium.components.browser_ui.widget.LoadingView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TosAndUmaFirstRunFragmentWithEnterpriseSupport extends ToSAndUMAFirstRunFragment implements Q90 {
    public boolean F0;
    public View G0;
    public View H0;
    public LoadingView I0;
    public TextView J0;
    public WX0 K0;
    public Handler L0;
    public long M0;

    public TosAndUmaFirstRunFragmentWithEnterpriseSupport() {
        new C5232vH0();
        new C1881bh1();
    }

    @Override // org.chromium.chrome.browser.firstrun.ToSAndUMAFirstRunFragment, defpackage.AbstractComponentCallbacksC3550lS
    public void F0(View view, Bundle bundle) {
        super.F0(view, bundle);
        this.G0 = view.findViewById(R.id.fre_bottom_group);
        this.H0 = view.findViewById(R.id.loading_view_container);
        this.I0 = (LoadingView) view.findViewById(R.id.progress_spinner_large);
        this.J0 = (TextView) view.findViewById(R.id.privacy_disclaimer);
        this.F0 = true;
        this.M0 = SystemClock.elapsedRealtime();
        if (this.K0.get() == null) {
            this.I0.H.add(this);
            this.I0.c();
            this.G0.setVisibility(8);
            l1(false);
        } else if (this.K0.get().booleanValue()) {
            this.G0.setVisibility(8);
            l1(false);
            m1(false);
        }
    }

    @Override // org.chromium.chrome.browser.firstrun.ToSAndUMAFirstRunFragment, defpackage.AbstractComponentCallbacksC3550lS
    public void e0(Context context) {
        super.e0(context);
        WX0 wx0 = new WX0(TQ.a(this).G(), EnterpriseInfo.b(), new C3946nm1(this, null));
        this.K0 = wx0;
        wx0.g(new C3433km1(this));
    }

    @Override // org.chromium.chrome.browser.firstrun.ToSAndUMAFirstRunFragment
    public boolean e1() {
        return this.K0.get() != null && !this.K0.get().booleanValue();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        LoadingView loadingView = this.I0;
        if (loadingView != null) {
            loadingView.removeCallbacks(loadingView.I);
            loadingView.removeCallbacks(loadingView.K);
            loadingView.H.clear();
            this.I0 = null;
        }
        WX0 wx0 = this.K0;
        if (wx0 != null) {
            wx0.a();
            this.K0 = null;
        }
        Handler handler = this.L0;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.L0 = null;
        }
        this.i0 = true;
    }

    public final void m1(boolean z) {
        this.J0.setVisibility(0);
        if (z) {
            this.J0.sendAccessibilityEvent(8);
        } else {
            TextView textView = this.J0;
            textView.announceForAccessibility(textView.getText());
        }
        RunnableC3604lm1 lm1 = new RunnableC3604lm1(this);
        Handler handler = new Handler(ThreadUtils.c());
        this.L0 = handler;
        int i = 1000;
        if (C0283Ep.h().i()) {
            i = 2000;
        }
        handler.postDelayed(lm1, (long) i);
    }

    public final void n1() {
        if (this.F0) {
            this.I0.a();
        }
    }
}
