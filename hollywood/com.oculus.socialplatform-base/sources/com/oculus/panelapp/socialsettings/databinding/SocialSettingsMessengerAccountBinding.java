package com.oculus.panelapp.socialsettings.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialsettings.views.SocialSettingsMessengerAccountViewModel;
import com.oculus.socialplatform.R;

public abstract class SocialSettingsMessengerAccountBinding extends AnonymousClass1uW {
    @Bindable
    public SocialSettingsMessengerAccountViewModel mViewModel;
    @NonNull
    public final OCTextView messengerAccountBody;
    @NonNull
    public final OCTextView messengerAccountHeader;
    @NonNull
    public final OCTextView messengerAccountProfileName;
    @NonNull
    public final ImageView messengerAccountProfilePicture;
    @NonNull
    public final OCButton messengerAccountSignOutButton;

    public abstract void setViewModel(@Nullable SocialSettingsMessengerAccountViewModel socialSettingsMessengerAccountViewModel);

    @Nullable
    public SocialSettingsMessengerAccountViewModel getViewModel() {
        return this.mViewModel;
    }

    public SocialSettingsMessengerAccountBinding(Object obj, View view, int i, OCTextView oCTextView, OCTextView oCTextView2, OCTextView oCTextView3, ImageView imageView, OCButton oCButton) {
        super(obj, view, i);
        this.messengerAccountBody = oCTextView;
        this.messengerAccountHeader = oCTextView2;
        this.messengerAccountProfileName = oCTextView3;
        this.messengerAccountProfilePicture = imageView;
        this.messengerAccountSignOutButton = oCButton;
    }

    public static SocialSettingsMessengerAccountBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static SocialSettingsMessengerAccountBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialSettingsMessengerAccountBinding) AnonymousClass1uW.bind(obj, view, R.layout.social_settings_messenger_account);
    }

    @NonNull
    public static SocialSettingsMessengerAccountBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static SocialSettingsMessengerAccountBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsMessengerAccountBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsMessengerAccountBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_messenger_account, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsMessengerAccountBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsMessengerAccountBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_messenger_account, null, false);
    }
}
