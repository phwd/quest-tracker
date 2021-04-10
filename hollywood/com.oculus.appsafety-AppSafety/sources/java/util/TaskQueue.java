package java.util;

/* access modifiers changed from: package-private */
/* compiled from: Timer */
public class TaskQueue {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private TimerTask[] queue = new TimerTask[128];
    private int size = 0;

    TaskQueue() {
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public void add(TimerTask task) {
        int i = this.size + 1;
        TimerTask[] timerTaskArr = this.queue;
        if (i == timerTaskArr.length) {
            this.queue = (TimerTask[]) Arrays.copyOf(timerTaskArr, timerTaskArr.length * 2);
        }
        TimerTask[] timerTaskArr2 = this.queue;
        int i2 = this.size + 1;
        this.size = i2;
        timerTaskArr2[i2] = task;
        fixUp(this.size);
    }

    /* access modifiers changed from: package-private */
    public TimerTask getMin() {
        return this.queue[1];
    }

    /* access modifiers changed from: package-private */
    public TimerTask get(int i) {
        return this.queue[i];
    }

    /* access modifiers changed from: package-private */
    public void removeMin() {
        TimerTask[] timerTaskArr = this.queue;
        int i = this.size;
        timerTaskArr[1] = timerTaskArr[i];
        this.size = i - 1;
        timerTaskArr[i] = null;
        fixDown(1);
    }

    /* access modifiers changed from: package-private */
    public void quickRemove(int i) {
        TimerTask[] timerTaskArr = this.queue;
        int i2 = this.size;
        timerTaskArr[i] = timerTaskArr[i2];
        this.size = i2 - 1;
        timerTaskArr[i2] = null;
    }

    /* access modifiers changed from: package-private */
    public void rescheduleMin(long newTime) {
        this.queue[1].nextExecutionTime = newTime;
        fixDown(1);
    }

    /* access modifiers changed from: package-private */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        for (int i = 1; i <= this.size; i++) {
            this.queue[i] = null;
        }
        this.size = 0;
    }

    private void fixUp(int k) {
        while (k > 1) {
            int j = k >> 1;
            if (this.queue[j].nextExecutionTime > this.queue[k].nextExecutionTime) {
                TimerTask[] timerTaskArr = this.queue;
                TimerTask tmp = timerTaskArr[j];
                timerTaskArr[j] = timerTaskArr[k];
                timerTaskArr[k] = tmp;
                k = j;
            } else {
                return;
            }
        }
    }

    private void fixDown(int k) {
        while (true) {
            int i = k << 1;
            int j = i;
            int i2 = this.size;
            if (i <= i2 && j > 0) {
                if (j < i2 && this.queue[j].nextExecutionTime > this.queue[j + 1].nextExecutionTime) {
                    j++;
                }
                if (this.queue[k].nextExecutionTime > this.queue[j].nextExecutionTime) {
                    TimerTask[] timerTaskArr = this.queue;
                    TimerTask tmp = timerTaskArr[j];
                    timerTaskArr[j] = timerTaskArr[k];
                    timerTaskArr[k] = tmp;
                    k = j;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void heapify() {
        for (int i = this.size / 2; i >= 1; i--) {
            fixDown(i);
        }
    }
}
