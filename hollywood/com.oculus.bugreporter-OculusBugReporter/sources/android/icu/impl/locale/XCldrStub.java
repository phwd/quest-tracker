package android.icu.impl.locale;

import android.icu.util.ICUException;
import android.icu.util.ICUUncheckedIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XCldrStub {

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static class Multimap<K, V> {
        private final Map<K, Set<V>> map;
        private final Class<Set<V>> setClass;

        private Multimap(Map<K, Set<V>> map2, Class<?> setClass2) {
            this.map = map2;
            this.setClass = setClass2 != null ? (Class<Set<V>>) setClass2 : HashSet.class;
        }

        @SafeVarargs
        public final Multimap<K, V> putAll(K key, V... values) {
            if (values.length != 0) {
                createSetIfMissing(key).addAll(Arrays.asList(values));
            }
            return this;
        }

        public void putAll(K key, Collection<V> values) {
            if (!values.isEmpty()) {
                createSetIfMissing(key).addAll(values);
            }
        }

        public void putAll(Collection<K> keys, V value) {
            for (K key : keys) {
                put(key, value);
            }
        }

        public void putAll(Multimap<K, V> source) {
            for (Map.Entry<K, Set<V>> entry : source.map.entrySet()) {
                putAll((Object) entry.getKey(), (Collection) entry.getValue());
            }
        }

        public void put(K key, V value) {
            createSetIfMissing(key).add(value);
        }

        private Set<V> createSetIfMissing(K key) {
            Set<V> old = this.map.get(key);
            if (old != null) {
                return old;
            }
            Map<K, Set<V>> map2 = this.map;
            Set<V> old2 = getInstance();
            map2.put(key, old2);
            return old2;
        }

        private Set<V> getInstance() {
            try {
                return this.setClass.newInstance();
            } catch (Exception e) {
                throw new ICUException(e);
            }
        }

        public Set<V> get(K key) {
            return this.map.get(key);
        }

        public Set<K> keySet() {
            return this.map.keySet();
        }

        public Map<K, Set<V>> asMap() {
            return this.map;
        }

        public Set<V> values() {
            Collection<Set<V>> values = this.map.values();
            if (values.size() == 0) {
                return Collections.emptySet();
            }
            Set<V> result = getInstance();
            for (Set<V> valueSet : values) {
                result.addAll(valueSet);
            }
            return result;
        }

        public int size() {
            return this.map.size();
        }

        public Iterable<Map.Entry<K, V>> entries() {
            return new MultimapIterator(this.map);
        }

        public boolean equals(Object obj) {
            return this == obj || (obj != null && obj.getClass() == getClass() && this.map.equals(((Multimap) obj).map));
        }

        public int hashCode() {
            return this.map.hashCode();
        }
    }

    public static class Multimaps {
        public static <K, V, R extends Multimap<K, V>> R invertFrom(Multimap<V, K> source, R target) {
            for (Map.Entry<V, Set<K>> entry : source.asMap().entrySet()) {
                target.putAll(entry.getValue(), entry.getKey());
            }
            return target;
        }

        public static <K, V, R extends Multimap<K, V>> R invertFrom(Map<V, K> source, R target) {
            for (Map.Entry<V, K> entry : source.entrySet()) {
                target.put(entry.getValue(), entry.getKey());
            }
            return target;
        }

        public static <K, V> Map<K, V> forMap(Map<K, V> map) {
            return map;
        }
    }

    private static class MultimapIterator<K, V> implements Iterator<Map.Entry<K, V>>, Iterable<Map.Entry<K, V>> {
        private final ReusableEntry<K, V> entry;
        private final Iterator<Map.Entry<K, Set<V>>> it1;
        private Iterator<V> it2;

        private MultimapIterator(Map<K, Set<V>> map) {
            this.it2 = null;
            this.entry = new ReusableEntry<>();
            this.it1 = map.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Iterator<V> it;
            return this.it1.hasNext() || ((it = this.it2) != null && it.hasNext());
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            Iterator<V> it = this.it2;
            if (it == null || !it.hasNext()) {
                Map.Entry<K, Set<V>> e = this.it1.next();
                this.entry.key = e.getKey();
                this.it2 = e.getValue().iterator();
            } else {
                this.entry.value = this.it2.next();
            }
            return this.entry;
        }

        @Override // java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: private */
    public static class ReusableEntry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;

        private ReusableEntry() {
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }
    }

    public static class HashMultimap<K, V> extends Multimap<K, V> {
        private HashMultimap() {
            super(new HashMap(), HashSet.class);
        }

        public static <K, V> HashMultimap<K, V> create() {
            return new HashMultimap<>();
        }
    }

    public static class TreeMultimap<K, V> extends Multimap<K, V> {
        private TreeMultimap() {
            super(new TreeMap(), TreeSet.class);
        }

        public static <K, V> TreeMultimap<K, V> create() {
            return new TreeMultimap<>();
        }
    }

    public static class LinkedHashMultimap<K, V> extends Multimap<K, V> {
        private LinkedHashMultimap() {
            super(new LinkedHashMap(), LinkedHashSet.class);
        }

        public static <K, V> LinkedHashMultimap<K, V> create() {
            return new LinkedHashMultimap<>();
        }
    }

    public static <T> String join(T[] source, String separator) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < source.length; i++) {
            if (i != 0) {
                result.append(separator);
            }
            result.append((Object) source[i]);
        }
        return result.toString();
    }

    public static <T> String join(Iterable<T> source, String separator) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (T item : source) {
            if (!first) {
                result.append(separator);
            } else {
                first = false;
            }
            result.append(item.toString());
        }
        return result.toString();
    }

    public static class CollectionUtilities {
        public static <T, U extends Iterable<T>> String join(U source, String separator) {
            return XCldrStub.join(source, separator);
        }
    }

    public static class Joiner {
        private final String separator;

        private Joiner(String separator2) {
            this.separator = separator2;
        }

        public static final Joiner on(String separator2) {
            return new Joiner(separator2);
        }

        public <T> String join(T[] source) {
            return XCldrStub.join(source, this.separator);
        }

        public <T> String join(Iterable<T> source) {
            return XCldrStub.join(source, this.separator);
        }
    }

    public static class Splitter {
        Pattern pattern;
        boolean trimResults;

        public Splitter(char c) {
            this(Pattern.compile("\\Q" + c + "\\E"));
        }

        public Splitter(Pattern p) {
            this.trimResults = false;
            this.pattern = p;
        }

        public static Splitter on(char c) {
            return new Splitter(c);
        }

        public static Splitter on(Pattern p) {
            return new Splitter(p);
        }

        public List<String> splitToList(String input) {
            String[] items = this.pattern.split(input);
            if (this.trimResults) {
                for (int i = 0; i < items.length; i++) {
                    items[i] = items[i].trim();
                }
            }
            return Arrays.asList(items);
        }

        public Splitter trimResults() {
            this.trimResults = true;
            return this;
        }

        public Iterable<String> split(String input) {
            return splitToList(input);
        }
    }

    public static class ImmutableSet {
        public static <T> Set<T> copyOf(Set<T> values) {
            return Collections.unmodifiableSet(new LinkedHashSet(values));
        }
    }

    public static class ImmutableMap {
        public static <K, V> Map<K, V> copyOf(Map<K, V> values) {
            return Collections.unmodifiableMap(new LinkedHashMap(values));
        }
    }

    public static class ImmutableMultimap {
        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.LinkedHashMap */
        /* JADX WARN: Multi-variable type inference failed */
        public static <K, V> Multimap<K, V> copyOf(Multimap<K, V> values) {
            Set<V> set;
            LinkedHashMap<K, Set<V>> temp = new LinkedHashMap<>();
            for (Map.Entry<K, Set<V>> entry : values.asMap().entrySet()) {
                Set<V> value = entry.getValue();
                K key = entry.getKey();
                if (value.size() == 1) {
                    set = Collections.singleton(value.iterator().next());
                } else {
                    set = Collections.unmodifiableSet(new LinkedHashSet(value));
                }
                temp.put(key, set);
            }
            return new Multimap<>(Collections.unmodifiableMap(temp), null);
        }
    }

    public static class FileUtilities {
        public static final Charset UTF8 = Charset.forName("utf-8");

        public static BufferedReader openFile(Class<?> class1, String file) {
            return openFile(class1, file, UTF8);
        }

        public static BufferedReader openFile(Class<?> class1, String file, Charset charset) {
            try {
                InputStream resourceAsStream = class1.getResourceAsStream(file);
                if (charset == null) {
                    charset = UTF8;
                }
                return new BufferedReader(new InputStreamReader(resourceAsStream, charset), 65536);
            } catch (Exception e) {
                String className = class1 == null ? null : class1.getCanonicalName();
                try {
                    String canonicalName = new File(getRelativeFileName(class1, "../util/")).getCanonicalPath();
                    throw new ICUUncheckedIOException("Couldn't open file " + file + "; in path " + canonicalName + "; relative to class: " + className, e);
                } catch (Exception e2) {
                    throw new ICUUncheckedIOException("Couldn't open file: " + file + "; relative to class: " + className, e);
                }
            }
        }

        public static String getRelativeFileName(Class<?> class1, String filename) {
            String resourceString = (class1 == null ? FileUtilities.class.getResource(filename) : class1.getResource(filename)).toString();
            if (resourceString.startsWith("file:")) {
                return resourceString.substring(5);
            }
            if (resourceString.startsWith("jar:file:")) {
                return resourceString.substring(9);
            }
            throw new ICUUncheckedIOException("File not found: " + resourceString);
        }
    }

    public static class RegexUtilities {
        public static int findMismatch(Matcher m, CharSequence s) {
            int i = 1;
            while (i < s.length() && (m.reset(s.subSequence(0, i)).matches() || m.hitEnd())) {
                i++;
            }
            return i - 1;
        }

        public static String showMismatch(Matcher m, CharSequence s) {
            int failPoint = findMismatch(m, s);
            return ((Object) s.subSequence(0, failPoint)) + "☹" + ((Object) s.subSequence(failPoint, s.length()));
        }
    }
}
