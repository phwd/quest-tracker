package com.oculus.panelapp.socialandroidbackpanel.views.list_item;

import X.AnonymousClass0mX;
import X.AnonymousClass1lG;
import X.AnonymousClass1uU;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import com.oculus.ocui.OCEventHandler;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.databinding.ListItemBinding;
import com.oculus.socialplatform.R;
import com.oculus.tablet.utils.ViewBindingUtil;
import java.util.function.Predicate;

public class ListItem extends ConstraintLayout {
    public ListItemBinding mBinding;
    public AnonymousClass1lG<AnonymousClass0mX> mImageListener;
    public ListItemViewModel mViewModel;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void applyImageVisibility() {
        ViewBindingUtil.setViewVisibility(this.mBinding.listItemRightGlyph, 4, this.mViewModel.mHasGlyph);
        ViewBindingUtil.setViewVisibility(this.mBinding.listItemLeftSingleIcon, 4, this.mViewModel.mHasIcon);
        ViewBindingUtil.setViewVisibility(this.mBinding.listItemLeftSingleIconContainer, 4, this.mViewModel.mHasIcon);
        ViewBindingUtil.setViewVisibility(this.mBinding.listItemLeftSingleImage, 4, this.mViewModel.mHasSingleImage);
        ViewBindingUtil.setViewVisibility(this.mBinding.listItemLeftMultiImage1Outline, 4, this.mViewModel.mHasMultiImage);
        ViewBindingUtil.setViewVisibility(this.mBinding.listItemLeftMultiImage1, 4, this.mViewModel.mHasMultiImage);
        ViewBindingUtil.setViewVisibility(this.mBinding.listItemLeftMultiImage2, 4, this.mViewModel.mHasMultiImage);
        ViewBindingUtil.toggleViewGone(this.mBinding.listItemSubtitleIconSpacer, this.mViewModel.mHasSubtitleIcon);
        ViewBindingUtil.toggleViewGone(this.mBinding.listItemSubtitleIcon, this.mViewModel.mHasSubtitleIcon);
        ViewBindingUtil.toggleViewGone(this.mBinding.listItemAltSubtitleIcon, this.mViewModel.mHasAltSubtitleIcon);
    }

    @BindingAdapter({"alt_subtitle"})
    public static void setAltSubtitle(ListItem listItem, String str) {
        listItem.mViewModel.setAltSubtitle(str);
        listItem.mBinding.listItemAltSubtitle.setText(str);
        ViewBindingUtil.toggleViewGone(listItem.mBinding.listItemAltSubtitle, ViewBindingUtil.NOT_NULL_OR_EMPTY.test(str));
    }

    @BindingAdapter({"alt_subtitle_icon"})
    public static void setAltSubtitleIcon(ListItem listItem, int i) {
        listItem.mViewModel.setAltSubtitleIcon(i);
        listItem.loadImages();
    }

    @BindingAdapter({"image"})
    public static void setImage(ListItem listItem, int i) {
        listItem.mViewModel.setImageResourceId(i);
        listItem.loadImages();
    }

    @BindingAdapter({"image_url"})
    public static void setImageUrl(ListItem listItem, String str) {
        listItem.mViewModel.setImageUrl(str);
        listItem.loadImages();
    }

    @BindingAdapter({"image_url2"})
    public static void setImageUrl2(ListItem listItem, String str) {
        listItem.mViewModel.setImageUrl2(str);
        listItem.loadImages();
    }

    @BindingAdapter({"subtitle"})
    public static void setSubtitle(ListItem listItem, String str) {
        listItem.mViewModel.setSubtitle(str);
        listItem.mBinding.listItemSubtitle.setText(str);
        ViewBindingUtil.toggleViewGone(listItem.mBinding.listItemSubtitle, ViewBindingUtil.NOT_NULL_OR_EMPTY.test(str));
    }

    @BindingAdapter({"subtitle_icon"})
    public static void setSubtitleIcon(ListItem listItem, int i) {
        listItem.mViewModel.setSubtitleIcon(i);
        listItem.loadImages();
    }

