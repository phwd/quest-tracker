package com.oculus.panelapp.social.databinding;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.BR;
import com.oculus.panelapp.social.R;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialListNullRowV2BindingImpl extends AnytimeTabletSocialListNullRowV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public AnytimeTabletSocialListNullRowV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSocialListNullRowV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (OCTextView) objArr[3], (ShellButton) objArr[4], (ImageView) objArr[1], (OCTextView) objArr[2]);
        this.mDirtyFlags = -1;
        this.nullRow.setTag(null);
        this.nullRowBody.setTag(null);
        this.nullRowCta.setTag(null);
        this.nullRowImage.setTag(null);
        this.nullRowTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (BR.isFBLinked != i) {
            return false;
        }
        setIsFBLinked(((Boolean) obj).booleanValue());
        return true;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListNullRowV2Binding
    public void setIsFBLinked(boolean z) {
        this.mIsFBLinked = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.isFBLinked);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        String str;
        String str2;
        Drawable drawable;
        int i;
        Resources resources;
        int i2;
        Resources resources2;
        int i3;
        Resources resources3;
        int i4;
        ImageView imageView;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean z = this.mIsFBLinked;
        int i5 = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        String str3 = null;
        if (i5 != 0) {
            if (i5 != 0) {
                if (z) {
                    j3 = j | 8 | 32 | 128;
                    j2 = 512;
                } else {
                    j3 = j | 4 | 16 | 64;
                    j2 = 256;
                }
                j = j3 | j2;
            }
            if (z) {
                resources = this.nullRowTitle.getResources();
                i = R.string.anytime_tablet_social_friend_upsell_title;
            } else {
                resources = this.nullRowTitle.getResources();
                i = R.string.anytime_tablet_social_bundle_upsell_title;
            }
            String string = resources.getString(i);
            if (z) {
                resources2 = this.nullRowCta.getResources();
                i2 = R.string.anytime_tablet_social_friend_upsell_button;
            } else {
                resources2 = this.nullRowCta.getResources();
                i2 = R.string.anytime_tablet_social_bundle_upsell_button;
            }
            str2 = resources2.getString(i2);
            if (z) {
                resources3 = this.nullRowBody.getResources();
                i3 = R.string.anytime_tablet_social_friend_upsell_body;
            } else {
                resources3 = this.nullRowBody.getResources();
                i3 = R.string.anytime_tablet_social_bundle_upsell_body;
            }
            String string2 = resources3.getString(i3);
            if (z) {
                imageView = this.nullRowImage;
                i4 = R.drawable.friends_upsell;
            } else {
                imageView = this.nullRowImage;
                i4 = R.drawable.facebook_upsell;
            }
            drawable = getDrawableFromResource(imageView, i4);
            str = string;
            str3 = string2;
        } else {
            drawable = null;
            str2 = null;
            str = null;
        }
        if ((j & 3) != 0) {
            TextViewBindingAdapter.setText(this.nullRowBody, str3);
            TextViewBindingAdapter.setText(this.nullRowCta, str2);
            ImageViewBindingAdapter.setImageDrawable(this.nullRowImage, drawable);
            TextViewBindingAdapter.setText(this.nullRowTitle, str);
        }
    }
}
