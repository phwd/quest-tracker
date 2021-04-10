package com.oculus.panelapp.androiddialog.dialogs.social.list_item;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.databinding.SocialListItemBinding;
import com.oculus.tablet.utils.ViewBindingUtil;

public class SocialListItem extends ConstraintLayout {
    private SocialListItemBinding mBinding;
    private RequestBuilder<Bitmap> mGlideFetcher;
    private RequestManager mGlideManager;
    private final RequestListener<Bitmap> mImageListener;
    private SocialListItemViewModel viewModel;

    public SocialListItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mImageListener = buildImageListener();
        this.mGlideManager = Glide.with(getContext());
        this.mGlideFetcher = this.mGlideManager.asBitmap();
        this.viewModel = SocialListItemViewModel.fromTypedArray(context, attributeSet);
        setupBinding(context);
    }

    @VisibleForTesting
    public SocialListItem(RequestManager requestManager, Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mImageListener = buildImageListener();
        this.mGlideManager = requestManager;
        this.mGlideFetcher = this.mGlideManager.asBitmap();
        this.viewModel = SocialListItemViewModel.fromTypedArray(context, attributeSet);
        setupBinding(context);
    }

    private void setupBinding(Context context) {
        this.mBinding = (SocialListItemBinding) DataBindingUtil.inflate((LayoutInflater) context.getSystemService("layout_inflater"), R.layout.social_list_item, this, true);
        ViewBindingUtil.toggleViewInvisible(this.mBinding.socialListItemTitle, ViewBindingUtil.NOT_NULL_OR_EMPTY.test(this.viewModel.getTitle()));
        ViewBindingUtil.toggleViewGone(this.mBinding.socialListItemSubtitle, ViewBindingUtil.NOT_NULL_OR_EMPTY.test(this.viewModel.getSubtitle()));
        ViewBindingUtil.toggleViewGone(this.mBinding.socialListItemAltSubtitle, ViewBindingUtil.NOT_NULL_OR_EMPTY.test(this.viewModel.getAltSubtitle()));
        this.mBinding.socialListItemTitle.setText(this.viewModel.getTitle());
        this.mBinding.socialListItemSubtitle.setText(this.viewModel.getSubtitle());
        this.mBinding.socialListItemAltSubtitle.setText(this.viewModel.getAltSubtitle());
        if (this.viewModel.getAltTextColor() != -1) {
            this.mBinding.socialListItemAltSubtitle.setTextColor(this.viewModel.getAltTextColor());
        }
    }

    private void loadImages() {
        applyImageVisibility();
        if (this.viewModel.hasIcon()) {
            this.mGlideFetcher.load(Integer.valueOf(this.viewModel.getImageResourceId())).into(this.mBinding.socialListItemLeftSingleIcon);
        }
        if (this.viewModel.hasSubtitleIcon()) {
            this.mGlideFetcher.load(Integer.valueOf(this.viewModel.getSubtitleIcon())).into(this.mBinding.socialListItemSubtitleIcon);
        }
        if (this.viewModel.hasAltSubtitleIcon()) {
            if (this.viewModel.getAltTextColor() != -1) {
                this.mBinding.socialListItemAltSubtitleIcon.setColorFilter(this.viewModel.getAltTextColor());
            }
            this.mGlideFetcher.load(Integer.valueOf(this.viewModel.getAltSubtitleIcon())).into(this.mBinding.socialListItemAltSubtitleIcon);
        }
        if (this.viewModel.hasMultiImage()) {
            ((RequestBuilder) this.mGlideFetcher.load(this.viewModel.getImageUrl()).circleCrop()).listener(this.mImageListener).into(this.mBinding.socialListItemLeftMultiImage1);
            ((RequestBuilder) this.mGlideFetcher.load(this.viewModel.getImageUrl2()).circleCrop()).listener(this.mImageListener).into(this.mBinding.socialListItemLeftMultiImage2);
        } else if (this.viewModel.hasSingleImage()) {
            ((RequestBuilder) this.mGlideFetcher.load(this.viewModel.getImageUrl()).circleCrop()).listener(buildImageListener()).into(this.mBinding.socialListItemLeftSingleImage);
        }
        if (this.viewModel.hasGlyph()) {
            this.mGlideFetcher.load(Integer.valueOf(this.viewModel.getGlyphResourceId())).into(this.mBinding.socialListItemRightGlyph);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void applyImageVisibility() {
        ViewBindingUtil.toggleViewInvisible(this.mBinding.socialListItemRightGlyph, this.viewModel.hasGlyph());
        ViewBindingUtil.toggleViewInvisible(this.mBinding.socialListItemLeftSingleIcon, this.viewModel.hasIcon());
        ViewBindingUtil.toggleViewInvisible(this.mBinding.socialListItemLeftSingleIconContainer, this.viewModel.hasIcon());
        ViewBindingUtil.toggleViewInvisible(this.mBinding.socialListItemLeftSingleImage, this.viewModel.hasSingleImage());
        ViewBindingUtil.toggleViewInvisible(this.mBinding.socialListItemLeftMultiImage1Outline, this.viewModel.hasMultiImage());
        ViewBindingUtil.toggleViewInvisible(this.mBinding.socialListItemLeftMultiImage1, this.viewModel.hasMultiImage());
        ViewBindingUtil.toggleViewInvisible(this.mBinding.socialListItemLeftMultiImage2, this.viewModel.hasMultiImage());
        ViewBindingUtil.toggleViewGone(this.mBinding.socialListItemSubtitleIconSpacer, this.viewModel.hasSubtitleIcon());
        ViewBindingUtil.toggleViewGone(this.mBinding.socialListItemSubtitleIcon, this.viewModel.hasSubtitleIcon());
        ViewBindingUtil.toggleViewGone(this.mBinding.socialListItemAltSubtitleIcon, this.viewModel.hasAltSubtitleIcon());
    }

    private RequestListener<Bitmap> buildImageListener() {
        return new RequestListener<Bitmap>() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.list_item.SocialListItem.AnonymousClass1 */

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
                return false;
            }

            public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
                SocialListItem.this.applyImageVisibility();
                if (!SocialListItem.this.viewModel.hasSingleImage() && !SocialListItem.this.viewModel.hasMultiImage()) {
                    return false;
                }
                SocialListItem.this.mBinding.socialListItemLeftSingleIcon.setVisibility(4);
                SocialListItem.this.mBinding.socialListItemLeftSingleIconContainer.setVisibility(4);
                return false;
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        loadImages();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mGlideManager.clear(this.mBinding.socialListItemLeftSingleIcon);
        this.mGlideManager.clear(this.mBinding.socialListItemLeftSingleImage);
        this.mGlideManager.clear(this.mBinding.socialListItemLeftMultiImage1);
        this.mGlideManager.clear(this.mBinding.socialListItemLeftMultiImage2);
        this.mGlideManager.clear(this.mBinding.socialListItemSubtitleIcon);
        this.mGlideManager.clear(this.mBinding.socialListItemAltSubtitleIcon);
        this.mGlideManager.clear(this.mBinding.socialListItemRightGlyph);
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mBinding.socialListItemButton.setVisibility(0);
        this.mBinding.socialListItemButton.setEventHandler(oCEventHandler);
    }

    @BindingAdapter({"image_url"})
    public static void setImageUrl(SocialListItem socialListItem, String str) {
        socialListItem.viewModel.setImageUrl(str);
        socialListItem.loadImages();
    }

    @BindingAdapter({"image_url2"})
    public static void setImageUrl2(SocialListItem socialListItem, String str) {
        socialListItem.viewModel.setImageUrl2(str);
        socialListItem.loadImages();
    }

    @BindingAdapter({"image"})
    public static void setImage(SocialListItem socialListItem, int i) {
        socialListItem.viewModel.setImageResourceId(i);
        socialListItem.loadImages();
    }

    @BindingAdapter({"title"})
    public static void setTitle(SocialListItem socialListItem, String str) {
        socialListItem.viewModel.setTitle(str);
        socialListItem.mBinding.socialListItemTitle.setText(str);
        ViewBindingUtil.toggleViewGone(socialListItem.mBinding.socialListItemTitle, ViewBindingUtil.NOT_NULL_OR_EMPTY.test(str));
    }

    @BindingAdapter({"subtitle"})
    public static void setSubtitle(SocialListItem socialListItem, String str) {
        socialListItem.viewModel.setSubtitle(str);
        socialListItem.mBinding.socialListItemSubtitle.setText(str);
        ViewBindingUtil.toggleViewGone(socialListItem.mBinding.socialListItemSubtitle, ViewBindingUtil.NOT_NULL_OR_EMPTY.test(str));
    }

    @BindingAdapter({"subtitle_icon"})
    public static void setSubtitleIcon(SocialListItem socialListItem, int i) {
        socialListItem.viewModel.setSubtitleIcon(i);
        socialListItem.loadImages();
    }

    @BindingAdapter({"alt_subtitle"})
    public static void setAltSubtitle(SocialListItem socialListItem, String str) {
        socialListItem.viewModel.setAltSubtitle(str);
        socialListItem.mBinding.socialListItemAltSubtitle.setText(str);
        ViewBindingUtil.toggleViewGone(socialListItem.mBinding.socialListItemAltSubtitle, ViewBindingUtil.NOT_NULL_OR_EMPTY.test(str));
    }

    @BindingAdapter({"alt_subtitle_icon"})
    public static void setAltSubtitleIcon(SocialListItem socialListItem, int i) {
        socialListItem.viewModel.setAltSubtitleIcon(i);
        socialListItem.loadImages();
    }
}
