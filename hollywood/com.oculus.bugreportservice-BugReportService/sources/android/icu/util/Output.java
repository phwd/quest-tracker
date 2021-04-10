package android.icu.util;

public class Output {
    public Object value;

    public String toString() {
        Object obj = this.value;
        return obj == null ? "null" : obj.toString();
    }

    public Output(Object obj) {
        this.value = obj;
    }
}
