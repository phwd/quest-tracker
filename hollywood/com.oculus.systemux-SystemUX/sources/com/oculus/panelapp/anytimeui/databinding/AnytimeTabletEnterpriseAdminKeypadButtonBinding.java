package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletEnterpriseAdminKeypadButtonBinding extends ViewDataBinding {
    @NonNull
    public final OCButton adminKeypadButton;
    @NonNull
    public final OCButton adminKeypadButtonDisabled;
    @Bindable
    protected Drawable mDrawableStart;
    @Bindable
    protected Boolean mEnabled;
    @Bindable
    protected String mLabel;
    @Bindable
    protected Integer mPaddingStartDip;

    public abstract void setDrawableStart(@Nullable Drawable drawable);

    public abstract void setEnabled(@Nullable Boolean bool);

    public abstract void setLabel(@Nullable String str);

    public abstract void setPaddingStartDip(@Nullable Integer num);

    protected AnytimeTabletEnterpriseAdminKeypadButtonBinding(Object obj, View view, int i, OCButton oCButton, OCButton oCButton2) {
        super(obj, view, i);
        this.adminKeypadButton = oCButton;
        this.adminKeypadButtonDisabled = oCButton2;
    }

    @Nullable
    public String getLabel() {
        return this.mLabel;
    }

    @Nullable
    public Boolean getEnabled() {
        return this.mEnabled;
    }

    @Nullable
    public Drawable getDrawableStart() {
        return this.mDrawableStart;
    }

    @Nullable
    public Integer getPaddingStartDip() {
        return this.mPaddingStartDip;
    }

    @NonNull
    public static AnytimeTabletEnterpriseAdminKeypadButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletEnterpriseAdminKeypadButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletEnterpriseAdminKeypadButtonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_enterprise_admin_keypad_button, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletEnterpriseAdminKeypadButtonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletEnterpriseAdminKeypadButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletEnterpriseAdminKeypadButtonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_enterprise_admin_keypad_button, null, false, obj);
    }

    public static AnytimeTabletEnterpriseAdminKeypadButtonBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletEnterpriseAdminKeypadButtonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletEnterpriseAdminKeypadButtonBinding) bind(obj, view, R.layout.anytime_tablet_enterprise_admin_keypad_button);
    }
}
