package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.TreeTraverser;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Beta
@GwtIncompatible
public final class Files {
    private static final SuccessorsFunction<File> FILE_TREE = new SuccessorsFunction<File>() {
        /* class com.google.common.io.Files.AnonymousClass3 */

        public Iterable<File> successors(File file) {
            return Files.fileTreeChildren(file);
        }
    };
    private static final TreeTraverser<File> FILE_TREE_TRAVERSER = new TreeTraverser<File>() {
        /* class com.google.common.io.Files.AnonymousClass2 */

        public String toString() {
            return "Files.fileTreeTraverser()";
        }

        public Iterable<File> children(File file) {
            return Files.fileTreeChildren(file);
        }
    };
    private static final int TEMP_DIR_ATTEMPTS = 10000;

    private enum FilePredicate implements Predicate<File> {
        IS_DIRECTORY {
            public String toString() {
                return "Files.isDirectory()";
            }

            public boolean apply(File file) {
                return file.isDirectory();
            }
        },
        IS_FILE {
            public String toString() {
                return "Files.isFile()";
            }

            public boolean apply(File file) {
                return file.isFile();
            }
        }
    }

    private Files() {
    }

    public static BufferedReader newReader(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }

    public static BufferedWriter newWriter(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
    }

    public static ByteSource asByteSource(File file) {
        return new FileByteSource(file);
    }

    /* access modifiers changed from: private */
    public static final class FileByteSource extends ByteSource {
        private final File file;

        private FileByteSource(File file2) {
            this.file = (File) Preconditions.checkNotNull(file2);
        }

        @Override // com.google.common.io.ByteSource
        public FileInputStream openStream() throws IOException {
            return new FileInputStream(this.file);
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            if (this.file.isFile()) {
                return Optional.of(Long.valueOf(this.file.length()));
            }
            return Optional.absent();
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            if (this.file.isFile()) {
                return this.file.length();
            }
            throw new FileNotFoundException(this.file.toString());
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() throws IOException {
            Closer create = Closer.create();
            try {
                FileInputStream fileInputStream = (FileInputStream) create.register(openStream());
                byte[] readFile = Files.readFile(fileInputStream, fileInputStream.getChannel().size());
                create.close();
                return readFile;
            } catch (Throwable th) {
                create.close();
                throw th;
            }
        }

        public String toString() {
            return "Files.asByteSource(" + this.file + ")";
        }
    }

    static byte[] readFile(InputStream inputStream, long j) throws IOException {
        if (j <= 2147483647L) {
            return ByteStreams.toByteArray(inputStream, j == 0 ? 4096 : (int) j);
        }
        throw new OutOfMemoryError("file is too large to fit in a byte array: " + j + " bytes");
    }

    public static ByteSink asByteSink(File file, FileWriteMode... fileWriteModeArr) {
        return new FileByteSink(file, fileWriteModeArr);
    }

    /* access modifiers changed from: private */
    public static final class FileByteSink extends ByteSink {
        private final File file;
        private final ImmutableSet<FileWriteMode> modes;

        private FileByteSink(File file2, FileWriteMode... fileWriteModeArr) {
            this.file = (File) Preconditions.checkNotNull(file2);
            this.modes = ImmutableSet.copyOf(fileWriteModeArr);
        }

        @Override // com.google.common.io.ByteSink
        public FileOutputStream openStream() throws IOException {
            return new FileOutputStream(this.file, this.modes.contains(FileWriteMode.APPEND));
        }

        public String toString() {
            return "Files.asByteSink(" + this.file + ", " + this.modes + ")";
        }
    }

    public static CharSource asCharSource(File file, Charset charset) {
        return asByteSource(file).asCharSource(charset);
    }

    public static CharSink asCharSink(File file, Charset charset, FileWriteMode... fileWriteModeArr) {
        return asByteSink(file, fileWriteModeArr).asCharSink(charset);
    }

    public static byte[] toByteArray(File file) throws IOException {
        return asByteSource(file).read();
    }

    @Deprecated
    public static String toString(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).read();
    }

    public static void write(byte[] bArr, File file) throws IOException {
        asByteSink(file, new FileWriteMode[0]).write(bArr);
    }

    @Deprecated
    public static void write(CharSequence charSequence, File file, Charset charset) throws IOException {
        asCharSink(file, charset, new FileWriteMode[0]).write(charSequence);
    }

    public static void copy(File file, OutputStream outputStream) throws IOException {
        asByteSource(file).copyTo(outputStream);
    }

    public static void copy(File file, File file2) throws IOException {
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        asByteSource(file).copyTo(asByteSink(file2, new FileWriteMode[0]));
    }

    @Deprecated
    public static void copy(File file, Charset charset, Appendable appendable) throws IOException {
        asCharSource(file, charset).copyTo(appendable);
    }

