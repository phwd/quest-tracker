package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCHighlight;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull;
import com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarSimple;
import com.oculus.panelapp.anytimeui.v2.bar.BarView;
import com.oculus.panelapp.anytimeui.v2.bar.BarViewModel;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusView;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIViewModel;
import com.oculus.panelapp.social.SocialViewModel;

public abstract class AnytimeBarViewV2Binding extends ViewDataBinding {
    @NonNull
    public final ActiveCallBarFull activeCallBarFull;
    @NonNull
    public final ActiveCallBarSimple activeCallBarSimple;
    @NonNull
    public final OCButton activeCallBarSimpleExpandedTapTarget;
    @NonNull
    public final BarView barView;
    @NonNull
    public final View leftEdge;
    @NonNull
    public final OCHighlight libraryHighlight;
    @Bindable
    protected BarViewModel mBarViewModel;
    @Bindable
    protected DestinationUIViewModel mDestinationUIViewModel;
    @Bindable
    protected SocialViewModel mSocialViewModel;
    @NonNull
    public final AnytimeBarActiveAppButtonV2Binding navigationButtonDestinationUi;
    @NonNull
    public final AnytimeBarNavigationButtonV2Binding navigationButtonLibrary;
    @NonNull
    public final AnytimeBarNavigationButtonV2Binding navigationButtonMessenger;
    @NonNull
    public final AnytimeBarNavigationButtonV2Binding navigationButtonNotifications;
    @NonNull
    public final OCButton navigationButtonProfile;
    @NonNull
    public final AnytimeBarNavigationButtonV2Binding navigationButtonSettings;
    @NonNull
    public final AnytimeBarNavigationButtonV2Binding navigationButtonSharing;
    @NonNull
    public final AnytimeBarNavigationButtonV2Binding navigationButtonSocial;
    @NonNull
    public final AnytimeBarNavigationStoreButtonV2Binding navigationButtonStoreLeft;
    @NonNull
    public final AnytimeBarNavigationStoreButtonV2Binding navigationButtonStoreRight;
    @NonNull
    public final SimpleDraweeView profileImageView;
    @NonNull
    public final View profileOnlinePresenceDot;
    @NonNull
    public final View profileOnlinePresenceDotEnterprise;
    @NonNull
    public final View rightEdge;
    @NonNull
    public final StatusView statusBarV2;
    @NonNull
    public final View storeDivider;

    public abstract void setBarViewModel(@Nullable BarViewModel barViewModel);

    public abstract void setDestinationUIViewModel(@Nullable DestinationUIViewModel destinationUIViewModel);

    public abstract void setSocialViewModel(@Nullable SocialViewModel socialViewModel);

    protected AnytimeBarViewV2Binding(Object obj, View view, int i, ActiveCallBarFull activeCallBarFull2, ActiveCallBarSimple activeCallBarSimple2, OCButton oCButton, BarView barView2, View view2, OCHighlight oCHighlight, AnytimeBarActiveAppButtonV2Binding anytimeBarActiveAppButtonV2Binding, AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding, AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding2, AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding3, OCButton oCButton2, AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding4, AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding5, AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding6, AnytimeBarNavigationStoreButtonV2Binding anytimeBarNavigationStoreButtonV2Binding, AnytimeBarNavigationStoreButtonV2Binding anytimeBarNavigationStoreButtonV2Binding2, SimpleDraweeView simpleDraweeView, View view3, View view4, View view5, StatusView statusView, View view6) {
        super(obj, view, i);
        this.activeCallBarFull = activeCallBarFull2;
        this.activeCallBarSimple = activeCallBarSimple2;
        this.activeCallBarSimpleExpandedTapTarget = oCButton;
        this.barView = barView2;
        this.leftEdge = view2;
        this.libraryHighlight = oCHighlight;
        this.navigationButtonDestinationUi = anytimeBarActiveAppButtonV2Binding;
        setContainedBinding(this.navigationButtonDestinationUi);
        this.navigationButtonLibrary = anytimeBarNavigationButtonV2Binding;
        setContainedBinding(this.navigationButtonLibrary);
        this.navigationButtonMessenger = anytimeBarNavigationButtonV2Binding2;
        setContainedBinding(this.navigationButtonMessenger);
        this.navigationButtonNotifications = anytimeBarNavigationButtonV2Binding3;
        setContainedBinding(this.navigationButtonNotifications);
        this.navigationButtonProfile = oCButton2;
        this.navigationButtonSettings = anytimeBarNavigationButtonV2Binding4;
        setContainedBinding(this.navigationButtonSettings);
        this.navigationButtonSharing = anytimeBarNavigationButtonV2Binding5;
        setContainedBinding(this.navigationButtonSharing);
        this.navigationButtonSocial = anytimeBarNavigationButtonV2Binding6;
        setContainedBinding(this.navigationButtonSocial);
        this.navigationButtonStoreLeft = anytimeBarNavigationStoreButtonV2Binding;
        setContainedBinding(this.navigationButtonStoreLeft);
        this.navigationButtonStoreRight = anytimeBarNavigationStoreButtonV2Binding2;
        setContainedBinding(this.navigationButtonStoreRight);
        this.profileImageView = simpleDraweeView;
        this.profileOnlinePresenceDot = view3;
        this.profileOnlinePresenceDotEnterprise = view4;
        this.rightEdge = view5;
        this.statusBarV2 = statusView;
        this.storeDivider = view6;
    }

    @Nullable
    public BarViewModel getBarViewModel() {
        return this.mBarViewModel;
    }

    @Nullable
    public DestinationUIViewModel getDestinationUIViewModel() {
        return this.mDestinationUIViewModel;
    }

    @Nullable
    public SocialViewModel getSocialViewModel() {
        return this.mSocialViewModel;
    }

    @NonNull
    public static AnytimeBarViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeBarViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeBarViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeBarViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_view_v2, null, false, obj);
    }

    public static AnytimeBarViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeBarViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeBarViewV2Binding) bind(obj, view, R.layout.anytime_bar_view_v2);
    }
}
