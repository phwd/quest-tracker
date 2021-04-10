package dalvik.system;

public final class CloseGuard {
    private static volatile Tracker currentTracker = null;
    private static volatile Reporter reporter = new DefaultReporter();
    private static volatile boolean stackAndTrackingEnabled = true;
    private Object closerNameOrAllocationInfo;

    public interface Reporter {
    }

    public interface Tracker {
        void close(Throwable th);

        void open(Throwable th);
    }

    public static CloseGuard get() {
        return new CloseGuard();
    }

    private CloseGuard() {
    }

    public void open(String str) {
        if (str == null) {
            throw new NullPointerException("closer == null");
        } else if (!stackAndTrackingEnabled) {
            this.closerNameOrAllocationInfo = str;
        } else {
            Throwable th = new Throwable("Explicit termination method '" + str + "' not called");
            this.closerNameOrAllocationInfo = th;
            Tracker tracker = currentTracker;
            if (tracker != null) {
                tracker.open(th);
            }
        }
    }

    public void close() {
        Tracker tracker = currentTracker;
        if (tracker != null) {
            Object obj = this.closerNameOrAllocationInfo;
            if (obj instanceof Throwable) {
                tracker.close((Throwable) obj);
            }
        }
        this.closerNameOrAllocationInfo = null;
    }

    private static final class DefaultReporter implements Reporter {
        private DefaultReporter() {
        }
    }
}
