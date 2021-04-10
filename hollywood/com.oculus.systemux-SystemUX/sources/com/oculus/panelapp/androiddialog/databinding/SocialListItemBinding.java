package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;

public abstract class SocialListItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout socialListItem;
    @NonNull
    public final OCTextView socialListItemAltSubtitle;
    @NonNull
    public final ImageView socialListItemAltSubtitleIcon;
    @NonNull
    public final Barrier socialListItemBottomBarrier;
    @NonNull
    public final OCButton socialListItemButton;
    @NonNull
    public final ImageView socialListItemLeftMultiImage1;
    @NonNull
    public final View socialListItemLeftMultiImage1Outline;
    @NonNull
    public final ImageView socialListItemLeftMultiImage2;
    @NonNull
    public final ImageView socialListItemLeftSingleIcon;
    @NonNull
    public final View socialListItemLeftSingleIconContainer;
    @NonNull
    public final ImageView socialListItemLeftSingleImage;
    @NonNull
    public final ImageView socialListItemRightGlyph;
    @NonNull
    public final OCTextView socialListItemSubtitle;
    @NonNull
    public final ImageView socialListItemSubtitleIcon;
    @NonNull
    public final Barrier socialListItemSubtitleIconBarrier;
    @NonNull
    public final View socialListItemSubtitleIconSpacer;
    @NonNull
    public final OCTextView socialListItemTitle;
    @NonNull
    public final Barrier socialListItemTitleBarrier;

    protected SocialListItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, OCTextView oCTextView, ImageView imageView, Barrier barrier, OCButton oCButton, ImageView imageView2, View view2, ImageView imageView3, ImageView imageView4, View view3, ImageView imageView5, ImageView imageView6, OCTextView oCTextView2, ImageView imageView7, Barrier barrier2, View view4, OCTextView oCTextView3, Barrier barrier3) {
        super(obj, view, i);
        this.socialListItem = constraintLayout;
        this.socialListItemAltSubtitle = oCTextView;
        this.socialListItemAltSubtitleIcon = imageView;
        this.socialListItemBottomBarrier = barrier;
        this.socialListItemButton = oCButton;
        this.socialListItemLeftMultiImage1 = imageView2;
        this.socialListItemLeftMultiImage1Outline = view2;
        this.socialListItemLeftMultiImage2 = imageView3;
        this.socialListItemLeftSingleIcon = imageView4;
        this.socialListItemLeftSingleIconContainer = view3;
        this.socialListItemLeftSingleImage = imageView5;
        this.socialListItemRightGlyph = imageView6;
        this.socialListItemSubtitle = oCTextView2;
        this.socialListItemSubtitleIcon = imageView7;
        this.socialListItemSubtitleIconBarrier = barrier2;
        this.socialListItemSubtitleIconSpacer = view4;
        this.socialListItemTitle = oCTextView3;
        this.socialListItemTitleBarrier = barrier3;
    }

    @NonNull
    public static SocialListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SocialListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static SocialListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SocialListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_list_item, null, false, obj);
    }

    public static SocialListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialListItemBinding) bind(obj, view, R.layout.social_list_item);
    }
}
