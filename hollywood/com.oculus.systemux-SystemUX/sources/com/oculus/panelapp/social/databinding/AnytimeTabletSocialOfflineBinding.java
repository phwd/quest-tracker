package com.oculus.panelapp.social.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.R;

public abstract class AnytimeTabletSocialOfflineBinding extends ViewDataBinding {
    @Bindable
    protected String mSubtitle;
    @Bindable
    protected String mTitle;
    @NonNull
    public final OCTextView offlineSubtitle;
    @NonNull
    public final OCTextView offlineTitle;

    public abstract void setSubtitle(@Nullable String str);

    public abstract void setTitle(@Nullable String str);

    protected AnytimeTabletSocialOfflineBinding(Object obj, View view, int i, OCTextView oCTextView, OCTextView oCTextView2) {
        super(obj, view, i);
        this.offlineSubtitle = oCTextView;
        this.offlineTitle = oCTextView2;
    }

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    @Nullable
    public String getSubtitle() {
        return this.mSubtitle;
    }

    @NonNull
    public static AnytimeTabletSocialOfflineBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialOfflineBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSocialOfflineBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_offline, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSocialOfflineBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialOfflineBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSocialOfflineBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_offline, null, false, obj);
    }

    public static AnytimeTabletSocialOfflineBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSocialOfflineBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialOfflineBinding) bind(obj, view, R.layout.anytime_tablet_social_offline);
    }
}
