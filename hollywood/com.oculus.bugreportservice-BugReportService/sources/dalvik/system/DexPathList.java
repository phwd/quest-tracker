package dalvik.system;

import android.system.ErrnoException;
import android.system.OsConstants;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import libcore.io.ClassPathURLStreamHandler;
import libcore.io.Libcore;

public final class DexPathList {
    private final ClassLoader definingContext;
    private Element[] dexElements;
    private IOException[] dexElementsSuppressedExceptions;
    private final List nativeLibraryDirectories;
    NativeLibraryElement[] nativeLibraryPathElements;
    private final List systemNativeLibraryDirectories;

    private List getAllNativeLibraryDirectories() {
        ArrayList arrayList = new ArrayList(this.nativeLibraryDirectories);
        arrayList.addAll(this.systemNativeLibraryDirectories);
        return arrayList;
    }

    DexPathList(ClassLoader classLoader, String str, String str2, File file, boolean z) {
        if (classLoader == null) {
            throw new NullPointerException("definingContext == null");
        } else if (str != null) {
            if (file != null) {
                if (!file.exists()) {
                    throw new IllegalArgumentException("optimizedDirectory doesn't exist: " + file);
                } else if (!file.canRead() || !file.canWrite()) {
                    throw new IllegalArgumentException("optimizedDirectory not readable/writable: " + file);
                }
            }
            this.definingContext = classLoader;
            ArrayList arrayList = new ArrayList();
            this.dexElements = makeDexElements(splitDexPath(str), file, arrayList, classLoader, z);
            this.nativeLibraryDirectories = splitPaths(str2, false);
            this.systemNativeLibraryDirectories = splitPaths(System.getProperty("java.library.path"), true);
            this.nativeLibraryPathElements = makePathElements(getAllNativeLibraryDirectories());
            if (arrayList.size() > 0) {
                this.dexElementsSuppressedExceptions = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
            } else {
                this.dexElementsSuppressedExceptions = null;
            }
        } else {
            throw new NullPointerException("dexPath == null");
        }
    }

    public String toString() {
        return "DexPathList[" + Arrays.toString(this.dexElements) + ",nativeLibraryDirectories=" + Arrays.toString(getAllNativeLibraryDirectories().toArray()) + "]";
    }

    private static List splitDexPath(String str) {
        return splitPaths(str, false);
    }

