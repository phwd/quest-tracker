package dalvik.system;

import android.system.ErrnoException;
import dalvik.system.DexPathList;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import libcore.io.Libcore;

public final class DexFile {
    private Object mCookie;
    private final String mFileName;
    private Object mInternalCookie;

    private static native Class defineClassNative(String str, ClassLoader classLoader, Object obj, DexFile dexFile);

    private static native Object openDexFileNative(String str, String str2, int i, ClassLoader classLoader, DexPathList.Element[] elementArr);

    private static native void setTrusted(Object obj);

    DexFile(File file, ClassLoader classLoader, DexPathList.Element[] elementArr) {
        this(file.getPath(), classLoader, elementArr);
    }

    DexFile(String str, ClassLoader classLoader, DexPathList.Element[] elementArr) {
        this.mCookie = openDexFile(str, null, 0, classLoader, elementArr);
        this.mInternalCookie = this.mCookie;
        this.mFileName = str;
    }

    private DexFile(String str, String str2, int i, ClassLoader classLoader, DexPathList.Element[] elementArr) {
        if (str2 != null) {
            try {
                String parent = new File(str2).getParent();
                if (Libcore.os.getuid() != Libcore.os.stat(parent).st_uid) {
                    throw new IllegalArgumentException("Optimized data directory " + parent + " is not owned by the current user. Shared storage cannot protect your application from code injection attacks.");
                }
            } catch (ErrnoException unused) {
            }
        }
        this.mCookie = openDexFile(str, str2, i, classLoader, elementArr);
        this.mInternalCookie = this.mCookie;
        this.mFileName = str;
    }

    static DexFile loadDex(String str, String str2, int i, ClassLoader classLoader, DexPathList.Element[] elementArr) {
        return new DexFile(str, str2, i, classLoader, elementArr);
    }

    public String getName() {
        return this.mFileName;
    }

    public String toString() {
        if (this.mFileName != null) {
            return getName();
        }
        return "InMemoryDexFile[cookie=" + Arrays.toString((long[]) this.mCookie) + "]";
    }

    public Class loadClassBinaryName(String str, ClassLoader classLoader, List list) {
        return defineClass(str, classLoader, this.mCookie, this, list);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Class defineClass(java.lang.String r0, java.lang.ClassLoader r1, java.lang.Object r2, dalvik.system.DexFile r3, java.util.List r4) {
        /*
            java.lang.Class r0 = defineClassNative(r0, r1, r2, r3)     // Catch:{ NoClassDefFoundError -> 0x000c, ClassNotFoundException -> 0x0005 }
            goto L_0x0013
        L_0x0005:
            r0 = move-exception
            if (r4 == 0) goto L_0x0012
            r4.add(r0)
            goto L_0x0012
        L_0x000c:
            r0 = move-exception
            if (r4 == 0) goto L_0x0012
            r4.add(r0)
        L_0x0012:
            r0 = 0
        L_0x0013:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: dalvik.system.DexFile.defineClass(java.lang.String, java.lang.ClassLoader, java.lang.Object, dalvik.system.DexFile, java.util.List):java.lang.Class");
    }

    private static Object openDexFile(String str, String str2, int i, ClassLoader classLoader, DexPathList.Element[] elementArr) {
        String str3;
        String absolutePath = new File(str).getAbsolutePath();
        if (str2 == null) {
            str3 = null;
        } else {
            str3 = new File(str2).getAbsolutePath();
        }
        return openDexFileNative(absolutePath, str3, i, classLoader, elementArr);
    }

    /* access modifiers changed from: package-private */
    public void setTrusted() {
        setTrusted(this.mCookie);
    }
}
