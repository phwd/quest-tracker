package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.enterprise.EnterpriseKioskNotInstalledDialog;

public class EnterpriseKioskNotInstalledDialogBindingImpl extends EnterpriseKioskNotInstalledDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(9);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    static {
        sIncludes.setIncludes(0, new String[]{"dialog_body"}, new int[]{1}, new int[]{R.layout.dialog_body});
        sViewsWithIds.put(R.id.left_guideline, 2);
        sViewsWithIds.put(R.id.right_guideline, 3);
        sViewsWithIds.put(R.id.enterprise_kiosk_not_installed_title, 4);
        sViewsWithIds.put(R.id.enterprise_kiosk_not_installed_spinner, 5);
        sViewsWithIds.put(R.id.enterprise_kiosk_not_installed_button, 6);
        sViewsWithIds.put(R.id.enterprise_kiosk_not_installed_admin_badge, 7);
        sViewsWithIds.put(R.id.enterprise_kiosk_not_installed_admin_login_text, 8);
    }

    public EnterpriseKioskNotInstalledDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds));
    }

    private EnterpriseKioskNotInstalledDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ImageView) objArr[7], (OCTextView) objArr[8], (View) objArr[6], (EnterpriseKioskNotInstalledDialog) objArr[0], (OCSpinner) objArr[5], (DialogBodyBinding) objArr[1], (OCTextView) objArr[4], (Guideline) objArr[2], (Guideline) objArr[3]);
        this.mDirtyFlags = -1;
        this.enterpriseKioskNotInstalledDialog.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.enterpriseKioskNotInstalledTextBody.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.enterpriseKioskNotInstalledTextBody.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            com.oculus.panelapp.androiddialog.databinding.DialogBodyBinding r0 = r4.enterpriseKioskNotInstalledTextBody
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.EnterpriseKioskNotInstalledDialogBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.enterpriseKioskNotInstalledTextBody.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeEnterpriseKioskNotInstalledTextBody((DialogBodyBinding) obj, i2);
    }

    private boolean onChangeEnterpriseKioskNotInstalledTextBody(DialogBodyBinding dialogBodyBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        if ((j & 2) != 0) {
            this.enterpriseKioskNotInstalledTextBody.setDialogBodyText(getRoot().getResources().getString(R.string.enterprise_kiosk_not_installed_dialog_description));
        }
        executeBindingsOn(this.enterpriseKioskNotInstalledTextBody);
    }
}
