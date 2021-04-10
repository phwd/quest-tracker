package com.oculus.panelapp.social.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialView;

public abstract class AnytimeTabletSocialViewV2Binding extends ViewDataBinding {
    @NonNull
    public final AnytimeTabletSocialFriendsHeaderBinding friendsHeader;
    @NonNull
    public final OCRecyclerView friendsList;
    @Bindable
    protected boolean mCanShowStartParty;
    @Bindable
    protected boolean mMuted;
    @Bindable
    protected SocialParty mParty;
    @Bindable
    protected boolean mShowLoading;
    @Bindable
    protected boolean mShowPartyFooter;
    @Bindable
    protected SocialView.SocialViewType mView;
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

    protected AnytimeTabletSocialViewV2Binding(Object obj, View view, int i, AnytimeTabletSocialFriendsHeaderBinding anytimeTabletSocialFriendsHeaderBinding, OCRecyclerView oCRecyclerView, AnytimeTabletSocialPartyFooterBinding anytimeTabletSocialPartyFooterBinding, AnytimeTabletSocialPartyHeaderBinding anytimeTabletSocialPartyHeaderBinding, OCRecyclerView oCRecyclerView2, OCSpinner oCSpinner) {
        super(obj, view, i);
        this.friendsHeader = anytimeTabletSocialFriendsHeaderBinding;
        setContainedBinding(this.friendsHeader);
        this.friendsList = oCRecyclerView;
        this.partyFooter = anytimeTabletSocialPartyFooterBinding;
        setContainedBinding(this.partyFooter);
        this.partyHeader = anytimeTabletSocialPartyHeaderBinding;
        setContainedBinding(this.partyHeader);
        this.partyList = oCRecyclerView2;
        this.secondaryLoadingSpinner = oCSpinner;
    }

    @Nullable
    public SocialParty getParty() {
        return this.mParty;
    }

    @Nullable
    public SocialView.SocialViewType getView() {
        return this.mView;
    }

    public boolean getMuted() {
        return this.mMuted;
    }

    public boolean getShowPartyFooter() {
        return this.mShowPartyFooter;
    }

    public boolean getCanShowStartParty() {
        return this.mCanShowStartParty;
    }

    public boolean getShowLoading() {
        return this.mShowLoading;
    }

    @NonNull
    public static AnytimeTabletSocialViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSocialViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSocialViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSocialViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_view_v2, null, false, obj);
    }

    public static AnytimeTabletSocialViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSocialViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialViewV2Binding) bind(obj, view, R.layout.anytime_tablet_social_view_v2);
    }
}
