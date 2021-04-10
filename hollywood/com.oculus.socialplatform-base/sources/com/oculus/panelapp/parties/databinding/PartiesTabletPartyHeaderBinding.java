package com.oculus.panelapp.parties.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.parties.views.PartiesViewModel;
import com.oculus.socialplatform.R;
import com.oculus.tablet.databinding.OsigButtonBorderlessBinding;
import com.oculus.tablet.databinding.OsigSeekbarBinding;

public abstract class PartiesTabletPartyHeaderBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton endCallButton;
    @Bindable
    public PartiesViewModel mPartiesViewModel;
    @NonNull
    public final OsigButtonBorderlessBinding navigateBackButton;
    @NonNull
    public final ConstraintLayout partyHeader;
    @NonNull
    public final OCTextView partyHeaderTitle;
    @NonNull
    public final OsigSeekbarBinding partyVolumeSlider;
    @NonNull
    public final OCButton privacySettingsButton;
    @NonNull
    public final OCTextView spotsAvailable;

    public abstract void setPartiesViewModel(@Nullable PartiesViewModel partiesViewModel);

    @Nullable
    public PartiesViewModel getPartiesViewModel() {
        return this.mPartiesViewModel;
    }

    public PartiesTabletPartyHeaderBinding(Object obj, View view, int i, OCButton oCButton, OsigButtonBorderlessBinding osigButtonBorderlessBinding, ConstraintLayout constraintLayout, OCTextView oCTextView, OsigSeekbarBinding osigSeekbarBinding, OCButton oCButton2, OCTextView oCTextView2) {
        super(obj, view, i);
        this.endCallButton = oCButton;
        this.navigateBackButton = osigButtonBorderlessBinding;
        setContainedBinding(osigButtonBorderlessBinding);
        this.partyHeader = constraintLayout;
        this.partyHeaderTitle = oCTextView;
        this.partyVolumeSlider = osigSeekbarBinding;
        setContainedBinding(osigSeekbarBinding);
        this.privacySettingsButton = oCButton2;
        this.spotsAvailable = oCTextView2;
    }

    public static PartiesTabletPartyHeaderBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PartiesTabletPartyHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PartiesTabletPartyHeaderBinding) AnonymousClass1uW.bind(obj, view, R.layout.parties_tablet_party_header);
    }

    @NonNull
    public static PartiesTabletPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PartiesTabletPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PartiesTabletPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PartiesTabletPartyHeaderBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.parties_tablet_party_header, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PartiesTabletPartyHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PartiesTabletPartyHeaderBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.parties_tablet_party_header, null, false);
    }
}
