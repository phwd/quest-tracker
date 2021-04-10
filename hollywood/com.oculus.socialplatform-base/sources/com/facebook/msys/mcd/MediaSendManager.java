package com.facebook.msys.mcd;

import X.AnonymousClass1NL;
import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.NetworkSession;
import com.facebook.msys.mci.NotificationCenter;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.io.File;
import java.io.IOException;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class MediaSendManager {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(NetworkSession networkSession, NotificationCenter notificationCenter, String str, int i);

    static {
        AnonymousClass1NZ.A00();
    }

    public MediaSendManager(NetworkSession networkSession, NotificationCenter notificationCenter, File file) {
        File file2 = file;
        if (file != null) {
            try {
                if (!file.exists() && !file.mkdir()) {
                    StringBuilder sb = new StringBuilder("Cache directory not exists and fails to create it: ");
                    sb.append(file);
                    throw new IllegalArgumentException(sb.toString());
                } else if (file.isDirectory()) {
                    File A00 = AnonymousClass1NL.A00(file, "media_send_cache");
                    this.mNativeHolder = initNativeHolder(networkSession, notificationCenter, (A00 != null ? A00 : file2).getCanonicalPath(), 104857600);
                } else {
                    StringBuilder sb2 = new StringBuilder("Cache directory is not a directory: ");
                    sb2.append(file);
                    throw new IllegalArgumentException(sb2.toString());
                }
            } catch (IOException e) {
                StringBuilder sb3 = new StringBuilder("Unable to get canonical path from: ");
                sb3.append(file);
                throw new RuntimeException(sb3.toString(), e);
            }
        } else {
            StringBuilder sb4 = new StringBuilder("Invalid cache directory: ");
            sb4.append(file);
            throw new IllegalArgumentException(sb4.toString());
        }
    }
}
