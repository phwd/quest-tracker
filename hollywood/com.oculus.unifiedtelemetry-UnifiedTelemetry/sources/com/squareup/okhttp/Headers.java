package com.squareup.okhttp;

import X.AnonymousClass06;
import com.squareup.okhttp.internal.http.HttpDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
            String str3;
            char charAt;
            if (str == null) {
                str3 = "name == null";
            } else if (!str.isEmpty()) {
                int length = str.length();
                int i = 0;
                while (true) {
                    if (i < length) {
                        charAt = str.charAt(i);
                        if (charAt <= 31 || charAt >= 127) {
                            str3 = String.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str);
                        } else {
                            i++;
                        }
                    } else if (str2 != null) {
                        int length2 = str2.length();
                        for (int i2 = 0; i2 < length2; i2++) {
                            char charAt2 = str2.charAt(i2);
                            if (charAt2 <= 31 || charAt2 >= 127) {
                                str3 = String.format("Unexpected char %#04x at %d in header value: %s", Integer.valueOf(charAt2), Integer.valueOf(i2), str2);
                            }
                        }
                        return;
                    } else {
                        str3 = "value == null";
                    }
                }
                str3 = String.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str);
            } else {
                str3 = "name is empty";
            }
            throw new IllegalArgumentException(str3);
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
            throw new IllegalArgumentException(AnonymousClass06.A04("Unexpected header: ", str));
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

    public Date getDate(String str) {
        String str2 = get(this.namesAndValues, str);
        if (str2 != null) {
            return HttpDate.parse(str2);
        }
        return null;
    }

    public String name(int i) {
        int i2 = i << 1;
        if (i2 < 0) {
            return null;
        }
        String[] strArr = this.namesAndValues;
        if (i2 < strArr.length) {
            return strArr[i2];
        }
        return null;
    }

    public Set<String> names() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int length = this.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            treeSet.add(name(i));
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
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int length = this.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            String name = name(i);
            List list = (List) linkedHashMap.get(name);
            if (list == null) {
                list = new ArrayList(2);
                linkedHashMap.put(name, list);
            }
            list.add(value(i));
        }
        return linkedHashMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int length = this.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            sb.append(name(i));
            sb.append(": ");
            sb.append(value(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    public String value(int i) {
        int i2 = (i << 1) + 1;
        if (i2 < 0) {
            return null;
        }
        String[] strArr = this.namesAndValues;
        if (i2 < strArr.length) {
            return strArr[i2];
        }
        return null;
    }

    public List<String> values(String str) {
        int length = this.namesAndValues.length >> 1;
        ArrayList arrayList = null;
        for (int i = 0; i < length; i++) {
            if (str.equalsIgnoreCase(name(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(value(i));
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
        String str;
        if (map != null) {
            String[] strArr = new String[(map.size() << 1)];
            int i = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    str = "Headers cannot be null";
                } else {
                    String trim = entry.getKey().trim();
                    String trim2 = entry.getValue().trim();
                    if (trim.length() != 0 && trim.indexOf(0) == -1 && trim2.indexOf(0) == -1) {
                        strArr[i] = trim;
                        strArr[i + 1] = trim2;
                        i += 2;
                    } else {
                        str = AnonymousClass06.A06("Unexpected header: ", trim, ": ", trim2);
                    }
                }
            }
            return new Headers(strArr);
        }
        str = "Expected map with header names and values";
        throw new IllegalArgumentException(str);
    }

    public static Headers of(String... strArr) {
        String str;
        if (strArr == null || strArr.length % 2 != 0) {
            str = "Expected alternating header names and values";
        } else {
            String[] strArr2 = (String[]) strArr.clone();
            int i = 0;
            while (true) {
                int length = strArr2.length;
                if (i < length) {
                    if (strArr2[i] == null) {
                        str = "Headers cannot be null";
                        break;
                    }
                    strArr2[i] = strArr2[i].trim();
                    i++;
                } else {
                    for (int i2 = 0; i2 < length; i2 += 2) {
                        String str2 = strArr2[i2];
                        String str3 = strArr2[i2 + 1];
                        if (str2.length() == 0 || str2.indexOf(0) != -1 || str3.indexOf(0) != -1) {
                            str = AnonymousClass06.A06("Unexpected header: ", str2, ": ", str3);
                        }
                    }
                    return new Headers(strArr2);
                }
            }
        }
        throw new IllegalArgumentException(str);
    }

    public String get(String str) {
        return get(this.namesAndValues, str);
    }
}
