package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeBarSystemStatusBinding extends ViewDataBinding {
    @NonNull
    public final ImageView batteryIcon;
    @NonNull
    public final OCButton notificationsButton;
    @NonNull
    public final OCButton profileButton;
    @NonNull
    public final SimpleDraweeView profilePhoto;
    @NonNull
    public final OCButton quickActionsButton;
    @NonNull
    public final OCTextView systemTime;
    @NonNull
    public final ImageView wifiIcon;

    protected AnytimeBarSystemStatusBinding(Object obj, View view, int i, ImageView imageView, OCButton oCButton, OCButton oCButton2, SimpleDraweeView simpleDraweeView, OCButton oCButton3, OCTextView oCTextView, ImageView imageView2) {
        super(obj, view, i);
        this.batteryIcon = imageView;
        this.notificationsButton = oCButton;
        this.profileButton = oCButton2;
        this.profilePhoto = simpleDraweeView;
        this.quickActionsButton = oCButton3;
        this.systemTime = oCTextView;
        this.wifiIcon = imageView2;
    }

    @NonNull
    public static AnytimeBarSystemStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarSystemStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeBarSystemStatusBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_system_status, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeBarSystemStatusBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarSystemStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeBarSystemStatusBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_system_status, null, false, obj);
    }

    public static AnytimeBarSystemStatusBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeBarSystemStatusBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeBarSystemStatusBinding) bind(obj, view, R.layout.anytime_bar_system_status);
    }
}
