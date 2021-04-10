package com.oculus.panelapp.social.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCSelect;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.BR;
import com.oculus.tablet.R;
import com.oculus.tablet.databinding.OsigButtonBorderlessBinding;
import com.oculus.tablet.databinding.OsigSeekbarBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialPartyHeaderBindingImpl extends AnytimeTabletSocialPartyHeaderBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(8);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"osig_button_borderless", "osig_seekbar"}, new int[]{4, 5}, new int[]{R.layout.osig_button_borderless, R.layout.osig_seekbar});
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.guideline, 6);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.party_privacy, 7);
    }

    public AnytimeTabletSocialPartyHeaderBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSocialPartyHeaderBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (Guideline) objArr[6], (OsigButtonBorderlessBinding) objArr[4], (ConstraintLayout) objArr[0], (OCTextView) objArr[2], (OCSelect) objArr[7], (OsigSeekbarBinding) objArr[5], (ShellButton) objArr[1], (OCTextView) objArr[3]);
        this.mDirtyFlags = -1;
        this.partyHeader.setTag(null);
        this.partyHeaderTitle.setTag(null);
        this.socialEndCallButton.setTag(null);
        this.spotsAvailable.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
        }
        this.navigateBackButton.invalidateAll();
        this.partyVolumeSlider.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.partyVolumeSlider.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.navigateBackButton.hasPendingBindings() == false) goto L_0x0016;
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
            com.oculus.tablet.databinding.OsigButtonBorderlessBinding r0 = r4.navigateBackButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.tablet.databinding.OsigSeekbarBinding r0 = r4.partyVolumeSlider
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
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.party == i) {
            setParty((SocialParty) obj);
        } else if (BR.partySpotsAvailable == i) {
            setPartySpotsAvailable((String) obj);
        } else if (BR.mutePartyVolume != i) {
            return false;
        } else {
            setMutePartyVolume(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBinding
    public void setParty(@Nullable SocialParty socialParty) {
        this.mParty = socialParty;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.party);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBinding
    public void setPartySpotsAvailable(@Nullable String str) {
        this.mPartySpotsAvailable = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.partySpotsAvailable);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBinding
    public void setMutePartyVolume(boolean z) {
        this.mMutePartyVolume = z;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.mutePartyVolume);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.navigateBackButton.setLifecycleOwner(lifecycleOwner);
        this.partyVolumeSlider.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeNavigateBackButton((OsigButtonBorderlessBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangePartyVolumeSlider((OsigSeekbarBinding) obj, i2);
    }

    private boolean onChangeNavigateBackButton(OsigButtonBorderlessBinding osigButtonBorderlessBinding, int i) {
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
        View view;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialParty socialParty = this.mParty;
        Drawable drawable = null;
        String str = this.mPartySpotsAvailable;
        boolean z = this.mMutePartyVolume;
        int i2 = ((j & 36) > 0 ? 1 : ((j & 36) == 0 ? 0 : -1));
        int i3 = 0;
        if (i2 != 0) {
            boolean z2 = socialParty != null;
            if (i2 != 0) {
                j |= z2 ? 512 : 256;
            }
            if (!z2) {
                i3 = 8;
            }
        }
        int i4 = ((j & 48) > 0 ? 1 : ((j & 48) == 0 ? 0 : -1));
        if (i4 != 0) {
            if (i4 != 0) {
                j |= z ? 128 : 64;
            }
            if (z) {
                view = getRoot();
                i = com.oculus.panelapp.social.R.drawable.oc_icon_volume_off_filled_24_d2d2d2;
            } else {
                view = getRoot();
                i = com.oculus.panelapp.social.R.drawable.oc_icon_volume_on_filled_24_d2d2d2;
            }
            drawable = getDrawableFromResource(view, i);
        }
        if ((32 & j) != 0) {
            this.navigateBackButton.setIcon(getDrawableFromResource(getRoot(), com.oculus.panelapp.social.R.drawable.oc_icon_arrow_left_filled_24_d2d2d2));
            this.partyVolumeSlider.setProgressDrawable(getDrawableFromResource(getRoot(), com.oculus.panelapp.social.R.drawable.social_seekbar_progress_v2));
        }
        if ((j & 36) != 0) {
            this.partyHeaderTitle.setVisibility(i3);
            this.partyVolumeSlider.getRoot().setVisibility(i3);
            this.socialEndCallButton.setVisibility(i3);
            this.spotsAvailable.setVisibility(i3);
        }
        if ((j & 48) != 0) {
            this.partyVolumeSlider.setIcon(drawable);
        }
        if ((j & 40) != 0) {
            TextViewBindingAdapter.setText(this.spotsAvailable, str);
        }
        executeBindingsOn(this.navigateBackButton);
        executeBindingsOn(this.partyVolumeSlider);
    }
}
