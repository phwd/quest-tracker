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
import com.oculus.panelapp.parties.views.PartyTravelFooterViewModel;
import com.oculus.socialplatform.R;

public abstract class PartyTravelFooterBinding extends AnonymousClass1uW {
    @NonNull
    public final OCTextView applicationName;
    @NonNull
    public final OCButton changeDestinationButton;
    @NonNull
    public final OCTextView destinationInfoSeparator;
    @NonNull
    public final OCTextView destinationName;
    @Bindable
    public PartyTravelFooterViewModel mPartyTravelFooterViewModel;
    @NonNull
    public final ConstraintLayout partyFooter;
    @NonNull
    public final OCButton primaryActionButton;
    @NonNull
    public final OCSpinner primaryActionButtonSpinner;
    @NonNull
    public final OCButton secondaryActionButton;
    @NonNull
    public final OCSpinner secondaryActionButtonSpinner;
    @NonNull
    public final OCTextView statusText;
    @NonNull
    public final ImageView travelFooterPreview;

    public abstract void setPartyTravelFooterViewModel(@Nullable PartyTravelFooterViewModel partyTravelFooterViewModel);

    @Nullable
    public PartyTravelFooterViewModel getPartyTravelFooterViewModel() {
        return this.mPartyTravelFooterViewModel;
    }

    public PartyTravelFooterBinding(Object obj, View view, int i, OCTextView oCTextView, OCButton oCButton, OCTextView oCTextView2, OCTextView oCTextView3, ConstraintLayout constraintLayout, OCButton oCButton2, OCSpinner oCSpinner, OCButton oCButton3, OCSpinner oCSpinner2, OCTextView oCTextView4, ImageView imageView) {
        super(obj, view, i);
        this.applicationName = oCTextView;
        this.changeDestinationButton = oCButton;
        this.destinationInfoSeparator = oCTextView2;
        this.destinationName = oCTextView3;
        this.partyFooter = constraintLayout;
        this.primaryActionButton = oCButton2;
        this.primaryActionButtonSpinner = oCSpinner;
        this.secondaryActionButton = oCButton3;
        this.secondaryActionButtonSpinner = oCSpinner2;
        this.statusText = oCTextView4;
        this.travelFooterPreview = imageView;
    }

    public static PartyTravelFooterBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PartyTravelFooterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PartyTravelFooterBinding) AnonymousClass1uW.bind(obj, view, R.layout.party_travel_footer);
    }

    @NonNull
    public static PartyTravelFooterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PartyTravelFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PartyTravelFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PartyTravelFooterBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.party_travel_footer, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PartyTravelFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PartyTravelFooterBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.party_travel_footer, null, false);
    }
}
