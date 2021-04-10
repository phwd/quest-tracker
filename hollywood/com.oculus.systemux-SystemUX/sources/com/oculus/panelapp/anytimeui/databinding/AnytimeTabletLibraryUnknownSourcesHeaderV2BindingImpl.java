package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCLink;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.LibraryContentAdapter;

public class AnytimeTabletLibraryUnknownSourcesHeaderV2BindingImpl extends AnytimeTabletLibraryUnknownSourcesHeaderV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    static {
        sViewsWithIds.put(R.id.info_icon, 2);
        sViewsWithIds.put(R.id.open_tab_layout, 3);
        sViewsWithIds.put(R.id.open_tab_button, 4);
        sViewsWithIds.put(R.id.open_tab_icon, 5);
    }

    public AnytimeTabletLibraryUnknownSourcesHeaderV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletLibraryUnknownSourcesHeaderV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[2], (OCButton) objArr[4], (ImageView) objArr[5], (ConstraintLayout) objArr[3], (OCLink) objArr[1]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.warningDescription.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        if ((j & 1) != 0) {
            OCLink.setUri(this.warningDescription, LibraryContentAdapter.LIBRARY_PRIVACY_NOTE_URL);
        }
    }
}
