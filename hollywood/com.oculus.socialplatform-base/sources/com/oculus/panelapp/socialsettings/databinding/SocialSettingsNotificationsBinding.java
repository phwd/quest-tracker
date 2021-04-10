package com.oculus.panelapp.socialsettings.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class SocialSettingsNotificationsBinding extends AnonymousClass1uW {
    @NonNull
    public final OCTextView notificationsBody;
    @NonNull
    public final OCButton notificationsButton;
    @NonNull
    public final OCTextView notificationsDescription;
    @NonNull
    public final OCTextView notificationsHeader;

    public SocialSettingsNotificationsBinding(Object obj, View view, int i, OCTextView oCTextView, OCButton oCButton, OCTextView oCTextView2, OCTextView oCTextView3) {
        super(obj, view, i);
        this.notificationsBody = oCTextView;
        this.notificationsButton = oCButton;
        this.notificationsDescription = oCTextView2;
        this.notificationsHeader = oCTextView3;
    }

    public static SocialSettingsNotificationsBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static SocialSettingsNotificationsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialSettingsNotificationsBinding) AnonymousClass1uW.bind(obj, view, R.layout.social_settings_notifications);
    }

    @NonNull
    public static SocialSettingsNotificationsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static SocialSettingsNotificationsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsNotificationsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsNotificationsBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_notifications, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsNotificationsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsNotificationsBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_notifications, null, false);
    }
}
