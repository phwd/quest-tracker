package dalvik.system;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import sun.misc.CompoundEnumeration;

public class BaseDexClassLoader extends ClassLoader {
    private static volatile Reporter reporter;
    private final DexPathList pathList;
    protected final ClassLoader[] sharedLibraryLoaders;

    public interface Reporter {
        void report(List list, List list2);
    }

    public BaseDexClassLoader(String str, File file, String str2, ClassLoader classLoader) {
        this(str, str2, classLoader, null, false);
    }

    public BaseDexClassLoader(String str, String str2, ClassLoader classLoader, ClassLoader[] classLoaderArr, boolean z) {
        super(classLoader);
        ClassLoader[] classLoaderArr2;
        if (classLoaderArr == null) {
            classLoaderArr2 = null;
        } else {
            classLoaderArr2 = (ClassLoader[]) Arrays.copyOf(classLoaderArr, classLoaderArr.length);
        }
        this.sharedLibraryLoaders = classLoaderArr2;
        this.pathList = new DexPathList(this, str, str2, null, z);
        if (reporter != null) {
            reportClassLoaderChain();
        }
    }

    private void reportClassLoaderChain() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(this);
        arrayList2.add(String.join(File.pathSeparator, this.pathList.getDexPaths()));
        ClassLoader parent = ClassLoader.getSystemClassLoader().getParent();
        ClassLoader parent2 = getParent();
        while (parent2 != null && parent2 != parent) {
            arrayList.add(parent2);
            if (parent2 instanceof BaseDexClassLoader) {
                arrayList2.add(String.join(File.pathSeparator, ((BaseDexClassLoader) parent2).pathList.getDexPaths()));
            } else {
                arrayList2.add(null);
            }
            parent2 = parent2.getParent();
        }
        reporter.report(arrayList, arrayList2);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Class findClass(String str) {
        ClassLoader[] classLoaderArr = this.sharedLibraryLoaders;
        if (classLoaderArr != null) {
            for (ClassLoader classLoader : classLoaderArr) {
                try {
                    return classLoader.loadClass(str);
                } catch (ClassNotFoundException unused) {
                }
            }
        }
        ArrayList<Throwable> arrayList = new ArrayList();
        Class findClass = this.pathList.findClass(str, arrayList);
        if (findClass != null) {
            return findClass;
        }
        ClassNotFoundException classNotFoundException = new ClassNotFoundException("Didn't find class \"" + str + "\" on path: " + this.pathList);
        for (Throwable th : arrayList) {
            classNotFoundException.addSuppressed(th);
        }
        throw classNotFoundException;
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public URL findResource(String str) {
        ClassLoader[] classLoaderArr = this.sharedLibraryLoaders;
        if (classLoaderArr != null) {
            for (ClassLoader classLoader : classLoaderArr) {
                URL resource = classLoader.getResource(str);
                if (resource != null) {
                    return resource;
                }
            }
        }
        return this.pathList.findResource(str);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Enumeration findResources(String str) {
        Enumeration findResources = this.pathList.findResources(str);
        ClassLoader[] classLoaderArr = this.sharedLibraryLoaders;
        if (classLoaderArr == null) {
            return findResources;
        }
        Enumeration[] enumerationArr = new Enumeration[(classLoaderArr.length + 1)];
        int i = 0;
        while (true) {
            ClassLoader[] classLoaderArr2 = this.sharedLibraryLoaders;
            if (i < classLoaderArr2.length) {
                try {
                    enumerationArr[i] = classLoaderArr2[i].getResources(str);
                } catch (IOException unused) {
                }
                i++;
            } else {
                enumerationArr[classLoaderArr2.length] = findResources;
                return new CompoundEnumeration(enumerationArr);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public synchronized Package getPackage(String str) {
        if (str != null) {
            if (!str.isEmpty()) {
                Package r0 = super.getPackage(str);
                if (r0 == null) {
                    r0 = definePackage(str, "Unknown", "0.0", "Unknown", "Unknown", "0.0", "Unknown", null);
                }
                return r0;
            }
        }
        return null;
    }

    public String toString() {
        return getClass().getName() + "[" + this.pathList + "]";
    }
}
