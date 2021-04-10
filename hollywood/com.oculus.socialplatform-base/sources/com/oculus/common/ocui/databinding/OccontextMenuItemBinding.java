package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class OccontextMenuItemBinding extends AnonymousClass1uW {
    @NonNull
    public final ConstraintLayout contextMenuItem;
    @NonNull
    public final ImageView itemIcon;
    @NonNull
    public final FrameLayout itemIconContainer;
    @NonNull
    public final ImageView itemRightIcon;
    @NonNull
    public final OCTextView itemSubtitle;
    @NonNull
    public final ConstraintLayout itemTextContainer;
    @NonNull
    public final OCTextView itemTitle;

    public OccontextMenuItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, FrameLayout frameLayout, ImageView imageView2, OCTextView oCTextView, ConstraintLayout constraintLayout2, OCTextView oCTextView2) {
        super(obj, view, i);
        this.contextMenuItem = constraintLayout;
        this.itemIcon = imageView;
        this.itemIconContainer = frameLayout;
        this.itemRightIcon = imageView2;
        this.itemSubtitle = oCTextView;
        this.itemTextContainer = constraintLayout2;
        this.itemTitle = oCTextView2;
    }

    public static OccontextMenuItemBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OccontextMenuItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OccontextMenuItemBinding) AnonymousClass1uW.bind(obj, view, R.layout.occontext_menu_item);
    }

    @NonNull
    public static OccontextMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OccontextMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OccontextMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OccontextMenuItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.occontext_menu_item, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OccontextMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OccontextMenuItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.occontext_menu_item, null, false);
    }
}
