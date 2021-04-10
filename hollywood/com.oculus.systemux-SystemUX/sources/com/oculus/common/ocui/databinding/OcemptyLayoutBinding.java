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
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;

public abstract class OcemptyLayoutBinding extends ViewDataBinding {
    @NonNull
    public final OCButton cta;
    @NonNull
    public final OCTextView headerText;
    @Bindable
    protected String mButtonText;
    @Bindable
    protected String mHeader;
    @Bindable
    protected Drawable mSplash;
    @NonNull
    public final ImageView splashImage;

    public abstract void setButtonText(@Nullable String str);

    public abstract void setHeader(@Nullable String str);

    public abstract void setSplash(@Nullable Drawable drawable);

    protected OcemptyLayoutBinding(Object obj, View view, int i, OCButton oCButton, OCTextView oCTextView, ImageView imageView) {
        super(obj, view, i);
        this.cta = oCButton;
        this.headerText = oCTextView;
        this.splashImage = imageView;
    }

    @Nullable
    public String getHeader() {
        return this.mHeader;
    }

    @Nullable
    public String getButtonText() {
        return this.mButtonText;
    }

    @Nullable
    public Drawable getSplash() {
        return this.mSplash;
    }

    @NonNull
    public static OcemptyLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcemptyLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OcemptyLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocempty_layout, viewGroup, z, obj);
    }

    @NonNull
    public static OcemptyLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcemptyLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OcemptyLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocempty_layout, null, false, obj);
    }

    public static OcemptyLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OcemptyLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcemptyLayoutBinding) bind(obj, view, R.layout.ocempty_layout);
    }
}
