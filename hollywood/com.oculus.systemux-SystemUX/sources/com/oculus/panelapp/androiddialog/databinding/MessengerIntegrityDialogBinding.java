package com.oculus.panelapp.androiddialog.databinding;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.integrity.MessengerIntegrityDialog;
import com.oculus.panelapp.androiddialog.dialogs.integrity.MessengerIntegrityDialogViewModel;

public abstract class MessengerIntegrityDialogBinding extends ViewDataBinding {
    @NonNull
    public final OCSpinner activeStatusLoadingSpinner;
    @NonNull
    public final OCButton backButton;
    @NonNull
    public final Group facebookBlockSection;
    @NonNull
    public final Group facebookBlockedMessengerUnblockSection;
    @NonNull
    public final Group facebookSection;
    @NonNull
    public final Group facebookUnblockSection;
    @NonNull
    public final OCTextView fbBlockMessengerAlsoBlockedExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbBlockMessengerAlsoBlockedExplanationBullet;
    @NonNull
    public final OCTextView fbBlockUnfriendExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbBlockUnfriendExplanationBullet;
    @NonNull
    public final OCTextView fbBlockedCannotUnblockMessagesCallsExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbBlockedCannotUnblockMessagesCallsExplanationBullet;
    @NonNull
    public final OCButton fbSectionButton;
    @NonNull
    public final OCTextView fbSectionTitle;
    @NonNull
    public final ImageView fbSectionTitleIcon;
    @NonNull
    public final OCTextView fbUnblockMessengerStillBlockedExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbUnblockMessengerStillBlockedExplanationBullet;
    @NonNull
    public final OCTextView fbUnblockRefriendExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbUnblockRefriendExplanationBullet;
    @NonNull
    public final Guideline leftGuideline;
    @Bindable
    protected Resources mResources;
    @Bindable
    protected MessengerIntegrityDialogViewModel mViewModel;
    @NonNull
    public final OCTextView messengerBlockGroupsRoomsExplanation;
    @NonNull
    public final BlockDialogBulletBinding messengerBlockGroupsRoomsExplanationBullet;
    @NonNull
    public final OCTextView messengerBlockMessagesCallsExplanation;
    @NonNull
    public final BlockDialogBulletBinding messengerBlockMessagesCallsExplanationBullet;
    @NonNull
    public final OCTextView messengerBlockNoFacebookBlockExplanation;
    @NonNull
    public final BlockDialogBulletBinding messengerBlockNoFacebookBlockExplanationBullet;
    @NonNull
    public final OCTextView messengerBlockNoOculusBlockExplanation;
    @NonNull
    public final BlockDialogBulletBinding messengerBlockNoOculusBlockExplanationBullet;
    @NonNull
    public final Group messengerBlockSection;
    @NonNull
    public final Group messengerSection;
    @NonNull
    public final OCButton messengerSectionButton;
    @NonNull
    public final OCTextView messengerSectionTitle;
    @NonNull
    public final ImageView messengerSectionTitleIcon;
    @NonNull
    public final OCTextView messengerUnblockBlockedSentMessagesExplanation;
    @NonNull
    public final BlockDialogBulletBinding messengerUnblockBlockedSentMessagesExplanationBullet;
    @NonNull
    public final OCTextView messengerUnblockMessagesCallsExplanation;
    @NonNull
    public final BlockDialogBulletBinding messengerUnblockMessagesCallsExplanationBullet;
    @NonNull
    public final Group messengerUnblockSection;
    @NonNull
    public final OCRecyclerView participantList;
    @NonNull
    public final Guideline rightGuideline;
    @NonNull
    public final View sectionDivider;
    @NonNull
    public final DialogTitleBinding title;
    @NonNull
    public final MessengerIntegrityDialog view;

    public abstract void setResources(@Nullable Resources resources);

    public abstract void setViewModel(@Nullable MessengerIntegrityDialogViewModel messengerIntegrityDialogViewModel);

