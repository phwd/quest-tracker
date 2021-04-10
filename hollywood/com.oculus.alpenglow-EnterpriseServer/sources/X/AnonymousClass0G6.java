package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.io.File;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0G6  reason: invalid class name */
public final class AnonymousClass0G6 {
    public static final Map<String, Lock> A04 = new HashMap();
    public FileChannel A00;
    public final File A01;
    public final Lock A02;
    public final boolean A03;

    public AnonymousClass0G6(@NonNull String str, @NonNull File file, boolean z) {
        Lock lock;
        File file2 = new File(file, AnonymousClass006.A05(str, ".lck"));
        this.A01 = file2;
        String absolutePath = file2.getAbsolutePath();
        Map<String, Lock> map = A04;
        synchronized (map) {
            lock = map.get(absolutePath);
            if (lock == null) {
                lock = new ReentrantLock();
                map.put(absolutePath, lock);
            }
        }
        this.A02 = lock;
        this.A03 = z;
    }
}
