package java.nio.channels;

public abstract class FileLock implements AutoCloseable {
    private final Channel channel;
    private final long position;
    private final boolean shared;
    private final long size;

    public abstract boolean isValid();

    public abstract void release();

    public Channel acquiredBy() {
        return this.channel;
    }

    public final long position() {
        return this.position;
    }

    public final long size() {
        return this.size;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        release();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("[");
        sb.append(this.position);
        sb.append(":");
        sb.append(this.size);
        sb.append(" ");
        sb.append(this.shared ? "shared" : "exclusive");
        sb.append(" ");
        sb.append(isValid() ? "valid" : "invalid");
        sb.append("]");
        return sb.toString();
    }
}
