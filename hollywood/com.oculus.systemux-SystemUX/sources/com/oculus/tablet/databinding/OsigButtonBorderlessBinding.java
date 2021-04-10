package com.oculus.tablet.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.tablet.R;

public abstract class OsigButtonBorderlessBinding extends ViewDataBinding {
    @NonNull
    public final OCButton button;
    @NonNull
    public final View buttonForeground;
    @NonNull
    public final ConstraintLayout container;
    @Bindable
    protected Drawable mActiveIndicator;
    @Bindable
    protected Boolean mEnabled;
    @Bindable
    protected Drawable mIcon;
    @Bindable
    protected String mText;

    public abstract void setActiveIndicator(@Nullable Drawable drawable);

    public abstract void setEnabled(@Nullable Boolean bool);

    public abstract void setIcon(@Nullable Drawable drawable);

    public abstract void setText(@Nullable String str);

    protected OsigButtonBorderlessBinding(Object obj, View view, int i, OCButton oCButton, View view2, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.button = oCButton;
        this.buttonForeground = view2;
        this.container = constraintLayout;
    }

    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getText() {
        return this.mText;
    }

    @Nullable
    public Boolean getEnabled() {
        return this.mEnabled;
    }

    @Nullable
    public Drawable getActiveIndicator() {
        return this.mActiveIndicator;
    }

    @NonNull
    public static OsigButtonBorderlessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OsigButtonBorderlessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OsigButtonBorderlessBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.osig_button_borderless, viewGroup, z, obj);
    }

    @NonNull
    public static OsigButtonBorderlessBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OsigButtonBorderlessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OsigButtonBorderlessBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.osig_button_borderless, null, false, obj);
    }

    public static OsigButtonBorderlessBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OsigButtonBorderlessBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OsigButtonBorderlessBinding) bind(obj, view, R.layout.osig_button_borderless);
    }
}
