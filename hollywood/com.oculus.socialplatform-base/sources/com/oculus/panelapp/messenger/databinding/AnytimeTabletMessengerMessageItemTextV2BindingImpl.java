package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.Typeface;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCTextView;

public class AnytimeTabletMessengerMessageItemTextV2BindingImpl extends AnytimeTabletMessengerMessageItemTextV2Binding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mDisplayText;
        int i = this.mDisplayTextColor;
        Typeface typeface = this.mDisplayTextTypeface;
        long j2 = 9 & j;
        long j3 = 10 & j;
        long j4 = j & 12;
        if (j2 != 0) {
            C11051qV.A02(this.messageText, str);
        }
        if (j3 != 0) {
            this.messageText.setTextColor(i);
        }
        if (j4 != 0) {
            this.messageText.setTypeface(typeface);
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
            this.mDirtyFlags = 8;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemTextV2Binding
    public void setDisplayText(@Nullable String str) {
        this.mDisplayText = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(123);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemTextV2Binding
    public void setDisplayTextColor(int i) {
        this.mDisplayTextColor = i;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(151);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemTextV2Binding
    public void setDisplayTextTypeface(@Nullable Typeface typeface) {
        this.mDisplayTextTypeface = typeface;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(145);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (123 == i) {
            setDisplayText((String) obj);
            return true;
        } else if (151 == i) {
            setDisplayTextColor(((Number) obj).intValue());
            return true;
        } else if (145 != i) {
            return false;
        } else {
            setDisplayTextTypeface((Typeface) obj);
            return true;
        }
    }

    public AnytimeTabletMessengerMessageItemTextV2BindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 1, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public AnytimeTabletMessengerMessageItemTextV2BindingImpl(AbstractC003408r r3, View view, Object[] objArr) {
        super(r3, view, 0, (OCTextView) objArr[0]);
        this.mDirtyFlags = -1;
        this.messageText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