    @BindingAdapter({"title"})
    public static void setTitle(ListItem listItem, String str) {
        listItem.mViewModel.setTitle(str);
        listItem.mBinding.listItemTitle.setText(str);
        ViewBindingUtil.toggleViewGone(listItem.mBinding.listItemTitle, ViewBindingUtil.NOT_NULL_OR_EMPTY.test(str));
    }

    private void setupBinding(Context context) {
        ListItemBinding listItemBinding = (ListItemBinding) AnonymousClass1uU.A00((LayoutInflater) context.getSystemService("layout_inflater"), R.layout.list_item, this, true);
        this.mBinding = listItemBinding;
        ViewBindingUtil.setViewVisibility(listItemBinding.listItemTitle, 4, ViewBindingUtil.NOT_NULL_OR_EMPTY.test(this.mViewModel.mTitle));
        OCTextView oCTextView = this.mBinding.listItemSubtitle;
        Predicate<String> predicate = ViewBindingUtil.NOT_NULL_OR_EMPTY;
        ViewBindingUtil.toggleViewGone(oCTextView, predicate.test(this.mViewModel.mSubtitle));
        ViewBindingUtil.toggleViewGone(this.mBinding.listItemAltSubtitle, predicate.test(this.mViewModel.mAltSubtitle));
        this.mBinding.listItemTitle.setText(this.mViewModel.mTitle);
        this.mBinding.listItemSubtitle.setText(this.mViewModel.mSubtitle);
        this.mBinding.listItemAltSubtitle.setText(this.mViewModel.mAltSubtitle);
        int i = this.mViewModel.mAltTextColor;
        if (i != -1) {
            this.mBinding.listItemAltSubtitle.setTextColor(i);
        }
        AnonymousClass1 r1 = new ViewOutlineProvider() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.list_item.ListItem.AnonymousClass1 */

            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), (float) (view.getWidth() >> 1));
            }
        };
        this.mImageListener = new AnonymousClass1lG<AnonymousClass0mX>() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.list_item.ListItem.AnonymousClass2 */

            public void onFinalImageSet(String str, AnonymousClass0mX r5, Animatable animatable) {
                ListItem.this.applyImageVisibility();
                ListItem listItem = ListItem.this;
                ListItemViewModel listItemViewModel = listItem.mViewModel;
                if (listItemViewModel.mHasSingleImage || listItemViewModel.mHasMultiImage) {
                    listItem.mBinding.listItemLeftSingleIcon.setVisibility(4);
                    ListItem.this.mBinding.listItemLeftSingleIconContainer.setVisibility(4);
                }
            }
        };
        this.mBinding.listItemLeftMultiImage1.setOutlineProvider(r1);
        this.mBinding.listItemLeftMultiImage1.setClipToOutline(true);
        this.mBinding.listItemLeftMultiImage2.setOutlineProvider(r1);
        this.mBinding.listItemLeftMultiImage2.setClipToOutline(true);
        this.mBinding.listItemLeftSingleImage.setOutlineProvider(r1);
        this.mBinding.listItemLeftSingleImage.setClipToOutline(true);
    }

    @VisibleForTesting
    public AnonymousClass1lG<AnonymousClass0mX> buildImageListener() {
        return new AnonymousClass1lG<AnonymousClass0mX>() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.list_item.ListItem.AnonymousClass2 */

            public void onFinalImageSet(String str, AnonymousClass0mX r5, Animatable animatable) {
                ListItem.this.applyImageVisibility();
                ListItem listItem = ListItem.this;
                ListItemViewModel listItemViewModel = listItem.mViewModel;
                if (listItemViewModel.mHasSingleImage || listItemViewModel.mHasMultiImage) {
                    listItem.mBinding.listItemLeftSingleIcon.setVisibility(4);
                    ListItem.this.mBinding.listItemLeftSingleIconContainer.setVisibility(4);
                }
            }
        };
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mBinding.listItemButton.setVisibility(0);
        this.mBinding.listItemButton.mEventHandler = oCEventHandler;
    }

    public ListItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mViewModel = ListItemViewModel.fromTypedArray(context, attributeSet);
        setupBinding(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadImages() {
        /*
        // Method dump skipped, instructions count: 228
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.views.list_item.ListItem.loadImages():void");
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        loadImages();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
