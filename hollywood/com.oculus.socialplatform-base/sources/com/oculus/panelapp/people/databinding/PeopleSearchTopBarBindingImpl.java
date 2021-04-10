package com.oculus.panelapp.people.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.people.views.PeopleSearchField;
import com.oculus.socialplatform.R;

public class PeopleSearchTopBarBindingImpl extends PeopleSearchTopBarBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
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
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.back_button, 1);
        sparseIntArray.put(R.id.search_box, 2);
    }

    public PeopleSearchTopBarBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 3, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public PeopleSearchTopBarBindingImpl(AbstractC003408r r10, View view, Object[] objArr) {
        super(r10, view, 0, (OCButton) objArr[1], (ConstraintLayout) objArr[0], (PeopleSearchField) objArr[2]);
        this.mDirtyFlags = -1;
        this.container.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
