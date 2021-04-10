package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class OcprogressbarBinding extends AnonymousClass1uW {
    @Bindable
    public int mProgress;
    @Bindable
    public boolean mShowProgressPercentage;
    @NonNull
    public final OCTextView percantageText;
    @NonNull
    public final ProgressBar progressBar;

    public abstract void setProgress(int i);

    public abstract void setShowProgressPercentage(boolean z);

    public int getProgress() {
        return this.mProgress;
    }

    public boolean getShowProgressPercentage() {
        return this.mShowProgressPercentage;
    }

    public OcprogressbarBinding(Object obj, View view, int i, OCTextView oCTextView, ProgressBar progressBar2) {
        super(obj, view, i);
        this.percantageText = oCTextView;
        this.progressBar = progressBar2;
    }

    public static OcprogressbarBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcprogressbarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcprogressbarBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocprogressbar);
    }

    @NonNull
    public static OcprogressbarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcprogressbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcprogressbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcprogressbarBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocprogressbar, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcprogressbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcprogressbarBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocprogressbar, null, false);
    }
}
