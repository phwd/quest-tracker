package defpackage;

import dalvik.system.PathClassLoader;

/* renamed from: KG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class KG1 extends PathClassLoader {
    public KG1(String str, ClassLoader classLoader) {
        super(str, classLoader);
    }

    @Override // java.lang.ClassLoader
    public final Class loadClass(String str, boolean z) {
        if (!str.startsWith("java.") && !str.startsWith("android.")) {
            try {
                return findClass(str);
            } catch (ClassNotFoundException unused) {
            }
        }
        return super.loadClass(str, z);
    }
}
