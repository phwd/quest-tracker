package com.oculus.common.ocui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.ocui.R;
import com.oculus.ocui.OCTextView;

public abstract class OcprogressbarBinding extends ViewDataBinding {
    @Bindable
    protected int mProgress;
    @Bindable
    protected boolean mShowProgressPercentage;
    @NonNull
    public final OCTextView percantageText;
    @NonNull
    public final ProgressBar progressBar;

    public abstract void setProgress(int i);

    public abstract void setShowProgressPercentage(boolean z);

    protected OcprogressbarBinding(Object obj, View view, int i, OCTextView oCTextView, ProgressBar progressBar2) {
        super(obj, view, i);
        this.percantageText = oCTextView;
        this.progressBar = progressBar2;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public boolean getShowProgressPercentage() {
        return this.mShowProgressPercentage;
    }

    @NonNull
    public static OcprogressbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcprogressbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OcprogressbarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocprogressbar, viewGroup, z, obj);
    }

    @NonNull
    public static OcprogressbarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcprogressbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OcprogressbarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocprogressbar, null, false, obj);
    }

    public static OcprogressbarBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OcprogressbarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcprogressbarBinding) bind(obj, view, R.layout.ocprogressbar);
    }
}
