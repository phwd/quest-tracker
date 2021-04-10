package com.oculus.panelapp.social.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialFriendsHeaderBinding extends ViewDataBinding {
    @NonNull
    public final ShellButton buttonGotoParty;
    @NonNull
    public final CommonTabletRectangularButtonBinding buttonStartParty;
    @NonNull
    public final ConstraintLayout friendsHeader;
    @Bindable
    protected SocialParty mParty;
    @Bindable
    protected boolean mShowStartParty;
    @NonNull
    public final OCTextView socialListHeaderText;

    public abstract void setParty(@Nullable SocialParty socialParty);

    public abstract void setShowStartParty(boolean z);

    protected AnytimeTabletSocialFriendsHeaderBinding(Object obj, View view, int i, ShellButton shellButton, CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, ConstraintLayout constraintLayout, OCTextView oCTextView) {
        super(obj, view, i);
        this.buttonGotoParty = shellButton;
        this.buttonStartParty = commonTabletRectangularButtonBinding;
        setContainedBinding(this.buttonStartParty);
        this.friendsHeader = constraintLayout;
        this.socialListHeaderText = oCTextView;
    }

    @Nullable
    public SocialParty getParty() {
        return this.mParty;
    }

    public boolean getShowStartParty() {
        return this.mShowStartParty;
    }

    @NonNull
    public static AnytimeTabletSocialFriendsHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialFriendsHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSocialFriendsHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_friends_header, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSocialFriendsHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialFriendsHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSocialFriendsHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_friends_header, null, false, obj);
    }

    public static AnytimeTabletSocialFriendsHeaderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSocialFriendsHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialFriendsHeaderBinding) bind(obj, view, R.layout.anytime_tablet_social_friends_header);
    }
}
