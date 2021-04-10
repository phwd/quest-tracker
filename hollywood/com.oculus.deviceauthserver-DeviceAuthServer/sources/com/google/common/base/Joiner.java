package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@GwtCompatible
public class Joiner {
    private final String separator;

    public static Joiner on(String separator2) {
        return new Joiner(separator2);
    }

    public static Joiner on(char separator2) {
        return new Joiner(String.valueOf(separator2));
    }

    private Joiner(String separator2) {
        this.separator = (String) Preconditions.checkNotNull(separator2);
    }

    private Joiner(Joiner prototype) {
        this.separator = prototype.separator;
    }

    public <A extends Appendable> A appendTo(A appendable, Iterable<?> parts) throws IOException {
        return (A) appendTo(appendable, parts.iterator());
    }

    public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
        Preconditions.checkNotNull(appendable);
        if (parts.hasNext()) {
            appendable.append(toString(parts.next()));
            while (parts.hasNext()) {
                appendable.append(this.separator);
                appendable.append(toString(parts.next()));
            }
        }
        return appendable;
    }

    public final <A extends Appendable> A appendTo(A appendable, Object[] parts) throws IOException {
        return (A) appendTo(appendable, Arrays.asList(parts));
    }

    public final <A extends Appendable> A appendTo(A appendable, @Nullable Object first, @Nullable Object second, Object... rest) throws IOException {
        return (A) appendTo(appendable, iterable(first, second, rest));
    }

    public final StringBuilder appendTo(StringBuilder builder, Iterable<?> parts) {
        return appendTo(builder, parts.iterator());
    }

    public final StringBuilder appendTo(StringBuilder builder, Iterator<?> parts) {
        try {
            appendTo((Appendable) builder, parts);
            return builder;
        } catch (IOException impossible) {
            throw new AssertionError(impossible);
        }
    }

    public final StringBuilder appendTo(StringBuilder builder, Object[] parts) {
        return appendTo(builder, (Iterable<?>) Arrays.asList(parts));
    }

    public final StringBuilder appendTo(StringBuilder builder, @Nullable Object first, @Nullable Object second, Object... rest) {
        return appendTo(builder, iterable(first, second, rest));
    }

    public final String join(Iterable<?> parts) {
        return join(parts.iterator());
    }

    public final String join(Iterator<?> parts) {
        return appendTo(new StringBuilder(), parts).toString();
    }

    public final String join(Object[] parts) {
        return join(Arrays.asList(parts));
    }

    public final String join(@Nullable Object first, @Nullable Object second, Object... rest) {
        return join(iterable(first, second, rest));
    }

    @CheckReturnValue
    public Joiner useForNull(final String nullText) {
        Preconditions.checkNotNull(nullText);
        return new Joiner(this) {
            /* class com.google.common.base.Joiner.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.base.Joiner
            public CharSequence toString(@Nullable Object part) {
                return part == null ? nullText : Joiner.this.toString(part);
            }

            @Override // com.google.common.base.Joiner
            public Joiner useForNull(String nullText) {
                throw new UnsupportedOperationException("already specified useForNull");
            }

            @Override // com.google.common.base.Joiner
            public Joiner skipNulls() {
                throw new UnsupportedOperationException("already specified useForNull");
            }
        };
    }

    @CheckReturnValue
    public Joiner skipNulls() {
        return new Joiner(this) {
            /* class com.google.common.base.Joiner.AnonymousClass2 */

            @Override // com.google.common.base.Joiner
            public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
                Preconditions.checkNotNull(appendable, "appendable");
                Preconditions.checkNotNull(parts, "parts");
                while (true) {
                    if (parts.hasNext()) {
                        Object part = parts.next();
                        if (part != null) {
                            appendable.append(Joiner.this.toString(part));
                            break;
                        }
                    } else {
                        break;
                    }
                }
                while (parts.hasNext()) {
                    Object part2 = parts.next();
                    if (part2 != null) {
                        appendable.append(Joiner.this.separator);
                        appendable.append(Joiner.this.toString(part2));
                    }
                }
                return appendable;
            }

            @Override // com.google.common.base.Joiner
            public Joiner useForNull(String nullText) {
                throw new UnsupportedOperationException("already specified skipNulls");
            }

            @Override // com.google.common.base.Joiner
            public MapJoiner withKeyValueSeparator(String kvs) {
                throw new UnsupportedOperationException("can't use .skipNulls() with maps");
            }
        };
    }

    @CheckReturnValue
    public MapJoiner withKeyValueSeparator(String keyValueSeparator) {
        return new MapJoiner(keyValueSeparator);
    }

    public static final class MapJoiner {
        private final Joiner joiner;
        private final String keyValueSeparator;

        private MapJoiner(Joiner joiner2, String keyValueSeparator2) {
            this.joiner = joiner2;
            this.keyValueSeparator = (String) Preconditions.checkNotNull(keyValueSeparator2);
        }

        public <A extends Appendable> A appendTo(A appendable, Map<?, ?> map) throws IOException {
            return (A) appendTo(appendable, map.entrySet());
        }

        public StringBuilder appendTo(StringBuilder builder, Map<?, ?> map) {
            return appendTo(builder, (Iterable<? extends Map.Entry<?, ?>>) map.entrySet());
        }

        public String join(Map<?, ?> map) {
            return join(map.entrySet());
        }

        @Beta
        public <A extends Appendable> A appendTo(A appendable, Iterable<? extends Map.Entry<?, ?>> entries) throws IOException {
            return (A) appendTo(appendable, entries.iterator());
        }

        @Beta
        public <A extends Appendable> A appendTo(A appendable, Iterator<? extends Map.Entry<?, ?>> parts) throws IOException {
            Preconditions.checkNotNull(appendable);
            if (parts.hasNext()) {
                Map.Entry<?, ?> entry = (Map.Entry) parts.next();
                appendable.append(this.joiner.toString(entry.getKey()));
                appendable.append(this.keyValueSeparator);
                appendable.append(this.joiner.toString(entry.getValue()));
                while (parts.hasNext()) {
                    appendable.append(this.joiner.separator);
                    Map.Entry<?, ?> e = (Map.Entry) parts.next();
                    appendable.append(this.joiner.toString(e.getKey()));
                    appendable.append(this.keyValueSeparator);
                    appendable.append(this.joiner.toString(e.getValue()));
                }
            }
            return appendable;
        }

        @Beta
        public StringBuilder appendTo(StringBuilder builder, Iterable<? extends Map.Entry<?, ?>> entries) {
            return appendTo(builder, entries.iterator());
        }

        @Beta
        public StringBuilder appendTo(StringBuilder builder, Iterator<? extends Map.Entry<?, ?>> entries) {
            try {
                appendTo((Appendable) builder, entries);
                return builder;
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        @Beta
        public String join(Iterable<? extends Map.Entry<?, ?>> entries) {
            return join(entries.iterator());
        }

        @Beta
        public String join(Iterator<? extends Map.Entry<?, ?>> entries) {
            return appendTo(new StringBuilder(), entries).toString();
        }

        @CheckReturnValue
        public MapJoiner useForNull(String nullText) {
            return new MapJoiner(this.joiner.useForNull(nullText), this.keyValueSeparator);
        }
    }

    /* access modifiers changed from: package-private */
    public CharSequence toString(Object part) {
        Preconditions.checkNotNull(part);
        return part instanceof CharSequence ? (CharSequence) part : part.toString();
    }

    private static Iterable<Object> iterable(final Object first, final Object second, final Object[] rest) {
        Preconditions.checkNotNull(rest);
        return new AbstractList<Object>() {
            /* class com.google.common.base.Joiner.AnonymousClass3 */

            public int size() {
                return rest.length + 2;
            }

            @Override // java.util.List, java.util.AbstractList
            public Object get(int index) {
                if (index == 0) {
                    return first;
                }
                if (index != 1) {
                    return rest[index - 2];
                }
                return second;
            }
        };
    }
}
