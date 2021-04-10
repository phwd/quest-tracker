package com.oculus.panelapp.social.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialGuidedAction;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialGuidedActionCardBinding extends ViewDataBinding {
    @NonNull
    public final View actionCardHoverOverlay;
    @NonNull
    public final ShellButton actionCtaButton;
    @NonNull
    public final OCTextView actionDescription;
    @Bindable
    protected SocialGuidedAction mAction;
    @Bindable
    protected boolean mHoveredOverCard;
    @NonNull
    public final ImageView profilePhoto;
    @NonNull
    public final ConstraintLayout socialSuggestedActionCard;

    public abstract void setAction(@Nullable SocialGuidedAction socialGuidedAction);

    public abstract void setHoveredOverCard(boolean z);

    protected AnytimeTabletSocialGuidedActionCardBinding(Object obj, View view, int i, View view2, ShellButton shellButton, OCTextView oCTextView, ImageView imageView, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.actionCardHoverOverlay = view2;
        this.actionCtaButton = shellButton;
        this.actionDescription = oCTextView;
        this.profilePhoto = imageView;
        this.socialSuggestedActionCard = constraintLayout;
    }

    public boolean getHoveredOverCard() {
        return this.mHoveredOverCard;
    }

    @Nullable
    public SocialGuidedAction getAction() {
        return this.mAction;
    }

    @NonNull
    public static AnytimeTabletSocialGuidedActionCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialGuidedActionCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSocialGuidedActionCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_guided_action_card, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSocialGuidedActionCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialGuidedActionCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSocialGuidedActionCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_guided_action_card, null, false, obj);
    }

    public static AnytimeTabletSocialGuidedActionCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSocialGuidedActionCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialGuidedActionCardBinding) bind(obj, view, R.layout.anytime_tablet_social_guided_action_card);
    }
}
