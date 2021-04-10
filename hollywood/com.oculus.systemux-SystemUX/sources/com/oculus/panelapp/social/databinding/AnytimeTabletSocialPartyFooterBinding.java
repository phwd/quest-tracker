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
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;

public abstract class AnytimeTabletSocialPartyFooterBinding extends ViewDataBinding {
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
    protected SocialParty mParty;
    @Bindable
    protected String mPrimaryActionButton;
    @Bindable
    protected String mSecondaryActionButton;
    @Bindable
    protected SocialViewModel mSocialViewModel;
    @Bindable
    protected String mStatusText;
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

    protected AnytimeTabletSocialPartyFooterBinding(Object obj, View view, int i, OCTextView oCTextView, OCButton oCButton, OCTextView oCTextView2, OCTextView oCTextView3, ImageView imageView, ConstraintLayout constraintLayout, CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, OCSpinner oCSpinner, CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding2, OCTextView oCTextView4) {
        super(obj, view, i);
        this.applicationName = oCTextView;
        this.changeDestinationButton = oCButton;
        this.destinationInfoSeparator = oCTextView2;
        this.destinationName = oCTextView3;
        this.icon = imageView;
        this.partyFooter = constraintLayout;
        this.primaryActionButton = commonTabletRectangularButtonBinding;
        setContainedBinding(this.primaryActionButton);
        this.primaryActionButtonSpinner = oCSpinner;
        this.sharePartyButton = commonTabletRectangularButtonBinding2;
        setContainedBinding(this.sharePartyButton);
        this.statusText = oCTextView4;
    }

    @Nullable
    public SocialParty getParty() {
        return this.mParty;
    }

    @Nullable
    public SocialViewModel getSocialViewModel() {
        return this.mSocialViewModel;
    }

    @Nullable
    public String getStatusText() {
        return this.mStatusText;
    }

    @Nullable
    public String getPrimaryActionButton() {
        return this.mPrimaryActionButton;
    }

    @Nullable
    public String getSecondaryActionButton() {
        return this.mSecondaryActionButton;
    }

    @NonNull
    public static AnytimeTabletSocialPartyFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialPartyFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSocialPartyFooterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_party_footer, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSocialPartyFooterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialPartyFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSocialPartyFooterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_party_footer, null, false, obj);
    }

    public static AnytimeTabletSocialPartyFooterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSocialPartyFooterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialPartyFooterBinding) bind(obj, view, R.layout.anytime_tablet_social_party_footer);
    }
}
