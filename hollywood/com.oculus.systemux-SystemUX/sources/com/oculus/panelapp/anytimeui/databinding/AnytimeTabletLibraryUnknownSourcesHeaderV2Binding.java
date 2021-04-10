package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCLink;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletLibraryUnknownSourcesHeaderV2Binding extends ViewDataBinding {
    @NonNull
    public final ImageView infoIcon;
    @NonNull
    public final OCButton openTabButton;
    @NonNull
    public final ImageView openTabIcon;
    @NonNull
    public final ConstraintLayout openTabLayout;
    @NonNull
    public final OCLink warningDescription;

    protected AnytimeTabletLibraryUnknownSourcesHeaderV2Binding(Object obj, View view, int i, ImageView imageView, OCButton oCButton, ImageView imageView2, ConstraintLayout constraintLayout, OCLink oCLink) {
        super(obj, view, i);
        this.infoIcon = imageView;
        this.openTabButton = oCButton;
        this.openTabIcon = imageView2;
        this.openTabLayout = constraintLayout;
        this.warningDescription = oCLink;
    }

    @NonNull
    public static AnytimeTabletLibraryUnknownSourcesHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryUnknownSourcesHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLibraryUnknownSourcesHeaderV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_unknown_sources_header_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLibraryUnknownSourcesHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryUnknownSourcesHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLibraryUnknownSourcesHeaderV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_unknown_sources_header_v2, null, false, obj);
    }

    public static AnytimeTabletLibraryUnknownSourcesHeaderV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLibraryUnknownSourcesHeaderV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLibraryUnknownSourcesHeaderV2Binding) bind(obj, view, R.layout.anytime_tablet_library_unknown_sources_header_v2);
    }
}
