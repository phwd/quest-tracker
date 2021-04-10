package com.oculus.panelapp.androiddialog.databinding;

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
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.integrity.block.FacebookBlockDialog;
import com.oculus.panelapp.androiddialog.dialogs.integrity.block.FacebookBlockDialogViewModel;

public abstract class FacebookBlockDialogBinding extends ViewDataBinding {
    @NonNull
    public final OCButton backButton;
    @NonNull
    public final ImageView blockTitleIcon;
    @NonNull
    public final Group facebookBlockSection;
    @NonNull
    public final Group facebookUnblockSection;
    @NonNull
    public final OCTextView fbBlockAddYouExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbBlockAddYouExplanationBullet;
    @NonNull
    public final DialogActionSpaceSecondaryBinding fbBlockButtonActionSpace;
    @NonNull
    public final OCTextView fbBlockInviteYouExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbBlockInviteYouExplanationBullet;
    @NonNull
    public final OCTextView fbBlockSeePostsExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbBlockSeePostsExplanationBullet;
    @NonNull
    public final OCTextView fbBlockStartConversationExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbBlockStartConversationExplanationBullet;
    @NonNull
    public final OCTextView fbBlockTagYouExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbBlockTagYouExplanationBullet;
    @NonNull
    public final OCTextView fbBlockTitle;
    @NonNull
    public final OCTextView fbBlockWillNotOculusBlockExplanation;
    @NonNull
    public final OCTextView fbBlockWillUnfriendExplanation;
    @NonNull
    public final DialogActionSpaceSecondaryBinding fbUnblockButtonActionSpace;
    @NonNull
    public final OCTextView fbUnblockMessengerStillBlockedExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbUnblockMessengerStillBlockedExplanationBullet;
    @NonNull
    public final OCTextView fbUnblockRefriendExplanation;
    @NonNull
    public final BlockDialogBulletBinding fbUnblockRefriendExplanationBullet;
    @NonNull
    public final OCTextView fbUnblockTitle;
    @NonNull
    public final ImageView fbUnblockTitleIcon;
    @NonNull
    public final Guideline leftGuideline;
    @Bindable
    protected FacebookBlockDialogViewModel mViewModel;
    @NonNull
    public final Guideline rightGuideline;
    @NonNull
    public final DialogTitleBinding title;
    @NonNull
    public final FacebookBlockDialog view;

    public abstract void setViewModel(@Nullable FacebookBlockDialogViewModel facebookBlockDialogViewModel);

    protected FacebookBlockDialogBinding(Object obj, View view2, int i, OCButton oCButton, ImageView imageView, Group group, Group group2, OCTextView oCTextView, BlockDialogBulletBinding blockDialogBulletBinding, DialogActionSpaceSecondaryBinding dialogActionSpaceSecondaryBinding, OCTextView oCTextView2, BlockDialogBulletBinding blockDialogBulletBinding2, OCTextView oCTextView3, BlockDialogBulletBinding blockDialogBulletBinding3, OCTextView oCTextView4, BlockDialogBulletBinding blockDialogBulletBinding4, OCTextView oCTextView5, BlockDialogBulletBinding blockDialogBulletBinding5, OCTextView oCTextView6, OCTextView oCTextView7, OCTextView oCTextView8, DialogActionSpaceSecondaryBinding dialogActionSpaceSecondaryBinding2, OCTextView oCTextView9, BlockDialogBulletBinding blockDialogBulletBinding6, OCTextView oCTextView10, BlockDialogBulletBinding blockDialogBulletBinding7, OCTextView oCTextView11, ImageView imageView2, Guideline guideline, Guideline guideline2, DialogTitleBinding dialogTitleBinding, FacebookBlockDialog facebookBlockDialog) {
        super(obj, view2, i);
        this.backButton = oCButton;
        this.blockTitleIcon = imageView;
        this.facebookBlockSection = group;
        this.facebookUnblockSection = group2;
        this.fbBlockAddYouExplanation = oCTextView;
        this.fbBlockAddYouExplanationBullet = blockDialogBulletBinding;
        setContainedBinding(this.fbBlockAddYouExplanationBullet);
        this.fbBlockButtonActionSpace = dialogActionSpaceSecondaryBinding;
        setContainedBinding(this.fbBlockButtonActionSpace);
        this.fbBlockInviteYouExplanation = oCTextView2;
        this.fbBlockInviteYouExplanationBullet = blockDialogBulletBinding2;
        setContainedBinding(this.fbBlockInviteYouExplanationBullet);
        this.fbBlockSeePostsExplanation = oCTextView3;
        this.fbBlockSeePostsExplanationBullet = blockDialogBulletBinding3;
        setContainedBinding(this.fbBlockSeePostsExplanationBullet);
        this.fbBlockStartConversationExplanation = oCTextView4;
        this.fbBlockStartConversationExplanationBullet = blockDialogBulletBinding4;
        setContainedBinding(this.fbBlockStartConversationExplanationBullet);
        this.fbBlockTagYouExplanation = oCTextView5;
        this.fbBlockTagYouExplanationBullet = blockDialogBulletBinding5;
        setContainedBinding(this.fbBlockTagYouExplanationBullet);
        this.fbBlockTitle = oCTextView6;
        this.fbBlockWillNotOculusBlockExplanation = oCTextView7;
        this.fbBlockWillUnfriendExplanation = oCTextView8;
        this.fbUnblockButtonActionSpace = dialogActionSpaceSecondaryBinding2;
        setContainedBinding(this.fbUnblockButtonActionSpace);
        this.fbUnblockMessengerStillBlockedExplanation = oCTextView9;
        this.fbUnblockMessengerStillBlockedExplanationBullet = blockDialogBulletBinding6;
        setContainedBinding(this.fbUnblockMessengerStillBlockedExplanationBullet);
        this.fbUnblockRefriendExplanation = oCTextView10;
        this.fbUnblockRefriendExplanationBullet = blockDialogBulletBinding7;
        setContainedBinding(this.fbUnblockRefriendExplanationBullet);
        this.fbUnblockTitle = oCTextView11;
        this.fbUnblockTitleIcon = imageView2;
        this.leftGuideline = guideline;
        this.rightGuideline = guideline2;
        this.title = dialogTitleBinding;
        setContainedBinding(this.title);
        this.view = facebookBlockDialog;
    }

    @Nullable
    public FacebookBlockDialogViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static FacebookBlockDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FacebookBlockDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FacebookBlockDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.facebook_block_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static FacebookBlockDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FacebookBlockDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FacebookBlockDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.facebook_block_dialog, null, false, obj);
    }

    public static FacebookBlockDialogBinding bind(@NonNull View view2) {
        return bind(view2, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FacebookBlockDialogBinding bind(@NonNull View view2, @Nullable Object obj) {
        return (FacebookBlockDialogBinding) bind(obj, view2, R.layout.facebook_block_dialog);
    }
}
