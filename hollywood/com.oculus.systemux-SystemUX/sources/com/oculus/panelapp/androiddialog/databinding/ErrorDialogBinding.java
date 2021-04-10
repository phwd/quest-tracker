package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorViewModel;

public abstract class ErrorDialogBinding extends ViewDataBinding {
    @NonNull
    public final OCTextView errorDialogMessage;
    @NonNull
    public final OCButton errorDialogPrimaryButton;
    @NonNull
    public final OCButton errorDialogPrimarySecondaryButton;
    @NonNull
    public final OCButton errorDialogSecondaryButton;
    @NonNull
    public final OCTextView errorDialogTitle;
    @Bindable
    protected ErrorViewModel mErrorViewModel;

    public abstract void setErrorViewModel(@Nullable ErrorViewModel errorViewModel);

    protected ErrorDialogBinding(Object obj, View view, int i, OCTextView oCTextView, OCButton oCButton, OCButton oCButton2, OCButton oCButton3, OCTextView oCTextView2) {
        super(obj, view, i);
        this.errorDialogMessage = oCTextView;
        this.errorDialogPrimaryButton = oCButton;
        this.errorDialogPrimarySecondaryButton = oCButton2;
        this.errorDialogSecondaryButton = oCButton3;
        this.errorDialogTitle = oCTextView2;
    }

    @Nullable
    public ErrorViewModel getErrorViewModel() {
        return this.mErrorViewModel;
    }

    @NonNull
    public static ErrorDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ErrorDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ErrorDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.error_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static ErrorDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ErrorDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ErrorDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.error_dialog, null, false, obj);
    }

    public static ErrorDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ErrorDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ErrorDialogBinding) bind(obj, view, R.layout.error_dialog);
    }
}
