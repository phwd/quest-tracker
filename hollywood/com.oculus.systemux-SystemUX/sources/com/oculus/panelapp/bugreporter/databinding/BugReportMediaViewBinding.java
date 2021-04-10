package com.oculus.panelapp.bugreporter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCBackButton;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.bugreporter.MediaViewModel;
import com.oculus.panelapp.bugreporter.R;

public abstract class BugReportMediaViewBinding extends ViewDataBinding {
    @NonNull
    public final OCBackButton backButton;
    @NonNull
    public final OCRecyclerView cameraRoll;
    @NonNull
    public final OCTextView cameraRollLabel;
    @NonNull
    public final OCButton cancelButton;
    @NonNull
    public final OCButton continueButton;
    @NonNull
    public final OCTextView description;
    @Bindable
    protected MediaViewModel mViewModel;
    @NonNull
    public final OCTextView sizeWarning;
    @NonNull
    public final OCTextView title;

    public abstract void setViewModel(@Nullable MediaViewModel mediaViewModel);

    protected BugReportMediaViewBinding(Object obj, View view, int i, OCBackButton oCBackButton, OCRecyclerView oCRecyclerView, OCTextView oCTextView, OCButton oCButton, OCButton oCButton2, OCTextView oCTextView2, OCTextView oCTextView3, OCTextView oCTextView4) {
        super(obj, view, i);
        this.backButton = oCBackButton;
        this.cameraRoll = oCRecyclerView;
        this.cameraRollLabel = oCTextView;
        this.cancelButton = oCButton;
        this.continueButton = oCButton2;
        this.description = oCTextView2;
        this.sizeWarning = oCTextView3;
        this.title = oCTextView4;
    }

    @Nullable
    public MediaViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static BugReportMediaViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportMediaViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BugReportMediaViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_media_view, viewGroup, z, obj);
    }

    @NonNull
    public static BugReportMediaViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportMediaViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BugReportMediaViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_media_view, null, false, obj);
    }

    public static BugReportMediaViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BugReportMediaViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BugReportMediaViewBinding) bind(obj, view, R.layout.bug_report_media_view);
    }
}
