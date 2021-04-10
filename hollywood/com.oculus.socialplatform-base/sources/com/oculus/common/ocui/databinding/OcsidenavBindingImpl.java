package com.oculus.common.ocui.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public class OcsidenavBindingImpl extends OcsidenavBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mTitle;
        Drawable drawable = this.mBackground;
        long j2 = 5 & j;
        if ((j & 6) != 0) {
            this.mboundView0.setBackground(drawable);
        }
        if (j2 != 0) {
            C11051qV.A02(this.title, str);
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
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.recycler_view, 2);
    }

    @Override // com.oculus.common.ocui.databinding.OcsidenavBinding
    public void setBackground(@Nullable Drawable drawable) {
        this.mBackground = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(177);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcsidenavBinding
    public void setTitle(@Nullable String str) {
        this.mTitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(67);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (67 == i) {
            setTitle((String) obj);
            return true;
        } else if (177 != i) {
            return false;
        } else {
            setBackground((Drawable) obj);
            return true;
        }
    }

    public OcsidenavBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 3, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public OcsidenavBindingImpl(AbstractC003408r r9, View view, Object[] objArr) {
        super(r9, view, 0, (OCRecyclerView) objArr[2], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.title.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
