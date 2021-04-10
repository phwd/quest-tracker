package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.quickpromotion.databinding.QpTooltipViewBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.ocui.OCTileButton;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSharingViewV2Binding extends ViewDataBinding {
    @NonNull
    public final QpTooltipViewBinding cameraRollQpTooltip;
    @NonNull
    public final ShellButton camerarollButton;
    @NonNull
    public final OCTileButton castingButton;
    @NonNull
    public final QpTooltipViewBinding castingQpTooltip;
    @NonNull
    public final OCTileButton livestreamButton;
    @NonNull
    public final LinearLayout livestreamButtonContainer;
    @NonNull
    public final AnytimeTabletLoadingDotsV2Binding loadingDots;
    @Bindable
    protected SharingViewModel mViewModel;
    @NonNull
    public final LinearLayout recentCaptureTilesRow;
    @NonNull
    public final OCTextView recentTitle;
    @NonNull
    public final FrameLayout recentsNullState;
    @NonNull
    public final OCTileButton screenrecordingButton;
    @NonNull
    public final LinearLayout screenrecordingButtonContainer;
    @NonNull
    public final OCTileButton screenshotButton;
    @NonNull
    public final LinearLayout screenshotButtonContainer;
    @NonNull
    public final OCTextView title;

    public abstract void setViewModel(@Nullable SharingViewModel sharingViewModel);

    protected AnytimeTabletSharingViewV2Binding(Object obj, View view, int i, QpTooltipViewBinding qpTooltipViewBinding, ShellButton shellButton, OCTileButton oCTileButton, QpTooltipViewBinding qpTooltipViewBinding2, OCTileButton oCTileButton2, LinearLayout linearLayout, AnytimeTabletLoadingDotsV2Binding anytimeTabletLoadingDotsV2Binding, LinearLayout linearLayout2, OCTextView oCTextView, FrameLayout frameLayout, OCTileButton oCTileButton3, LinearLayout linearLayout3, OCTileButton oCTileButton4, LinearLayout linearLayout4, OCTextView oCTextView2) {
        super(obj, view, i);
        this.cameraRollQpTooltip = qpTooltipViewBinding;
        setContainedBinding(this.cameraRollQpTooltip);
        this.camerarollButton = shellButton;
        this.castingButton = oCTileButton;
        this.castingQpTooltip = qpTooltipViewBinding2;
        setContainedBinding(this.castingQpTooltip);
        this.livestreamButton = oCTileButton2;
        this.livestreamButtonContainer = linearLayout;
        this.loadingDots = anytimeTabletLoadingDotsV2Binding;
        setContainedBinding(this.loadingDots);
        this.recentCaptureTilesRow = linearLayout2;
        this.recentTitle = oCTextView;
        this.recentsNullState = frameLayout;
        this.screenrecordingButton = oCTileButton3;
        this.screenrecordingButtonContainer = linearLayout3;
        this.screenshotButton = oCTileButton4;
        this.screenshotButtonContainer = linearLayout4;
        this.title = oCTextView2;
    }

    @Nullable
    public SharingViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static AnytimeTabletSharingViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSharingViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSharingViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_sharing_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSharingViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSharingViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSharingViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_sharing_view_v2, null, false, obj);
    }

    public static AnytimeTabletSharingViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSharingViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSharingViewV2Binding) bind(obj, view, R.layout.anytime_tablet_sharing_view_v2);
    }
}
