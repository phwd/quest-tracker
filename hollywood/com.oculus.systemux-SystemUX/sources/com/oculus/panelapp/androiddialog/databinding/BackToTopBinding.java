package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;

public abstract class BackToTopBinding extends ViewDataBinding {
    @NonNull
    public final OCButton backToTopButton;
    @NonNull
    public final LinearLayout backToTopButtonHeightLayout;
    @NonNull
    public final ImageView backToTopIcon;
    @NonNull
    public final OCTextView backToTopText;

    protected BackToTopBinding(Object obj, View view, int i, OCButton oCButton, LinearLayout linearLayout, ImageView imageView, OCTextView oCTextView) {
        super(obj, view, i);
        this.backToTopButton = oCButton;
        this.backToTopButtonHeightLayout = linearLayout;
        this.backToTopIcon = imageView;
        this.backToTopText = oCTextView;
    }

    @NonNull
    public static BackToTopBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BackToTopBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BackToTopBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.back_to_top, viewGroup, z, obj);
    }

    @NonNull
    public static BackToTopBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BackToTopBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BackToTopBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.back_to_top, null, false, obj);
    }

    public static BackToTopBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BackToTopBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BackToTopBinding) bind(obj, view, R.layout.back_to_top);
    }
}
