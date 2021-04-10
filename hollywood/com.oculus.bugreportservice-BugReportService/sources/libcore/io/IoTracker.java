package libcore.io;

import dalvik.system.BlockGuard;

public final class IoTracker {
    private boolean isOpen = true;
    private Mode mode = Mode.READ;
    private int opCount;
    private int totalByteCount;

    public enum Mode {
        READ,
        WRITE
    }

    public void trackIo(int i) {
        this.opCount++;
        this.totalByteCount += i;
        if (this.isOpen && this.opCount > 10 && this.totalByteCount < 5120) {
            BlockGuard.getThreadPolicy().onUnbufferedIO();
            this.isOpen = false;
        }
    }
}
