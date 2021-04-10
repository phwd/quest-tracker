package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.views.start_messenger_call.StartMessengerCallView;
import com.oculus.socialplatform.R;

public class StartMessengerCallViewBindingImpl extends StartMessengerCallViewBinding {
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
        sparseIntArray.put(R.id.start_messenger_call_title, 1);
        sparseIntArray.put(R.id.start_messenger_call_secondary_button, 2);
        sparseIntArray.put(R.id.start_messenger_call_primary_button, 3);
    }

    public StartMessengerCallViewBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 4, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public StartMessengerCallViewBindingImpl(AbstractC003408r r11, View view, Object[] objArr) {
        super(r11, view, 0, (StartMessengerCallView) objArr[0], (OCButton) objArr[3], (OCButton) objArr[2], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.startMessengerCall.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