    private static List splitPaths(String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            String[] split = str.split(File.pathSeparator);
            for (String str2 : split) {
                if (z) {
                    try {
                        if (!OsConstants.S_ISDIR(Libcore.os.stat(str2).st_mode)) {
                        }
                    } catch (ErrnoException unused) {
                    }
                }
                arrayList.add(new File(str2));
            }
        }
        return arrayList;
    }

    private static Element[] makeDexElements(List list, File file, List list2, ClassLoader classLoader, boolean z) {
        int i;
        DexFile dexFile;
        int i2;
        IOException e;
        Element[] elementArr = new Element[list.size()];
        Iterator it = list.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            File file2 = (File) it.next();
            if (file2.isDirectory()) {
                elementArr[i3] = new Element(file2);
                i3++;
            } else if (file2.isFile()) {
                DexFile dexFile2 = null;
                if (file2.getName().endsWith(".dex")) {
                    try {
                        dexFile = loadDexFile(file2, file, classLoader, elementArr);
                        if (dexFile != null) {
                            i2 = i3 + 1;
                            try {
                                elementArr[i3] = new Element(dexFile, null);
                                i3 = i2;
                            } catch (IOException e2) {
                                e = e2;
                                System.logE("Unable to load dex file: " + file2, e);
                                list2.add(e);
                                i = i2;
                                dexFile.setTrusted();
                                i3 = i;
                            }
                        }
                        i = i3;
                    } catch (IOException e3) {
                        i2 = i3;
                        e = e3;
                        dexFile = null;
                        System.logE("Unable to load dex file: " + file2, e);
                        list2.add(e);
                        i = i2;
                        dexFile.setTrusted();
                        i3 = i;
                    }
                } else {
                    try {
                        dexFile2 = loadDexFile(file2, file, classLoader, elementArr);
                    } catch (IOException e4) {
                        list2.add(e4);
                    }
                    dexFile = dexFile2;
                    if (dexFile == null) {
                        i = i3 + 1;
                        elementArr[i3] = new Element(file2);
                    } else {
                        i = i3 + 1;
                        elementArr[i3] = new Element(dexFile, file2);
                    }
                }
                if (dexFile != null && z) {
                    dexFile.setTrusted();
                }
                i3 = i;
            } else {
                System.logW("ClassLoader referenced unknown path: " + file2);
            }
        }
        return i3 != elementArr.length ? (Element[]) Arrays.copyOf(elementArr, i3) : elementArr;
    }

    private static DexFile loadDexFile(File file, File file2, ClassLoader classLoader, Element[] elementArr) {
        if (file2 == null) {
            return new DexFile(file, classLoader, elementArr);
        }
        return DexFile.loadDex(file.getPath(), optimizedPathFor(file, file2), 0, classLoader, elementArr);
    }

    private static String optimizedPathFor(File file, File file2) {
        String name = file.getName();
        if (!name.endsWith(".dex")) {
            int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf < 0) {
                name = name + ".dex";
            } else {
                StringBuilder sb = new StringBuilder(lastIndexOf + 4);
                sb.append((CharSequence) name, 0, lastIndexOf);
                sb.append(".dex");
                name = sb.toString();
            }
        }
        return new File(file2, name).getPath();
    }

    private static NativeLibraryElement[] makePathElements(List list) {
        NativeLibraryElement[] nativeLibraryElementArr = new NativeLibraryElement[list.size()];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            File file = (File) it.next();
            String path = file.getPath();
            if (path.contains("!/")) {
                String[] split = path.split("!/", 2);
                nativeLibraryElementArr[i] = new NativeLibraryElement(new File(split[0]), split[1]);
                i++;
            } else if (file.isDirectory()) {
                nativeLibraryElementArr[i] = new NativeLibraryElement(file);
                i++;
            }
        }
        return i != nativeLibraryElementArr.length ? (NativeLibraryElement[]) Arrays.copyOf(nativeLibraryElementArr, i) : nativeLibraryElementArr;
    }

    public Class findClass(String str, List list) {
        for (Element element : this.dexElements) {
            Class findClass = element.findClass(str, this.definingContext, list);
            if (findClass != null) {
                return findClass;
            }
        }
        IOException[] iOExceptionArr = this.dexElementsSuppressedExceptions;
        if (iOExceptionArr == null) {
            return null;
        }
        list.addAll(Arrays.asList(iOExceptionArr));
        return null;
    }

    public URL findResource(String str) {
        for (Element element : this.dexElements) {
            URL findResource = element.findResource(str);
            if (findResource != null) {
                return findResource;
            }
        }
        return null;
    }

    public Enumeration findResources(String str) {
        ArrayList arrayList = new ArrayList();
        for (Element element : this.dexElements) {
            URL findResource = element.findResource(str);
            if (findResource != null) {
                arrayList.add(findResource);
            }
        }
        return Collections.enumeration(arrayList);
    }

    /* access modifiers changed from: package-private */
    public List getDexPaths() {
        ArrayList arrayList = new ArrayList();
        for (Element element : this.dexElements) {
            String dexPath = element.getDexPath();
            if (dexPath != null) {
                arrayList.add(dexPath);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public static class Element {
        private final DexFile dexFile;
        private boolean initialized;
        private final File path;
        private final Boolean pathIsDirectory;
        private ClassPathURLStreamHandler urlHandler;

        public Element(DexFile dexFile2, File file) {
            if (dexFile2 == null && file == null) {
                throw new NullPointerException("Either dexFile or path must be non-null");
            }
            this.dexFile = dexFile2;
            this.path = file;
            File file2 = this.path;
            this.pathIsDirectory = file2 == null ? null : Boolean.valueOf(file2.isDirectory());
        }

        public Element(File file) {
            this(null, file);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private String getDexPath() {
            File file = this.path;
            if (file == null) {
                DexFile dexFile2 = this.dexFile;
                if (dexFile2 != null) {
                    return dexFile2.getName();
                }
                return null;
            } else if (file.isDirectory()) {
                return null;
            } else {
                return this.path.getAbsolutePath();
            }
        }

        public String toString() {
            String str = "zip file \"";
            if (this.dexFile == null) {
                StringBuilder sb = new StringBuilder();
                if (this.pathIsDirectory.booleanValue()) {
                    str = "directory \"";
                }
                sb.append(str);
                sb.append(this.path);
                sb.append("\"");
                return sb.toString();
            } else if (this.path == null) {
                return "dex file \"" + this.dexFile + "\"";
            } else {
                return str + this.path + "\"";
            }
        }

        public synchronized void maybeInit() {
            if (!this.initialized) {
                if (this.path == null || this.pathIsDirectory.booleanValue()) {
                    this.initialized = true;
                    return;
                }
                try {
                    this.urlHandler = new ClassPathURLStreamHandler(this.path.getPath());
                } catch (IOException e) {
                    System.logE("Unable to open zip file: " + this.path, e);
                    this.urlHandler = null;
                }
                this.initialized = true;
            }
        }

        public Class findClass(String str, ClassLoader classLoader, List list) {
            DexFile dexFile2 = this.dexFile;
            if (dexFile2 != null) {
                return dexFile2.loadClassBinaryName(str, classLoader, list);
            }
            return null;
        }

        public URL findResource(String str) {
            maybeInit();
            ClassPathURLStreamHandler classPathURLStreamHandler = this.urlHandler;
            if (classPathURLStreamHandler != null) {
                return classPathURLStreamHandler.getEntryUrlOrNull(str);
            }
            File file = this.path;
            if (file == null || !file.isDirectory()) {
                return null;
            }
            File file2 = new File(this.path, str);
            if (!file2.exists()) {
                return null;
            }
            try {
                return file2.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class NativeLibraryElement {
        private final File path;
        private final String zipDir;

        public NativeLibraryElement(File file) {
            this.path = file;
            this.zipDir = null;
        }

        public NativeLibraryElement(File file, String str) {
            this.path = file;
            this.zipDir = str;
            if (str == null) {
                throw new IllegalArgumentException();
            }
        }

        public String toString() {
            String str;
            if (this.zipDir == null) {
                return "directory \"" + this.path + "\"";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("zip file \"");
            sb.append(this.path);
            sb.append("\"");
            if (!this.zipDir.isEmpty()) {
                str = ", dir \"" + this.zipDir + "\"";
            } else {
                str = "";
            }
            sb.append(str);
            return sb.toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof NativeLibraryElement)) {
                return false;
            }
            NativeLibraryElement nativeLibraryElement = (NativeLibraryElement) obj;
            return Objects.equals(this.path, nativeLibraryElement.path) && Objects.equals(this.zipDir, nativeLibraryElement.zipDir);
        }

        public int hashCode() {
            return Objects.hash(this.path, this.zipDir);
        }
    }
}
