package sun.nio.fs;

import com.sun.nio.file.SensitivityWatchEventModifier;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
public class PollingWatchService extends AbstractWatchService {
    private final Map<Object, PollingWatchKey> map = new HashMap();
    private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        /* class sun.nio.fs.PollingWatchService.AnonymousClass1 */

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    PollingWatchService() {
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.fs.AbstractWatchService
    public WatchKey register(final Path path, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
        final Set<WatchEvent.Kind<?>> eventSet = new HashSet<>(events.length);
        for (WatchEvent.Kind<?> event : events) {
            if (event == StandardWatchEventKinds.ENTRY_CREATE || event == StandardWatchEventKinds.ENTRY_MODIFY || event == StandardWatchEventKinds.ENTRY_DELETE) {
                eventSet.add(event);
            } else if (event != StandardWatchEventKinds.OVERFLOW) {
                if (event == null) {
                    throw new NullPointerException("An element in event set is 'null'");
                }
                throw new UnsupportedOperationException(event.name());
            }
        }
        if (!eventSet.isEmpty()) {
            final SensitivityWatchEventModifier sensivity = SensitivityWatchEventModifier.MEDIUM;
            if (modifiers.length > 0) {
                for (WatchEvent.Modifier modifier : modifiers) {
                    if (modifier == null) {
                        throw new NullPointerException();
                    } else if (modifier instanceof SensitivityWatchEventModifier) {
                        sensivity = (SensitivityWatchEventModifier) modifier;
                    } else {
                        throw new UnsupportedOperationException("Modifier not supported");
                    }
                }
            }
            if (isOpen()) {
                try {
                    return (WatchKey) AccessController.doPrivileged(new PrivilegedExceptionAction<PollingWatchKey>() {
                        /* class sun.nio.fs.PollingWatchService.AnonymousClass2 */

                        @Override // java.security.PrivilegedExceptionAction
                        public PollingWatchKey run() throws IOException {
                            return PollingWatchService.this.doPrivilegedRegister(path, eventSet, sensivity);
                        }
                    });
                } catch (PrivilegedActionException pae) {
                    Throwable cause = pae.getCause();
                    if (cause == null || !(cause instanceof IOException)) {
                        throw new AssertionError(pae);
                    }
                    throw ((IOException) cause);
                }
            } else {
                throw new ClosedWatchServiceException();
            }
        } else {
            throw new IllegalArgumentException("No events to register");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private PollingWatchKey doPrivilegedRegister(Path path, Set<? extends WatchEvent.Kind<?>> events, SensitivityWatchEventModifier sensivity) throws IOException {
        PollingWatchKey watchKey;
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class, new LinkOption[0]);
        if (attrs.isDirectory()) {
            Object fileKey = attrs.fileKey();
            if (fileKey != null) {
                synchronized (closeLock()) {
                    if (isOpen()) {
                        synchronized (this.map) {
                            watchKey = this.map.get(fileKey);
                            if (watchKey == null) {
                                watchKey = new PollingWatchKey(path, this, fileKey);
                                this.map.put(fileKey, watchKey);
                            } else {
                                watchKey.disable();
                            }
                        }
                        watchKey.enable(events, (long) sensivity.sensitivityValueInSeconds());
                    } else {
                        throw new ClosedWatchServiceException();
                    }
                }
                return watchKey;
            }
            throw new AssertionError((Object) "File keys must be supported");
        }
        throw new NotDirectoryException(path.toString());
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.fs.AbstractWatchService
    public void implClose() throws IOException {
        synchronized (this.map) {
            for (Map.Entry<Object, PollingWatchKey> entry : this.map.entrySet()) {
                PollingWatchKey watchKey = entry.getValue();
                watchKey.disable();
                watchKey.invalidate();
            }
            this.map.clear();
        }
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            /* class sun.nio.fs.PollingWatchService.AnonymousClass3 */

            @Override // java.security.PrivilegedAction
            public Void run() {
                PollingWatchService.this.scheduledExecutor.shutdown();
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public static class CacheEntry {
        private long lastModified;
        private int lastTickCount;

        CacheEntry(long lastModified2, int lastTickCount2) {
            this.lastModified = lastModified2;
            this.lastTickCount = lastTickCount2;
        }

        /* access modifiers changed from: package-private */
        public int lastTickCount() {
            return this.lastTickCount;
        }

        /* access modifiers changed from: package-private */
        public long lastModified() {
            return this.lastModified;
        }

        /* access modifiers changed from: package-private */
        public void update(long lastModified2, int tickCount) {
            this.lastModified = lastModified2;
            this.lastTickCount = tickCount;
        }
    }

    /* access modifiers changed from: private */
    public class PollingWatchKey extends AbstractWatchKey {
        private Map<Path, CacheEntry> entries = new HashMap();
        private Set<? extends WatchEvent.Kind<?>> events;
        private final Object fileKey;
        private ScheduledFuture<?> poller;
        private int tickCount = 0;
        private volatile boolean valid = true;

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x004f, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0050, code lost:
            if (r1 != null) goto L_0x0052;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            r1.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
            r11.addSuppressed(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x005a, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        PollingWatchKey(java.nio.file.Path r12, sun.nio.fs.PollingWatchService r13, java.lang.Object r14) throws java.io.IOException {
            /*
                r10 = this;
                sun.nio.fs.PollingWatchService.this = r11
                r10.<init>(r12, r13)
                r10.fileKey = r14
                r11 = 1
                r10.valid = r11
                r0 = 0
                r10.tickCount = r0
                java.util.HashMap r1 = new java.util.HashMap
                r1.<init>()
                r10.entries = r1
                java.nio.file.DirectoryStream r1 = java.nio.file.Files.newDirectoryStream(r12)     // Catch:{ DirectoryIteratorException -> 0x005b }
                java.util.Iterator r2 = r1.iterator()     // Catch:{ all -> 0x004d }
            L_0x001c:
                boolean r3 = r2.hasNext()     // Catch:{ all -> 0x004d }
                if (r3 == 0) goto L_0x0048
                java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x004d }
                java.nio.file.Path r3 = (java.nio.file.Path) r3     // Catch:{ all -> 0x004d }
                java.nio.file.LinkOption[] r4 = new java.nio.file.LinkOption[r11]     // Catch:{ all -> 0x004d }
                java.nio.file.LinkOption r5 = java.nio.file.LinkOption.NOFOLLOW_LINKS     // Catch:{ all -> 0x004d }
                r4[r0] = r5     // Catch:{ all -> 0x004d }
                java.nio.file.attribute.FileTime r4 = java.nio.file.Files.getLastModifiedTime(r3, r4)     // Catch:{ all -> 0x004d }
                long r4 = r4.toMillis()     // Catch:{ all -> 0x004d }
                java.util.Map<java.nio.file.Path, sun.nio.fs.PollingWatchService$CacheEntry> r6 = r10.entries     // Catch:{ all -> 0x004d }
                java.nio.file.Path r7 = r3.getFileName()     // Catch:{ all -> 0x004d }
                sun.nio.fs.PollingWatchService$CacheEntry r8 = new sun.nio.fs.PollingWatchService$CacheEntry     // Catch:{ all -> 0x004d }
                int r9 = r10.tickCount     // Catch:{ all -> 0x004d }
                r8.<init>(r4, r9)     // Catch:{ all -> 0x004d }
                r6.put(r7, r8)     // Catch:{ all -> 0x004d }
                goto L_0x001c
            L_0x0048:
                r1.close()
                return
            L_0x004d:
                r11 = move-exception
                throw r11     // Catch:{ all -> 0x004f }
            L_0x004f:
                r0 = move-exception
                if (r1 == 0) goto L_0x005a
                r1.close()     // Catch:{ all -> 0x0056 }
                goto L_0x005a
            L_0x0056:
                r2 = move-exception
                r11.addSuppressed(r2)
            L_0x005a:
                throw r0
            L_0x005b:
                r11 = move-exception
                java.io.IOException r0 = r11.getCause()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.nio.fs.PollingWatchService.PollingWatchKey.<init>(sun.nio.fs.PollingWatchService, java.nio.file.Path, sun.nio.fs.PollingWatchService, java.lang.Object):void");
        }

        /* access modifiers changed from: package-private */
        public Object fileKey() {
            return this.fileKey;
        }

        @Override // java.nio.file.WatchKey
        public boolean isValid() {
            return this.valid;
        }

        /* access modifiers changed from: package-private */
        public void invalidate() {
            this.valid = false;
        }

        /* access modifiers changed from: package-private */
        public void enable(Set<? extends WatchEvent.Kind<?>> events2, long period) {
            synchronized (this) {
                this.events = events2;
                this.poller = PollingWatchService.this.scheduledExecutor.scheduleAtFixedRate(new Runnable() {
                    /* class sun.nio.fs.PollingWatchService.PollingWatchKey.AnonymousClass1 */

                    @Override // java.lang.Runnable
                    public void run() {
                        PollingWatchKey.this.poll();
                    }
                }, period, period, TimeUnit.SECONDS);
            }
        }

        /* access modifiers changed from: package-private */
        public void disable() {
            synchronized (this) {
                if (this.poller != null) {
                    this.poller.cancel(false);
                }
            }
        }

        @Override // java.nio.file.WatchKey
        public void cancel() {
            this.valid = false;
            synchronized (PollingWatchService.this.map) {
                PollingWatchService.this.map.remove(fileKey());
            }
            disable();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x00cc  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void poll() {
            /*
            // Method dump skipped, instructions count: 263
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.nio.fs.PollingWatchService.PollingWatchKey.poll():void");
        }
    }
}
