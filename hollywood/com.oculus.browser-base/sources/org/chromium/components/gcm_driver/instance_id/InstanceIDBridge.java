package org.chromium.components.gcm_driver.instance_id;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InstanceIDBridge {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f10848a;
    public final String b;
    public long c;
    public M20 d;

    public InstanceIDBridge(long j, String str) {
        this.b = str;
        this.c = j;
    }

    public static InstanceIDBridge create(long j, String str) {
        return new InstanceIDBridge(j, str);
    }

    public static boolean setBlockOnAsyncTasksForTesting(boolean z) {
        boolean z2 = f10848a;
        f10848a = z;
        return z2;
    }

    public final void deleteInstanceID(int i) {
        new I20(this, i).b();
    }

    public final void deleteToken(int i, String str, String str2) {
        new H20(this, str, str2, i).b();
    }

    public final void destroy() {
        this.c = 0;
    }

    public void getCreationTime(int i) {
        new F20(this, i).b();
    }

    public void getId(int i) {
        new E20(this, i).b();
    }

    public final void getToken(int i, String str, String str2, int i2) {
        new G20(this, str, i2, str2, i).b();
    }
}
