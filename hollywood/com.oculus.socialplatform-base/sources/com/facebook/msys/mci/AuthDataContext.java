package com.facebook.msys.mci;

import X.AnonymousClass1NX;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AuthDataContext {
    public final AuthData mAuthData;
    public final Database mDatabase;
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(AuthData authData, Database database);

    @DoNotStrip
    public native boolean getIsValid();

    @DoNotStrip
    public native void invalidate();

    static {
        AnonymousClass1NX.A00();
    }

    public AuthDataContext(AuthData authData, Database database) {
        if (authData != null) {
            this.mAuthData = authData;
            if (database != null) {
                this.mDatabase = database;
                this.mNativeHolder = initNativeHolder(authData, database);
                return;
            }
            throw null;
        }
        throw null;
    }
}
