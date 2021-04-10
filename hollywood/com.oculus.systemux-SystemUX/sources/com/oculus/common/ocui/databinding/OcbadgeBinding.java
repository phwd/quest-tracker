package com.oculus.common.ocui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.ocui.R;
import com.oculus.ocui.OCTextView;

public abstract class OcbadgeBinding extends ViewDataBinding {
    @Bindable
    protected String mBadgeCount;
    @NonNull
    public final OCTextView text;

    public abstract void setBadgeCount(@Nullable String str);

    protected OcbadgeBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.text = oCTextView;
    }

    @Nullable
    public String getBadgeCount() {
        return this.mBadgeCount;
    }

    @NonNull
    public static OcbadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcbadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OcbadgeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocbadge, viewGroup, z, obj);
    }

    @NonNull
    public static OcbadgeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcbadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OcbadgeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocbadge, null, false, obj);
    }

    public static OcbadgeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OcbadgeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcbadgeBinding) bind(obj, view, R.layout.ocbadge);
    }
}
