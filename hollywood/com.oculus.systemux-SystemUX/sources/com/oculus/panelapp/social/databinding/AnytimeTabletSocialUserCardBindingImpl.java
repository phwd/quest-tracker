package com.oculus.panelapp.social.databinding;

import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.BR;
import com.oculus.panelapp.social.R;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialUserCardBindingImpl extends AnytimeTabletSocialUserCardBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.profile_photo, 9);
        sViewsWithIds.put(R.id.cta_loading_spinner, 10);
        sViewsWithIds.put(R.id.secondary_button_loading_spinner, 11);
        sViewsWithIds.put(R.id.secondary_button, 12);
        sViewsWithIds.put(R.id.card_hover_overlay, 13);
    }

    public AnytimeTabletSocialUserCardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSocialUserCardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (View) objArr[13], (ShellButton) objArr[8], (OCSpinner) objArr[10], (OCTextView) objArr[7], (ImageView) objArr[4], (ImageView) objArr[3], (ImageView) objArr[2], (ImageView) objArr[1], (ImageView) objArr[9], (ShellButton) objArr[12], (OCSpinner) objArr[11], (ConstraintLayout) objArr[0], (OCTextView) objArr[6], (OCTextView) objArr[5]);
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

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
        if (BR.isMuted == i) {
            setIsMuted(((Boolean) obj).booleanValue());
        } else if (BR.isSpeaking == i) {
            setIsSpeaking(((Boolean) obj).booleanValue());
        } else if (BR.usernameText == i) {
            setUsernameText((String) obj);
        } else if (BR.groupLaunchStatusText == i) {
            setGroupLaunchStatusText((String) obj);
        } else if (BR.ctaIcon == i) {
            setCtaIcon((Drawable) obj);
        } else if (BR.subtitleText == i) {
            setSubtitleText((String) obj);
        } else if (BR.isFriend != i) {
            return false;
        } else {
            setIsFriend(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setIsMuted(boolean z) {
        this.mIsMuted = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.isMuted);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setIsSpeaking(boolean z) {
        this.mIsSpeaking = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.isSpeaking);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setUsernameText(@Nullable String str) {
        this.mUsernameText = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.usernameText);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setGroupLaunchStatusText(@Nullable String str) {
        this.mGroupLaunchStatusText = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.groupLaunchStatusText);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setCtaIcon(@Nullable Drawable drawable) {
        this.mCtaIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.ctaIcon);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setSubtitleText(@Nullable String str) {
        this.mSubtitleText = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.subtitleText);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding
    public void setIsFriend(boolean z) {
        this.mIsFriend = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.isFriend);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean z2 = this.mIsMuted;
        boolean z3 = this.mIsSpeaking;
        String str = this.mUsernameText;
        String str2 = this.mGroupLaunchStatusText;
        Drawable drawable = this.mCtaIcon;
        String str3 = this.mSubtitleText;
        boolean z4 = this.mIsFriend;
        int i2 = 0;
        if ((j & 131) != 0) {
            if ((j & 129) != 0) {
                j |= z2 ? PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH : 1024;
            }
            i = ((j & 129) == 0 || z2) ? 0 : 8;
            z = !z2;
            if ((j & 131) != 0) {
                j |= z ? 512 : 256;
            }
        } else {
            z = false;
            i = 0;
        }
        int i3 = ((j & 192) > 0 ? 1 : ((j & 192) == 0 ? 0 : -1));
        Drawable drawable2 = null;
        if (i3 != 0) {
            if (i3 != 0) {
                j |= z4 ? 8192 : 4096;
            }
            if (z4) {
                drawable2 = getDrawableFromResource(this.username, R.drawable.anytime_social_user_card_friend);
            }
        }
        int i4 = ((j & 131) > 0 ? 1 : ((j & 131) == 0 ? 0 : -1));
        if (i4 != 0) {
            if (!z) {
                z3 = false;
            }
            if (i4 != 0) {
                j |= z3 ? 32768 : 16384;
            }
            if (!z3) {
                i2 = 8;
            }
        }
        if ((j & 144) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.ctaButton, drawable);
        }
        if ((j & 136) != 0) {
            TextViewBindingAdapter.setText(this.groupLaunchStatus, str2);
        }
        if ((j & 129) != 0) {
            this.partyMutedIndicator.setVisibility(i);
            this.partyMutedIndicatorBackground.setVisibility(i);
        }
        if ((j & 131) != 0) {
            this.partySpeakingIndicator.setVisibility(i2);
            this.partySpeakingIndicatorBackground.setVisibility(i2);
        }
        if ((160 & j) != 0) {
            TextViewBindingAdapter.setText(this.subtitle, str3);
        }
        if ((132 & j) != 0) {
            TextViewBindingAdapter.setText(this.username, str);
        }
        if ((j & 192) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.username, drawable2);
        }
    }
}
