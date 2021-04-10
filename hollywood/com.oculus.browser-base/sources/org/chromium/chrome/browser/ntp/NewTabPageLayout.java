package org.chromium.chrome.browser.ntp;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NewTabPageLayout extends LinearLayout implements AbstractC1303Vh1, AbstractC3976nw1 {
    public final int F = getResources().getDimensionPixelSize(R.dimen.f26040_resource_name_obfuscated_RES_2131166223);
    public int G = -1;
    public View H;
    public LogoView I;

    /* renamed from: J  reason: collision with root package name */
    public ViewGroup f10712J;
    public View K;
    public C1128Sl L = new C1128Sl();
    public boolean M;
    public boolean N = true;

    public NewTabPageLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.AbstractC1303Vh1
    public void a(C0815Nh1 nh1) {
        throw null;
    }

    @Override // defpackage.AbstractC1303Vh1
    public void b() {
        throw null;
    }

    @Override // defpackage.AbstractC1303Vh1
    public void c(C0815Nh1 nh1) {
        throw null;
    }

    @Override // defpackage.AbstractC1303Vh1
    public void d() {
        throw null;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.M) {
            this.M = true;
            throw null;
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.H = findViewById(R.id.ntp_middle_spacer);
        this.I = (LogoView) findViewById(R.id.search_provider_logo);
        new C2584fo0((ViewStub) findViewById(R.id.video_iph_stub), Profile.b());
        boolean z = false;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.f41610_resource_name_obfuscated_RES_2131624470, (ViewGroup) this, false);
        this.f10712J = viewGroup;
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        layoutParams.width = -2;
        this.f10712J.setLayoutParams(layoutParams);
        addView(this.f10712J, indexOfChild(this.H) + 1);
        if (N.MwBQ$0Eq() == 1) {
            z = true;
        }
        if (z) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.explore_sites_stub);
            viewStub.setLayoutResource(R.layout.f38420_resource_name_obfuscated_RES_2131624151);
            this.K = viewStub.inflate();
        }
        View findViewById = findViewById(R.id.search_box);
        if (AbstractC5762yQ0.e()) {
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.f24900_resource_name_obfuscated_RES_2131166109);
            findViewById.setPaddingRelative(dimensionPixelOffset, findViewById.getPaddingTop(), dimensionPixelOffset, findViewById.getPaddingBottom());
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f10712J.getVisibility() == 8) {
            View view = this.K;
            if (view != null) {
                view.getMeasuredWidth();
                throw null;
            }
            return;
        }
        this.f10712J.getMeasuredWidth();
        throw null;
    }

    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        throw null;
    }
}
