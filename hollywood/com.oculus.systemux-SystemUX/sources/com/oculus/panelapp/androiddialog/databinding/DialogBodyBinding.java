package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;

public abstract class DialogBodyBinding extends ViewDataBinding {
    @NonNull
    public final OCTextView dialogBodyText;
    @Bindable
    protected String mDialogBodyText;

    public abstract void setDialogBodyText(@Nullable String str);

    protected DialogBodyBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.dialogBodyText = oCTextView;
    }

    @Nullable
    public String getDialogBodyText() {
        return this.mDialogBodyText;
    }

    @NonNull
    public static DialogBodyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DialogBodyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DialogBodyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_body, viewGroup, z, obj);
    }

    @NonNull
    public static DialogBodyBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DialogBodyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DialogBodyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_body, null, false, obj);
    }

    public static DialogBodyBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBodyBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DialogBodyBinding) bind(obj, view, R.layout.dialog_body);
    }
}
