package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;
import com.oculus.tablet.databinding.OsigSeekbarBinding;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialListItemV2BindingImpl extends AnytimeTabletSocialListItemV2Binding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeActionButton(CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, int i) {
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
    public boolean hasPendingBindings() {
        int i;
        synchronized (this) {
            i = (this.mDirtyFlags > 0 ? 1 : (this.mDirtyFlags == 0 ? 0 : -1));
        }
        if (i != 0 || this.partyVolumeSlider.hasPendingBindings() || this.actionButton.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
        }
        this.partyVolumeSlider.invalidateAll();
        this.actionButton.invalidateAll();
        requestRebind();
    }

    static {
        AnonymousClass1ui r4 = new AnonymousClass1ui(13);
        sIncludes = r4;
        r4.A00(new String[]{"osig_seekbar", "common_tablet_rectangular_button"}, new int[]{5, 6}, new int[]{R.layout.osig_seekbar, R.layout.common_tablet_rectangular_button});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.loading_spinner, 7);
        sparseIntArray.put(R.id.menu_button, 8);
        sparseIntArray.put(R.id.call_button, 9);
        sparseIntArray.put(R.id.start_party_loading_spinner, 10);
        sparseIntArray.put(R.id.chat_button, 11);
        sparseIntArray.put(R.id.secondary_loading_spinner, 12);
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
        String str = this.mNameText;
        float f = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        String str2 = this.mActionButtonText;
        String str3 = this.mSubtitleText;
        boolean z = this.mIsInvitedUser;
        boolean z2 = this.mMuted;
        Drawable drawable = null;
        long j4 = j & 160;
        if (j4 != 0) {
            if (j4 != 0) {
                if (z) {
                    j3 = 512;
                } else {
                    j3 = 256;
                }
                j |= j3;
            }
            f = 1.0f;
            if (z) {
                f = 0.4f;
            }
        }
        long j5 = j & 192;
        if (j5 != 0) {
            if (j5 != 0) {
                if (z2) {
                    j2 = BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM;
                } else {
                    j2 = 1024;
                }
                j |= j2;
            }
            ShellButton shellButton = this.partyMute;
            int i = R.drawable.oc_icon_microphone_on_filled_24_d2d2d2;
            if (z2) {
                i = R.drawable.oc_icon_microphone_off_filled_24_d2d2d2;
            }
            drawable = AnonymousClass1uW.getDrawableFromResource(shellButton, i);
        }
        if ((136 & j) != 0) {
            this.actionButton.setText(str2);
        }
        if ((j & 160) != 0 && AnonymousClass1uW.SDK_INT >= 11) {
            this.icon.setAlpha(f);
            this.name.setAlpha(f);
            this.subtitle.setAlpha(f);
        }
        if ((132 & j) != 0) {
            C11051qV.A02(this.name, str);
        }
        if ((j & 192) != 0) {
            this.partyMute.setBackground(drawable);
        }
        if ((128 & j) != 0) {
            this.partyVolumeSlider.setIcon(AnonymousClass1uW.getDrawableFromResource(this.mRoot, R.drawable.oc_icon_volume_low_filled_24_d2d2d2));
        }
        if ((j & 144) != 0) {
            C11051qV.A02(this.subtitle, str3);
        }
        AnonymousClass1uW.executeBindingsOn(this.partyVolumeSlider);
        AnonymousClass1uW.executeBindingsOn(this.actionButton);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeActionButton((CommonTabletRectangularButtonBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangePartyVolumeSlider((OsigSeekbarBinding) obj, i2);
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding
    public void setActionButtonText(@Nullable String str) {
        this.mActionButtonText = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(91);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding
    public void setIsInvitedUser(boolean z) {
        this.mIsInvitedUser = z;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(93);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding
    public void setMuted(boolean z) {
        this.mMuted = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(99);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding
    public void setNameText(@Nullable String str) {
        this.mNameText = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(78);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding
    public void setSubtitleText(@Nullable String str) {
        this.mSubtitleText = str;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(31);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (78 == i) {
            setNameText((String) obj);
            return true;
        } else if (91 == i) {
            setActionButtonText((String) obj);
            return true;
        } else if (31 == i) {
            setSubtitleText((String) obj);
            return true;
        } else if (93 == i) {
            setIsInvitedUser(((Boolean) obj).booleanValue());
            return true;
        } else if (99 != i) {
            return false;
        } else {
            setMuted(((Boolean) obj).booleanValue());
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.partyVolumeSlider.setLifecycleOwner(r2);
        this.actionButton.setLifecycleOwner(r2);
    }

    public AnytimeTabletSocialListItemV2BindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 13, sIncludes, sViewsWithIds));
    }

    public AnytimeTabletSocialListItemV2BindingImpl(AbstractC003408r r31, View view, Object[] objArr) {
        super(r31, view, 2, (CommonTabletRectangularButtonBinding) objArr[6], (ShellButton) objArr[9], (ShellButton) objArr[11], (ImageView) objArr[1], (OCSpinner) objArr[7], (ShellButton) objArr[8], (OCTextView) objArr[2], (ShellButton) objArr[4], (OsigSeekbarBinding) objArr[5], (OCSpinner) objArr[12], (ConstraintLayout) objArr[0], (OCSpinner) objArr[10], (OCTextView) objArr[3]);
        this.mDirtyFlags = -1;
        this.icon.setTag(null);
        this.name.setTag(null);
        this.partyMute.setTag(null);
        this.socialListItem.setTag(null);
        this.subtitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
