package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialUserCardBindingImpl extends AnytimeTabletSocialUserCardBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

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
            this.mDirtyFlags = 128;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.profile_photo, 9);
        sparseIntArray.put(R.id.cta_loading_spinner, 10);
        sparseIntArray.put(R.id.secondary_button_loading_spinner, 11);
        sparseIntArray.put(R.id.secondary_button, 12);
        sparseIntArray.put(R.id.card_hover_overlay, 13);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        if (r1 != false) goto L_0x0039;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 240
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBindingImpl.executeBindings():void");
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setCtaIcon(@Nullable Drawable drawable) {
        this.mCtaIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(47);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setGroupLaunchStatusText(@Nullable String str) {
        this.mGroupLaunchStatusText = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(26);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setIsFriend(boolean z) {
        this.mIsFriend = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(98);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setIsMuted(boolean z) {
        this.mIsMuted = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(109);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setIsSpeaking(boolean z) {
        this.mIsSpeaking = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(100);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setSubtitleText(@Nullable String str) {
        this.mSubtitleText = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(31);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setUsernameText(@Nullable String str) {
        this.mUsernameText = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(81);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (109 == i) {
            setIsMuted(((Boolean) obj).booleanValue());
            return true;
        } else if (100 == i) {
            setIsSpeaking(((Boolean) obj).booleanValue());
            return true;
        } else if (81 == i) {
            setUsernameText((String) obj);
            return true;
        } else if (26 == i) {
            setGroupLaunchStatusText((String) obj);
            return true;
        } else if (47 == i) {
            setCtaIcon((Drawable) obj);
            return true;
        } else if (31 == i) {
            setSubtitleText((String) obj);
            return true;
        } else if (98 != i) {
            return false;
        } else {
            setIsFriend(((Boolean) obj).booleanValue());
            return true;
        }
    }

    public AnytimeTabletSocialUserCardBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 14, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletSocialUserCardBindingImpl(AbstractC003408r r34, View view, Object[] objArr) {
        super(r34, view, 0, (View) objArr[13], (ShellButton) objArr[8], (OCSpinner) objArr[10], (OCTextView) objArr[7], (ImageView) objArr[4], (ImageView) objArr[3], (ImageView) objArr[2], (ImageView) objArr[1], (ImageView) objArr[9], (ShellButton) objArr[12], (OCSpinner) objArr[11], (ConstraintLayout) objArr[0], (OCTextView) objArr[6], (OCTextView) objArr[5]);
        this.mDirtyFlags = -1;
        this.ctaButton.setTag(null);
        this.groupLaunchStatus.setTag(null);
        this.partyMutedIndicator.setTag(null);
        this.partyMutedIndicatorBackground.setTag(null);
        this.partySpeakingIndicator.setTag(null);
        this.partySpeakingIndicatorBackground.setTag(null);
        this.socialUserCard.setTag(null);
        this.subtitle.setTag(null);
        this.username.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
