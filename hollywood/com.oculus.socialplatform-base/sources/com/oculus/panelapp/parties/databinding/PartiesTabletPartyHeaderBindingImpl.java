package com.oculus.panelapp.parties.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.parties.views.PartiesViewModel;
import com.oculus.socialplatform.R;
import com.oculus.tablet.databinding.OsigButtonBorderlessBinding;
import com.oculus.tablet.databinding.OsigSeekbarBinding;

public class PartiesTabletPartyHeaderBindingImpl extends PartiesTabletPartyHeaderBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    static {
        AnonymousClass1ui r4 = new AnonymousClass1ui(7);
        sIncludes = r4;
        r4.A00(new String[]{"osig_button_borderless", "osig_seekbar"}, new int[]{4, 5}, new int[]{R.layout.osig_button_borderless, R.layout.osig_seekbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.party_header_title, 6);
    }

    private boolean onChangeNavigateBackButton(OsigButtonBorderlessBinding osigButtonBorderlessBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangePartiesViewModel(PartiesViewModel partiesViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 32) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 28) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 24) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i != 21) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
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
        if (i != 0 || this.navigateBackButton.hasPendingBindings() || this.partyVolumeSlider.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
        }
        this.navigateBackButton.invalidateAll();
        this.partyVolumeSlider.invalidateAll();
        requestRebind();
    }

    @Override // com.oculus.panelapp.parties.databinding.PartiesTabletPartyHeaderBinding
    public void setPartiesViewModel(@Nullable PartiesViewModel partiesViewModel) {
        updateRegistration(2, partiesViewModel);
        this.mPartiesViewModel = partiesViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(27);
        super.requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        if (r11 != false) goto L_0x0036;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 259
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.databinding.PartiesTabletPartyHeaderBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeNavigateBackButton((OsigButtonBorderlessBinding) obj, i2);
        }
        if (i == 1) {
            return onChangePartyVolumeSlider((OsigSeekbarBinding) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangePartiesViewModel((PartiesViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (27 != i) {
            return false;
        }
        setPartiesViewModel((PartiesViewModel) obj);
        return true;
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.navigateBackButton.setLifecycleOwner(r2);
        this.partyVolumeSlider.setLifecycleOwner(r2);
    }

    public PartiesTabletPartyHeaderBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 7, sIncludes, sViewsWithIds));
    }

    public PartiesTabletPartyHeaderBindingImpl(AbstractC003408r r14, View view, Object[] objArr) {
        super(r14, view, 3, (OCButton) objArr[3], (OsigButtonBorderlessBinding) objArr[4], (ConstraintLayout) objArr[0], (OCTextView) objArr[6], (OsigSeekbarBinding) objArr[5], (OCButton) objArr[2], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.endCallButton.setTag(null);
        this.partyHeader.setTag(null);
        this.privacySettingsButton.setTag(null);
        this.spotsAvailable.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
