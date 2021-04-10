package com.oculus.common.ocui.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSideNavItem;
import com.oculus.socialplatform.R;

public class OcsidenavItemBindingImpl extends OcsidenavItemBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;

    static {
        AnonymousClass1ui r3 = new AnonymousClass1ui(3);
        sIncludes = r3;
        r3.A00(new String[]{"ocsidenav_badge"}, new int[]{2}, new int[]{R.layout.ocsidenav_badge});
    }

    private boolean onChangeBadge(OcsidenavBadgeBinding ocsidenavBadgeBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeSideNavItem(OCSideNavItem oCSideNavItem, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 174) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i != 172) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        int i;
        synchronized (this) {
            i = (this.mDirtyFlags > 0 ? 1 : (this.mDirtyFlags == 0 ? 0 : -1));
        }
        if (i != 0 || this.badge.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
        }
        this.badge.invalidateAll();
        requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcsidenavItemBinding
    public void setSideNavItem(@Nullable OCSideNavItem oCSideNavItem) {
        updateRegistration(1, oCSideNavItem);
        this.mSideNavItem = oCSideNavItem;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(169);
        super.requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        if (r11 <= 9) goto L_0x0038;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 171
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.common.ocui.databinding.OcsidenavItemBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeBadge((OcsidenavBadgeBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeSideNavItem((OCSideNavItem) obj, i2);
    }

    @Override // com.oculus.common.ocui.databinding.OcsidenavItemBinding
    public void setBackground(@Nullable Drawable drawable) {
        this.mBackground = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(177);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (177 == i) {
            setBackground((Drawable) obj);
            return true;
        } else if (169 != i) {
            return false;
        } else {
            setSideNavItem((OCSideNavItem) obj);
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.badge.setLifecycleOwner(r2);
    }

    public OcsidenavItemBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 3, sIncludes, (SparseIntArray) null));
    }

    public OcsidenavItemBindingImpl(AbstractC003408r r9, View view, Object[] objArr) {
        super(r9, view, 2, (OcsidenavBadgeBinding) objArr[2], (OCButton) objArr[1]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.navButton.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
