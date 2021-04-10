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
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class OcemptyLayoutBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton cta;
    @NonNull
    public final OCTextView headerText;
    @Bindable
    public String mButtonText;
    @Bindable
    public String mHeader;
    @Bindable
    public Drawable mSplash;
    @NonNull
    public final ImageView splashImage;

    public abstract void setButtonText(@Nullable String str);

    public abstract void setHeader(@Nullable String str);

    public abstract void setSplash(@Nullable Drawable drawable);

    @Nullable
    public String getButtonText() {
        return this.mButtonText;
    }

    @Nullable
    public String getHeader() {
        return this.mHeader;
    }

    @Nullable
    public Drawable getSplash() {
        return this.mSplash;
    }

    public OcemptyLayoutBinding(Object obj, View view, int i, OCButton oCButton, OCTextView oCTextView, ImageView imageView) {
        super(obj, view, i);
        this.cta = oCButton;
        this.headerText = oCTextView;
        this.splashImage = imageView;
    }

    public static OcemptyLayoutBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcemptyLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcemptyLayoutBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocempty_layout);
    }

    @NonNull
    public static OcemptyLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcemptyLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcemptyLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcemptyLayoutBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocempty_layout, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcemptyLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcemptyLayoutBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocempty_layout, null, false);
    }
}
