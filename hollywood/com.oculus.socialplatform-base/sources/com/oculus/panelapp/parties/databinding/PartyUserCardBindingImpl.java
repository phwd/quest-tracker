package com.oculus.panelapp.parties.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.acra.ErrorReporter;
import com.facebook.acra.util.StatFsUtil;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.parties.views.PartyUserCardViewModel;
import com.oculus.socialplatform.R;
import okio.SegmentPool;

public class PartyUserCardBindingImpl extends PartyUserCardBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;
    @NonNull
    public final LinearLayout mboundView8;

    private boolean onChangePartyUserCardViewModel(PartyUserCardViewModel partyUserCardViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 3) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 41) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 15) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 49) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 10) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 31) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == 46) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == 4) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == 26) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == 44) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == 33) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM;
            }
            return true;
        } else if (i == 47) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_INSTALL_ALT_STACK;
            }
            return true;
        } else if (i == 17) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i == 38) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        } else if (i == 9) {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        } else if (i == 12) {
            synchronized (this) {
                this.mDirtyFlags |= SegmentPool.MAX_SIZE;
            }
            return true;
        } else if (i == 50) {
            synchronized (this) {
                this.mDirtyFlags |= 131072;
            }
            return true;
        } else if (i == 5) {
            synchronized (this) {
                this.mDirtyFlags |= 262144;
            }
            return true;
        } else if (i != 25) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= ErrorReporter.SIGQUIT_MAX_REPORT_SIZE;
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
            this.mDirtyFlags = StatFsUtil.IN_MEGA_BYTE;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.parties.databinding.PartyUserCardBinding
    public void setPartyUserCardViewModel(@Nullable PartyUserCardViewModel partyUserCardViewModel) {
        updateRegistration(0, partyUserCardViewModel);
        this.mPartyUserCardViewModel = partyUserCardViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(34);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.profile_photo, 17);
        sparseIntArray.put(R.id.cta_row, 18);
        sparseIntArray.put(R.id.card_hover_overlay, 19);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0142, code lost:
        if (r13 != false) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0180, code lost:
        if (r15 != false) goto L_0x0182;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x01c1, code lost:
        if (r17 != false) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005a, code lost:
        if (r9 != false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a8, code lost:
        if (r9 == false) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00e9, code lost:
        if (r9 != false) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0107, code lost:
        if (r11 != false) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0122, code lost:
        if (r13 != false) goto L_0x0124;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 921
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.databinding.PartyUserCardBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangePartyUserCardViewModel((PartyUserCardViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (34 != i) {
            return false;
        }
        setPartyUserCardViewModel((PartyUserCardViewModel) obj);
        return true;
    }

    public PartyUserCardBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 20, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public PartyUserCardBindingImpl(AbstractC003408r r44, View view, Object[] objArr) {
        super(r44, view, 1, (View) objArr[2], (View) objArr[19], (OCButton) objArr[12], (OCButton) objArr[13], (OCSpinner) objArr[15], (OCButton) objArr[14], (View) objArr[18], (OCTextView) objArr[10], (OCTextView) objArr[11], (OCButton) objArr[16], (ImageView) objArr[6], (ImageView) objArr[5], (ImageView) objArr[4], (ImageView) objArr[3], (ConstraintLayout) objArr[0], (ImageView) objArr[17], (View) objArr[1], (OCTextView) objArr[9], (OCTextView) objArr[7]);
        this.mDirtyFlags = -1;
        this.audioConnectedIcon.setTag(null);
        this.ctaButton.setTag(null);
        this.ctaLeftButton.setTag(null);
        this.ctaLoadingSpinner.setTag(null);
        this.ctaRightButton.setTag(null);
        this.groupLaunchSeparator.setTag(null);
        this.groupLaunchStatus.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[8];
        this.mboundView8 = linearLayout;
        linearLayout.setTag(null);
        this.overflowMenuButton.setTag(null);
        this.partyMutedIndicator.setTag(null);
        this.partyMutedIndicatorBackground.setTag(null);
        this.partySpeakingIndicator.setTag(null);
        this.partySpeakingIndicatorBackground.setTag(null);
        this.partyUserCard.setTag(null);
        this.profilePhotoBorder.setTag(null);
        this.subtitle.setTag(null);
        this.username.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
