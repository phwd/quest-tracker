package X;

import com.oculus.horizon.linkedaccounts.dumper.LinkedAccountsDumperPlugin;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

/* renamed from: X.1Ss  reason: invalid class name */
public final class AnonymousClass1Ss {
    public static Method A00;
    public static Method A01;
    public static Object A02;
    public static Object A03;
    public static Method A04;
    public static Method A05;
    public static Method A06;
    public static Method A07;
    public static Method A08;
    public static final boolean A09;

    static {
        boolean z = false;
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            Class<?> loadClass = contextClassLoader.loadClass("java.nio.file.Files");
            Class<?> loadClass2 = contextClassLoader.loadClass("java.nio.file.Path");
            Class<?> loadClass3 = contextClassLoader.loadClass("java.nio.file.attribute.FileAttribute");
            Class<?> loadClass4 = contextClassLoader.loadClass("java.nio.file.LinkOption");
            A00 = loadClass.getMethod("isSymbolicLink", loadClass2);
            A05 = loadClass.getMethod(LinkedAccountsDumperPlugin.COMMAND_DELETE, loadClass2);
            A07 = loadClass.getMethod("readSymbolicLink", loadClass2);
            Object newInstance = Array.newInstance(loadClass3, 0);
            A02 = newInstance;
            A04 = loadClass.getMethod("createSymbolicLink", loadClass2, loadClass2, newInstance.getClass());
            Object newInstance2 = Array.newInstance(loadClass4, 0);
            A03 = newInstance2;
            A06 = loadClass.getMethod("exists", loadClass2, newInstance2.getClass());
            A01 = File.class.getMethod("toPath", new Class[0]);
            A08 = loadClass2.getMethod("toFile", new Class[0]);
            z = true;
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
        }
        A09 = z;
    }
}
