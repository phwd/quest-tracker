package com.google.common.base;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public final class Splitter {
    private final int limit;
    private final boolean omitEmptyStrings;
    private final Strategy strategy;
    private final CharMatcher trimmer;

    /* access modifiers changed from: private */
    public interface Strategy {
        Iterator<String> iterator(Splitter splitter, CharSequence charSequence);
    }

    private Splitter(Strategy strategy2) {
        this(strategy2, false, CharMatcher.none(), Integer.MAX_VALUE);
    }

    private Splitter(Strategy strategy2, boolean omitEmptyStrings2, CharMatcher trimmer2, int limit2) {
        this.strategy = strategy2;
        this.omitEmptyStrings = omitEmptyStrings2;
        this.trimmer = trimmer2;
        this.limit = limit2;
    }

    public static Splitter on(char separator) {
        return on(CharMatcher.is(separator));
    }

    public static Splitter on(final CharMatcher separatorMatcher) {
        Preconditions.checkNotNull(separatorMatcher);
        return new Splitter(new Strategy() {
            /* class com.google.common.base.Splitter.AnonymousClass1 */

            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                return new SplittingIterator(splitter, toSplit) {
                    /* class com.google.common.base.Splitter.AnonymousClass1.AnonymousClass1 */

                    /* access modifiers changed from: package-private */
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int start) {
                        return CharMatcher.this.indexIn(this.toSplit, start);
                    }

                    /* access modifiers changed from: package-private */
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int separatorPosition) {
                        return separatorPosition + 1;
                    }
                };
            }
        });
    }

    public static Splitter on(final String separator) {
        boolean z;
        if (separator.length() != 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "The separator may not be the empty string.");
        if (separator.length() == 1) {
            return on(separator.charAt(0));
        }
        return new Splitter(new Strategy() {
            /* class com.google.common.base.Splitter.AnonymousClass2 */

            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                return new SplittingIterator(splitter, toSplit) {
                    /* class com.google.common.base.Splitter.AnonymousClass2.AnonymousClass1 */

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int start) {
                        int separatorLength = separator.length();
                        int last = this.toSplit.length() - separatorLength;
                        for (int p = start; p <= last; p++) {
                            for (int i = 0; i < separatorLength; i++) {
                                if (this.toSplit.charAt(i + p) != separator.charAt(i)) {
                                }
                            }
                            return p;
                        }
                        return -1;
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int separatorPosition) {
                        return separator.length() + separatorPosition;
                    }
                };
            }
        });
    }

    @GwtIncompatible
    public static Splitter on(Pattern separatorPattern) {
        return on(new JdkPattern(separatorPattern));
    }

    private static Splitter on(final CommonPattern separatorPattern) {
        Preconditions.checkArgument(!separatorPattern.matcher("").matches(), "The pattern may not match the empty string: %s", separatorPattern);
        return new Splitter(new Strategy() {
            /* class com.google.common.base.Splitter.AnonymousClass3 */

            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                final CommonMatcher matcher = CommonPattern.this.matcher(toSplit);
                return new SplittingIterator(splitter, toSplit) {
                    /* class com.google.common.base.Splitter.AnonymousClass3.AnonymousClass1 */

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int start) {
                        if (matcher.find(start)) {
                            return matcher.start();
                        }
                        return -1;
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int separatorPosition) {
                        return matcher.end();
                    }
                };
            }
        });
    }

    @GwtIncompatible
    public static Splitter onPattern(String separatorPattern) {
        return on(Platform.compilePattern(separatorPattern));
    }

    public static Splitter fixedLength(final int length) {
        Preconditions.checkArgument(length > 0, "The length may not be less than 1");
        return new Splitter(new Strategy() {
            /* class com.google.common.base.Splitter.AnonymousClass4 */

            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                return new SplittingIterator(splitter, toSplit) {
                    /* class com.google.common.base.Splitter.AnonymousClass4.AnonymousClass1 */

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int start) {
                        int nextChunkStart = start + length;
                        if (nextChunkStart < this.toSplit.length()) {
                            return nextChunkStart;
                        }
                        return -1;
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int separatorPosition) {
                        return separatorPosition;
                    }
                };
            }
        });
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    public Splitter limit(int limit2) {
        Preconditions.checkArgument(limit2 > 0, "must be greater than zero: %s", limit2);
        return new Splitter(this.strategy, this.omitEmptyStrings, this.trimmer, limit2);
    }

    public Splitter trimResults() {
        return trimResults(CharMatcher.whitespace());
    }

    public Splitter trimResults(CharMatcher trimmer2) {
        Preconditions.checkNotNull(trimmer2);
        return new Splitter(this.strategy, this.omitEmptyStrings, trimmer2, this.limit);
    }

    public Iterable<String> split(final CharSequence sequence) {
        Preconditions.checkNotNull(sequence);
        return new Iterable<String>() {
            /* class com.google.common.base.Splitter.AnonymousClass5 */

            @Override // java.lang.Iterable
            public Iterator<String> iterator() {
                return Splitter.this.splittingIterator(sequence);
            }

            public String toString() {
                return Joiner.on(", ").appendTo(new StringBuilder().append('['), (Iterable<?>) this).append(']').toString();
            }
        };
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Iterator<String> splittingIterator(CharSequence sequence) {
        return this.strategy.iterator(this, sequence);
    }

    @Beta
    public List<String> splitToList(CharSequence sequence) {
        Preconditions.checkNotNull(sequence);
        Iterator<String> iterator = splittingIterator(sequence);
        List<String> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return Collections.unmodifiableList(result);
    }

    @Beta
    public MapSplitter withKeyValueSeparator(String separator) {
        return withKeyValueSeparator(on(separator));
    }

    @Beta
    public MapSplitter withKeyValueSeparator(char separator) {
        return withKeyValueSeparator(on(separator));
    }

    @Beta
    public MapSplitter withKeyValueSeparator(Splitter keyValueSplitter) {
        return new MapSplitter(keyValueSplitter);
    }

    @Beta
    public static final class MapSplitter {
        private static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
        private final Splitter entrySplitter;
        private final Splitter outerSplitter;

        private MapSplitter(Splitter outerSplitter2, Splitter entrySplitter2) {
            this.outerSplitter = outerSplitter2;
            this.entrySplitter = (Splitter) Preconditions.checkNotNull(entrySplitter2);
        }

        public Map<String, String> split(CharSequence sequence) {
            Map<String, String> map = new LinkedHashMap<>();
            for (String entry : this.outerSplitter.split(sequence)) {
                Iterator<String> entryFields = this.entrySplitter.splittingIterator(entry);
                Preconditions.checkArgument(entryFields.hasNext(), INVALID_ENTRY_MESSAGE, entry);
                String key = entryFields.next();
                Preconditions.checkArgument(!map.containsKey(key), "Duplicate key [%s] found.", key);
                Preconditions.checkArgument(entryFields.hasNext(), INVALID_ENTRY_MESSAGE, entry);
                map.put(key, entryFields.next());
                Preconditions.checkArgument(!entryFields.hasNext(), INVALID_ENTRY_MESSAGE, entry);
            }
            return Collections.unmodifiableMap(map);
        }
    }

    private static abstract class SplittingIterator extends AbstractIterator<String> {
        int limit;
        int offset = 0;
        final boolean omitEmptyStrings;
        final CharSequence toSplit;
        final CharMatcher trimmer;

        /* access modifiers changed from: package-private */
        public abstract int separatorEnd(int i);

        /* access modifiers changed from: package-private */
        public abstract int separatorStart(int i);

        protected SplittingIterator(Splitter splitter, CharSequence toSplit2) {
            this.trimmer = splitter.trimmer;
            this.omitEmptyStrings = splitter.omitEmptyStrings;
            this.limit = splitter.limit;
            this.toSplit = toSplit2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.AbstractIterator
        public String computeNext() {
            int end;
            int nextStart = this.offset;
            while (this.offset != -1) {
                int start = nextStart;
                int separatorPosition = separatorStart(this.offset);
                if (separatorPosition == -1) {
                    end = this.toSplit.length();
                    this.offset = -1;
                } else {
                    end = separatorPosition;
                    this.offset = separatorEnd(separatorPosition);
                }
                if (this.offset == nextStart) {
                    this.offset++;
                    if (this.offset > this.toSplit.length()) {
                        this.offset = -1;
                    }
                } else {
                    while (start < end && this.trimmer.matches(this.toSplit.charAt(start))) {
                        start++;
                    }
                    while (end > start && this.trimmer.matches(this.toSplit.charAt(end - 1))) {
                        end--;
                    }
                    if (!this.omitEmptyStrings || start != end) {
                        if (this.limit == 1) {
                            end = this.toSplit.length();
                            this.offset = -1;
                            while (end > start && this.trimmer.matches(this.toSplit.charAt(end - 1))) {
                                end--;
                            }
                        } else {
                            this.limit--;
                        }
                        return this.toSplit.subSequence(start, end).toString();
                    }
                    nextStart = this.offset;
                }
            }
            return (String) endOfData();
        }
    }
}
