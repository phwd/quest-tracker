package com.oculus.panelapp.people.databinding;

import X.AbstractC003408r;
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
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.people.views.PeopleEmptyViewModel;

public class PeopleTabletEmptyBindingImpl extends PeopleTabletEmptyBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    private boolean onChangeViewModel(PeopleEmptyViewModel peopleEmptyViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 75) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 66) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 67) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 72) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 80) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 16) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i != 76) {
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

    @Override // com.oculus.panelapp.people.databinding.PeopleTabletEmptyBinding
    public void setViewModel(@Nullable PeopleEmptyViewModel peopleEmptyViewModel) {
        updateRegistration(0, peopleEmptyViewModel);
        this.mViewModel = peopleEmptyViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        Drawable drawable;
        Drawable drawable2;
        String str;
        String str2;
        int i;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PeopleEmptyViewModel peopleEmptyViewModel = this.mViewModel;
        int i2 = 0;
        String str3 = null;
        if ((511 & j) != 0) {
            if ((j & 265) == 0 || peopleEmptyViewModel == null) {
                str2 = null;
            } else {
                str2 = peopleEmptyViewModel.mTitle;
            }
            if ((j & 259) == 0 || peopleEmptyViewModel == null) {
                drawable2 = null;
            } else {
                drawable2 = peopleEmptyViewModel.mImage;
            }
            if ((j & 261) == 0 || peopleEmptyViewModel == null) {
                i = 0;
            } else {
                i = peopleEmptyViewModel.getTextGravity();
            }
            if (!((j & 273) == 0 || peopleEmptyViewModel == null)) {
                i2 = peopleEmptyViewModel.getTitleTextSize();
            }
            if ((j & 385) == 0 || peopleEmptyViewModel == null) {
                drawable = null;
            } else {
                drawable = peopleEmptyViewModel.getCtaBackground();
            }
            if ((j & 289) == 0 || peopleEmptyViewModel == null) {
                str = null;
            } else {
                str = peopleEmptyViewModel.mSubtitle;
            }
            if (!((j & 321) == 0 || peopleEmptyViewModel == null)) {
                str3 = peopleEmptyViewModel.mCtaText;
            }
        } else {
            drawable = null;
            drawable2 = null;
            str = null;
            str2 = null;
            i = 0;
        }
        if ((j & 321) != 0) {
            C11051qV.A02(this.ctaButton, str3);
        }
        if ((j & 385) != 0) {
            this.ctaButton.setBackground(drawable);
        }
        if ((j & 259) != 0) {
            this.image.setImageDrawable(drawable2);
        }
        if ((j & 261) != 0) {
            this.subtitle.setGravity(i);
            this.title.setGravity(i);
        }
        if ((289 & j) != 0) {
            C11051qV.A02(this.subtitle, str);
        }
        if ((j & 265) != 0) {
            C11051qV.A02(this.title, str2);
        }
        if ((j & 273) != 0) {
            this.title.setTextSize(0, (float) i2);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((PeopleEmptyViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((PeopleEmptyViewModel) obj);
        return true;
    }

    public PeopleTabletEmptyBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 5, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public PeopleTabletEmptyBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 1, (OCButton) objArr[4], (ConstraintLayout) objArr[0], (ImageView) objArr[1], (OCTextView) objArr[3], (OCTextView) objArr[2]);
        this.mDirtyFlags = -1;
        this.ctaButton.setTag(null);
        this.emptyView.setTag(null);
        this.image.setTag(null);
        this.subtitle.setTag(null);
        this.title.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
