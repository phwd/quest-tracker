package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialListNullRowV2BindingImpl extends AnytimeTabletSocialListNullRowV2Binding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        Drawable drawable;
        String str;
        String str2;
        ImageView imageView;
        int i;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean z = this.mIsFBLinked;
        long j4 = j & 3;
        String str3 = null;
        if (j4 != 0) {
            if (j4 != 0) {
                if (z) {
                    j2 = j | 8 | 32 | 128;
                    j3 = 512;
                } else {
                    j2 = j | 4 | 16 | 64;
                    j3 = 256;
                }
                j = j2 | j3;
            }
            Resources resources = this.nullRowTitle.getResources();
            int i2 = R.string.anytime_tablet_social_bundle_upsell_title;
            if (z) {
                i2 = R.string.anytime_tablet_social_friend_upsell_title;
            }
            str3 = resources.getString(i2);
            Resources resources2 = this.nullRowCta.getResources();
            int i3 = R.string.anytime_tablet_social_bundle_upsell_button;
            if (z) {
                i3 = R.string.anytime_tablet_social_friend_upsell_button;
            }
            str = resources2.getString(i3);
            Resources resources3 = this.nullRowBody.getResources();
            int i4 = R.string.anytime_tablet_social_bundle_upsell_body;
            if (z) {
                i4 = R.string.anytime_tablet_social_friend_upsell_body;
            }
            str2 = resources3.getString(i4);
            if (z) {
                imageView = this.nullRowImage;
                i = R.drawable.friends_upsell;
            } else {
                imageView = this.nullRowImage;
                i = R.drawable.facebook_upsell;
            }
            drawable = AnonymousClass1uW.getDrawableFromResource(imageView, i);
        } else {
            drawable = null;
            str = null;
            str2 = null;
        }
        if ((j & 3) != 0) {
            C11051qV.A02(this.nullRowBody, str2);
            C11051qV.A02(this.nullRowCta, str);
            this.nullRowImage.setImageDrawable(drawable);
            C11051qV.A02(this.nullRowTitle, str3);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListNullRowV2Binding
    public void setIsFBLinked(boolean z) {
        this.mIsFBLinked = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(104);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (104 != i) {
            return false;
        }
        setIsFBLinked(((Boolean) obj).booleanValue());
        return true;
    }

    public AnytimeTabletSocialListNullRowV2BindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 5, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public AnytimeTabletSocialListNullRowV2BindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 0, (ConstraintLayout) objArr[0], (OCTextView) objArr[3], (ShellButton) objArr[4], (ImageView) objArr[1], (OCTextView) objArr[2]);
        this.mDirtyFlags = -1;
        this.nullRow.setTag(null);
        this.nullRowBody.setTag(null);
        this.nullRowCta.setTag(null);
        this.nullRowImage.setTag(null);
        this.nullRowTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
