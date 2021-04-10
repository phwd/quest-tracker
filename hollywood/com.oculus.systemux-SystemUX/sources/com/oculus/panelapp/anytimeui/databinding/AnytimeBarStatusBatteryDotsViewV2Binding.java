package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeBarStatusBatteryDotsViewV2Binding extends ViewDataBinding {
    @NonNull
    public final ImageView dot1;
    @NonNull
    public final ImageView dot2;
    @NonNull
    public final ImageView dot3;
    @NonNull
    public final ImageView dot4;

    protected AnytimeBarStatusBatteryDotsViewV2Binding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4) {
        super(obj, view, i);
        this.dot1 = imageView;
        this.dot2 = imageView2;
        this.dot3 = imageView3;
        this.dot4 = imageView4;
    }

    @NonNull
    public static AnytimeBarStatusBatteryDotsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarStatusBatteryDotsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeBarStatusBatteryDotsViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_status_battery_dots_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeBarStatusBatteryDotsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarStatusBatteryDotsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeBarStatusBatteryDotsViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_status_battery_dots_view_v2, null, false, obj);
    }

    public static AnytimeBarStatusBatteryDotsViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeBarStatusBatteryDotsViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeBarStatusBatteryDotsViewV2Binding) bind(obj, view, R.layout.anytime_bar_status_battery_dots_view_v2);
    }
}
