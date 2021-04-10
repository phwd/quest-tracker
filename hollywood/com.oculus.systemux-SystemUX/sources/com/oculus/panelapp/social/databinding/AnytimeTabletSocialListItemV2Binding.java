package com.oculus.panelapp.social.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;
import com.oculus.tablet.databinding.OsigSeekbarBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialListItemV2Binding extends ViewDataBinding {
    @NonNull
    public final CommonTabletRectangularButtonBinding actionButton;
    @NonNull
    public final ShellButton callButton;
    @NonNull
    public final ShellButton chatButton;
    @NonNull
    public final ImageView icon;
    @NonNull
    public final OCSpinner loadingSpinner;
    @Bindable
    protected String mActionButtonText;
    @Bindable
    protected boolean mIsInvitedUser;
    @Bindable
    protected boolean mMuted;
    @Bindable
    protected String mNameText;
    @Bindable
    protected String mSubtitleText;
    @NonNull
    public final ShellButton menuButton;
    @NonNull
    public final OCTextView name;
    @NonNull
    public final ShellButton partyMute;
    @NonNull
    public final OsigSeekbarBinding partyVolumeSlider;
    @NonNull
    public final OCSpinner secondaryLoadingSpinner;
    @NonNull
    public final ConstraintLayout socialListItem;
    @NonNull
    public final OCSpinner startPartyLoadingSpinner;
    @NonNull
    public final OCTextView subtitle;

    public abstract void setActionButtonText(@Nullable String str);

    public abstract void setIsInvitedUser(boolean z);

    public abstract void setMuted(boolean z);

    public abstract void setNameText(@Nullable String str);

    public abstract void setSubtitleText(@Nullable String str);

    protected AnytimeTabletSocialListItemV2Binding(Object obj, View view, int i, CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, ShellButton shellButton, ShellButton shellButton2, ImageView imageView, OCSpinner oCSpinner, ShellButton shellButton3, OCTextView oCTextView, ShellButton shellButton4, OsigSeekbarBinding osigSeekbarBinding, OCSpinner oCSpinner2, ConstraintLayout constraintLayout, OCSpinner oCSpinner3, OCTextView oCTextView2) {
        super(obj, view, i);
        this.actionButton = commonTabletRectangularButtonBinding;
        setContainedBinding(this.actionButton);
        this.callButton = shellButton;
        this.chatButton = shellButton2;
        this.icon = imageView;
        this.loadingSpinner = oCSpinner;
        this.menuButton = shellButton3;
        this.name = oCTextView;
        this.partyMute = shellButton4;
        this.partyVolumeSlider = osigSeekbarBinding;
        setContainedBinding(this.partyVolumeSlider);
        this.secondaryLoadingSpinner = oCSpinner2;
        this.socialListItem = constraintLayout;
        this.startPartyLoadingSpinner = oCSpinner3;
        this.subtitle = oCTextView2;
    }

    @Nullable
    public String getNameText() {
        return this.mNameText;
    }

    @Nullable
    public String getSubtitleText() {
        return this.mSubtitleText;
    }

    @Nullable
    public String getActionButtonText() {
        return this.mActionButtonText;
    }

    public boolean getMuted() {
        return this.mMuted;
    }

    public boolean getIsInvitedUser() {
        return this.mIsInvitedUser;
    }

    @NonNull
    public static AnytimeTabletSocialListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSocialListItemV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_list_item_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSocialListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSocialListItemV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_list_item_v2, null, false, obj);
    }

    public static AnytimeTabletSocialListItemV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSocialListItemV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialListItemV2Binding) bind(obj, view, R.layout.anytime_tablet_social_list_item_v2);
    }
}
