package java.nio.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileTreeWalker;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.spi.FileSystemProvider;
import java.nio.file.spi.FileTypeDetector;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.Spliterators;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import sun.nio.fs.DefaultFileTypeDetector;

public final class Files {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int BUFFER_SIZE = 8192;
    private static final int MAX_BUFFER_SIZE = 2147483639;

    private Files() {
    }

    private static FileSystemProvider provider(Path path) {
        return path.getFileSystem().provider();
    }

    private static Runnable asUncheckedRunnable(Closeable c) {
        return new Runnable() {
            /* class java.nio.file.$$Lambda$Files$powUktDqIsUPxzmcqaqk0NiO6iA */

            @Override // java.lang.Runnable
            public final void run() {
                Files.lambda$asUncheckedRunnable$0(Closeable.this);
            }
        };
    }

    static /* synthetic */ void lambda$asUncheckedRunnable$0(Closeable c) {
        try {
            c.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static InputStream newInputStream(Path path, OpenOption... options) throws IOException {
        return provider(path).newInputStream(path, options);
    }

    public static OutputStream newOutputStream(Path path, OpenOption... options) throws IOException {
        return provider(path).newOutputStream(path, options);
    }

    public static SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException {
        return provider(path).newByteChannel(path, options, attrs);
    }

    public static SeekableByteChannel newByteChannel(Path path, OpenOption... options) throws IOException {
        Set<OpenOption> set = new HashSet<>(options.length);
        Collections.addAll(set, options);
        return newByteChannel(path, set, new FileAttribute[0]);
    }

    /* access modifiers changed from: private */
    public static class AcceptAllFilter implements DirectoryStream.Filter<Path> {
        static final AcceptAllFilter FILTER = new AcceptAllFilter();

        private AcceptAllFilter() {
        }

        public boolean accept(Path entry) {
            return true;
        }
    }

    public static DirectoryStream<Path> newDirectoryStream(Path dir) throws IOException {
        return provider(dir).newDirectoryStream(dir, AcceptAllFilter.FILTER);
    }

    public static DirectoryStream<Path> newDirectoryStream(Path dir, String glob) throws IOException {
        if (glob.equals("*")) {
            return newDirectoryStream(dir);
        }
        FileSystem fs = dir.getFileSystem();
        final PathMatcher matcher = fs.getPathMatcher("glob:" + glob);
        return fs.provider().newDirectoryStream(dir, new DirectoryStream.Filter<Path>() {
            /* class java.nio.file.Files.AnonymousClass1 */

            public boolean accept(Path entry) {
                return PathMatcher.this.matches(entry.getFileName());
            }
        });
    }

    public static DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) throws IOException {
        return provider(dir).newDirectoryStream(dir, filter);
    }

    public static Path createFile(Path path, FileAttribute<?>... attrs) throws IOException {
        newByteChannel(path, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE), attrs).close();
        return path;
    }

    public static Path createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException {
        provider(dir).createDirectory(dir, attrs);
        return dir;
    }

    public static Path createDirectories(Path dir, FileAttribute<?>... attrs) throws IOException {
        try {
            createAndCheckIsDirectory(dir, attrs);
            return dir;
        } catch (FileAlreadyExistsException x) {
            throw x;
        } catch (IOException e) {
            SecurityException se = null;
            try {
                dir = dir.toAbsolutePath();
            } catch (SecurityException x2) {
                se = x2;
            }
            Path parent = dir.getParent();
            while (parent != null) {
                try {
                    provider(parent).checkAccess(parent, new AccessMode[0]);
                    break;
                } catch (NoSuchFileException e2) {
                    parent = parent.getParent();
                }
            }
            if (parent != null) {
                Path child = parent;
                for (Path name : parent.relativize(dir)) {
                    child = child.resolve(name);
                    createAndCheckIsDirectory(child, attrs);
                }
                return dir;
            } else if (se == null) {
                throw new FileSystemException(dir.toString(), null, "Unable to determine if root directory exists");
            } else {
                throw se;
            }
        }
    }

    private static void createAndCheckIsDirectory(Path dir, FileAttribute<?>... attrs) throws IOException {
        try {
            createDirectory(dir, attrs);
        } catch (FileAlreadyExistsException x) {
            if (!isDirectory(dir, LinkOption.NOFOLLOW_LINKS)) {
                throw x;
            }
        }
    }

