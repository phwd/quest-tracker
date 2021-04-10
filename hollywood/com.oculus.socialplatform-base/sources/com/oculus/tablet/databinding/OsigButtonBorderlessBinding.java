package com.oculus.tablet.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.socialplatform.R;

public abstract class OsigButtonBorderlessBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton button;
    @NonNull
    public final View buttonForeground;
    @NonNull
    public final ConstraintLayout container;
    @Bindable
    public Drawable mActiveIndicator;
    @Bindable
    public Boolean mEnabled;
    @Bindable
    public Drawable mIcon;
    @Bindable
    public String mText;

    public abstract void setActiveIndicator(@Nullable Drawable drawable);

    public abstract void setEnabled(@Nullable Boolean bool);

    public abstract void setIcon(@Nullable Drawable drawable);

    public abstract void setText(@Nullable String str);

    @Nullable
    public Drawable getActiveIndicator() {
        return this.mActiveIndicator;
    }

    @Nullable
    public Boolean getEnabled() {
        return this.mEnabled;
    }

    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getText() {
        return this.mText;
    }

    public OsigButtonBorderlessBinding(Object obj, View view, int i, OCButton oCButton, View view2, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.button = oCButton;
        this.buttonForeground = view2;
        this.container = constraintLayout;
    }

    public static OsigButtonBorderlessBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OsigButtonBorderlessBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OsigButtonBorderlessBinding) AnonymousClass1uW.bind(obj, view, R.layout.osig_button_borderless);
    }

    @NonNull
    public static OsigButtonBorderlessBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OsigButtonBorderlessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OsigButtonBorderlessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OsigButtonBorderlessBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.osig_button_borderless, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OsigButtonBorderlessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OsigButtonBorderlessBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.osig_button_borderless, null, false);
    }
}
