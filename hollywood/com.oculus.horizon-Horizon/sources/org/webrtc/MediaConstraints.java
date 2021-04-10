package org.webrtc;

import X.AnonymousClass006;
import java.util.LinkedList;
import java.util.List;

public class MediaConstraints {
    public final List<KeyValuePair> mandatory = new LinkedList();
    public final List<KeyValuePair> optional = new LinkedList();

    public static class KeyValuePair {
        public final String key;
        public final String value;

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                KeyValuePair keyValuePair = (KeyValuePair) obj;
                if (!this.key.equals(keyValuePair.key) || !this.value.equals(keyValuePair.value)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            return this.key.hashCode() + this.value.hashCode();
        }

        public String toString() {
            return AnonymousClass006.A07(this.key, ": ", this.value);
        }

        public KeyValuePair(String str, String str2) {
            this.key = str;
            this.value = str2;
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static String stringifyKeyValuePairList(List<KeyValuePair> list) {
        StringBuilder sb = new StringBuilder("[");
        for (KeyValuePair keyValuePair : list) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(keyValuePair.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public String toString() {
        return AnonymousClass006.A08("mandatory: ", stringifyKeyValuePairList(this.mandatory), ", optional: ", stringifyKeyValuePairList(this.optional));
    }
}
