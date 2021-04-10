package java.util;

public final class Optional {
    private static final Optional EMPTY = new Optional();
    private final Object value = null;

    private Optional() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return false;
        }
        return Objects.equals(this.value, ((Optional) obj).value);
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    public String toString() {
        Object obj = this.value;
        if (obj == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", obj);
    }
}
