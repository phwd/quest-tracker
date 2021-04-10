package java.lang;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class ThreadLocal<T> {
    private static final int HASH_INCREMENT = 1640531527;
    private static AtomicInteger nextHashCode = new AtomicInteger();
    private final int threadLocalHashCode = nextHashCode();

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    /* access modifiers changed from: protected */
    public T initialValue() {
        return null;
    }

    public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
        return new SuppliedThreadLocal(supplier);
    }

    public T get() {
        ThreadLocalMap.Entry e;
        ThreadLocalMap map = getMap(Thread.currentThread());
        return (map == null || (e = map.getEntry(this)) == null) ? setInitialValue() : (T) e.value;
    }

    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            map.set(this, value);
        } else {
            createMap(t, value);
        }
        return value;
    }

    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            map.set(this, value);
        } else {
            createMap(t, value);
        }
    }

    public void remove() {
        ThreadLocalMap m = getMap(Thread.currentThread());
        if (m != null) {
            m.remove(this);
        }
    }

    /* access modifiers changed from: package-private */
    public ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }

    /* access modifiers changed from: package-private */
    public void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap((ThreadLocal<?>) this, (Object) firstValue);
    }

    static ThreadLocalMap createInheritedMap(ThreadLocalMap parentMap) {
        return new ThreadLocalMap(parentMap);
    }

    /* access modifiers changed from: package-private */
    public T childValue(T t) {
        throw new UnsupportedOperationException();
    }

    static final class SuppliedThreadLocal<T> extends ThreadLocal<T> {
        private final Supplier<? extends T> supplier;

        SuppliedThreadLocal(Supplier<? extends T> supplier2) {
            this.supplier = (Supplier) Objects.requireNonNull(supplier2);
        }

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public T initialValue() {
            return (T) this.supplier.get();
        }
    }

    /* access modifiers changed from: package-private */
    public static class ThreadLocalMap {
        private static final int INITIAL_CAPACITY = 16;
        private int size;
        private Entry[] table;
        private int threshold;

        /* access modifiers changed from: package-private */
        public static class Entry extends WeakReference<ThreadLocal<?>> {
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                this.value = v;
            }
        }

        private void setThreshold(int len) {
            this.threshold = (len * 2) / 3;
        }

        private static int nextIndex(int i, int len) {
            if (i + 1 < len) {
                return i + 1;
            }
            return 0;
        }

        private static int prevIndex(int i, int len) {
            return i + -1 >= 0 ? i - 1 : len - 1;
        }

        ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
            this.size = 0;
            this.table = new Entry[16];
            this.table[((ThreadLocal) firstKey).threadLocalHashCode & 15] = new Entry(firstKey, firstValue);
            this.size = 1;
            setThreshold(16);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: java.lang.ThreadLocal */
        /* JADX WARN: Multi-variable type inference failed */
        private ThreadLocalMap(ThreadLocalMap parentMap) {
            ThreadLocal<Object> key;
            Entry[] entryArr;
            this.size = 0;
            Entry[] parentTable = parentMap.table;
            int len = parentTable.length;
            setThreshold(len);
            this.table = new Entry[len];
            for (Entry e : parentTable) {
                if (!(e == null || (key = (ThreadLocal) e.get()) == null)) {
                    Entry c = new Entry(key, key.childValue(e.value));
                    int h = ((ThreadLocal) key).threadLocalHashCode & (len - 1);
                    while (true) {
                        entryArr = this.table;
                        if (entryArr[h] == null) {
                            break;
                        }
                        h = nextIndex(h, len);
                    }
                    entryArr[h] = c;
                    this.size++;
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Entry getEntry(ThreadLocal<?> key) {
            int i = ((ThreadLocal) key).threadLocalHashCode;
            Entry[] entryArr = this.table;
            int i2 = i & (entryArr.length - 1);
            Entry e = entryArr[i2];
            if (e == null || e.get() != key) {
                return getEntryAfterMiss(key, i2, e);
            }
            return e;
        }

        private Entry getEntryAfterMiss(ThreadLocal<?> key, int i, Entry e) {
            Entry[] tab = this.table;
            int len = tab.length;
            while (e != null) {
                ThreadLocal<?> k = (ThreadLocal) e.get();
                if (k == key) {
                    return e;
                }
                if (k == null) {
                    expungeStaleEntry(i);
                } else {
                    i = nextIndex(i, len);
                }
                e = tab[i];
            }
            return null;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void set(ThreadLocal<?> key, Object value) {
            Entry[] tab = this.table;
            int len = tab.length;
            int i = ((ThreadLocal) key).threadLocalHashCode & (len - 1);
            Entry e = tab[i];
            while (e != null) {
                ThreadLocal<?> k = (ThreadLocal) e.get();
                if (k == key) {
                    e.value = value;
                    return;
                } else if (k == null) {
                    replaceStaleEntry(key, value, i);
                    return;
                } else {
                    int nextIndex = nextIndex(i, len);
                    i = nextIndex;
                    e = tab[nextIndex];
                }
            }
            tab[i] = new Entry(key, value);
            int sz = this.size + 1;
            this.size = sz;
            if (!cleanSomeSlots(i, sz) && sz >= this.threshold) {
                rehash();
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void remove(ThreadLocal<?> key) {
            Entry[] tab = this.table;
            int len = tab.length;
            int i = ((ThreadLocal) key).threadLocalHashCode & (len - 1);
            Entry e = tab[i];
            while (e != null) {
                if (e.get() == key) {
                    e.clear();
                    expungeStaleEntry(i);
                    return;
                }
                int nextIndex = nextIndex(i, len);
                i = nextIndex;
                e = tab[nextIndex];
            }
        }

        private void replaceStaleEntry(ThreadLocal<?> key, Object value, int staleSlot) {
            Entry[] tab = this.table;
            int len = tab.length;
            int slotToExpunge = staleSlot;
            int i = prevIndex(staleSlot, len);
            while (true) {
                Entry e = tab[i];
                if (e == null) {
                    break;
                }
                if (e.get() == null) {
                    slotToExpunge = i;
                }
                i = prevIndex(i, len);
            }
            int i2 = nextIndex(staleSlot, len);
            while (true) {
                Entry e2 = tab[i2];
                if (e2 != null) {
                    ThreadLocal<?> k = (ThreadLocal) e2.get();
                    if (k == key) {
                        e2.value = value;
                        tab[i2] = tab[staleSlot];
                        tab[staleSlot] = e2;
                        if (slotToExpunge == staleSlot) {
                            slotToExpunge = i2;
                        }
                        cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
                        return;
                    }
                    if (k == null && slotToExpunge == staleSlot) {
                        slotToExpunge = i2;
                    }
                    i2 = nextIndex(i2, len);
                } else {
                    tab[staleSlot].value = null;
                    tab[staleSlot] = new Entry(key, value);
                    if (slotToExpunge != staleSlot) {
                        cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
                        return;
                    }
                    return;
                }
            }
        }

        private int expungeStaleEntry(int staleSlot) {
            Entry[] tab = this.table;
            int len = tab.length;
            tab[staleSlot].value = null;
            tab[staleSlot] = null;
            this.size--;
            int i = nextIndex(staleSlot, len);
            while (true) {
                Entry e = tab[i];
                if (e == null) {
                    return i;
                }
                ThreadLocal<?> k = (ThreadLocal) e.get();
                if (k == null) {
                    e.value = null;
                    tab[i] = null;
                    this.size--;
                } else {
                    int h = ((ThreadLocal) k).threadLocalHashCode & (len - 1);
                    if (h != i) {
                        tab[i] = null;
                        while (tab[h] != null) {
                            h = nextIndex(h, len);
                        }
                        tab[h] = e;
                    }
                }
                i = nextIndex(i, len);
            }
        }

        private boolean cleanSomeSlots(int i, int n) {
            int i2;
            boolean removed = false;
            Entry[] tab = this.table;
            int len = tab.length;
            do {
                i = nextIndex(i, len);
                Entry e = tab[i];
                if (e != null && e.get() == null) {
                    n = len;
                    removed = true;
                    i = expungeStaleEntry(i);
                }
                i2 = n >>> 1;
                n = i2;
            } while (i2 != 0);
            return removed;
        }

        private void rehash() {
            expungeStaleEntries();
            int i = this.size;
            int i2 = this.threshold;
            if (i >= i2 - (i2 / 4)) {
                resize();
            }
        }

        private void resize() {
            Entry[] oldTab = this.table;
            int oldLen = oldTab.length;
            int newLen = oldLen * 2;
            Entry[] newTab = new Entry[newLen];
            int count = 0;
            for (Entry e : oldTab) {
                if (e != null) {
                    ThreadLocal<?> k = (ThreadLocal) e.get();
                    if (k == null) {
                        e.value = null;
                    } else {
                        int h = ((ThreadLocal) k).threadLocalHashCode & (newLen - 1);
                        while (newTab[h] != null) {
                            h = nextIndex(h, newLen);
                        }
                        newTab[h] = e;
                        count++;
                    }
                }
            }
            setThreshold(newLen);
            this.size = count;
            this.table = newTab;
        }

        private void expungeStaleEntries() {
            Entry[] tab = this.table;
            int len = tab.length;
            for (int j = 0; j < len; j++) {
                Entry e = tab[j];
                if (e != null && e.get() == null) {
                    expungeStaleEntry(j);
                }
            }
        }
    }
}
