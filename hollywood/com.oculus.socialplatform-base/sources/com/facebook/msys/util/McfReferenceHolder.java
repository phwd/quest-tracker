package com.facebook.msys.util;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class McfReferenceHolder {
    @DoNotStrip
    public long nativeReference = 0;

    static {
        AnonymousClass1Nh.A00();
    }

    @DoNotStrip
    private void setNativeReference(long j) {
        this.nativeReference = j;
    }
}
