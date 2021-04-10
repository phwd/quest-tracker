package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;

public abstract class AnytimeTabletAndroidSettingsListItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout bottomViewGroup;
    @NonNull
    public final ImageView icon;
    @NonNull
    public final SimpleDraweeView image;
    @Bindable
    protected SettingsItem mItem;
    @NonNull
    public final LinearLayout rightViewGroup;
    @NonNull
    public final OCLink subtitle;
    @NonNull
    public final OCTextView title;

    public abstract void setItem(@Nullable SettingsItem settingsItem);

    protected AnytimeTabletAndroidSettingsListItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, OCLink oCLink, OCTextView oCTextView) {
        super(obj, view, i);
        this.bottomViewGroup = constraintLayout;
        this.icon = imageView;
        this.image = simpleDraweeView;
        this.rightViewGroup = linearLayout;
        this.subtitle = oCLink;
        this.title = oCTextView;
    }

    @Nullable
    public SettingsItem getItem() {
        return this.mItem;
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_list_item, null, false, obj);
    }

    public static AnytimeTabletAndroidSettingsListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletAndroidSettingsListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsListItemBinding) bind(obj, view, R.layout.anytime_tablet_android_settings_list_item);
    }
}
