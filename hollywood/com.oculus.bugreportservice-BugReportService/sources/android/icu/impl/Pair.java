package android.icu.impl;

public class Pair {
    public final Object first;
    public final Object second;

    protected Pair(Object obj, Object obj2) {
        this.first = obj;
        this.second = obj2;
    }

    public static Pair of(Object obj, Object obj2) {
        if (obj != null && obj2 != null) {
            return new Pair(obj, obj2);
        }
        throw new IllegalArgumentException("Pair.of requires non null values.");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return this.first.equals(pair.first) && this.second.equals(pair.second);
    }

    public int hashCode() {
        return (this.first.hashCode() * 37) + this.second.hashCode();
    }
}
