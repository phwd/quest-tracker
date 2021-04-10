package sun.nio.fs;

import android.icu.impl.coll.CollationFastLatin;
import com.sun.nio.file.SensitivityWatchEventModifier;
import dalvik.system.CloseGuard;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import sun.misc.Unsafe;

/* access modifiers changed from: package-private */
public class LinuxWatchService extends AbstractWatchService {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private final Poller poller;

    private static native void configureBlocking(int i, boolean z) throws UnixException;

    /* access modifiers changed from: private */
    public static native int[] eventOffsets();

    /* access modifiers changed from: private */
    public static native int eventSize();

    /* access modifiers changed from: private */
    public static native int inotifyAddWatch(int i, long j, int i2) throws UnixException;

    private static native int inotifyInit() throws UnixException;

    /* access modifiers changed from: private */
    public static native void inotifyRmWatch(int i, int i2) throws UnixException;

    /* access modifiers changed from: private */
    public static native int poll(int i, int i2) throws UnixException;

    private static native void socketpair(int[] iArr) throws UnixException;

    LinuxWatchService(UnixFileSystem fs) throws IOException {
        String msg;
        try {
            int ifd = inotifyInit();
            int[] sp = new int[2];
            try {
                configureBlocking(ifd, false);
                socketpair(sp);
                configureBlocking(sp[0], false);
                this.poller = new Poller(fs, this, ifd, sp);
                this.poller.start();
            } catch (UnixException x) {
                UnixNativeDispatcher.close(ifd);
                throw new IOException(x.errorString());
            }
        } catch (UnixException x2) {
            if (x2.errno() == UnixConstants.EMFILE) {
                msg = "User limit of inotify instances reached or too many open files";
            } else {
                msg = x2.errorString();
            }
            throw new IOException(msg);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.fs.AbstractWatchService
    public WatchKey register(Path dir, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
        return this.poller.register(dir, events, modifiers);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.fs.AbstractWatchService
    public void implClose() throws IOException {
        this.poller.close();
    }

    /* access modifiers changed from: private */
    public static class LinuxWatchKey extends AbstractWatchKey {
        private final int ifd;
        private volatile int wd;

        LinuxWatchKey(UnixPath dir, LinuxWatchService watcher, int ifd2, int wd2) {
            super(dir, watcher);
            this.ifd = ifd2;
            this.wd = wd2;
        }

        /* access modifiers changed from: package-private */
        public int descriptor() {
            return this.wd;
        }

        /* access modifiers changed from: package-private */
        public void invalidate(boolean remove) {
            if (remove) {
                try {
                    LinuxWatchService.inotifyRmWatch(this.ifd, this.wd);
                } catch (UnixException e) {
                }
            }
            this.wd = -1;
        }

        @Override // java.nio.file.WatchKey
        public boolean isValid() {
            return this.wd != -1;
        }

        @Override // java.nio.file.WatchKey
        public void cancel() {
            if (isValid()) {
                ((LinuxWatchService) watcher()).poller.cancel(this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static class Poller extends AbstractPoller {
        private static final int BUFFER_SIZE = 8192;
        private static final int IN_ATTRIB = 4;
        private static final int IN_CREATE = 256;
        private static final int IN_DELETE = 512;
        private static final int IN_IGNORED = 32768;
        private static final int IN_MODIFY = 2;
        private static final int IN_MOVED_FROM = 64;
        private static final int IN_MOVED_TO = 128;
        private static final int IN_Q_OVERFLOW = 16384;
        private static final int IN_UNMOUNT = 8192;
        private static final int OFFSETOF_LEN;
        private static final int OFFSETOF_MASK;
        private static final int OFFSETOF_NAME;
        private static final int OFFSETOF_WD;
        private static final int SIZEOF_INOTIFY_EVENT = LinuxWatchService.eventSize();
        private static final int[] offsets = LinuxWatchService.eventOffsets();
        private final long address;
        private final UnixFileSystem fs;
        private final CloseGuard guard = CloseGuard.get();
        private final int ifd;
        private final int[] socketpair;
        private final LinuxWatchService watcher;
        private final Map<Integer, LinuxWatchKey> wdToKey;

        static {
            int[] iArr = offsets;
            OFFSETOF_WD = iArr[0];
            OFFSETOF_MASK = iArr[1];
            OFFSETOF_LEN = iArr[3];
            OFFSETOF_NAME = iArr[4];
        }

        Poller(UnixFileSystem fs2, LinuxWatchService watcher2, int ifd2, int[] sp) {
            this.fs = fs2;
            this.watcher = watcher2;
            this.ifd = ifd2;
            this.socketpair = sp;
            this.wdToKey = new HashMap();
            this.address = LinuxWatchService.unsafe.allocateMemory(8192);
            this.guard.open("close");
        }

        /* access modifiers changed from: package-private */
        @Override // sun.nio.fs.AbstractPoller
        public void wakeup() throws IOException {
            try {
                UnixNativeDispatcher.write(this.socketpair[1], this.address, 1);
            } catch (UnixException x) {
                throw new IOException(x.errorString());
            }
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        @Override // sun.nio.fs.AbstractPoller
        public Object implRegister(Path obj, Set<? extends WatchEvent.Kind<?>> events, WatchEvent.Modifier... modifiers) {
            UnixPath dir = (UnixPath) obj;
            int mask = 0;
            for (WatchEvent.Kind<?> event : events) {
                if (event == StandardWatchEventKinds.ENTRY_CREATE) {
                    mask |= CollationFastLatin.LATIN_LIMIT;
                } else if (event == StandardWatchEventKinds.ENTRY_DELETE) {
                    mask |= 576;
                } else if (event == StandardWatchEventKinds.ENTRY_MODIFY) {
                    mask |= 6;
                }
            }
            if (modifiers.length > 0) {
                for (WatchEvent.Modifier modifier : modifiers) {
                    if (modifier == null) {
                        return new NullPointerException();
                    }
                    if (!(modifier instanceof SensitivityWatchEventModifier)) {
                        return new UnsupportedOperationException("Modifier not supported");
                    }
                }
            }
            try {
                if (!UnixFileAttributes.get(dir, true).isDirectory()) {
                    return new NotDirectoryException(dir.getPathForExceptionMessage());
                }
                try {
                    NativeBuffer buffer = NativeBuffers.asNativeBuffer(dir.getByteArrayForSysCalls());
                    try {
                        int wd = LinuxWatchService.inotifyAddWatch(this.ifd, buffer.address(), mask);
                        buffer.release();
                        LinuxWatchKey key = this.wdToKey.get(Integer.valueOf(wd));
                        if (key != null) {
                            return key;
                        }
                        LinuxWatchKey key2 = new LinuxWatchKey(dir, this.watcher, this.ifd, wd);
                        this.wdToKey.put(Integer.valueOf(wd), key2);
                        return key2;
                    } catch (Throwable th) {
                        buffer.release();
                        throw th;
                    }
                } catch (UnixException x) {
                    if (x.errno() == UnixConstants.ENOSPC) {
                        return new IOException("User limit of inotify watches reached");
                    }
                    return x.asIOException(dir);
                }
            } catch (UnixException x2) {
                return x2.asIOException(dir);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // sun.nio.fs.AbstractPoller
        public void implCancelKey(WatchKey obj) {
            LinuxWatchKey key = (LinuxWatchKey) obj;
            if (key.isValid()) {
                this.wdToKey.remove(Integer.valueOf(key.descriptor()));
                key.invalidate(true);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // sun.nio.fs.AbstractPoller
        public void implCloseAll() {
            this.guard.close();
            for (Map.Entry<Integer, LinuxWatchKey> entry : this.wdToKey.entrySet()) {
                entry.getValue().invalidate(true);
            }
            this.wdToKey.clear();
            LinuxWatchService.unsafe.freeMemory(this.address);
            UnixNativeDispatcher.close(this.socketpair[0]);
            UnixNativeDispatcher.close(this.socketpair[1]);
            UnixNativeDispatcher.close(this.ifd);
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            try {
                if (this.guard != null) {
                    this.guard.warnIfOpen();
                }
                close();
            } finally {
                super.finalize();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int bytesRead;
            int nReady;
            while (true) {
                try {
                    int nReady2 = LinuxWatchService.poll(this.ifd, this.socketpair[0]);
                    try {
                        bytesRead = UnixNativeDispatcher.read(this.ifd, this.address, 8192);
                    } catch (UnixException x) {
                        if (x.errno() == UnixConstants.EAGAIN) {
                            bytesRead = 0;
                        } else {
                            throw x;
                        }
                    }
                    if (nReady2 > 1 || (nReady2 == 1 && bytesRead == 0)) {
                        try {
                            UnixNativeDispatcher.read(this.socketpair[0], this.address, 8192);
                            if (processRequests()) {
                                return;
                            }
                        } catch (UnixException x2) {
                            if (x2.errno() != UnixConstants.EAGAIN) {
                                throw x2;
                            }
                        }
                    }
                    int offset = 0;
                    while (offset < bytesRead) {
                        long event = this.address + ((long) offset);
                        int wd = LinuxWatchService.unsafe.getInt(((long) OFFSETOF_WD) + event);
                        int mask = LinuxWatchService.unsafe.getInt(((long) OFFSETOF_MASK) + event);
                        int len = LinuxWatchService.unsafe.getInt(((long) OFFSETOF_LEN) + event);
                        UnixPath name = null;
                        if (len > 0) {
                            int actual = len;
                            while (actual > 0) {
                                if (LinuxWatchService.unsafe.getByte(((((long) OFFSETOF_NAME) + event) + ((long) actual)) - 1) != 0) {
                                    break;
                                }
                                actual--;
                            }
                            if (actual > 0) {
                                byte[] buf = new byte[actual];
                                int i = 0;
                                while (i < actual) {
                                    buf[i] = LinuxWatchService.unsafe.getByte(((long) OFFSETOF_NAME) + event + ((long) i));
                                    i++;
                                    nReady2 = nReady2;
                                    event = event;
                                }
                                nReady = nReady2;
                                name = new UnixPath(this.fs, buf);
                            } else {
                                nReady = nReady2;
                            }
                        } else {
                            nReady = nReady2;
                        }
                        processEvent(wd, mask, name);
                        offset += SIZEOF_INOTIFY_EVENT + len;
                        nReady2 = nReady;
                    }
                } catch (UnixException x3) {
                    x3.printStackTrace();
                    return;
                }
            }
        }

        private WatchEvent.Kind<?> maskToEventKind(int mask) {
            if ((mask & 2) > 0) {
                return StandardWatchEventKinds.ENTRY_MODIFY;
            }
            if ((mask & 4) > 0) {
                return StandardWatchEventKinds.ENTRY_MODIFY;
            }
            if ((mask & 256) > 0) {
                return StandardWatchEventKinds.ENTRY_CREATE;
            }
            if ((mask & 128) > 0) {
                return StandardWatchEventKinds.ENTRY_CREATE;
            }
            if ((mask & 512) > 0) {
                return StandardWatchEventKinds.ENTRY_DELETE;
            }
            if ((mask & 64) > 0) {
                return StandardWatchEventKinds.ENTRY_DELETE;
            }
            return null;
        }

        private void processEvent(int wd, int mask, UnixPath name) {
            WatchEvent.Kind<?> kind;
            if ((mask & 16384) > 0) {
                for (Map.Entry<Integer, LinuxWatchKey> entry : this.wdToKey.entrySet()) {
                    entry.getValue().signalEvent(StandardWatchEventKinds.OVERFLOW, null);
                }
                return;
            }
            LinuxWatchKey key = this.wdToKey.get(Integer.valueOf(wd));
            if (key != null) {
                if ((32768 & mask) > 0) {
                    this.wdToKey.remove(Integer.valueOf(wd));
                    key.invalidate(false);
                    key.signal();
                } else if (name != null && (kind = maskToEventKind(mask)) != null) {
                    key.signalEvent(kind, name);
                }
            }
        }
    }
}
