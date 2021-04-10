package com.oculus.common.ocui.databinding;

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
import com.oculus.common.ocui.R;
import com.oculus.ocui.OCTextView;

public abstract class OctileButtonBinding extends ViewDataBinding {
    @NonNull
    public final OCTextView activeIndicatorView;
    @NonNull
    public final ImageView ctaIcon;
    @Bindable
    protected Drawable mActiveIndicator;
    @Bindable
    protected Drawable mBackground;
    @Bindable
    protected String mCtaText;
    @Bindable
    protected String mSubtitle;
    @Bindable
    protected String mTitle;
    @Bindable
    protected Drawable mTitleIcon;
    @NonNull
    public final OCTextView textView;
    @NonNull
    public final ImageView titleIconImage;

    public abstract void setActiveIndicator(@Nullable Drawable drawable);

    public abstract void setBackground(@Nullable Drawable drawable);

    public abstract void setCtaText(@Nullable String str);

    public abstract void setSubtitle(@Nullable String str);

    public abstract void setTitle(@Nullable String str);

    public abstract void setTitleIcon(@Nullable Drawable drawable);

    protected OctileButtonBinding(Object obj, View view, int i, OCTextView oCTextView, ImageView imageView, OCTextView oCTextView2, ImageView imageView2) {
        super(obj, view, i);
        this.activeIndicatorView = oCTextView;
        this.ctaIcon = imageView;
        this.textView = oCTextView2;
        this.titleIconImage = imageView2;
    }

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    @Nullable
    public Drawable getTitleIcon() {
        return this.mTitleIcon;
    }

    @Nullable
    public String getSubtitle() {
        return this.mSubtitle;
    }

    @Nullable
    public Drawable getActiveIndicator() {
        return this.mActiveIndicator;
    }

    @Nullable
    public String getCtaText() {
        return this.mCtaText;
    }

    @Nullable
    public Drawable getBackground() {
        return this.mBackground;
    }

    @NonNull
    public static OctileButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OctileButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OctileButtonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.octile_button, viewGroup, z, obj);
    }

    @NonNull
    public static OctileButtonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OctileButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OctileButtonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.octile_button, null, false, obj);
    }

    public static OctileButtonBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OctileButtonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OctileButtonBinding) bind(obj, view, R.layout.octile_button);
    }
}
