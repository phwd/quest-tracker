package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletLibraryUnknownSourceRowV2Binding extends ViewDataBinding {
    @NonNull
    public final OCButton appMenuButton;
    @NonNull
    public final ImageView appMenuIcon;
    @NonNull
    public final OCTextView appPackageName;
    @NonNull
    public final OCButton appRowButton;
    @NonNull
    public final OCTextView appTitle;

    protected AnytimeTabletLibraryUnknownSourceRowV2Binding(Object obj, View view, int i, OCButton oCButton, ImageView imageView, OCTextView oCTextView, OCButton oCButton2, OCTextView oCTextView2) {
        super(obj, view, i);
        this.appMenuButton = oCButton;
        this.appMenuIcon = imageView;
        this.appPackageName = oCTextView;
        this.appRowButton = oCButton2;
        this.appTitle = oCTextView2;
    }

    @NonNull
    public static AnytimeTabletLibraryUnknownSourceRowV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryUnknownSourceRowV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLibraryUnknownSourceRowV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_unknown_source_row_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLibraryUnknownSourceRowV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryUnknownSourceRowV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLibraryUnknownSourceRowV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_unknown_source_row_v2, null, false, obj);
    }

    public static AnytimeTabletLibraryUnknownSourceRowV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLibraryUnknownSourceRowV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLibraryUnknownSourceRowV2Binding) bind(obj, view, R.layout.anytime_tablet_library_unknown_source_row_v2);
    }
}
