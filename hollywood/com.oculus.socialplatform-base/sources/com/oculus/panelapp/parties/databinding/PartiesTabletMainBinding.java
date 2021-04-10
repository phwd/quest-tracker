package com.oculus.panelapp.parties.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.parties.views.PartiesView;
import com.oculus.panelapp.parties.views.PartiesViewModel;
import com.oculus.socialplatform.R;

public abstract class PartiesTabletMainBinding extends AnonymousClass1uW {
    @NonNull
    public final Guideline headerGuideline;
    @NonNull
    public final OCRecyclerView horizontalRecycler;
    @Bindable
    public PartiesViewModel mPartiesViewModel;
    @NonNull
    public final PartiesView partiesView;
    @NonNull
    public final PartyTravelFooterBinding partyFooter;
    @NonNull
    public final PartiesTabletPartyHeaderBinding partyHeader;

    public abstract void setPartiesViewModel(@Nullable PartiesViewModel partiesViewModel);

    @Nullable
    public PartiesViewModel getPartiesViewModel() {
        return this.mPartiesViewModel;
    }

    public PartiesTabletMainBinding(Object obj, View view, int i, Guideline guideline, OCRecyclerView oCRecyclerView, PartiesView partiesView2, PartyTravelFooterBinding partyTravelFooterBinding, PartiesTabletPartyHeaderBinding partiesTabletPartyHeaderBinding) {
        super(obj, view, i);
        this.headerGuideline = guideline;
        this.horizontalRecycler = oCRecyclerView;
        this.partiesView = partiesView2;
        this.partyFooter = partyTravelFooterBinding;
        setContainedBinding(partyTravelFooterBinding);
        this.partyHeader = partiesTabletPartyHeaderBinding;
        setContainedBinding(partiesTabletPartyHeaderBinding);
    }

    public static PartiesTabletMainBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static PartiesTabletMainBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PartiesTabletMainBinding) AnonymousClass1uW.bind(obj, view, R.layout.parties_tablet_main);
    }

    @NonNull
    public static PartiesTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static PartiesTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static PartiesTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PartiesTabletMainBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.parties_tablet_main, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static PartiesTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (PartiesTabletMainBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.parties_tablet_main, null, false);
    }
}
