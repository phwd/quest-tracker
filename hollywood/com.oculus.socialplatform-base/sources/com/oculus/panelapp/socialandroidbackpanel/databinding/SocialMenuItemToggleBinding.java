package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.ocui.OCToggle;
import com.oculus.socialplatform.R;

public abstract class SocialMenuItemToggleBinding extends AnonymousClass1uW {
    @Bindable
    public String mBottomText;
    @Bindable
    public boolean mChecked;
    @Bindable
    public Drawable mIcon;
    @Bindable
    public String mTopText;
    @NonNull
    public final OCTextView menuItemBottomText;
    @NonNull
    public final ImageView menuItemIconImage;
    @NonNull
    public final ConstraintLayout menuItemToggle;
    @NonNull
    public final OCTextView menuItemTopText;
    @NonNull
    public final OCToggle toggle;

    public abstract void setBottomText(@Nullable String str);

    public abstract void setChecked(boolean z);

    public abstract void setIcon(@Nullable Drawable drawable);

    public abstract void setTopText(@Nullable String str);

    @Nullable
    public String getBottomText() {
        return this.mBottomText;
    }

    public boolean getChecked() {
        return this.mChecked;
    }

    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getTopText() {
        return this.mTopText;
    }

    public SocialMenuItemToggleBinding(Object obj, View view, int i, OCTextView oCTextView, ImageView imageView, ConstraintLayout constraintLayout, OCTextView oCTextView2, OCToggle oCToggle) {
        super(obj, view, i);
        this.menuItemBottomText = oCTextView;
        this.menuItemIconImage = imageView;
        this.menuItemToggle = constraintLayout;
        this.menuItemTopText = oCTextView2;
        this.toggle = oCToggle;
    }

    public static SocialMenuItemToggleBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static SocialMenuItemToggleBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialMenuItemToggleBinding) AnonymousClass1uW.bind(obj, view, R.layout.social_menu_item_toggle);
    }

    @NonNull
    public static SocialMenuItemToggleBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static SocialMenuItemToggleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static SocialMenuItemToggleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialMenuItemToggleBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_menu_item_toggle, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static SocialMenuItemToggleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialMenuItemToggleBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_menu_item_toggle, null, false);
    }
}
