package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCBackButton;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCInfo;
import com.oculus.ocui.OCProgressBar;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel;

public abstract class CustomSystemDialogBinding extends ViewDataBinding {
    @NonNull
    public final OCBackButton backButton;
    @NonNull
    public final OCTextView body;
    @NonNull
    public final Barrier bottomButtonBarrier;
    @NonNull
    public final OCButton iconButton;
    @NonNull
    public final OCInfo infoBox;
    @Bindable
    protected CustomDialogViewModel mViewModel;
    @NonNull
    public final OCButton primaryButton;
    @NonNull
    public final OCProgressBar progressBar;
    @NonNull
    public final OCSpinner progressSpinner;
    @NonNull
    public final OCButton secondaryButton;
    @NonNull
    public final OCButton tertiaryButton;
    @NonNull
    public final OCTextView title;
    @NonNull
    public final Barrier topButtonBarrier;

    public abstract void setViewModel(@Nullable CustomDialogViewModel customDialogViewModel);

    protected CustomSystemDialogBinding(Object obj, View view, int i, OCBackButton oCBackButton, OCTextView oCTextView, Barrier barrier, OCButton oCButton, OCInfo oCInfo, OCButton oCButton2, OCProgressBar oCProgressBar, OCSpinner oCSpinner, OCButton oCButton3, OCButton oCButton4, OCTextView oCTextView2, Barrier barrier2) {
        super(obj, view, i);
        this.backButton = oCBackButton;
        this.body = oCTextView;
        this.bottomButtonBarrier = barrier;
        this.iconButton = oCButton;
        this.infoBox = oCInfo;
        this.primaryButton = oCButton2;
        this.progressBar = oCProgressBar;
        this.progressSpinner = oCSpinner;
        this.secondaryButton = oCButton3;
        this.tertiaryButton = oCButton4;
        this.title = oCTextView2;
        this.topButtonBarrier = barrier2;
    }

    @Nullable
    public CustomDialogViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static CustomSystemDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CustomSystemDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CustomSystemDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.custom_system_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static CustomSystemDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CustomSystemDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CustomSystemDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.custom_system_dialog, null, false, obj);
    }

    public static CustomSystemDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CustomSystemDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CustomSystemDialogBinding) bind(obj, view, R.layout.custom_system_dialog);
    }
}
