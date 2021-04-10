package com.google.android.material.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageButton;
import androidx.customview.view.AbsSavedState;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CheckableImageButton extends C4353q8 implements Checkable {
    public static final int[] H = {16842912};
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9693J = true;
    public boolean K = true;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new C0827Nn();
        public boolean H;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.G, i);
            parcel.writeInt(this.H ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.H = parcel.readInt() != 1 ? false : true;
        }
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f5240_resource_name_obfuscated_RES_2130968970);
        AbstractC1920bu1.n(this, new C0766Mn(this));
    }

    public boolean isChecked() {
        return this.I;
    }

    public int[] onCreateDrawableState(int i) {
        if (!this.I) {
            return super.onCreateDrawableState(i);
        }
        int[] iArr = H;
        return ImageButton.mergeDrawableStates(super.onCreateDrawableState(i + iArr.length), iArr);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.G);
        setChecked(savedState.H);
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.H = this.I;
        return savedState;
    }

    public void setChecked(boolean z) {
        if (this.f9693J && this.I != z) {
            this.I = z;
            refreshDrawableState();
            sendAccessibilityEvent(2048);
        }
    }

    public void setPressed(boolean z) {
        if (this.K) {
            super.setPressed(z);
        }
    }

    public void toggle() {
        setChecked(!this.I);
    }
}
