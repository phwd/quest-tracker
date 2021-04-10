package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCSelect;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.tablet.databinding.OsigButtonBorderlessBinding;
import com.oculus.tablet.databinding.OsigSeekbarBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialPartyHeaderBindingImpl extends AnytimeTabletSocialPartyHeaderBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeNavigateBackButton(OsigButtonBorderlessBinding osigButtonBorderlessBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangePartyVolumeSlider(OsigSeekbarBinding osigSeekbarBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialParty socialParty = this.mParty;
        Drawable drawable = null;
        String str = this.mPartySpotsAvailable;
        boolean z = this.mMutePartyVolume;
        long j4 = j & 36;
        int i = 0;
        if (j4 != 0) {
            boolean z2 = false;
            if (socialParty != null) {
                z2 = true;
            }
            if (j4 != 0) {
                if (z2) {
                    j3 = 512;
                } else {
                    j3 = 256;
                }
                j |= j3;
            }
            if (!z2) {
                i = 8;
            }
        }
        long j5 = j & 48;
        if (j5 != 0) {
            if (j5 != 0) {
                if (z) {
                    j2 = 128;
                } else {
                    j2 = 64;
                }
                j |= j2;
            }
            View view = this.mRoot;
            int i2 = R.drawable.oc_icon_volume_on_filled_24_d2d2d2;
            if (z) {
                i2 = R.drawable.oc_icon_volume_off_filled_24_d2d2d2;
            }
            drawable = AnonymousClass1uW.getDrawableFromResource(view, i2);
        }
        if ((32 & j) != 0) {
            this.navigateBackButton.setIcon(AnonymousClass1uW.getDrawableFromResource(this.mRoot, R.drawable.oc_icon_arrow_left_filled_24_d2d2d2));
            this.partyVolumeSlider.setProgressDrawable(AnonymousClass1uW.getDrawableFromResource(this.mRoot, R.drawable.social_seekbar_progress_v2));
        }
        if ((36 & j) != 0) {
            this.partyHeaderTitle.setVisibility(i);
            this.partyVolumeSlider.mRoot.setVisibility(i);
            this.socialEndCallButton.setVisibility(i);
            this.spotsAvailable.setVisibility(i);
        }
        if ((j & 48) != 0) {
            this.partyVolumeSlider.setIcon(drawable);
        }
        if ((j & 40) != 0) {
            C11051qV.A02(this.spotsAvailable, str);
        }
        AnonymousClass1uW.executeBindingsOn(this.navigateBackButton);
        AnonymousClass1uW.executeBindingsOn(this.partyVolumeSlider);
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        int i;
        synchronized (this) {
            i = (this.mDirtyFlags > 0 ? 1 : (this.mDirtyFlags == 0 ? 0 : -1));
        }
        if (i != 0 || this.navigateBackButton.hasPendingBindings() || this.partyVolumeSlider.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
        }
        this.navigateBackButton.invalidateAll();
        this.partyVolumeSlider.invalidateAll();
        requestRebind();
    }

    static {
        AnonymousClass1ui r4 = new AnonymousClass1ui(8);
        sIncludes = r4;
        r4.A00(new String[]{"osig_button_borderless", "osig_seekbar"}, new int[]{4, 5}, new int[]{R.layout.osig_button_borderless, R.layout.osig_seekbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.guideline, 6);
        sparseIntArray.put(R.id.party_privacy, 7);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeNavigateBackButton((OsigButtonBorderlessBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangePartyVolumeSlider((OsigSeekbarBinding) obj, i2);
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBinding
    public void setMutePartyVolume(boolean z) {
        this.mMutePartyVolume = z;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(21);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBinding
    public void setParty(@Nullable SocialParty socialParty) {
        this.mParty = socialParty;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(29);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBinding
    public void setPartySpotsAvailable(@Nullable String str) {
        this.mPartySpotsAvailable = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(32);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (29 == i) {
            setParty((SocialParty) obj);
            return true;
        } else if (32 == i) {
            setPartySpotsAvailable((String) obj);
            return true;
        } else if (21 != i) {
            return false;
        } else {
            setMutePartyVolume(((Boolean) obj).booleanValue());
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.navigateBackButton.setLifecycleOwner(r2);
        this.partyVolumeSlider.setLifecycleOwner(r2);
    }

    public AnytimeTabletSocialPartyHeaderBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 8, sIncludes, sViewsWithIds));
    }

    public AnytimeTabletSocialPartyHeaderBindingImpl(AbstractC003408r r15, View view, Object[] objArr) {
        super(r15, view, 2, (Guideline) objArr[6], (OsigButtonBorderlessBinding) objArr[4], (ConstraintLayout) objArr[0], (OCTextView) objArr[2], (OCSelect) objArr[7], (OsigSeekbarBinding) objArr[5], (ShellButton) objArr[1], (OCTextView) objArr[3]);
        this.mDirtyFlags = -1;
        this.partyHeader.setTag(null);
        this.partyHeaderTitle.setTag(null);
        this.socialEndCallButton.setTag(null);
        this.spotsAvailable.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
