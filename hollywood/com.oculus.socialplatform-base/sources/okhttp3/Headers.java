package okhttp3;

import X.AnonymousClass006;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import okhttp3.internal.http.HttpDate;

public final class Headers {
    public final String[] namesAndValues;

    public static final class Builder {
        public final List<String> namesAndValues = new ArrayList(20);

        public Builder removeAll(String str) {
            int i = 0;
            while (i < this.namesAndValues.size()) {
                if (str.equalsIgnoreCase(this.namesAndValues.get(i))) {
                    this.namesAndValues.remove(i);
                    this.namesAndValues.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        private void checkNameAndValue(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            } else if (!str.isEmpty()) {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    if (charAt <= ' ' || charAt >= 127) {
                        throw new IllegalArgumentException(String.format(Locale.US, "Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
                    }
                }
                if (str2 != null) {
                    int length2 = str2.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        char charAt2 = str2.charAt(i2);
                        if (charAt2 <= 31) {
                            if (charAt2 != '\t') {
                                throw new IllegalArgumentException(String.format(Locale.US, "Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2));
                            }
                        } else if (charAt2 >= 127) {
                            throw new IllegalArgumentException(String.format(Locale.US, "Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2));
                        }
                    }
                    return;
                }
                throw new NullPointerException("value == null");
            } else {
                throw new IllegalArgumentException("name is empty");
            }
        }

        public Headers build() {
            return new Headers(this);
        }

        public String get(String str) {
            int size = this.namesAndValues.size();
            do {
                size -= 2;
                if (size < 0) {
                    return null;
                }
            } while (!str.equalsIgnoreCase(this.namesAndValues.get(size)));
            return this.namesAndValues.get(size + 1);
        }

        public Builder set(String str, String str2) {
            checkNameAndValue(str, str2);
            removeAll(str);
            addLenient(str, str2);
            return this;
        }

        public Builder add(String str) {
            int indexOf = str.indexOf(":");
            if (indexOf != -1) {
                String trim = str.substring(0, indexOf).trim();
                String substring = str.substring(indexOf + 1);
                checkNameAndValue(trim, substring);
                addLenient(trim, substring);
                return this;
            }
            throw new IllegalArgumentException(AnonymousClass006.A07("Unexpected header: ", str));
        }

        public Builder add(String str, String str2) {
            checkNameAndValue(str, str2);
            addLenient(str, str2);
            return this;
        }

        public Builder addLenient(String str) {
            int indexOf = str.indexOf(":", 1);
            if (indexOf != -1) {
                addLenient(str.substring(0, indexOf), str.substring(indexOf + 1));
                return this;
            }
            if (str.startsWith(":")) {
                str = str.substring(1);
            }
            addLenient("", str);
            return this;
        }

        public Builder addLenient(String str, String str2) {
            this.namesAndValues.add(str);
            this.namesAndValues.add(str2.trim());
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Headers) || !Arrays.equals(((Headers) obj).namesAndValues, this.namesAndValues)) {
            return false;
        }
        return true;
    }

    public Date getDate(String str) {
        String str2 = get(this.namesAndValues, str);
        if (str2 != null) {
            return HttpDate.parse(str2);
        }
        return null;
    }

    public int hashCode() {
        return Arrays.hashCode(this.namesAndValues);
    }

    public String name(int i) {
        return this.namesAndValues[i << 1];
    }

    public Set<String> names() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int length = this.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            treeSet.add(this.namesAndValues[i << 1]);
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        Collections.addAll(builder.namesAndValues, this.namesAndValues);
        return builder;
    }

    public int size() {
        return this.namesAndValues.length >> 1;
    }

    public Map<String, List<String>> toMultimap() {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        int length = this.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            String lowerCase = this.namesAndValues[i << 1].toLowerCase(Locale.US);
            List list = (List) treeMap.get(lowerCase);
            if (list == null) {
                list = new ArrayList(2);
                treeMap.put(lowerCase, list);
            }
            list.add(this.namesAndValues[(i << 1) + 1]);
        }
        return treeMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String[] strArr = this.namesAndValues;
        int length = strArr.length >> 1;
        for (int i = 0; i < length; i++) {
            int i2 = i << 1;
            sb.append(strArr[i2]);
            sb.append(": ");
            sb.append(strArr[i2 + 1]);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String value(int i) {
        return this.namesAndValues[(i << 1) + 1];
    }

    public List<String> values(String str) {
        int length = this.namesAndValues.length >> 1;
        ArrayList arrayList = null;
        for (int i = 0; i < length; i++) {
            String[] strArr = this.namesAndValues;
            if (str.equalsIgnoreCase(strArr[i << 1])) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(strArr[(i << 1) + 1]);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public Headers(Builder builder) {
        List<String> list = builder.namesAndValues;
        this.namesAndValues = (String[]) list.toArray(new String[list.size()]);
    }

    public Headers(String[] strArr) {
        this.namesAndValues = strArr;
    }

    public static String get(String[] strArr, String str) {
        int length = strArr.length;
        do {
            length -= 2;
            if (length < 0) {
                return null;
            }
        } while (!str.equalsIgnoreCase(strArr[length]));
        return strArr[length + 1];
    }

    public static Headers of(Map<String, String> map) {
        if (map != null) {
            String[] strArr = new String[(map.size() << 1)];
            int i = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    throw new IllegalArgumentException("Headers cannot be null");
                }
                String trim = entry.getKey().trim();
                String trim2 = entry.getValue().trim();
                if (trim.length() != 0 && trim.indexOf(0) == -1 && trim2.indexOf(0) == -1) {
                    strArr[i] = trim;
                    strArr[i + 1] = trim2;
                    i += 2;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A0B("Unexpected header: ", trim, ": ", trim2));
                }
            }
            return new Headers(strArr);
        }
        throw new NullPointerException("headers == null");
    }

    public static Headers of(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("namesAndValues == null");
        } else if (strArr.length % 2 == 0) {
            String[] strArr2 = (String[]) strArr.clone();
            int i = 0;
            while (true) {
                int length = strArr2.length;
                if (i >= length) {
                    for (int i2 = 0; i2 < length; i2 += 2) {
                        String str = strArr2[i2];
                        String str2 = strArr2[i2 + 1];
                        if (str.length() == 0 || str.indexOf(0) != -1 || str2.indexOf(0) != -1) {
                            throw new IllegalArgumentException(AnonymousClass006.A0B("Unexpected header: ", str, ": ", str2));
                        }
                    }
                    return new Headers(strArr2);
                } else if (strArr2[i] != null) {
                    strArr2[i] = strArr2[i].trim();
                    i++;
                } else {
                    throw new IllegalArgumentException("Headers cannot be null");
                }
            }
        } else {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
    }

    public String get(String str) {
        return get(this.namesAndValues, str);
    }
}
