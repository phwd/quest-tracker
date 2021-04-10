package dalvik.system;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import sun.misc.CompoundEnumeration;

public class BaseDexClassLoader extends ClassLoader {
    private static volatile Reporter reporter = null;
    private final DexPathList pathList;
    protected final ClassLoader[] sharedLibraryLoaders;

    public interface Reporter {
        void report(List<ClassLoader> list, List<String> list2);
    }

    public BaseDexClassLoader(String dexPath, File optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        this(dexPath, librarySearchPath, parent, (ClassLoader[]) null, false);
    }

    public BaseDexClassLoader(String dexPath, File optimizedDirectory, String librarySearchPath, ClassLoader parent, boolean isTrusted) {
        this(dexPath, librarySearchPath, parent, (ClassLoader[]) null, isTrusted);
    }

    public BaseDexClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, ClassLoader[] libraries) {
        this(dexPath, librarySearchPath, parent, libraries, false);
    }

    public BaseDexClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, ClassLoader[] sharedLibraryLoaders2, boolean isTrusted) {
        super(parent);
        ClassLoader[] classLoaderArr;
        if (sharedLibraryLoaders2 == null) {
            classLoaderArr = null;
        } else {
            classLoaderArr = (ClassLoader[]) Arrays.copyOf(sharedLibraryLoaders2, sharedLibraryLoaders2.length);
        }
        this.sharedLibraryLoaders = classLoaderArr;
        this.pathList = new DexPathList(this, dexPath, librarySearchPath, null, isTrusted);
        if (reporter != null) {
            reportClassLoaderChain();
        }
    }

    private void reportClassLoaderChain() {
        ArrayList<ClassLoader> classLoadersChain = new ArrayList<>();
        ArrayList<String> classPaths = new ArrayList<>();
        classLoadersChain.add(this);
        classPaths.add(String.join(File.pathSeparator, this.pathList.getDexPaths()));
        ClassLoader bootClassLoader = ClassLoader.getSystemClassLoader().getParent();
        ClassLoader current = getParent();
        while (current != null && current != bootClassLoader) {
            classLoadersChain.add(current);
            if (current instanceof BaseDexClassLoader) {
                classPaths.add(String.join(File.pathSeparator, ((BaseDexClassLoader) current).pathList.getDexPaths()));
            } else {
                classPaths.add(null);
            }
            current = current.getParent();
        }
        reporter.report(classLoadersChain, classPaths);
    }

    public BaseDexClassLoader(ByteBuffer[] dexFiles, String librarySearchPath, ClassLoader parent) {
        super(parent);
        this.sharedLibraryLoaders = null;
        this.pathList = new DexPathList(this, librarySearchPath);
        this.pathList.initByteBufferDexPath(dexFiles);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Class<?> findClass(String name) throws ClassNotFoundException {
        ClassLoader[] classLoaderArr = this.sharedLibraryLoaders;
        if (classLoaderArr != null) {
            for (int i = 0; i < classLoaderArr.length; i++) {
                try {
                    return classLoaderArr[i].loadClass(name);
                } catch (ClassNotFoundException e) {
                }
            }
        }
        List<Throwable> suppressedExceptions = new ArrayList<>();
        Class c = this.pathList.findClass(name, suppressedExceptions);
        if (c != null) {
            return c;
        }
        ClassNotFoundException cnfe = new ClassNotFoundException("Didn't find class \"" + name + "\" on path: " + ((Object) this.pathList));
        for (Throwable t : suppressedExceptions) {
            cnfe.addSuppressed(t);
        }
        throw cnfe;
    }

    public void addDexPath(String dexPath) {
        addDexPath(dexPath, false);
    }

    public void addDexPath(String dexPath, boolean isTrusted) {
        this.pathList.addDexPath(dexPath, null, isTrusted);
    }

    public void addNativePath(Collection<String> libPaths) {
        this.pathList.addNativePath(libPaths);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public URL findResource(String name) {
        ClassLoader[] classLoaderArr = this.sharedLibraryLoaders;
        if (classLoaderArr != null) {
            for (ClassLoader loader : classLoaderArr) {
                URL url = loader.getResource(name);
                if (url != null) {
                    return url;
                }
            }
        }
        return this.pathList.findResource(name);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Enumeration<URL> findResources(String name) {
        Enumeration<URL> myResources = this.pathList.findResources(name);
        ClassLoader[] classLoaderArr = this.sharedLibraryLoaders;
        if (classLoaderArr == null) {
            return myResources;
        }
        Enumeration<URL>[] tmp = new Enumeration[(classLoaderArr.length + 1)];
        int i = 0;
        while (true) {
            ClassLoader[] classLoaderArr2 = this.sharedLibraryLoaders;
            if (i < classLoaderArr2.length) {
                try {
                    tmp[i] = classLoaderArr2[i].getResources(name);
                } catch (IOException e) {
                }
                i++;
            } else {
                tmp[classLoaderArr2.length] = myResources;
                return new CompoundEnumeration(tmp);
            }
        }
    }

    @Override // java.lang.ClassLoader
    public String findLibrary(String name) {
        return this.pathList.findLibrary(name);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public synchronized Package getPackage(String name) {
        if (name != null) {
            if (!name.isEmpty()) {
                Package pack = super.getPackage(name);
                if (pack == null) {
                    pack = definePackage(name, "Unknown", "0.0", "Unknown", "Unknown", "0.0", "Unknown", null);
                }
                return pack;
            }
        }
        return null;
    }

    public String getLdLibraryPath() {
        StringBuilder result = new StringBuilder();
        for (File directory : this.pathList.getNativeLibraryDirectories()) {
            if (result.length() > 0) {
                result.append(':');
            }
            result.append((Object) directory);
        }
        return result.toString();
    }

    public String toString() {
        return getClass().getName() + "[" + ((Object) this.pathList) + "]";
    }

    public static void setReporter(Reporter newReporter) {
        reporter = newReporter;
    }

    public static Reporter getReporter() {
        return reporter;
    }
}
