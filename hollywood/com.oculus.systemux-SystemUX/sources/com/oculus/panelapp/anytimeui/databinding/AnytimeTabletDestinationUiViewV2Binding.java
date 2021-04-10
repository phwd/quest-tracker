package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingViewModel;
import com.oculus.tablet.databinding.OsigButtonBorderlessBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletDestinationUiViewV2Binding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout appDetailRow;
    @NonNull
    public final SimpleDraweeView appImage;
    @NonNull
    public final ImageView appScreenshot;
    @NonNull
    public final OCTextView appTitle;
    @NonNull
    public final OsigButtonBorderlessBinding castingButton;
    @NonNull
    public final OsigButtonBorderlessBinding inviteButton;
    @NonNull
    public final Guideline leftGuideline;
    @NonNull
    public final OsigButtonBorderlessBinding livestreamButton;
    @NonNull
    public final LinearLayout livestreamButtonContainer;
    @Bindable
    protected DestinationUIViewModel mDestinationUIViewModel;
    @Bindable
    protected SharingViewModel mSharingViewModel;
    @NonNull
    public final OCTextView parentAppTitle;
    @NonNull
    public final ShellButton quitButton;
    @NonNull
    public final OsigButtonBorderlessBinding reportButton;
    @NonNull
    public final ShellButton restartButton;
    @NonNull
    public final ShellButton resumeButton;
    @NonNull
    public final Guideline rightGuideline;
    @NonNull
    public final OsigButtonBorderlessBinding screenrecordingButton;
    @NonNull
    public final LinearLayout screenrecordingButtonContainer;
    @NonNull
    public final OsigButtonBorderlessBinding screenshotButton;
    @NonNull
    public final LinearLayout screenshotButtonContainer;

    public abstract void setDestinationUIViewModel(@Nullable DestinationUIViewModel destinationUIViewModel);

    public abstract void setSharingViewModel(@Nullable SharingViewModel sharingViewModel);

    protected AnytimeTabletDestinationUiViewV2Binding(Object obj, View view, int i, ConstraintLayout constraintLayout, SimpleDraweeView simpleDraweeView, ImageView imageView, OCTextView oCTextView, OsigButtonBorderlessBinding osigButtonBorderlessBinding, OsigButtonBorderlessBinding osigButtonBorderlessBinding2, Guideline guideline, OsigButtonBorderlessBinding osigButtonBorderlessBinding3, LinearLayout linearLayout, OCTextView oCTextView2, ShellButton shellButton, OsigButtonBorderlessBinding osigButtonBorderlessBinding4, ShellButton shellButton2, ShellButton shellButton3, Guideline guideline2, OsigButtonBorderlessBinding osigButtonBorderlessBinding5, LinearLayout linearLayout2, OsigButtonBorderlessBinding osigButtonBorderlessBinding6, LinearLayout linearLayout3) {
        super(obj, view, i);
        this.appDetailRow = constraintLayout;
        this.appImage = simpleDraweeView;
        this.appScreenshot = imageView;
        this.appTitle = oCTextView;
        this.castingButton = osigButtonBorderlessBinding;
        setContainedBinding(this.castingButton);
        this.inviteButton = osigButtonBorderlessBinding2;
        setContainedBinding(this.inviteButton);
        this.leftGuideline = guideline;
        this.livestreamButton = osigButtonBorderlessBinding3;
        setContainedBinding(this.livestreamButton);
        this.livestreamButtonContainer = linearLayout;
        this.parentAppTitle = oCTextView2;
        this.quitButton = shellButton;
        this.reportButton = osigButtonBorderlessBinding4;
        setContainedBinding(this.reportButton);
        this.restartButton = shellButton2;
        this.resumeButton = shellButton3;
        this.rightGuideline = guideline2;
        this.screenrecordingButton = osigButtonBorderlessBinding5;
        setContainedBinding(this.screenrecordingButton);
        this.screenrecordingButtonContainer = linearLayout2;
        this.screenshotButton = osigButtonBorderlessBinding6;
        setContainedBinding(this.screenshotButton);
        this.screenshotButtonContainer = linearLayout3;
    }

    @Nullable
    public DestinationUIViewModel getDestinationUIViewModel() {
        return this.mDestinationUIViewModel;
    }

    @Nullable
    public SharingViewModel getSharingViewModel() {
        return this.mSharingViewModel;
    }

    @NonNull
    public static AnytimeTabletDestinationUiViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletDestinationUiViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletDestinationUiViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_destination_ui_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletDestinationUiViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletDestinationUiViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletDestinationUiViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_destination_ui_view_v2, null, false, obj);
    }

    public static AnytimeTabletDestinationUiViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletDestinationUiViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletDestinationUiViewV2Binding) bind(obj, view, R.layout.anytime_tablet_destination_ui_view_v2);
    }
}
