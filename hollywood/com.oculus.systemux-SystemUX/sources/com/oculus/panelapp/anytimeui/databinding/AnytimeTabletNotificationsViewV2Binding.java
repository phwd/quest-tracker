package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCEmptyLayout;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSideNav;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletNotificationsViewV2Binding extends ViewDataBinding {
    @Bindable
    protected String mSectionHeader;
    @NonNull
    public final OCRecyclerView notificationList;
    @NonNull
    public final OCTextView notificationListHeader;
    @NonNull
    public final OCEmptyLayout nullState;
    @NonNull
    public final OCSideNav sideNav;

    public abstract void setSectionHeader(@Nullable String str);

    protected AnytimeTabletNotificationsViewV2Binding(Object obj, View view, int i, OCRecyclerView oCRecyclerView, OCTextView oCTextView, OCEmptyLayout oCEmptyLayout, OCSideNav oCSideNav) {
        super(obj, view, i);
        this.notificationList = oCRecyclerView;
        this.notificationListHeader = oCTextView;
        this.nullState = oCEmptyLayout;
        this.sideNav = oCSideNav;
    }

    @Nullable
    public String getSectionHeader() {
        return this.mSectionHeader;
    }

    @NonNull
    public static AnytimeTabletNotificationsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletNotificationsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletNotificationsViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_notifications_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletNotificationsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletNotificationsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletNotificationsViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_notifications_view_v2, null, false, obj);
    }

    public static AnytimeTabletNotificationsViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletNotificationsViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletNotificationsViewV2Binding) bind(obj, view, R.layout.anytime_tablet_notifications_view_v2);
    }
}
