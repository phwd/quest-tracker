package com.facebook.mediamanager;

import X.AnonymousClass0l0;
import X.AnonymousClass1NL;
import android.annotation.SuppressLint;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.mci.NetworkSession;
import com.facebook.msys.mci.NotificationCenter;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
public class MediaManager {
    @DoNotStrip
    public final NativeHolder mNativeHolder;
    public final NotificationCenter mNotificationCenter;
    public final Map<String, MediaLoadRequest> mTokenToRequest = Collections.synchronizedMap(new HashMap());

    public @interface MediaLoadPriority {
    }

    public @interface MimeType {
    }

    public @interface NotificationUserInfoKey {
    }

    public MediaManager(NetworkSession networkSession, NotificationCenter notificationCenter, File file) {
        String str;
        this.mNotificationCenter = notificationCenter;
        try {
            File A00 = AnonymousClass1NL.A00(file, "media_load_cache");
            str = (A00 == null ? file : A00).getCanonicalPath();
        } catch (IOException unused) {
            str = file.getAbsolutePath();
        }
        this.mNativeHolder = initNativeHolder(this, networkSession, notificationCenter, str, 262144000, 5242880, 0.2f);
    }

    @DoNotStrip
    private native void cancelMediaLoadNative(String str);

    @DoNotStrip
    public static native NativeHolder initNativeHolder(MediaManager mediaManager, NetworkSession networkSession, NotificationCenter notificationCenter, String str, int i, int i2, float f);

    @DoNotStrip
    private native boolean isMediaLoadCanceledNative(String str);

    @DoNotStrip
    private native String loadMediaNative(String str, @Nullable String str2, String str3, float f, float f2, float f3, String str4, int i, boolean z, boolean z2, @Nullable Object obj);

    @DoNotStrip
    private native void registerLoggingContextNative(Mailbox mailbox);

    static {
        AnonymousClass0l0.A06("mediamanagerjni");
    }

    @DoNotStrip
    private void dispatchMediaListenerCallback(String str, Object obj) {
        if (obj != null) {
            this.mTokenToRequest.get(str);
        }
    }

    @DoNotStrip
    private void dispatchMediaProgressListenerCallback(String str, Object obj) {
        if (obj != null) {
            this.mTokenToRequest.get(str);
        }
    }
}
