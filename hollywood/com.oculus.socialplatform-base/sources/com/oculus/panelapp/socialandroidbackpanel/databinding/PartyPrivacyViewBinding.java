package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyView;
import com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel;
import com.oculus.socialplatform.R;

public abstract class PartyPrivacyViewBinding extends AnonymousClass1uW {
    @NonNull
    public final PartyPrivacyMenuItemToggleBinding joinPolicyToggleMenuItem;
    @NonNull
    public final PartyPrivacyMenuItemToggleBinding linkPolicyToggleMenuItem;
    @Bindable
    public PartyPrivacyViewModel mPartyPrivacyViewModel;
    @NonNull
    public final OCButton partyPrivacyCloseButton;
    @NonNull
    public final OCTextView partyPrivacyLink;
    @NonNull
    public final View partyPrivacyLinkAndShare;
    @NonNull
    public final OCTextView partyPrivacyLinkTitle;
    @NonNull
    public final OCButton partyPrivacyShareButton;
    @NonNull
    public final OCTextView partyPrivacyTitle;
    @NonNull
    public final PartyPrivacyView partyPrivacyView;

    public abstract void setPartyPrivacyViewModel(@Nullable PartyPrivacyViewModel partyPrivacyViewModel);

    @Nullable
    public PartyPrivacyViewModel getPartyPrivacyViewModel() {
        return this.mPartyPrivacyViewModel;
    }

    public PartyPrivacyViewBinding(Object obj, View view, int i, PartyPrivacyMenuItemToggleBinding partyPrivacyMenuItemToggleBinding, PartyPrivacyMenuItemToggleBinding partyPrivacyMenuItemToggleBinding2, OCButton oCButton, OCTextView oCTextView, View view2, OCTextView oCTextView2, OCButton oCButton2, OCTextView oCTextView3, PartyPrivacyView partyPrivacyView2) {
        super(obj, view, i);
        this.joinPolicyToggleMenuItem = partyPrivacyMenuItemToggleBinding;
        setContainedBinding(partyPrivacyMenuItemToggleBinding);
        this.linkPolicyToggleMenuItem = partyPrivacyMenuItemToggleBinding2;
        setContainedBinding(partyPrivacyMenuItemToggleBinding2);
        this.partyPrivacyCloseButton = oCButton;
        this.partyPrivacyLink = oCTextView;
        this.partyPrivacyLinkAndShare = view2;
        this.partyPrivacyLinkTitle = oCTextView2;
        this.partyPrivacyShareButton = oCButton2;
        this.partyPrivacyTitle = oCTextView3;
        this.partyPrivacyView = partyPrivacyView2;
    }

    public static PartyPrivacyViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PartyPrivacyViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PartyPrivacyViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.party_privacy_view);
    }

    @NonNull
    public static PartyPrivacyViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PartyPrivacyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PartyPrivacyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PartyPrivacyViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.party_privacy_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PartyPrivacyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PartyPrivacyViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.party_privacy_view, null, false);
    }
}
