package com.oculus.panelapp.social.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;
import com.oculus.tablet.databinding.OsigSeekbarBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialListItemV2Binding extends AnonymousClass1uW {
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
    public String mActionButtonText;
    @Bindable
    public boolean mIsInvitedUser;
    @Bindable
    public boolean mMuted;
    @Bindable
    public String mNameText;
    @Bindable
    public String mSubtitleText;
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

    @Nullable
    public String getActionButtonText() {
        return this.mActionButtonText;
    }

    public boolean getIsInvitedUser() {
        return this.mIsInvitedUser;
    }

    public boolean getMuted() {
        return this.mMuted;
    }

    @Nullable
    public String getNameText() {
        return this.mNameText;
    }

    @Nullable
    public String getSubtitleText() {
        return this.mSubtitleText;
    }

    public AnytimeTabletSocialListItemV2Binding(Object obj, View view, int i, CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, ShellButton shellButton, ShellButton shellButton2, ImageView imageView, OCSpinner oCSpinner, ShellButton shellButton3, OCTextView oCTextView, ShellButton shellButton4, OsigSeekbarBinding osigSeekbarBinding, OCSpinner oCSpinner2, ConstraintLayout constraintLayout, OCSpinner oCSpinner3, OCTextView oCTextView2) {
        super(obj, view, i);
        this.actionButton = commonTabletRectangularButtonBinding;
        setContainedBinding(commonTabletRectangularButtonBinding);
        this.callButton = shellButton;
        this.chatButton = shellButton2;
        this.icon = imageView;
        this.loadingSpinner = oCSpinner;
        this.menuButton = shellButton3;
        this.name = oCTextView;
        this.partyMute = shellButton4;
        this.partyVolumeSlider = osigSeekbarBinding;
        setContainedBinding(osigSeekbarBinding);
        this.secondaryLoadingSpinner = oCSpinner2;
        this.socialListItem = constraintLayout;
        this.startPartyLoadingSpinner = oCSpinner3;
        this.subtitle = oCTextView2;
    }

    public static AnytimeTabletSocialListItemV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletSocialListItemV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialListItemV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_social_list_item_v2);
    }

    @NonNull
    public static AnytimeTabletSocialListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletSocialListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialListItemV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_list_item_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialListItemV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_list_item_v2, null, false);
    }
}
