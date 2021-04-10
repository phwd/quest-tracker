package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AuthSession {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public interface CompletionListener {
        @DoNotStrip
        void onAuthComplete(@Nullable AuthData authData, @Nullable RedactedString redactedString, @Nullable Throwable th);
    }

    @DoNotStrip
    private native void exchangeTokenNative(AuthData authData, CompletionListener completionListener);

    @DoNotStrip
    private native void facebookLoginNative(String str, String str2, @Nullable String str3, AuthData authData, CompletionListener completionListener);

    @DoNotStrip
    private native void facebookWorkAccountLoginNative(String str, String str2, @Nullable String str3, AuthData authData, CompletionListener completionListener);

    @DoNotStrip
    public static native NativeHolder initNativeHolder(NetworkSession networkSession, AppInfo appInfo);

    @DoNotStrip
    private native void loadUserNative(AuthData authData, CompletionListener completionListener);

    @DoNotStrip
    private native void logOutNative(NetworkSession networkSession, AuthData authData, CompletionListener completionListener);

    @DoNotStrip
    private native void personalToWorkSwitchNative(String str, AuthData authData, CompletionListener completionListener);

    static {
        AnonymousClass1Nh.A00();
    }
}
