package com.oculus.panelapp.social.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCSelect;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.tablet.databinding.OsigButtonBorderlessBinding;
import com.oculus.tablet.databinding.OsigSeekbarBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialPartyHeaderBinding extends AnonymousClass1uW {
    @NonNull
    public final Guideline guideline;
    @Bindable
    public boolean mMutePartyVolume;
    @Bindable
    public SocialParty mParty;
    @Bindable
    public String mPartySpotsAvailable;
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

    public boolean getMutePartyVolume() {
        return this.mMutePartyVolume;
    }

    @Nullable
    public SocialParty getParty() {
        return this.mParty;
    }

    @Nullable
    public String getPartySpotsAvailable() {
        return this.mPartySpotsAvailable;
    }

    public AnytimeTabletSocialPartyHeaderBinding(Object obj, View view, int i, Guideline guideline2, OsigButtonBorderlessBinding osigButtonBorderlessBinding, ConstraintLayout constraintLayout, OCTextView oCTextView, OCSelect oCSelect, OsigSeekbarBinding osigSeekbarBinding, ShellButton shellButton, OCTextView oCTextView2) {
        super(obj, view, i);
        this.guideline = guideline2;
        this.navigateBackButton = osigButtonBorderlessBinding;
        setContainedBinding(osigButtonBorderlessBinding);
        this.partyHeader = constraintLayout;
        this.partyHeaderTitle = oCTextView;
        this.partyPrivacy = oCSelect;
        this.partyVolumeSlider = osigSeekbarBinding;
        setContainedBinding(osigSeekbarBinding);
        this.socialEndCallButton = shellButton;
        this.spotsAvailable = oCTextView2;
    }

    public static AnytimeTabletSocialPartyHeaderBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletSocialPartyHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialPartyHeaderBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_social_party_header);
    }

    @NonNull
    public static AnytimeTabletSocialPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletSocialPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialPartyHeaderBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_party_header, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialPartyHeaderBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_party_header, null, false);
    }
}
