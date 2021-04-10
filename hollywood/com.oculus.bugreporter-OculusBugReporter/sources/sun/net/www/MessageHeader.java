package sun.net.www;

import android.icu.text.PluralRules;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class MessageHeader {
    private String[] keys;
    private int nkeys;
    private String[] values;

    public MessageHeader() {
        grow();
    }

    public MessageHeader(InputStream is) throws IOException {
        parseHeader(is);
    }

    public synchronized String getHeaderNamesInList() {
        StringJoiner joiner;
        joiner = new StringJoiner(",");
        for (int i = 0; i < this.nkeys; i++) {
            joiner.add(this.keys[i]);
        }
        return joiner.toString();
    }

    public synchronized void reset() {
        this.keys = null;
        this.values = null;
        this.nkeys = 0;
        grow();
    }

    public synchronized String findValue(String k) {
        if (k == null) {
            int i = this.nkeys;
            do {
                i--;
                if (i >= 0) {
                }
            } while (this.keys[i] != null);
            return this.values[i];
        }
        int i2 = this.nkeys;
        do {
            i2--;
            if (i2 >= 0) {
            }
        } while (!k.equalsIgnoreCase(this.keys[i2]));
        return this.values[i2];
        return null;
    }

    public synchronized int getKey(String k) {
        int i = this.nkeys;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.keys[i] == k || (k != null && k.equalsIgnoreCase(this.keys[i]))) {
            }
        }
        return i;
    }

    public synchronized String getKey(int n) {
        if (n >= 0) {
            if (n < this.nkeys) {
                return this.keys[n];
            }
        }
        return null;
    }

    public synchronized String getValue(int n) {
        if (n >= 0) {
            if (n < this.nkeys) {
                return this.values[n];
            }
        }
        return null;
    }

    public synchronized String findNextValue(String k, String v) {
        boolean foundV = false;
        if (k != null) {
            int i = this.nkeys;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                } else if (k.equalsIgnoreCase(this.keys[i])) {
                    if (foundV) {
                        return this.values[i];
                    } else if (this.values[i] == v) {
                        foundV = true;
                    }
                }
            }
        } else {
            int i2 = this.nkeys;
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                } else if (this.keys[i2] == null) {
                    if (foundV) {
                        return this.values[i2];
                    } else if (this.values[i2] == v) {
                        foundV = true;
                    }
                }
            }
        }
        return null;
    }

    public boolean filterNTLMResponses(String k) {
        int i;
        boolean found = false;
        int i2 = 0;
        while (true) {
            if (i2 >= this.nkeys) {
                break;
            }
            if (k.equalsIgnoreCase(this.keys[i2])) {
                String[] strArr = this.values;
                if (strArr[i2] != null && strArr[i2].length() > 5 && this.values[i2].substring(0, 5).equalsIgnoreCase("NTLM ")) {
                    found = true;
                    break;
                }
            }
            i2++;
        }
        if (found) {
            int j = 0;
            int i3 = 0;
            while (true) {
                i = this.nkeys;
                if (i3 >= i) {
                    break;
                }
                if (!k.equalsIgnoreCase(this.keys[i3]) || (!"Negotiate".equalsIgnoreCase(this.values[i3]) && !"Kerberos".equalsIgnoreCase(this.values[i3]))) {
                    if (i3 != j) {
                        String[] strArr2 = this.keys;
                        strArr2[j] = strArr2[i3];
                        String[] strArr3 = this.values;
                        strArr3[j] = strArr3[i3];
                    }
                    j++;
                }
                i3++;
            }
            if (j != i) {
                this.nkeys = j;
                return true;
            }
        }
        return false;
    }

    class HeaderIterator implements Iterator<String> {
        boolean haveNext = false;
        int index = 0;
        String key;
        Object lock;
        int next = -1;

        public HeaderIterator(String k, Object lock2) {
            this.key = k;
            this.lock = lock2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            synchronized (this.lock) {
                if (this.haveNext) {
                    return true;
                }
                while (this.index < MessageHeader.this.nkeys) {
                    if (this.key.equalsIgnoreCase(MessageHeader.this.keys[this.index])) {
                        this.haveNext = true;
                        int i = this.index;
                        this.index = i + 1;
                        this.next = i;
                        return true;
                    }
                    this.index++;
                }
                return false;
            }
        }

        @Override // java.util.Iterator
        public String next() {
            synchronized (this.lock) {
                if (this.haveNext) {
                    this.haveNext = false;
                    return MessageHeader.this.values[this.next];
                } else if (hasNext()) {
                    return next();
                } else {
                    throw new NoSuchElementException("No more elements");
                }
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove not allowed");
        }
    }

    public Iterator<String> multiValueIterator(String k) {
        return new HeaderIterator(k, this);
    }

    public synchronized Map<String, List<String>> getHeaders() {
        return getHeaders(null);
    }

    public synchronized Map<String, List<String>> getHeaders(String[] excludeList) {
        return filterAndAddHeaders(excludeList, null);
    }

    public synchronized Map<String, List<String>> filterAndAddHeaders(String[] excludeList, Map<String, List<String>> include) {
        Map<String, List<String>> m;
        boolean skipIt = false;
        m = new HashMap<>();
        int i = this.nkeys;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            if (excludeList != null) {
                int j = 0;
                while (true) {
                    if (j >= excludeList.length) {
                        break;
                    }
                    if (excludeList[j] != null && excludeList[j].equalsIgnoreCase(this.keys[i])) {
                        skipIt = true;
                        break;
                    }
                    j++;
                }
            }
            if (!skipIt) {
                List<String> l = m.get(this.keys[i]);
                if (l == null) {
                    l = new ArrayList<>();
                    m.put(this.keys[i], l);
                }
                l.add(this.values[i]);
            } else {
                skipIt = false;
            }
        }
        if (include != null) {
            for (Map.Entry<String, List<String>> entry : include.entrySet()) {
                List<String> l2 = m.get(entry.getKey());
                if (l2 == null) {
                    l2 = new ArrayList<>();
                    m.put(entry.getKey(), l2);
                }
                l2.addAll(entry.getValue());
            }
        }
        for (String key : m.keySet()) {
            m.put(key, Collections.unmodifiableList(m.get(key)));
        }
        return Collections.unmodifiableMap(m);
    }

    public synchronized void print(PrintStream p) {
        for (int i = 0; i < this.nkeys; i++) {
            if (this.keys[i] != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.keys[i]);
                sb.append(this.values[i] != null ? PluralRules.KEYWORD_RULE_SEPARATOR + this.values[i] : "");
                sb.append("\r\n");
                p.print(sb.toString());
            }
        }
        p.print("\r\n");
        p.flush();
    }

    public synchronized void add(String k, String v) {
        grow();
        this.keys[this.nkeys] = k;
        this.values[this.nkeys] = v;
        this.nkeys++;
    }

    public synchronized void prepend(String k, String v) {
        grow();
        for (int i = this.nkeys; i > 0; i--) {
            this.keys[i] = this.keys[i - 1];
            this.values[i] = this.values[i - 1];
        }
        this.keys[0] = k;
        this.values[0] = v;
        this.nkeys++;
    }

    public synchronized void set(int i, String k, String v) {
        grow();
        if (i >= 0) {
            if (i >= this.nkeys) {
                add(k, v);
            } else {
                this.keys[i] = k;
                this.values[i] = v;
            }
        }
    }

    private void grow() {
        String[] strArr = this.keys;
        if (strArr == null || this.nkeys >= strArr.length) {
            int i = this.nkeys;
            String[] nk = new String[(i + 4)];
            String[] nv = new String[(i + 4)];
            String[] strArr2 = this.keys;
            if (strArr2 != null) {
                System.arraycopy(strArr2, 0, nk, 0, i);
            }
            String[] strArr3 = this.values;
            if (strArr3 != null) {
                System.arraycopy(strArr3, 0, nv, 0, this.nkeys);
            }
            this.keys = nk;
            this.values = nv;
        }
    }

    public synchronized void remove(String k) {
        int i = 0;
        if (k == null) {
            while (i < this.nkeys) {
                while (this.keys[i] == null && i < this.nkeys) {
                    for (int j = i; j < this.nkeys - 1; j++) {
                        this.keys[j] = this.keys[j + 1];
                        this.values[j] = this.values[j + 1];
                    }
                    this.nkeys--;
                }
                i++;
            }
        } else {
            while (i < this.nkeys) {
                while (k.equalsIgnoreCase(this.keys[i]) && i < this.nkeys) {
                    for (int j2 = i; j2 < this.nkeys - 1; j2++) {
                        this.keys[j2] = this.keys[j2 + 1];
                        this.values[j2] = this.values[j2 + 1];
                    }
                    this.nkeys--;
                }
                i++;
            }
        }
    }

    public synchronized void set(String k, String v) {
        int i = this.nkeys;
        do {
            i--;
            if (i < 0) {
                add(k, v);
                return;
            }
        } while (!k.equalsIgnoreCase(this.keys[i]));
        this.values[i] = v;
    }

    public synchronized void setIfNotSet(String k, String v) {
        if (findValue(k) == null) {
            add(k, v);
        }
    }

    public static String canonicalID(String id) {
        if (id == null) {
            return "";
        }
        int st = 0;
        int len = id.length();
        boolean substr = false;
        while (st < len && ((c = id.charAt(st)) == 60 || c <= 32)) {
            st++;
            substr = true;
        }
        while (st < len) {
            int c = id.charAt(len - 1);
            if (c != 62 && c > 32) {
                break;
            }
            len--;
            substr = true;
        }
        return substr ? id.substring(st, len) : id;
    }

    public void parseHeader(InputStream is) throws IOException {
        synchronized (this) {
            this.nkeys = 0;
        }
        mergeHeader(is);
    }

    /* JADX INFO: Multiple debug info for r4v12 int: [D('ns' char[]), D('len' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x006c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mergeHeader(java.io.InputStream r13) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 176
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.www.MessageHeader.mergeHeader(java.io.InputStream):void");
    }

    public synchronized String toString() {
        String result;
        result = super.toString() + this.nkeys + " pairs: ";
        int i = 0;
        while (i < this.keys.length && i < this.nkeys) {
            result = result + "{" + this.keys[i] + PluralRules.KEYWORD_RULE_SEPARATOR + this.values[i] + "}";
            i++;
        }
        return result;
    }
}
