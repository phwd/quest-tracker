package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletLibraryNullStateBinding extends ViewDataBinding {
    @NonNull
    public final OCTextView nullStateDescription;

    protected AnytimeTabletLibraryNullStateBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.nullStateDescription = oCTextView;
    }

    @NonNull
    public static AnytimeTabletLibraryNullStateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryNullStateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLibraryNullStateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_null_state, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLibraryNullStateBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryNullStateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLibraryNullStateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_null_state, null, false, obj);
    }

    public static AnytimeTabletLibraryNullStateBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLibraryNullStateBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLibraryNullStateBinding) bind(obj, view, R.layout.anytime_tablet_library_null_state);
    }
}
