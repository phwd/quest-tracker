package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.list_item.SocialListItem;

public abstract class SocialCreateVrInviteDialogBinding extends ViewDataBinding {
    @NonNull
    public final SocialCreateVrInviteListItemBinding chooseLaterListItem;
    @NonNull
    public final OCButton createVrInviteBackButton;
    @NonNull
    public final OCSpinner createVrInviteLoadingSpinner;
    @NonNull
    public final OCButton createVrInvitePrimaryButton;
    @NonNull
    public final OCButton createVrInviteSecondaryButton;
    @NonNull
    public final OCTextView destinationsHeader;
    @NonNull
    public final OCSpinner destinationsHeaderSpinner;
    @NonNull
    public final OCRecyclerView destinationsView;
    @Bindable
    protected SocialUserViewModel mSocialUserViewModel;
    @Bindable
    protected CreateVrInviteViewModel mViewModel;
    @NonNull
    public final Group profileGroup;
    @NonNull
    public final View selectedAppBackground;
    @NonNull
    public final OCTextView selectedAppDescription;
    @NonNull
    public final OCTextView selectedAppDisplayName;
    @NonNull
    public final Group selectedAppGroup;
    @NonNull
    public final OCTextView selectedAppGroupJoinDetails;
    @NonNull
    public final OCTextView selectedAppHeader;
    @NonNull
    public final ImageView selectedAppImage;
    @NonNull
    public final SocialListItem socialCreatePartyAsRow;
    @NonNull
    public final CreateVrInviteDialog socialCreatePartyDialog;
    @NonNull
    public final SocialListItem socialCreatePartyLinkSharing;
    @NonNull
    public final SocialMenuItemToggleBinding socialCreatePartyReminder;
    @NonNull
    public final DialogTitleBinding title;

    public abstract void setSocialUserViewModel(@Nullable SocialUserViewModel socialUserViewModel);

    public abstract void setViewModel(@Nullable CreateVrInviteViewModel createVrInviteViewModel);

    protected SocialCreateVrInviteDialogBinding(Object obj, View view, int i, SocialCreateVrInviteListItemBinding socialCreateVrInviteListItemBinding, OCButton oCButton, OCSpinner oCSpinner, OCButton oCButton2, OCButton oCButton3, OCTextView oCTextView, OCSpinner oCSpinner2, OCRecyclerView oCRecyclerView, Group group, View view2, OCTextView oCTextView2, OCTextView oCTextView3, Group group2, OCTextView oCTextView4, OCTextView oCTextView5, ImageView imageView, SocialListItem socialListItem, CreateVrInviteDialog createVrInviteDialog, SocialListItem socialListItem2, SocialMenuItemToggleBinding socialMenuItemToggleBinding, DialogTitleBinding dialogTitleBinding) {
        super(obj, view, i);
        this.chooseLaterListItem = socialCreateVrInviteListItemBinding;
        setContainedBinding(this.chooseLaterListItem);
        this.createVrInviteBackButton = oCButton;
        this.createVrInviteLoadingSpinner = oCSpinner;
        this.createVrInvitePrimaryButton = oCButton2;
        this.createVrInviteSecondaryButton = oCButton3;
        this.destinationsHeader = oCTextView;
        this.destinationsHeaderSpinner = oCSpinner2;
        this.destinationsView = oCRecyclerView;
        this.profileGroup = group;
        this.selectedAppBackground = view2;
        this.selectedAppDescription = oCTextView2;
        this.selectedAppDisplayName = oCTextView3;
        this.selectedAppGroup = group2;
        this.selectedAppGroupJoinDetails = oCTextView4;
        this.selectedAppHeader = oCTextView5;
        this.selectedAppImage = imageView;
        this.socialCreatePartyAsRow = socialListItem;
        this.socialCreatePartyDialog = createVrInviteDialog;
        this.socialCreatePartyLinkSharing = socialListItem2;
        this.socialCreatePartyReminder = socialMenuItemToggleBinding;
        setContainedBinding(this.socialCreatePartyReminder);
        this.title = dialogTitleBinding;
        setContainedBinding(this.title);
    }

    @Nullable
    public SocialUserViewModel getSocialUserViewModel() {
        return this.mSocialUserViewModel;
    }

    @Nullable
    public CreateVrInviteViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static SocialCreateVrInviteDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialCreateVrInviteDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SocialCreateVrInviteDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_create_vr_invite_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static SocialCreateVrInviteDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialCreateVrInviteDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SocialCreateVrInviteDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_create_vr_invite_dialog, null, false, obj);
    }

    public static SocialCreateVrInviteDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialCreateVrInviteDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialCreateVrInviteDialogBinding) bind(obj, view, R.layout.social_create_vr_invite_dialog);
    }
}
