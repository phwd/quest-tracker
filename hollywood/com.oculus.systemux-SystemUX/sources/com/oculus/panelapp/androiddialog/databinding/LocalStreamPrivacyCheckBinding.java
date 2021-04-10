package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.androiddialog.R;

public abstract class LocalStreamPrivacyCheckBinding extends ViewDataBinding {
    @NonNull
    public final OCButton allowButton;
    @NonNull
    public final OCTextView body;
    @NonNull
    public final OCButton denyButton;
    @NonNull
    public final OCToggle disablePrivacyCheckToggle;
    @NonNull
    public final OCTextView title;
    @NonNull
    public final OCTextView toggleText;

    protected LocalStreamPrivacyCheckBinding(Object obj, View view, int i, OCButton oCButton, OCTextView oCTextView, OCButton oCButton2, OCToggle oCToggle, OCTextView oCTextView2, OCTextView oCTextView3) {
        super(obj, view, i);
        this.allowButton = oCButton;
        this.body = oCTextView;
        this.denyButton = oCButton2;
        this.disablePrivacyCheckToggle = oCToggle;
        this.title = oCTextView2;
        this.toggleText = oCTextView3;
    }

    @NonNull
    public static LocalStreamPrivacyCheckBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LocalStreamPrivacyCheckBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LocalStreamPrivacyCheckBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.local_stream_privacy_check, viewGroup, z, obj);
    }

    @NonNull
    public static LocalStreamPrivacyCheckBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LocalStreamPrivacyCheckBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LocalStreamPrivacyCheckBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.local_stream_privacy_check, null, false, obj);
    }

    public static LocalStreamPrivacyCheckBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LocalStreamPrivacyCheckBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LocalStreamPrivacyCheckBinding) bind(obj, view, R.layout.local_stream_privacy_check);
    }
}
