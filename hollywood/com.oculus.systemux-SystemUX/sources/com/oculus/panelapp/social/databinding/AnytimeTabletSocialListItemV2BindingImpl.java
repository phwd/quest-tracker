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
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.BR;
import com.oculus.tablet.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;
import com.oculus.tablet.databinding.OsigSeekbarBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialListItemV2BindingImpl extends AnytimeTabletSocialListItemV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(13);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"osig_seekbar", "common_tablet_rectangular_button"}, new int[]{5, 6}, new int[]{R.layout.osig_seekbar, R.layout.common_tablet_rectangular_button});
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.loading_spinner, 7);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.menu_button, 8);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.call_button, 9);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.start_party_loading_spinner, 10);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.chat_button, 11);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.secondary_loading_spinner, 12);
    }

    public AnytimeTabletSocialListItemV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSocialListItemV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (CommonTabletRectangularButtonBinding) objArr[6], (ShellButton) objArr[9], (ShellButton) objArr[11], (ImageView) objArr[1], (OCSpinner) objArr[7], (ShellButton) objArr[8], (OCTextView) objArr[2], (ShellButton) objArr[4], (OsigSeekbarBinding) objArr[5], (OCSpinner) objArr[12], (ConstraintLayout) objArr[0], (OCSpinner) objArr[10], (OCTextView) objArr[3]);
        this.mDirtyFlags = -1;
        this.icon.setTag(null);
        this.name.setTag(null);
        this.partyMute.setTag(null);
        this.socialListItem.setTag(null);
        this.subtitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
        }
        this.partyVolumeSlider.invalidateAll();
        this.actionButton.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.actionButton.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.partyVolumeSlider.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0021 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            com.oculus.tablet.databinding.OsigSeekbarBinding r0 = r4.partyVolumeSlider
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding r0 = r4.actionButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            r0 = 0
            return r0
        L_0x0021:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2BindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.nameText == i) {
            setNameText((String) obj);
        } else if (BR.actionButtonText == i) {
            setActionButtonText((String) obj);
        } else if (BR.subtitleText == i) {
            setSubtitleText((String) obj);
        } else if (BR.isInvitedUser == i) {
            setIsInvitedUser(((Boolean) obj).booleanValue());
        } else if (BR.muted != i) {
            return false;
        } else {
            setMuted(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding
    public void setNameText(@Nullable String str) {
        this.mNameText = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.nameText);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding
    public void setActionButtonText(@Nullable String str) {
        this.mActionButtonText = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.actionButtonText);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding
    public void setSubtitleText(@Nullable String str) {
        this.mSubtitleText = str;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.subtitleText);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding
    public void setIsInvitedUser(boolean z) {
        this.mIsInvitedUser = z;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.isInvitedUser);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding
    public void setMuted(boolean z) {
        this.mMuted = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.muted);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.partyVolumeSlider.setLifecycleOwner(lifecycleOwner);
        this.actionButton.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeActionButton((CommonTabletRectangularButtonBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangePartyVolumeSlider((OsigSeekbarBinding) obj, i2);
    }

    private boolean onChangeActionButton(CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangePartyVolumeSlider(OsigSeekbarBinding osigSeekbarBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        ShellButton shellButton;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mNameText;
        float f = 0.0f;
        String str2 = this.mActionButtonText;
        String str3 = this.mSubtitleText;
        boolean z = this.mIsInvitedUser;
        boolean z2 = this.mMuted;
        Drawable drawable = null;
        int i2 = ((j & 160) > 0 ? 1 : ((j & 160) == 0 ? 0 : -1));
        if (i2 != 0) {
            if (i2 != 0) {
                j |= z ? 512 : 256;
            }
            f = z ? 0.4f : 1.0f;
        }
        int i3 = ((j & 192) > 0 ? 1 : ((j & 192) == 0 ? 0 : -1));
        if (i3 != 0) {
            if (i3 != 0) {
                j |= z2 ? PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH : 1024;
            }
            if (z2) {
                shellButton = this.partyMute;
                i = com.oculus.panelapp.social.R.drawable.oc_icon_microphone_off_filled_24_d2d2d2;
            } else {
                shellButton = this.partyMute;
                i = com.oculus.panelapp.social.R.drawable.oc_icon_microphone_on_filled_24_d2d2d2;
            }
            drawable = getDrawableFromResource(shellButton, i);
        }
        if ((136 & j) != 0) {
            this.actionButton.setText(str2);
        }
        if ((j & 160) != 0 && getBuildSdkInt() >= 11) {
            this.icon.setAlpha(f);
            this.name.setAlpha(f);
            this.subtitle.setAlpha(f);
        }
        if ((132 & j) != 0) {
            TextViewBindingAdapter.setText(this.name, str);
        }
        if ((j & 192) != 0) {
            ViewBindingAdapter.setBackground(this.partyMute, drawable);
        }
        if ((128 & j) != 0) {
            this.partyVolumeSlider.setIcon(getDrawableFromResource(getRoot(), com.oculus.panelapp.social.R.drawable.oc_icon_volume_low_filled_24_d2d2d2));
        }
        if ((j & 144) != 0) {
            TextViewBindingAdapter.setText(this.subtitle, str3);
        }
        executeBindingsOn(this.partyVolumeSlider);
        executeBindingsOn(this.actionButton);
    }
}
