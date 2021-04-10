package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class OctileButtonBinding extends AnonymousClass1uW {
    @NonNull
    public final OCTextView activeIndicatorView;
    @NonNull
    public final ImageView ctaIcon;
    @Bindable
    public Drawable mActiveIndicator;
    @Bindable
    public Drawable mBackground;
    @Bindable
    public String mCtaText;
    @Bindable
    public String mLabel;
    @Bindable
    public String mSubtitle;
    @Bindable
    public String mTitle;
    @Bindable
    public Drawable mTitleIcon;
    @NonNull
    public final OCTextView subtitle;
    @NonNull
    public final OCTextView title;
    @NonNull
    public final ImageView titleIconImage;

    public abstract void setActiveIndicator(@Nullable Drawable drawable);

    public abstract void setBackground(@Nullable Drawable drawable);

    public abstract void setCtaText(@Nullable String str);

    public abstract void setLabel(@Nullable String str);

    public abstract void setSubtitle(@Nullable String str);

    public abstract void setTitle(@Nullable String str);

    public abstract void setTitleIcon(@Nullable Drawable drawable);

    @Nullable
    public Drawable getActiveIndicator() {
        return this.mActiveIndicator;
    }

    @Nullable
    public Drawable getBackground() {
        return this.mBackground;
    }

    @Nullable
    public String getCtaText() {
        return this.mCtaText;
    }

    @Nullable
    public String getLabel() {
        return this.mLabel;
    }

    @Nullable
    public String getSubtitle() {
        return this.mSubtitle;
    }

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    @Nullable
    public Drawable getTitleIcon() {
        return this.mTitleIcon;
    }

    public OctileButtonBinding(Object obj, View view, int i, OCTextView oCTextView, ImageView imageView, OCTextView oCTextView2, OCTextView oCTextView3, ImageView imageView2) {
        super(obj, view, i);
        this.activeIndicatorView = oCTextView;
        this.ctaIcon = imageView;
        this.subtitle = oCTextView2;
        this.title = oCTextView3;
        this.titleIconImage = imageView2;
    }

    public static OctileButtonBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OctileButtonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OctileButtonBinding) AnonymousClass1uW.bind(obj, view, R.layout.octile_button);
    }

    @NonNull
    public static OctileButtonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OctileButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OctileButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OctileButtonBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.octile_button, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OctileButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OctileButtonBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.octile_button, null, false);
    }
}
