package sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.nio.channels.Channel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* access modifiers changed from: package-private */
/* compiled from: FileLockTable */
public class SharedFileLockTable extends FileLockTable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static ConcurrentHashMap<FileKey, List<FileLockReference>> lockMap = new ConcurrentHashMap<>();
    private static ReferenceQueue<FileLock> queue = new ReferenceQueue<>();
    private final Channel channel;
    private final FileKey fileKey;

    /* access modifiers changed from: private */
    /* compiled from: FileLockTable */
    public static class FileLockReference extends WeakReference<FileLock> {
        private FileKey fileKey;

        FileLockReference(FileLock referent, ReferenceQueue<FileLock> queue, FileKey key) {
            super(referent, queue);
            this.fileKey = key;
        }

        /* access modifiers changed from: package-private */
        public FileKey fileKey() {
            return this.fileKey;
        }
    }

    SharedFileLockTable(Channel channel2, FileDescriptor fd) throws IOException {
        this.channel = channel2;
        this.fileKey = FileKey.create(fd);
    }

    /* JADX INFO: Multiple debug info for r1v7 java.util.List<sun.nio.ch.SharedFileLockTable$FileLockReference>: [D('current' java.util.List<sun.nio.ch.SharedFileLockTable$FileLockReference>), D('list' java.util.List<sun.nio.ch.SharedFileLockTable$FileLockReference>)] */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        checkList(r0, r10.position(), r10.size());
        r0.add(new sun.nio.ch.SharedFileLockTable.FileLockReference(r10, sun.nio.ch.SharedFileLockTable.queue, r9.fileKey));
     */
    @Override // sun.nio.ch.FileLockTable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void add(java.nio.channels.FileLock r10) throws java.nio.channels.OverlappingFileLockException {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.SharedFileLockTable.add(java.nio.channels.FileLock):void");
    }

    private void removeKeyIfEmpty(FileKey fk, List<FileLockReference> list) {
        if (list.isEmpty()) {
            lockMap.remove(fk);
        }
    }

    @Override // sun.nio.ch.FileLockTable
    public void remove(FileLock fl) {
        List<FileLockReference> list = lockMap.get(this.fileKey);
        if (list != null) {
            synchronized (list) {
                int index = 0;
                while (true) {
                    if (index >= list.size()) {
                        break;
                    }
                    FileLockReference ref = list.get(index);
                    if (((FileLock) ref.get()) == fl) {
                        ref.clear();
                        list.remove(index);
                        break;
                    }
                    index++;
                }
            }
        }
    }

    @Override // sun.nio.ch.FileLockTable
    public List<FileLock> removeAll() {
        List<FileLock> result = new ArrayList<>();
        List<FileLockReference> list = lockMap.get(this.fileKey);
        if (list != null) {
            synchronized (list) {
                int index = 0;
                while (index < list.size()) {
                    FileLockReference ref = list.get(index);
                    FileLock lock = (FileLock) ref.get();
                    if (lock == null || lock.acquiredBy() != this.channel) {
                        index++;
                    } else {
                        ref.clear();
                        list.remove(index);
                        result.add(lock);
                    }
                }
                removeKeyIfEmpty(this.fileKey, list);
            }
        }
        return result;
    }

    @Override // sun.nio.ch.FileLockTable
    public void replace(FileLock fromLock, FileLock toLock) {
        List<FileLockReference> list = lockMap.get(this.fileKey);
        synchronized (list) {
            int index = 0;
            while (true) {
                if (index >= list.size()) {
                    break;
                }
                FileLockReference ref = list.get(index);
                if (((FileLock) ref.get()) == fromLock) {
                    ref.clear();
                    list.set(index, new FileLockReference(toLock, queue, this.fileKey));
                    break;
                }
                index++;
            }
        }
    }

    private void checkList(List<FileLockReference> list, long position, long size) throws OverlappingFileLockException {
        for (FileLockReference ref : list) {
            FileLock fl = (FileLock) ref.get();
            if (fl != null && fl.overlaps(position, size)) {
                throw new OverlappingFileLockException();
            }
        }
    }

    private void removeStaleEntries() {
        while (true) {
            FileLockReference ref = (FileLockReference) queue.poll();
            if (ref != null) {
                FileKey fk = ref.fileKey();
                List<FileLockReference> list = lockMap.get(fk);
                if (list != null) {
                    synchronized (list) {
                        list.remove(ref);
                        removeKeyIfEmpty(fk, list);
                    }
                }
            } else {
                return;
            }
        }
    }
}
