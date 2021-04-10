package org.chromium.chrome.browser.accessibility_tab_switcher;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccessibilityTabModelWrapper extends LinearLayout {
    public static final /* synthetic */ int F = 0;
    public AccessibilityTabModelListView G;
    public View H;
    public TabLayout I;

    /* renamed from: J  reason: collision with root package name */
    public D81 f10606J;
    public D81 K;
    public ImageView L;
    public ImageView M;
    public ColorStateList N;
    public ColorStateList O;
    public ColorStateList P;
    public ColorStateList Q;
    public AbstractC0124Ca1 R;
    public AbstractC0612Ka1 S = new S(this);
    public boolean T;

    public AccessibilityTabModelWrapper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final L a() {
        return (L) this.G.getAdapter();
    }

    public void b() {
        String str;
        AbstractC0124Ca1 ca1 = this.R;
        if (ca1 != null) {
            boolean r = ((AbstractC0246Ea1) ca1).r();
            d();
            if (r) {
                setBackgroundColor(getResources().getColor(R.color.f10880_resource_name_obfuscated_RES_2131099778));
                this.I.r(this.Q.getDefaultColor());
                this.L.setImageTintList(this.O);
                this.M.setImageTintList(this.Q);
            } else {
                setBackgroundColor(getResources().getColor(R.color.f10840_resource_name_obfuscated_RES_2131099774));
                this.I.r(this.P.getDefaultColor());
                this.L.setImageTintList(this.P);
                this.M.setImageTintList(this.N);
            }
            if (r && !this.K.a()) {
                this.K.b();
            } else if (!r && !this.f10606J.a()) {
                this.f10606J.b();
            }
            AccessibilityTabModelListView accessibilityTabModelListView = this.G;
            if (r) {
                str = getContext().getString(R.string.f45960_resource_name_obfuscated_RES_2131951913);
            } else {
                str = getContext().getString(R.string.f45980_resource_name_obfuscated_RES_2131951915);
            }
            accessibilityTabModelListView.setContentDescription(str);
            L a2 = a();
            TabModel l = ((AbstractC0246Ea1) this.R).l(r);
            a2.H = l;
            a2.G = l.j();
            a2.notifyDataSetChanged();
        }
    }

    public void c(AbstractC0124Ca1 ca1) {
        if (this.T) {
            AbstractC0124Ca1 ca12 = this.R;
            ((AbstractC0246Ea1) ca12).f.c(this.S);
        }
        this.R = ca1;
        if (this.T) {
            ((AbstractC0246Ea1) ca1).c(this.S);
        }
        b();
    }

    public final void d() {
        boolean z = true;
        int i = 0;
        if (((AbstractC0246Ea1) this.R).l(true).j().getCount() <= 0) {
            z = false;
        }
        View view = this.H;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
    }

    public void onAttachedToWindow() {
        ((AbstractC0246Ea1) this.R).c(this.S);
        this.T = true;
        super.onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        this.T = false;
        super.onDetachedFromWindow();
    }
}
