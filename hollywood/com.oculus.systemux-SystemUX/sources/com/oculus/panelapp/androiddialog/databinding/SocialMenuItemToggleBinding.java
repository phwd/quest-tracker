package com.oculus.panelapp.androiddialog.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.androiddialog.R;

public abstract class SocialMenuItemToggleBinding extends ViewDataBinding {
    @Bindable
    protected String mBottomText;
    @Bindable
    protected boolean mChecked;
    @Bindable
    protected Drawable mIcon;
    @Bindable
    protected String mTopText;
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

    protected SocialMenuItemToggleBinding(Object obj, View view, int i, OCTextView oCTextView, ImageView imageView, ConstraintLayout constraintLayout, OCTextView oCTextView2, OCToggle oCToggle) {
        super(obj, view, i);
        this.menuItemBottomText = oCTextView;
        this.menuItemIconImage = imageView;
        this.menuItemToggle = constraintLayout;
        this.menuItemTopText = oCTextView2;
        this.toggle = oCToggle;
    }

    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getTopText() {
        return this.mTopText;
    }

    @Nullable
    public String getBottomText() {
        return this.mBottomText;
    }

    public boolean getChecked() {
        return this.mChecked;
    }

    @NonNull
    public static SocialMenuItemToggleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialMenuItemToggleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SocialMenuItemToggleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_menu_item_toggle, viewGroup, z, obj);
    }

    @NonNull
    public static SocialMenuItemToggleBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialMenuItemToggleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SocialMenuItemToggleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_menu_item_toggle, null, false, obj);
    }

    public static SocialMenuItemToggleBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialMenuItemToggleBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialMenuItemToggleBinding) bind(obj, view, R.layout.social_menu_item_toggle);
    }
}
