package com.oculus.panelapp.bugreporter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCPlaceholderGlint;
import com.oculus.panelapp.bugreporter.R;

public abstract class BugReportCameraRollPlaceholderBinding extends ViewDataBinding {
    @NonNull
    public final OCPlaceholderGlint placeholder;

    protected BugReportCameraRollPlaceholderBinding(Object obj, View view, int i, OCPlaceholderGlint oCPlaceholderGlint) {
        super(obj, view, i);
        this.placeholder = oCPlaceholderGlint;
    }

    @NonNull
    public static BugReportCameraRollPlaceholderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportCameraRollPlaceholderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BugReportCameraRollPlaceholderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_camera_roll_placeholder, viewGroup, z, obj);
    }

    @NonNull
    public static BugReportCameraRollPlaceholderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportCameraRollPlaceholderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BugReportCameraRollPlaceholderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_camera_roll_placeholder, null, false, obj);
    }

    public static BugReportCameraRollPlaceholderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BugReportCameraRollPlaceholderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BugReportCameraRollPlaceholderBinding) bind(obj, view, R.layout.bug_report_camera_roll_placeholder);
    }
}
