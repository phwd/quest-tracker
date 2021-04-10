package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.panelapp.socialandroidbackpanel.views.application_invites.ApplicationInvitesView;
import com.oculus.panelapp.socialandroidbackpanel.views.application_invites.ApplicationInvitesViewModel;

public class ApplicationInvitesViewBindingImpl extends ApplicationInvitesViewBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    private boolean onChangeApplicationInvitesViewModel(ApplicationInvitesViewModel applicationInvitesViewModel, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

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
            this.mDirtyFlags = 2;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeApplicationInvitesViewModel((ApplicationInvitesViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (210 != i) {
            return false;
        }
        setApplicationInvitesViewModel((ApplicationInvitesViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.ApplicationInvitesViewBinding
    public void setApplicationInvitesViewModel(@Nullable ApplicationInvitesViewModel applicationInvitesViewModel) {
        this.mApplicationInvitesViewModel = applicationInvitesViewModel;
    }

    public ApplicationInvitesViewBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 1, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public ApplicationInvitesViewBindingImpl(AbstractC003408r r3, View view, Object[] objArr) {
        super(r3, view, 1, (ApplicationInvitesView) objArr[0]);
        this.mDirtyFlags = -1;
        this.applicationInvitesView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
