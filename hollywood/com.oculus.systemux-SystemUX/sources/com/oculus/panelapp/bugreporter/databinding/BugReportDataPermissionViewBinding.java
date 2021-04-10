package com.oculus.panelapp.bugreporter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCCheckbox;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.bugreporter.DataPermissionViewModel;
import com.oculus.panelapp.bugreporter.R;

public abstract class BugReportDataPermissionViewBinding extends ViewDataBinding {
    @NonNull
    public final OCButton backButton;
    @NonNull
    public final OCButton cancelButton;
    @NonNull
    public final OCCheckbox checkbox;
    @NonNull
    public final OCTextView checkboxLabel;
    @Bindable
    protected DataPermissionViewModel mViewModel;
    @NonNull
    public final OCButton submitButton;
    @NonNull
    public final OCTextView systemInformation;
    @NonNull
    public final OCTextView systemLabel;
    @NonNull
    public final OCTextView title;

    public abstract void setViewModel(@Nullable DataPermissionViewModel dataPermissionViewModel);

    protected BugReportDataPermissionViewBinding(Object obj, View view, int i, OCButton oCButton, OCButton oCButton2, OCCheckbox oCCheckbox, OCTextView oCTextView, OCButton oCButton3, OCTextView oCTextView2, OCTextView oCTextView3, OCTextView oCTextView4) {
        super(obj, view, i);
        this.backButton = oCButton;
        this.cancelButton = oCButton2;
        this.checkbox = oCCheckbox;
        this.checkboxLabel = oCTextView;
        this.submitButton = oCButton3;
        this.systemInformation = oCTextView2;
        this.systemLabel = oCTextView3;
        this.title = oCTextView4;
    }

    @Nullable
    public DataPermissionViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static BugReportDataPermissionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportDataPermissionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BugReportDataPermissionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_data_permission_view, viewGroup, z, obj);
    }

    @NonNull
    public static BugReportDataPermissionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportDataPermissionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BugReportDataPermissionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_data_permission_view, null, false, obj);
    }

    public static BugReportDataPermissionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BugReportDataPermissionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BugReportDataPermissionViewBinding) bind(obj, view, R.layout.bug_report_data_permission_view);
    }
}
