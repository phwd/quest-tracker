package dalvik.system;

import android.system.ErrnoException;
import android.system.OsConstants;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import libcore.io.ClassPathURLStreamHandler;
import libcore.io.IoUtils;
import libcore.io.Libcore;

public final class DexPathList {
    private static final String DEX_SUFFIX = ".dex";
    private static final String zipSeparator = "!/";
    private final ClassLoader definingContext;
    private Element[] dexElements;
    private IOException[] dexElementsSuppressedExceptions;
    private final List<File> nativeLibraryDirectories;
    NativeLibraryElement[] nativeLibraryPathElements;
    private final List<File> systemNativeLibraryDirectories;

    private List<File> getAllNativeLibraryDirectories() {
        List<File> allNativeLibraryDirectories = new ArrayList<>(this.nativeLibraryDirectories);
        allNativeLibraryDirectories.addAll(this.systemNativeLibraryDirectories);
        return allNativeLibraryDirectories;
    }

    public DexPathList(ClassLoader definingContext2, String librarySearchPath) {
        if (definingContext2 != null) {
            this.definingContext = definingContext2;
            this.nativeLibraryDirectories = splitPaths(librarySearchPath, false);
            this.systemNativeLibraryDirectories = splitPaths(System.getProperty("java.library.path"), true);
            this.nativeLibraryPathElements = makePathElements(getAllNativeLibraryDirectories());
            return;
        }
        throw new NullPointerException("definingContext == null");
    }

    public DexPathList(ClassLoader definingContext2, String dexPath, String librarySearchPath, File optimizedDirectory) {
        this(definingContext2, dexPath, librarySearchPath, optimizedDirectory, false);
    }

