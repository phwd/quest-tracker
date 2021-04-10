package sun.net;

import java.net.URL;
import java.util.EventObject;
import sun.net.ProgressSource;

public class ProgressEvent extends EventObject {
    private String contentType;
    private long expected;
    private String method;
    private long progress;
    private ProgressSource.State state;
    private URL url;

    public ProgressEvent(ProgressSource progressSource, URL url2, String str, String str2, ProgressSource.State state2, long j, long j2) {
        super(progressSource);
        this.url = url2;
        this.method = str;
        this.contentType = str2;
        this.progress = j;
        this.expected = j2;
        this.state = state2;
    }

    public String toString() {
        return ProgressEvent.class.getName() + "[url=" + this.url + ", method=" + this.method + ", state=" + this.state + ", content-type=" + this.contentType + ", progress=" + this.progress + ", expected=" + this.expected + "]";
    }
}
