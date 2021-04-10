package com.google.common.base;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Joiner {
    private final String separator;

    public static Joiner on(String str) {
        return new Joiner(str);
    }

    public static Joiner on(char c) {
        return new Joiner(String.valueOf(c));
    }

    private Joiner(String str) {
        Preconditions.checkNotNull(str);
        this.separator = str;
    }

    private Joiner(Joiner joiner) {
        this.separator = joiner.separator;
    }

    public <A extends Appendable> A appendTo(A a, Iterator<?> it) throws IOException {
        Preconditions.checkNotNull(a);
        if (it.hasNext()) {
            a.append(toString(it.next()));
            while (it.hasNext()) {
                a.append(this.separator);
                a.append(toString(it.next()));
            }
        }
        return a;
    }

    public final StringBuilder appendTo(StringBuilder sb, Iterable<?> iterable) {
        appendTo(sb, iterable.iterator());
        return sb;
    }

    public final StringBuilder appendTo(StringBuilder sb, Iterator<?> it) {
        try {
            appendTo((Appendable) sb, it);
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public Joiner useForNull(final String str) {
        Preconditions.checkNotNull(str);
        return new Joiner(this) {
            /* class com.google.common.base.Joiner.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.base.Joiner
            public CharSequence toString(Object obj) {
                return obj == null ? str : Joiner.this.toString(obj);
            }

            @Override // com.google.common.base.Joiner
            public Joiner useForNull(String str) {
                throw new UnsupportedOperationException("already specified useForNull");
            }
        };
    }

    public MapJoiner withKeyValueSeparator(String str) {
        return new MapJoiner(str);
    }

    public static final class MapJoiner {
        private final Joiner joiner;
        private final String keyValueSeparator;

        private MapJoiner(Joiner joiner2, String str) {
            this.joiner = joiner2;
            Preconditions.checkNotNull(str);
            this.keyValueSeparator = str;
        }

        public StringBuilder appendTo(StringBuilder sb, Map<?, ?> map) {
            appendTo(sb, map.entrySet());
            return sb;
        }

        public <A extends Appendable> A appendTo(A a, Iterator<? extends Map.Entry<?, ?>> it) throws IOException {
            Preconditions.checkNotNull(a);
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                a.append(this.joiner.toString(entry.getKey()));
                a.append(this.keyValueSeparator);
                a.append(this.joiner.toString(entry.getValue()));
                while (it.hasNext()) {
                    a.append(this.joiner.separator);
                    Map.Entry entry2 = (Map.Entry) it.next();
                    a.append(this.joiner.toString(entry2.getKey()));
                    a.append(this.keyValueSeparator);
                    a.append(this.joiner.toString(entry2.getValue()));
                }
            }
            return a;
        }

        public StringBuilder appendTo(StringBuilder sb, Iterable<? extends Map.Entry<?, ?>> iterable) {
            appendTo(sb, iterable.iterator());
            return sb;
        }

        public StringBuilder appendTo(StringBuilder sb, Iterator<? extends Map.Entry<?, ?>> it) {
            try {
                appendTo((Appendable) sb, it);
                return sb;
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public CharSequence toString(Object obj) {
        Preconditions.checkNotNull(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }
}
