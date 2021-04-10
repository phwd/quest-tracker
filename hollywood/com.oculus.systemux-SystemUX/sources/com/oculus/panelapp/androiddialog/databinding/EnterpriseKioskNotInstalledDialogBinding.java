package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.enterprise.EnterpriseKioskNotInstalledDialog;

public abstract class EnterpriseKioskNotInstalledDialogBinding extends ViewDataBinding {
    @NonNull
    public final ImageView enterpriseKioskNotInstalledAdminBadge;
    @NonNull
    public final OCTextView enterpriseKioskNotInstalledAdminLoginText;
    @NonNull
    public final View enterpriseKioskNotInstalledButton;
    @NonNull
    public final EnterpriseKioskNotInstalledDialog enterpriseKioskNotInstalledDialog;
    @NonNull
    public final OCSpinner enterpriseKioskNotInstalledSpinner;
    @NonNull
    public final DialogBodyBinding enterpriseKioskNotInstalledTextBody;
    @NonNull
    public final OCTextView enterpriseKioskNotInstalledTitle;
    @NonNull
    public final Guideline leftGuideline;
    @NonNull
    public final Guideline rightGuideline;

    protected EnterpriseKioskNotInstalledDialogBinding(Object obj, View view, int i, ImageView imageView, OCTextView oCTextView, View view2, EnterpriseKioskNotInstalledDialog enterpriseKioskNotInstalledDialog2, OCSpinner oCSpinner, DialogBodyBinding dialogBodyBinding, OCTextView oCTextView2, Guideline guideline, Guideline guideline2) {
        super(obj, view, i);
        this.enterpriseKioskNotInstalledAdminBadge = imageView;
        this.enterpriseKioskNotInstalledAdminLoginText = oCTextView;
        this.enterpriseKioskNotInstalledButton = view2;
        this.enterpriseKioskNotInstalledDialog = enterpriseKioskNotInstalledDialog2;
        this.enterpriseKioskNotInstalledSpinner = oCSpinner;
        this.enterpriseKioskNotInstalledTextBody = dialogBodyBinding;
        setContainedBinding(this.enterpriseKioskNotInstalledTextBody);
        this.enterpriseKioskNotInstalledTitle = oCTextView2;
        this.leftGuideline = guideline;
        this.rightGuideline = guideline2;
    }

    @NonNull
    public static EnterpriseKioskNotInstalledDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static EnterpriseKioskNotInstalledDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (EnterpriseKioskNotInstalledDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enterprise_kiosk_not_installed_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static EnterpriseKioskNotInstalledDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static EnterpriseKioskNotInstalledDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (EnterpriseKioskNotInstalledDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enterprise_kiosk_not_installed_dialog, null, false, obj);
    }

    public static EnterpriseKioskNotInstalledDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnterpriseKioskNotInstalledDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (EnterpriseKioskNotInstalledDialogBinding) bind(obj, view, R.layout.enterprise_kiosk_not_installed_dialog);
    }
}
