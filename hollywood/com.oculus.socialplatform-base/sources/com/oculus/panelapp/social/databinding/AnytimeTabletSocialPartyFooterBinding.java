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
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.socialplatform.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;

public abstract class AnytimeTabletSocialPartyFooterBinding extends AnonymousClass1uW {
    @NonNull
    public final OCTextView applicationName;
    @NonNull
    public final OCButton changeDestinationButton;
    @NonNull
    public final OCTextView destinationInfoSeparator;
    @NonNull
    public final OCTextView destinationName;
    @NonNull
    public final ImageView icon;
    @Bindable
    public SocialParty mParty;
    @Bindable
    public String mPrimaryActionButton;
    @Bindable
    public String mSecondaryActionButton;
    @Bindable
    public SocialViewModel mSocialViewModel;
    @Bindable
    public String mStatusText;
    @NonNull
    public final ConstraintLayout partyFooter;
    @NonNull
    public final CommonTabletRectangularButtonBinding primaryActionButton;
    @NonNull
    public final OCSpinner primaryActionButtonSpinner;
    @NonNull
    public final CommonTabletRectangularButtonBinding sharePartyButton;
    @NonNull
    public final OCTextView statusText;

    public abstract void setParty(@Nullable SocialParty socialParty);

    public abstract void setPrimaryActionButton(@Nullable String str);

    public abstract void setSecondaryActionButton(@Nullable String str);

    public abstract void setSocialViewModel(@Nullable SocialViewModel socialViewModel);

    public abstract void setStatusText(@Nullable String str);

    @Nullable
    public SocialParty getParty() {
        return this.mParty;
    }

    @Nullable
    public String getPrimaryActionButton() {
        return this.mPrimaryActionButton;
    }

    @Nullable
    public String getSecondaryActionButton() {
        return this.mSecondaryActionButton;
    }

    @Nullable
    public SocialViewModel getSocialViewModel() {
        return this.mSocialViewModel;
    }

    @Nullable
    public String getStatusText() {
        return this.mStatusText;
    }

    public AnytimeTabletSocialPartyFooterBinding(Object obj, View view, int i, OCTextView oCTextView, OCButton oCButton, OCTextView oCTextView2, OCTextView oCTextView3, ImageView imageView, ConstraintLayout constraintLayout, CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, OCSpinner oCSpinner, CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding2, OCTextView oCTextView4) {
        super(obj, view, i);
        this.applicationName = oCTextView;
        this.changeDestinationButton = oCButton;
        this.destinationInfoSeparator = oCTextView2;
        this.destinationName = oCTextView3;
        this.icon = imageView;
        this.partyFooter = constraintLayout;
        this.primaryActionButton = commonTabletRectangularButtonBinding;
        setContainedBinding(commonTabletRectangularButtonBinding);
        this.primaryActionButtonSpinner = oCSpinner;
        this.sharePartyButton = commonTabletRectangularButtonBinding2;
        setContainedBinding(commonTabletRectangularButtonBinding2);
        this.statusText = oCTextView4;
    }

    public static AnytimeTabletSocialPartyFooterBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletSocialPartyFooterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialPartyFooterBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_social_party_footer);
    }

    @NonNull
    public static AnytimeTabletSocialPartyFooterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletSocialPartyFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialPartyFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialPartyFooterBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_party_footer, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialPartyFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialPartyFooterBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_party_footer, null, false);
    }
}
