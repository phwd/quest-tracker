package com.oculus.panelapp.social.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.SocialGuidedAction;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialGuidedActionCardBinding extends AnonymousClass1uW {
    @NonNull
    public final View actionCardHoverOverlay;
    @NonNull
    public final ShellButton actionCtaButton;
    @NonNull
    public final OCTextView actionDescription;
    @Bindable
    public SocialGuidedAction mAction;
    @Bindable
    public boolean mHoveredOverCard;
    @NonNull
    public final ImageView profilePhoto;
    @NonNull
    public final ConstraintLayout socialSuggestedActionCard;

    public abstract void setAction(@Nullable SocialGuidedAction socialGuidedAction);

    public abstract void setHoveredOverCard(boolean z);

    @Nullable
    public SocialGuidedAction getAction() {
        return this.mAction;
    }

    public boolean getHoveredOverCard() {
        return this.mHoveredOverCard;
    }

    public AnytimeTabletSocialGuidedActionCardBinding(Object obj, View view, int i, View view2, ShellButton shellButton, OCTextView oCTextView, ImageView imageView, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.actionCardHoverOverlay = view2;
        this.actionCtaButton = shellButton;
        this.actionDescription = oCTextView;
        this.profilePhoto = imageView;
        this.socialSuggestedActionCard = constraintLayout;
    }

    public static AnytimeTabletSocialGuidedActionCardBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletSocialGuidedActionCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialGuidedActionCardBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_social_guided_action_card);
    }

    @NonNull
    public static AnytimeTabletSocialGuidedActionCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletSocialGuidedActionCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialGuidedActionCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialGuidedActionCardBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_guided_action_card, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialGuidedActionCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialGuidedActionCardBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_guided_action_card, null, false);
    }
}
