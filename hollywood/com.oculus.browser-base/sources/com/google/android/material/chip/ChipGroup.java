package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChipGroup extends AbstractC4573rR {

    /* renamed from: J  reason: collision with root package name */
    public int f9690J;
    public int K;
    public boolean L;
    public boolean M;
    public final C3440kp N = new C3440kp(this, null);
    public ViewGroup$OnHierarchyChangeListenerC3782mp O = new ViewGroup$OnHierarchyChangeListenerC3782mp(this, null);
    public int P = -1;
    public boolean Q = false;

    public ChipGroup(Context context, AttributeSet attributeSet) {
        super(AbstractC3918nd0.a(context, attributeSet, R.attr.f2700_resource_name_obfuscated_RES_2130968716, R.style.f75410_resource_name_obfuscated_RES_2132018114), attributeSet, R.attr.f2700_resource_name_obfuscated_RES_2130968716);
        TypedArray d = AbstractC1178Tg1.d(getContext(), attributeSet, FJ0.A, R.attr.f2700_resource_name_obfuscated_RES_2130968716, R.style.f75410_resource_name_obfuscated_RES_2132018114, new int[0]);
        int dimensionPixelOffset = d.getDimensionPixelOffset(1, 0);
        int dimensionPixelOffset2 = d.getDimensionPixelOffset(2, dimensionPixelOffset);
        if (this.f9690J != dimensionPixelOffset2) {
            this.f9690J = dimensionPixelOffset2;
            this.G = dimensionPixelOffset2;
            requestLayout();
        }
        int dimensionPixelOffset3 = d.getDimensionPixelOffset(3, dimensionPixelOffset);
        if (this.K != dimensionPixelOffset3) {
            this.K = dimensionPixelOffset3;
            this.F = dimensionPixelOffset3;
            requestLayout();
        }
        this.H = d.getBoolean(5, false);
        boolean z = d.getBoolean(6, false);
        if (this.L != z) {
            this.L = z;
            this.Q = true;
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof Chip) {
                    ((Chip) childAt).setChecked(false);
                }
            }
            this.Q = false;
            this.P = -1;
        }
        this.M = d.getBoolean(4, false);
        int resourceId = d.getResourceId(0, -1);
        if (resourceId != -1) {
            this.P = resourceId;
        }
        d.recycle();
        super.setOnHierarchyChangeListener(this.O);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setImportantForAccessibility(1);
    }

    public final void a(int i, boolean z) {
        View findViewById = findViewById(i);
        if (findViewById instanceof Chip) {
            this.Q = true;
            ((Chip) findViewById).setChecked(z);
            this.Q = false;
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof Chip) {
            Chip chip = (Chip) view;
            if (chip.isChecked()) {
                int i2 = this.P;
                if (i2 != -1 && this.L) {
                    a(i2, false);
                }
                this.P = chip.getId();
            }
        }
        super.addView(view, i, layoutParams);
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof C3611lp);
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C3611lp(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C3611lp(getContext(), attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        int i = this.P;
        if (i != -1) {
            a(i, true);
            this.P = this.P;
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (this.H) {
            i = 0;
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                if (getChildAt(i2) instanceof Chip) {
                    i++;
                }
            }
        } else {
            i = -1;
        }
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) B.a(this.I, i, false, this.L ? 1 : 2).f7712a);
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.O.F = onHierarchyChangeListener;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C3611lp(layoutParams);
    }
}
