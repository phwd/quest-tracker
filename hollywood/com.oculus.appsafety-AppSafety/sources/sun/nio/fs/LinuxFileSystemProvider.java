package sun.nio.fs;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.spi.FileTypeDetector;

public class LinuxFileSystemProvider extends UnixFileSystemProvider {
    /* access modifiers changed from: package-private */
    @Override // sun.nio.fs.UnixFileSystemProvider
    public LinuxFileSystem newFileSystem(String dir) {
        return new LinuxFileSystem(this, dir);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.fs.UnixFileSystemProvider
    public LinuxFileStore getFileStore(UnixPath path) throws IOException {
        throw new SecurityException("getFileStore");
    }

    @Override // sun.nio.fs.UnixFileSystemProvider, java.nio.file.spi.FileSystemProvider
    public <V extends FileAttributeView> V getFileAttributeView(Path obj, Class<V> type, LinkOption... options) {
        return (V) super.getFileAttributeView(obj, type, options);
    }

    @Override // sun.nio.fs.UnixFileSystemProvider, sun.nio.fs.AbstractFileSystemProvider
    public DynamicFileAttributeView getFileAttributeView(Path obj, String name, LinkOption... options) {
        return super.getFileAttributeView(obj, name, options);
    }

    @Override // sun.nio.fs.UnixFileSystemProvider, java.nio.file.spi.FileSystemProvider
    public <A extends BasicFileAttributes> A readAttributes(Path file, Class<A> type, LinkOption... options) throws IOException {
        return (A) super.readAttributes(file, type, options);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.fs.UnixFileSystemProvider
    public FileTypeDetector getFileTypeDetector() {
        return new MimeTypesFileTypeDetector();
    }
}
