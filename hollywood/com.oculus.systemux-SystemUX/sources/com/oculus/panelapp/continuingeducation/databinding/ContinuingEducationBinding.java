package com.oculus.panelapp.continuingeducation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.continuingeducation.R;

public abstract class ContinuingEducationBinding extends ViewDataBinding {
    @NonNull
    public final OCTextView text;

    protected ContinuingEducationBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.text = oCTextView;
    }

    @NonNull
    public static ContinuingEducationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ContinuingEducationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ContinuingEducationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.continuing_education, viewGroup, z, obj);
    }

    @NonNull
    public static ContinuingEducationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ContinuingEducationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ContinuingEducationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.continuing_education, null, false, obj);
    }

    public static ContinuingEducationBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ContinuingEducationBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ContinuingEducationBinding) bind(obj, view, R.layout.continuing_education);
    }
}
