package com.oculus.panelapp.parties.databinding;

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
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.parties.views.PartyUserCardViewModel;
import com.oculus.socialplatform.R;

public abstract class PartyUserCardBinding extends AnonymousClass1uW {
    @NonNull
    public final View audioConnectedIcon;
    @NonNull
    public final View cardHoverOverlay;
    @NonNull
    public final OCButton ctaButton;
    @NonNull
    public final OCButton ctaLeftButton;
    @NonNull
    public final OCSpinner ctaLoadingSpinner;
    @NonNull
    public final OCButton ctaRightButton;
    @NonNull
    public final View ctaRow;
    @NonNull
    public final OCTextView groupLaunchSeparator;
    @NonNull
    public final OCTextView groupLaunchStatus;
    @Bindable
    public PartyUserCardViewModel mPartyUserCardViewModel;
    @NonNull
    public final OCButton overflowMenuButton;
    @NonNull
    public final ImageView partyMutedIndicator;
    @NonNull
    public final ImageView partyMutedIndicatorBackground;
    @NonNull
    public final ImageView partySpeakingIndicator;
    @NonNull
    public final ImageView partySpeakingIndicatorBackground;
    @NonNull
    public final ConstraintLayout partyUserCard;
    @NonNull
    public final ImageView profilePhoto;
    @NonNull
    public final View profilePhotoBorder;
    @NonNull
    public final OCTextView subtitle;
    @NonNull
    public final OCTextView username;

    public abstract void setPartyUserCardViewModel(@Nullable PartyUserCardViewModel partyUserCardViewModel);

    @Nullable
    public PartyUserCardViewModel getPartyUserCardViewModel() {
        return this.mPartyUserCardViewModel;
    }

    public PartyUserCardBinding(Object obj, View view, int i, View view2, View view3, OCButton oCButton, OCButton oCButton2, OCSpinner oCSpinner, OCButton oCButton3, View view4, OCTextView oCTextView, OCTextView oCTextView2, OCButton oCButton4, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ConstraintLayout constraintLayout, ImageView imageView5, View view5, OCTextView oCTextView3, OCTextView oCTextView4) {
        super(obj, view, i);
        this.audioConnectedIcon = view2;
        this.cardHoverOverlay = view3;
        this.ctaButton = oCButton;
        this.ctaLeftButton = oCButton2;
        this.ctaLoadingSpinner = oCSpinner;
        this.ctaRightButton = oCButton3;
        this.ctaRow = view4;
        this.groupLaunchSeparator = oCTextView;
        this.groupLaunchStatus = oCTextView2;
        this.overflowMenuButton = oCButton4;
        this.partyMutedIndicator = imageView;
        this.partyMutedIndicatorBackground = imageView2;
        this.partySpeakingIndicator = imageView3;
        this.partySpeakingIndicatorBackground = imageView4;
        this.partyUserCard = constraintLayout;
        this.profilePhoto = imageView5;
        this.profilePhotoBorder = view5;
        this.subtitle = oCTextView3;
        this.username = oCTextView4;
    }

    public static PartyUserCardBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PartyUserCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PartyUserCardBinding) AnonymousClass1uW.bind(obj, view, R.layout.party_user_card);
    }

    @NonNull
    public static PartyUserCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PartyUserCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PartyUserCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PartyUserCardBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.party_user_card, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PartyUserCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PartyUserCardBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.party_user_card, null, false);
    }
}
