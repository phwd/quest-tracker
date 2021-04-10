package bolts;

public class Capture<T> {
    public T value;

    public T get() {
        return this.value;
    }

    public void set(T t) {
        this.value = t;
    }

    public Capture() {
    }

    public Capture(T t) {
        this.value = t;
    }
}
