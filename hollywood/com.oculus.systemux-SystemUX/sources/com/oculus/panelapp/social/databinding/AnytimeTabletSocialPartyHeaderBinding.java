package com.oculus.panelapp.social.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCSelect;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.R;
import com.oculus.tablet.databinding.OsigButtonBorderlessBinding;
import com.oculus.tablet.databinding.OsigSeekbarBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialPartyHeaderBinding extends ViewDataBinding {
    @NonNull
    public final Guideline guideline;
    @Bindable
    protected boolean mMutePartyVolume;
    @Bindable
    protected SocialParty mParty;
    @Bindable
    protected String mPartySpotsAvailable;
    @NonNull
    public final OsigButtonBorderlessBinding navigateBackButton;
    @NonNull
    public final ConstraintLayout partyHeader;
    @NonNull
    public final OCTextView partyHeaderTitle;
    @NonNull
    public final OCSelect partyPrivacy;
    @NonNull
    public final OsigSeekbarBinding partyVolumeSlider;
    @NonNull
    public final ShellButton socialEndCallButton;
    @NonNull
    public final OCTextView spotsAvailable;

    public abstract void setMutePartyVolume(boolean z);

    public abstract void setParty(@Nullable SocialParty socialParty);

    public abstract void setPartySpotsAvailable(@Nullable String str);

    protected AnytimeTabletSocialPartyHeaderBinding(Object obj, View view, int i, Guideline guideline2, OsigButtonBorderlessBinding osigButtonBorderlessBinding, ConstraintLayout constraintLayout, OCTextView oCTextView, OCSelect oCSelect, OsigSeekbarBinding osigSeekbarBinding, ShellButton shellButton, OCTextView oCTextView2) {
        super(obj, view, i);
        this.guideline = guideline2;
        this.navigateBackButton = osigButtonBorderlessBinding;
        setContainedBinding(this.navigateBackButton);
        this.partyHeader = constraintLayout;
        this.partyHeaderTitle = oCTextView;
        this.partyPrivacy = oCSelect;
        this.partyVolumeSlider = osigSeekbarBinding;
        setContainedBinding(this.partyVolumeSlider);
        this.socialEndCallButton = shellButton;
        this.spotsAvailable = oCTextView2;
    }

    @Nullable
    public SocialParty getParty() {
        return this.mParty;
    }

    @Nullable
    public String getPartySpotsAvailable() {
        return this.mPartySpotsAvailable;
    }

    public boolean getMutePartyVolume() {
        return this.mMutePartyVolume;
    }

    @NonNull
    public static AnytimeTabletSocialPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSocialPartyHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_party_header, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSocialPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSocialPartyHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_party_header, null, false, obj);
    }

    public static AnytimeTabletSocialPartyHeaderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSocialPartyHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialPartyHeaderBinding) bind(obj, view, R.layout.anytime_tablet_social_party_header);
    }
}
