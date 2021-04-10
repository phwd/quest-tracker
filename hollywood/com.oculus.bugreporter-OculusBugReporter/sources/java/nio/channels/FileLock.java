package java.nio.channels;

import android.icu.impl.number.Padder;
import java.io.IOException;

public abstract class FileLock implements AutoCloseable {
    private final Channel channel;
    private final long position;
    private final boolean shared;
    private final long size;

    public abstract boolean isValid();

    public abstract void release() throws IOException;

    protected FileLock(FileChannel channel2, long position2, long size2, boolean shared2) {
        if (position2 < 0) {
            throw new IllegalArgumentException("Negative position");
        } else if (size2 < 0) {
            throw new IllegalArgumentException("Negative size");
        } else if (position2 + size2 >= 0) {
            this.channel = channel2;
            this.position = position2;
            this.size = size2;
            this.shared = shared2;
        } else {
            throw new IllegalArgumentException("Negative position + size");
        }
    }

    protected FileLock(AsynchronousFileChannel channel2, long position2, long size2, boolean shared2) {
        if (position2 < 0) {
            throw new IllegalArgumentException("Negative position");
        } else if (size2 < 0) {
            throw new IllegalArgumentException("Negative size");
        } else if (position2 + size2 >= 0) {
            this.channel = channel2;
            this.position = position2;
            this.size = size2;
            this.shared = shared2;
        } else {
            throw new IllegalArgumentException("Negative position + size");
        }
    }

    public final FileChannel channel() {
        Channel channel2 = this.channel;
        if (channel2 instanceof FileChannel) {
            return (FileChannel) channel2;
        }
        return null;
    }

    public Channel acquiredBy() {
        return this.channel;
    }

    public final long position() {
        return this.position;
    }

    public final long size() {
        return this.size;
    }

    public final boolean isShared() {
        return this.shared;
    }

    public final boolean overlaps(long position2, long size2) {
        long j = this.position;
        if (position2 + size2 > j && j + this.size > position2) {
            return true;
        }
        return false;
    }

    @Override // java.lang.AutoCloseable
    public final void close() throws IOException {
        release();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("[");
        sb.append(this.position);
        sb.append(":");
        sb.append(this.size);
        sb.append(Padder.FALLBACK_PADDING_STRING);
        sb.append(this.shared ? "shared" : "exclusive");
        sb.append(Padder.FALLBACK_PADDING_STRING);
        sb.append(isValid() ? "valid" : "invalid");
        sb.append("]");
        return sb.toString();
    }
}
