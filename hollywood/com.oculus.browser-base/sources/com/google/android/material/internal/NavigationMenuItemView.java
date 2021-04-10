package com.google.android.material.internal;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NavigationMenuItemView extends WR implements AbstractC2228dj0 {
    public static final int[] d0 = {16842912};
    public int e0;
    public boolean f0;
    public final CheckedTextView g0;
    public FrameLayout h0;
    public C0817Ni0 i0;
    public final C5349w j0;

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        C1009Qm0 qm0 = new C1009Qm0(this);
        this.j0 = qm0;
        if (this.I != 0) {
            this.I = 0;
            requestLayout();
        }
        LayoutInflater.from(context).inflate(R.layout.f37940_resource_name_obfuscated_RES_2131624103, (ViewGroup) this, true);
        this.e0 = context.getResources().getDimensionPixelSize(R.dimen.f18380_resource_name_obfuscated_RES_2131165457);
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R.id.design_menu_item_text);
        this.g0 = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        AbstractC1920bu1.n(checkedTextView, qm0);
    }

    @Override // defpackage.AbstractC2228dj0
    public C0817Ni0 d() {
        return this.i0;
    }

    @Override // defpackage.AbstractC2228dj0
    public void e(C0817Ni0 ni0, int i) {
        StateListDrawable stateListDrawable;
        this.i0 = ni0;
        int i2 = ni0.f8568a;
        if (i2 > 0) {
            setId(i2);
        }
        setVisibility(ni0.isVisible() ? 0 : 8);
        boolean z = true;
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(R.attr.f3070_resource_name_obfuscated_RES_2130968753, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(d0, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            setBackground(stateListDrawable);
        }
        boolean isCheckable = ni0.isCheckable();
        refreshDrawableState();
        if (this.f0 != isCheckable) {
            this.f0 = isCheckable;
            this.j0.h(this.g0, 2048);
        }
        boolean isChecked = ni0.isChecked();
        refreshDrawableState();
        this.g0.setChecked(isChecked);
        setEnabled(ni0.isEnabled());
        this.g0.setText(ni0.e);
        Drawable icon = ni0.getIcon();
        if (icon != null) {
            int i3 = this.e0;
            icon.setBounds(0, 0, i3, i3);
        }
        this.g0.setCompoundDrawablesRelative(icon, null, null, null);
        View actionView = ni0.getActionView();
        if (actionView != null) {
            if (this.h0 == null) {
                this.h0 = (FrameLayout) ((ViewStub) findViewById(R.id.design_menu_item_action_area_stub)).inflate();
            }
            this.h0.removeAllViews();
            this.h0.addView(actionView);
        }
        setContentDescription(ni0.q);
        Il1.a(this, ni0.r);
        C0817Ni0 ni02 = this.i0;
        if (!(ni02.e == null && ni02.getIcon() == null && this.i0.getActionView() != null)) {
            z = false;
        }
        if (z) {
            this.g0.setVisibility(8);
            FrameLayout frameLayout = this.h0;
            if (frameLayout != null) {
                C5545x80 x80 = (C5545x80) frameLayout.getLayoutParams();
                ((ViewGroup.MarginLayoutParams) x80).width = -1;
                this.h0.setLayoutParams(x80);
                return;
            }
            return;
        }
        this.g0.setVisibility(0);
        FrameLayout frameLayout2 = this.h0;
        if (frameLayout2 != null) {
            C5545x80 x802 = (C5545x80) frameLayout2.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) x802).width = -2;
            this.h0.setLayoutParams(x802);
        }
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        C0817Ni0 ni0 = this.i0;
        if (ni0 != null && ni0.isCheckable() && this.i0.isChecked()) {
            ViewGroup.mergeDrawableStates(onCreateDrawableState, d0);
        }
        return onCreateDrawableState;
    }
}
