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
import com.oculus.ocui.OCSelect;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.guidebar.QPGuideBarView;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel;

public abstract class AnytimeTabletLibraryViewV2Binding extends ViewDataBinding {
    @NonNull
    public final OCButton backToTopButton;
    @NonNull
    public final LinearLayout backToTopButtonHeightLayout;
    @NonNull
    public final ImageView backToTopIcon;
    @NonNull
    public final OCTextView backToTopText;
    @NonNull
    public final OCButton dismissCtaIcon;
    @NonNull
    public final OCSelect filterSelect;
    @NonNull
    public final View guideBarBody;
    @NonNull
    public final OCButton guideBarCta;
    @NonNull
    public final ImageView guideBarIcon;
    @NonNull
    public final OCTextView guideBarText;
    @NonNull
    public final OCRecyclerView libraryContent;
    @Bindable
    protected LibraryViewModel mViewModel;
    @NonNull
    public final OCSelect platformSelect;
    @NonNull
    public final QPGuideBarView qpGuideBar;
    @NonNull
    public final OCSelect sorterSelect;
    @NonNull
    public final OCTextView tabletTitle;

    public abstract void setViewModel(@Nullable LibraryViewModel libraryViewModel);

    protected AnytimeTabletLibraryViewV2Binding(Object obj, View view, int i, OCButton oCButton, LinearLayout linearLayout, ImageView imageView, OCTextView oCTextView, OCButton oCButton2, OCSelect oCSelect, View view2, OCButton oCButton3, ImageView imageView2, OCTextView oCTextView2, OCRecyclerView oCRecyclerView, OCSelect oCSelect2, QPGuideBarView qPGuideBarView, OCSelect oCSelect3, OCTextView oCTextView3) {
        super(obj, view, i);
        this.backToTopButton = oCButton;
        this.backToTopButtonHeightLayout = linearLayout;
        this.backToTopIcon = imageView;
        this.backToTopText = oCTextView;
        this.dismissCtaIcon = oCButton2;
        this.filterSelect = oCSelect;
        this.guideBarBody = view2;
        this.guideBarCta = oCButton3;
        this.guideBarIcon = imageView2;
        this.guideBarText = oCTextView2;
        this.libraryContent = oCRecyclerView;
        this.platformSelect = oCSelect2;
        this.qpGuideBar = qPGuideBarView;
        this.sorterSelect = oCSelect3;
        this.tabletTitle = oCTextView3;
    }

    @Nullable
    public LibraryViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static AnytimeTabletLibraryViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLibraryViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLibraryViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLibraryViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_view_v2, null, false, obj);
    }

    public static AnytimeTabletLibraryViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLibraryViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLibraryViewV2Binding) bind(obj, view, R.layout.anytime_tablet_library_view_v2);
    }
}
