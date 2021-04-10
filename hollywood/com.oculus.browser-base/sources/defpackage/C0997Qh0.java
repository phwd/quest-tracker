package defpackage;

import android.media.session.MediaSessionManager;
import android.os.Build;
import android.text.TextUtils;
import java.util.Objects;

/* renamed from: Qh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0997Qh0 {

    /* renamed from: a  reason: collision with root package name */
    public C1119Sh0 f8778a;

    public C0997Qh0(String str, int i, int i2) {
        Objects.requireNonNull(str, "package shouldn't be null");
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packageName should be nonempty");
        } else if (Build.VERSION.SDK_INT >= 28) {
            this.f8778a = new C1058Rh0(str, i, i2);
        } else {
            this.f8778a = new C1119Sh0(str, i, i2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0997Qh0)) {
            return false;
        }
        return this.f8778a.equals(((C0997Qh0) obj).f8778a);
    }

    public int hashCode() {
        return this.f8778a.hashCode();
    }

    public C0997Qh0(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        String packageName = remoteUserInfo.getPackageName();
        Objects.requireNonNull(packageName, "package shouldn't be null");
        if (!TextUtils.isEmpty(packageName)) {
            this.f8778a = new C1058Rh0(remoteUserInfo);
            return;
        }
        throw new IllegalArgumentException("packageName should be nonempty");
    }
}
