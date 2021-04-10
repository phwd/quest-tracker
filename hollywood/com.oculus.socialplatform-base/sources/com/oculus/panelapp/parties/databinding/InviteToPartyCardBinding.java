package com.oculus.panelapp.parties.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.parties.views.InviteToPartyCardViewModel;
import com.oculus.socialplatform.R;

public abstract class InviteToPartyCardBinding extends AnonymousClass1uW {
    @NonNull
    public final OCTextView actionDescription;
    @NonNull
    public final View cardHoverOverlay;
    @NonNull
    public final ConstraintLayout inviteToPartyActionCard;
    @NonNull
    public final Group inviteToPartyHoverGroup;
    @NonNull
    public final View inviteToPartyIcon;
    @NonNull
    public final Group inviteToPartyUnhoverGroup;
    @Bindable
    public InviteToPartyCardViewModel mInviteToPartyCardViewModel;
    @NonNull
    public final OCTextView overlayActionDescription;
    @NonNull
    public final InviteToPartyCtaButtonBinding overlayFbActionCtaButton;
    @NonNull
    public final InviteToPartyCtaButtonBinding overlayOcActionCtaButton;
    @NonNull
    public final OCTextView subtitle;

    public abstract void setInviteToPartyCardViewModel(@Nullable InviteToPartyCardViewModel inviteToPartyCardViewModel);

    @Nullable
    public InviteToPartyCardViewModel getInviteToPartyCardViewModel() {
        return this.mInviteToPartyCardViewModel;
    }

    public InviteToPartyCardBinding(Object obj, View view, int i, OCTextView oCTextView, View view2, ConstraintLayout constraintLayout, Group group, View view3, Group group2, OCTextView oCTextView2, InviteToPartyCtaButtonBinding inviteToPartyCtaButtonBinding, InviteToPartyCtaButtonBinding inviteToPartyCtaButtonBinding2, OCTextView oCTextView3) {
        super(obj, view, i);
        this.actionDescription = oCTextView;
        this.cardHoverOverlay = view2;
        this.inviteToPartyActionCard = constraintLayout;
        this.inviteToPartyHoverGroup = group;
        this.inviteToPartyIcon = view3;
        this.inviteToPartyUnhoverGroup = group2;
        this.overlayActionDescription = oCTextView2;
        this.overlayFbActionCtaButton = inviteToPartyCtaButtonBinding;
        setContainedBinding(inviteToPartyCtaButtonBinding);
        this.overlayOcActionCtaButton = inviteToPartyCtaButtonBinding2;
        setContainedBinding(inviteToPartyCtaButtonBinding2);
        this.subtitle = oCTextView3;
    }

    public static InviteToPartyCardBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static InviteToPartyCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (InviteToPartyCardBinding) AnonymousClass1uW.bind(obj, view, R.layout.invite_to_party_card);
    }

    @NonNull
    public static InviteToPartyCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static InviteToPartyCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static InviteToPartyCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (InviteToPartyCardBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.invite_to_party_card, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static InviteToPartyCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (InviteToPartyCardBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.invite_to_party_card, null, false);
    }
}
