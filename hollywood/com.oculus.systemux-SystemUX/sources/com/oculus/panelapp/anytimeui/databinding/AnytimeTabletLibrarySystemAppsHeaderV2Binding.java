package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel;

public abstract class AnytimeTabletLibrarySystemAppsHeaderV2Binding extends ViewDataBinding {
    @NonNull
    public final AnytimeTabletLibrarySystemAppButtonV2Binding libraryBrowserButton;
    @NonNull
    public final AnytimeTabletLibrarySystemAppButtonV2Binding libraryHomeButton;
    @NonNull
    public final AnytimeTabletLibrarySystemAppButtonV2Binding librarySocialButton;
    @NonNull
    public final AnytimeTabletLibrarySystemAppButtonV2Binding libraryStoreButton;
    @NonNull
    public final AnytimeTabletLibrarySystemAppButtonV2Binding libraryTvButton;
    @Bindable
    protected App mBrowserApp;
    @Bindable
    protected App mTvApp;
    @Bindable
    protected LibraryViewModel mViewModel;

    public abstract void setBrowserApp(@Nullable App app);

    public abstract void setTvApp(@Nullable App app);

    public abstract void setViewModel(@Nullable LibraryViewModel libraryViewModel);

    protected AnytimeTabletLibrarySystemAppsHeaderV2Binding(Object obj, View view, int i, AnytimeTabletLibrarySystemAppButtonV2Binding anytimeTabletLibrarySystemAppButtonV2Binding, AnytimeTabletLibrarySystemAppButtonV2Binding anytimeTabletLibrarySystemAppButtonV2Binding2, AnytimeTabletLibrarySystemAppButtonV2Binding anytimeTabletLibrarySystemAppButtonV2Binding3, AnytimeTabletLibrarySystemAppButtonV2Binding anytimeTabletLibrarySystemAppButtonV2Binding4, AnytimeTabletLibrarySystemAppButtonV2Binding anytimeTabletLibrarySystemAppButtonV2Binding5) {
        super(obj, view, i);
        this.libraryBrowserButton = anytimeTabletLibrarySystemAppButtonV2Binding;
        setContainedBinding(this.libraryBrowserButton);
        this.libraryHomeButton = anytimeTabletLibrarySystemAppButtonV2Binding2;
        setContainedBinding(this.libraryHomeButton);
        this.librarySocialButton = anytimeTabletLibrarySystemAppButtonV2Binding3;
        setContainedBinding(this.librarySocialButton);
        this.libraryStoreButton = anytimeTabletLibrarySystemAppButtonV2Binding4;
        setContainedBinding(this.libraryStoreButton);
        this.libraryTvButton = anytimeTabletLibrarySystemAppButtonV2Binding5;
        setContainedBinding(this.libraryTvButton);
    }

    @Nullable
    public LibraryViewModel getViewModel() {
        return this.mViewModel;
    }

    @Nullable
    public App getBrowserApp() {
        return this.mBrowserApp;
    }

    @Nullable
    public App getTvApp() {
        return this.mTvApp;
    }

    @NonNull
    public static AnytimeTabletLibrarySystemAppsHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibrarySystemAppsHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLibrarySystemAppsHeaderV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_system_apps_header_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLibrarySystemAppsHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibrarySystemAppsHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLibrarySystemAppsHeaderV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_system_apps_header_v2, null, false, obj);
    }

    public static AnytimeTabletLibrarySystemAppsHeaderV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLibrarySystemAppsHeaderV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLibrarySystemAppsHeaderV2Binding) bind(obj, view, R.layout.anytime_tablet_library_system_apps_header_v2);
    }
}
