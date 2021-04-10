package com.oculus.panelapp.social.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletSocialOfflineBinding extends AnonymousClass1uW {
    @Bindable
    public String mSubtitle;
    @Bindable
    public String mTitle;
    @NonNull
    public final OCTextView offlineSubtitle;
    @NonNull
    public final OCTextView offlineTitle;

    public abstract void setSubtitle(@Nullable String str);

    public abstract void setTitle(@Nullable String str);

    @Nullable
    public String getSubtitle() {
        return this.mSubtitle;
    }

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    public AnytimeTabletSocialOfflineBinding(Object obj, View view, int i, OCTextView oCTextView, OCTextView oCTextView2) {
        super(obj, view, i);
        this.offlineSubtitle = oCTextView;
        this.offlineTitle = oCTextView2;
    }

    public static AnytimeTabletSocialOfflineBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletSocialOfflineBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialOfflineBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_social_offline);
    }

    @NonNull
    public static AnytimeTabletSocialOfflineBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletSocialOfflineBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialOfflineBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialOfflineBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_offline, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialOfflineBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialOfflineBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_offline, null, false);
    }
}
