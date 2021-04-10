package com.oculus.panelapp.people.databinding;

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
import com.oculus.panelapp.people.views.PeopleUserCardViewModel;
import com.oculus.socialplatform.R;

public class PeopleTabletPeopleUserCardBindingImpl extends PeopleTabletPeopleUserCardBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeViewModel(PeopleUserCardViewModel peopleUserCardViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 77) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 81) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 83) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 47) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 38) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 12) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == 82) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == 69) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == 64) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i != 31) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
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
            this.mDirtyFlags = BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.people.databinding.PeopleTabletPeopleUserCardBinding
    public void setViewModel(@Nullable PeopleUserCardViewModel peopleUserCardViewModel) {
        updateRegistration(0, peopleUserCardViewModel);
        this.mViewModel = peopleUserCardViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.profile_photo, 8);
        sparseIntArray.put(R.id.cta_row, 9);
        sparseIntArray.put(R.id.cta_loading_spinner, 10);
        sparseIntArray.put(R.id.secondary_cta_button, 11);
        sparseIntArray.put(R.id.secondary_cta_button_loading_spinner, 12);
        sparseIntArray.put(R.id.card_hover_overlay, 13);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0065, code lost:
        if (r13 == null) goto L_0x0067;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 406
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.databinding.PeopleTabletPeopleUserCardBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((PeopleUserCardViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((PeopleUserCardViewModel) obj);
        return true;
    }

    public PeopleTabletPeopleUserCardBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 14, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public PeopleTabletPeopleUserCardBindingImpl(AbstractC003408r r34, View view, Object[] objArr) {
        super(r34, view, 1, (View) objArr[13], (OCButton) objArr[3], (OCButton) objArr[4], (OCSpinner) objArr[10], (OCButton) objArr[5], (View) objArr[9], (OCTextView) objArr[1], (ConstraintLayout) objArr[0], (ImageView) objArr[6], (ImageView) objArr[8], (OCButton) objArr[11], (OCSpinner) objArr[12], (OCTextView) objArr[7], (OCTextView) objArr[2]);
        this.mDirtyFlags = -1;
        this.ctaButton.setTag(null);
        this.ctaLeftButton.setTag(null);
        this.ctaRightButton.setTag(null);
        this.lastActiveTime.setTag(null);
        this.peopleUserCard.setTag(null);
        this.presenceIcon.setTag(null);
        this.subtitle.setTag(null);
        this.username.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
