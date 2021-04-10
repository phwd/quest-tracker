package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.enterprise.EnterpriseCastToBrowserPinDialog;

public abstract class EnterpriseCastToBrowserPinDialogBinding extends ViewDataBinding {
    @NonNull
    public final EnterpriseCastToBrowserPinDialog enterpriseCastToBrowserPinDialog;
    @NonNull
    public final OCTextView enterpriseCastToBrowserPinDialogBody;
    @NonNull
    public final OCButton enterpriseCastToBrowserPinDialogCancelButton;
    @NonNull
    public final DialogTitleBinding enterpriseCastToBrowserPinDialogTitle;
    @NonNull
    public final EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigit0;
    @NonNull
    public final EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigit1;
    @NonNull
    public final EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigit2;
    @NonNull
    public final EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigit3;
    @NonNull
    public final EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigit4;
    @NonNull
    public final EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigit5;
    @Bindable
    protected String mDigit0;
    @Bindable
    protected String mDigit1;
    @Bindable
    protected String mDigit2;
    @Bindable
    protected String mDigit3;
    @Bindable
    protected String mDigit4;
    @Bindable
    protected String mDigit5;

    public abstract void setDigit0(@Nullable String str);

    public abstract void setDigit1(@Nullable String str);

    public abstract void setDigit2(@Nullable String str);

    public abstract void setDigit3(@Nullable String str);

    public abstract void setDigit4(@Nullable String str);

    public abstract void setDigit5(@Nullable String str);

    protected EnterpriseCastToBrowserPinDialogBinding(Object obj, View view, int i, EnterpriseCastToBrowserPinDialog enterpriseCastToBrowserPinDialog2, OCTextView oCTextView, OCButton oCButton, DialogTitleBinding dialogTitleBinding, EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding, EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding2, EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding3, EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding4, EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding5, EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding6) {
        super(obj, view, i);
        this.enterpriseCastToBrowserPinDialog = enterpriseCastToBrowserPinDialog2;
        this.enterpriseCastToBrowserPinDialogBody = oCTextView;
        this.enterpriseCastToBrowserPinDialogCancelButton = oCButton;
        this.enterpriseCastToBrowserPinDialogTitle = dialogTitleBinding;
        setContainedBinding(this.enterpriseCastToBrowserPinDialogTitle);
        this.enterpriseCastToBrowserPinDigit0 = enterpriseCastToBrowserPinDigitBinding;
        setContainedBinding(this.enterpriseCastToBrowserPinDigit0);
        this.enterpriseCastToBrowserPinDigit1 = enterpriseCastToBrowserPinDigitBinding2;
        setContainedBinding(this.enterpriseCastToBrowserPinDigit1);
        this.enterpriseCastToBrowserPinDigit2 = enterpriseCastToBrowserPinDigitBinding3;
        setContainedBinding(this.enterpriseCastToBrowserPinDigit2);
        this.enterpriseCastToBrowserPinDigit3 = enterpriseCastToBrowserPinDigitBinding4;
        setContainedBinding(this.enterpriseCastToBrowserPinDigit3);
        this.enterpriseCastToBrowserPinDigit4 = enterpriseCastToBrowserPinDigitBinding5;
        setContainedBinding(this.enterpriseCastToBrowserPinDigit4);
        this.enterpriseCastToBrowserPinDigit5 = enterpriseCastToBrowserPinDigitBinding6;
        setContainedBinding(this.enterpriseCastToBrowserPinDigit5);
    }

    @Nullable
    public String getDigit0() {
        return this.mDigit0;
    }

    @Nullable
    public String getDigit1() {
        return this.mDigit1;
    }

    @Nullable
    public String getDigit2() {
        return this.mDigit2;
    }

    @Nullable
    public String getDigit3() {
        return this.mDigit3;
    }

    @Nullable
    public String getDigit4() {
        return this.mDigit4;
    }

    @Nullable
    public String getDigit5() {
        return this.mDigit5;
    }

    @NonNull
    public static EnterpriseCastToBrowserPinDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static EnterpriseCastToBrowserPinDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (EnterpriseCastToBrowserPinDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enterprise_cast_to_browser_pin_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static EnterpriseCastToBrowserPinDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static EnterpriseCastToBrowserPinDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (EnterpriseCastToBrowserPinDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enterprise_cast_to_browser_pin_dialog, null, false, obj);
    }

    public static EnterpriseCastToBrowserPinDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnterpriseCastToBrowserPinDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (EnterpriseCastToBrowserPinDialogBinding) bind(obj, view, R.layout.enterprise_cast_to_browser_pin_dialog);
    }
}
