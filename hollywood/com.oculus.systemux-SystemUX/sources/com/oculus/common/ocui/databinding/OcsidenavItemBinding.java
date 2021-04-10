package com.oculus.common.ocui.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.ocui.R;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSideNavItem;

public abstract class OcsidenavItemBinding extends ViewDataBinding {
    @NonNull
    public final OcsidenavBadgeBinding badge;
    @Bindable
    protected Drawable mBackground;
    @Bindable
    protected OCSideNavItem mSideNavItem;
    @NonNull
    public final OCButton navButton;

    public abstract void setBackground(@Nullable Drawable drawable);

    public abstract void setSideNavItem(@Nullable OCSideNavItem oCSideNavItem);

    protected OcsidenavItemBinding(Object obj, View view, int i, OcsidenavBadgeBinding ocsidenavBadgeBinding, OCButton oCButton) {
        super(obj, view, i);
        this.badge = ocsidenavBadgeBinding;
        setContainedBinding(this.badge);
        this.navButton = oCButton;
    }

    @Nullable
    public OCSideNavItem getSideNavItem() {
        return this.mSideNavItem;
    }

    @Nullable
    public Drawable getBackground() {
        return this.mBackground;
    }

    @NonNull
    public static OcsidenavItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcsidenavItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OcsidenavItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocsidenav_item, viewGroup, z, obj);
    }

    @NonNull
    public static OcsidenavItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcsidenavItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OcsidenavItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocsidenav_item, null, false, obj);
    }

    public static OcsidenavItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OcsidenavItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcsidenavItemBinding) bind(obj, view, R.layout.ocsidenav_item);
    }
}
