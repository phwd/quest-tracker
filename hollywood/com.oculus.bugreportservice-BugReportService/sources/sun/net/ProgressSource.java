package sun.net;

import java.net.URL;

public class ProgressSource {
    private boolean connected = false;
    private String contentType;
    private long expected = -1;
    private long lastProgress = 0;
    private String method;
    private long progress = 0;
    private ProgressMonitor progressMonitor;
    private State state;
    private int threshold = 8192;
    private URL url;

    public enum State {
        NEW,
        CONNECTED,
        UPDATE,
        DELETE
    }

    public ProgressSource(URL url2, String str, long j) {
        this.url = url2;
        this.method = str;
        this.contentType = "content/unknown";
        this.progress = 0;
        this.lastProgress = 0;
        this.expected = j;
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

    public void updateProgress(long j, long j2) {
        this.lastProgress = this.progress;
        this.progress = j;
        this.expected = j2;
        if (!connected()) {
            this.state = State.CONNECTED;
        } else {
            this.state = State.UPDATE;
        }
        long j3 = this.lastProgress;
        int i = this.threshold;
        if (j3 / ((long) i) != this.progress / ((long) i)) {
            this.progressMonitor.updateProgress(this);
        }
        long j4 = this.expected;
        if (j4 != -1) {
            long j5 = this.progress;
            if (j5 >= j4 && j5 != 0) {
                close();
            }
        }
    }

    public Object clone() {
        return super.clone();
    }

    public String toString() {
        return ProgressSource.class.getName() + "[url=" + this.url + ", method=" + this.method + ", state=" + this.state + ", content-type=" + this.contentType + ", progress=" + this.progress + ", expected=" + this.expected + "]";
    }
}
