package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.TreeTraverser;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.io.ByteSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
@GwtIncompatible
public final class MoreFiles {
    private static final SuccessorsFunction<Path> FILE_TREE = new SuccessorsFunction<Path>() {
        /* class com.google.common.io.MoreFiles.AnonymousClass1 */

        public Iterable<Path> successors(Path path) {
            return MoreFiles.fileTreeChildren(path);
        }
    };

    private MoreFiles() {
    }

    public static ByteSource asByteSource(Path path, OpenOption... openOptionArr) {
        return new PathByteSource(path, openOptionArr);
    }

    /* access modifiers changed from: private */
    public static final class PathByteSource extends ByteSource {
        private static final LinkOption[] FOLLOW_LINKS = new LinkOption[0];
        private final boolean followLinks;
        private final OpenOption[] options;
        private final Path path;

        private PathByteSource(Path path2, OpenOption... openOptionArr) {
            this.path = (Path) Preconditions.checkNotNull(path2);
            this.options = (OpenOption[]) openOptionArr.clone();
            this.followLinks = followLinks(this.options);
        }

        private static boolean followLinks(OpenOption[] openOptionArr) {
            for (OpenOption openOption : openOptionArr) {
                if (openOption == LinkOption.NOFOLLOW_LINKS) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return Files.newInputStream(this.path, this.options);
        }

        private BasicFileAttributes readAttributes() throws IOException {
            return Files.readAttributes(this.path, BasicFileAttributes.class, this.followLinks ? FOLLOW_LINKS : new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            try {
                BasicFileAttributes readAttributes = readAttributes();
                if (readAttributes.isDirectory() || readAttributes.isSymbolicLink()) {
                    return Optional.absent();
                }
                return Optional.of(Long.valueOf(readAttributes.size()));
            } catch (IOException unused) {
                return Optional.absent();
            }
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            BasicFileAttributes readAttributes = readAttributes();
            if (readAttributes.isDirectory()) {
                throw new IOException("can't read: is a directory");
            } else if (!readAttributes.isSymbolicLink()) {
                return readAttributes.size();
            } else {
                throw new IOException("can't read: is a symbolic link");
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
            if (r0 != null) goto L_0x0021;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
            if (r1 != null) goto L_0x0023;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
            r1.addSuppressed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001b, code lost:
            r2 = move-exception;
         */
        @Override // com.google.common.io.ByteSource
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public byte[] read() throws java.io.IOException {
            /*
                r5 = this;
                java.nio.file.Path r0 = r5.path
                java.nio.file.OpenOption[] r1 = r5.options
                java.nio.channels.SeekableByteChannel r0 = java.nio.file.Files.newByteChannel(r0, r1)
                r1 = 0
                java.io.InputStream r2 = java.nio.channels.Channels.newInputStream(r0)     // Catch:{ Throwable -> 0x001d }
                long r3 = r0.size()     // Catch:{ Throwable -> 0x001d }
                byte[] r1 = com.google.common.io.Files.readFile(r2, r3)     // Catch:{ Throwable -> 0x001d }
                if (r0 == 0) goto L_0x001a
                r0.close()
            L_0x001a:
                return r1
            L_0x001b:
                r2 = move-exception
                goto L_0x001f
            L_0x001d:
                r1 = move-exception
                throw r1     // Catch:{ all -> 0x001b }
            L_0x001f:
                if (r0 == 0) goto L_0x002f
                if (r1 == 0) goto L_0x002c
                r0.close()     // Catch:{ Throwable -> 0x0027 }
                goto L_0x002f
            L_0x0027:
                r0 = move-exception
                r1.addSuppressed(r0)
                goto L_0x002f
            L_0x002c:
                r0.close()
            L_0x002f:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.MoreFiles.PathByteSource.read():byte[]");
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            if (this.options.length == 0) {
                return new ByteSource.AsCharSource(charset) {
                    /* class com.google.common.io.MoreFiles.PathByteSource.AnonymousClass1 */

                    @Override // com.google.common.io.CharSource
                    public Stream<String> lines() throws IOException {
                        return Files.lines(PathByteSource.this.path, this.charset);
                    }
                };
            }
            return super.asCharSource(charset);
        }

        public String toString() {
            return "MoreFiles.asByteSource(" + this.path + ", " + Arrays.toString(this.options) + ")";
        }
    }

    public static ByteSink asByteSink(Path path, OpenOption... openOptionArr) {
        return new PathByteSink(path, openOptionArr);
    }

    /* access modifiers changed from: private */
    public static final class PathByteSink extends ByteSink {
        private final OpenOption[] options;
        private final Path path;

        private PathByteSink(Path path2, OpenOption... openOptionArr) {
            this.path = (Path) Preconditions.checkNotNull(path2);
            this.options = (OpenOption[]) openOptionArr.clone();
        }

        @Override // com.google.common.io.ByteSink
        public OutputStream openStream() throws IOException {
            return Files.newOutputStream(this.path, this.options);
        }

        public String toString() {
            return "MoreFiles.asByteSink(" + this.path + ", " + Arrays.toString(this.options) + ")";
        }
    }

    public static CharSource asCharSource(Path path, Charset charset, OpenOption... openOptionArr) {
        return asByteSource(path, openOptionArr).asCharSource(charset);
    }

    public static CharSink asCharSink(Path path, Charset charset, OpenOption... openOptionArr) {
        return asByteSink(path, openOptionArr).asCharSink(charset);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
        if (r2 != null) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0015, code lost:
        if (r0 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001c, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0020, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.common.collect.ImmutableList<java.nio.file.Path> listFiles(java.nio.file.Path r2) throws java.io.IOException {
        /*
            java.nio.file.DirectoryStream r2 = java.nio.file.Files.newDirectoryStream(r2)     // Catch:{ DirectoryIteratorException -> 0x0024 }
            r0 = 0
            com.google.common.collect.ImmutableList r0 = com.google.common.collect.ImmutableList.copyOf(r2)     // Catch:{ Throwable -> 0x0011 }
            if (r2 == 0) goto L_0x000e
            r2.close()
        L_0x000e:
            return r0
        L_0x000f:
            r1 = move-exception
            goto L_0x0013
        L_0x0011:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x000f }
        L_0x0013:
            if (r2 == 0) goto L_0x0023
            if (r0 == 0) goto L_0x0020
            r2.close()     // Catch:{ Throwable -> 0x001b }
            goto L_0x0023
        L_0x001b:
            r2 = move-exception
            r0.addSuppressed(r2)
            goto L_0x0023
        L_0x0020:
            r2.close()
        L_0x0023:
            throw r1
        L_0x0024:
            r2 = move-exception
            java.io.IOException r2 = r2.getCause()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.MoreFiles.listFiles(java.nio.file.Path):com.google.common.collect.ImmutableList");
    }

    @Deprecated
    public static TreeTraverser<Path> directoryTreeTraverser() {
        return DirectoryTreeTraverser.INSTANCE;
    }

    private static final class DirectoryTreeTraverser extends TreeTraverser<Path> {
        private static final DirectoryTreeTraverser INSTANCE = new DirectoryTreeTraverser();

        private DirectoryTreeTraverser() {
        }

        public Iterable<Path> children(Path path) {
            return MoreFiles.fileTreeChildren(path);
        }
    }

    public static Traverser<Path> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }

    /* access modifiers changed from: private */
    public static Iterable<Path> fileTreeChildren(Path path) {
        if (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            return ImmutableList.of();
        }
        try {
            return listFiles(path);
        } catch (IOException e) {
            throw new DirectoryIteratorException(e);
        }
    }

    public static Predicate<Path> isDirectory(LinkOption... linkOptionArr) {
        final LinkOption[] linkOptionArr2 = (LinkOption[]) linkOptionArr.clone();
        return new Predicate<Path>() {
            /* class com.google.common.io.MoreFiles.AnonymousClass2 */

            public boolean apply(Path path) {
                return Files.isDirectory(path, linkOptionArr2);
            }

            public String toString() {
                return "MoreFiles.isDirectory(" + Arrays.toString(linkOptionArr2) + ")";
            }
        };
    }

    private static boolean isDirectory(SecureDirectoryStream<Path> secureDirectoryStream, Path path, LinkOption... linkOptionArr) throws IOException {
        return ((BasicFileAttributeView) secureDirectoryStream.getFileAttributeView(path, BasicFileAttributeView.class, linkOptionArr)).readAttributes().isDirectory();
    }

    public static Predicate<Path> isRegularFile(LinkOption... linkOptionArr) {
        final LinkOption[] linkOptionArr2 = (LinkOption[]) linkOptionArr.clone();
        return new Predicate<Path>() {
            /* class com.google.common.io.MoreFiles.AnonymousClass3 */

            public boolean apply(Path path) {
                return Files.isRegularFile(path, linkOptionArr2);
            }

            public String toString() {
                return "MoreFiles.isRegularFile(" + Arrays.toString(linkOptionArr2) + ")";
            }
        };
    }

    public static boolean equal(Path path, Path path2) throws IOException {
        Preconditions.checkNotNull(path);
        Preconditions.checkNotNull(path2);
        if (Files.isSameFile(path, path2)) {
            return true;
        }
        ByteSource asByteSource = asByteSource(path, new OpenOption[0]);
        ByteSource asByteSource2 = asByteSource(path2, new OpenOption[0]);
        long longValue = asByteSource.sizeIfKnown().or((Long) 0L).longValue();
        long longValue2 = asByteSource2.sizeIfKnown().or((Long) 0L).longValue();
        if (longValue == 0 || longValue2 == 0 || longValue == longValue2) {
            return asByteSource.contentEquals(asByteSource2);
        }
        return false;
    }

    public static void touch(Path path) throws IOException {
        Preconditions.checkNotNull(path);
        try {
            Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
        } catch (NoSuchFileException unused) {
            try {
                Files.createFile(path, new FileAttribute[0]);
            } catch (FileAlreadyExistsException unused2) {
            }
        }
    }

    public static void createParentDirectories(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        Path parent = path.toAbsolutePath().normalize().getParent();
        if (parent != null && !Files.isDirectory(parent, new LinkOption[0])) {
            Files.createDirectories(parent, fileAttributeArr);
            if (!Files.isDirectory(parent, new LinkOption[0])) {
                throw new IOException("Unable to create parent directories of " + path);
            }
        }
    }

    public static String getFileExtension(Path path) {
        String path2;
        int lastIndexOf;
        Path fileName = path.getFileName();
        if (fileName == null || (lastIndexOf = (path2 = fileName.toString()).lastIndexOf(46)) == -1) {
            return "";
        }
        return path2.substring(lastIndexOf + 1);
    }

    public static String getNameWithoutExtension(Path path) {
        Path fileName = path.getFileName();
        if (fileName == null) {
            return "";
        }
        String path2 = fileName.toString();
        int lastIndexOf = path2.lastIndexOf(46);
        return lastIndexOf == -1 ? path2 : path2.substring(0, lastIndexOf);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
        r2 = r7;
        r7 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void deleteRecursively(java.nio.file.Path r6, com.google.common.io.RecursiveDeleteOption... r7) throws java.io.IOException {
        /*
            java.nio.file.Path r0 = getParentPath(r6)
            r1 = 0
            if (r0 == 0) goto L_0x0052
            r2 = 0
            java.nio.file.DirectoryStream r0 = java.nio.file.Files.newDirectoryStream(r0)     // Catch:{ IOException -> 0x0045 }
            boolean r3 = r0 instanceof java.nio.file.SecureDirectoryStream     // Catch:{ Throwable -> 0x002e, all -> 0x002b }
            if (r3 == 0) goto L_0x001c
            r2 = 1
            r3 = r0
            java.nio.file.SecureDirectoryStream r3 = (java.nio.file.SecureDirectoryStream) r3     // Catch:{ Throwable -> 0x002e, all -> 0x002b }
            java.nio.file.Path r4 = r6.getFileName()     // Catch:{ Throwable -> 0x002e, all -> 0x002b }
            java.util.Collection r1 = deleteRecursivelySecure(r3, r4)     // Catch:{ Throwable -> 0x002e, all -> 0x002b }
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            if (r2 != 0) goto L_0x004b
            checkAllowsInsecure(r6, r7)
            java.util.Collection r1 = deleteRecursivelyInsecure(r6)
            goto L_0x004b
        L_0x002b:
            r7 = move-exception
            r2 = r1
            goto L_0x0034
        L_0x002e:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r2 = move-exception
            r5 = r2
            r2 = r7
            r7 = r5
        L_0x0034:
            if (r0 == 0) goto L_0x0044
            if (r2 == 0) goto L_0x0041
            r0.close()     // Catch:{ Throwable -> 0x003c }
            goto L_0x0044
        L_0x003c:
            r0 = move-exception
            r2.addSuppressed(r0)
            goto L_0x0044
        L_0x0041:
            r0.close()
        L_0x0044:
            throw r7
        L_0x0045:
            r7 = move-exception
            if (r1 == 0) goto L_0x0051
            r1.add(r7)
        L_0x004b:
            if (r1 == 0) goto L_0x0050
            throwDeleteFailed(r6, r1)
        L_0x0050:
            return
        L_0x0051:
            throw r7
        L_0x0052:
            java.nio.file.FileSystemException r7 = new java.nio.file.FileSystemException
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = "can't delete recursively"
            r7.<init>(r6, r1, r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.MoreFiles.deleteRecursively(java.nio.file.Path, com.google.common.io.RecursiveDeleteOption[]):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0024, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        r2 = r5;
        r5 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void deleteDirectoryContents(java.nio.file.Path r4, com.google.common.io.RecursiveDeleteOption... r5) throws java.io.IOException {
        /*
            r0 = 0
            java.nio.file.DirectoryStream r1 = java.nio.file.Files.newDirectoryStream(r4)     // Catch:{ IOException -> 0x0039 }
            boolean r2 = r1 instanceof java.nio.file.SecureDirectoryStream     // Catch:{ Throwable -> 0x0022, all -> 0x001f }
            if (r2 == 0) goto L_0x0011
            r5 = r1
            java.nio.file.SecureDirectoryStream r5 = (java.nio.file.SecureDirectoryStream) r5     // Catch:{ Throwable -> 0x0022, all -> 0x001f }
            java.util.Collection r5 = deleteDirectoryContentsSecure(r5)     // Catch:{ Throwable -> 0x0022, all -> 0x001f }
            goto L_0x0018
        L_0x0011:
            checkAllowsInsecure(r4, r5)     // Catch:{ Throwable -> 0x0022, all -> 0x001f }
            java.util.Collection r5 = deleteDirectoryContentsInsecure(r1)     // Catch:{ Throwable -> 0x0022, all -> 0x001f }
        L_0x0018:
            r0 = r5
            if (r1 == 0) goto L_0x003f
            r1.close()
            goto L_0x003f
        L_0x001f:
            r5 = move-exception
            r2 = r0
            goto L_0x0028
        L_0x0022:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r2 = move-exception
            r3 = r2
            r2 = r5
            r5 = r3
        L_0x0028:
            if (r1 == 0) goto L_0x0038
            if (r2 == 0) goto L_0x0035
            r1.close()     // Catch:{ Throwable -> 0x0030 }
            goto L_0x0038
        L_0x0030:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x0038
        L_0x0035:
            r1.close()
        L_0x0038:
            throw r5
        L_0x0039:
            r5 = move-exception
            if (r0 == 0) goto L_0x0045
            r0.add(r5)
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            throwDeleteFailed(r4, r0)
        L_0x0044:
            return
        L_0x0045:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.MoreFiles.deleteDirectoryContents(java.nio.file.Path, com.google.common.io.RecursiveDeleteOption[]):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        r7 = r6;
        r6 = r7;
     */
    @org.checkerframework.checker.nullness.compatqual.NullableDecl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Collection<java.io.IOException> deleteRecursivelySecure(java.nio.file.SecureDirectoryStream<java.nio.file.Path> r6, java.nio.file.Path r7) {
        /*
            r0 = 1
            r1 = 0
            java.nio.file.LinkOption[] r2 = new java.nio.file.LinkOption[r0]     // Catch:{ IOException -> 0x0046 }
            java.nio.file.LinkOption r3 = java.nio.file.LinkOption.NOFOLLOW_LINKS     // Catch:{ IOException -> 0x0046 }
            r4 = 0
            r2[r4] = r3     // Catch:{ IOException -> 0x0046 }
            boolean r2 = isDirectory(r6, r7, r2)     // Catch:{ IOException -> 0x0046 }
            if (r2 == 0) goto L_0x0042
            java.nio.file.LinkOption[] r0 = new java.nio.file.LinkOption[r0]     // Catch:{ IOException -> 0x0046 }
            java.nio.file.LinkOption r2 = java.nio.file.LinkOption.NOFOLLOW_LINKS     // Catch:{ IOException -> 0x0046 }
            r0[r4] = r2     // Catch:{ IOException -> 0x0046 }
            java.nio.file.SecureDirectoryStream r0 = r6.newDirectoryStream(r7, r0)     // Catch:{ IOException -> 0x0046 }
            java.util.Collection r1 = deleteDirectoryContentsSecure(r0)     // Catch:{ Throwable -> 0x002b, all -> 0x0028 }
            if (r0 == 0) goto L_0x0022
            r0.close()
        L_0x0022:
            if (r1 != 0) goto L_0x0045
            r6.deleteDirectory(r7)
            goto L_0x0045
        L_0x0028:
            r6 = move-exception
            r7 = r1
            goto L_0x0031
        L_0x002b:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x002d }
        L_0x002d:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L_0x0031:
            if (r0 == 0) goto L_0x0041
            if (r7 == 0) goto L_0x003e
            r0.close()     // Catch:{ Throwable -> 0x0039 }
            goto L_0x0041
        L_0x0039:
            r0 = move-exception
            r7.addSuppressed(r0)
            goto L_0x0041
        L_0x003e:
            r0.close()
        L_0x0041:
            throw r6
        L_0x0042:
            r6.deleteFile(r7)
        L_0x0045:
            return r1
        L_0x0046:
            r6 = move-exception
            java.util.Collection r6 = addException(r1, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.MoreFiles.deleteRecursivelySecure(java.nio.file.SecureDirectoryStream, java.nio.file.Path):java.util.Collection");
    }

    @NullableDecl
    private static Collection<IOException> deleteDirectoryContentsSecure(SecureDirectoryStream<Path> secureDirectoryStream) {
        Collection<IOException> collection = null;
        try {
            for (Path path : secureDirectoryStream) {
                collection = concat(collection, deleteRecursivelySecure(secureDirectoryStream, path.getFileName()));
            }
            return collection;
        } catch (DirectoryIteratorException e) {
            return addException(null, e.getCause());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        r2 = r5;
        r5 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        r5 = th;
     */
    @org.checkerframework.checker.nullness.compatqual.NullableDecl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Collection<java.io.IOException> deleteRecursivelyInsecure(java.nio.file.Path r5) {
        /*
            r0 = 1
            r1 = 0
            java.nio.file.LinkOption[] r0 = new java.nio.file.LinkOption[r0]     // Catch:{ IOException -> 0x003d }
            r2 = 0
            java.nio.file.LinkOption r3 = java.nio.file.LinkOption.NOFOLLOW_LINKS     // Catch:{ IOException -> 0x003d }
            r0[r2] = r3     // Catch:{ IOException -> 0x003d }
            boolean r0 = java.nio.file.Files.isDirectory(r5, r0)     // Catch:{ IOException -> 0x003d }
            if (r0 == 0) goto L_0x0037
            java.nio.file.DirectoryStream r0 = java.nio.file.Files.newDirectoryStream(r5)     // Catch:{ IOException -> 0x003d }
            java.util.Collection r1 = deleteDirectoryContentsInsecure(r0)     // Catch:{ Throwable -> 0x0020, all -> 0x001d }
            if (r0 == 0) goto L_0x0037
            r0.close()
            goto L_0x0037
        L_0x001d:
            r5 = move-exception
            r2 = r1
            goto L_0x0026
        L_0x0020:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0022 }
        L_0x0022:
            r2 = move-exception
            r4 = r2
            r2 = r5
            r5 = r4
        L_0x0026:
            if (r0 == 0) goto L_0x0036
            if (r2 == 0) goto L_0x0033
            r0.close()     // Catch:{ Throwable -> 0x002e }
            goto L_0x0036
        L_0x002e:
            r0 = move-exception
            r2.addSuppressed(r0)
            goto L_0x0036
        L_0x0033:
            r0.close()
        L_0x0036:
            throw r5
        L_0x0037:
            if (r1 != 0) goto L_0x003c
            java.nio.file.Files.delete(r5)
        L_0x003c:
            return r1
        L_0x003d:
            r5 = move-exception
            java.util.Collection r5 = addException(r1, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.MoreFiles.deleteRecursivelyInsecure(java.nio.file.Path):java.util.Collection");
    }

    @NullableDecl
    private static Collection<IOException> deleteDirectoryContentsInsecure(DirectoryStream<Path> directoryStream) {
        Collection<IOException> collection = null;
        try {
            for (Path path : directoryStream) {
                collection = concat(collection, deleteRecursivelyInsecure(path));
            }
            return collection;
        } catch (DirectoryIteratorException e) {
            return addException(null, e.getCause());
        }
    }

    @NullableDecl
    private static Path getParentPath(Path path) {
        Path parent = path.getParent();
        if (parent != null) {
            return parent;
        }
        if (path.getNameCount() == 0) {
            return null;
        }
        return path.getFileSystem().getPath(".", new String[0]);
    }

    private static void checkAllowsInsecure(Path path, RecursiveDeleteOption[] recursiveDeleteOptionArr) throws InsecureRecursiveDeleteException {
        if (!Arrays.asList(recursiveDeleteOptionArr).contains(RecursiveDeleteOption.ALLOW_INSECURE)) {
            throw new InsecureRecursiveDeleteException(path.toString());
        }
    }

    private static Collection<IOException> addException(@NullableDecl Collection<IOException> collection, IOException iOException) {
        if (collection == null) {
            collection = new ArrayList<>();
        }
        collection.add(iOException);
        return collection;
    }

    @NullableDecl
    private static Collection<IOException> concat(@NullableDecl Collection<IOException> collection, @NullableDecl Collection<IOException> collection2) {
        if (collection == null) {
            return collection2;
        }
        if (collection2 != null) {
            collection.addAll(collection2);
        }
        return collection;
    }

    private static void throwDeleteFailed(Path path, Collection<IOException> collection) throws FileSystemException {
        FileSystemException fileSystemException = new FileSystemException(path.toString(), null, "failed to delete one or more files; see suppressed exceptions for details");
        for (IOException iOException : collection) {
            fileSystemException.addSuppressed(iOException);
        }
        throw fileSystemException;
    }
}
