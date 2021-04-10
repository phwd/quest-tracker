package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletCommonNullStateV2Binding extends ViewDataBinding {
    @Bindable
    protected String mText;
    @NonNull
    public final OCTextView textView;

    public abstract void setText(@Nullable String str);

    protected AnytimeTabletCommonNullStateV2Binding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.textView = oCTextView;
    }

    @Nullable
    public String getText() {
        return this.mText;
    }

    @NonNull
    public static AnytimeTabletCommonNullStateV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletCommonNullStateV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletCommonNullStateV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_common_null_state_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletCommonNullStateV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletCommonNullStateV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletCommonNullStateV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_common_null_state_v2, null, false, obj);
    }

    public static AnytimeTabletCommonNullStateV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletCommonNullStateV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletCommonNullStateV2Binding) bind(obj, view, R.layout.anytime_tablet_common_null_state_v2);
    }
}
