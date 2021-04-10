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

public abstract class BugReportConfirmationViewBinding extends ViewDataBinding {
    @NonNull
    public final OCTextView body;
    @NonNull
    public final OCButton closeButton;
    @NonNull
    public final OCTextView title;

    protected BugReportConfirmationViewBinding(Object obj, View view, int i, OCTextView oCTextView, OCButton oCButton, OCTextView oCTextView2) {
        super(obj, view, i);
        this.body = oCTextView;
        this.closeButton = oCButton;
        this.title = oCTextView2;
    }

    @NonNull
    public static BugReportConfirmationViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportConfirmationViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BugReportConfirmationViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_confirmation_view, viewGroup, z, obj);
    }

    @NonNull
    public static BugReportConfirmationViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportConfirmationViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BugReportConfirmationViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_confirmation_view, null, false, obj);
    }

    public static BugReportConfirmationViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BugReportConfirmationViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BugReportConfirmationViewBinding) bind(obj, view, R.layout.bug_report_confirmation_view);
    }
}
