package com.oculus.panelapp.bugreporter.databinding;

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
import com.oculus.panelapp.bugreporter.R;

public abstract class BugReportCameraRollImageBinding extends ViewDataBinding {
    @NonNull
    public final OCButton button;
    @NonNull
    public final ImageView image;
    @NonNull
    public final LinearLayout imageContainer;
    @NonNull
    public final View selectedIndicator;
    @NonNull
    public final OCTextView timestamp;

    protected BugReportCameraRollImageBinding(Object obj, View view, int i, OCButton oCButton, ImageView imageView, LinearLayout linearLayout, View view2, OCTextView oCTextView) {
        super(obj, view, i);
        this.button = oCButton;
        this.image = imageView;
        this.imageContainer = linearLayout;
        this.selectedIndicator = view2;
        this.timestamp = oCTextView;
    }

    @NonNull
    public static BugReportCameraRollImageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportCameraRollImageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BugReportCameraRollImageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_camera_roll_image, viewGroup, z, obj);
    }

    @NonNull
    public static BugReportCameraRollImageBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BugReportCameraRollImageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BugReportCameraRollImageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.bug_report_camera_roll_image, null, false, obj);
    }

    public static BugReportCameraRollImageBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BugReportCameraRollImageBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BugReportCameraRollImageBinding) bind(obj, view, R.layout.bug_report_camera_roll_image);
    }
}
