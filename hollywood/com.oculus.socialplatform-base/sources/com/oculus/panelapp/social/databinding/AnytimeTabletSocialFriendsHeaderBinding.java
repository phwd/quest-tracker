package com.oculus.panelapp.social.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialFriendsHeaderBinding extends AnonymousClass1uW {
    @NonNull
    public final ShellButton buttonGotoParty;
    @NonNull
    public final CommonTabletRectangularButtonBinding buttonStartParty;
    @NonNull
    public final ConstraintLayout friendsHeader;
    @Bindable
    public SocialParty mParty;
    @Bindable
    public boolean mShowStartParty;
    @NonNull
    public final OCTextView socialListHeaderText;

    public abstract void setParty(@Nullable SocialParty socialParty);

    public abstract void setShowStartParty(boolean z);

    @Nullable
    public SocialParty getParty() {
        return this.mParty;
    }

    public boolean getShowStartParty() {
        return this.mShowStartParty;
    }

    public AnytimeTabletSocialFriendsHeaderBinding(Object obj, View view, int i, ShellButton shellButton, CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, ConstraintLayout constraintLayout, OCTextView oCTextView) {
        super(obj, view, i);
        this.buttonGotoParty = shellButton;
        this.buttonStartParty = commonTabletRectangularButtonBinding;
        setContainedBinding(commonTabletRectangularButtonBinding);
        this.friendsHeader = constraintLayout;
        this.socialListHeaderText = oCTextView;
    }

    public static AnytimeTabletSocialFriendsHeaderBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletSocialFriendsHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialFriendsHeaderBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_social_friends_header);
    }

    @NonNull
    public static AnytimeTabletSocialFriendsHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletSocialFriendsHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialFriendsHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialFriendsHeaderBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_friends_header, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialFriendsHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialFriendsHeaderBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_friends_header, null, false);
    }
}
