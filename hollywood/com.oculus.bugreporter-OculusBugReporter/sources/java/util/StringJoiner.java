package java.util;

public final class StringJoiner {
    private final String delimiter;
    private String emptyValue;
    private final String prefix;
    private final String suffix;
    private StringBuilder value;

    public StringJoiner(CharSequence delimiter2) {
        this(delimiter2, "", "");
    }

    public StringJoiner(CharSequence delimiter2, CharSequence prefix2, CharSequence suffix2) {
        Objects.requireNonNull(prefix2, "The prefix must not be null");
        Objects.requireNonNull(delimiter2, "The delimiter must not be null");
        Objects.requireNonNull(suffix2, "The suffix must not be null");
        this.prefix = prefix2.toString();
        this.delimiter = delimiter2.toString();
        this.suffix = suffix2.toString();
        this.emptyValue = this.prefix + this.suffix;
    }

    public StringJoiner setEmptyValue(CharSequence emptyValue2) {
        this.emptyValue = ((CharSequence) Objects.requireNonNull(emptyValue2, "The empty value must not be null")).toString();
        return this;
    }

    public String toString() {
        if (this.value == null) {
            return this.emptyValue;
        }
        if (this.suffix.equals("")) {
            return this.value.toString();
        }
        int initialLength = this.value.length();
        StringBuilder sb = this.value;
        sb.append(this.suffix);
        String result = sb.toString();
        this.value.setLength(initialLength);
        return result;
    }

    public StringJoiner add(CharSequence newElement) {
        prepareBuilder().append(newElement);
        return this;
    }

    public StringJoiner merge(StringJoiner other) {
        Objects.requireNonNull(other);
        StringBuilder sb = other.value;
        if (sb != null) {
            prepareBuilder().append((CharSequence) other.value, other.prefix.length(), sb.length());
        }
        return this;
    }

    private StringBuilder prepareBuilder() {
        StringBuilder sb = this.value;
        if (sb != null) {
            sb.append(this.delimiter);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.prefix);
            this.value = sb2;
        }
        return this.value;
    }

    public int length() {
        StringBuilder sb = this.value;
        if (sb != null) {
            return sb.length() + this.suffix.length();
        }
        return this.emptyValue.length();
    }
}
