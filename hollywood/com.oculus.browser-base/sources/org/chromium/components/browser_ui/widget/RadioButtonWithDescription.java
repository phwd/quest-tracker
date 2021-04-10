package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RadioButtonWithDescription extends RelativeLayout implements View.OnClickListener {
    public RadioButton F;
    public TextView G;
    public TextView H;
    public LJ0 I;

    /* renamed from: J  reason: collision with root package name */
    public List f10823J;

    public RadioButtonWithDescription(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(c(), (ViewGroup) this, true);
        i();
        if (attributeSet != null) {
            a(attributeSet);
        }
        setMinimumHeight(getResources().getDimensionPixelSize(R.dimen.f20870_resource_name_obfuscated_RES_2131165706));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.f24610_resource_name_obfuscated_RES_2131166080);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.f24620_resource_name_obfuscated_RES_2131166081);
        setPaddingRelative(getPaddingStart() == 0 ? dimensionPixelSize : getPaddingStart(), getPaddingTop() == 0 ? dimensionPixelSize2 : getPaddingTop(), getPaddingEnd() != 0 ? getPaddingEnd() : dimensionPixelSize, getPaddingBottom() != 0 ? getPaddingBottom() : dimensionPixelSize2);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(16843534, typedValue, true);
            if (b() != -1) {
                View findViewById = findViewById(R.id.radio_container);
                findViewById.setBackgroundResource(typedValue.resourceId);
                findViewById.setPaddingRelative(getPaddingStart(), findViewById.getPaddingTop(), findViewById.getPaddingEnd(), findViewById.getPaddingBottom());
                setPaddingRelative(0, getPaddingTop(), getPaddingEnd(), getPaddingBottom());
            } else {
                setBackgroundResource(typedValue.resourceId);
            }
        }
        setOnClickListener(this);
        setFocusable(true);
    }

    public void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, FJ0.s0, 0, 0);
        String string = obtainStyledAttributes.getString(1);
        if (string != null) {
            this.G.setText(string);
        }
        String string2 = obtainStyledAttributes.getString(0);
        if (string2 != null) {
            this.H.setText(string2);
            this.H.setVisibility(0);
        } else {
            ((RelativeLayout.LayoutParams) this.G.getLayoutParams()).addRule(15);
        }
        obtainStyledAttributes.recycle();
    }

    public int b() {
        return -1;
    }

    public int c() {
        return R.layout.f41110_resource_name_obfuscated_RES_2131624420;
    }

    public TextView d() {
        return (TextView) findViewById(R.id.primary);
    }

    @Override // android.view.View, android.view.ViewGroup
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.View, android.view.ViewGroup
    public void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    public boolean e() {
        return this.F.isChecked();
    }

    public void f(boolean z) {
        g(z);
        if (z) {
            requestFocus();
        }
    }

    public void g(boolean z) {
        List<RadioButtonWithDescription> list = this.f10823J;
        if (list != null && z) {
            for (RadioButtonWithDescription radioButtonWithDescription : list) {
                if (radioButtonWithDescription != this) {
                    radioButtonWithDescription.f(false);
                }
            }
        }
        this.F.setChecked(z);
    }

    public void h(CharSequence charSequence) {
        this.H.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            ((RelativeLayout.LayoutParams) this.G.getLayoutParams()).addRule(15);
            this.H.setVisibility(8);
            return;
        }
        ((RelativeLayout.LayoutParams) this.G.getLayoutParams()).removeRule(15);
        this.H.setVisibility(0);
    }

    public void i() {
        this.F = (RadioButton) findViewById(R.id.radio_button);
        this.G = d();
        this.H = (TextView) findViewById(R.id.description);
        int b = b();
        if (b != -1) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.end_view_stub);
            viewStub.setLayoutResource(b);
            viewStub.inflate();
        }
    }

    public void onClick(View view) {
        f(true);
        LJ0 lj0 = this.I;
        if (lj0 != null) {
            lj0.a(this);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("superState"));
            f(bundle.getBoolean("isChecked"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putBoolean("isChecked", e());
        return bundle;
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.H.setEnabled(z);
        this.G.setEnabled(z);
        this.F.setEnabled(z);
    }
}
