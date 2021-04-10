package java.util;

public final class StringJoiner {
    private final String delimiter;
    private String emptyValue;
    private final String prefix;
    private final String suffix;
    private StringBuilder value;

    public StringJoiner(CharSequence charSequence) {
        this(charSequence, "", "");
    }

    public StringJoiner(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        Objects.requireNonNull(charSequence2, "The prefix must not be null");
        Objects.requireNonNull(charSequence, "The delimiter must not be null");
        Objects.requireNonNull(charSequence3, "The suffix must not be null");
        this.prefix = charSequence2.toString();
        this.delimiter = charSequence.toString();
        this.suffix = charSequence3.toString();
        this.emptyValue = this.prefix + this.suffix;
    }

    public String toString() {
        if (this.value == null) {
            return this.emptyValue;
        }
        if (this.suffix.equals("")) {
            return this.value.toString();
        }
        int length = this.value.length();
        StringBuilder sb = this.value;
        sb.append(this.suffix);
        String sb2 = sb.toString();
        this.value.setLength(length);
        return sb2;
    }

    public StringJoiner add(CharSequence charSequence) {
        prepareBuilder().append(charSequence);
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
}
