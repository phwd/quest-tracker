package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.vrshell.SystemUXRoute;

public abstract class AnytimeBarNavigationButtonV2Binding extends ViewDataBinding {
    @NonNull
    public final View badge;
    @NonNull
    public final OCButton button;
    @NonNull
    public final ImageView buttonIcon;
    @NonNull
    public final OCTextView buttonLabel;
    @Bindable
    protected Drawable mIcon;
    @Bindable
    protected String mLabel;
    @Bindable
    protected SystemUXRoute mRoute;

    public abstract void setIcon(@Nullable Drawable drawable);

    public abstract void setLabel(@Nullable String str);

    public abstract void setRoute(@Nullable SystemUXRoute systemUXRoute);

    protected AnytimeBarNavigationButtonV2Binding(Object obj, View view, int i, View view2, OCButton oCButton, ImageView imageView, OCTextView oCTextView) {
        super(obj, view, i);
        this.badge = view2;
        this.button = oCButton;
        this.buttonIcon = imageView;
        this.buttonLabel = oCTextView;
    }

    @Nullable
    public SystemUXRoute getRoute() {
        return this.mRoute;
    }

    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getLabel() {
        return this.mLabel;
    }

    @NonNull
    public static AnytimeBarNavigationButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarNavigationButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeBarNavigationButtonV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_navigation_button_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeBarNavigationButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarNavigationButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeBarNavigationButtonV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_navigation_button_v2, null, false, obj);
    }

    public static AnytimeBarNavigationButtonV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeBarNavigationButtonV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeBarNavigationButtonV2Binding) bind(obj, view, R.layout.anytime_bar_navigation_button_v2);
    }
}
