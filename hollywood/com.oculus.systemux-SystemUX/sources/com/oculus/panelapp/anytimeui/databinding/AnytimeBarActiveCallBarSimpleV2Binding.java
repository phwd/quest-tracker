package com.oculus.panelapp.anytimeui.databinding;

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
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.social.SocialViewModel;

public abstract class AnytimeBarActiveCallBarSimpleV2Binding extends ViewDataBinding {
    @NonNull
    public final OCTextView activeCallBarTitle;
    @NonNull
    public final OCButton activeCallButton;
    @Bindable
    protected SocialViewModel mViewModel;

    public abstract void setViewModel(@Nullable SocialViewModel socialViewModel);

    protected AnytimeBarActiveCallBarSimpleV2Binding(Object obj, View view, int i, OCTextView oCTextView, OCButton oCButton) {
        super(obj, view, i);
        this.activeCallBarTitle = oCTextView;
        this.activeCallButton = oCButton;
    }

    @Nullable
    public SocialViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static AnytimeBarActiveCallBarSimpleV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarActiveCallBarSimpleV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeBarActiveCallBarSimpleV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_active_call_bar_simple_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeBarActiveCallBarSimpleV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarActiveCallBarSimpleV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeBarActiveCallBarSimpleV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_active_call_bar_simple_v2, null, false, obj);
    }

    public static AnytimeBarActiveCallBarSimpleV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeBarActiveCallBarSimpleV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeBarActiveCallBarSimpleV2Binding) bind(obj, view, R.layout.anytime_bar_active_call_bar_simple_v2);
    }
}
