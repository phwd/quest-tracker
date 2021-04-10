package sun.util.logging;

public interface LoggingProxy {
    Object getLogger(String str);

    String getProperty(String str);

    boolean isLoggable(Object obj, Object obj2);

    void log(Object obj, Object obj2, String str);

    Object parseLevel(String str);

    void setLevel(Object obj, Object obj2);
}
