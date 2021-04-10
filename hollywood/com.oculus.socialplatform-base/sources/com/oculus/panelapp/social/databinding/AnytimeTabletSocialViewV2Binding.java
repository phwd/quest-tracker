package com.oculus.panelapp.social.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.panelapp.social.SocialView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletSocialViewV2Binding extends AnonymousClass1uW {
    @NonNull
    public final AnytimeTabletSocialFriendsHeaderBinding friendsHeader;
    @NonNull
    public final OCRecyclerView friendsList;
    @Bindable
    public boolean mCanShowStartParty;
    @Bindable
    public boolean mMuted;
    @Bindable
    public SocialParty mParty;
    @Bindable
    public boolean mShowLoading;
    @Bindable
    public boolean mShowPartyFooter;
    @Bindable
    public SocialView.SocialViewType mView;
    @NonNull
    public final AnytimeTabletSocialPartyFooterBinding partyFooter;
    @NonNull
    public final AnytimeTabletSocialPartyHeaderBinding partyHeader;
    @NonNull
    public final OCRecyclerView partyList;
    @NonNull
    public final OCSpinner secondaryLoadingSpinner;

    public abstract void setCanShowStartParty(boolean z);

    public abstract void setMuted(boolean z);

    public abstract void setParty(@Nullable SocialParty socialParty);

    public abstract void setShowLoading(boolean z);

    public abstract void setShowPartyFooter(boolean z);

    public abstract void setView(@Nullable SocialView.SocialViewType socialViewType);

    public boolean getCanShowStartParty() {
        return this.mCanShowStartParty;
    }

    public boolean getMuted() {
        return this.mMuted;
    }

    @Nullable
    public SocialParty getParty() {
        return this.mParty;
    }

    public boolean getShowLoading() {
        return this.mShowLoading;
    }

    public boolean getShowPartyFooter() {
        return this.mShowPartyFooter;
    }

    @Nullable
    public SocialView.SocialViewType getView() {
        return this.mView;
    }

    public AnytimeTabletSocialViewV2Binding(Object obj, View view, int i, AnytimeTabletSocialFriendsHeaderBinding anytimeTabletSocialFriendsHeaderBinding, OCRecyclerView oCRecyclerView, AnytimeTabletSocialPartyFooterBinding anytimeTabletSocialPartyFooterBinding, AnytimeTabletSocialPartyHeaderBinding anytimeTabletSocialPartyHeaderBinding, OCRecyclerView oCRecyclerView2, OCSpinner oCSpinner) {
        super(obj, view, i);
        this.friendsHeader = anytimeTabletSocialFriendsHeaderBinding;
        setContainedBinding(anytimeTabletSocialFriendsHeaderBinding);
        this.friendsList = oCRecyclerView;
        this.partyFooter = anytimeTabletSocialPartyFooterBinding;
        setContainedBinding(anytimeTabletSocialPartyFooterBinding);
        this.partyHeader = anytimeTabletSocialPartyHeaderBinding;
        setContainedBinding(anytimeTabletSocialPartyHeaderBinding);
        this.partyList = oCRecyclerView2;
        this.secondaryLoadingSpinner = oCSpinner;
    }

    public static AnytimeTabletSocialViewV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletSocialViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialViewV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_social_view_v2);
    }

    @NonNull
    public static AnytimeTabletSocialViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletSocialViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialViewV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_view_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialViewV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_view_v2, null, false);
    }
}
