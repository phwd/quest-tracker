package okhttp3;

public interface Call extends Cloneable {
    void enqueue(Callback callback);
}
