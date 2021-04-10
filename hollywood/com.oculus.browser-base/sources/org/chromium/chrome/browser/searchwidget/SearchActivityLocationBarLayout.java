package org.chromium.chrome.browser.searchwidget;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SearchActivityLocationBarLayout extends AbstractView$OnClickListenerC5272va0 {
    public static final /* synthetic */ int g0 = 0;
    public AbstractC3035iQ0 h0;
    public boolean i0;
    public boolean j0;
    public boolean k0;
    public boolean l0;

    public SearchActivityLocationBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.layout.f39160_resource_name_obfuscated_RES_2131624225);
        Resources resources = getResources();
        int i = ToolbarPhone.U;
        Drawable c = AbstractC3153j7.c(resources, R.drawable.f33780_resource_name_obfuscated_RES_2131231418);
        c.mutate();
        c.setColorFilter(resources.getColor(R.color.f15380_resource_name_obfuscated_RES_2131100228), PorterDuff.Mode.SRC_IN);
        setBackground(c);
        this.H = true;
    }

    public void A(boolean z) {
        Objects.requireNonNull(this.K);
        N.MjJ0r9e$();
        boolean b = this.d0.b();
        Objects.requireNonNull(AbstractC3378kR0.a());
        PU0 pu0 = NU0.f8549a;
        if (pu0.d("org.chromium.chrome.browser.searchwidget.IS_VOICE_SEARCH_AVAILABLE", true) != b) {
            pu0.m("org.chromium.chrome.browser.searchwidget.IS_VOICE_SEARCH_AVAILABLE", b);
            AbstractC3378kR0.b(null);
        }
        if (z && this.I.isFocused()) {
            super.j(true);
            q(false);
        }
        this.i0 = false;
        this.K.H.a0 = false;
        if (!TextUtils.isEmpty(this.f11487J.g())) {
            C0859Oc oc = this.K;
            oc.H.o(this.f11487J.h());
        }
        if (this.j0) {
            y(z);
            this.j0 = false;
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void a() {
        SearchActivity searchActivity = (SearchActivity) this.h0;
        searchActivity.finish();
        searchActivity.overridePendingTransition(0, R.anim.f130_resource_name_obfuscated_RES_2130771981);
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void f(C0859Oc oc, Oq1 oq1, R11 r11, AbstractC4422qa0 qa0, Uy1 uy1, WindowAndroid windowAndroid, Sv1 sv1, AbstractC1509Ys0 ys0) {
        super.f(oc, oq1, r11, qa0, uy1, windowAndroid, sv1, ys0);
        boolean b = LocaleManager.getInstance().b();
        this.i0 = b;
        this.K.H.a0 = b;
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void h() {
        super.h();
        boolean b = LocaleManager.getInstance().b();
        this.i0 = b;
        this.K.H.a0 = b;
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void j(boolean z) {
        super.j(z);
        if (z) {
            q(false);
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void m(String str) {
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.k0 = z;
        if (z) {
            z();
        } else {
            this.I.clearFocus();
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void t() {
        super.t();
        v();
        findViewById(R.id.url_action_container).setVisibility(0);
    }

    public final void y(boolean z) {
        if (!this.d0.b() || !z) {
            this.l0 |= !this.I.hasFocus();
            z();
            new Handler().post(new RunnableC2864hQ0(this));
            return;
        }
        this.d0.h(2);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:50:0x010b */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.util.List] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void z() {
        /*
        // Method dump skipped, instructions count: 415
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.searchwidget.SearchActivityLocationBarLayout.z():void");
    }
}
