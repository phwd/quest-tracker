package oculus.internal.functional;

public final class Pair<L, R> {
    public final L left;
    public final R right;

    public Pair(L left2, R right2) {
        this.left = left2;
        this.right = right2;
    }

    public static <X, Y> Pair<X, Y> Pair(X left2, Y right2) {
        return new Pair<>(left2, right2);
    }

    public L left() {
        return this.left;
    }

    public R right() {
        return this.right;
    }

    public int hashCode() {
        return ((this.left.hashCode() + 217) * 31) + this.right.hashCode();
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Pair)) {
            return false;
        }
        Pair o = (Pair) other;
        if (!this.left.equals(o.left) || !this.right.equals(o.right)) {
            return false;
        }
        return true;
    }
}
