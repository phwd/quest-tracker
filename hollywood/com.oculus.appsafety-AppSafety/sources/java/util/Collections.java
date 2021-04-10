package java.util;

import dalvik.system.VMRuntime;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Collections {
    private static final int BINARYSEARCH_THRESHOLD = 5000;
    private static final int COPY_THRESHOLD = 10;
    public static final List EMPTY_LIST = new EmptyList();
    public static final Map EMPTY_MAP = new EmptyMap();
    public static final Set EMPTY_SET = new EmptySet();
    private static final int FILL_THRESHOLD = 25;
    private static final int INDEXOFSUBLIST_THRESHOLD = 35;
    private static final int REPLACEALL_THRESHOLD = 11;
    private static final int REVERSE_THRESHOLD = 18;
    private static final int ROTATE_THRESHOLD = 100;
    private static final int SHUFFLE_THRESHOLD = 5;
    private static Random r;

    private Collections() {
    }

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        sort(list, null);
    }

    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        if (VMRuntime.getRuntime().getTargetSdkVersion() > 25) {
            list.sort(c);
        } else if (list.getClass() == ArrayList.class) {
            Arrays.sort(((ArrayList) list).elementData, 0, list.size(), c);
        } else {
            Object[] a = list.toArray();
            Arrays.sort(a, c);
            ListIterator<T> i = list.listIterator();
            for (Object obj : a) {
                i.next();
                i.set((T) obj);
            }
        }
    }

    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        if ((list instanceof RandomAccess) || list.size() < BINARYSEARCH_THRESHOLD) {
            return indexedBinarySearch(list, key);
        }
        return iteratorBinarySearch(list, key);
    }

    private static <T> int indexedBinarySearch(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = ((Comparable) list.get(mid)).compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp <= 0) {
                return mid;
            } else {
                high = mid - 1;
            }
        }
        return -(low + 1);
    }

    private static <T> int iteratorBinarySearch(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size() - 1;
        ListIterator<? extends Comparable<? super T>> i = list.listIterator();
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = ((Comparable) get(i, mid)).compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp <= 0) {
                return mid;
            } else {
                high = mid - 1;
            }
        }
        return -(low + 1);
    }

    private static <T> T get(ListIterator<? extends T> i, int index) {
        T obj;
        int pos = i.nextIndex();
        if (pos <= index) {
            while (true) {
                obj = (T) i.next();
                int pos2 = pos + 1;
                if (pos >= index) {
                    break;
                }
                pos = pos2;
            }
        } else {
            do {
                obj = (T) i.previous();
                pos--;
            } while (pos > index);
        }
        return obj;
    }

    public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) {
        if (c == null) {
            return binarySearch(list, key);
        }
        if ((list instanceof RandomAccess) || list.size() < BINARYSEARCH_THRESHOLD) {
            return indexedBinarySearch(list, key, c);
        }
        return iteratorBinarySearch(list, key, c);
    }

    private static <T> int indexedBinarySearch(List<? extends T> l, T key, Comparator<? super T> c) {
        int low = 0;
        int high = l.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = c.compare((Object) l.get(mid), key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp <= 0) {
                return mid;
            } else {
                high = mid - 1;
            }
        }
        return -(low + 1);
    }

    private static <T> int iteratorBinarySearch(List<? extends T> l, T key, Comparator<? super T> c) {
        int low = 0;
        int high = l.size() - 1;
        ListIterator<? extends T> i = l.listIterator();
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = c.compare((Object) get(i, mid), key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp <= 0) {
                return mid;
            } else {
                high = mid - 1;
            }
        }
        return -(low + 1);
    }

    public static void reverse(List<?> list) {
        int size = list.size();
        if (size < 18 || (list instanceof RandomAccess)) {
            int i = 0;
            int mid = size >> 1;
            int j = size - 1;
            while (i < mid) {
                swap(list, i, j);
                i++;
                j--;
            }
            return;
        }
        ListIterator fwd = list.listIterator();
        ListIterator rev = list.listIterator(size);
        int mid2 = list.size() >> 1;
        for (int i2 = 0; i2 < mid2; i2++) {
            Object tmp = fwd.next();
            fwd.set(rev.previous());
            rev.set(tmp);
        }
    }

    public static void shuffle(List<?> list) {
        Random rnd = r;
        if (rnd == null) {
            Random random = new Random();
            rnd = random;
            r = random;
        }
        shuffle(list, rnd);
    }

    public static void shuffle(List<?> list, Random rnd) {
        int size = list.size();
        if (size < 5 || (list instanceof RandomAccess)) {
            for (int i = size; i > 1; i--) {
                swap(list, i - 1, rnd.nextInt(i));
            }
            return;
        }
        Object[] arr = list.toArray();
        for (int i2 = size; i2 > 1; i2--) {
            swap(arr, i2 - 1, rnd.nextInt(i2));
        }
        ListIterator it = list.listIterator();
        for (Object obj : arr) {
            it.next();
            it.set(obj);
        }
    }

    public static void swap(List<?> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    private static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <T> void fill(List<? super T> list, T obj) {
        int size = list.size();
        if (size < 25 || (list instanceof RandomAccess)) {
            for (int i = 0; i < size; i++) {
                list.set(i, obj);
            }
            return;
        }
        ListIterator<? super T> itr = list.listIterator();
        for (int i2 = 0; i2 < size; i2++) {
            itr.next();
            itr.set(obj);
        }
    }

    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        int srcSize = src.size();
        if (srcSize > dest.size()) {
            throw new IndexOutOfBoundsException("Source does not fit in dest");
        } else if (srcSize < 10 || ((src instanceof RandomAccess) && (dest instanceof RandomAccess))) {
            for (int i = 0; i < srcSize; i++) {
                dest.set(i, (Object) src.get(i));
            }
        } else {
            ListIterator<? super T> di = dest.listIterator();
            ListIterator<? extends T> si = src.listIterator();
            for (int i2 = 0; i2 < srcSize; i2++) {
                di.next();
                di.set((Object) si.next());
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Comparable<? super T>> T min(Collection<? extends T> coll) {
        Iterator<? extends T> i = coll.iterator();
        T candidate = (T) i.next();
        while (i.hasNext()) {
            Object candidate2 = i.next();
            if (((Comparable) candidate2).compareTo(candidate) < 0) {
                candidate = candidate2;
            }
        }
        return candidate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T min(java.util.Collection<? extends T> r4, java.util.Comparator<? super T> r5) {
        /*
            if (r5 != 0) goto L_0x0007
            java.lang.Object r0 = min(r4)
            return r0
        L_0x0007:
            java.util.Iterator r0 = r4.iterator()
            java.lang.Object r1 = r0.next()
        L_0x000f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0021
            java.lang.Object r2 = r0.next()
            int r3 = r5.compare(r2, r1)
            if (r3 >= 0) goto L_0x0020
            r1 = r2
        L_0x0020:
            goto L_0x000f
        L_0x0021:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.Collections.min(java.util.Collection, java.util.Comparator):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Comparable<? super T>> T max(Collection<? extends T> coll) {
        Iterator<? extends T> i = coll.iterator();
        T candidate = (T) i.next();
        while (i.hasNext()) {
            Object candidate2 = i.next();
            if (((Comparable) candidate2).compareTo(candidate) > 0) {
                candidate = candidate2;
            }
        }
        return candidate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T max(java.util.Collection<? extends T> r4, java.util.Comparator<? super T> r5) {
        /*
            if (r5 != 0) goto L_0x0007
            java.lang.Object r0 = max(r4)
            return r0
        L_0x0007:
            java.util.Iterator r0 = r4.iterator()
            java.lang.Object r1 = r0.next()
        L_0x000f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0021
            java.lang.Object r2 = r0.next()
            int r3 = r5.compare(r2, r1)
            if (r3 <= 0) goto L_0x0020
            r1 = r2
        L_0x0020:
            goto L_0x000f
        L_0x0021:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.Collections.max(java.util.Collection, java.util.Comparator):java.lang.Object");
    }

    public static void rotate(List<?> list, int distance) {
        if ((list instanceof RandomAccess) || list.size() < 100) {
            rotate1(list, distance);
        } else {
            rotate2(list, distance);
        }
    }

    private static <T> void rotate1(List<T> list, int distance) {
        int size = list.size();
        if (size != 0) {
            int distance2 = distance % size;
            if (distance2 < 0) {
                distance2 += size;
            }
            if (distance2 != 0) {
                int cycleStart = 0;
                int nMoved = 0;
                while (nMoved != size) {
                    T displaced = list.get(cycleStart);
                    int i = cycleStart;
                    do {
                        i += distance2;
                        if (i >= size) {
                            i -= size;
                        }
                        displaced = list.set(i, displaced);
                        nMoved++;
                    } while (i != cycleStart);
                    cycleStart++;
                }
            }
        }
    }

    private static void rotate2(List<?> list, int distance) {
        int size = list.size();
        if (size != 0) {
            int mid = (-distance) % size;
            if (mid < 0) {
                mid += size;
            }
            if (mid != 0) {
                reverse(list.subList(0, mid));
                reverse(list.subList(mid, size));
                reverse(list);
            }
        }
    }

    public static <T> boolean replaceAll(List<T> list, T oldVal, T newVal) {
        boolean result = false;
        int size = list.size();
        if (size >= 11 && !(list instanceof RandomAccess)) {
            ListIterator<T> itr = list.listIterator();
            if (oldVal == null) {
                for (int i = 0; i < size; i++) {
                    if (itr.next() == null) {
                        itr.set(newVal);
                        result = true;
                    }
                }
            } else {
                for (int i2 = 0; i2 < size; i2++) {
                    if (oldVal.equals(itr.next())) {
                        itr.set(newVal);
                        result = true;
                    }
                }
            }
        } else if (oldVal == null) {
            for (int i3 = 0; i3 < size; i3++) {
                if (list.get(i3) == null) {
                    list.set(i3, newVal);
                    result = true;
                }
            }
        } else {
            for (int i4 = 0; i4 < size; i4++) {
                if (oldVal.equals(list.get(i4))) {
                    list.set(i4, newVal);
                    result = true;
                }
            }
        }
        return result;
    }

    public static int indexOfSubList(List<?> source, List<?> target) {
        int sourceSize = source.size();
        int targetSize = target.size();
        int maxCandidate = sourceSize - targetSize;
        if (sourceSize < 35 || ((source instanceof RandomAccess) && (target instanceof RandomAccess))) {
            for (int candidate = 0; candidate <= maxCandidate; candidate++) {
                int i = 0;
                int j = candidate;
                while (i < targetSize) {
                    if (eq(target.get(i), source.get(j))) {
                        i++;
                        j++;
                    }
                }
                return candidate;
            }
            return -1;
        }
        ListIterator<?> si = source.listIterator();
        for (int candidate2 = 0; candidate2 <= maxCandidate; candidate2++) {
            ListIterator<?> ti = target.listIterator();
            for (int i2 = 0; i2 < targetSize; i2++) {
                if (!eq(ti.next(), si.next())) {
                    for (int j2 = 0; j2 < i2; j2++) {
                        si.previous();
                    }
                }
            }
            return candidate2;
        }
        return -1;
    }

    public static int lastIndexOfSubList(List<?> source, List<?> target) {
        int sourceSize = source.size();
        int targetSize = target.size();
        int maxCandidate = sourceSize - targetSize;
        if (sourceSize < 35 || (source instanceof RandomAccess)) {
            for (int candidate = maxCandidate; candidate >= 0; candidate--) {
                int i = 0;
                int j = candidate;
                while (i < targetSize) {
                    if (eq(target.get(i), source.get(j))) {
                        i++;
                        j++;
                    }
                }
                return candidate;
            }
        } else if (maxCandidate < 0) {
            return -1;
        } else {
            ListIterator<?> si = source.listIterator(maxCandidate);
            for (int candidate2 = maxCandidate; candidate2 >= 0; candidate2--) {
                ListIterator<?> ti = target.listIterator();
                for (int i2 = 0; i2 < targetSize; i2++) {
                    if (!eq(ti.next(), si.next())) {
                        if (candidate2 != 0) {
                            for (int j2 = 0; j2 <= i2 + 1; j2++) {
                                si.previous();
                            }
                        }
                    }
                }
                return candidate2;
            }
        }
        return -1;
    }

    public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c) {
        return new UnmodifiableCollection(c);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableCollection<E> implements Collection<E>, Serializable {
        private static final long serialVersionUID = 1820017752578914078L;
        final Collection<? extends E> c;

        UnmodifiableCollection(Collection<? extends E> c2) {
            if (c2 != null) {
                this.c = c2;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Collection
        public int size() {
            return this.c.size();
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return this.c.isEmpty();
        }

        @Override // java.util.Collection
        public boolean contains(Object o) {
            return this.c.contains(o);
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return this.c.toArray();
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] a) {
            return (T[]) this.c.toArray(a);
        }

        public String toString() {
            return this.c.toString();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return new Iterator<E>() {
                /* class java.util.Collections.UnmodifiableCollection.AnonymousClass1 */
                private final Iterator<? extends E> i = UnmodifiableCollection.this.c.iterator();

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.i.hasNext();
                }

                @Override // java.util.Iterator
                public E next() {
                    return (E) this.i.next();
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.Iterator
                public void forEachRemaining(Consumer<? super E> action) {
                    this.i.forEachRemaining(action);
                }
            };
        }

        @Override // java.util.Collection
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> coll) {
            return this.c.containsAll(coll);
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> action) {
            this.c.forEach(action);
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super E> predicate) {
            throw new UnsupportedOperationException();
        }

        /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.Spliterator<? extends E>, java.util.Spliterator<E> */
        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return (Spliterator<? extends E>) this.c.spliterator();
        }

        /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.stream.Stream<? extends E>, java.util.stream.Stream<E> */
        @Override // java.util.Collection
        public Stream<E> stream() {
            return (Stream<? extends E>) this.c.stream();
        }

        /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.stream.Stream<? extends E>, java.util.stream.Stream<E> */
        @Override // java.util.Collection
        public Stream<E> parallelStream() {
            return (Stream<? extends E>) this.c.parallelStream();
        }
    }

    public static <T> Set<T> unmodifiableSet(Set<? extends T> s) {
        return new UnmodifiableSet(s);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableSet<E> extends UnmodifiableCollection<E> implements Set<E>, Serializable {
        private static final long serialVersionUID = -9215047833775013803L;

        UnmodifiableSet(Set<? extends E> s) {
            super(s);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object o) {
            return o == this || this.c.equals(o);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return this.c.hashCode();
        }
    }

    public static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> s) {
        return new UnmodifiableSortedSet(s);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableSortedSet<E> extends UnmodifiableSet<E> implements SortedSet<E>, Serializable {
        private static final long serialVersionUID = -4929149591599911165L;
        private final SortedSet<E> ss;

        UnmodifiableSortedSet(SortedSet<E> s) {
            super(s);
            this.ss = s;
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return this.ss.comparator();
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E fromElement, E toElement) {
            return new UnmodifiableSortedSet(this.ss.subSet(fromElement, toElement));
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E toElement) {
            return new UnmodifiableSortedSet(this.ss.headSet(toElement));
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E fromElement) {
            return new UnmodifiableSortedSet(this.ss.tailSet(fromElement));
        }

        @Override // java.util.SortedSet
        public E first() {
            return this.ss.first();
        }

        @Override // java.util.SortedSet
        public E last() {
            return this.ss.last();
        }
    }

    public static <T> NavigableSet<T> unmodifiableNavigableSet(NavigableSet<T> s) {
        return new UnmodifiableNavigableSet(s);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableNavigableSet<E> extends UnmodifiableSortedSet<E> implements NavigableSet<E>, Serializable {
        private static final NavigableSet<?> EMPTY_NAVIGABLE_SET = new EmptyNavigableSet();
        private static final long serialVersionUID = -6027448201786391929L;
        private final NavigableSet<E> ns;

        private static class EmptyNavigableSet<E> extends UnmodifiableNavigableSet<E> implements Serializable {
            private static final long serialVersionUID = -6291252904449939134L;

            public EmptyNavigableSet() {
                super(new TreeSet());
            }

            private Object readResolve() {
                return UnmodifiableNavigableSet.EMPTY_NAVIGABLE_SET;
            }
        }

        UnmodifiableNavigableSet(NavigableSet<E> s) {
            super(s);
            this.ns = s;
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            return this.ns.lower(e);
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            return this.ns.floor(e);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            return this.ns.ceiling(e);
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            return this.ns.higher(e);
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return new UnmodifiableNavigableSet(this.ns.descendingSet());
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
            return new UnmodifiableNavigableSet(this.ns.subSet(fromElement, fromInclusive, toElement, toInclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E toElement, boolean inclusive) {
            return new UnmodifiableNavigableSet(this.ns.headSet(toElement, inclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
            return new UnmodifiableNavigableSet(this.ns.tailSet(fromElement, inclusive));
        }
    }

    public static <T> List<T> unmodifiableList(List<? extends T> list) {
        if (list instanceof RandomAccess) {
            return new UnmodifiableRandomAccessList(list);
        }
        return new UnmodifiableList(list);
    }

    static class UnmodifiableList<E> extends UnmodifiableCollection<E> implements List<E> {
        private static final long serialVersionUID = -283967356065247728L;
        final List<? extends E> list;

        UnmodifiableList(List<? extends E> list2) {
            super(list2);
            this.list = list2;
        }

        @Override // java.util.List, java.util.Collection
        public boolean equals(Object o) {
            return o == this || this.list.equals(o);
        }

        @Override // java.util.List, java.util.Collection
        public int hashCode() {
            return this.list.hashCode();
        }

        @Override // java.util.List
        public E get(int index) {
            return (E) this.list.get(index);
        }

        @Override // java.util.List
        public E set(int index, E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public void add(int index, E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public E remove(int index) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public int indexOf(Object o) {
            return this.list.indexOf(o);
        }

        @Override // java.util.List
        public int lastIndexOf(Object o) {
            return this.list.lastIndexOf(o);
        }

        @Override // java.util.List
        public boolean addAll(int index, Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public void replaceAll(UnaryOperator<E> unaryOperator) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public void sort(Comparator<? super E> comparator) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override // java.util.List
        public ListIterator<E> listIterator(final int index) {
            return new ListIterator<E>() {
                /* class java.util.Collections.UnmodifiableList.AnonymousClass1 */
                private final ListIterator<? extends E> i = UnmodifiableList.this.list.listIterator(index);

                @Override // java.util.Iterator, java.util.ListIterator
                public boolean hasNext() {
                    return this.i.hasNext();
                }

                @Override // java.util.Iterator, java.util.ListIterator
                public E next() {
                    return (E) this.i.next();
                }

                @Override // java.util.ListIterator
                public boolean hasPrevious() {
                    return this.i.hasPrevious();
                }

                @Override // java.util.ListIterator
                public E previous() {
                    return (E) this.i.previous();
                }

                @Override // java.util.ListIterator
                public int nextIndex() {
                    return this.i.nextIndex();
                }

                @Override // java.util.ListIterator
                public int previousIndex() {
                    return this.i.previousIndex();
                }

                @Override // java.util.Iterator, java.util.ListIterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.ListIterator
                public void set(E e) {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.ListIterator
                public void add(E e) {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.Iterator
                public void forEachRemaining(Consumer<? super E> action) {
                    this.i.forEachRemaining(action);
                }
            };
        }

        @Override // java.util.List
        public List<E> subList(int fromIndex, int toIndex) {
            return new UnmodifiableList(this.list.subList(fromIndex, toIndex));
        }

        private Object readResolve() {
            List<? extends E> list2 = this.list;
            if (list2 instanceof RandomAccess) {
                return new UnmodifiableRandomAccessList(list2);
            }
            return this;
        }
    }

    static class UnmodifiableRandomAccessList<E> extends UnmodifiableList<E> implements RandomAccess {
        private static final long serialVersionUID = -2542308836966382001L;

        UnmodifiableRandomAccessList(List<? extends E> list) {
            super(list);
        }

        @Override // java.util.List, java.util.Collections.UnmodifiableList
        public List<E> subList(int fromIndex, int toIndex) {
            return new UnmodifiableRandomAccessList(this.list.subList(fromIndex, toIndex));
        }

        private Object writeReplace() {
            return new UnmodifiableList(this.list);
        }
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m) {
        return new UnmodifiableMap(m);
    }

    private static class UnmodifiableMap<K, V> implements Map<K, V>, Serializable {
        private static final long serialVersionUID = -1034234728574286014L;
        private transient Set<Map.Entry<K, V>> entrySet;
        private transient Set<K> keySet;
        private final Map<? extends K, ? extends V> m;
        private transient Collection<V> values;

        UnmodifiableMap(Map<? extends K, ? extends V> m2) {
            if (m2 != null) {
                this.m = m2;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Map
        public int size() {
            return this.m.size();
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.m.isEmpty();
        }

        @Override // java.util.Map
        public boolean containsKey(Object key) {
            return this.m.containsKey(key);
        }

        @Override // java.util.Map
        public boolean containsValue(Object val) {
            return this.m.containsValue(val);
        }

        @Override // java.util.Map
        public V get(Object key) {
            return (V) this.m.get(key);
        }

        @Override // java.util.Map
        public V put(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V remove(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            if (this.keySet == null) {
                this.keySet = Collections.unmodifiableSet(this.m.keySet());
            }
            return this.keySet;
        }

        @Override // java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            if (this.entrySet == null) {
                this.entrySet = new UnmodifiableEntrySet(this.m.entrySet());
            }
            return this.entrySet;
        }

        @Override // java.util.Map
        public Collection<V> values() {
            if (this.values == null) {
                this.values = Collections.unmodifiableCollection(this.m.values());
            }
            return this.values;
        }

        @Override // java.util.Map
        public boolean equals(Object o) {
            return o == this || this.m.equals(o);
        }

        @Override // java.util.Map
        public int hashCode() {
            return this.m.hashCode();
        }

        public String toString() {
            return this.m.toString();
        }

        @Override // java.util.Map
        public V getOrDefault(Object k, V defaultValue) {
            return (V) this.m.getOrDefault(k, defaultValue);
        }

        @Override // java.util.Map
        public void forEach(BiConsumer<? super K, ? super V> action) {
            this.m.forEach(action);
        }

        @Override // java.util.Map
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V putIfAbsent(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public boolean remove(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public boolean replace(K k, V v, V v2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V replace(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V computeIfAbsent(K k, Function<? super K, ? extends V> function) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V computeIfPresent(K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V compute(K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V merge(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }

        static class UnmodifiableEntrySet<K, V> extends UnmodifiableSet<Map.Entry<K, V>> {
            private static final long serialVersionUID = 7854390611657943733L;

            UnmodifiableEntrySet(Set<? extends Map.Entry<? extends K, ? extends V>> s) {
                super(s);
            }

            static <K, V> Consumer<Map.Entry<K, V>> entryConsumer(Consumer<? super Map.Entry<K, V>> action) {
                return new Consumer() {
                    /* class java.util.$$Lambda$Collections$UnmodifiableMap$UnmodifiableEntrySet$W5VhpDb0JlKqrRuOSf_2RiCnSgo */

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Consumer.this.accept(new Collections.UnmodifiableMap.UnmodifiableEntrySet.UnmodifiableEntry((Map.Entry) obj));
                    }
                };
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.lang.Iterable
            public void forEach(Consumer<? super Map.Entry<K, V>> action) {
                Objects.requireNonNull(action);
                this.c.forEach(entryConsumer(action));
            }

            /* access modifiers changed from: package-private */
            public static final class UnmodifiableEntrySetSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {
                final Spliterator<Map.Entry<K, V>> s;

                UnmodifiableEntrySetSpliterator(Spliterator<Map.Entry<K, V>> s2) {
                    this.s = s2;
                }

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action) {
                    Objects.requireNonNull(action);
                    return this.s.tryAdvance(UnmodifiableEntrySet.entryConsumer(action));
                }

                @Override // java.util.Spliterator
                public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action) {
                    Objects.requireNonNull(action);
                    this.s.forEachRemaining(UnmodifiableEntrySet.entryConsumer(action));
                }

                @Override // java.util.Spliterator
                public Spliterator<Map.Entry<K, V>> trySplit() {
                    Spliterator<Map.Entry<K, V>> split = this.s.trySplit();
                    if (split == null) {
                        return null;
                    }
                    return new UnmodifiableEntrySetSpliterator(split);
                }

                @Override // java.util.Spliterator
                public long estimateSize() {
                    return this.s.estimateSize();
                }

                @Override // java.util.Spliterator
                public long getExactSizeIfKnown() {
                    return this.s.getExactSizeIfKnown();
                }

                @Override // java.util.Spliterator
                public int characteristics() {
                    return this.s.characteristics();
                }

                @Override // java.util.Spliterator
                public boolean hasCharacteristics(int characteristics) {
                    return this.s.hasCharacteristics(characteristics);
                }

                @Override // java.util.Spliterator
                public Comparator<? super Map.Entry<K, V>> getComparator() {
                    return this.s.getComparator();
                }
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Spliterator<Map.Entry<K, V>> spliterator() {
                return new UnmodifiableEntrySetSpliterator(this.c.spliterator());
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection
            public Stream<Map.Entry<K, V>> stream() {
                return StreamSupport.stream(spliterator(), false);
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection
            public Stream<Map.Entry<K, V>> parallelStream() {
                return StreamSupport.stream(spliterator(), true);
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<K, V>> iterator() {
                return new Iterator<Map.Entry<K, V>>() {
                    /* class java.util.Collections.UnmodifiableMap.UnmodifiableEntrySet.AnonymousClass1 */
                    private final Iterator<? extends Map.Entry<? extends K, ? extends V>> i = UnmodifiableEntrySet.this.c.iterator();

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.i.hasNext();
                    }

                    @Override // java.util.Iterator
                    public Map.Entry<K, V> next() {
                        return new UnmodifiableEntry((Map.Entry) this.i.next());
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set
            public Object[] toArray() {
                Object[] a = this.c.toArray();
                for (int i = 0; i < a.length; i++) {
                    a[i] = new UnmodifiableEntry((Map.Entry) a[i]);
                }
                return a;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Collection */
            /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: T[] */
            /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.lang.Object[] */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] a) {
                T[] tArr = (T[]) this.c.toArray(a.length == 0 ? a : Arrays.copyOf(a, 0));
                for (int i = 0; i < tArr.length; i++) {
                    tArr[i] = new UnmodifiableEntry((Map.Entry) tArr[i]);
                }
                if (tArr.length > a.length) {
                    return tArr;
                }
                System.arraycopy(tArr, 0, a, 0, tArr.length);
                if (a.length > tArr.length) {
                    a[tArr.length] = null;
                }
                return a;
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set
            public boolean contains(Object o) {
                if (!(o instanceof Map.Entry)) {
                    return false;
                }
                return this.c.contains(new UnmodifiableEntry((Map.Entry) o));
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set
            public boolean containsAll(Collection<?> coll) {
                Iterator<?> it = coll.iterator();
                while (it.hasNext()) {
                    if (!contains(it.next())) {
                        return false;
                    }
                }
                return true;
            }

            @Override // java.util.Collection, java.util.Collections.UnmodifiableSet, java.util.Set
            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Set)) {
                    return false;
                }
                Set<?> s = (Set) o;
                if (s.size() != this.c.size()) {
                    return false;
                }
                return containsAll(s);
            }

            /* access modifiers changed from: private */
            public static class UnmodifiableEntry<K, V> implements Map.Entry<K, V> {
                private Map.Entry<? extends K, ? extends V> e;

                UnmodifiableEntry(Map.Entry<? extends K, ? extends V> e2) {
                    this.e = (Map.Entry) Objects.requireNonNull(e2);
                }

                @Override // java.util.Map.Entry
                public K getKey() {
                    return (K) this.e.getKey();
                }

                @Override // java.util.Map.Entry
                public V getValue() {
                    return (V) this.e.getValue();
                }

                @Override // java.util.Map.Entry
                public V setValue(V v) {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.Map.Entry
                public int hashCode() {
                    return this.e.hashCode();
                }

                @Override // java.util.Map.Entry
                public boolean equals(Object o) {
                    if (this == o) {
                        return true;
                    }
                    if (!(o instanceof Map.Entry)) {
                        return false;
                    }
                    Map.Entry<?, ?> t = (Map.Entry) o;
                    if (!Collections.eq(this.e.getKey(), t.getKey()) || !Collections.eq(this.e.getValue(), t.getValue())) {
                        return false;
                    }
                    return true;
                }

                public String toString() {
                    return this.e.toString();
                }
            }
        }
    }

    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> m) {
        return new UnmodifiableSortedMap(m);
    }

    static class UnmodifiableSortedMap<K, V> extends UnmodifiableMap<K, V> implements SortedMap<K, V>, Serializable {
        private static final long serialVersionUID = -8806743815996713206L;
        private final SortedMap<K, ? extends V> sm;

        UnmodifiableSortedMap(SortedMap<K, ? extends V> m) {
            super(m);
            this.sm = m;
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.sm.comparator();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            return new UnmodifiableSortedMap(this.sm.subMap(fromKey, toKey));
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K toKey) {
            return new UnmodifiableSortedMap(this.sm.headMap(toKey));
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K fromKey) {
            return new UnmodifiableSortedMap(this.sm.tailMap(fromKey));
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return this.sm.firstKey();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return this.sm.lastKey();
        }
    }

    public static <K, V> NavigableMap<K, V> unmodifiableNavigableMap(NavigableMap<K, ? extends V> m) {
        return new UnmodifiableNavigableMap(m);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableNavigableMap<K, V> extends UnmodifiableSortedMap<K, V> implements NavigableMap<K, V>, Serializable {
        private static final EmptyNavigableMap<?, ?> EMPTY_NAVIGABLE_MAP = new EmptyNavigableMap<>();
        private static final long serialVersionUID = -4858195264774772197L;
        private final NavigableMap<K, ? extends V> nm;

        /* access modifiers changed from: private */
        public static class EmptyNavigableMap<K, V> extends UnmodifiableNavigableMap<K, V> implements Serializable {
            private static final long serialVersionUID = -2239321462712562324L;

            EmptyNavigableMap() {
                super(new TreeMap());
            }

            @Override // java.util.Collections.UnmodifiableNavigableMap, java.util.NavigableMap
            public NavigableSet<K> navigableKeySet() {
                return Collections.emptyNavigableSet();
            }

            private Object readResolve() {
                return UnmodifiableNavigableMap.EMPTY_NAVIGABLE_MAP;
            }
        }

        UnmodifiableNavigableMap(NavigableMap<K, ? extends V> m) {
            super(m);
            this.nm = m;
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K key) {
            return this.nm.lowerKey(key);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K key) {
            return this.nm.floorKey(key);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K key) {
            return this.nm.ceilingKey(key);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K key) {
            return this.nm.higherKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K key) {
            Map.Entry<K, ? extends V> lowerEntry = this.nm.lowerEntry(key);
            if (lowerEntry != null) {
                return new UnmodifiableMap.UnmodifiableEntrySet.UnmodifiableEntry(lowerEntry);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K key) {
            Map.Entry<K, ? extends V> floorEntry = this.nm.floorEntry(key);
            if (floorEntry != null) {
                return new UnmodifiableMap.UnmodifiableEntrySet.UnmodifiableEntry(floorEntry);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K key) {
            Map.Entry<K, ? extends V> ceilingEntry = this.nm.ceilingEntry(key);
            if (ceilingEntry != null) {
                return new UnmodifiableMap.UnmodifiableEntrySet.UnmodifiableEntry(ceilingEntry);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K key) {
            Map.Entry<K, ? extends V> higherEntry = this.nm.higherEntry(key);
            if (higherEntry != null) {
                return new UnmodifiableMap.UnmodifiableEntrySet.UnmodifiableEntry(higherEntry);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            Map.Entry<K, ? extends V> firstEntry = this.nm.firstEntry();
            if (firstEntry != null) {
                return new UnmodifiableMap.UnmodifiableEntrySet.UnmodifiableEntry(firstEntry);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            Map.Entry<K, ? extends V> lastEntry = this.nm.lastEntry();
            if (lastEntry != null) {
                return new UnmodifiableMap.UnmodifiableEntrySet.UnmodifiableEntry(lastEntry);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return Collections.unmodifiableNavigableMap(this.nm.descendingMap());
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return Collections.unmodifiableNavigableSet(this.nm.navigableKeySet());
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return Collections.unmodifiableNavigableSet(this.nm.descendingKeySet());
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            return Collections.unmodifiableNavigableMap(this.nm.subMap(fromKey, fromInclusive, toKey, toInclusive));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            return Collections.unmodifiableNavigableMap(this.nm.headMap(toKey, inclusive));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            return Collections.unmodifiableNavigableMap(this.nm.tailMap(fromKey, inclusive));
        }
    }

    public static <T> Collection<T> synchronizedCollection(Collection<T> c) {
        return new SynchronizedCollection(c);
    }

    static <T> Collection<T> synchronizedCollection(Collection<T> c, Object mutex) {
        return new SynchronizedCollection(c, mutex);
    }

    static class SynchronizedCollection<E> implements Collection<E>, Serializable {
        private static final long serialVersionUID = 3053995032091335093L;
        final Collection<E> c;
        final Object mutex;

        SynchronizedCollection(Collection<E> c2) {
            this.c = (Collection) Objects.requireNonNull(c2);
            this.mutex = this;
        }

        SynchronizedCollection(Collection<E> c2, Object mutex2) {
            this.c = (Collection) Objects.requireNonNull(c2);
            this.mutex = Objects.requireNonNull(mutex2);
        }

        @Override // java.util.Collection
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = this.c.size();
            }
            return size;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = this.c.isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Collection
        public boolean contains(Object o) {
            boolean contains;
            synchronized (this.mutex) {
                contains = this.c.contains(o);
            }
            return contains;
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            Object[] array;
            synchronized (this.mutex) {
                array = this.c.toArray();
            }
            return array;
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] a) {
            T[] tArr;
            synchronized (this.mutex) {
                tArr = (T[]) this.c.toArray(a);
            }
            return tArr;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return this.c.iterator();
        }

        @Override // java.util.Collection
        public boolean add(E e) {
            boolean add;
            synchronized (this.mutex) {
                add = this.c.add(e);
            }
            return add;
        }

        @Override // java.util.Collection
        public boolean remove(Object o) {
            boolean remove;
            synchronized (this.mutex) {
                remove = this.c.remove(o);
            }
            return remove;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> coll) {
            boolean containsAll;
            synchronized (this.mutex) {
                containsAll = this.c.containsAll(coll);
            }
            return containsAll;
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends E> coll) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = this.c.addAll(coll);
            }
            return addAll;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> coll) {
            boolean removeAll;
            synchronized (this.mutex) {
                removeAll = this.c.removeAll(coll);
            }
            return removeAll;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> coll) {
            boolean retainAll;
            synchronized (this.mutex) {
                retainAll = this.c.retainAll(coll);
            }
            return retainAll;
        }

        @Override // java.util.Collection
        public void clear() {
            synchronized (this.mutex) {
                this.c.clear();
            }
        }

        public String toString() {
            String obj;
            synchronized (this.mutex) {
                obj = this.c.toString();
            }
            return obj;
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> consumer) {
            synchronized (this.mutex) {
                this.c.forEach(consumer);
            }
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super E> filter) {
            boolean removeIf;
            synchronized (this.mutex) {
                removeIf = this.c.removeIf(filter);
            }
            return removeIf;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return this.c.spliterator();
        }

        @Override // java.util.Collection
        public Stream<E> stream() {
            return this.c.stream();
        }

        @Override // java.util.Collection
        public Stream<E> parallelStream() {
            return this.c.parallelStream();
        }

        private void writeObject(ObjectOutputStream s) throws IOException {
            synchronized (this.mutex) {
                s.defaultWriteObject();
            }
        }
    }

    public static <T> Set<T> synchronizedSet(Set<T> s) {
        return new SynchronizedSet(s);
    }

    static <T> Set<T> synchronizedSet(Set<T> s, Object mutex) {
        return new SynchronizedSet(s, mutex);
    }

    static class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {
        private static final long serialVersionUID = 487447009682186044L;

        SynchronizedSet(Set<E> s) {
            super(s);
        }

        SynchronizedSet(Set<E> s, Object mutex) {
            super(s, mutex);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object o) {
            boolean equals;
            if (this == o) {
                return true;
            }
            synchronized (this.mutex) {
                equals = this.c.equals(o);
            }
            return equals;
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = this.c.hashCode();
            }
            return hashCode;
        }
    }

    public static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s) {
        return new SynchronizedSortedSet(s);
    }

    static class SynchronizedSortedSet<E> extends SynchronizedSet<E> implements SortedSet<E> {
        private static final long serialVersionUID = 8695801310862127406L;
        private final SortedSet<E> ss;

        SynchronizedSortedSet(SortedSet<E> s) {
            super(s);
            this.ss = s;
        }

        SynchronizedSortedSet(SortedSet<E> s, Object mutex) {
            super(s, mutex);
            this.ss = s;
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator;
            synchronized (this.mutex) {
                comparator = this.ss.comparator();
            }
            return comparator;
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E fromElement, E toElement) {
            SynchronizedSortedSet synchronizedSortedSet;
            synchronized (this.mutex) {
                synchronizedSortedSet = new SynchronizedSortedSet(this.ss.subSet(fromElement, toElement), this.mutex);
            }
            return synchronizedSortedSet;
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E toElement) {
            SynchronizedSortedSet synchronizedSortedSet;
            synchronized (this.mutex) {
                synchronizedSortedSet = new SynchronizedSortedSet(this.ss.headSet(toElement), this.mutex);
            }
            return synchronizedSortedSet;
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E fromElement) {
            SynchronizedSortedSet synchronizedSortedSet;
            synchronized (this.mutex) {
                synchronizedSortedSet = new SynchronizedSortedSet(this.ss.tailSet(fromElement), this.mutex);
            }
            return synchronizedSortedSet;
        }

        @Override // java.util.SortedSet
        public E first() {
            E first;
            synchronized (this.mutex) {
                first = this.ss.first();
            }
            return first;
        }

        @Override // java.util.SortedSet
        public E last() {
            E last;
            synchronized (this.mutex) {
                last = this.ss.last();
            }
            return last;
        }
    }

    public static <T> NavigableSet<T> synchronizedNavigableSet(NavigableSet<T> s) {
        return new SynchronizedNavigableSet(s);
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedNavigableSet<E> extends SynchronizedSortedSet<E> implements NavigableSet<E> {
        private static final long serialVersionUID = -5505529816273629798L;
        private final NavigableSet<E> ns;

        SynchronizedNavigableSet(NavigableSet<E> s) {
            super(s);
            this.ns = s;
        }

        SynchronizedNavigableSet(NavigableSet<E> s, Object mutex) {
            super(s, mutex);
            this.ns = s;
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            E lower;
            synchronized (this.mutex) {
                lower = this.ns.lower(e);
            }
            return lower;
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            E floor;
            synchronized (this.mutex) {
                floor = this.ns.floor(e);
            }
            return floor;
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            E ceiling;
            synchronized (this.mutex) {
                ceiling = this.ns.ceiling(e);
            }
            return ceiling;
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            E higher;
            synchronized (this.mutex) {
                higher = this.ns.higher(e);
            }
            return higher;
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            E pollFirst;
            synchronized (this.mutex) {
                pollFirst = this.ns.pollFirst();
            }
            return pollFirst;
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            E pollLast;
            synchronized (this.mutex) {
                pollLast = this.ns.pollLast();
            }
            return pollLast;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet(this.ns.descendingSet(), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            Iterator<E> it;
            synchronized (this.mutex) {
                it = descendingSet().iterator();
            }
            return it;
        }

        @Override // java.util.SortedSet, java.util.NavigableSet, java.util.Collections.SynchronizedSortedSet
        public NavigableSet<E> subSet(E fromElement, E toElement) {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet(this.ns.subSet(fromElement, true, toElement, false), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // java.util.SortedSet, java.util.NavigableSet, java.util.Collections.SynchronizedSortedSet
        public NavigableSet<E> headSet(E toElement) {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet(this.ns.headSet(toElement, false), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // java.util.SortedSet, java.util.NavigableSet, java.util.Collections.SynchronizedSortedSet
        public NavigableSet<E> tailSet(E fromElement) {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet(this.ns.tailSet(fromElement, true), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet(this.ns.subSet(fromElement, fromInclusive, toElement, toInclusive), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E toElement, boolean inclusive) {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet(this.ns.headSet(toElement, inclusive), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet(this.ns.tailSet(fromElement, inclusive), this.mutex);
            }
            return synchronizedNavigableSet;
        }
    }

    public static <T> List<T> synchronizedList(List<T> list) {
        if (list instanceof RandomAccess) {
            return new SynchronizedRandomAccessList(list);
        }
        return new SynchronizedList(list);
    }

    static <T> List<T> synchronizedList(List<T> list, Object mutex) {
        if (list instanceof RandomAccess) {
            return new SynchronizedRandomAccessList(list, mutex);
        }
        return new SynchronizedList(list, mutex);
    }

    static class SynchronizedList<E> extends SynchronizedCollection<E> implements List<E> {
        private static final long serialVersionUID = -7754090372962971524L;
        final List<E> list;

        SynchronizedList(List<E> list2) {
            super(list2);
            this.list = list2;
        }

        SynchronizedList(List<E> list2, Object mutex) {
            super(list2, mutex);
            this.list = list2;
        }

        @Override // java.util.List, java.util.Collection
        public boolean equals(Object o) {
            boolean equals;
            if (this == o) {
                return true;
            }
            synchronized (this.mutex) {
                equals = this.list.equals(o);
            }
            return equals;
        }

        @Override // java.util.List, java.util.Collection
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = this.list.hashCode();
            }
            return hashCode;
        }

        @Override // java.util.List
        public E get(int index) {
            E e;
            synchronized (this.mutex) {
                e = this.list.get(index);
            }
            return e;
        }

        @Override // java.util.List
        public E set(int index, E element) {
            E e;
            synchronized (this.mutex) {
                e = this.list.set(index, element);
            }
            return e;
        }

        @Override // java.util.List
        public void add(int index, E element) {
            synchronized (this.mutex) {
                this.list.add(index, element);
            }
        }

        @Override // java.util.List
        public E remove(int index) {
            E remove;
            synchronized (this.mutex) {
                remove = this.list.remove(index);
            }
            return remove;
        }

        @Override // java.util.List
        public int indexOf(Object o) {
            int indexOf;
            synchronized (this.mutex) {
                indexOf = this.list.indexOf(o);
            }
            return indexOf;
        }

        @Override // java.util.List
        public int lastIndexOf(Object o) {
            int lastIndexOf;
            synchronized (this.mutex) {
                lastIndexOf = this.list.lastIndexOf(o);
            }
            return lastIndexOf;
        }

        @Override // java.util.List
        public boolean addAll(int index, Collection<? extends E> c) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = this.list.addAll(index, c);
            }
            return addAll;
        }

        @Override // java.util.List
        public ListIterator<E> listIterator() {
            return this.list.listIterator();
        }

        @Override // java.util.List
        public ListIterator<E> listIterator(int index) {
            return this.list.listIterator(index);
        }

        @Override // java.util.List
        public List<E> subList(int fromIndex, int toIndex) {
            SynchronizedList synchronizedList;
            synchronized (this.mutex) {
                synchronizedList = new SynchronizedList(this.list.subList(fromIndex, toIndex), this.mutex);
            }
            return synchronizedList;
        }

        @Override // java.util.List
        public void replaceAll(UnaryOperator<E> operator) {
            synchronized (this.mutex) {
                this.list.replaceAll(operator);
            }
        }

        @Override // java.util.List
        public void sort(Comparator<? super E> c) {
            synchronized (this.mutex) {
                this.list.sort(c);
            }
        }

        private Object readResolve() {
            List<E> list2 = this.list;
            if (list2 instanceof RandomAccess) {
                return new SynchronizedRandomAccessList(list2);
            }
            return this;
        }
    }

    static class SynchronizedRandomAccessList<E> extends SynchronizedList<E> implements RandomAccess {
        private static final long serialVersionUID = 1530674583602358482L;

        SynchronizedRandomAccessList(List<E> list) {
            super(list);
        }

        SynchronizedRandomAccessList(List<E> list, Object mutex) {
            super(list, mutex);
        }

        @Override // java.util.List, java.util.Collections.SynchronizedList
        public List<E> subList(int fromIndex, int toIndex) {
            SynchronizedRandomAccessList synchronizedRandomAccessList;
            synchronized (this.mutex) {
                synchronizedRandomAccessList = new SynchronizedRandomAccessList(this.list.subList(fromIndex, toIndex), this.mutex);
            }
            return synchronizedRandomAccessList;
        }

        private Object writeReplace() {
            return new SynchronizedList(this.list);
        }
    }

    public static <K, V> Map<K, V> synchronizedMap(Map<K, V> m) {
        return new SynchronizedMap(m);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedMap<K, V> implements Map<K, V>, Serializable {
        private static final long serialVersionUID = 1978198479659022715L;
        private transient Set<Map.Entry<K, V>> entrySet;
        private transient Set<K> keySet;
        private final Map<K, V> m;
        final Object mutex;
        private transient Collection<V> values;

        SynchronizedMap(Map<K, V> m2) {
            this.m = (Map) Objects.requireNonNull(m2);
            this.mutex = this;
        }

        SynchronizedMap(Map<K, V> m2, Object mutex2) {
            this.m = m2;
            this.mutex = mutex2;
        }

        @Override // java.util.Map
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = this.m.size();
            }
            return size;
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = this.m.isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Map
        public boolean containsKey(Object key) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = this.m.containsKey(key);
            }
            return containsKey;
        }

        @Override // java.util.Map
        public boolean containsValue(Object value) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = this.m.containsValue(value);
            }
            return containsValue;
        }

        @Override // java.util.Map
        public V get(Object key) {
            V v;
            synchronized (this.mutex) {
                v = this.m.get(key);
            }
            return v;
        }

        @Override // java.util.Map
        public V put(K key, V value) {
            V put;
            synchronized (this.mutex) {
                put = this.m.put(key, value);
            }
            return put;
        }

        @Override // java.util.Map
        public V remove(Object key) {
            V remove;
            synchronized (this.mutex) {
                remove = this.m.remove(key);
            }
            return remove;
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            synchronized (this.mutex) {
                this.m.putAll(map);
            }
        }

        @Override // java.util.Map
        public void clear() {
            synchronized (this.mutex) {
                this.m.clear();
            }
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                if (this.keySet == null) {
                    this.keySet = new SynchronizedSet(this.m.keySet(), this.mutex);
                }
                set = this.keySet;
            }
            return set;
        }

        @Override // java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = new SynchronizedSet(this.m.entrySet(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }

        @Override // java.util.Map
        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                if (this.values == null) {
                    this.values = new SynchronizedCollection(this.m.values(), this.mutex);
                }
                collection = this.values;
            }
            return collection;
        }

        @Override // java.util.Map
        public boolean equals(Object o) {
            boolean equals;
            if (this == o) {
                return true;
            }
            synchronized (this.mutex) {
                equals = this.m.equals(o);
            }
            return equals;
        }

        @Override // java.util.Map
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = this.m.hashCode();
            }
            return hashCode;
        }

        public String toString() {
            String obj;
            synchronized (this.mutex) {
                obj = this.m.toString();
            }
            return obj;
        }

        @Override // java.util.Map
        public V getOrDefault(Object k, V defaultValue) {
            V orDefault;
            synchronized (this.mutex) {
                orDefault = this.m.getOrDefault(k, defaultValue);
            }
            return orDefault;
        }

        @Override // java.util.Map
        public void forEach(BiConsumer<? super K, ? super V> action) {
            synchronized (this.mutex) {
                this.m.forEach(action);
            }
        }

        @Override // java.util.Map
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
            synchronized (this.mutex) {
                this.m.replaceAll(function);
            }
        }

        @Override // java.util.Map
        public V putIfAbsent(K key, V value) {
            V putIfAbsent;
            synchronized (this.mutex) {
                putIfAbsent = this.m.putIfAbsent(key, value);
            }
            return putIfAbsent;
        }

        @Override // java.util.Map
        public boolean remove(Object key, Object value) {
            boolean remove;
            synchronized (this.mutex) {
                remove = this.m.remove(key, value);
            }
            return remove;
        }

        @Override // java.util.Map
        public boolean replace(K key, V oldValue, V newValue) {
            boolean replace;
            synchronized (this.mutex) {
                replace = this.m.replace(key, oldValue, newValue);
            }
            return replace;
        }

        @Override // java.util.Map
        public V replace(K key, V value) {
            V replace;
            synchronized (this.mutex) {
                replace = this.m.replace(key, value);
            }
            return replace;
        }

        @Override // java.util.Map
        public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
            V computeIfAbsent;
            synchronized (this.mutex) {
                computeIfAbsent = this.m.computeIfAbsent(key, mappingFunction);
            }
            return computeIfAbsent;
        }

        @Override // java.util.Map
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
            V computeIfPresent;
            synchronized (this.mutex) {
                computeIfPresent = this.m.computeIfPresent(key, remappingFunction);
            }
            return computeIfPresent;
        }

        @Override // java.util.Map
        public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
            V compute;
            synchronized (this.mutex) {
                compute = this.m.compute(key, remappingFunction);
            }
            return compute;
        }

        @Override // java.util.Map
        public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
            V merge;
            synchronized (this.mutex) {
                merge = this.m.merge(key, value, remappingFunction);
            }
            return merge;
        }

        private void writeObject(ObjectOutputStream s) throws IOException {
            synchronized (this.mutex) {
                s.defaultWriteObject();
            }
        }
    }

    public static <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> m) {
        return new SynchronizedSortedMap(m);
    }

    static class SynchronizedSortedMap<K, V> extends SynchronizedMap<K, V> implements SortedMap<K, V> {
        private static final long serialVersionUID = -8798146769416483793L;
        private final SortedMap<K, V> sm;

        SynchronizedSortedMap(SortedMap<K, V> m) {
            super(m);
            this.sm = m;
        }

        SynchronizedSortedMap(SortedMap<K, V> m, Object mutex) {
            super(m, mutex);
            this.sm = m;
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator;
            synchronized (this.mutex) {
                comparator = this.sm.comparator();
            }
            return comparator;
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            SynchronizedSortedMap synchronizedSortedMap;
            synchronized (this.mutex) {
                synchronizedSortedMap = new SynchronizedSortedMap(this.sm.subMap(fromKey, toKey), this.mutex);
            }
            return synchronizedSortedMap;
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K toKey) {
            SynchronizedSortedMap synchronizedSortedMap;
            synchronized (this.mutex) {
                synchronizedSortedMap = new SynchronizedSortedMap(this.sm.headMap(toKey), this.mutex);
            }
            return synchronizedSortedMap;
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K fromKey) {
            SynchronizedSortedMap synchronizedSortedMap;
            synchronized (this.mutex) {
                synchronizedSortedMap = new SynchronizedSortedMap(this.sm.tailMap(fromKey), this.mutex);
            }
            return synchronizedSortedMap;
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            K firstKey;
            synchronized (this.mutex) {
                firstKey = this.sm.firstKey();
            }
            return firstKey;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            K lastKey;
            synchronized (this.mutex) {
                lastKey = this.sm.lastKey();
            }
            return lastKey;
        }
    }

    public static <K, V> NavigableMap<K, V> synchronizedNavigableMap(NavigableMap<K, V> m) {
        return new SynchronizedNavigableMap(m);
    }

    static class SynchronizedNavigableMap<K, V> extends SynchronizedSortedMap<K, V> implements NavigableMap<K, V> {
        private static final long serialVersionUID = 699392247599746807L;
        private final NavigableMap<K, V> nm;

        SynchronizedNavigableMap(NavigableMap<K, V> m) {
            super(m);
            this.nm = m;
        }

        SynchronizedNavigableMap(NavigableMap<K, V> m, Object mutex) {
            super(m, mutex);
            this.nm = m;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K key) {
            Map.Entry<K, V> lowerEntry;
            synchronized (this.mutex) {
                lowerEntry = this.nm.lowerEntry(key);
            }
            return lowerEntry;
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K key) {
            K lowerKey;
            synchronized (this.mutex) {
                lowerKey = this.nm.lowerKey(key);
            }
            return lowerKey;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K key) {
            Map.Entry<K, V> floorEntry;
            synchronized (this.mutex) {
                floorEntry = this.nm.floorEntry(key);
            }
            return floorEntry;
        }

        @Override // java.util.NavigableMap
        public K floorKey(K key) {
            K floorKey;
            synchronized (this.mutex) {
                floorKey = this.nm.floorKey(key);
            }
            return floorKey;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K key) {
            Map.Entry<K, V> ceilingEntry;
            synchronized (this.mutex) {
                ceilingEntry = this.nm.ceilingEntry(key);
            }
            return ceilingEntry;
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K key) {
            K ceilingKey;
            synchronized (this.mutex) {
                ceilingKey = this.nm.ceilingKey(key);
            }
            return ceilingKey;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K key) {
            Map.Entry<K, V> higherEntry;
            synchronized (this.mutex) {
                higherEntry = this.nm.higherEntry(key);
            }
            return higherEntry;
        }

        @Override // java.util.NavigableMap
        public K higherKey(K key) {
            K higherKey;
            synchronized (this.mutex) {
                higherKey = this.nm.higherKey(key);
            }
            return higherKey;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            Map.Entry<K, V> firstEntry;
            synchronized (this.mutex) {
                firstEntry = this.nm.firstEntry();
            }
            return firstEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            Map.Entry<K, V> lastEntry;
            synchronized (this.mutex) {
                lastEntry = this.nm.lastEntry();
            }
            return lastEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            Map.Entry<K, V> pollFirstEntry;
            synchronized (this.mutex) {
                pollFirstEntry = this.nm.pollFirstEntry();
            }
            return pollFirstEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            Map.Entry<K, V> pollLastEntry;
            synchronized (this.mutex) {
                pollLastEntry = this.nm.pollLastEntry();
            }
            return pollLastEntry;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            SynchronizedNavigableMap synchronizedNavigableMap;
            synchronized (this.mutex) {
                synchronizedNavigableMap = new SynchronizedNavigableMap(this.nm.descendingMap(), this.mutex);
            }
            return synchronizedNavigableMap;
        }

        @Override // java.util.Collections.SynchronizedMap, java.util.Map, java.util.SortedMap
        public NavigableSet<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet(this.nm.navigableKeySet(), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            SynchronizedNavigableSet synchronizedNavigableSet;
            synchronized (this.mutex) {
                synchronizedNavigableSet = new SynchronizedNavigableSet(this.nm.descendingKeySet(), this.mutex);
            }
            return synchronizedNavigableSet;
        }

        @Override // java.util.Collections.SynchronizedSortedMap, java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            SynchronizedNavigableMap synchronizedNavigableMap;
            synchronized (this.mutex) {
                synchronizedNavigableMap = new SynchronizedNavigableMap(this.nm.subMap(fromKey, true, toKey, false), this.mutex);
            }
            return synchronizedNavigableMap;
        }

        @Override // java.util.Collections.SynchronizedSortedMap, java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> headMap(K toKey) {
            SynchronizedNavigableMap synchronizedNavigableMap;
            synchronized (this.mutex) {
                synchronizedNavigableMap = new SynchronizedNavigableMap(this.nm.headMap(toKey, false), this.mutex);
            }
            return synchronizedNavigableMap;
        }

        @Override // java.util.Collections.SynchronizedSortedMap, java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> tailMap(K fromKey) {
            SynchronizedNavigableMap synchronizedNavigableMap;
            synchronized (this.mutex) {
                synchronizedNavigableMap = new SynchronizedNavigableMap(this.nm.tailMap(fromKey, true), this.mutex);
            }
            return synchronizedNavigableMap;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            SynchronizedNavigableMap synchronizedNavigableMap;
            synchronized (this.mutex) {
                synchronizedNavigableMap = new SynchronizedNavigableMap(this.nm.subMap(fromKey, fromInclusive, toKey, toInclusive), this.mutex);
            }
            return synchronizedNavigableMap;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            SynchronizedNavigableMap synchronizedNavigableMap;
            synchronized (this.mutex) {
                synchronizedNavigableMap = new SynchronizedNavigableMap(this.nm.headMap(toKey, inclusive), this.mutex);
            }
            return synchronizedNavigableMap;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            SynchronizedNavigableMap synchronizedNavigableMap;
            synchronized (this.mutex) {
                synchronizedNavigableMap = new SynchronizedNavigableMap(this.nm.tailMap(fromKey, inclusive), this.mutex);
            }
            return synchronizedNavigableMap;
        }
    }

    public static <E> Collection<E> checkedCollection(Collection<E> c, Class<E> type) {
        return new CheckedCollection(c, type);
    }

    static <T> T[] zeroLengthArray(Class<T> type) {
        return (T[]) ((Object[]) Array.newInstance((Class<?>) type, 0));
    }

    static class CheckedCollection<E> implements Collection<E>, Serializable {
        private static final long serialVersionUID = 1578914078182001775L;
        final Collection<E> c;
        final Class<E> type;
        private E[] zeroLengthElementArray;

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public E typeCheck(Object o) {
            if (o == 0 || this.type.isInstance(o)) {
                return o;
            }
            throw new ClassCastException(badElementMsg(o));
        }

        private String badElementMsg(Object o) {
            return "Attempt to insert " + ((Object) o.getClass()) + " element into collection with element type " + ((Object) this.type);
        }

        CheckedCollection(Collection<E> c2, Class<E> type2) {
            this.c = (Collection) Objects.requireNonNull(c2, "c");
            this.type = (Class) Objects.requireNonNull(type2, "type");
        }

        @Override // java.util.Collection
        public int size() {
            return this.c.size();
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return this.c.isEmpty();
        }

        @Override // java.util.Collection
        public boolean contains(Object o) {
            return this.c.contains(o);
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return this.c.toArray();
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] a) {
            return (T[]) this.c.toArray(a);
        }

        public String toString() {
            return this.c.toString();
        }

        @Override // java.util.Collection
        public boolean remove(Object o) {
            return this.c.remove(o);
        }

        @Override // java.util.Collection
        public void clear() {
            this.c.clear();
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> coll) {
            return this.c.containsAll(coll);
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> coll) {
            return this.c.removeAll(coll);
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> coll) {
            return this.c.retainAll(coll);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            final Iterator<E> it = this.c.iterator();
            return new Iterator<E>() {
                /* class java.util.Collections.CheckedCollection.AnonymousClass1 */

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override // java.util.Iterator
                public E next() {
                    return (E) it.next();
                }

                @Override // java.util.Iterator
                public void remove() {
                    it.remove();
                }
            };
        }

        @Override // java.util.Collection
        public boolean add(E e) {
            return this.c.add(typeCheck(e));
        }

        private E[] zeroLengthElementArray() {
            E[] eArr = this.zeroLengthElementArray;
            if (eArr != null) {
                return eArr;
            }
            E[] eArr2 = (E[]) Collections.zeroLengthArray(this.type);
            this.zeroLengthElementArray = eArr2;
            return eArr2;
        }

        /* access modifiers changed from: package-private */
        public Collection<E> checkedCopyOf(Collection<? extends E> coll) {
            Object[] a;
            try {
                E[] z = zeroLengthElementArray();
                a = coll.toArray(z);
                if (a.getClass() != z.getClass()) {
                    a = Arrays.copyOf(a, a.length, z.getClass());
                }
            } catch (ArrayStoreException e) {
                a = (Object[]) coll.toArray().clone();
                for (Object o : a) {
                    typeCheck(o);
                }
            }
            return Arrays.asList(a);
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends E> coll) {
            return this.c.addAll(checkedCopyOf(coll));
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> action) {
            this.c.forEach(action);
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super E> filter) {
            return this.c.removeIf(filter);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return this.c.spliterator();
        }

        @Override // java.util.Collection
        public Stream<E> stream() {
            return this.c.stream();
        }

        @Override // java.util.Collection
        public Stream<E> parallelStream() {
            return this.c.parallelStream();
        }
    }

    public static <E> Queue<E> checkedQueue(Queue<E> queue, Class<E> type) {
        return new CheckedQueue(queue, type);
    }

    static class CheckedQueue<E> extends CheckedCollection<E> implements Queue<E>, Serializable {
        private static final long serialVersionUID = 1433151992604707767L;
        final Queue<E> queue;

        CheckedQueue(Queue<E> queue2, Class<E> elementType) {
            super(queue2, elementType);
            this.queue = queue2;
        }

        @Override // java.util.Queue
        public E element() {
            return this.queue.element();
        }

        @Override // java.util.Collection
        public boolean equals(Object o) {
            return o == this || this.c.equals(o);
        }

        @Override // java.util.Collection
        public int hashCode() {
            return this.c.hashCode();
        }

        @Override // java.util.Queue
        public E peek() {
            return this.queue.peek();
        }

        @Override // java.util.Queue
        public E poll() {
            return this.queue.poll();
        }

        @Override // java.util.Queue
        public E remove() {
            return this.queue.remove();
        }

        @Override // java.util.Queue
        public boolean offer(E e) {
            return this.queue.offer(typeCheck(e));
        }
    }

    public static <E> Set<E> checkedSet(Set<E> s, Class<E> type) {
        return new CheckedSet(s, type);
    }

    static class CheckedSet<E> extends CheckedCollection<E> implements Set<E>, Serializable {
        private static final long serialVersionUID = 4694047833775013803L;

        CheckedSet(Set<E> s, Class<E> elementType) {
            super(s, elementType);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object o) {
            return o == this || this.c.equals(o);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return this.c.hashCode();
        }
    }

    public static <E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type) {
        return new CheckedSortedSet(s, type);
    }

    /* access modifiers changed from: package-private */
    public static class CheckedSortedSet<E> extends CheckedSet<E> implements SortedSet<E>, Serializable {
        private static final long serialVersionUID = 1599911165492914959L;
        private final SortedSet<E> ss;

        CheckedSortedSet(SortedSet<E> s, Class<E> type) {
            super(s, type);
            this.ss = s;
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return this.ss.comparator();
        }

        @Override // java.util.SortedSet
        public E first() {
            return this.ss.first();
        }

        @Override // java.util.SortedSet
        public E last() {
            return this.ss.last();
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E fromElement, E toElement) {
            return Collections.checkedSortedSet(this.ss.subSet(fromElement, toElement), this.type);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E toElement) {
            return Collections.checkedSortedSet(this.ss.headSet(toElement), this.type);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E fromElement) {
            return Collections.checkedSortedSet(this.ss.tailSet(fromElement), this.type);
        }
    }

    public static <E> NavigableSet<E> checkedNavigableSet(NavigableSet<E> s, Class<E> type) {
        return new CheckedNavigableSet(s, type);
    }

    /* access modifiers changed from: package-private */
    public static class CheckedNavigableSet<E> extends CheckedSortedSet<E> implements NavigableSet<E>, Serializable {
        private static final long serialVersionUID = -5429120189805438922L;
        private final NavigableSet<E> ns;

        CheckedNavigableSet(NavigableSet<E> s, Class<E> type) {
            super(s, type);
            this.ns = s;
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            return this.ns.lower(e);
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            return this.ns.floor(e);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            return this.ns.ceiling(e);
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            return this.ns.higher(e);
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            return this.ns.pollFirst();
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            return this.ns.pollLast();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return Collections.checkedNavigableSet(this.ns.descendingSet(), this.type);
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return Collections.checkedNavigableSet(this.ns.descendingSet(), this.type).iterator();
        }

        @Override // java.util.SortedSet, java.util.NavigableSet, java.util.Collections.CheckedSortedSet
        public NavigableSet<E> subSet(E fromElement, E toElement) {
            return Collections.checkedNavigableSet(this.ns.subSet(fromElement, true, toElement, false), this.type);
        }

        @Override // java.util.SortedSet, java.util.NavigableSet, java.util.Collections.CheckedSortedSet
        public NavigableSet<E> headSet(E toElement) {
            return Collections.checkedNavigableSet(this.ns.headSet(toElement, false), this.type);
        }

        @Override // java.util.SortedSet, java.util.NavigableSet, java.util.Collections.CheckedSortedSet
        public NavigableSet<E> tailSet(E fromElement) {
            return Collections.checkedNavigableSet(this.ns.tailSet(fromElement, true), this.type);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
            return Collections.checkedNavigableSet(this.ns.subSet(fromElement, fromInclusive, toElement, toInclusive), this.type);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E toElement, boolean inclusive) {
            return Collections.checkedNavigableSet(this.ns.headSet(toElement, inclusive), this.type);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
            return Collections.checkedNavigableSet(this.ns.tailSet(fromElement, inclusive), this.type);
        }
    }

    public static <E> List<E> checkedList(List<E> list, Class<E> type) {
        if (list instanceof RandomAccess) {
            return new CheckedRandomAccessList(list, type);
        }
        return new CheckedList(list, type);
    }

    static class CheckedList<E> extends CheckedCollection<E> implements List<E> {
        private static final long serialVersionUID = 65247728283967356L;
        final List<E> list;

        CheckedList(List<E> list2, Class<E> type) {
            super(list2, type);
            this.list = list2;
        }

        @Override // java.util.List, java.util.Collection
        public boolean equals(Object o) {
            return o == this || this.list.equals(o);
        }

        @Override // java.util.List, java.util.Collection
        public int hashCode() {
            return this.list.hashCode();
        }

        @Override // java.util.List
        public E get(int index) {
            return this.list.get(index);
        }

        @Override // java.util.List
        public E remove(int index) {
            return this.list.remove(index);
        }

        @Override // java.util.List
        public int indexOf(Object o) {
            return this.list.indexOf(o);
        }

        @Override // java.util.List
        public int lastIndexOf(Object o) {
            return this.list.lastIndexOf(o);
        }

        @Override // java.util.List
        public E set(int index, E element) {
            return this.list.set(index, typeCheck(element));
        }

        @Override // java.util.List
        public void add(int index, E element) {
            this.list.add(index, typeCheck(element));
        }

        @Override // java.util.List
        public boolean addAll(int index, Collection<? extends E> c) {
            return this.list.addAll(index, checkedCopyOf(c));
        }

        @Override // java.util.List
        public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override // java.util.List
        public ListIterator<E> listIterator(int index) {
            final ListIterator<E> i = this.list.listIterator(index);
            return new ListIterator<E>() {
                /* class java.util.Collections.CheckedList.AnonymousClass1 */

                @Override // java.util.Iterator, java.util.ListIterator
                public boolean hasNext() {
                    return i.hasNext();
                }

                @Override // java.util.Iterator, java.util.ListIterator
                public E next() {
                    return (E) i.next();
                }

                @Override // java.util.ListIterator
                public boolean hasPrevious() {
                    return i.hasPrevious();
                }

                @Override // java.util.ListIterator
                public E previous() {
                    return (E) i.previous();
                }

                @Override // java.util.ListIterator
                public int nextIndex() {
                    return i.nextIndex();
                }

                @Override // java.util.ListIterator
                public int previousIndex() {
                    return i.previousIndex();
                }

                @Override // java.util.Iterator, java.util.ListIterator
                public void remove() {
                    i.remove();
                }

                /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.ListIterator */
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.ListIterator
                public void set(E e) {
                    i.set(CheckedList.this.typeCheck(e));
                }

                /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.ListIterator */
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.ListIterator
                public void add(E e) {
                    i.add(CheckedList.this.typeCheck(e));
                }

                @Override // java.util.Iterator
                public void forEachRemaining(Consumer<? super E> action) {
                    i.forEachRemaining(action);
                }
            };
        }

        @Override // java.util.List
        public List<E> subList(int fromIndex, int toIndex) {
            return new CheckedList(this.list.subList(fromIndex, toIndex), this.type);
        }

        @Override // java.util.List
        public void replaceAll(UnaryOperator<E> operator) {
            Objects.requireNonNull(operator);
            this.list.replaceAll(new UnaryOperator(operator) {
                /* class java.util.$$Lambda$Collections$CheckedList$gXIP1Db1_l1aVeW3UfOh4dLyESo */
                private final /* synthetic */ UnaryOperator f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Collections.CheckedList.this.lambda$replaceAll$0$Collections$CheckedList(this.f$1, obj);
                }
            });
        }

        public /* synthetic */ Object lambda$replaceAll$0$Collections$CheckedList(UnaryOperator operator, Object e) {
            return typeCheck(operator.apply(e));
        }

        @Override // java.util.List
        public void sort(Comparator<? super E> c) {
            this.list.sort(c);
        }
    }

    static class CheckedRandomAccessList<E> extends CheckedList<E> implements RandomAccess {
        private static final long serialVersionUID = 1638200125423088369L;

        CheckedRandomAccessList(List<E> list, Class<E> type) {
            super(list, type);
        }

        @Override // java.util.List, java.util.Collections.CheckedList
        public List<E> subList(int fromIndex, int toIndex) {
            return new CheckedRandomAccessList(this.list.subList(fromIndex, toIndex), this.type);
        }
    }

    public static <K, V> Map<K, V> checkedMap(Map<K, V> m, Class<K> keyType, Class<V> valueType) {
        return new CheckedMap(m, keyType, valueType);
    }

    /* access modifiers changed from: private */
    public static class CheckedMap<K, V> implements Map<K, V>, Serializable {
        private static final long serialVersionUID = 5742860141034234728L;
        private transient Set<Map.Entry<K, V>> entrySet;
        final Class<K> keyType;
        private final Map<K, V> m;
        final Class<V> valueType;

        private void typeCheck(Object key, Object value) {
            if (key != null && !this.keyType.isInstance(key)) {
                throw new ClassCastException(badKeyMsg(key));
            } else if (value != null && !this.valueType.isInstance(value)) {
                throw new ClassCastException(badValueMsg(value));
            }
        }

        private BiFunction<? super K, ? super V, ? extends V> typeCheck(BiFunction<? super K, ? super V, ? extends V> func) {
            Objects.requireNonNull(func);
            return new BiFunction(func) {
                /* class java.util.$$Lambda$Collections$CheckedMap$5MCuh91_pd5SsNapFva5jp8gVs8 */
                private final /* synthetic */ BiFunction f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return Collections.CheckedMap.this.lambda$typeCheck$0$Collections$CheckedMap(this.f$1, obj, obj2);
                }
            };
        }

        public /* synthetic */ Object lambda$typeCheck$0$Collections$CheckedMap(BiFunction func, Object k, Object v) {
            Object apply = func.apply(k, v);
            typeCheck(k, apply);
            return apply;
        }

        private String badKeyMsg(Object key) {
            return "Attempt to insert " + ((Object) key.getClass()) + " key into map with key type " + ((Object) this.keyType);
        }

        private String badValueMsg(Object value) {
            return "Attempt to insert " + ((Object) value.getClass()) + " value into map with value type " + ((Object) this.valueType);
        }

        CheckedMap(Map<K, V> m2, Class<K> keyType2, Class<V> valueType2) {
            this.m = (Map) Objects.requireNonNull(m2);
            this.keyType = (Class) Objects.requireNonNull(keyType2);
            this.valueType = (Class) Objects.requireNonNull(valueType2);
        }

        @Override // java.util.Map
        public int size() {
            return this.m.size();
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.m.isEmpty();
        }

        @Override // java.util.Map
        public boolean containsKey(Object key) {
            return this.m.containsKey(key);
        }

        @Override // java.util.Map
        public boolean containsValue(Object v) {
            return this.m.containsValue(v);
        }

        @Override // java.util.Map
        public V get(Object key) {
            return this.m.get(key);
        }

        @Override // java.util.Map
        public V remove(Object key) {
            return this.m.remove(key);
        }

        @Override // java.util.Map
        public void clear() {
            this.m.clear();
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            return this.m.keySet();
        }

        @Override // java.util.Map
        public Collection<V> values() {
            return this.m.values();
        }

        @Override // java.util.Map
        public boolean equals(Object o) {
            return o == this || this.m.equals(o);
        }

        @Override // java.util.Map
        public int hashCode() {
            return this.m.hashCode();
        }

        public String toString() {
            return this.m.toString();
        }

        @Override // java.util.Map
        public V put(K key, V value) {
            typeCheck(key, value);
            return this.m.put(key, value);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.Map<K, V> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> t) {
            Object[] entries = t.entrySet().toArray();
            List<Map.Entry<K, V>> checked = new ArrayList<>(entries.length);
            for (Object o : entries) {
                Map.Entry<?, ?> e = (Map.Entry) o;
                Object k = e.getKey();
                Object v = e.getValue();
                typeCheck(k, v);
                checked.add(new AbstractMap.SimpleImmutableEntry<>(k, v));
            }
            for (Map.Entry<K, V> e2 : checked) {
                this.m.put(e2.getKey(), e2.getValue());
            }
        }

        @Override // java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            if (this.entrySet == null) {
                this.entrySet = new CheckedEntrySet(this.m.entrySet(), this.valueType);
            }
            return this.entrySet;
        }

        @Override // java.util.Map
        public void forEach(BiConsumer<? super K, ? super V> action) {
            this.m.forEach(action);
        }

        @Override // java.util.Map
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
            this.m.replaceAll(typeCheck(function));
        }

        @Override // java.util.Map
        public V putIfAbsent(K key, V value) {
            typeCheck(key, value);
            return this.m.putIfAbsent(key, value);
        }

        @Override // java.util.Map
        public boolean remove(Object key, Object value) {
            return this.m.remove(key, value);
        }

        @Override // java.util.Map
        public boolean replace(K key, V oldValue, V newValue) {
            typeCheck(key, newValue);
            return this.m.replace(key, oldValue, newValue);
        }

        @Override // java.util.Map
        public V replace(K key, V value) {
            typeCheck(key, value);
            return this.m.replace(key, value);
        }

        @Override // java.util.Map
        public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
            Objects.requireNonNull(mappingFunction);
            return this.m.computeIfAbsent(key, new Function(mappingFunction) {
                /* class java.util.$$Lambda$Collections$CheckedMap$hvicTzt8soLX23klS8sBMiCuEvk */
                private final /* synthetic */ Function f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Collections.CheckedMap.this.lambda$computeIfAbsent$1$Collections$CheckedMap(this.f$1, obj);
                }
            });
        }

        public /* synthetic */ Object lambda$computeIfAbsent$1$Collections$CheckedMap(Function mappingFunction, Object k) {
            Object apply = mappingFunction.apply(k);
            typeCheck(k, apply);
            return apply;
        }

        @Override // java.util.Map
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
            return this.m.computeIfPresent(key, typeCheck(remappingFunction));
        }

        @Override // java.util.Map
        public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
            return this.m.compute(key, typeCheck(remappingFunction));
        }

        @Override // java.util.Map
        public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
            Objects.requireNonNull(remappingFunction);
            return this.m.merge(key, value, new BiFunction(remappingFunction) {
                /* class java.util.$$Lambda$Collections$CheckedMap$UHd_dm3NjSdMnE1bZpQe0MXp_Gk */
                private final /* synthetic */ BiFunction f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return Collections.CheckedMap.this.lambda$merge$2$Collections$CheckedMap(this.f$1, obj, obj2);
                }
            });
        }

        public /* synthetic */ Object lambda$merge$2$Collections$CheckedMap(BiFunction remappingFunction, Object v1, Object v2) {
            Object apply = remappingFunction.apply(v1, v2);
            typeCheck(null, apply);
            return apply;
        }

        /* access modifiers changed from: package-private */
        public static class CheckedEntrySet<K, V> implements Set<Map.Entry<K, V>> {
            private final Set<Map.Entry<K, V>> s;
            private final Class<V> valueType;

            @Override // java.util.Collection, java.util.Set
            public /* bridge */ /* synthetic */ boolean add(Object obj) {
                return add((Map.Entry) ((Map.Entry) obj));
            }

            CheckedEntrySet(Set<Map.Entry<K, V>> s2, Class<V> valueType2) {
                this.s = s2;
                this.valueType = valueType2;
            }

            @Override // java.util.Collection, java.util.Set
            public int size() {
                return this.s.size();
            }

            @Override // java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return this.s.isEmpty();
            }

            public String toString() {
                return this.s.toString();
            }

            @Override // java.util.Collection, java.util.Set
            public int hashCode() {
                return this.s.hashCode();
            }

            @Override // java.util.Collection, java.util.Set
            public void clear() {
                this.s.clear();
            }

            public boolean add(Map.Entry<K, V> entry) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection, java.util.Set
            public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<K, V>> iterator() {
                final Iterator<Map.Entry<K, V>> i = this.s.iterator();
                final Class<V> valueType2 = this.valueType;
                return new Iterator<Map.Entry<K, V>>() {
                    /* class java.util.Collections.CheckedMap.CheckedEntrySet.AnonymousClass1 */

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        i.remove();
                    }

                    @Override // java.util.Iterator
                    public Map.Entry<K, V> next() {
                        return CheckedEntrySet.checkedEntry((Map.Entry) i.next(), valueType2);
                    }
                };
            }

            @Override // java.util.Collection, java.util.Set
            public Object[] toArray() {
                Object[] dest;
                Object[] source = this.s.toArray();
                if (CheckedEntry.class.isInstance(source.getClass().getComponentType())) {
                    dest = source;
                } else {
                    dest = new Object[source.length];
                }
                for (int i = 0; i < source.length; i++) {
                    dest[i] = checkedEntry((Map.Entry) source[i], this.valueType);
                }
                return dest;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: T[] */
            /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: java.lang.Object[] */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] a) {
                T[] arr = (T[]) this.s.toArray(a.length == 0 ? a : Arrays.copyOf(a, 0));
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = checkedEntry((Map.Entry) arr[i], this.valueType);
                }
                if (arr.length > a.length) {
                    return arr;
                }
                System.arraycopy(arr, 0, a, 0, arr.length);
                if (a.length > arr.length) {
                    a[arr.length] = null;
                }
                return a;
            }

            @Override // java.util.Collection, java.util.Set
            public boolean contains(Object o) {
                if (!(o instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry<?, ?> e = (Map.Entry) o;
                return this.s.contains(e instanceof CheckedEntry ? e : checkedEntry(e, this.valueType));
            }

            @Override // java.util.Collection, java.util.Set
            public boolean containsAll(Collection<?> c) {
                Iterator<?> it = c.iterator();
                while (it.hasNext()) {
                    if (!contains(it.next())) {
                        return false;
                    }
                }
                return true;
            }

            @Override // java.util.Collection, java.util.Set
            public boolean remove(Object o) {
                if (!(o instanceof Map.Entry)) {
                    return false;
                }
                return this.s.remove(new AbstractMap.SimpleImmutableEntry((Map.Entry) o));
            }

            @Override // java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> c) {
                return batchRemove(c, false);
            }

            @Override // java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> c) {
                return batchRemove(c, true);
            }

            private boolean batchRemove(Collection<?> c, boolean complement) {
                Objects.requireNonNull(c);
                boolean modified = false;
                Iterator<Map.Entry<K, V>> it = iterator();
                while (it.hasNext()) {
                    if (c.contains(it.next()) != complement) {
                        it.remove();
                        modified = true;
                    }
                }
                return modified;
            }

            @Override // java.util.Collection, java.util.Set
            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Set)) {
                    return false;
                }
                Set<?> that = (Set) o;
                if (that.size() != this.s.size() || !containsAll(that)) {
                    return false;
                }
                return true;
            }

            static <K, V, T> CheckedEntry<K, V, T> checkedEntry(Map.Entry<K, V> e, Class<T> valueType2) {
                return new CheckedEntry<>(e, valueType2);
            }

            /* access modifiers changed from: private */
            public static class CheckedEntry<K, V, T> implements Map.Entry<K, V> {
                private final Map.Entry<K, V> e;
                private final Class<T> valueType;

                CheckedEntry(Map.Entry<K, V> e2, Class<T> valueType2) {
                    this.e = (Map.Entry) Objects.requireNonNull(e2);
                    this.valueType = (Class) Objects.requireNonNull(valueType2);
                }

                @Override // java.util.Map.Entry
                public K getKey() {
                    return this.e.getKey();
                }

                @Override // java.util.Map.Entry
                public V getValue() {
                    return this.e.getValue();
                }

                @Override // java.util.Map.Entry
                public int hashCode() {
                    return this.e.hashCode();
                }

                public String toString() {
                    return this.e.toString();
                }

                @Override // java.util.Map.Entry
                public V setValue(V value) {
                    if (value == null || this.valueType.isInstance(value)) {
                        return this.e.setValue(value);
                    }
                    throw new ClassCastException(badValueMsg(value));
                }

                private String badValueMsg(Object value) {
                    return "Attempt to insert " + ((Object) value.getClass()) + " value into map with value type " + ((Object) this.valueType);
                }

                @Override // java.util.Map.Entry
                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof Map.Entry)) {
                        return false;
                    }
                    return this.e.equals(new AbstractMap.SimpleImmutableEntry((Map.Entry) o));
                }
            }
        }
    }

    public static <K, V> SortedMap<K, V> checkedSortedMap(SortedMap<K, V> m, Class<K> keyType, Class<V> valueType) {
        return new CheckedSortedMap(m, keyType, valueType);
    }

    /* access modifiers changed from: package-private */
    public static class CheckedSortedMap<K, V> extends CheckedMap<K, V> implements SortedMap<K, V>, Serializable {
        private static final long serialVersionUID = 1599671320688067438L;
        private final SortedMap<K, V> sm;

        CheckedSortedMap(SortedMap<K, V> m, Class<K> keyType, Class<V> valueType) {
            super(m, keyType, valueType);
            this.sm = m;
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.sm.comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return this.sm.firstKey();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return this.sm.lastKey();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            return Collections.checkedSortedMap(this.sm.subMap(fromKey, toKey), this.keyType, this.valueType);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K toKey) {
            return Collections.checkedSortedMap(this.sm.headMap(toKey), this.keyType, this.valueType);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K fromKey) {
            return Collections.checkedSortedMap(this.sm.tailMap(fromKey), this.keyType, this.valueType);
        }
    }

    public static <K, V> NavigableMap<K, V> checkedNavigableMap(NavigableMap<K, V> m, Class<K> keyType, Class<V> valueType) {
        return new CheckedNavigableMap(m, keyType, valueType);
    }

    /* access modifiers changed from: package-private */
    public static class CheckedNavigableMap<K, V> extends CheckedSortedMap<K, V> implements NavigableMap<K, V>, Serializable {
        private static final long serialVersionUID = -4852462692372534096L;
        private final NavigableMap<K, V> nm;

        CheckedNavigableMap(NavigableMap<K, V> m, Class<K> keyType, Class<V> valueType) {
            super(m, keyType, valueType);
            this.nm = m;
        }

        @Override // java.util.Collections.CheckedSortedMap, java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.nm.comparator();
        }

        @Override // java.util.Collections.CheckedSortedMap, java.util.SortedMap
        public K firstKey() {
            return this.nm.firstKey();
        }

        @Override // java.util.Collections.CheckedSortedMap, java.util.SortedMap
        public K lastKey() {
            return this.nm.lastKey();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K key) {
            Map.Entry<K, V> lower = this.nm.lowerEntry(key);
            if (lower != null) {
                return new CheckedMap.CheckedEntrySet.CheckedEntry(lower, this.valueType);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K key) {
            return this.nm.lowerKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K key) {
            Map.Entry<K, V> floor = this.nm.floorEntry(key);
            if (floor != null) {
                return new CheckedMap.CheckedEntrySet.CheckedEntry(floor, this.valueType);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public K floorKey(K key) {
            return this.nm.floorKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K key) {
            Map.Entry<K, V> ceiling = this.nm.ceilingEntry(key);
            if (ceiling != null) {
                return new CheckedMap.CheckedEntrySet.CheckedEntry(ceiling, this.valueType);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K key) {
            return this.nm.ceilingKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K key) {
            Map.Entry<K, V> higher = this.nm.higherEntry(key);
            if (higher != null) {
                return new CheckedMap.CheckedEntrySet.CheckedEntry(higher, this.valueType);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public K higherKey(K key) {
            return this.nm.higherKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            Map.Entry<K, V> first = this.nm.firstEntry();
            if (first != null) {
                return new CheckedMap.CheckedEntrySet.CheckedEntry(first, this.valueType);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            Map.Entry<K, V> last = this.nm.lastEntry();
            if (last != null) {
                return new CheckedMap.CheckedEntrySet.CheckedEntry(last, this.valueType);
            }
            return null;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            Map.Entry<K, V> entry = this.nm.pollFirstEntry();
            if (entry == null) {
                return null;
            }
            return new CheckedMap.CheckedEntrySet.CheckedEntry(entry, this.valueType);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            Map.Entry<K, V> entry = this.nm.pollLastEntry();
            if (entry == null) {
                return null;
            }
            return new CheckedMap.CheckedEntrySet.CheckedEntry(entry, this.valueType);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return Collections.checkedNavigableMap(this.nm.descendingMap(), this.keyType, this.valueType);
        }

        @Override // java.util.Map, java.util.Collections.CheckedMap, java.util.SortedMap
        public NavigableSet<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return Collections.checkedNavigableSet(this.nm.navigableKeySet(), this.keyType);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return Collections.checkedNavigableSet(this.nm.descendingKeySet(), this.keyType);
        }

        @Override // java.util.Collections.CheckedSortedMap, java.util.NavigableMap, java.util.SortedMap
        public NavigableMap<K, V> subMap(K fromKey, K toKey) {
            return Collections.checkedNavigableMap(this.nm.subMap(fromKey, true, toKey, false), this.keyType, this.valueType);
        }

        @Override // java.util.Collections.CheckedSortedMap, java.util.NavigableMap, java.util.SortedMap
        public NavigableMap<K, V> headMap(K toKey) {
            return Collections.checkedNavigableMap(this.nm.headMap(toKey, false), this.keyType, this.valueType);
        }

        @Override // java.util.Collections.CheckedSortedMap, java.util.NavigableMap, java.util.SortedMap
        public NavigableMap<K, V> tailMap(K fromKey) {
            return Collections.checkedNavigableMap(this.nm.tailMap(fromKey, true), this.keyType, this.valueType);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            return Collections.checkedNavigableMap(this.nm.subMap(fromKey, fromInclusive, toKey, toInclusive), this.keyType, this.valueType);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            return Collections.checkedNavigableMap(this.nm.headMap(toKey, inclusive), this.keyType, this.valueType);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            return Collections.checkedNavigableMap(this.nm.tailMap(fromKey, inclusive), this.keyType, this.valueType);
        }
    }

    public static <T> Iterator<T> emptyIterator() {
        return EmptyIterator.EMPTY_ITERATOR;
    }

    /* access modifiers changed from: private */
    public static class EmptyIterator<E> implements Iterator<E> {
        static final EmptyIterator<Object> EMPTY_ITERATOR = new EmptyIterator<>();

        private EmptyIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public E next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }

        @Override // java.util.Iterator
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
        }
    }

    public static <T> ListIterator<T> emptyListIterator() {
        return EmptyListIterator.EMPTY_ITERATOR;
    }

    /* access modifiers changed from: private */
    public static class EmptyListIterator<E> extends EmptyIterator<E> implements ListIterator<E> {
        static final EmptyListIterator<Object> EMPTY_ITERATOR = new EmptyListIterator<>();

        private EmptyListIterator() {
            super();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return false;
        }

        @Override // java.util.ListIterator
        public E previous() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return 0;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            throw new IllegalStateException();
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    public static <T> Enumeration<T> emptyEnumeration() {
        return EmptyEnumeration.EMPTY_ENUMERATION;
    }

    /* access modifiers changed from: private */
    public static class EmptyEnumeration<E> implements Enumeration<E> {
        static final EmptyEnumeration<Object> EMPTY_ENUMERATION = new EmptyEnumeration<>();

        private EmptyEnumeration() {
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public E nextElement() {
            throw new NoSuchElementException();
        }
    }

    public static final <T> Set<T> emptySet() {
        return EMPTY_SET;
    }

    private static class EmptySet<E> extends AbstractSet<E> implements Serializable {
        private static final long serialVersionUID = 1582296315990362920L;

        private EmptySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<E> iterator() {
            return Collections.emptyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> c) {
            return c.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return new Object[0];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] a) {
            if (a.length > 0) {
                a[0] = null;
            }
            return a;
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> action) {
            Objects.requireNonNull(action);
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super E> filter) {
            Objects.requireNonNull(filter);
            return false;
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return Spliterators.emptySpliterator();
        }

        private Object readResolve() {
            return Collections.EMPTY_SET;
        }
    }

    public static <E> SortedSet<E> emptySortedSet() {
        return UnmodifiableNavigableSet.EMPTY_NAVIGABLE_SET;
    }

    public static <E> NavigableSet<E> emptyNavigableSet() {
        return UnmodifiableNavigableSet.EMPTY_NAVIGABLE_SET;
    }

    public static final <T> List<T> emptyList() {
        return EMPTY_LIST;
    }

    private static class EmptyList<E> extends AbstractList<E> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 8842843931221139166L;

        private EmptyList() {
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
        public Iterator<E> iterator() {
            return Collections.emptyIterator();
        }

        @Override // java.util.List, java.util.AbstractList
        public ListIterator<E> listIterator() {
            return Collections.emptyListIterator();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return 0;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean isEmpty() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean contains(Object obj) {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean containsAll(Collection<?> c) {
            return c.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public Object[] toArray() {
            return new Object[0];
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public <T> T[] toArray(T[] a) {
            if (a.length > 0) {
                a[0] = null;
            }
            return a;
        }

        @Override // java.util.List, java.util.AbstractList
        public E get(int index) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        @Override // java.util.List, java.util.Collection, java.util.AbstractList
        public boolean equals(Object o) {
            return (o instanceof List) && ((List) o).isEmpty();
        }

        @Override // java.util.List, java.util.Collection, java.util.AbstractList
        public int hashCode() {
            return 1;
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super E> filter) {
            Objects.requireNonNull(filter);
            return false;
        }

        @Override // java.util.List
        public void replaceAll(UnaryOperator<E> operator) {
            Objects.requireNonNull(operator);
        }

        @Override // java.util.List
        public void sort(Comparator<? super E> comparator) {
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> action) {
            Objects.requireNonNull(action);
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return Spliterators.emptySpliterator();
        }

        private Object readResolve() {
            return Collections.EMPTY_LIST;
        }
    }

    public static final <K, V> Map<K, V> emptyMap() {
        return EMPTY_MAP;
    }

    public static final <K, V> SortedMap<K, V> emptySortedMap() {
        return UnmodifiableNavigableMap.EMPTY_NAVIGABLE_MAP;
    }

    public static final <K, V> NavigableMap<K, V> emptyNavigableMap() {
        return UnmodifiableNavigableMap.EMPTY_NAVIGABLE_MAP;
    }

    private static class EmptyMap<K, V> extends AbstractMap<K, V> implements Serializable {
        private static final long serialVersionUID = 6428348081105594320L;

        private EmptyMap() {
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return 0;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return true;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsValue(Object value) {
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return Collections.emptySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> values() {
            return Collections.emptySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            return Collections.emptySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean equals(Object o) {
            return (o instanceof Map) && ((Map) o).isEmpty();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int hashCode() {
            return 0;
        }

        @Override // java.util.Map
        public V getOrDefault(Object k, V defaultValue) {
            return defaultValue;
        }

        @Override // java.util.Map
        public void forEach(BiConsumer<? super K, ? super V> action) {
            Objects.requireNonNull(action);
        }

        @Override // java.util.Map
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
            Objects.requireNonNull(function);
        }

        @Override // java.util.Map
        public V putIfAbsent(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public boolean remove(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public boolean replace(K k, V v, V v2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V replace(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V computeIfAbsent(K k, Function<? super K, ? extends V> function) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V computeIfPresent(K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V compute(K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V merge(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }

        private Object readResolve() {
            return Collections.EMPTY_MAP;
        }
    }

    public static <T> Set<T> singleton(T o) {
        return new SingletonSet(o);
    }

    static <E> Iterator<E> singletonIterator(final E e) {
        return new Iterator<E>() {
            /* class java.util.Collections.AnonymousClass1 */
            private boolean hasNext = true;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.hasNext;
            }

            @Override // java.util.Iterator
            public E next() {
                if (this.hasNext) {
                    this.hasNext = false;
                    return (E) Object.this;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Iterator
            public void forEachRemaining(Consumer<? super E> action) {
                Objects.requireNonNull(action);
                if (this.hasNext) {
                    action.accept((Object) Object.this);
                    this.hasNext = false;
                }
            }
        };
    }

    static <T> Spliterator<T> singletonSpliterator(final T element) {
        return new Spliterator<T>() {
            /* class java.util.Collections.AnonymousClass2 */
            long est = 1;

            @Override // java.util.Spliterator
            public Spliterator<T> trySplit() {
                return null;
            }

            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super T> consumer) {
                Objects.requireNonNull(consumer);
                long j = this.est;
                if (j <= 0) {
                    return false;
                }
                this.est = j - 1;
                consumer.accept((Object) Object.this);
                return true;
            }

            @Override // java.util.Spliterator
            public void forEachRemaining(Consumer<? super T> consumer) {
                tryAdvance(consumer);
            }

            @Override // java.util.Spliterator
            public long estimateSize() {
                return this.est;
            }

            @Override // java.util.Spliterator
            public int characteristics() {
                return (Object.this != null ? 256 : 0) | 64 | 16384 | 1024 | 1 | 16;
            }
        };
    }

    /* access modifiers changed from: private */
    public static class SingletonSet<E> extends AbstractSet<E> implements Serializable {
        private static final long serialVersionUID = 3193687207550431679L;
        private final E element;

        SingletonSet(E e) {
            this.element = e;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<E> iterator() {
            return Collections.singletonIterator(this.element);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return 1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return Collections.eq(o, this.element);
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> action) {
            action.accept(this.element);
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return Collections.singletonSpliterator(this.element);
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super E> predicate) {
            throw new UnsupportedOperationException();
        }
    }

    public static <T> List<T> singletonList(T o) {
        return new SingletonList(o);
    }

    private static class SingletonList<E> extends AbstractList<E> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 3093736618740652951L;
        private final E element;

        SingletonList(E obj) {
            this.element = obj;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
        public Iterator<E> iterator() {
            return Collections.singletonIterator(this.element);
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return 1;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean contains(Object obj) {
            return Collections.eq(obj, this.element);
        }

        @Override // java.util.List, java.util.AbstractList
        public E get(int index) {
            if (index == 0) {
                return this.element;
            }
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: 1");
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> action) {
            action.accept(this.element);
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super E> predicate) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public void replaceAll(UnaryOperator<E> unaryOperator) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public void sort(Comparator<? super E> comparator) {
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return Collections.singletonSpliterator(this.element);
        }
    }

    public static <K, V> Map<K, V> singletonMap(K key, V value) {
        return new SingletonMap(key, value);
    }

    private static class SingletonMap<K, V> extends AbstractMap<K, V> implements Serializable {
        private static final long serialVersionUID = -6979724477215052911L;
        private transient Set<Map.Entry<K, V>> entrySet;
        private final K k;
        private transient Set<K> keySet;
        private final V v;
        private transient Collection<V> values;

        SingletonMap(K key, V value) {
            this.k = key;
            this.v = value;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return 1;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return Collections.eq(key, this.k);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsValue(Object value) {
            return Collections.eq(value, this.v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            if (Collections.eq(key, this.k)) {
                return this.v;
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            if (this.keySet == null) {
                this.keySet = Collections.singleton(this.k);
            }
            return this.keySet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            if (this.entrySet == null) {
                this.entrySet = Collections.singleton(new AbstractMap.SimpleImmutableEntry(this.k, this.v));
            }
            return this.entrySet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> values() {
            if (this.values == null) {
                this.values = Collections.singleton(this.v);
            }
            return this.values;
        }

        @Override // java.util.Map
        public V getOrDefault(Object key, V defaultValue) {
            return Collections.eq(key, this.k) ? this.v : defaultValue;
        }

        @Override // java.util.Map
        public void forEach(BiConsumer<? super K, ? super V> action) {
            action.accept(this.k, this.v);
        }

        @Override // java.util.Map
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V putIfAbsent(K k2, V v2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public boolean remove(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public boolean replace(K k2, V v2, V v3) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V replace(K k2, V v2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V computeIfAbsent(K k2, Function<? super K, ? extends V> function) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V computeIfPresent(K k2, BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V compute(K k2, BiFunction<? super K, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public V merge(K k2, V v2, BiFunction<? super V, ? super V, ? extends V> biFunction) {
            throw new UnsupportedOperationException();
        }
    }

    public static <T> List<T> nCopies(int n, T o) {
        if (n >= 0) {
            return new CopiesList(n, o);
        }
        throw new IllegalArgumentException("List length = " + n);
    }

    /* access modifiers changed from: private */
    public static class CopiesList<E> extends AbstractList<E> implements RandomAccess, Serializable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long serialVersionUID = 2739099268398711800L;
        final E element;
        final int n;

        CopiesList(int n2, E e) {
            this.n = n2;
            this.element = e;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return this.n;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean contains(Object obj) {
            return this.n != 0 && Collections.eq(obj, this.element);
        }

        @Override // java.util.List, java.util.AbstractList
        public int indexOf(Object o) {
            return contains(o) ? 0 : -1;
        }

        @Override // java.util.List, java.util.AbstractList
        public int lastIndexOf(Object o) {
            if (contains(o)) {
                return this.n - 1;
            }
            return -1;
        }

        @Override // java.util.List, java.util.AbstractList
        public E get(int index) {
            if (index >= 0 && index < this.n) {
                return this.element;
            }
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.n);
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public Object[] toArray() {
            int i = this.n;
            Object[] a = new Object[i];
            E e = this.element;
            if (e != null) {
                Arrays.fill(a, 0, i, e);
            }
            return a;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public <T> T[] toArray(T[] a) {
            int n2 = this.n;
            if (a.length < n2) {
                a = (T[]) ((Object[]) Array.newInstance(a.getClass().getComponentType(), n2));
                E e = this.element;
                if (e != null) {
                    Arrays.fill(a, 0, n2, e);
                }
            } else {
                Arrays.fill(a, 0, n2, this.element);
                if (a.length > n2) {
                    a[n2] = null;
                }
            }
            return a;
        }

        @Override // java.util.List, java.util.AbstractList
        public List<E> subList(int fromIndex, int toIndex) {
            if (fromIndex < 0) {
                throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
            } else if (toIndex > this.n) {
                throw new IndexOutOfBoundsException("toIndex = " + toIndex);
            } else if (fromIndex <= toIndex) {
                return new CopiesList(toIndex - fromIndex, this.element);
            } else {
                throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
            }
        }

        public /* synthetic */ Object lambda$stream$0$Collections$CopiesList(int i) {
            return this.element;
        }

        @Override // java.util.Collection
        public Stream<E> stream() {
            return IntStream.range(0, this.n).mapToObj(new IntFunction() {
                /* class java.util.$$Lambda$Collections$CopiesList$uHL7LkfEBowvpXOMQGFkZqUGxm4 */

                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return Collections.CopiesList.this.lambda$stream$0$Collections$CopiesList(i);
                }
            });
        }

        public /* synthetic */ Object lambda$parallelStream$1$Collections$CopiesList(int i) {
            return this.element;
        }

        @Override // java.util.Collection
        public Stream<E> parallelStream() {
            return IntStream.range(0, this.n).parallel().mapToObj(new IntFunction() {
                /* class java.util.$$Lambda$Collections$CopiesList$JkPuGMNhrKbnEHjebm8AvHc2xHw */

                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return Collections.CopiesList.this.lambda$parallelStream$1$Collections$CopiesList(i);
                }
            });
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return stream().spliterator();
        }
    }

    public static <T> Comparator<T> reverseOrder() {
        return ReverseComparator.REVERSE_ORDER;
    }

    /* access modifiers changed from: private */
    public static class ReverseComparator implements Comparator<Comparable<Object>>, Serializable {
        static final ReverseComparator REVERSE_ORDER = new ReverseComparator();
        private static final long serialVersionUID = 7207038068494060240L;

        private ReverseComparator() {
        }

        public int compare(Comparable<Object> c1, Comparable<Object> c2) {
            return c2.compareTo(c1);
        }

        private Object readResolve() {
            return Collections.reverseOrder();
        }

        @Override // java.util.Comparator
        public Comparator<Comparable<Object>> reversed() {
            return Comparator.naturalOrder();
        }
    }

    public static <T> Comparator<T> reverseOrder(Comparator<T> cmp) {
        if (cmp == null) {
            return reverseOrder();
        }
        if (cmp instanceof ReverseComparator2) {
            return ((ReverseComparator2) cmp).cmp;
        }
        return new ReverseComparator2(cmp);
    }

    /* access modifiers changed from: private */
    public static class ReverseComparator2<T> implements Comparator<T>, Serializable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long serialVersionUID = 4374092139857L;
        final Comparator<T> cmp;

        ReverseComparator2(Comparator<T> cmp2) {
            this.cmp = cmp2;
        }

        @Override // java.util.Comparator
        public int compare(T t1, T t2) {
            return this.cmp.compare(t2, t1);
        }

        @Override // java.util.Comparator
        public boolean equals(Object o) {
            return o == this || ((o instanceof ReverseComparator2) && this.cmp.equals(((ReverseComparator2) o).cmp));
        }

        public int hashCode() {
            return this.cmp.hashCode() ^ Integer.MIN_VALUE;
        }

        @Override // java.util.Comparator
        public Comparator<T> reversed() {
            return this.cmp;
        }
    }

    public static <T> Enumeration<T> enumeration(final Collection<T> c) {
        return new Enumeration<T>() {
            /* class java.util.Collections.AnonymousClass3 */
            private final Iterator<T> i = Collection.this.iterator();

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.i.hasNext();
            }

            @Override // java.util.Enumeration
            public T nextElement() {
                return this.i.next();
            }
        };
    }

    public static <T> ArrayList<T> list(Enumeration<T> e) {
        ArrayList<T> l = new ArrayList<>();
        while (e.hasMoreElements()) {
            l.add(e.nextElement());
        }
        return l;
    }

    static boolean eq(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
    }

    public static int frequency(Collection<?> c, Object o) {
        int result = 0;
        if (o == null) {
            Iterator<?> it = c.iterator();
            while (it.hasNext()) {
                if (it.next() == null) {
                    result++;
                }
            }
        } else {
            Iterator<?> it2 = c.iterator();
            while (it2.hasNext()) {
                if (o.equals(it2.next())) {
                    result++;
                }
            }
        }
        return result;
    }

    public static boolean disjoint(Collection<?> c1, Collection<?> c2) {
        Collection<?> contains = c2;
        Collection<?> iterate = c1;
        if (c1 instanceof Set) {
            iterate = c2;
            contains = c1;
        } else if (!(c2 instanceof Set)) {
            int c1size = c1.size();
            int c2size = c2.size();
            if (c1size == 0 || c2size == 0) {
                return true;
            }
            if (c1size > c2size) {
                iterate = c2;
                contains = c1;
            }
        }
        Iterator<?> it = iterate.iterator();
        while (it.hasNext()) {
            if (contains.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    public static <T> boolean addAll(Collection<? super T> c, T... elements) {
        boolean result = false;
        for (T element : elements) {
            result |= c.add(element);
        }
        return result;
    }

    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return new SetFromMap(map);
    }

    /* access modifiers changed from: private */
    public static class SetFromMap<E> extends AbstractSet<E> implements Set<E>, Serializable {
        private static final long serialVersionUID = 2454657854757543876L;
        private final Map<E, Boolean> m;
        private transient Set<E> s;

        SetFromMap(Map<E, Boolean> map) {
            if (map.isEmpty()) {
                this.m = map;
                this.s = map.keySet();
                return;
            }
            throw new IllegalArgumentException("Map is non-empty");
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.m.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.m.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.m.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return this.m.containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return this.m.remove(o) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(E e) {
            return this.m.put(e, Boolean.TRUE) == null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<E> iterator() {
            return this.s.iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return this.s.toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] a) {
            return (T[]) this.s.toArray(a);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.s.toString();
        }

        @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
        public int hashCode() {
            return this.s.hashCode();
        }

        @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean equals(Object o) {
            return o == this || this.s.equals(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> c) {
            return this.s.containsAll(c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean removeAll(Collection<?> c) {
            return this.s.removeAll(c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            return this.s.retainAll(c);
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> action) {
            this.s.forEach(action);
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super E> filter) {
            return this.s.removeIf(filter);
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return this.s.spliterator();
        }

        @Override // java.util.Collection
        public Stream<E> stream() {
            return this.s.stream();
        }

        @Override // java.util.Collection
        public Stream<E> parallelStream() {
            return this.s.parallelStream();
        }

        private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
            stream.defaultReadObject();
            this.s = this.m.keySet();
        }
    }

    public static <T> Queue<T> asLifoQueue(Deque<T> deque) {
        return new AsLIFOQueue(deque);
    }

    static class AsLIFOQueue<E> extends AbstractQueue<E> implements Queue<E>, Serializable {
        private static final long serialVersionUID = 1802017725587941708L;
        private final Deque<E> q;

        AsLIFOQueue(Deque<E> q2) {
            this.q = q2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue, java.util.Queue
        public boolean add(E e) {
            this.q.addFirst(e);
            return true;
        }

        @Override // java.util.Queue
        public boolean offer(E e) {
            return this.q.offerFirst(e);
        }

        @Override // java.util.Queue
        public E poll() {
            return this.q.pollFirst();
        }

        @Override // java.util.AbstractQueue, java.util.Queue
        public E remove() {
            return this.q.removeFirst();
        }

        @Override // java.util.Queue
        public E peek() {
            return this.q.peekFirst();
        }

        @Override // java.util.AbstractQueue, java.util.Queue
        public E element() {
            return this.q.getFirst();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue
        public void clear() {
            this.q.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.q.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.q.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            return this.q.contains(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object o) {
            return this.q.remove(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return this.q.iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return this.q.toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] a) {
            return (T[]) this.q.toArray(a);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.q.toString();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> c) {
            return this.q.containsAll(c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> c) {
            return this.q.removeAll(c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> c) {
            return this.q.retainAll(c);
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> action) {
            this.q.forEach(action);
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super E> filter) {
            return this.q.removeIf(filter);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return this.q.spliterator();
        }

        @Override // java.util.Collection
        public Stream<E> stream() {
            return this.q.stream();
        }

        @Override // java.util.Collection
        public Stream<E> parallelStream() {
            return this.q.parallelStream();
        }
    }
}
