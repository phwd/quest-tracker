package com.oculus.panelapp.bugreporter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.bugreporter.R;

public abstract class BugReportOsUpdateViewBinding extends ViewDataBinding {
    @NonNull
    public final OCTextView body;
    @NonNull
    public final OCButton cancelButton;
    @NonNull
    public final OCButton reportButton;
    @NonNull
    public final OCTextView title;
    @NonNull
    public final OCButton updateButton;

    protected BugReportOsUpdateViewBinding(Object obj, View view, int i, OCTextView oCTextView, OCButton oCButton, OCButton oCButton2, OCTextView oCTextView2, OCButton oCButton3) {
        super(obj, view, i);
        this.body = oCTextView;
        this.cancelButton = oCButton;
        this.reportButton = oCButton2;
        this.title = oCTextView2;
        this.updateButton = oCButton3;
    }

    @NonNull
    public static BugReportOsUpdateViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportOsUpdateViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BugReportOsUpdateViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_os_update_view, viewGroup, z, obj);
    }

    @NonNull
    public static BugReportOsUpdateViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportOsUpdateViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BugReportOsUpdateViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_os_update_view, null, false, obj);
    }

    public static BugReportOsUpdateViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BugReportOsUpdateViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BugReportOsUpdateViewBinding) bind(obj, view, R.layout.bug_report_os_update_view);
    }
}