    public static Path createTempFile(Path dir, String prefix, String suffix, FileAttribute<?>... attrs) throws IOException {
        return TempFileHelper.createTempFile((Path) Objects.requireNonNull(dir), prefix, suffix, attrs);
    }

    public static Path createTempFile(String prefix, String suffix, FileAttribute<?>... attrs) throws IOException {
        return TempFileHelper.createTempFile(null, prefix, suffix, attrs);
    }

    public static Path createTempDirectory(Path dir, String prefix, FileAttribute<?>... attrs) throws IOException {
        return TempFileHelper.createTempDirectory((Path) Objects.requireNonNull(dir), prefix, attrs);
    }

    public static Path createTempDirectory(String prefix, FileAttribute<?>... attrs) throws IOException {
        return TempFileHelper.createTempDirectory(null, prefix, attrs);
    }

    public static Path createSymbolicLink(Path link, Path target, FileAttribute<?>... attrs) throws IOException {
        provider(link).createSymbolicLink(link, target, attrs);
        return link;
    }

    public static Path createLink(Path link, Path existing) throws IOException {
        provider(link).createLink(link, existing);
        return link;
    }

    public static void delete(Path path) throws IOException {
        provider(path).delete(path);
    }

    public static boolean deleteIfExists(Path path) throws IOException {
        return provider(path).deleteIfExists(path);
    }

    public static Path copy(Path source, Path target, CopyOption... options) throws IOException {
        FileSystemProvider provider = provider(source);
        if (provider(target) == provider) {
            provider.copy(source, target, options);
        } else {
            CopyMoveHelper.copyToForeignTarget(source, target, options);
        }
        return target;
    }

    public static Path move(Path source, Path target, CopyOption... options) throws IOException {
        FileSystemProvider provider = provider(source);
        if (provider(target) == provider) {
            provider.move(source, target, options);
        } else {
            CopyMoveHelper.moveToForeignTarget(source, target, options);
        }
        return target;
    }

    public static Path readSymbolicLink(Path link) throws IOException {
        return provider(link).readSymbolicLink(link);
    }

    public static FileStore getFileStore(Path path) throws IOException {
        return provider(path).getFileStore(path);
    }

    public static boolean isSameFile(Path path, Path path2) throws IOException {
        return provider(path).isSameFile(path, path2);
    }

    public static boolean isHidden(Path path) throws IOException {
        return provider(path).isHidden(path);
    }

    private static class FileTypeDetectors {
        static final FileTypeDetector defaultFileTypeDetector = createDefaultFileTypeDetector();
        static final List<FileTypeDetector> installeDetectors = loadInstalledDetectors();

        private FileTypeDetectors() {
        }

        private static FileTypeDetector createDefaultFileTypeDetector() {
            return (FileTypeDetector) AccessController.doPrivileged(new PrivilegedAction<FileTypeDetector>() {
                /* class java.nio.file.Files.FileTypeDetectors.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public FileTypeDetector run() {
                    return DefaultFileTypeDetector.create();
                }
            });
        }

        private static List<FileTypeDetector> loadInstalledDetectors() {
            return (List) AccessController.doPrivileged(new PrivilegedAction<List<FileTypeDetector>>() {
                /* class java.nio.file.Files.FileTypeDetectors.AnonymousClass2 */

                @Override // java.security.PrivilegedAction
                public List<FileTypeDetector> run() {
                    List<FileTypeDetector> list = new ArrayList<>();
                    Iterator<FileTypeDetector> it = ServiceLoader.load(FileTypeDetector.class, ClassLoader.getSystemClassLoader()).iterator();
                    while (it.hasNext()) {
                        list.add(it.next());
                    }
                    return list;
                }
            });
        }
    }

    public static String probeContentType(Path path) throws IOException {
        for (FileTypeDetector detector : FileTypeDetectors.installeDetectors) {
            String result = detector.probeContentType(path);
            if (result != null) {
                return result;
            }
        }
        return FileTypeDetectors.defaultFileTypeDetector.probeContentType(path);
    }