    protected MessengerIntegrityDialogBinding(Object obj, View view2, int i, OCSpinner oCSpinner, OCButton oCButton, Group group, Group group2, Group group3, Group group4, OCTextView oCTextView, BlockDialogBulletBinding blockDialogBulletBinding, OCTextView oCTextView2, BlockDialogBulletBinding blockDialogBulletBinding2, OCTextView oCTextView3, BlockDialogBulletBinding blockDialogBulletBinding3, OCButton oCButton2, OCTextView oCTextView4, ImageView imageView, OCTextView oCTextView5, BlockDialogBulletBinding blockDialogBulletBinding4, OCTextView oCTextView6, BlockDialogBulletBinding blockDialogBulletBinding5, Guideline guideline, OCTextView oCTextView7, BlockDialogBulletBinding blockDialogBulletBinding6, OCTextView oCTextView8, BlockDialogBulletBinding blockDialogBulletBinding7, OCTextView oCTextView9, BlockDialogBulletBinding blockDialogBulletBinding8, OCTextView oCTextView10, BlockDialogBulletBinding blockDialogBulletBinding9, Group group5, Group group6, OCButton oCButton3, OCTextView oCTextView11, ImageView imageView2, OCTextView oCTextView12, BlockDialogBulletBinding blockDialogBulletBinding10, OCTextView oCTextView13, BlockDialogBulletBinding blockDialogBulletBinding11, Group group7, OCRecyclerView oCRecyclerView, Guideline guideline2, View view3, DialogTitleBinding dialogTitleBinding, MessengerIntegrityDialog messengerIntegrityDialog) {
        super(obj, view2, i);
        this.activeStatusLoadingSpinner = oCSpinner;
        this.backButton = oCButton;
        this.facebookBlockSection = group;
        this.facebookBlockedMessengerUnblockSection = group2;
        this.facebookSection = group3;
        this.facebookUnblockSection = group4;
        this.fbBlockMessengerAlsoBlockedExplanation = oCTextView;
        this.fbBlockMessengerAlsoBlockedExplanationBullet = blockDialogBulletBinding;
        setContainedBinding(this.fbBlockMessengerAlsoBlockedExplanationBullet);
        this.fbBlockUnfriendExplanation = oCTextView2;
        this.fbBlockUnfriendExplanationBullet = blockDialogBulletBinding2;
        setContainedBinding(this.fbBlockUnfriendExplanationBullet);
        this.fbBlockedCannotUnblockMessagesCallsExplanation = oCTextView3;
        this.fbBlockedCannotUnblockMessagesCallsExplanationBullet = blockDialogBulletBinding3;
        setContainedBinding(this.fbBlockedCannotUnblockMessagesCallsExplanationBullet);
        this.fbSectionButton = oCButton2;
        this.fbSectionTitle = oCTextView4;
        this.fbSectionTitleIcon = imageView;
        this.fbUnblockMessengerStillBlockedExplanation = oCTextView5;
        this.fbUnblockMessengerStillBlockedExplanationBullet = blockDialogBulletBinding4;
        setContainedBinding(this.fbUnblockMessengerStillBlockedExplanationBullet);
        this.fbUnblockRefriendExplanation = oCTextView6;
        this.fbUnblockRefriendExplanationBullet = blockDialogBulletBinding5;
        setContainedBinding(this.fbUnblockRefriendExplanationBullet);
        this.leftGuideline = guideline;
        this.messengerBlockGroupsRoomsExplanation = oCTextView7;
        this.messengerBlockGroupsRoomsExplanationBullet = blockDialogBulletBinding6;
        setContainedBinding(this.messengerBlockGroupsRoomsExplanationBullet);
        this.messengerBlockMessagesCallsExplanation = oCTextView8;
        this.messengerBlockMessagesCallsExplanationBullet = blockDialogBulletBinding7;
        setContainedBinding(this.messengerBlockMessagesCallsExplanationBullet);
        this.messengerBlockNoFacebookBlockExplanation = oCTextView9;
        this.messengerBlockNoFacebookBlockExplanationBullet = blockDialogBulletBinding8;
        setContainedBinding(this.messengerBlockNoFacebookBlockExplanationBullet);
        this.messengerBlockNoOculusBlockExplanation = oCTextView10;
        this.messengerBlockNoOculusBlockExplanationBullet = blockDialogBulletBinding9;
        setContainedBinding(this.messengerBlockNoOculusBlockExplanationBullet);
        this.messengerBlockSection = group5;
        this.messengerSection = group6;
        this.messengerSectionButton = oCButton3;
        this.messengerSectionTitle = oCTextView11;
        this.messengerSectionTitleIcon = imageView2;
        this.messengerUnblockBlockedSentMessagesExplanation = oCTextView12;
        this.messengerUnblockBlockedSentMessagesExplanationBullet = blockDialogBulletBinding10;
        setContainedBinding(this.messengerUnblockBlockedSentMessagesExplanationBullet);
        this.messengerUnblockMessagesCallsExplanation = oCTextView13;
        this.messengerUnblockMessagesCallsExplanationBullet = blockDialogBulletBinding11;
        setContainedBinding(this.messengerUnblockMessagesCallsExplanationBullet);
        this.messengerUnblockSection = group7;
        this.participantList = oCRecyclerView;
        this.rightGuideline = guideline2;
        this.sectionDivider = view3;
        this.title = dialogTitleBinding;
        setContainedBinding(this.title);
        this.view = messengerIntegrityDialog;
    }

    @Nullable
    public MessengerIntegrityDialogViewModel getViewModel() {
        return this.mViewModel;
    }

    @Nullable
    public Resources getResources() {
        return this.mResources;
    }

    @NonNull
    public static MessengerIntegrityDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MessengerIntegrityDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MessengerIntegrityDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.messenger_integrity_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static MessengerIntegrityDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MessengerIntegrityDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MessengerIntegrityDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.messenger_integrity_dialog, null, false, obj);
    }

    public static MessengerIntegrityDialogBinding bind(@NonNull View view2) {
        return bind(view2, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MessengerIntegrityDialogBinding bind(@NonNull View view2, @Nullable Object obj) {
        return (MessengerIntegrityDialogBinding) bind(obj, view2, R.layout.messenger_integrity_dialog);
    }
}
