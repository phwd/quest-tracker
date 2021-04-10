package com.facebook.msys.mci;

import X.AnonymousClass1NX;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DatabaseConnection {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public interface DatabaseRunnable {
        @DoNotStrip
        void run(SqliteHolder sqliteHolder);
    }

    @DoNotStrip
    public native void execute(DatabaseRunnable databaseRunnable);

    @DoNotStrip
    public native int getMode();

    static {
        AnonymousClass1NX.A00();
    }

    @DoNotStrip
    public DatabaseConnection(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
