package com.oculus.panelapp.socialandroidbackpanel.views.list_item;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.oculus.panelapp.socialandroidbackpanel.R;

public class ListItemViewModel {
    public String mAltSubtitle;
    public int mAltSubtitleIcon;
    public int mAltTextColor;
    public int mGlyphResourceId;
    public boolean mHasAltSubtitleIcon;
    public boolean mHasGlyph;
    public boolean mHasIcon;
    public boolean mHasMultiImage;
    public boolean mHasSingleImage;
    public boolean mHasSubtitleIcon;
    public int mImageResourceId;
    public String mImageUrl;
    public String mImageUrl2;
    public String mSubtitle;
    public int mSubtitleIcon;
    public String mTitle;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0058, code lost:
        if (r3.test(r4.mSubtitle) == false) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x003e, code lost:
        if (com.oculus.tablet.utils.ViewBindingUtil.NULL_OR_EMPTY.test(r4.mImageUrl2) == false) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void calculate() {
        /*
        // Method dump skipped, instructions count: 121
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.views.list_item.ListItemViewModel.calculate():void");
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

    public int getImageResourceId() {
        return this.mImageResourceId;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getImageUrl2() {
        return this.mImageUrl2;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public int getSubtitleIcon() {
        return this.mSubtitleIcon;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean hasAltSubtitleIcon() {
        return this.mHasAltSubtitleIcon;
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

    public void setAltSubtitle(String str) {
        this.mAltSubtitle = str;
        calculate();
    }

    public void setAltSubtitleIcon(int i) {
        this.mAltSubtitleIcon = i;
        calculate();
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

    public void setSubtitle(String str) {
        this.mSubtitle = str;
        calculate();
    }

    public void setSubtitleIcon(int i) {
        this.mSubtitleIcon = i;
        calculate();
    }

    public void setTitle(String str) {
        this.mTitle = str;
        calculate();
    }

    /* JADX INFO: finally extract failed */
    public static ListItemViewModel fromTypedArray(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ListItem, 0, 0);
        ListItemViewModel listItemViewModel = new ListItemViewModel();
        try {
            listItemViewModel.mImageUrl = obtainStyledAttributes.getString(5);
            listItemViewModel.mImageUrl2 = obtainStyledAttributes.getString(6);
            listItemViewModel.mImageResourceId = obtainStyledAttributes.getResourceId(4, -1);
            listItemViewModel.mTitle = obtainStyledAttributes.getString(9);
            listItemViewModel.mSubtitleIcon = obtainStyledAttributes.getResourceId(8, -1);
            listItemViewModel.mSubtitle = obtainStyledAttributes.getString(7);
            listItemViewModel.mAltSubtitleIcon = obtainStyledAttributes.getResourceId(1, -1);
            listItemViewModel.mAltSubtitle = obtainStyledAttributes.getString(0);
            listItemViewModel.mAltTextColor = obtainStyledAttributes.getColor(2, -1);
            listItemViewModel.mGlyphResourceId = obtainStyledAttributes.getResourceId(3, -1);
            obtainStyledAttributes.recycle();
            listItemViewModel.calculate();
            return listItemViewModel;
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