    public static <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> type, LinkOption... options) {
        return (V) provider(path).getFileAttributeView(path, type, options);
    }

    public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) throws IOException {
        return (A) provider(path).readAttributes(path, type, options);
    }

    public static Path setAttribute(Path path, String attribute, Object value, LinkOption... options) throws IOException {
        provider(path).setAttribute(path, attribute, value, options);
        return path;
    }

    public static Object getAttribute(Path path, String attribute, LinkOption... options) throws IOException {
        String name;
        if (attribute.indexOf(42) >= 0 || attribute.indexOf(44) >= 0) {
            throw new IllegalArgumentException(attribute);
        }
        Map<String, Object> map = readAttributes(path, attribute, options);
        int pos = attribute.indexOf(58);
        if (pos == -1) {
            name = attribute;
        } else {
            name = pos == attribute.length() ? "" : attribute.substring(pos + 1);
        }
        return map.get(name);
    }

    public static Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException {
        return provider(path).readAttributes(path, attributes, options);
    }

    public static Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption... options) throws IOException {
        return ((PosixFileAttributes) readAttributes(path, PosixFileAttributes.class, options)).permissions();
    }

    public static Path setPosixFilePermissions(Path path, Set<PosixFilePermission> perms) throws IOException {
        PosixFileAttributeView view = (PosixFileAttributeView) getFileAttributeView(path, PosixFileAttributeView.class, new LinkOption[0]);
        if (view != null) {
            view.setPermissions(perms);
            return path;
        }
        throw new UnsupportedOperationException();
    }

    public static UserPrincipal getOwner(Path path, LinkOption... options) throws IOException {
        FileOwnerAttributeView view = (FileOwnerAttributeView) getFileAttributeView(path, FileOwnerAttributeView.class, options);
        if (view != null) {
            return view.getOwner();
        }
        throw new UnsupportedOperationException();
    }

    public static Path setOwner(Path path, UserPrincipal owner) throws IOException {
        FileOwnerAttributeView view = (FileOwnerAttributeView) getFileAttributeView(path, FileOwnerAttributeView.class, new LinkOption[0]);
        if (view != null) {
            view.setOwner(owner);
            return path;
        }
        throw new UnsupportedOperationException();
    }

    public static boolean isSymbolicLink(Path path) {
        try {
            return readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS).isSymbolicLink();
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isDirectory(Path path, LinkOption... options) {
        try {
            return readAttributes(path, BasicFileAttributes.class, options).isDirectory();
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isRegularFile(Path path, LinkOption... options) {
        try {
            return readAttributes(path, BasicFileAttributes.class, options).isRegularFile();
        } catch (IOException e) {
            return false;
        }
    }

    public static FileTime getLastModifiedTime(Path path, LinkOption... options) throws IOException {
        return readAttributes(path, BasicFileAttributes.class, options).lastModifiedTime();
    }

    public static Path setLastModifiedTime(Path path, FileTime time) throws IOException {
        ((BasicFileAttributeView) getFileAttributeView(path, BasicFileAttributeView.class, new LinkOption[0])).setTimes(time, null, null);
        return path;
    }

    public static long size(Path path) throws IOException {
        return readAttributes(path, BasicFileAttributes.class, new LinkOption[0]).size();
    }

    private static boolean followLinks(LinkOption... options) {
        boolean followLinks = true;
        for (LinkOption opt : options) {
            if (opt == LinkOption.NOFOLLOW_LINKS) {
                followLinks = false;
            } else if (opt == null) {
                throw new NullPointerException();
            } else {
                throw new AssertionError((Object) "Should not get here");
            }
        }
        return followLinks;
    }

    public static boolean exists(Path path, LinkOption... options) {
        try {
            if (followLinks(options)) {
                provider(path).checkAccess(path, new AccessMode[0]);
            } else {
                readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean notExists(Path path, LinkOption... options) {
        try {
            if (followLinks(options)) {
                provider(path).checkAccess(path, new AccessMode[0]);
            } else {
                readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            }
            return false;
        } catch (NoSuchFileException e) {
            return true;
        } catch (IOException e2) {
            return false;
        }
    }

    private static boolean isAccessible(Path path, AccessMode... modes) {
        try {
            provider(path).checkAccess(path, modes);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isReadable(Path path) {
        return isAccessible(path, AccessMode.READ);
    }

    public static boolean isWritable(Path path) {
        return isAccessible(path, AccessMode.WRITE);
    }

    public static boolean isExecutable(Path path) {
        return isAccessible(path, AccessMode.EXECUTE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0092, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0093, code lost:
        $closeResource(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0096, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.file.Path walkFileTree(java.nio.file.Path r5, java.util.Set<java.nio.file.FileVisitOption> r6, int r7, java.nio.file.FileVisitor<? super java.nio.file.Path> r8) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 151
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.Files.walkFileTree(java.nio.file.Path, java.util.Set, int, java.nio.file.FileVisitor):java.nio.file.Path");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.nio.file.Files$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$java$nio$file$FileTreeWalker$EventType = new int[FileTreeWalker.EventType.values().length];

        static {
            try {
                $SwitchMap$java$nio$file$FileTreeWalker$EventType[FileTreeWalker.EventType.ENTRY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$nio$file$FileTreeWalker$EventType[FileTreeWalker.EventType.START_DIRECTORY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$nio$file$FileTreeWalker$EventType[FileTreeWalker.EventType.END_DIRECTORY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 != null) {
            try {
                x1.close();
            } catch (Throwable th) {
                x0.addSuppressed(th);
            }
        } else {
            x1.close();
        }
    }

    public static Path walkFileTree(Path start, FileVisitor<? super Path> visitor) throws IOException {
        return walkFileTree(start, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, visitor);
    }

    public static BufferedReader newBufferedReader(Path path, Charset cs) throws IOException {
        return new BufferedReader(new InputStreamReader(newInputStream(path, new OpenOption[0]), cs.newDecoder()));
    }

    public static BufferedReader newBufferedReader(Path path) throws IOException {
        return newBufferedReader(path, StandardCharsets.UTF_8);
    }

    public static BufferedWriter newBufferedWriter(Path path, Charset cs, OpenOption... options) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(newOutputStream(path, options), cs.newEncoder()));
    }

    public static BufferedWriter newBufferedWriter(Path path, OpenOption... options) throws IOException {
        return newBufferedWriter(path, StandardCharsets.UTF_8, options);
    }

    private static long copy(InputStream source, OutputStream sink) throws IOException {
        long nread = 0;
        byte[] buf = new byte[8192];
        while (true) {
            int n = source.read(buf);
            if (n <= 0) {
                return nread;
            }
            sink.write(buf, 0, n);
            nread += (long) n;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005d, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005e, code lost:
        if (r1 != null) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0060, code lost:
        $closeResource(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0063, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long copy(java.io.InputStream r7, java.nio.file.Path r8, java.nio.file.CopyOption... r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 105
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.Files.copy(java.io.InputStream, java.nio.file.Path, java.nio.file.CopyOption[]):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r0 != null) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        $closeResource(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long copy(java.nio.file.Path r4, java.io.OutputStream r5) throws java.io.IOException {
        /*
            java.util.Objects.requireNonNull(r5)
            r0 = 0
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.io.InputStream r0 = newInputStream(r4, r0)
            long r1 = copy(r0, r5)     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0014
            r3 = 0
            $closeResource(r3, r0)
        L_0x0014:
            return r1
        L_0x0015:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0017 }
        L_0x0017:
            r2 = move-exception
            if (r0 == 0) goto L_0x001d
            $closeResource(r1, r0)
        L_0x001d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.Files.copy(java.nio.file.Path, java.io.OutputStream):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] read(java.io.InputStream r6, int r7) throws java.io.IOException {
        /*
            r0 = r7
            byte[] r1 = new byte[r0]
            r2 = 0
        L_0x0004:
            int r3 = r0 - r2
            int r3 = r6.read(r1, r2, r3)
            r4 = r3
            if (r3 <= 0) goto L_0x000f
            int r2 = r2 + r4
            goto L_0x0004
        L_0x000f:
            if (r4 < 0) goto L_0x0041
            int r3 = r6.read()
            r4 = r3
            if (r3 >= 0) goto L_0x0019
            goto L_0x0041
        L_0x0019:
            r3 = 2147483639(0x7ffffff7, float:NaN)
            int r5 = r3 - r0
            if (r0 > r5) goto L_0x0029
            int r3 = r0 << 1
            r5 = 8192(0x2000, float:1.14794E-41)
            int r0 = java.lang.Math.max(r3, r5)
            goto L_0x002e
        L_0x0029:
            if (r0 == r3) goto L_0x0039
            r0 = 2147483639(0x7ffffff7, float:NaN)
        L_0x002e:
            byte[] r1 = java.util.Arrays.copyOf(r1, r0)
            int r3 = r2 + 1
            byte r5 = (byte) r4
            r1[r2] = r5
            r2 = r3
            goto L_0x0004
        L_0x0039:
            java.lang.OutOfMemoryError r3 = new java.lang.OutOfMemoryError
            java.lang.String r5 = "Required array size too large"
            r3.<init>(r5)
            throw r3
        L_0x0041:
            if (r0 != r2) goto L_0x0045
            r3 = r1
            goto L_0x0049
        L_0x0045:
            byte[] r3 = java.util.Arrays.copyOf(r1, r2)
        L_0x0049:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.Files.read(java.io.InputStream, int):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        if (r1 != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0033, code lost:
        $closeResource(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0036, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0039, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003a, code lost:
        if (r0 != null) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003c, code lost:
        $closeResource(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003f, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] readAllBytes(java.nio.file.Path r6) throws java.io.IOException {
        /*
            r0 = 0
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.nio.channels.SeekableByteChannel r0 = newByteChannel(r6, r0)
            java.io.InputStream r1 = java.nio.channels.Channels.newInputStream(r0)     // Catch:{ all -> 0x0037 }
            long r2 = r0.size()     // Catch:{ all -> 0x002e }
            r4 = 2147483639(0x7ffffff7, double:1.060997891E-314)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x0026
            int r4 = (int) r2     // Catch:{ all -> 0x002e }
            byte[] r4 = read(r1, r4)     // Catch:{ all -> 0x002e }
            r5 = 0
            if (r1 == 0) goto L_0x0022
            $closeResource(r5, r1)
        L_0x0022:
            $closeResource(r5, r0)
            return r4
        L_0x0026:
            java.lang.OutOfMemoryError r4 = new java.lang.OutOfMemoryError
            java.lang.String r5 = "Required array size too large"
            r4.<init>(r5)
            throw r4
        L_0x002e:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r3 = move-exception
            if (r1 == 0) goto L_0x0036
            $closeResource(r2, r1)
        L_0x0036:
            throw r3
        L_0x0037:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r2 = move-exception
            if (r0 == 0) goto L_0x003f
            $closeResource(r1, r0)
        L_0x003f:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.Files.readAllBytes(java.nio.file.Path):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (r0 != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        $closeResource(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> readAllLines(java.nio.file.Path r3, java.nio.charset.Charset r4) throws java.io.IOException {
        /*
            java.io.BufferedReader r0 = newBufferedReader(r3, r4)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x001b }
            r1.<init>()     // Catch:{ all -> 0x001b }
        L_0x0009:
            java.lang.String r2 = r0.readLine()     // Catch:{ all -> 0x001b }
            if (r2 != 0) goto L_0x0016
            r2 = 0
            $closeResource(r2, r0)
            return r1
        L_0x0016:
            r1.add(r2)
            goto L_0x0009
        L_0x001b:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001d }
        L_0x001d:
            r2 = move-exception
            if (r0 == 0) goto L_0x0023
            $closeResource(r1, r0)
        L_0x0023:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.Files.readAllLines(java.nio.file.Path, java.nio.charset.Charset):java.util.List");
    }

    public static List<String> readAllLines(Path path) throws IOException {
        return readAllLines(path, StandardCharsets.UTF_8);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        if (r0 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        $closeResource(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.file.Path write(java.nio.file.Path r5, byte[] r6, java.nio.file.OpenOption... r7) throws java.io.IOException {
        /*
            java.util.Objects.requireNonNull(r6)
            java.io.OutputStream r0 = newOutputStream(r5, r7)
            int r1 = r6.length     // Catch:{ all -> 0x001f }
            r2 = r1
        L_0x0009:
            if (r2 <= 0) goto L_0x0018
            r3 = 8192(0x2000, float:1.14794E-41)
            int r3 = java.lang.Math.min(r2, r3)     // Catch:{ all -> 0x001f }
            int r4 = r1 - r2
            r0.write(r6, r4, r3)     // Catch:{ all -> 0x001f }
            int r2 = r2 - r3
            goto L_0x0009
        L_0x0018:
            if (r0 == 0) goto L_0x001e
            r1 = 0
            $closeResource(r1, r0)
        L_0x001e:
            return r5
        L_0x001f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r2 = move-exception
            if (r0 == 0) goto L_0x0027
            $closeResource(r1, r0)
        L_0x0027:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.Files.write(java.nio.file.Path, byte[], java.nio.file.OpenOption[]):java.nio.file.Path");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
        $closeResource(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.file.Path write(java.nio.file.Path r5, java.lang.Iterable<? extends java.lang.CharSequence> r6, java.nio.charset.Charset r7, java.nio.file.OpenOption... r8) throws java.io.IOException {
        /*
            java.util.Objects.requireNonNull(r6)
            java.nio.charset.CharsetEncoder r0 = r7.newEncoder()
            java.io.OutputStream r1 = newOutputStream(r5, r8)
            java.io.BufferedWriter r2 = new java.io.BufferedWriter
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter
            r3.<init>(r1, r0)
            r2.<init>(r3)
            java.util.Iterator r3 = r6.iterator()     // Catch:{ all -> 0x0031 }
        L_0x0019:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0031 }
            if (r4 == 0) goto L_0x002c
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0031 }
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ all -> 0x0031 }
            r2.append(r4)     // Catch:{ all -> 0x0031 }
            r2.newLine()     // Catch:{ all -> 0x0031 }
            goto L_0x0019
        L_0x002c:
            r3 = 0
            $closeResource(r3, r2)
            return r5
        L_0x0031:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r4 = move-exception
            $closeResource(r3, r2)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.Files.write(java.nio.file.Path, java.lang.Iterable, java.nio.charset.Charset, java.nio.file.OpenOption[]):java.nio.file.Path");
    }

    public static Path write(Path path, Iterable<? extends CharSequence> lines, OpenOption... options) throws IOException {
        return write(path, lines, StandardCharsets.UTF_8, options);
    }

    public static Stream<Path> list(Path dir) throws IOException {
        DirectoryStream<Path> ds = newDirectoryStream(dir);
        try {
            final Iterator<Path> delegate = ds.iterator();
            return (Stream) StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<Path>() {
                /* class java.nio.file.Files.AnonymousClass2 */

                @Override // java.util.Iterator
                public boolean hasNext() {
                    try {
                        return Iterator.this.hasNext();
                    } catch (DirectoryIteratorException e) {
                        throw new UncheckedIOException(e.getCause());
                    }
                }

                @Override // java.util.Iterator
                public Path next() {
                    try {
                        return (Path) Iterator.this.next();
                    } catch (DirectoryIteratorException e) {
                        throw new UncheckedIOException(e.getCause());
                    }
                }
            }, 1), false).onClose(asUncheckedRunnable(ds));
        } catch (Error | RuntimeException e) {
            try {
                ds.close();
            } catch (IOException ex) {
                e.addSuppressed(ex);
            } catch (Throwable th) {
            }
            throw e;
        }
    }

    public static Stream<Path> walk(Path start, int maxDepth, FileVisitOption... options) throws IOException {
        FileTreeIterator iterator = new FileTreeIterator(start, maxDepth, options);
        try {
            Stream stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 1), false);
            Objects.requireNonNull(iterator);
            return ((Stream) stream.onClose(new Runnable() {
                /* class java.nio.file.$$Lambda$sYbGIj22XbOmrXSY16DZsES4BAM */

                @Override // java.lang.Runnable
                public final void run() {
                    FileTreeIterator.this.close();
                }
            })).map($$Lambda$Files$troLqSRHugOdjQwE7dW2qp22ctc.INSTANCE);
        } catch (Error | RuntimeException e) {
            iterator.close();
            throw e;
        }
    }

    public static Stream<Path> walk(Path start, FileVisitOption... options) throws IOException {
        return walk(start, Integer.MAX_VALUE, options);
    }

    public static Stream<Path> find(Path start, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher, FileVisitOption... options) throws IOException {
        FileTreeIterator iterator = new FileTreeIterator(start, maxDepth, options);
        try {
            Stream stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 1), false);
            Objects.requireNonNull(iterator);
            return ((Stream) stream.onClose(new Runnable() {
                /* class java.nio.file.$$Lambda$sYbGIj22XbOmrXSY16DZsES4BAM */

                @Override // java.lang.Runnable
                public final void run() {
                    FileTreeIterator.this.close();
                }
            })).filter(new Predicate() {
                /* class java.nio.file.$$Lambda$Files$4idNQbLxq4bhe2g1MNnC6cjfF5E */

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    FileTreeWalker.Event event;
                    return BiPredicate.this.test(event.file(), ((FileTreeWalker.Event) obj).attributes());
                }
            }).map($$Lambda$Files$cNMxoBpYNc_xj_crDjR6l6JHUZ0.INSTANCE);
        } catch (Error | RuntimeException e) {
            iterator.close();
            throw e;
        }
    }

    public static Stream<String> lines(Path path, Charset cs) throws IOException {
        BufferedReader br = newBufferedReader(path, cs);
        try {
            return (Stream) br.lines().onClose(asUncheckedRunnable(br));
        } catch (Error | RuntimeException e) {
            try {
                br.close();
            } catch (IOException ex) {
                e.addSuppressed(ex);
            } catch (Throwable th) {
            }
            throw e;
        }
    }

    public static Stream<String> lines(Path path) throws IOException {
        return lines(path, StandardCharsets.UTF_8);
    }
}
