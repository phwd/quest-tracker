package sun.nio.fs;

import dalvik.system.CloseGuard;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* access modifiers changed from: package-private */
public class UnixDirectoryStream implements DirectoryStream<Path> {
    private final UnixPath dir;
    private final long dp;
    private final DirectoryStream.Filter<? super Path> filter;
    private final CloseGuard guard = CloseGuard.get();
    private volatile boolean isClosed;
    private Iterator<Path> iterator;
    private final ReentrantReadWriteLock streamLock = new ReentrantReadWriteLock(true);

    UnixDirectoryStream(UnixPath dir2, long dp2, DirectoryStream.Filter<? super Path> filter2) {
        this.dir = dir2;
        this.dp = dp2;
        this.filter = filter2;
        this.guard.open("close");
    }

    /* access modifiers changed from: protected */
    public final UnixPath directory() {
        return this.dir;
    }

    /* access modifiers changed from: protected */
    public final Lock readLock() {
        return this.streamLock.readLock();
    }

    /* access modifiers changed from: protected */
    public final Lock writeLock() {
        return this.streamLock.writeLock();
    }

    /* access modifiers changed from: protected */
    public final boolean isOpen() {
        return !this.isClosed;
    }

    /* access modifiers changed from: protected */
    public final boolean closeImpl() throws IOException {
        if (this.isClosed) {
            return false;
        }
        this.isClosed = true;
        try {
            UnixNativeDispatcher.closedir(this.dp);
            this.guard.close();
            return true;
        } catch (UnixException x) {
            throw new IOException(x.errorString());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        writeLock().lock();
        try {
            closeImpl();
        } finally {
            writeLock().unlock();
        }
    }

    /* access modifiers changed from: protected */
    public final Iterator<Path> iterator(DirectoryStream<Path> ds) {
        Iterator<Path> it;
        if (!this.isClosed) {
            synchronized (this) {
                if (this.iterator == null) {
                    this.iterator = new UnixDirectoryIterator(ds);
                    it = this.iterator;
                } else {
                    throw new IllegalStateException("Iterator already obtained");
                }
            }
            return it;
        }
        throw new IllegalStateException("Directory stream is closed");
    }

    @Override // java.nio.file.DirectoryStream, java.lang.Iterable
    public Iterator<Path> iterator() {
        return iterator(this);
    }

    /* access modifiers changed from: private */
    public class UnixDirectoryIterator implements Iterator<Path> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean atEof = false;
        private Path nextEntry;
        private final DirectoryStream<Path> stream;

        UnixDirectoryIterator(DirectoryStream<Path> stream2) {
            this.stream = stream2;
        }

        private boolean isSelfOrParent(byte[] nameAsBytes) {
            if (nameAsBytes[0] == 46 && (nameAsBytes.length == 1 || (nameAsBytes.length == 2 && nameAsBytes[1] == 46))) {
                return true;
            }
            return false;
        }

        private Path readNextEntry() {
            UnixPath resolve;
            while (true) {
                byte[] nameAsBytes = null;
                UnixDirectoryStream.this.readLock().lock();
                try {
                    if (UnixDirectoryStream.this.isOpen()) {
                        nameAsBytes = UnixNativeDispatcher.readdir(UnixDirectoryStream.this.dp);
                    }
                    UnixDirectoryStream.this.readLock().unlock();
                    if (nameAsBytes == null) {
                        this.atEof = true;
                        return null;
                    } else if (!isSelfOrParent(nameAsBytes)) {
                        resolve = UnixDirectoryStream.this.dir.resolve(nameAsBytes);
                        try {
                            if (UnixDirectoryStream.this.filter == null || UnixDirectoryStream.this.filter.accept(resolve)) {
                                return resolve;
                            }
                        } catch (IOException ioe) {
                            throw new DirectoryIteratorException(ioe);
                        }
                    }
                } catch (UnixException x) {
                    throw new DirectoryIteratorException(x.asIOException(UnixDirectoryStream.this.dir));
                } catch (Throwable th) {
                    UnixDirectoryStream.this.readLock().unlock();
                    throw th;
                }
            }
            return resolve;
        }

        @Override // java.util.Iterator
        public synchronized boolean hasNext() {
            if (this.nextEntry == null && !this.atEof) {
                this.nextEntry = readNextEntry();
            }
            return this.nextEntry != null;
        }

        @Override // java.util.Iterator
        public synchronized Path next() {
            Path result;
            if (this.nextEntry != null || this.atEof) {
                result = this.nextEntry;
                this.nextEntry = null;
            } else {
                result = readNextEntry();
            }
            if (result == null) {
                throw new NoSuchElementException();
            }
            return result;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
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
