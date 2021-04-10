package java.util.logging;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LogRecord implements Serializable {
    private static final AtomicLong globalSequenceNumber = new AtomicLong(0);
    private static final AtomicInteger nextThreadId = new AtomicInteger(1073741823);
    private static final long serialVersionUID = 5372048053134512534L;
    private static final ThreadLocal threadIds = new ThreadLocal();
    private Level level;
    private String loggerName;
    private String message;
    private long millis = System.currentTimeMillis();
    private transient boolean needToInferCaller = true;
    private transient ResourceBundle resourceBundle;
    private String resourceBundleName;
    private long sequenceNumber = globalSequenceNumber.getAndIncrement();
    private String sourceClassName;
    private String sourceMethodName;
    private int threadID = defaultThreadID();
    private Throwable thrown;

    private int defaultThreadID() {
        long id = Thread.currentThread().getId();
        if (id < 1073741823) {
            return (int) id;
        }
        Integer num = (Integer) threadIds.get();
        if (num == null) {
            num = Integer.valueOf(nextThreadId.getAndIncrement());
            threadIds.set(num);
        }
        return num.intValue();
    }

    public LogRecord(Level level2, String str) {
        level2.getClass();
        this.level = level2;
        this.message = str;
    }

    public void setLoggerName(String str) {
        this.loggerName = str;
    }

    public void setResourceBundle(ResourceBundle resourceBundle2) {
        this.resourceBundle = resourceBundle2;
    }

    public void setResourceBundleName(String str) {
        this.resourceBundleName = str;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setThrown(Throwable th) {
        this.thrown = th;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
