package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.androiddialog.R;

public abstract class DialogActionSpacePrimarySecondaryBinding extends ViewDataBinding {
    @NonNull
    public final OCButton actionSpacePrimaryButton;
    @NonNull
    public final OCButton actionSpaceSecondaryButton;

    protected DialogActionSpacePrimarySecondaryBinding(Object obj, View view, int i, OCButton oCButton, OCButton oCButton2) {
        super(obj, view, i);
        this.actionSpacePrimaryButton = oCButton;
        this.actionSpaceSecondaryButton = oCButton2;
    }

    @NonNull
    public static DialogActionSpacePrimarySecondaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DialogActionSpacePrimarySecondaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DialogActionSpacePrimarySecondaryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_action_space_primary_secondary, viewGroup, z, obj);
    }

    @NonNull
    public static DialogActionSpacePrimarySecondaryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DialogActionSpacePrimarySecondaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DialogActionSpacePrimarySecondaryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_action_space_primary_secondary, null, false, obj);
    }

    public static DialogActionSpacePrimarySecondaryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogActionSpacePrimarySecondaryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DialogActionSpacePrimarySecondaryBinding) bind(obj, view, R.layout.dialog_action_space_primary_secondary);
    }
}
