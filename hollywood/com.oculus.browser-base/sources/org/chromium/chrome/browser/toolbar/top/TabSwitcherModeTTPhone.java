package org.chromium.chrome.browser.toolbar.top;

import J.N;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewStub;
import com.oculus.browser.R;
import org.chromium.chrome.browser.toolbar.IncognitoToggleTabLayout;
import org.chromium.chrome.browser.toolbar.NewTabButton;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabSwitcherModeTTPhone extends OptimizedFrameLayout implements View.OnClickListener, C00 {
    public View.OnClickListener G;
    public C5880z61 H;
    public AbstractC0124Ca1 I;

    /* renamed from: J  reason: collision with root package name */
    public D00 f10789J;
    public IncognitoToggleTabLayout K;
    public View L;
    public NewTabButton M;
    public ToggleTabStackButton N;
    public int O;
    public boolean P;
    public ColorStateList Q;
    public boolean R;
    public boolean S;
    public ObjectAnimator T;

    public TabSwitcherModeTTPhone(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.C00
    public void c(boolean z) {
        this.R = z;
        i();
    }

    public void d(boolean z) {
        NewTabButton newTabButton = this.M;
        if (newTabButton != null) {
            newTabButton.d();
        }
        if (N.M09VlOh_("HorizontalTabSwitcherAndroid") && N.M$3vpOHw()) {
            j(!z);
        }
        i();
    }

    public void e(boolean z) {
        if ((!z) != this.S) {
            boolean z2 = !z;
            this.S = z2;
            this.K.setVisibility(z2 ? 8 : 0);
            h();
        }
    }

    public void f(D00 d00) {
        this.f10789J = d00;
        d00.f7854a.b(this);
        this.R = d00.b();
        i();
        NewTabButton newTabButton = this.M;
        if (newTabButton != null) {
            D00 d002 = this.f10789J;
            newTabButton.K = d002;
            d002.f7854a.b(newTabButton);
            newTabButton.c(d002.b());
        }
    }

    public void g(boolean z) {
        ObjectAnimator objectAnimator = this.T;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        setVisibility(0);
        float f = 0.0f;
        setAlpha(z ? 0.0f : 1.0f);
        boolean z2 = AbstractC4772sd1.b() && AbstractC4772sd1.j();
        long j = z2 ? 150 : 200;
        Property property = View.ALPHA;
        float[] fArr = new float[1];
        if (z) {
            f = 1.0f;
        }
        fArr[0] = f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, property, fArr);
        this.T = ofFloat;
        ofFloat.setDuration(j);
        if (z2 && z) {
            this.T.setStartDelay(j);
        }
        this.T.setInterpolator(G30.d);
        if (!z) {
            IncognitoToggleTabLayout incognitoToggleTabLayout = this.K;
            if (incognitoToggleTabLayout != null) {
                incognitoToggleTabLayout.setClickable(false);
            }
        } else {
            NewTabButton newTabButton = this.M;
            if (newTabButton != null) {
                newTabButton.setEnabled(true);
            }
            View view = this.L;
            if (view != null) {
                view.setEnabled(true);
            }
        }
        this.T.addListener(new C4432qd1(this, z));
        this.T.start();
        if (C5052uE.a()) {
            this.T.end();
        }
    }

    public final void h() {
        View view = this.L;
        int i = 0;
        if (view != null) {
            view.setVisibility(this.S ? 0 : 8);
        }
        NewTabButton newTabButton = this.M;
        if (newTabButton != null) {
            if (this.S) {
                i = 8;
            }
            newTabButton.setVisibility(i);
        }
    }

    public final void i() {
        int i;
        boolean z;
        boolean z2 = false;
        if (C5052uE.a() || AbstractC4772sd1.b()) {
            i = AbstractC2934hr.b(getResources(), this.R);
        } else {
            i = 0;
        }
        if (this.O != i) {
            this.O = i;
            setBackgroundColor(i);
        }
        if (i == 0) {
            Resources resources = getResources();
            if (k() && this.R) {
                z2 = true;
            }
            z = AbstractC1270Uv.g(AbstractC2934hr.b(resources, z2));
        } else {
            z = AbstractC1270Uv.g(i);
        }
        if (this.P != z) {
            this.P = z;
            if (this.Q == null) {
                Context context = getContext();
                ThreadLocal threadLocal = AbstractC5544x8.f11592a;
                this.Q = context.getColorStateList(R.color.f11330_resource_name_obfuscated_RES_2131099823);
                getContext().getColorStateList(R.color.f11390_resource_name_obfuscated_RES_2131099829);
            }
            ToggleTabStackButton toggleTabStackButton = this.N;
            if (toggleTabStackButton != null) {
                toggleTabStackButton.i(z);
            }
        }
    }

    public final void j(boolean z) {
        IncognitoToggleTabLayout incognitoToggleTabLayout = this.K;
        int i = 0;
        if (incognitoToggleTabLayout != null) {
            incognitoToggleTabLayout.setVisibility(z ? 0 : 8);
        } else if (z) {
            IncognitoToggleTabLayout incognitoToggleTabLayout2 = (IncognitoToggleTabLayout) ((ViewStub) findViewById(R.id.incognito_tabs_stub)).inflate();
            this.K = incognitoToggleTabLayout2;
            C5880z61 z61 = this.H;
            if (z61 != null) {
                incognitoToggleTabLayout2.H0 = z61;
                z61.a(incognitoToggleTabLayout2);
            }
            AbstractC0124Ca1 ca1 = this.I;
            if (ca1 != null) {
                this.K.x(ca1);
            }
        }
        boolean z2 = !z;
        ToggleTabStackButton toggleTabStackButton = this.N;
        if (toggleTabStackButton != null) {
            if (!z2) {
                i = 8;
            }
            toggleTabStackButton.setVisibility(i);
        }
    }

    public final boolean k() {
        return !C5052uE.a() && N.M09VlOh_("HorizontalTabSwitcherAndroid");
    }

    public void onClick(View view) {
        if (this.M == view || this.L == view) {
            view.setEnabled(false);
            View.OnClickListener onClickListener = this.G;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.M = (NewTabButton) findViewById(R.id.new_tab_button);
        this.L = findViewById(R.id.new_tab_view);
        this.N = (ToggleTabStackButton) findViewById(R.id.tab_switcher_mode_tab_switcher_button);
        this.M.setOnClickListener(this);
        this.L.setOnClickListener(this);
        j((k() || AbstractC4772sd1.b()) && N.M$3vpOHw());
        h();
    }
}
