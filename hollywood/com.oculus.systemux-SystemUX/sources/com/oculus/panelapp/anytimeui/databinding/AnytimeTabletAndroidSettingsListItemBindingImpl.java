package com.oculus.panelapp.anytimeui.databinding;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsListItem;

public class AnytimeTabletAndroidSettingsListItemBindingImpl extends AnytimeTabletAndroidSettingsListItemBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final SettingsListItem mboundView0;

    static {
        sViewsWithIds.put(R.id.right_view_group, 6);
    }

    public AnytimeTabletAndroidSettingsListItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletAndroidSettingsListItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[5], (ImageView) objArr[2], (SimpleDraweeView) objArr[1], (LinearLayout) objArr[6], (OCLink) objArr[4], (OCTextView) objArr[3]);
        this.mDirtyFlags = -1;
        this.bottomViewGroup.setTag(null);
        this.icon.setTag(null);
        this.image.setTag(null);
        this.mboundView0 = (SettingsListItem) objArr[0];
        this.mboundView0.setTag(null);
        this.subtitle.setTag(null);
        this.title.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.item != i) {
            return false;
        }
        setItem((SettingsItem) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsListItemBinding
    public void setItem(@Nullable SettingsItem settingsItem) {
        updateRegistration(0, settingsItem);
        this.mItem = settingsItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeItem((SettingsItem) obj, i2);
    }

    private boolean onChangeItem(SettingsItem settingsItem, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.subtitle) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i != BR.subtitleUri) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        boolean z;
        String str;
        Drawable drawable;
        String str2;
        String str3;
        int i;
        int i2;
        boolean z2;
        int i3;
        float f;
        int i4;
        Resources resources;
        int i5;
        int i6;
        String str4;
        Drawable drawable2;
        int i7;
        Drawable drawable3;
        Uri uri;
        boolean z3;
        boolean z4;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SettingsItem settingsItem = this.mItem;
        Drawable drawable4 = null;
        r15 = null;
        String str5 = null;
        boolean z5 = false;
        if ((15 & j) != 0) {
            int i8 = ((j & 9) > 0 ? 1 : ((j & 9) == 0 ? 0 : -1));
            if (i8 != 0) {
                if (settingsItem != null) {
                    i7 = settingsItem.getIconVisibility();
                    drawable2 = settingsItem.getIcon();
                    z4 = settingsItem.getIsImageCircular();
                    str4 = settingsItem.getTitle();
                    z3 = settingsItem.isSubtitleVisible();
                    uri = settingsItem.getImageUri();
                    z = settingsItem.isTitleVisible();
                } else {
                    drawable2 = null;
                    str4 = null;
                    uri = null;
                    i7 = 0;
                    z4 = false;
                    z3 = false;
                    z = false;
                }
                if (i8 != 0) {
                    j |= z4 ? 8192 : 4096;
                }
                if ((j & 9) != 0) {
                    j |= z3 ? 32768 : 16384;
                }
                if ((j & 9) != 0) {
                    if (z) {
                        j3 = j | 128;
                        j2 = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    } else {
                        j3 = j | 64;
                        j2 = 1024;
                    }
                    j = j3 | j2;
                }
                Drawable drawableFromResource = z4 ? getDrawableFromResource(this.image, R.drawable.settings_list_item_circular_image_background) : getDrawableFromResource(this.image, R.drawable.settings_list_item_image_background);
                i6 = z3 ? 0 : 8;
                boolean z6 = uri == null;
                i5 = z ? 0 : 8;
                if ((j & 9) != 0) {
                    j |= z6 ? 32 : 16;
                }
                if (z6) {
                    z5 = true;
                }
                drawable3 = drawableFromResource;
                z2 = z5;
                z5 = z3;
            } else {
                drawable3 = null;
                drawable2 = null;
                str4 = null;
                z2 = false;
                i7 = 0;
                z = false;
                i6 = 0;
                i5 = 0;
            }
            String subtitle = ((j & 11) == 0 || settingsItem == null) ? null : settingsItem.getSubtitle();
            if (!((j & 13) == 0 || settingsItem == null)) {
                str5 = settingsItem.getSubtitleUri();
            }
            drawable = drawable3;
            str = str5;
            i = i7;
            drawable4 = drawable2;
            str3 = str4;
            str2 = subtitle;
            i2 = i6;
            i3 = i5;
        } else {
            str3 = null;
            str2 = null;
            drawable = null;
            str = null;
            i3 = 0;
            z2 = false;
            i2 = 0;
            i = 0;
            z = false;
        }
        int i9 = ((j & 9) > 0 ? 1 : ((j & 9) == 0 ? 0 : -1));
        if (i9 != 0) {
            if (z) {
                z5 = true;
            }
            if (i9 != 0) {
                j |= z5 ? 512 : 256;
            }
            if (z5) {
                resources = this.bottomViewGroup.getResources();
                i4 = R.dimen.settings_list_item_padding;
            } else {
                resources = this.bottomViewGroup.getResources();
                i4 = R.dimen.settings_list_item_padding_zero;
            }
            f = resources.getDimension(i4);
        } else {
            f = 0.0f;
        }
        if ((9 & j) != 0) {
            ViewBindingAdapter.setPaddingTop(this.bottomViewGroup, f);
            ImageViewBindingAdapter.setImageDrawable(this.icon, drawable4);
            this.icon.setVisibility(i);
            ViewBindingAdapter.setBackground(this.image, drawable);
            SimpleDraweeView simpleDraweeView = this.image;
            int i10 = z2 ? 1 : 0;
            int i11 = z2 ? 1 : 0;
            int i12 = z2 ? 1 : 0;
            int i13 = z2 ? 1 : 0;
            int i14 = z2 ? 1 : 0;
            simpleDraweeView.setVisibility(i10);
            this.subtitle.setVisibility(i2);
            TextViewBindingAdapter.setText(this.title, str3);
            this.title.setVisibility(i3);
        }
        if ((11 & j) != 0) {
            TextViewBindingAdapter.setText(this.subtitle, str2);
        }
        if ((j & 13) != 0) {
            OCLink.setUri(this.subtitle, str);
        }
    }
}
