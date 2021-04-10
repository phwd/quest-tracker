package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.androiddialog.R;

public abstract class DialogActionSpaceSecondaryBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout actionSpaceSecondary;
    @NonNull
    public final OCButton actionSpaceSecondaryButton;
    @Bindable
    protected String mButtonText;
    @Bindable
    protected boolean mIsButtonEnabled;

    public abstract void setButtonText(@Nullable String str);

    public abstract void setIsButtonEnabled(boolean z);

    protected DialogActionSpaceSecondaryBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, OCButton oCButton) {
        super(obj, view, i);
        this.actionSpaceSecondary = constraintLayout;
        this.actionSpaceSecondaryButton = oCButton;
    }

    @Nullable
    public String getButtonText() {
        return this.mButtonText;
    }

    public boolean getIsButtonEnabled() {
        return this.mIsButtonEnabled;
    }

    @NonNull
    public static DialogActionSpaceSecondaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DialogActionSpaceSecondaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DialogActionSpaceSecondaryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_action_space_secondary, viewGroup, z, obj);
    }

    @NonNull
    public static DialogActionSpaceSecondaryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DialogActionSpaceSecondaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DialogActionSpaceSecondaryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_action_space_secondary, null, false, obj);
    }

    public static DialogActionSpaceSecondaryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogActionSpaceSecondaryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DialogActionSpaceSecondaryBinding) bind(obj, view, R.layout.dialog_action_space_secondary);
    }
}
