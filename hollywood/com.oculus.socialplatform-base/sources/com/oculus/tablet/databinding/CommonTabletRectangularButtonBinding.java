package com.oculus.tablet.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class CommonTabletRectangularButtonBinding extends AnonymousClass1uW {
    @NonNull
    public final ShellButton button;
    @Bindable
    public Drawable mIcon;
    @Bindable
    public String mText;

    public abstract void setIcon(@Nullable Drawable drawable);

    public abstract void setText(@Nullable String str);

    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getText() {
        return this.mText;
    }

    public CommonTabletRectangularButtonBinding(Object obj, View view, int i, ShellButton shellButton) {
        super(obj, view, i);
        this.button = shellButton;
    }

    public static CommonTabletRectangularButtonBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static CommonTabletRectangularButtonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CommonTabletRectangularButtonBinding) AnonymousClass1uW.bind(obj, view, R.layout.common_tablet_rectangular_button);
    }

    @NonNull
    public static CommonTabletRectangularButtonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static CommonTabletRectangularButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static CommonTabletRectangularButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (CommonTabletRectangularButtonBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.common_tablet_rectangular_button, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static CommonTabletRectangularButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (CommonTabletRectangularButtonBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.common_tablet_rectangular_button, null, false);
    }
}
