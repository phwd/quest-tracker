package com.oculus.panelapp.parties.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.parties.views.PartyTravelFooterViewModel;
import com.oculus.socialplatform.R;

public class PartyTravelFooterBindingImpl extends PartyTravelFooterBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangePartyTravelFooterViewModel(PartyTravelFooterViewModel partyTravelFooterViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 39) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 29) {
            synchronized (this) {
                this.mDirtyFlags |= 16382;
            }
            return true;
        } else if (i == 2) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 43) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 48) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 30) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 11) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == 19) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == 1) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == 45) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == 8) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == 40) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM;
            }
            return true;
        } else if (i == 20) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_INSTALL_ALT_STACK;
            }
            return true;
        } else if (i != 42) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
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
            this.mDirtyFlags = 32768;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.parties.databinding.PartyTravelFooterBinding
    public void setPartyTravelFooterViewModel(@Nullable PartyTravelFooterViewModel partyTravelFooterViewModel) {
        updateRegistration(0, partyTravelFooterViewModel);
        this.mPartyTravelFooterViewModel = partyTravelFooterViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.travel_footer_preview, 10);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005b, code lost:
        if (r11 != false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0082, code lost:
        if (r11 != false) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009d, code lost:
        if (r11 != false) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00b8, code lost:
        if (r13 != false) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00d7, code lost:
        if (r11 != false) goto L_0x00d9;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 575
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.databinding.PartyTravelFooterBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangePartyTravelFooterViewModel((PartyTravelFooterViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (14 != i) {
            return false;
        }
        setPartyTravelFooterViewModel((PartyTravelFooterViewModel) obj);
        return true;
    }

    public PartyTravelFooterBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 11, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public PartyTravelFooterBindingImpl(AbstractC003408r r18, View view, Object[] objArr) {
        super(r18, view, 1, (OCTextView) objArr[1], (OCButton) objArr[5], (OCTextView) objArr[2], (OCTextView) objArr[3], (ConstraintLayout) objArr[0], (OCButton) objArr[8], (OCSpinner) objArr[9], (OCButton) objArr[6], (OCSpinner) objArr[7], (OCTextView) objArr[4], (ImageView) objArr[10]);
        this.mDirtyFlags = -1;
        this.applicationName.setTag(null);
        this.changeDestinationButton.setTag(null);
        this.destinationInfoSeparator.setTag(null);
        this.destinationName.setTag(null);
        this.partyFooter.setTag(null);
        this.primaryActionButton.setTag(null);
        this.primaryActionButtonSpinner.setTag(null);
        this.secondaryActionButton.setTag(null);
        this.secondaryActionButtonSpinner.setTag(null);
        this.statusText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
