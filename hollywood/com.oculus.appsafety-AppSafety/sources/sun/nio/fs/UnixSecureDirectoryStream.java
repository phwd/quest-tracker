package sun.nio.fs;

import dalvik.system.CloseGuard;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.ClosedDirectoryStreamException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.LinkOption;
import java.nio.file.NotDirectoryException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.ProviderMismatchException;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import sun.nio.fs.UnixUserPrincipals;

/* access modifiers changed from: package-private */
public class UnixSecureDirectoryStream implements SecureDirectoryStream<Path> {
    private final int dfd;
    private final UnixDirectoryStream ds;
    private final CloseGuard guard = CloseGuard.get();

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.util.Set, java.nio.file.attribute.FileAttribute[]] */
    @Override // java.nio.file.SecureDirectoryStream
    public /* bridge */ /* synthetic */ SeekableByteChannel newByteChannel(Path path, Set set, FileAttribute[] fileAttributeArr) throws IOException {
        return newByteChannel(path, (Set<? extends OpenOption>) set, (FileAttribute<?>[]) fileAttributeArr);
    }

    UnixSecureDirectoryStream(UnixPath dir, long dp, int dfd2, DirectoryStream.Filter<? super Path> filter) {
        this.ds = new UnixDirectoryStream(dir, dp, filter);
        this.dfd = dfd2;
        if (dfd2 != -1) {
            this.guard.open("close");
        }
    }

    /* JADX INFO: finally extract failed */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.ds.writeLock().lock();
        try {
            if (this.ds.closeImpl()) {
                UnixNativeDispatcher.close(this.dfd);
            }
            this.ds.writeLock().unlock();
            this.guard.close();
        } catch (Throwable th) {
            this.ds.writeLock().unlock();
            throw th;
        }
    }

    @Override // java.nio.file.DirectoryStream, java.lang.Iterable
    public Iterator<Path> iterator() {
        return this.ds.iterator(this);
    }

    private UnixPath getName(Path obj) {
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj instanceof UnixPath) {
            return (UnixPath) obj;
        } else {
            throw new ProviderMismatchException();
        }
    }

    public SecureDirectoryStream<Path> newDirectoryStream(Path obj, LinkOption... options) throws IOException {
        long ptr;
        int newdfd2;
        UnixPath file = getName(obj);
        UnixPath child = this.ds.directory().resolve((Path) file);
        boolean followLinks = Util.followLinks(options);
        if (System.getSecurityManager() != null) {
            child.checkRead();
        }
        this.ds.readLock().lock();
        try {
            if (this.ds.isOpen()) {
                try {
                    int flags = UnixConstants.O_RDONLY;
                    if (!followLinks) {
                        flags |= UnixConstants.O_NOFOLLOW;
                    }
                    int newdfd1 = UnixNativeDispatcher.openat(this.dfd, file.asByteArray(), flags, 0);
                    newdfd2 = UnixNativeDispatcher.dup(newdfd1);
                    ptr = UnixNativeDispatcher.fdopendir(newdfd1);
                } catch (UnixException x) {
                    if (-1 != -1) {
                        UnixNativeDispatcher.close(-1);
                    }
                    if (-1 != -1) {
                        UnixNativeDispatcher.close(-1);
                    }
                    if (x.errno() != UnixConstants.ENOTDIR) {
                        x.rethrowAsIOException(file);
                        newdfd2 = -1;
                        ptr = 0;
                    } else {
                        throw new NotDirectoryException(file.toString());
                    }
                }
                return new UnixSecureDirectoryStream(child, ptr, newdfd2, null);
            }
            throw new ClosedDirectoryStreamException();
        } finally {
            this.ds.readLock().unlock();
        }
    }

    public SeekableByteChannel newByteChannel(Path obj, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException {
        UnixPath file = getName(obj);
        int mode = UnixFileModeAttribute.toUnixMode(UnixFileModeAttribute.ALL_READWRITE, attrs);
        String pathToCheck = this.ds.directory().resolve((Path) file).getPathForPermissionCheck();
        this.ds.readLock().lock();
        try {
            if (this.ds.isOpen()) {
                try {
                    return UnixChannelFactory.newFileChannel(this.dfd, file, pathToCheck, options, mode);
                } catch (UnixException x) {
                    x.rethrowAsIOException(file);
                    this.ds.readLock().unlock();
                    return null;
                }
            } else {
                throw new ClosedDirectoryStreamException();
            }
        } finally {
            this.ds.readLock().unlock();
        }
    }

    private void implDelete(Path obj, boolean haveFlags, int flags) throws IOException {
        UnixPath file = getName(obj);
        if (System.getSecurityManager() != null) {
            this.ds.directory().resolve((Path) file).checkDelete();
        }
        this.ds.readLock().lock();
        try {
            if (this.ds.isOpen()) {
                if (!haveFlags) {
                    UnixFileAttributes attrs = null;
                    int i = 0;
                    try {
                        attrs = UnixFileAttributes.get(this.dfd, file, false);
                    } catch (UnixException x) {
                        x.rethrowAsIOException(file);
                    }
                    if (attrs.isDirectory()) {
                        i = 512;
                    }
                    flags = i;
                }
                try {
                    UnixNativeDispatcher.unlinkat(this.dfd, file.asByteArray(), flags);
                } catch (UnixException x2) {
                    if ((flags & 512) == 0 || !(x2.errno() == UnixConstants.EEXIST || x2.errno() == UnixConstants.ENOTEMPTY)) {
                        x2.rethrowAsIOException(file);
                    } else {
                        throw new DirectoryNotEmptyException(null);
                    }
                }
                return;
            }
            throw new ClosedDirectoryStreamException();
        } finally {
            this.ds.readLock().unlock();
        }
    }

    public void deleteFile(Path file) throws IOException {
        implDelete(file, true, 0);
    }

    public void deleteDirectory(Path dir) throws IOException {
        implDelete(dir, true, 512);
    }

    public void move(Path fromObj, SecureDirectoryStream<Path> dir, Path toObj) throws IOException {
        UnixSecureDirectoryStream that;
        UnixPath from = getName(fromObj);
        UnixPath to = getName(toObj);
        if (dir == null) {
            throw new NullPointerException();
        } else if (dir instanceof UnixSecureDirectoryStream) {
            that = (UnixSecureDirectoryStream) dir;
            if (System.getSecurityManager() != null) {
                this.ds.directory().resolve((Path) from).checkWrite();
                that.ds.directory().resolve((Path) to).checkWrite();
            }
            this.ds.readLock().lock();
            try {
                that.ds.readLock().lock();
                try {
                    if (!this.ds.isOpen() || !that.ds.isOpen()) {
                        throw new ClosedDirectoryStreamException();
                    }
                    try {
                        UnixNativeDispatcher.renameat(this.dfd, from.asByteArray(), that.dfd, to.asByteArray());
                    } catch (UnixException x) {
                        if (x.errno() != UnixConstants.EXDEV) {
                            x.rethrowAsIOException(from, to);
                        } else {
                            throw new AtomicMoveNotSupportedException(from.toString(), to.toString(), x.errorString());
                        }
                    }
                    that.ds.readLock().unlock();
                } finally {
                    that.ds.readLock().unlock();
                }
            } finally {
                this.ds.readLock().unlock();
            }
        } else {
            throw new ProviderMismatchException();
        }
    }

    private <V extends FileAttributeView> V getFileAttributeViewImpl(UnixPath file, Class<V> type, boolean followLinks) {
        if (type == null) {
            throw new NullPointerException();
        } else if (type == BasicFileAttributeView.class) {
            return new BasicFileAttributeViewImpl(file, followLinks);
        } else {
            return (type == PosixFileAttributeView.class || type == FileOwnerAttributeView.class) ? new PosixFileAttributeViewImpl(file, followLinks) : (V) null;
        }
    }

    @Override // java.nio.file.SecureDirectoryStream
    public <V extends FileAttributeView> V getFileAttributeView(Class<V> type) {
        return (V) getFileAttributeViewImpl(null, type, false);
    }

    public <V extends FileAttributeView> V getFileAttributeView(Path obj, Class<V> type, LinkOption... options) {
        return (V) getFileAttributeViewImpl(getName(obj), type, Util.followLinks(options));
    }

    /* access modifiers changed from: private */
    public class BasicFileAttributeViewImpl implements BasicFileAttributeView {
        final UnixPath file;
        final boolean followLinks;

        BasicFileAttributeViewImpl(UnixPath file2, boolean followLinks2) {
            this.file = file2;
            this.followLinks = followLinks2;
        }

        /* access modifiers changed from: package-private */
        public int open() throws IOException {
            int oflags = UnixConstants.O_RDONLY;
            if (!this.followLinks) {
                oflags |= UnixConstants.O_NOFOLLOW;
            }
            try {
                return UnixNativeDispatcher.openat(UnixSecureDirectoryStream.this.dfd, this.file.asByteArray(), oflags, 0);
            } catch (UnixException x) {
                x.rethrowAsIOException(this.file);
                return -1;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void checkWriteAccess() {
            if (System.getSecurityManager() == null) {
                return;
            }
            if (this.file == null) {
                UnixSecureDirectoryStream.this.ds.directory().checkWrite();
            } else {
                UnixSecureDirectoryStream.this.ds.directory().resolve((Path) this.file).checkWrite();
            }
        }

        @Override // java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.AttributeView
        public String name() {
            return "basic";
        }

        @Override // java.nio.file.attribute.BasicFileAttributeView
        public BasicFileAttributes readAttributes() throws IOException {
            UnixFileAttributes attrs;
            UnixSecureDirectoryStream.this.ds.readLock().lock();
            try {
                if (UnixSecureDirectoryStream.this.ds.isOpen()) {
                    if (System.getSecurityManager() != null) {
                        if (this.file == null) {
                            UnixSecureDirectoryStream.this.ds.directory().checkRead();
                        } else {
                            UnixSecureDirectoryStream.this.ds.directory().resolve((Path) this.file).checkRead();
                        }
                    }
                    try {
                        if (this.file == null) {
                            attrs = UnixFileAttributes.get(UnixSecureDirectoryStream.this.dfd);
                        } else {
                            attrs = UnixFileAttributes.get(UnixSecureDirectoryStream.this.dfd, this.file, this.followLinks);
                        }
                        return attrs.asBasicFileAttributes();
                    } catch (UnixException x) {
                        x.rethrowAsIOException(this.file);
                        UnixSecureDirectoryStream.this.ds.readLock().unlock();
                        return null;
                    }
                } else {
                    throw new ClosedDirectoryStreamException();
                }
            } finally {
                UnixSecureDirectoryStream.this.ds.readLock().unlock();
            }
        }

        @Override // java.nio.file.attribute.BasicFileAttributeView
        public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime) throws IOException {
            checkWriteAccess();
            UnixSecureDirectoryStream.this.ds.readLock().lock();
            try {
                if (UnixSecureDirectoryStream.this.ds.isOpen()) {
                    int fd = this.file == null ? UnixSecureDirectoryStream.this.dfd : open();
                    if (lastModifiedTime == null || lastAccessTime == null) {
                        try {
                            UnixFileAttributes attrs = UnixFileAttributes.get(fd);
                            if (lastModifiedTime == null) {
                                lastModifiedTime = attrs.lastModifiedTime();
                            }
                            if (lastAccessTime == null) {
                                lastAccessTime = attrs.lastAccessTime();
                            }
                        } catch (UnixException x) {
                            x.rethrowAsIOException(this.file);
                        } catch (Throwable th) {
                            if (this.file != null) {
                                UnixNativeDispatcher.close(fd);
                            }
                            throw th;
                        }
                    }
                    try {
                        UnixNativeDispatcher.futimes(fd, lastAccessTime.to(TimeUnit.MICROSECONDS), lastModifiedTime.to(TimeUnit.MICROSECONDS));
                    } catch (UnixException x2) {
                        x2.rethrowAsIOException(this.file);
                    }
                    if (this.file != null) {
                        UnixNativeDispatcher.close(fd);
                    }
                    return;
                }
                throw new ClosedDirectoryStreamException();
            } finally {
                UnixSecureDirectoryStream.this.ds.readLock().unlock();
            }
        }
    }

    /* access modifiers changed from: private */
    public class PosixFileAttributeViewImpl extends BasicFileAttributeViewImpl implements PosixFileAttributeView {
        PosixFileAttributeViewImpl(UnixPath file, boolean followLinks) {
            super(file, followLinks);
        }

        private void checkWriteAndUserAccess() {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                checkWriteAccess();
                sm.checkPermission(new RuntimePermission("accessUserInformation"));
            }
        }

        @Override // java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.PosixFileAttributeView, java.nio.file.attribute.AttributeView, java.nio.file.attribute.FileOwnerAttributeView, sun.nio.fs.UnixSecureDirectoryStream.BasicFileAttributeViewImpl
        public String name() {
            return "posix";
        }

        @Override // java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.PosixFileAttributeView, java.nio.file.attribute.PosixFileAttributeView, sun.nio.fs.UnixSecureDirectoryStream.BasicFileAttributeViewImpl
        public PosixFileAttributes readAttributes() throws IOException {
            UnixFileAttributes attrs;
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                if (this.file == null) {
                    UnixSecureDirectoryStream.this.ds.directory().checkRead();
                } else {
                    UnixSecureDirectoryStream.this.ds.directory().resolve((Path) this.file).checkRead();
                }
                sm.checkPermission(new RuntimePermission("accessUserInformation"));
            }
            UnixSecureDirectoryStream.this.ds.readLock().lock();
            try {
                if (UnixSecureDirectoryStream.this.ds.isOpen()) {
                    try {
                        if (this.file == null) {
                            attrs = UnixFileAttributes.get(UnixSecureDirectoryStream.this.dfd);
                        } else {
                            attrs = UnixFileAttributes.get(UnixSecureDirectoryStream.this.dfd, this.file, this.followLinks);
                        }
                        return attrs;
                    } catch (UnixException x) {
                        x.rethrowAsIOException(this.file);
                        UnixSecureDirectoryStream.this.ds.readLock().unlock();
                        return null;
                    }
                } else {
                    throw new ClosedDirectoryStreamException();
                }
            } finally {
                UnixSecureDirectoryStream.this.ds.readLock().unlock();
            }
        }

        /* JADX INFO: finally extract failed */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
            if (r0 >= 0) goto L_0x0038;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
            sun.nio.fs.UnixNativeDispatcher.close(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
            if (r0 >= 0) goto L_0x0038;
         */
        @Override // java.nio.file.attribute.PosixFileAttributeView
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setPermissions(java.util.Set<java.nio.file.attribute.PosixFilePermission> r4) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 122
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.nio.fs.UnixSecureDirectoryStream.PosixFileAttributeViewImpl.setPermissions(java.util.Set):void");
        }

        /* JADX INFO: finally extract failed */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
            if (r0 >= 0) goto L_0x0034;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
            sun.nio.fs.UnixNativeDispatcher.close(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
            if (r0 >= 0) goto L_0x0034;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void setOwners(int r4, int r5) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 118
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.nio.fs.UnixSecureDirectoryStream.PosixFileAttributeViewImpl.setOwners(int, int):void");
        }

        @Override // java.nio.file.attribute.FileOwnerAttributeView
        public UserPrincipal getOwner() throws IOException {
            return readAttributes().owner();
        }

        @Override // java.nio.file.attribute.FileOwnerAttributeView
        public void setOwner(UserPrincipal owner) throws IOException {
            if (!(owner instanceof UnixUserPrincipals.User)) {
                throw new ProviderMismatchException();
            } else if (!(owner instanceof UnixUserPrincipals.Group)) {
                setOwners(((UnixUserPrincipals.User) owner).uid(), -1);
            } else {
                throw new IOException("'owner' parameter can't be a group");
            }
        }

        @Override // java.nio.file.attribute.PosixFileAttributeView
        public void setGroup(GroupPrincipal group) throws IOException {
            if (group instanceof UnixUserPrincipals.Group) {
                setOwners(-1, ((UnixUserPrincipals.Group) group).gid());
                return;
            }
            throw new ProviderMismatchException();
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws IOException {
        CloseGuard closeGuard = this.guard;
        if (closeGuard != null) {
            closeGuard.warnIfOpen();
        }
        close();
    }
}
