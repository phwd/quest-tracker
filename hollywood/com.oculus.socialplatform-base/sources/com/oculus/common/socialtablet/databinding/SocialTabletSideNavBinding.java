package com.oculus.common.socialtablet.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNavViewModel;
import com.oculus.ocui.OCButton;
import com.oculus.socialplatform.R;

public abstract class SocialTabletSideNavBinding extends AnonymousClass1uW {
    @Bindable
    public SocialTabletSideNavViewModel mSocialTabletSideNavViewModel;
    @NonNull
    public final OCButton sideNavChatButton;
    @NonNull
    public final OCButton sideNavPeopleButton;
    @NonNull
    public final ImageView sideNavProfileSwitcher;
    @NonNull
    public final View sideNavProfileSwitcherBadge;
    @NonNull
    public final View sideNavProfileSwitcherBorder;
    @NonNull
    public final OCButton sideNavSettingsButton;

    public abstract void setSocialTabletSideNavViewModel(@Nullable SocialTabletSideNavViewModel socialTabletSideNavViewModel);

    @Nullable
    public SocialTabletSideNavViewModel getSocialTabletSideNavViewModel() {
        return this.mSocialTabletSideNavViewModel;
    }

    public SocialTabletSideNavBinding(Object obj, View view, int i, OCButton oCButton, OCButton oCButton2, ImageView imageView, View view2, View view3, OCButton oCButton3) {
        super(obj, view, i);
        this.sideNavChatButton = oCButton;
        this.sideNavPeopleButton = oCButton2;
        this.sideNavProfileSwitcher = imageView;
        this.sideNavProfileSwitcherBadge = view2;
        this.sideNavProfileSwitcherBorder = view3;
        this.sideNavSettingsButton = oCButton3;
    }

    public static SocialTabletSideNavBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static SocialTabletSideNavBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialTabletSideNavBinding) AnonymousClass1uW.bind(obj, view, R.layout.social_tablet_side_nav);
    }

    @NonNull
    public static SocialTabletSideNavBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static SocialTabletSideNavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static SocialTabletSideNavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialTabletSideNavBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_tablet_side_nav, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static SocialTabletSideNavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialTabletSideNavBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_tablet_side_nav, null, false);
    }
}
