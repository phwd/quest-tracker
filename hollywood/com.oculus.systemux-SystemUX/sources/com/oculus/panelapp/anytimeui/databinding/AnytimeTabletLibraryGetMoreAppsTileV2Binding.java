package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletLibraryGetMoreAppsTileV2Binding extends ViewDataBinding {
    @NonNull
    public final OCButton button;
    @NonNull
    public final CardView cardView;
    @NonNull
    public final ImageView chevronRightIcon;
    @NonNull
    public final OCTextView getMoreAppsText;
    @NonNull
    public final ImageView storeIcon;

    protected AnytimeTabletLibraryGetMoreAppsTileV2Binding(Object obj, View view, int i, OCButton oCButton, CardView cardView2, ImageView imageView, OCTextView oCTextView, ImageView imageView2) {
        super(obj, view, i);
        this.button = oCButton;
        this.cardView = cardView2;
        this.chevronRightIcon = imageView;
        this.getMoreAppsText = oCTextView;
        this.storeIcon = imageView2;
    }

    @NonNull
    public static AnytimeTabletLibraryGetMoreAppsTileV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryGetMoreAppsTileV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLibraryGetMoreAppsTileV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_get_more_apps_tile_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLibraryGetMoreAppsTileV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryGetMoreAppsTileV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLibraryGetMoreAppsTileV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_get_more_apps_tile_v2, null, false, obj);
    }

    public static AnytimeTabletLibraryGetMoreAppsTileV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLibraryGetMoreAppsTileV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLibraryGetMoreAppsTileV2Binding) bind(obj, view, R.layout.anytime_tablet_library_get_more_apps_tile_v2);
    }
}
