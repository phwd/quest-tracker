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

public abstract class DialogTitleBinding extends ViewDataBinding {
    @NonNull
    public final OCTextView dialogTitleTextView;
    @Bindable
    protected String mDialogTitleText;

    public abstract void setDialogTitleText(@Nullable String str);

    protected DialogTitleBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.dialogTitleTextView = oCTextView;
    }

    @Nullable
    public String getDialogTitleText() {
        return this.mDialogTitleText;
    }

    @NonNull
    public static DialogTitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DialogTitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DialogTitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_title, viewGroup, z, obj);
    }

    @NonNull
    public static DialogTitleBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DialogTitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DialogTitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_title, null, false, obj);
    }

    public static DialogTitleBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTitleBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DialogTitleBinding) bind(obj, view, R.layout.dialog_title);
    }
}
