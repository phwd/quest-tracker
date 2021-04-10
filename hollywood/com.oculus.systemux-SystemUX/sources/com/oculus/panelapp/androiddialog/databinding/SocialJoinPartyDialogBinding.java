package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.join_party.SocialJoinPartyDialog;
import com.oculus.panelapp.androiddialog.dialogs.social.list_item.SocialListItem;

public abstract class SocialJoinPartyDialogBinding extends ViewDataBinding {
    @Bindable
    protected PartyInviteViewModel mPartyViewModel;
    @Bindable
    protected SocialUserViewModel mViewerViewModel;
    @NonNull
    public final SocialListItem socialJoinPartyDestinationRow;
    @NonNull
    public final SocialJoinPartyDialog socialJoinPartyDialog;
    @NonNull
    public final SocialListItem socialJoinPartyJoiningAsRow;
    @NonNull
    public final SocialListItem socialJoinPartyPartyDescriptionRow;
    @NonNull
    public final OCButton socialJoinPartyPrimaryButton;
    @NonNull
    public final OCButton socialJoinPartySecondaryButton;
    @NonNull
    public final OCTextView socialJoinPartyTitle;

    public abstract void setPartyViewModel(@Nullable PartyInviteViewModel partyInviteViewModel);

    public abstract void setViewerViewModel(@Nullable SocialUserViewModel socialUserViewModel);

    protected SocialJoinPartyDialogBinding(Object obj, View view, int i, SocialListItem socialListItem, SocialJoinPartyDialog socialJoinPartyDialog2, SocialListItem socialListItem2, SocialListItem socialListItem3, OCButton oCButton, OCButton oCButton2, OCTextView oCTextView) {
        super(obj, view, i);
        this.socialJoinPartyDestinationRow = socialListItem;
        this.socialJoinPartyDialog = socialJoinPartyDialog2;
        this.socialJoinPartyJoiningAsRow = socialListItem2;
        this.socialJoinPartyPartyDescriptionRow = socialListItem3;
        this.socialJoinPartyPrimaryButton = oCButton;
        this.socialJoinPartySecondaryButton = oCButton2;
        this.socialJoinPartyTitle = oCTextView;
    }

    @Nullable
    public PartyInviteViewModel getPartyViewModel() {
        return this.mPartyViewModel;
    }

    @Nullable
    public SocialUserViewModel getViewerViewModel() {
        return this.mViewerViewModel;
    }

    @NonNull
    public static SocialJoinPartyDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialJoinPartyDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SocialJoinPartyDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_join_party_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static SocialJoinPartyDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialJoinPartyDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SocialJoinPartyDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_join_party_dialog, null, false, obj);
    }

    public static SocialJoinPartyDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialJoinPartyDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialJoinPartyDialogBinding) bind(obj, view, R.layout.social_join_party_dialog);
    }
}
