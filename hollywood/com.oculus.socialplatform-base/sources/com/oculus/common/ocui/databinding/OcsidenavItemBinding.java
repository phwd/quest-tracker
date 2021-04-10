package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSideNavItem;
import com.oculus.socialplatform.R;

public abstract class OcsidenavItemBinding extends AnonymousClass1uW {
    @NonNull
    public final OcsidenavBadgeBinding badge;
    @Bindable
    public Drawable mBackground;
    @Bindable
    public OCSideNavItem mSideNavItem;
    @NonNull
    public final OCButton navButton;

    public abstract void setBackground(@Nullable Drawable drawable);

    public abstract void setSideNavItem(@Nullable OCSideNavItem oCSideNavItem);

    @Nullable
    public Drawable getBackground() {
        return this.mBackground;
    }

    @Nullable
    public OCSideNavItem getSideNavItem() {
        return this.mSideNavItem;
    }

    public OcsidenavItemBinding(Object obj, View view, int i, OcsidenavBadgeBinding ocsidenavBadgeBinding, OCButton oCButton) {
        super(obj, view, i);
        this.badge = ocsidenavBadgeBinding;
        setContainedBinding(ocsidenavBadgeBinding);
        this.navButton = oCButton;
    }

    public static OcsidenavItemBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcsidenavItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcsidenavItemBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocsidenav_item);
    }

    @NonNull
    public static OcsidenavItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcsidenavItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcsidenavItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcsidenavItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocsidenav_item, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcsidenavItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcsidenavItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocsidenav_item, null, false);
    }
}
