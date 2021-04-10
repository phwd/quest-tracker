package libcore.io;

import android.system.OsConstants;
import java.io.FileDescriptor;
import java.nio.ByteOrder;

public final class MemoryMappedFile implements AutoCloseable {
    private final long address;
    private boolean closed;
    private final int size;

    public MemoryMappedFile(long j, long j2) {
        this.address = j;
        if (j2 < 0 || j2 > 2147483647L) {
            throw new IllegalArgumentException("Unsupported file size=" + j2);
        }
        this.size = (int) j2;
    }

    public static MemoryMappedFile mmapRO(String str) {
        FileDescriptor open = Libcore.os.open(str, OsConstants.O_RDONLY, 0);
        try {
            long j = Libcore.os.fstat(open).st_size;
            return new MemoryMappedFile(Libcore.os.mmap(0, j, OsConstants.PROT_READ, OsConstants.MAP_SHARED, open, 0), j);
        } finally {
            Libcore.os.close(open);
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (!this.closed) {
            this.closed = true;
            Libcore.os.munmap(this.address, (long) this.size);
        }
    }

    public BufferIterator bigEndianIterator() {
        return new NioBufferIterator(this, this.address, this.size, ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN);
    }

    /* access modifiers changed from: package-private */
    public void checkNotClosed() {
        if (this.closed) {
            throw new IllegalStateException("MemoryMappedFile is closed");
        }
    }
}