    @Deprecated
    public static void append(CharSequence charSequence, File file, Charset charset) throws IOException {
        asCharSink(file, charset, FileWriteMode.APPEND).write(charSequence);
    }

    public static boolean equal(File file, File file2) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        if (file == file2 || file.equals(file2)) {
            return true;
        }
        long length = file.length();
        long length2 = file2.length();
        if (length == 0 || length2 == 0 || length == length2) {
            return asByteSource(file).contentEquals(asByteSource(file2));
        }
        return false;
    }

    public static File createTempDir() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        String str = System.currentTimeMillis() + "-";
        for (int i = 0; i < TEMP_DIR_ATTEMPTS; i++) {
            File file2 = new File(file, str + i);
            if (file2.mkdir()) {
                return file2;
            }
        }
        throw new IllegalStateException("Failed to create directory within 10000 attempts (tried " + str + "0 to " + str + 9999 + ')');
    }

    public static void touch(File file) throws IOException {
        Preconditions.checkNotNull(file);
        if (!file.createNewFile() && !file.setLastModified(System.currentTimeMillis())) {
            throw new IOException("Unable to update modification time of " + file);
        }
    }

    public static void createParentDirs(File file) throws IOException {
        Preconditions.checkNotNull(file);
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                throw new IOException("Unable to create parent directories of " + file);
            }
        }
    }

    public static void move(File file, File file2) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        if (!file.renameTo(file2)) {
            copy(file, file2);
            if (file.delete()) {
                return;
            }
            if (!file2.delete()) {
                throw new IOException("Unable to delete " + file2);
            }
            throw new IOException("Unable to delete " + file);
        }
    }

    @Deprecated
    public static String readFirstLine(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).readFirstLine();
    }

    public static List<String> readLines(File file, Charset charset) throws IOException {
        return (List) asCharSource(file, charset).readLines(new LineProcessor<List<String>>() {
            /* class com.google.common.io.Files.AnonymousClass1 */
            final List<String> result = Lists.newArrayList();

            @Override // com.google.common.io.LineProcessor
            public boolean processLine(String str) {
                this.result.add(str);
                return true;
            }

            @Override // com.google.common.io.LineProcessor
            public List<String> getResult() {
                return this.result;
            }
        });
    }

    @Deprecated
    @CanIgnoreReturnValue
    public static <T> T readLines(File file, Charset charset, LineProcessor<T> lineProcessor) throws IOException {
        return (T) asCharSource(file, charset).readLines(lineProcessor);
    }

    @Deprecated
    @CanIgnoreReturnValue
    public static <T> T readBytes(File file, ByteProcessor<T> byteProcessor) throws IOException {
        return (T) asByteSource(file).read(byteProcessor);
    }

    @Deprecated
    public static HashCode hash(File file, HashFunction hashFunction) throws IOException {
        return asByteSource(file).hash(hashFunction);
    }

    public static MappedByteBuffer map(File file) throws IOException {
        Preconditions.checkNotNull(file);
        return map(file, FileChannel.MapMode.READ_ONLY);
    }

    public static MappedByteBuffer map(File file, FileChannel.MapMode mapMode) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(mapMode);
        if (file.exists()) {
            return map(file, mapMode, file.length());
        }
        throw new FileNotFoundException(file.toString());
    }

    public static MappedByteBuffer map(File file, FileChannel.MapMode mapMode, long j) throws FileNotFoundException, IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(mapMode);
        Closer create = Closer.create();
        try {
            MappedByteBuffer map = map((RandomAccessFile) create.register(new RandomAccessFile(file, mapMode == FileChannel.MapMode.READ_ONLY ? "r" : "rw")), mapMode, j);
            create.close();
            return map;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    private static MappedByteBuffer map(RandomAccessFile randomAccessFile, FileChannel.MapMode mapMode, long j) throws IOException {
        Closer create = Closer.create();
        try {
            MappedByteBuffer map = ((FileChannel) create.register(randomAccessFile.getChannel())).map(mapMode, 0, j);
            create.close();
            return map;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        if (r4.equals(".") != false) goto L_0x0051;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0023 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String simplifyPath(java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.Files.simplifyPath(java.lang.String):java.lang.String");
    }

    public static String getFileExtension(String str) {
        Preconditions.checkNotNull(str);
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }

    public static String getNameWithoutExtension(String str) {
        Preconditions.checkNotNull(str);
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf == -1 ? name : name.substring(0, lastIndexOf);
    }

    @Deprecated
    public static TreeTraverser<File> fileTreeTraverser() {
        return FILE_TREE_TRAVERSER;
    }

    public static Traverser<File> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }

    /* access modifiers changed from: private */
    public static Iterable<File> fileTreeChildren(File file) {
        File[] listFiles;
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(listFiles));
    }

    public static Predicate<File> isDirectory() {
        return FilePredicate.IS_DIRECTORY;
    }

    public static Predicate<File> isFile() {
        return FilePredicate.IS_FILE;
    }
}
