package java.util;

public abstract class TimerTask implements Runnable {
    static final int CANCELLED = 3;
    static final int EXECUTED = 2;
    static final int SCHEDULED = 1;
    static final int VIRGIN = 0;
    final Object lock = new Object();
    long nextExecutionTime;
    long period = 0;
    int state = 0;

    @Override // java.lang.Runnable
    public abstract void run();

    protected TimerTask() {
    }

    public boolean cancel() {
        boolean result;
        synchronized (this.lock) {
            result = true;
            if (this.state != 1) {
                result = false;
            }
            this.state = 3;
        }
        return result;
    }

    public long scheduledExecutionTime() {
        long j;
        synchronized (this.lock) {
            if (this.period < 0) {
                j = this.nextExecutionTime + this.period;
            } else {
                j = this.nextExecutionTime - this.period;
            }
        }
        return j;
    }
}
