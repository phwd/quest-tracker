package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsConstraintLayout;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import com.oculus.vrshell.panels.views.ShellProgressBarView;

public abstract class AnytimeTabletNotificationsListItemV2Binding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout container;
    @NonNull
    public final AnytimeTabletNotificationsActionButtonV2Binding dismissButtonOriginal;
    @Bindable
    protected IBaseVRNotification mNotification;
    @NonNull
    public final OCButton moreButton;
    @NonNull
    public final OCTextView notificationDescription;
    @NonNull
    public final ImageView notificationIcon;
    @NonNull
    public final NotificationsConstraintLayout notificationListItem;
    @NonNull
    public final ShellProgressBarView notificationProgressBar;
    @NonNull
    public final OCTextView notificationTimestamp;
    @NonNull
    public final OCTextView notificationTitle;
    @NonNull
    public final AnytimeTabletNotificationsActionButtonV2Binding primaryActionButton;
    @NonNull
    public final AnytimeTabletNotificationsActionButtonV2Binding secondaryActionButton;

    public abstract void setNotification(@Nullable IBaseVRNotification iBaseVRNotification);

    protected AnytimeTabletNotificationsListItemV2Binding(Object obj, View view, int i, ConstraintLayout constraintLayout, AnytimeTabletNotificationsActionButtonV2Binding anytimeTabletNotificationsActionButtonV2Binding, OCButton oCButton, OCTextView oCTextView, ImageView imageView, NotificationsConstraintLayout notificationsConstraintLayout, ShellProgressBarView shellProgressBarView, OCTextView oCTextView2, OCTextView oCTextView3, AnytimeTabletNotificationsActionButtonV2Binding anytimeTabletNotificationsActionButtonV2Binding2, AnytimeTabletNotificationsActionButtonV2Binding anytimeTabletNotificationsActionButtonV2Binding3) {
        super(obj, view, i);
        this.container = constraintLayout;
        this.dismissButtonOriginal = anytimeTabletNotificationsActionButtonV2Binding;
        setContainedBinding(this.dismissButtonOriginal);
        this.moreButton = oCButton;
        this.notificationDescription = oCTextView;
        this.notificationIcon = imageView;
        this.notificationListItem = notificationsConstraintLayout;
        this.notificationProgressBar = shellProgressBarView;
        this.notificationTimestamp = oCTextView2;
        this.notificationTitle = oCTextView3;
        this.primaryActionButton = anytimeTabletNotificationsActionButtonV2Binding2;
        setContainedBinding(this.primaryActionButton);
        this.secondaryActionButton = anytimeTabletNotificationsActionButtonV2Binding3;
        setContainedBinding(this.secondaryActionButton);
    }

    @Nullable
    public IBaseVRNotification getNotification() {
        return this.mNotification;
    }

    @NonNull
    public static AnytimeTabletNotificationsListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletNotificationsListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletNotificationsListItemV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_notifications_list_item_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletNotificationsListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletNotificationsListItemV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletNotificationsListItemV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_notifications_list_item_v2, null, false, obj);
    }

    public static AnytimeTabletNotificationsListItemV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletNotificationsListItemV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletNotificationsListItemV2Binding) bind(obj, view, R.layout.anytime_tablet_notifications_list_item_v2);
    }
}
