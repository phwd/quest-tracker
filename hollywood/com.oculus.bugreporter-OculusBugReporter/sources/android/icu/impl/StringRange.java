package android.icu.impl;

import android.icu.lang.CharSequences;
import android.icu.util.ICUException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class StringRange {
    public static final Comparator<int[]> COMPARE_INT_ARRAYS = new Comparator<int[]>() {
        /* class android.icu.impl.StringRange.AnonymousClass1 */

        public int compare(int[] o1, int[] o2) {
            int minIndex = Math.min(o1.length, o2.length);
            for (int i = 0; i < minIndex; i++) {
                int diff = o1[i] - o2[i];
                if (diff != 0) {
                    return diff;
                }
            }
            return o1.length - o2.length;
        }
    };
    private static final boolean DEBUG = false;

    public interface Adder {
        void add(String str, String str2);
    }

    public static void compact(Set<String> source, Adder adder, boolean shorterPairs, boolean moreCompact) {
        String str;
        int currentCp;
        if (!moreCompact) {
            String start = null;
            String end = null;
            int lastCp = 0;
            int prefixLen = 0;
            Iterator<String> it = source.iterator();
            while (true) {
                String str2 = null;
                if (!it.hasNext()) {
                    break;
                }
                String s = it.next();
                if (start != null) {
                    if (s.regionMatches(0, start, 0, prefixLen) && (currentCp = s.codePointAt(prefixLen)) == lastCp + 1 && s.length() == Character.charCount(currentCp) + prefixLen) {
                        end = s;
                        lastCp = currentCp;
                    } else {
                        if (end != null) {
                            if (!shorterPairs) {
                                str2 = end;
                            } else {
                                str2 = end.substring(prefixLen, end.length());
                            }
                        }
                        adder.add(start, str2);
                    }
                }
                start = s;
                end = null;
                lastCp = s.codePointBefore(s.length());
                prefixLen = s.length() - Character.charCount(lastCp);
            }
            if (end == null) {
                str = null;
            } else if (!shorterPairs) {
                str = end;
            } else {
                str = end.substring(prefixLen, end.length());
            }
            adder.add(start, str);
            return;
        }
        Relation<Integer, Ranges> lengthToArrays = Relation.of(new TreeMap(), TreeSet.class);
        for (String s2 : source) {
            Ranges item = new Ranges(s2);
            lengthToArrays.put(item.size(), item);
        }
        for (Map.Entry<Integer, Set<Ranges>> entry : lengthToArrays.keyValuesSet()) {
            Iterator<Ranges> it2 = compact(entry.getKey().intValue(), entry.getValue()).iterator();
            while (it2.hasNext()) {
                Ranges ranges = it2.next();
                adder.add(ranges.start(), ranges.end(shorterPairs));
            }
        }
    }

    public static void compact(Set<String> source, Adder adder, boolean shorterPairs) {
        compact(source, adder, shorterPairs, false);
    }

    private static LinkedList<Ranges> compact(int size, Set<Ranges> inputRanges) {
        LinkedList<Ranges> ranges = new LinkedList<>(inputRanges);
        for (int i = size - 1; i >= 0; i--) {
            Ranges last = null;
            Iterator<Ranges> it = ranges.iterator();
            while (it.hasNext()) {
                Ranges item = it.next();
                if (last == null) {
                    last = item;
                } else if (last.merge(i, item)) {
                    it.remove();
                } else {
                    last = item;
                }
            }
        }
        return ranges;
    }

    /* access modifiers changed from: package-private */
    public static final class Range implements Comparable<Range> {
        int max;
        int min;

        public Range(int min2, int max2) {
            this.min = min2;
            this.max = max2;
        }

        public boolean equals(Object obj) {
            return this == obj || (obj != null && (obj instanceof Range) && compareTo((Range) obj) == 0);
        }

        public int compareTo(Range that) {
            int diff = this.min - that.min;
            if (diff != 0) {
                return diff;
            }
            return this.max - that.max;
        }

        public int hashCode() {
            return (this.min * 37) + this.max;
        }

        public String toString() {
            StringBuilder result = new StringBuilder().appendCodePoint(this.min);
            if (this.min == this.max) {
                return result.toString();
            }
            result.append('~');
            return result.appendCodePoint(this.max).toString();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Ranges implements Comparable<Ranges> {
        private final Range[] ranges;

        public Ranges(String s) {
            int[] array = CharSequences.codePoints(s);
            this.ranges = new Range[array.length];
            for (int i = 0; i < array.length; i++) {
                this.ranges[i] = new Range(array[i], array[i]);
            }
        }

        public boolean merge(int pivot, Ranges other) {
            for (int i = this.ranges.length - 1; i >= 0; i--) {
                if (i == pivot) {
                    if (this.ranges[i].max != other.ranges[i].min - 1) {
                        return false;
                    }
                } else if (!this.ranges[i].equals(other.ranges[i])) {
                    return false;
                }
            }
            this.ranges[pivot].max = other.ranges[pivot].max;
            return true;
        }

        public String start() {
            StringBuilder result = new StringBuilder();
            int i = 0;
            while (true) {
                Range[] rangeArr = this.ranges;
                if (i >= rangeArr.length) {
                    return result.toString();
                }
                result.appendCodePoint(rangeArr[i].min);
                i++;
            }
        }

        public String end(boolean mostCompact) {
            int firstDiff = firstDifference();
            if (firstDiff == this.ranges.length) {
                return null;
            }
            StringBuilder result = new StringBuilder();
            int i = mostCompact ? firstDiff : 0;
            while (true) {
                Range[] rangeArr = this.ranges;
                if (i >= rangeArr.length) {
                    return result.toString();
                }
                result.appendCodePoint(rangeArr[i].max);
                i++;
            }
        }

        public int firstDifference() {
            int i = 0;
            while (true) {
                Range[] rangeArr = this.ranges;
                if (i >= rangeArr.length) {
                    return rangeArr.length;
                }
                if (rangeArr[i].min != this.ranges[i].max) {
                    return i;
                }
                i++;
            }
        }

        public Integer size() {
            return Integer.valueOf(this.ranges.length);
        }

        public int compareTo(Ranges other) {
            int diff = this.ranges.length - other.ranges.length;
            if (diff != 0) {
                return diff;
            }
            int i = 0;
            while (true) {
                Range[] rangeArr = this.ranges;
                if (i >= rangeArr.length) {
                    return 0;
                }
                int diff2 = rangeArr[i].compareTo(other.ranges[i]);
                if (diff2 != 0) {
                    return diff2;
                }
                i++;
            }
        }

        public String toString() {
            String start = start();
            String end = end(false);
            if (end == null) {
                return start;
            }
            return start + "~" + end;
        }
    }

    public static Collection<String> expand(String start, String end, boolean requireSameLength, Collection<String> output) {
        if (start == null || end == null) {
            throw new ICUException("Range must have 2 valid strings");
        }
        int[] startCps = CharSequences.codePoints(start);
        int[] endCps = CharSequences.codePoints(end);
        int startOffset = startCps.length - endCps.length;
        if (requireSameLength && startOffset != 0) {
            throw new ICUException("Range must have equal-length strings");
        } else if (startOffset < 0) {
            throw new ICUException("Range must have start-length ≥ end-length");
        } else if (endCps.length != 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < startOffset; i++) {
                builder.appendCodePoint(startCps[i]);
            }
            add(0, startOffset, startCps, endCps, builder, output);
            return output;
        } else {
            throw new ICUException("Range must have end-length > 0");
        }
    }

    private static void add(int endIndex, int startOffset, int[] starts, int[] ends, StringBuilder builder, Collection<String> output) {
        int start = starts[endIndex + startOffset];
        int end = ends[endIndex];
        if (start <= end) {
            boolean last = true;
            if (endIndex != ends.length - 1) {
                last = false;
            }
            int startLen = builder.length();
            for (int i = start; i <= end; i++) {
                builder.appendCodePoint(i);
                if (last) {
                    output.add(builder.toString());
                } else {
                    add(endIndex + 1, startOffset, starts, ends, builder, output);
                }
                builder.setLength(startLen);
            }
            return;
        }
        throw new ICUException("Range must have xᵢ ≤ yᵢ for each index i");
    }
}
