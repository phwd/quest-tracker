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
import com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyView;
import com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel;
import com.oculus.panelapp.socialandroidbackpanel.views.list_item.ListItem;
import com.oculus.socialplatform.R;

public abstract class JoinPartyViewBinding extends AnonymousClass1uW {
    @NonNull
    public final JoinPartyView joinParty;
    @NonNull
    public final ListItem joinPartyDestinationRow;
    @NonNull
    public final ListItem joinPartyJoiningAsRow;
    @NonNull
    public final ListItem joinPartyPartyDescriptionRow;
    @NonNull
    public final OCButton joinPartyPrimaryButton;
    @NonNull
    public final OCButton joinPartySecondaryButton;
    @NonNull
    public final OCTextView joinPartyTitle;
    @Bindable
    public JoinPartyViewModel mJoinPartyViewModel;

    public abstract void setJoinPartyViewModel(@Nullable JoinPartyViewModel joinPartyViewModel);

    @Nullable
    public JoinPartyViewModel getJoinPartyViewModel() {
        return this.mJoinPartyViewModel;
    }

    public JoinPartyViewBinding(Object obj, View view, int i, JoinPartyView joinPartyView, ListItem listItem, ListItem listItem2, ListItem listItem3, OCButton oCButton, OCButton oCButton2, OCTextView oCTextView) {
        super(obj, view, i);
        this.joinParty = joinPartyView;
        this.joinPartyDestinationRow = listItem;
        this.joinPartyJoiningAsRow = listItem2;
        this.joinPartyPartyDescriptionRow = listItem3;
        this.joinPartyPrimaryButton = oCButton;
        this.joinPartySecondaryButton = oCButton2;
        this.joinPartyTitle = oCTextView;
    }

    public static JoinPartyViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static JoinPartyViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (JoinPartyViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.join_party_view);
    }

    @NonNull
    public static JoinPartyViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static JoinPartyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static JoinPartyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (JoinPartyViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.join_party_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static JoinPartyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (JoinPartyViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.join_party_view, null, false);
    }
}
