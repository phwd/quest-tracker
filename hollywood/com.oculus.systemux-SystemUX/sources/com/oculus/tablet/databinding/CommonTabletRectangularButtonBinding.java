package com.oculus.tablet.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.tablet.R;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class CommonTabletRectangularButtonBinding extends ViewDataBinding {
    @NonNull
    public final ShellButton button;
    @Bindable
    protected Drawable mIcon;
    @Bindable
    protected String mText;

    public abstract void setIcon(@Nullable Drawable drawable);

    public abstract void setText(@Nullable String str);

    protected CommonTabletRectangularButtonBinding(Object obj, View view, int i, ShellButton shellButton) {
        super(obj, view, i);
        this.button = shellButton;
    }

    @Nullable
    public String getText() {
        return this.mText;
    }

    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }

    @NonNull
    public static CommonTabletRectangularButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CommonTabletRectangularButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CommonTabletRectangularButtonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.common_tablet_rectangular_button, viewGroup, z, obj);
    }

    @NonNull
    public static CommonTabletRectangularButtonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CommonTabletRectangularButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CommonTabletRectangularButtonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.common_tablet_rectangular_button, null, false, obj);
    }

    public static CommonTabletRectangularButtonBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CommonTabletRectangularButtonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CommonTabletRectangularButtonBinding) bind(obj, view, R.layout.common_tablet_rectangular_button);
    }
}
