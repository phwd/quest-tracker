package com.oculus.panelapp.androiddialog.dialogs.social.list_item;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.tablet.utils.ViewBindingUtil;

/* access modifiers changed from: package-private */
public class SocialListItemViewModel {
    private String mAltSubtitle;
    private int mAltSubtitleIcon;
    private int mAltTextColor;
    private int mGlyphResourceId;
    private boolean mHasAltSubtitleIcon;
    private boolean mHasGlyph;
    private boolean mHasIcon;
    private boolean mHasMultiImage;
    private boolean mHasSingleImage;
    private boolean mHasSubtitleIcon;
    private int mImageResourceId;
    private String mImageUrl;
    private String mImageUrl2;
    private String mSubtitle;
    private int mSubtitleIcon;
    private String mTitle;

    SocialListItemViewModel() {
    }

    private void calculate() {
        this.mHasGlyph = ViewBindingUtil.NON_NEGATIVE.test(Integer.valueOf(this.mGlyphResourceId));
        this.mHasIcon = ViewBindingUtil.NON_NEGATIVE.test(Integer.valueOf(this.mImageResourceId));
        boolean z = false;
        this.mHasMultiImage = ViewBindingUtil.all(ViewBindingUtil.NOT_NULL_OR_EMPTY, this.mImageUrl, this.mImageUrl2);
        this.mHasSingleImage = !this.mHasMultiImage && ViewBindingUtil.NOT_NULL_OR_EMPTY.test(this.mImageUrl) && ViewBindingUtil.NULL_OR_EMPTY.test(this.mImageUrl2);
        this.mHasSubtitleIcon = ViewBindingUtil.NON_NEGATIVE.test(Integer.valueOf(this.mSubtitleIcon)) && ViewBindingUtil.NOT_NULL_OR_EMPTY.test(this.mSubtitle);
        if (this.mHasSubtitleIcon && ViewBindingUtil.NON_NEGATIVE.test(Integer.valueOf(this.mAltSubtitleIcon)) && ViewBindingUtil.NOT_NULL_OR_EMPTY.test(this.mAltSubtitle)) {
            z = true;
        }
        this.mHasAltSubtitleIcon = z;
    }

    public boolean hasGlyph() {
        return this.mHasGlyph;
    }

    public boolean hasIcon() {
        return this.mHasIcon;
    }

    public boolean hasMultiImage() {
        return this.mHasMultiImage;
    }

    public boolean hasSingleImage() {
        return this.mHasSingleImage;
    }

    public boolean hasSubtitleIcon() {
        return this.mHasSubtitleIcon;
    }

    public boolean hasAltSubtitleIcon() {
        return this.mHasAltSubtitleIcon;
    }

    public int getImageResourceId() {
        return this.mImageResourceId;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getImageUrl2() {
        return this.mImageUrl2;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public int getSubtitleIcon() {
        return this.mSubtitleIcon;
    }

    public String getAltSubtitle() {
        return this.mAltSubtitle;
    }

    public int getAltSubtitleIcon() {
        return this.mAltSubtitleIcon;
    }

    public int getAltTextColor() {
        return this.mAltTextColor;
    }

    public int getGlyphResourceId() {
        return this.mGlyphResourceId;
    }

    public void setImageResourceId(int i) {
        this.mImageResourceId = i;
        calculate();
    }

    public void setImageUrl(String str) {
        this.mImageUrl = str;
        calculate();
    }

    public void setImageUrl2(String str) {
        this.mImageUrl2 = str;
        calculate();
    }

    public void setTitle(String str) {
        this.mTitle = str;
        calculate();
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
        calculate();
    }

    public void setSubtitleIcon(int i) {
        this.mSubtitleIcon = i;
        calculate();
    }

    public void setAltSubtitle(String str) {
        this.mAltSubtitle = str;
        calculate();
    }

    public void setAltSubtitleIcon(int i) {
        this.mAltSubtitleIcon = i;
        calculate();
    }

    public void setAltTextColor(int i) {
        this.mAltTextColor = i;
        calculate();
    }

    public void setGlyphResourceId(int i) {
        this.mGlyphResourceId = i;
        calculate();
    }

    /* JADX INFO: finally extract failed */
    public static SocialListItemViewModel fromTypedArray(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SocialListItem, 0, 0);
        SocialListItemViewModel socialListItemViewModel = new SocialListItemViewModel();
        try {
            socialListItemViewModel.mImageUrl = obtainStyledAttributes.getString(R.styleable.SocialListItem_image_url);
            socialListItemViewModel.mImageUrl2 = obtainStyledAttributes.getString(R.styleable.SocialListItem_image_url2);
            socialListItemViewModel.mImageResourceId = obtainStyledAttributes.getResourceId(R.styleable.SocialListItem_image, -1);
            socialListItemViewModel.mTitle = obtainStyledAttributes.getString(R.styleable.SocialListItem_title);
            socialListItemViewModel.mSubtitleIcon = obtainStyledAttributes.getResourceId(R.styleable.SocialListItem_subtitle_icon, -1);
            socialListItemViewModel.mSubtitle = obtainStyledAttributes.getString(R.styleable.SocialListItem_subtitle);
            socialListItemViewModel.mAltSubtitleIcon = obtainStyledAttributes.getResourceId(R.styleable.SocialListItem_alt_subtitle_icon, -1);
            socialListItemViewModel.mAltSubtitle = obtainStyledAttributes.getString(R.styleable.SocialListItem_alt_subtitle);
            socialListItemViewModel.mAltTextColor = obtainStyledAttributes.getColor(R.styleable.SocialListItem_alt_text_color, -1);
            socialListItemViewModel.mGlyphResourceId = obtainStyledAttributes.getResourceId(R.styleable.SocialListItem_glyph, -1);
            obtainStyledAttributes.recycle();
            socialListItemViewModel.calculate();
            return socialListItemViewModel;
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
