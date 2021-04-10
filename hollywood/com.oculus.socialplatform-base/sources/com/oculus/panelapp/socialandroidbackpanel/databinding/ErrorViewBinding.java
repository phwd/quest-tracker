package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorViewModel;
import com.oculus.socialplatform.R;

public abstract class ErrorViewBinding extends AnonymousClass1uW {
    @NonNull
    public final OCTextView errorViewMessage;
    @NonNull
    public final OCButton errorViewPrimaryButton;
    @NonNull
    public final OCButton errorViewPrimarySecondaryButton;
    @NonNull
    public final OCButton errorViewSecondaryButton;
    @NonNull
    public final OCTextView errorViewTitle;
    @Bindable
    public ErrorViewModel mErrorViewModel;

    public abstract void setErrorViewModel(@Nullable ErrorViewModel errorViewModel);

    @Nullable
    public ErrorViewModel getErrorViewModel() {
        return this.mErrorViewModel;
    }

    public ErrorViewBinding(Object obj, View view, int i, OCTextView oCTextView, OCButton oCButton, OCButton oCButton2, OCButton oCButton3, OCTextView oCTextView2) {
        super(obj, view, i);
        this.errorViewMessage = oCTextView;
        this.errorViewPrimaryButton = oCButton;
        this.errorViewPrimarySecondaryButton = oCButton2;
        this.errorViewSecondaryButton = oCButton3;
        this.errorViewTitle = oCTextView2;
    }

    public static ErrorViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static ErrorViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ErrorViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.error_view);
    }

    @NonNull
    public static ErrorViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static ErrorViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static ErrorViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (ErrorViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.error_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static ErrorViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (ErrorViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.error_view, null, false);
    }
}
