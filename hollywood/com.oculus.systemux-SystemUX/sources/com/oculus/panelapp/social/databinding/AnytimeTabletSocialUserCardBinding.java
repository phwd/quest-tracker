package com.oculus.panelapp.social.databinding;

import android.graphics.drawable.Drawable;
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
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.R;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialUserCardBinding extends ViewDataBinding {
    @NonNull
    public final View cardHoverOverlay;
    @NonNull
    public final ShellButton ctaButton;
    @NonNull
    public final OCSpinner ctaLoadingSpinner;
    @NonNull
    public final OCTextView groupLaunchStatus;
    @Bindable
    protected Drawable mCtaIcon;
    @Bindable
    protected String mGroupLaunchStatusText;
    @Bindable
    protected boolean mIsFriend;
    @Bindable
    protected boolean mIsMuted;
    @Bindable
    protected boolean mIsSpeaking;
    @Bindable
    protected String mSubtitleText;
    @Bindable
    protected String mUsernameText;
    @NonNull
    public final ImageView partyMutedIndicator;
    @NonNull
    public final ImageView partyMutedIndicatorBackground;
    @NonNull
    public final ImageView partySpeakingIndicator;
    @NonNull
    public final ImageView partySpeakingIndicatorBackground;
    @NonNull
    public final ImageView profilePhoto;
    @NonNull
    public final ShellButton secondaryButton;
    @NonNull
    public final OCSpinner secondaryButtonLoadingSpinner;
    @NonNull
    public final ConstraintLayout socialUserCard;
    @NonNull
    public final OCTextView subtitle;
    @NonNull
    public final OCTextView username;

    public abstract void setCtaIcon(@Nullable Drawable drawable);

    public abstract void setGroupLaunchStatusText(@Nullable String str);

    public abstract void setIsFriend(boolean z);

    public abstract void setIsMuted(boolean z);

    public abstract void setIsSpeaking(boolean z);

    public abstract void setSubtitleText(@Nullable String str);

    public abstract void setUsernameText(@Nullable String str);

    protected AnytimeTabletSocialUserCardBinding(Object obj, View view, int i, View view2, ShellButton shellButton, OCSpinner oCSpinner, OCTextView oCTextView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ShellButton shellButton2, OCSpinner oCSpinner2, ConstraintLayout constraintLayout, OCTextView oCTextView2, OCTextView oCTextView3) {
        super(obj, view, i);
        this.cardHoverOverlay = view2;
        this.ctaButton = shellButton;
        this.ctaLoadingSpinner = oCSpinner;
        this.groupLaunchStatus = oCTextView;
        this.partyMutedIndicator = imageView;
        this.partyMutedIndicatorBackground = imageView2;
        this.partySpeakingIndicator = imageView3;
        this.partySpeakingIndicatorBackground = imageView4;
        this.profilePhoto = imageView5;
        this.secondaryButton = shellButton2;
        this.secondaryButtonLoadingSpinner = oCSpinner2;
        this.socialUserCard = constraintLayout;
        this.subtitle = oCTextView2;
        this.username = oCTextView3;
    }

    @Nullable
    public String getUsernameText() {
        return this.mUsernameText;
    }

    @Nullable
    public String getSubtitleText() {
        return this.mSubtitleText;
    }

    @Nullable
    public String getGroupLaunchStatusText() {
        return this.mGroupLaunchStatusText;
    }

    public boolean getIsFriend() {
        return this.mIsFriend;
    }

    public boolean getIsSpeaking() {
        return this.mIsSpeaking;
    }

    public boolean getIsMuted() {
        return this.mIsMuted;
    }

    @Nullable
    public Drawable getCtaIcon() {
        return this.mCtaIcon;
    }

    @NonNull
    public static AnytimeTabletSocialUserCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialUserCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSocialUserCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_user_card, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSocialUserCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialUserCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSocialUserCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_user_card, null, false, obj);
    }

    public static AnytimeTabletSocialUserCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSocialUserCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialUserCardBinding) bind(obj, view, R.layout.anytime_tablet_social_user_card);
    }
}
