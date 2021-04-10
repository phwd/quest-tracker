package defpackage;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: d7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface AbstractC2129d7 extends Z6 {
    void disconnect();

    void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    int getMinApkVersion();

    boolean requiresSignIn();
}
