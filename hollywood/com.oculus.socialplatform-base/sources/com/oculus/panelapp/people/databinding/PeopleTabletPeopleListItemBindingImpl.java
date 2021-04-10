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
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.people.views.UserViewModel;
import com.oculus.socialplatform.R;

public class PeopleTabletPeopleListItemBindingImpl extends PeopleTabletPeopleListItemBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeViewModel(UserViewModel userViewModel, int i) {
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
        } else if (i == 78) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 82) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 65) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 64) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 31) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i != 73) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 128;
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
            this.mDirtyFlags = 256;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.people.databinding.PeopleTabletPeopleListItemBinding
    public void setViewModel(@Nullable UserViewModel userViewModel) {
        updateRegistration(0, userViewModel);
        this.mViewModel = userViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.cta_row, 6);
        sparseIntArray.put(R.id.cta_left_button, 7);
        sparseIntArray.put(R.id.cta_middle_button, 8);
        sparseIntArray.put(R.id.cta_overflow_button, 9);
        sparseIntArray.put(R.id.cta_loading_spinner, 10);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0072, code lost:
        if (r13 == null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ad, code lost:
        if (r16 != false) goto L_0x00af;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 422
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.databinding.PeopleTabletPeopleListItemBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((UserViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((UserViewModel) obj);
        return true;
    }

    public PeopleTabletPeopleListItemBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 11, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public PeopleTabletPeopleListItemBindingImpl(AbstractC003408r r18, View view, Object[] objArr) {
        super(r18, view, 1, (OCButton) objArr[7], (OCSpinner) objArr[10], (OCButton) objArr[8], (OCButton) objArr[9], (View) objArr[6], (ImageView) objArr[1], (OCTextView) objArr[2], (OCTextView) objArr[3], (ConstraintLayout) objArr[0], (ImageView) objArr[4], (OCTextView) objArr[5]);
        this.mDirtyFlags = -1;
        this.icon.setTag(null);
        this.lastActiveTime.setTag(null);
        this.name.setTag(null);
        this.peopleListItem.setTag(null);
        this.presenceIcon.setTag(null);
        this.subtitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
