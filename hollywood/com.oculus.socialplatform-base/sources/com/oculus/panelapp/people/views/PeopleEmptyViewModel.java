package com.oculus.panelapp.people.views;

import X.AnonymousClass1uc;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.socialplatform.R;

public class PeopleEmptyViewModel extends AnonymousClass1uc {
    public static final String TAG = LoggingUtil.tag(PeopleEmptyViewModel.class);
    public Context mContext;
    public String mCtaText;
    public Drawable mImage;
    public String mSubtitle;
    public String mTitle;
    public PeopleEmptyAdapterItemType mType;

    /* renamed from: com.oculus.panelapp.people.views.PeopleEmptyViewModel$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$people$views$PeopleEmptyAdapterItemType;

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|24) */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
            // Method dump skipped, instructions count: 113
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.PeopleEmptyViewModel.AnonymousClass1.<clinit>():void");
        }
    }

    @Bindable
    public String getCtaText() {
        return this.mCtaText;
    }

    @Bindable
    public Drawable getImage() {
        return this.mImage;
    }

    public PeopleEmptyViewLayoutType getLayoutType() {
        switch (this.mType.ordinal()) {
            case 3:
            case 6:
            case 13:
                return PeopleEmptyViewLayoutType.IMAGE;
            default:
                return PeopleEmptyViewLayoutType.ICON;
        }
    }

    @Bindable
    public String getSubtitle() {
        return this.mSubtitle;
    }

    @Bindable
    public String getTitle() {
        return this.mTitle;
    }

    public void setCtaText(String str) {
        this.mCtaText = str;
        notifyPropertyChanged(16);
    }

    public void setImage(Drawable drawable) {
        this.mImage = drawable;
        notifyPropertyChanged(75);
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
        notifyPropertyChanged(80);
    }

    public void setTitle(String str) {
        this.mTitle = str;
        notifyPropertyChanged(67);
    }

    public void setType(PeopleEmptyAdapterItemType peopleEmptyAdapterItemType) {
        this.mType = peopleEmptyAdapterItemType;
        notifyPropertyChanged(72);
        notifyPropertyChanged(66);
        notifyPropertyChanged(76);
    }

    public PeopleEmptyViewModel(Context context) {
        this.mContext = context;
    }

    @Bindable
    public Drawable getCtaBackground() {
        Resources resources;
        int i;
        if (getLayoutType() == PeopleEmptyViewLayoutType.IMAGE) {
            resources = this.mContext.getResources();
            i = R.drawable.ocbutton_primary;
        } else {
            resources = this.mContext.getResources();
            i = R.drawable.ocbutton_secondary;
        }
        return resources.getDrawable(i);
    }

    @Bindable
    public int getTextGravity() {
        if (getLayoutType() == PeopleEmptyViewLayoutType.IMAGE) {
            return 8388611;
        }
        return 17;
    }

    @Bindable
    public int getTitleTextSize() {
        Resources resources;
        int i;
        if (getLayoutType() == PeopleEmptyViewLayoutType.IMAGE) {
            resources = this.mContext.getResources();
            i = R.dimen.octypography_body1_line_height;
        } else {
            resources = this.mContext.getResources();
            i = R.dimen.abc_text_size_menu_header_material;
        }
        return resources.getDimensionPixelSize(i);
    }
}
