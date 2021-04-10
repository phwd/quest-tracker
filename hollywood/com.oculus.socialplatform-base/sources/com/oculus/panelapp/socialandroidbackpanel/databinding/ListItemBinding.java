package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import X.C10221le;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class ListItemBinding extends AnonymousClass1uW {
    @NonNull
    public final ConstraintLayout listItem;
    @NonNull
    public final OCTextView listItemAltSubtitle;
    @NonNull
    public final C10221le listItemAltSubtitleIcon;
    @NonNull
    public final Barrier listItemBottomBarrier;
    @NonNull
    public final OCButton listItemButton;
    @NonNull
    public final C10221le listItemLeftMultiImage1;
    @NonNull
    public final View listItemLeftMultiImage1Outline;
    @NonNull
    public final C10221le listItemLeftMultiImage2;
    @NonNull
    public final C10221le listItemLeftSingleIcon;
    @NonNull
    public final View listItemLeftSingleIconContainer;
    @NonNull
    public final C10221le listItemLeftSingleImage;
    @NonNull
    public final C10221le listItemRightGlyph;
    @NonNull
    public final OCTextView listItemSubtitle;
    @NonNull
    public final C10221le listItemSubtitleIcon;
    @NonNull
    public final Barrier listItemSubtitleIconBarrier;
    @NonNull
    public final View listItemSubtitleIconSpacer;
    @NonNull
    public final OCTextView listItemTitle;
    @NonNull
    public final Barrier listItemTitleBarrier;

    public ListItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, OCTextView oCTextView, C10221le r7, Barrier barrier, OCButton oCButton, C10221le r10, View view2, C10221le r12, C10221le r13, View view3, C10221le r15, C10221le r16, OCTextView oCTextView2, C10221le r18, Barrier barrier2, View view4, OCTextView oCTextView3, Barrier barrier3) {
        super(obj, view, i);
        this.listItem = constraintLayout;
        this.listItemAltSubtitle = oCTextView;
        this.listItemAltSubtitleIcon = r7;
        this.listItemBottomBarrier = barrier;
        this.listItemButton = oCButton;
        this.listItemLeftMultiImage1 = r10;
        this.listItemLeftMultiImage1Outline = view2;
        this.listItemLeftMultiImage2 = r12;
        this.listItemLeftSingleIcon = r13;
        this.listItemLeftSingleIconContainer = view3;
        this.listItemLeftSingleImage = r15;
        this.listItemRightGlyph = r16;
        this.listItemSubtitle = oCTextView2;
        this.listItemSubtitleIcon = r18;
        this.listItemSubtitleIconBarrier = barrier2;
        this.listItemSubtitleIconSpacer = view4;
        this.listItemTitle = oCTextView3;
        this.listItemTitleBarrier = barrier3;
    }

    public static ListItemBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static ListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemBinding) AnonymousClass1uW.bind(obj, view, R.layout.list_item);
    }

    @NonNull
    public static ListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static ListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static ListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (ListItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.list_item, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static ListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (ListItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.list_item, null, false);
    }
}
