package java.lang;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocal {
    private static AtomicInteger nextHashCode = new AtomicInteger();
    private final int threadLocalHashCode = nextHashCode();

    /* access modifiers changed from: protected */
    public Object initialValue() {
        return null;
    }

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(1640531527);
    }

    public Object get() {
        ThreadLocalMap.Entry entry;
        ThreadLocalMap map = getMap(Thread.currentThread());
        if (map == null || (entry = map.getEntry(this)) == null) {
            return setInitialValue();
        }
        return entry.value;
    }

    private Object setInitialValue() {
        Object initialValue = initialValue();
        Thread currentThread = Thread.currentThread();
        ThreadLocalMap map = getMap(currentThread);
        if (map != null) {
            map.set(this, initialValue);
        } else {
            createMap(currentThread, initialValue);
        }
        return initialValue;
    }

    public void set(Object obj) {
        Thread currentThread = Thread.currentThread();
        ThreadLocalMap map = getMap(currentThread);
        if (map != null) {
            map.set(this, obj);
        } else {
            createMap(currentThread, obj);
        }
    }

    public void remove() {
        ThreadLocalMap map = getMap(Thread.currentThread());
        if (map != null) {
            map.remove(this);
        }
    }

    /* access modifiers changed from: package-private */
    public ThreadLocalMap getMap(Thread thread) {
        return thread.threadLocals;
    }

    /* access modifiers changed from: package-private */
    public void createMap(Thread thread, Object obj) {
        thread.threadLocals = new ThreadLocalMap(this, obj);
    }

    static ThreadLocalMap createInheritedMap(ThreadLocalMap threadLocalMap) {
        return new ThreadLocalMap(threadLocalMap);
    }

    /* access modifiers changed from: package-private */
    public Object childValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public static class ThreadLocalMap {
        private int size;
        private Entry[] table;
        private int threshold;

        private static int nextIndex(int i, int i2) {
            int i3 = i + 1;
            if (i3 < i2) {
                return i3;
            }
            return 0;
        }

        private static int prevIndex(int i, int i2) {
            int i3 = i - 1;
            return i3 >= 0 ? i3 : i2 - 1;
        }

        /* access modifiers changed from: package-private */
        public static class Entry extends WeakReference {
            Object value;

            Entry(ThreadLocal threadLocal, Object obj) {
                super(threadLocal);
                this.value = obj;
            }
        }

        private void setThreshold(int i) {
            this.threshold = (i * 2) / 3;
        }

        ThreadLocalMap(ThreadLocal threadLocal, Object obj) {
            this.size = 0;
            this.table = new Entry[16];
            this.table[threadLocal.threadLocalHashCode & 15] = new Entry(threadLocal, obj);
            this.size = 1;
            setThreshold(16);
        }

        private ThreadLocalMap(ThreadLocalMap threadLocalMap) {
            ThreadLocal threadLocal;
            Entry[] entryArr;
            this.size = 0;
            Entry[] entryArr2 = threadLocalMap.table;
            int length = entryArr2.length;
            setThreshold(length);
            this.table = new Entry[length];
            for (Entry entry : entryArr2) {
                if (!(entry == null || (threadLocal = (ThreadLocal) entry.get()) == null)) {
                    Entry entry2 = new Entry(threadLocal, threadLocal.childValue(entry.value));
                    int i = threadLocal.threadLocalHashCode & (length - 1);
                    while (true) {
                        entryArr = this.table;
                        if (entryArr[i] == null) {
                            break;
                        }
                        i = nextIndex(i, length);
                    }
                    entryArr[i] = entry2;
                    this.size++;
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Entry getEntry(ThreadLocal threadLocal) {
            int i = threadLocal.threadLocalHashCode;
            Entry[] entryArr = this.table;
            int length = i & (entryArr.length - 1);
            Entry entry = entryArr[length];
            if (entry == null || entry.get() != threadLocal) {
                return getEntryAfterMiss(threadLocal, length, entry);
            }
            return entry;
        }

        private Entry getEntryAfterMiss(ThreadLocal threadLocal, int i, Entry entry) {
            Entry[] entryArr = this.table;
            int length = entryArr.length;
            while (entry != null) {
                ThreadLocal threadLocal2 = (ThreadLocal) entry.get();
                if (threadLocal2 == threadLocal) {
                    return entry;
                }
                if (threadLocal2 == null) {
                    expungeStaleEntry(i);
                } else {
                    i = nextIndex(i, length);
                }
                entry = entryArr[i];
            }
            return null;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void set(ThreadLocal threadLocal, Object obj) {
            Entry[] entryArr = this.table;
            int length = entryArr.length;
            int i = threadLocal.threadLocalHashCode & (length - 1);
            Entry entry = entryArr[i];
            while (entry != null) {
                ThreadLocal threadLocal2 = (ThreadLocal) entry.get();
                if (threadLocal2 == threadLocal) {
                    entry.value = obj;
                    return;
                } else if (threadLocal2 == null) {
                    replaceStaleEntry(threadLocal, obj, i);
                    return;
                } else {
                    i = nextIndex(i, length);
                    entry = entryArr[i];
                }
            }
            entryArr[i] = new Entry(threadLocal, obj);
            int i2 = this.size + 1;
            this.size = i2;
            if (!cleanSomeSlots(i, i2) && i2 >= this.threshold) {
                rehash();
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void remove(ThreadLocal threadLocal) {
            Entry[] entryArr = this.table;
            int length = entryArr.length;
            int i = threadLocal.threadLocalHashCode & (length - 1);
            Entry entry = entryArr[i];
            while (entry != null) {
                if (entry.get() == threadLocal) {
                    entry.clear();
                    expungeStaleEntry(i);
                    return;
                }
                i = nextIndex(i, length);
                entry = entryArr[i];
            }
        }

        private void replaceStaleEntry(ThreadLocal threadLocal, Object obj, int i) {
            Entry[] entryArr = this.table;
            int length = entryArr.length;
            int prevIndex = prevIndex(i, length);
            int i2 = i;
            while (true) {
                Entry entry = entryArr[prevIndex];
                if (entry == null) {
                    break;
                }
                if (entry.get() == null) {
                    i2 = prevIndex;
                }
                prevIndex = prevIndex(prevIndex, length);
            }
            int nextIndex = nextIndex(i, length);
            while (true) {
                Entry entry2 = entryArr[nextIndex];
                if (entry2 != null) {
                    ThreadLocal threadLocal2 = (ThreadLocal) entry2.get();
                    if (threadLocal2 == threadLocal) {
                        entry2.value = obj;
                        entryArr[nextIndex] = entryArr[i];
                        entryArr[i] = entry2;
                        if (i2 != i) {
                            nextIndex = i2;
                        }
                        cleanSomeSlots(expungeStaleEntry(nextIndex), length);
                        return;
                    }
                    if (threadLocal2 == null && i2 == i) {
                        i2 = nextIndex;
                    }
                    nextIndex = nextIndex(nextIndex, length);
                } else {
                    entryArr[i].value = null;
                    entryArr[i] = new Entry(threadLocal, obj);
                    if (i2 != i) {
                        cleanSomeSlots(expungeStaleEntry(i2), length);
                        return;
                    }
                    return;
                }
            }
        }

        private int expungeStaleEntry(int i) {
            Entry[] entryArr = this.table;
            int length = entryArr.length;
            entryArr[i].value = null;
            entryArr[i] = null;
            this.size--;
            int nextIndex = nextIndex(i, length);
            while (true) {
                Entry entry = entryArr[nextIndex];
                if (entry == null) {
                    return nextIndex;
                }
                ThreadLocal threadLocal = (ThreadLocal) entry.get();
                if (threadLocal == null) {
                    entry.value = null;
                    entryArr[nextIndex] = null;
                    this.size--;
                } else {
                    int i2 = threadLocal.threadLocalHashCode & (length - 1);
                    if (i2 != nextIndex) {
                        entryArr[nextIndex] = null;
                        while (entryArr[i2] != null) {
                            i2 = nextIndex(i2, length);
                        }
                        entryArr[i2] = entry;
                    }
                }
                nextIndex = nextIndex(nextIndex, length);
            }
        }

        private boolean cleanSomeSlots(int i, int i2) {
            Entry[] entryArr = this.table;
            int length = entryArr.length;
            boolean z = false;
            do {
                i = nextIndex(i, length);
                Entry entry = entryArr[i];
                if (entry != null && entry.get() == null) {
                    i = expungeStaleEntry(i);
                    i2 = length;
                    z = true;
                }
                i2 >>>= 1;
            } while (i2 != 0);
            return z;
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
            Entry[] entryArr = this.table;
            int length = entryArr.length;
            int i = length * 2;
            Entry[] entryArr2 = new Entry[i];
            int i2 = 0;
            for (Entry entry : entryArr) {
                if (entry != null) {
                    ThreadLocal threadLocal = (ThreadLocal) entry.get();
                    if (threadLocal == null) {
                        entry.value = null;
                    } else {
                        int i3 = threadLocal.threadLocalHashCode & (i - 1);
                        while (entryArr2[i3] != null) {
                            i3 = nextIndex(i3, i);
                        }
                        entryArr2[i3] = entry;
                        i2++;
                    }
                }
            }
            setThreshold(i);
            this.size = i2;
            this.table = entryArr2;
        }

        private void expungeStaleEntries() {
            Entry[] entryArr = this.table;
            int length = entryArr.length;
            for (int i = 0; i < length; i++) {
                Entry entry = entryArr[i];
                if (entry != null && entry.get() == null) {
                    expungeStaleEntry(i);
                }
            }
        }
    }
}
