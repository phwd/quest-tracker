package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.ManagedLauncherViewModel;

public abstract class AnytimeTabletManagedLauncherViewV2Binding extends ViewDataBinding {
    @NonNull
    public final OCButton backToTopButton;
    @NonNull
    public final LinearLayout backToTopButtonHeightLayout;
    @NonNull
    public final ImageView backToTopIcon;
    @NonNull
    public final OCTextView backToTopText;
    @Bindable
    protected ManagedLauncherViewModel mViewModel;
    @NonNull
    public final OCRecyclerView managedLauncherContent;
    @NonNull
    public final OCTextView managedLauncherTabletNoApps;
    @NonNull
    public final OCTextView managedLauncherTabletTitle;

    public abstract void setViewModel(@Nullable ManagedLauncherViewModel managedLauncherViewModel);

    protected AnytimeTabletManagedLauncherViewV2Binding(Object obj, View view, int i, OCButton oCButton, LinearLayout linearLayout, ImageView imageView, OCTextView oCTextView, OCRecyclerView oCRecyclerView, OCTextView oCTextView2, OCTextView oCTextView3) {
        super(obj, view, i);
        this.backToTopButton = oCButton;
        this.backToTopButtonHeightLayout = linearLayout;
        this.backToTopIcon = imageView;
        this.backToTopText = oCTextView;
        this.managedLauncherContent = oCRecyclerView;
        this.managedLauncherTabletNoApps = oCTextView2;
        this.managedLauncherTabletTitle = oCTextView3;
    }

    @Nullable
    public ManagedLauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static AnytimeTabletManagedLauncherViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletManagedLauncherViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletManagedLauncherViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_managed_launcher_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletManagedLauncherViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletManagedLauncherViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletManagedLauncherViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_managed_launcher_view_v2, null, false, obj);
    }

    public static AnytimeTabletManagedLauncherViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletManagedLauncherViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletManagedLauncherViewV2Binding) bind(obj, view, R.layout.anytime_tablet_managed_launcher_view_v2);
    }
}
