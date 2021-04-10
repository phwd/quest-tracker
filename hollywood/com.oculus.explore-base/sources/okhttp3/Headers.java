package okhttp3;

import com.oculus.common.build.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.Util;

public final class Headers {
    private final String[] namesAndValues;

    Headers(Builder builder) {
        this.namesAndValues = (String[]) builder.namesAndValues.toArray(new String[builder.namesAndValues.size()]);
    }

    public String get(String name) {
        return get(this.namesAndValues, name);
    }

    public int size() {
        return this.namesAndValues.length / 2;
    }

    public String name(int index) {
        return this.namesAndValues[index * 2];
    }

    public String value(int index) {
        return this.namesAndValues[(index * 2) + 1];
    }

    public List<String> values(String name) {
        List<String> result = null;
        int size = size();
        for (int i = 0; i < size; i++) {
            if (name.equalsIgnoreCase(name(i))) {
                if (result == null) {
                    result = new ArrayList<>(2);
                }
                result.add(value(i));
            }
        }
        if (result != null) {
            return Collections.unmodifiableList(result);
        }
        return Collections.emptyList();
    }

    public Builder newBuilder() {
        Builder result = new Builder();
        Collections.addAll(result.namesAndValues, this.namesAndValues);
        return result;
    }

    public boolean equals(Object other) {
        return (other instanceof Headers) && Arrays.equals(((Headers) other).namesAndValues, this.namesAndValues);
    }

    public int hashCode() {
        return Arrays.hashCode(this.namesAndValues);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        int size = size();
        for (int i = 0; i < size; i++) {
            result.append(name(i)).append(": ").append(value(i)).append("\n");
        }
        return result.toString();
    }

    private static String get(String[] namesAndValues2, String name) {
        for (int i = namesAndValues2.length - 2; i >= 0; i -= 2) {
            if (name.equalsIgnoreCase(namesAndValues2[i])) {
                return namesAndValues2[i + 1];
            }
        }
        return null;
    }

    static void checkName(String name) {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (name.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        } else {
            int length = name.length();
            for (int i = 0; i < length; i++) {
                char c = name.charAt(i);
                if (c <= ' ' || c >= 127) {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(c), Integer.valueOf(i), name));
                }
            }
        }
    }

    static void checkValue(String value, String name) {
        if (value == null) {
            throw new NullPointerException("value for name " + name + " == null");
        }
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char c = value.charAt(i);
            if ((c <= 31 && c != '\t') || c >= 127) {
                throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(c), Integer.valueOf(i), name, value));
            }
        }
    }

    public static final class Builder {
        final List<String> namesAndValues = new ArrayList(20);

        /* access modifiers changed from: package-private */
        public Builder addLenient(String line) {
            int index = line.indexOf(":", 1);
            if (index != -1) {
                return addLenient(line.substring(0, index), line.substring(index + 1));
            }
            if (line.startsWith(":")) {
                return addLenient(BuildConfig.PROVIDER_SUFFIX, line.substring(1));
            }
            return addLenient(BuildConfig.PROVIDER_SUFFIX, line);
        }

        public Builder add(String name, String value) {
            Headers.checkName(name);
            Headers.checkValue(value, name);
            return addLenient(name, value);
        }

        /* access modifiers changed from: package-private */
        public Builder addLenient(String name, String value) {
            this.namesAndValues.add(name);
            this.namesAndValues.add(value.trim());
            return this;
        }

        public Builder removeAll(String name) {
            int i = 0;
            while (i < this.namesAndValues.size()) {
                if (name.equalsIgnoreCase(this.namesAndValues.get(i))) {
                    this.namesAndValues.remove(i);
                    this.namesAndValues.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public Builder set(String name, String value) {
            Headers.checkName(name);
            Headers.checkValue(value, name);
            removeAll(name);
            addLenient(name, value);
            return this;
        }

        public Headers build() {
            return new Headers(this);
        }
    }
}
