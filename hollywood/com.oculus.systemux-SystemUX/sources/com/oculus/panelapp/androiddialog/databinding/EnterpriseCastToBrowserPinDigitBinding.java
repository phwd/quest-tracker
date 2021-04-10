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

public abstract class EnterpriseCastToBrowserPinDigitBinding extends ViewDataBinding {
    @NonNull
    public final OCTextView enterpriseCastToBrowserPinDigit;
    @Bindable
    protected String mDigit;

    public abstract void setDigit(@Nullable String str);

    protected EnterpriseCastToBrowserPinDigitBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.enterpriseCastToBrowserPinDigit = oCTextView;
    }

    @Nullable
    public String getDigit() {
        return this.mDigit;
    }

    @NonNull
    public static EnterpriseCastToBrowserPinDigitBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static EnterpriseCastToBrowserPinDigitBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (EnterpriseCastToBrowserPinDigitBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enterprise_cast_to_browser_pin_digit, viewGroup, z, obj);
    }

    @NonNull
    public static EnterpriseCastToBrowserPinDigitBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static EnterpriseCastToBrowserPinDigitBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (EnterpriseCastToBrowserPinDigitBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.enterprise_cast_to_browser_pin_digit, null, false, obj);
    }

    public static EnterpriseCastToBrowserPinDigitBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EnterpriseCastToBrowserPinDigitBinding bind(@NonNull View view, @Nullable Object obj) {
        return (EnterpriseCastToBrowserPinDigitBinding) bind(obj, view, R.layout.enterprise_cast_to_browser_pin_digit);
    }
}
