package com.facebook.msys.dasm;

import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DasmSupportHelper {
    public static volatile Context sContext;

    @DoNotStrip
    @Nullable
    public static AssetManager assets() {
        if (sContext != null) {
            return sContext.getAssets();
        }
        return null;
    }

    static {
        AnonymousClass1NZ.A00();
    }
}
