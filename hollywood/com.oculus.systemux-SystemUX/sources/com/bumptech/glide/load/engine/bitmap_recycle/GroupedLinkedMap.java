package com.bumptech.glide.load.engine.bitmap_recycle;

import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public class GroupedLinkedMap<K extends Poolable, V> {
    private final LinkedEntry<K, V> head = new LinkedEntry<>();
    private final Map<K, LinkedEntry<K, V>> keyToEntry = new HashMap();

    GroupedLinkedMap() {
    }

    public void put(K k, V v) {
        LinkedEntry<K, V> linkedEntry = this.keyToEntry.get(k);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry<>(k);
            makeTail(linkedEntry);
            this.keyToEntry.put(k, linkedEntry);
        } else {
            k.offer();
        }
        linkedEntry.add(v);
    }

    @Nullable
    public V get(K k) {
        LinkedEntry<K, V> linkedEntry = this.keyToEntry.get(k);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry<>(k);
            this.keyToEntry.put(k, linkedEntry);
        } else {
            k.offer();
        }
        makeHead(linkedEntry);
        return linkedEntry.removeLast();
    }

    @Nullable
    public V removeLast() {
        for (LinkedEntry<K, V> linkedEntry = this.head.prev; !linkedEntry.equals(this.head); linkedEntry = linkedEntry.prev) {
            V removeLast = linkedEntry.removeLast();
            if (removeLast != null) {
                return removeLast;
            }
            removeEntry(linkedEntry);
            this.keyToEntry.remove(linkedEntry.key);
            linkedEntry.key.offer();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z = false;
        for (LinkedEntry<K, V> linkedEntry = this.head.next; !linkedEntry.equals(this.head); linkedEntry = linkedEntry.next) {
            z = true;
            sb.append('{');
            sb.append((Object) linkedEntry.key);
            sb.append(':');
            sb.append(linkedEntry.size());
            sb.append("}, ");
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }

    private void makeHead(LinkedEntry<K, V> linkedEntry) {
        removeEntry(linkedEntry);
        LinkedEntry<K, V> linkedEntry2 = this.head;
        linkedEntry.prev = linkedEntry2;
        linkedEntry.next = linkedEntry2.next;
        updateEntry(linkedEntry);
    }

    private void makeTail(LinkedEntry<K, V> linkedEntry) {
        removeEntry(linkedEntry);
        linkedEntry.prev = this.head.prev;
        linkedEntry.next = this.head;
        updateEntry(linkedEntry);
    }

    private static <K, V> void updateEntry(LinkedEntry<K, V> linkedEntry) {
        linkedEntry.next.prev = linkedEntry;
        linkedEntry.prev.next = linkedEntry;
    }

    private static <K, V> void removeEntry(LinkedEntry<K, V> linkedEntry) {
        linkedEntry.prev.next = linkedEntry.next;
        linkedEntry.next.prev = linkedEntry.prev;
    }

    /* access modifiers changed from: private */
    public static class LinkedEntry<K, V> {
        final K key;
        LinkedEntry<K, V> next;
        LinkedEntry<K, V> prev;
        private List<V> values;

        LinkedEntry() {
            this(null);
        }

        LinkedEntry(K k) {
            this.prev = this;
            this.next = this;
            this.key = k;
        }

        @Nullable
        public V removeLast() {
            int size = size();
            if (size > 0) {
                return this.values.remove(size - 1);
            }
            return null;
        }

        public int size() {
            List<V> list = this.values;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public void add(V v) {
            if (this.values == null) {
                this.values = new ArrayList();
            }
            this.values.add(v);
        }
    }
}
