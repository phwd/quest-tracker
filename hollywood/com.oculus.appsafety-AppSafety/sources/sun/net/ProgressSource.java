package sun.net;

import java.net.URL;

public class ProgressSource {
    private boolean connected;
    private String contentType;
    private long expected;
    private long lastProgress;
    private String method;
    private long progress;
    private ProgressMonitor progressMonitor;
    private State state;
    private int threshold;
    private URL url;

    public enum State {
        NEW,
        CONNECTED,
        UPDATE,
        DELETE
    }

    public ProgressSource(URL url2, String method2) {
        this(url2, method2, -1);
    }

    public ProgressSource(URL url2, String method2, long expected2) {
        this.progress = 0;
        this.lastProgress = 0;
        this.expected = -1;
        this.connected = false;
        this.threshold = 8192;
        this.url = url2;
        this.method = method2;
        this.contentType = "content/unknown";
        this.progress = 0;
        this.lastProgress = 0;
        this.expected = expected2;
        this.state = State.NEW;
        this.progressMonitor = ProgressMonitor.getDefault();
        this.threshold = this.progressMonitor.getProgressUpdateThreshold();
    }

    public boolean connected() {
        if (this.connected) {
            return true;
        }
        this.connected = true;
        this.state = State.CONNECTED;
        return false;
    }

    public void close() {
        this.state = State.DELETE;
    }

    public URL getURL() {
        return this.url;
    }

    public String getMethod() {
        return this.method;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String ct) {
        this.contentType = ct;
    }

    public long getProgress() {
        return this.progress;
    }

    public long getExpected() {
        return this.expected;
    }

    public State getState() {
        return this.state;
    }

    public void beginTracking() {
        this.progressMonitor.registerSource(this);
    }

    public void finishTracking() {
        this.progressMonitor.unregisterSource(this);
    }

    public void updateProgress(long latestProgress, long expectedProgress) {
        this.lastProgress = this.progress;
        this.progress = latestProgress;
        this.expected = expectedProgress;
        if (!connected()) {
            this.state = State.CONNECTED;
        } else {
            this.state = State.UPDATE;
        }
        long j = this.lastProgress;
        int i = this.threshold;
        if (j / ((long) i) != this.progress / ((long) i)) {
            this.progressMonitor.updateProgress(this);
        }
        long j2 = this.expected;
        if (j2 != -1) {
            long j3 = this.progress;
            if (j3 >= j2 && j3 != 0) {
                close();
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return getClass().getName() + "[url=" + ((Object) this.url) + ", method=" + this.method + ", state=" + ((Object) this.state) + ", content-type=" + this.contentType + ", progress=" + this.progress + ", expected=" + this.expected + "]";
    }
}
