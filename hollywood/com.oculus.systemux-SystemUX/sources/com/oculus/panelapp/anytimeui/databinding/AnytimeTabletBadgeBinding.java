package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletBadgeBinding extends ViewDataBinding {
    @Bindable
    protected String mBadgeCount;
    @NonNull
    public final OCTextView text;

    public abstract void setBadgeCount(@Nullable String str);

    protected AnytimeTabletBadgeBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.text = oCTextView;
    }

    @Nullable
    public String getBadgeCount() {
        return this.mBadgeCount;
    }

    @NonNull
    public static AnytimeTabletBadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletBadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletBadgeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_badge, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletBadgeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletBadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletBadgeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_badge, null, false, obj);
    }

    public static AnytimeTabletBadgeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletBadgeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletBadgeBinding) bind(obj, view, R.layout.anytime_tablet_badge);
    }
}
