package org.chromium.chrome.browser.omnibox.status;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class StatusView extends LinearLayout {
    public View F;
    public int G;
    public int H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f10725J;
    public ImageView K;
    public TextView L;
    public View M;
    public View N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public int R;
    public Drawable S;
    public TouchDelegate T;
    public C3974nw U;
    public F21 V = new F21();
    public boolean W;
    public Rect a0;
    public AbstractC4422qa0 b0;

    public StatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final boolean a() {
        return (this.S == null || this.K.getVisibility() == 8 || this.K.getAlpha() == 0.0f) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (defpackage.AbstractC5762yQ0.g(r4) != false) goto L_0x00aa;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(android.graphics.drawable.Drawable r8) {
        /*
        // Method dump skipped, instructions count: 240
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.status.StatusView.c(android.graphics.drawable.Drawable):void");
    }

    public final void d() {
        int i;
        if (this.F != null) {
            Objects.requireNonNull(this.V);
            if (AbstractC5762yQ0.e()) {
                i = 0;
            } else if (a()) {
                i = this.G;
            } else {
                i = this.H;
            }
            View view = this.F;
            view.setPaddingRelative(view.getPaddingStart(), this.F.getPaddingTop(), i, this.F.getPaddingBottom());
        }
    }

    public void e() {
        AbstractC4422qa0 qa0 = this.b0;
        if (qa0 != null) {
            F21 f21 = this.V;
            boolean a2 = qa0.a();
            Objects.requireNonNull(f21);
            if (AbstractC5762yQ0.g(a2)) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.K.getLayoutParams());
                layoutParams.setMarginEnd(0);
                layoutParams.width = getResources().getDimensionPixelSize(R.dimen.f20490_resource_name_obfuscated_RES_2131165668);
                this.K.setLayoutParams(layoutParams);
                setPaddingRelative(getPaddingStart(), getPaddingTop(), getResources().getDimensionPixelOffset(R.dimen.f24840_resource_name_obfuscated_RES_2131166103), getPaddingBottom());
                this.L.setPaddingRelative(getResources().getDimensionPixelSize(R.dimen.f24880_resource_name_obfuscated_RES_2131166107), this.L.getPaddingTop(), this.L.getPaddingEnd(), this.L.getPaddingBottom());
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.N.getLayoutParams());
                layoutParams2.width = getResources().getDimensionPixelSize(R.dimen.f24870_resource_name_obfuscated_RES_2131166106);
                this.N.setLayoutParams(layoutParams2);
            }
        }
    }

    /* renamed from: f */
    public final void b() {
        if (!a()) {
            TouchDelegate touchDelegate = this.T;
            if (touchDelegate != null) {
                this.U.f10521a.remove(touchDelegate);
                this.T = null;
                this.a0 = new Rect();
                return;
            }
            return;
        }
        Rect rect = new Rect();
        this.K.getHitRect(rect);
        if (!rect.equals(new Rect())) {
            boolean z = true;
            if (getLayoutDirection() != 1) {
                z = false;
            }
            if (this.I == 0) {
                this.I = getResources().getDimensionPixelSize(R.dimen.f20460_resource_name_obfuscated_RES_2131165665);
            }
            if (this.f10725J == 0) {
                this.f10725J = getResources().getDimensionPixelSize(R.dimen.f20420_resource_name_obfuscated_RES_2131165661);
            }
            rect.left -= z ? this.f10725J : this.I;
            rect.right += z ? this.I : this.f10725J;
            if (this.T == null || !rect.equals(this.a0) || this.W != z) {
                this.a0 = rect;
                TouchDelegate touchDelegate2 = this.T;
                if (touchDelegate2 != null) {
                    this.U.f10521a.remove(touchDelegate2);
                }
                TouchDelegate touchDelegate3 = new TouchDelegate(rect, this.K);
                this.T = touchDelegate3;
                this.U.f10521a.add(touchDelegate3);
                this.W = z;
            }
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.K = (ImageView) findViewById(R.id.location_bar_status_icon);
        this.L = (TextView) findViewById(R.id.location_bar_verbose_status);
        this.M = findViewById(R.id.location_bar_verbose_status_separator);
        this.N = findViewById(R.id.location_bar_verbose_status_extra_space);
        this.K.setOnLongClickListener(new E21(this));
    }
}
