package com.oculus.panelapp.socialreauth.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNav;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCScrollView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialreauth.views.PasswordInput;
import com.oculus.panelapp.socialreauth.views.SocialReauthView;
import com.oculus.panelapp.socialreauth.views.SocialReauthViewModel;
import com.oculus.socialplatform.R;

public abstract class SocialReauthTabletMainBinding extends AnonymousClass1uW {
    @NonNull
    public final OCTextView body;
    @NonNull
    public final OCScrollView bodyScrollView;
    @NonNull
    public final OCButton continueBtn;
    @NonNull
    public final ImageView continueBtnIcon;
    @NonNull
    public final OCTextView continueBtnLabel;
    @NonNull
    public final OCTextView errorBody;
    @NonNull
    public final OCTextView footer;
    @NonNull
    public final OCTextView header;
    @Bindable
    public SocialReauthViewModel mViewModel;
    @NonNull
    public final PasswordInput passwordInput;
    @NonNull
    public final View passwordInputBackground;
    @NonNull
    public final OCTextView passwordInputLabel;
    @NonNull
    public final SocialReauthView socialReauthView;
    @NonNull
    public final SocialTabletSideNav socialTabletSideNav;
    @NonNull
    public final OCButton togglePasswordVisibilityBtn;

    public abstract void setViewModel(@Nullable SocialReauthViewModel socialReauthViewModel);

    @Nullable
    public SocialReauthViewModel getViewModel() {
        return this.mViewModel;
    }

    public SocialReauthTabletMainBinding(Object obj, View view, int i, OCTextView oCTextView, OCScrollView oCScrollView, OCButton oCButton, ImageView imageView, OCTextView oCTextView2, OCTextView oCTextView3, OCTextView oCTextView4, OCTextView oCTextView5, PasswordInput passwordInput2, View view2, OCTextView oCTextView6, SocialReauthView socialReauthView2, SocialTabletSideNav socialTabletSideNav2, OCButton oCButton2) {
        super(obj, view, i);
        this.body = oCTextView;
        this.bodyScrollView = oCScrollView;
        this.continueBtn = oCButton;
        this.continueBtnIcon = imageView;
        this.continueBtnLabel = oCTextView2;
        this.errorBody = oCTextView3;
        this.footer = oCTextView4;
        this.header = oCTextView5;
        this.passwordInput = passwordInput2;
        this.passwordInputBackground = view2;
        this.passwordInputLabel = oCTextView6;
        this.socialReauthView = socialReauthView2;
        this.socialTabletSideNav = socialTabletSideNav2;
        this.togglePasswordVisibilityBtn = oCButton2;
    }

    public static SocialReauthTabletMainBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static SocialReauthTabletMainBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialReauthTabletMainBinding) AnonymousClass1uW.bind(obj, view, R.layout.social_reauth_tablet_main);
    }

    @NonNull
    public static SocialReauthTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static SocialReauthTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static SocialReauthTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialReauthTabletMainBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_reauth_tablet_main, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static SocialReauthTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialReauthTabletMainBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_reauth_tablet_main, null, false);
    }
}