    DexPathList(ClassLoader definingContext2, String dexPath, String librarySearchPath, File optimizedDirectory, boolean isTrusted) {
        if (definingContext2 == null) {
            throw new NullPointerException("definingContext == null");
        } else if (dexPath != null) {
            if (optimizedDirectory != null) {
                if (!optimizedDirectory.exists()) {
                    throw new IllegalArgumentException("optimizedDirectory doesn't exist: " + ((Object) optimizedDirectory));
                } else if (!optimizedDirectory.canRead() || !optimizedDirectory.canWrite()) {
                    throw new IllegalArgumentException("optimizedDirectory not readable/writable: " + ((Object) optimizedDirectory));
                }
            }
            this.definingContext = definingContext2;
            ArrayList<IOException> suppressedExceptions = new ArrayList<>();
            this.dexElements = makeDexElements(splitDexPath(dexPath), optimizedDirectory, suppressedExceptions, definingContext2, isTrusted);
            this.nativeLibraryDirectories = splitPaths(librarySearchPath, false);
            this.systemNativeLibraryDirectories = splitPaths(System.getProperty("java.library.path"), true);
            this.nativeLibraryPathElements = makePathElements(getAllNativeLibraryDirectories());
            if (suppressedExceptions.size() > 0) {
                this.dexElementsSuppressedExceptions = (IOException[]) suppressedExceptions.toArray(new IOException[suppressedExceptions.size()]);
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

    public List<File> getNativeLibraryDirectories() {
        return this.nativeLibraryDirectories;
    }

    public void addDexPath(String dexPath, File optimizedDirectory) {
        addDexPath(dexPath, optimizedDirectory, false);
    }

    public void addDexPath(String dexPath, File optimizedDirectory, boolean isTrusted) {
        List<IOException> suppressedExceptionList = new ArrayList<>();
        Element[] newElements = makeDexElements(splitDexPath(dexPath), optimizedDirectory, suppressedExceptionList, this.definingContext, isTrusted);
        if (newElements != null && newElements.length > 0) {
            Element[] oldElements = this.dexElements;
            this.dexElements = new Element[(oldElements.length + newElements.length)];
            System.arraycopy(oldElements, 0, this.dexElements, 0, oldElements.length);
            System.arraycopy(newElements, 0, this.dexElements, oldElements.length, newElements.length);
        }
        if (suppressedExceptionList.size() > 0) {
            IOException[] newSuppressedExceptions = (IOException[]) suppressedExceptionList.toArray(new IOException[suppressedExceptionList.size()]);
            if (this.dexElementsSuppressedExceptions != null) {
                IOException[] oldSuppressedExceptions = this.dexElementsSuppressedExceptions;
                this.dexElementsSuppressedExceptions = new IOException[(oldSuppressedExceptions.length + newSuppressedExceptions.length)];
                System.arraycopy(oldSuppressedExceptions, 0, this.dexElementsSuppressedExceptions, 0, oldSuppressedExceptions.length);
                System.arraycopy(newSuppressedExceptions, 0, this.dexElementsSuppressedExceptions, oldSuppressedExceptions.length, newSuppressedExceptions.length);
                return;
            }
            this.dexElementsSuppressedExceptions = newSuppressedExceptions;
        }
    }

    /* access modifiers changed from: package-private */
    public void initByteBufferDexPath(ByteBuffer[] dexFiles) {
        if (dexFiles == null) {
            throw new NullPointerException("dexFiles == null");
        } else if (Arrays.stream(dexFiles).anyMatch($$Lambda$DexPathList$_CyMypnZmV6ArWiPOPB4EkAIeUc.INSTANCE)) {
            throw new NullPointerException("dexFiles contains a null Buffer!");
        } else if (this.dexElements == null && this.dexElementsSuppressedExceptions == null) {
            List<IOException> suppressedExceptions = new ArrayList<>();
            try {
                DexFile dex = new DexFile(dexFiles, this.definingContext, (Element[]) null);
                String classLoaderContext = dex.isBackedByOatFile() ? null : DexFile.getClassLoaderContext(this.definingContext, null);
                this.dexElements = new Element[]{new Element(dex)};
                if (classLoaderContext != null) {
                    dex.verifyInBackground(this.definingContext, classLoaderContext);
                }
            } catch (IOException suppressed) {
                System.logE("Unable to load dex files", suppressed);
                suppressedExceptions.add(suppressed);
                this.dexElements = new Element[0];
            }
            if (suppressedExceptions.size() > 0) {
                this.dexElementsSuppressedExceptions = (IOException[]) suppressedExceptions.toArray(new IOException[suppressedExceptions.size()]);
            }
        } else {
            throw new IllegalStateException("Should only be called once");
        }
    }

    static /* synthetic */ boolean lambda$initByteBufferDexPath$0(ByteBuffer v) {
        return v == null;
    }

    private static List<File> splitDexPath(String path) {
        return splitPaths(path, false);
    }

    private static List<File> splitPaths(String searchPath, boolean directoriesOnly) {
        List<File> result = new ArrayList<>();
        if (searchPath != null) {
            String[] split = searchPath.split(File.pathSeparator);
            for (String path : split) {
                if (directoriesOnly) {
                    try {
                        if (!OsConstants.S_ISDIR(Libcore.os.stat(path).st_mode)) {
                        }
                    } catch (ErrnoException e) {
                    }
                }
                result.add(new File(path));
            }
        }
        return result;
    }

    public static Element[] makeInMemoryDexElements(ByteBuffer[] dexFiles, List<IOException> suppressedExceptions) {
        IOException suppressed;
        Element[] elements = new Element[dexFiles.length];
        int elementPos = 0;
        for (ByteBuffer buf : dexFiles) {
            try {
                int elementPos2 = elementPos + 1;
                try {
                    elements[elementPos] = new Element(new DexFile(new ByteBuffer[]{buf}, (ClassLoader) null, (Element[]) null));
                    elementPos = elementPos2;
                } catch (IOException e) {
                    suppressed = e;
                    elementPos = elementPos2;
                    System.logE("Unable to load dex file: " + ((Object) buf), suppressed);
                    suppressedExceptions.add(suppressed);
                }
            } catch (IOException e2) {
                suppressed = e2;
                System.logE("Unable to load dex file: " + ((Object) buf), suppressed);
                suppressedExceptions.add(suppressed);
            }
        }
        if (elementPos != elements.length) {
            return (Element[]) Arrays.copyOf(elements, elementPos);
        }
        return elements;
    }

    private static Element[] makeDexElements(List<File> files, File optimizedDirectory, List<IOException> suppressedExceptions, ClassLoader loader) {
        return makeDexElements(files, optimizedDirectory, suppressedExceptions, loader, false);
    }

    private static Element[] makeDexElements(List<File> files, File optimizedDirectory, List<IOException> suppressedExceptions, ClassLoader loader, boolean isTrusted) {
        int elementsPos;
        IOException suppressed;
        Element[] elements = new Element[files.size()];
        int elementsPos2 = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                elements[elementsPos2] = new Element(file);
                elementsPos2++;
            } else if (file.isFile()) {
                DexFile dex = null;
                if (file.getName().endsWith(DEX_SUFFIX)) {
                    try {
                        dex = loadDexFile(file, optimizedDirectory, loader, elements);
                        if (dex != null) {
                            elementsPos = elementsPos2 + 1;
                            try {
                                elements[elementsPos2] = new Element(dex, null);
                                elementsPos2 = elementsPos;
                            } catch (IOException e) {
                                suppressed = e;
                                System.logE("Unable to load dex file: " + ((Object) file), suppressed);
                                suppressedExceptions.add(suppressed);
                                dex.setTrusted();
                                elementsPos2 = elementsPos;
                            }
                        }
                        elementsPos = elementsPos2;
                    } catch (IOException e2) {
                        elementsPos = elementsPos2;
                        suppressed = e2;
                        System.logE("Unable to load dex file: " + ((Object) file), suppressed);
                        suppressedExceptions.add(suppressed);
                        dex.setTrusted();
                        elementsPos2 = elementsPos;
                    }
                } else {
                    try {
                        dex = loadDexFile(file, optimizedDirectory, loader, elements);
                    } catch (IOException suppressed2) {
                        suppressedExceptions.add(suppressed2);
                    }
                    if (dex == null) {
                        elementsPos = elementsPos2 + 1;
                        elements[elementsPos2] = new Element(file);
                    } else {
                        elementsPos = elementsPos2 + 1;
                        elements[elementsPos2] = new Element(dex, file);
                    }
                }
                if (dex != null && isTrusted) {
                    dex.setTrusted();
                }
                elementsPos2 = elementsPos;
            } else {
                System.logW("ClassLoader referenced unknown path: " + ((Object) file));
            }
        }
        if (elementsPos2 != elements.length) {
            return (Element[]) Arrays.copyOf(elements, elementsPos2);
        }
        return elements;
    }

    private static DexFile loadDexFile(File file, File optimizedDirectory, ClassLoader loader, Element[] elements) throws IOException {
        if (optimizedDirectory == null) {
            return new DexFile(file, loader, elements);
        }
        return DexFile.loadDex(file.getPath(), optimizedPathFor(file, optimizedDirectory), 0, loader, elements);
    }

    private static String optimizedPathFor(File path, File optimizedDirectory) {
        String fileName = path.getName();
        if (!fileName.endsWith(DEX_SUFFIX)) {
            int lastDot = fileName.lastIndexOf(".");
            if (lastDot < 0) {
                fileName = fileName + DEX_SUFFIX;
            } else {
                StringBuilder sb = new StringBuilder(lastDot + 4);
                sb.append((CharSequence) fileName, 0, lastDot);
                sb.append(DEX_SUFFIX);
                fileName = sb.toString();
            }
        }
        return new File(optimizedDirectory, fileName).getPath();
    }

    private static Element[] makePathElements(List<File> files, File optimizedDirectory, List<IOException> suppressedExceptions) {
        return makeDexElements(files, optimizedDirectory, suppressedExceptions, null);
    }

    private static NativeLibraryElement[] makePathElements(List<File> files) {
        NativeLibraryElement[] elements = new NativeLibraryElement[files.size()];
        int elementsPos = 0;
        for (File file : files) {
            String path = file.getPath();
            if (path.contains(zipSeparator)) {
                String[] split = path.split(zipSeparator, 2);
                elements[elementsPos] = new NativeLibraryElement(new File(split[0]), split[1]);
                elementsPos++;
            } else if (file.isDirectory()) {
                elements[elementsPos] = new NativeLibraryElement(file);
                elementsPos++;
            }
        }
        if (elementsPos != elements.length) {
            return (NativeLibraryElement[]) Arrays.copyOf(elements, elementsPos);
        }
        return elements;
    }

    public Class<?> findClass(String name, List<Throwable> suppressed) {
        for (Element element : this.dexElements) {
            Class<?> clazz = element.findClass(name, this.definingContext, suppressed);
            if (clazz != null) {
                return clazz;
            }
        }
        IOException[] iOExceptionArr = this.dexElementsSuppressedExceptions;
        if (iOExceptionArr == null) {
            return null;
        }
        suppressed.addAll(Arrays.asList(iOExceptionArr));
        return null;
    }

    public URL findResource(String name) {
        for (Element element : this.dexElements) {
            URL url = element.findResource(name);
            if (url != null) {
                return url;
            }
        }
        return null;
    }

    public Enumeration<URL> findResources(String name) {
        ArrayList<URL> result = new ArrayList<>();
        for (Element element : this.dexElements) {
            URL url = element.findResource(name);
            if (url != null) {
                result.add(url);
            }
        }
        return Collections.enumeration(result);
    }

    public String findLibrary(String libraryName) {
        String fileName = System.mapLibraryName(libraryName);
        for (NativeLibraryElement element : this.nativeLibraryPathElements) {
            String path = element.findNativeLibrary(fileName);
            if (path != null) {
                return path;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public List<String> getDexPaths() {
        List<String> dexPaths = new ArrayList<>();
        for (Element e : this.dexElements) {
            String dexPath = e.getDexPath();
            if (dexPath != null) {
                dexPaths.add(dexPath);
            }
        }
        return dexPaths;
    }

    public void addNativePath(Collection<String> libPaths) {
        if (!libPaths.isEmpty()) {
            List<File> libFiles = new ArrayList<>(libPaths.size());
            for (String path : libPaths) {
                libFiles.add(new File(path));
            }
            ArrayList<NativeLibraryElement> newPaths = new ArrayList<>(this.nativeLibraryPathElements.length + libPaths.size());
            newPaths.addAll(Arrays.asList(this.nativeLibraryPathElements));
            NativeLibraryElement[] makePathElements = makePathElements(libFiles);
            for (NativeLibraryElement element : makePathElements) {
                if (!newPaths.contains(element)) {
                    newPaths.add(element);
                }
            }
            this.nativeLibraryPathElements = (NativeLibraryElement[]) newPaths.toArray(new NativeLibraryElement[newPaths.size()]);
        }
    }

    /* access modifiers changed from: package-private */
    public static class Element {
        private final DexFile dexFile;
        private boolean initialized;
        private final File path;
        private final Boolean pathIsDirectory;
        private ClassPathURLStreamHandler urlHandler;

        public Element(DexFile dexFile2, File dexZipPath) {
            if (dexFile2 == null && dexZipPath == null) {
                throw new NullPointerException("Either dexFile or path must be non-null");
            }
            this.dexFile = dexFile2;
            this.path = dexZipPath;
            File file = this.path;
            this.pathIsDirectory = file == null ? null : Boolean.valueOf(file.isDirectory());
        }

        public Element(DexFile dexFile2) {
            this(dexFile2, null);
        }

        public Element(File path2) {
            this(null, path2);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        @Deprecated
        public Element(File dir, boolean isDirectory, File zip, DexFile dexFile2) {
            this(dir != null ? null : dexFile2, dir != null ? dir : zip);
            System.err.println("Warning: Using deprecated Element constructor. Do not use internal APIs, this constructor will be removed in the future.");
            if (dir != null && (zip != null || dexFile2 != null)) {
                throw new IllegalArgumentException("Using dir and zip|dexFile no longer supported.");
            } else if (!isDirectory) {
            } else {
                if (zip != null || dexFile2 != null) {
                    throw new IllegalArgumentException("Unsupported argument combination.");
                }
            }
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
                sb.append((Object) this.path);
                sb.append("\"");
                return sb.toString();
            } else if (this.path == null) {
                return "dex file \"" + ((Object) this.dexFile) + "\"";
            } else {
                return str + ((Object) this.path) + "\"";
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
                } catch (IOException ioe) {
                    System.logE("Unable to open zip file: " + ((Object) this.path), ioe);
                    this.urlHandler = null;
                }
                this.initialized = true;
            }
        }

        public Class<?> findClass(String name, ClassLoader definingContext, List<Throwable> suppressed) {
            DexFile dexFile2 = this.dexFile;
            if (dexFile2 != null) {
                return dexFile2.loadClassBinaryName(name, definingContext, suppressed);
            }
            return null;
        }

        public URL findResource(String name) {
            maybeInit();
            ClassPathURLStreamHandler classPathURLStreamHandler = this.urlHandler;
            if (classPathURLStreamHandler != null) {
                return classPathURLStreamHandler.getEntryUrlOrNull(name);
            }
            File file = this.path;
            if (file == null || !file.isDirectory()) {
                return null;
            }
            File resourceFile = new File(this.path, name);
            if (!resourceFile.exists()) {
                return null;
            }
            try {
                return resourceFile.toURI().toURL();
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class NativeLibraryElement {
        private boolean initialized;
        private final File path;
        private ClassPathURLStreamHandler urlHandler;
        private final String zipDir;

        public NativeLibraryElement(File dir) {
            this.path = dir;
            this.zipDir = null;
        }

        public NativeLibraryElement(File zip, String zipDir2) {
            this.path = zip;
            this.zipDir = zipDir2;
            if (zipDir2 == null) {
                throw new IllegalArgumentException();
            }
        }

        public String toString() {
            String str;
            if (this.zipDir == null) {
                return "directory \"" + ((Object) this.path) + "\"";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("zip file \"");
            sb.append((Object) this.path);
            sb.append("\"");
            if (!this.zipDir.isEmpty()) {
                str = ", dir \"" + this.zipDir + "\"";
            } else {
                str = "";
            }
            sb.append(str);
            return sb.toString();
        }

        public synchronized void maybeInit() {
            if (!this.initialized) {
                if (this.zipDir == null) {
                    this.initialized = true;
                    return;
                }
                try {
                    this.urlHandler = new ClassPathURLStreamHandler(this.path.getPath());
                } catch (IOException ioe) {
                    System.logE("Unable to open zip file: " + ((Object) this.path), ioe);
                    this.urlHandler = null;
                }
                this.initialized = true;
            }
        }

        public String findNativeLibrary(String name) {
            maybeInit();
            if (this.zipDir == null) {
                String entryPath = new File(this.path, name).getPath();
                if (IoUtils.canOpenReadOnly(entryPath)) {
                    return entryPath;
                }
                return null;
            } else if (this.urlHandler == null) {
                return null;
            } else {
                String entryName = this.zipDir + '/' + name;
                if (!this.urlHandler.isEntryStored(entryName)) {
                    return null;
                }
                return this.path.getPath() + DexPathList.zipSeparator + entryName;
            }
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof NativeLibraryElement)) {
                return false;
            }
            NativeLibraryElement that = (NativeLibraryElement) o;
            if (!Objects.equals(this.path, that.path) || !Objects.equals(this.zipDir, that.zipDir)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(this.path, this.zipDir);
        }
    }
}
