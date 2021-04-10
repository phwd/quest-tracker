package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;

public abstract class AnytimeBarStatusViewV2Binding extends ViewDataBinding {
    @NonNull
    public final AnytimeBarStatusBatteryDotsViewV2Binding batteryDotsHeadset;
    @NonNull
    public final AnytimeBarStatusBatteryDotsViewV2Binding batteryDotsLeftController;
    @NonNull
    public final AnytimeBarStatusBatteryDotsViewV2Binding batteryDotsRightController;
    @NonNull
    public final LinearLayout batteryHeadset;
    @NonNull
    public final LinearLayout batteryLeftController;
    @NonNull
    public final LinearLayout batteryRightController;
    @NonNull
    public final LinearLayout clockTooltipWrapper;
    @NonNull
    public final ImageView iconBatteryHeadset;
    @NonNull
    public final ImageView iconBatteryLeftController;
    @NonNull
    public final ImageView iconBatteryRightController;
    @Bindable
    protected boolean mPartyButtonVisible;
    @Bindable
    protected StatusViewModel mViewModel;
    @NonNull
    public final OCTextView statusClockTimeText;
    @NonNull
    public final OCButton statusPartyIcon;
    @NonNull
    public final OCButton statusWifiButton;
    @NonNull
    public final ImageView statusWifiIcon;

    public abstract void setPartyButtonVisible(boolean z);

    public abstract void setViewModel(@Nullable StatusViewModel statusViewModel);

    protected AnytimeBarStatusViewV2Binding(Object obj, View view, int i, AnytimeBarStatusBatteryDotsViewV2Binding anytimeBarStatusBatteryDotsViewV2Binding, AnytimeBarStatusBatteryDotsViewV2Binding anytimeBarStatusBatteryDotsViewV2Binding2, AnytimeBarStatusBatteryDotsViewV2Binding anytimeBarStatusBatteryDotsViewV2Binding3, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, ImageView imageView, ImageView imageView2, ImageView imageView3, OCTextView oCTextView, OCButton oCButton, OCButton oCButton2, ImageView imageView4) {
        super(obj, view, i);
        this.batteryDotsHeadset = anytimeBarStatusBatteryDotsViewV2Binding;
        setContainedBinding(this.batteryDotsHeadset);
        this.batteryDotsLeftController = anytimeBarStatusBatteryDotsViewV2Binding2;
        setContainedBinding(this.batteryDotsLeftController);
        this.batteryDotsRightController = anytimeBarStatusBatteryDotsViewV2Binding3;
        setContainedBinding(this.batteryDotsRightController);
        this.batteryHeadset = linearLayout;
        this.batteryLeftController = linearLayout2;
        this.batteryRightController = linearLayout3;
        this.clockTooltipWrapper = linearLayout4;
        this.iconBatteryHeadset = imageView;
        this.iconBatteryLeftController = imageView2;
        this.iconBatteryRightController = imageView3;
        this.statusClockTimeText = oCTextView;
        this.statusPartyIcon = oCButton;
        this.statusWifiButton = oCButton2;
        this.statusWifiIcon = imageView4;
    }

    public boolean getPartyButtonVisible() {
        return this.mPartyButtonVisible;
    }

    @Nullable
    public StatusViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static AnytimeBarStatusViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarStatusViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeBarStatusViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_status_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeBarStatusViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarStatusViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeBarStatusViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_status_view_v2, null, false, obj);
    }

    public static AnytimeBarStatusViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeBarStatusViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeBarStatusViewV2Binding) bind(obj, view, R.layout.anytime_bar_status_view_v2);
    }
}
