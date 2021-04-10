package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible
public class StandardTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    @GwtTransient
    final Map<R, Map<C, V>> backingMap;
    private transient Set<C> columnKeySet;
    private transient StandardTable<R, C, V>.ColumnMap columnMap;
    @GwtTransient
    final Supplier<? extends Map<C, V>> factory;
    private transient Map<R, Map<C, V>> rowMap;

    StandardTable(Map<R, Map<C, V>> backingMap2, Supplier<? extends Map<C, V>> factory2) {
        this.backingMap = backingMap2;
        this.factory = factory2;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
        return (rowKey == null || columnKey == null || !super.contains(rowKey, columnKey)) ? false : true;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsColumn(@Nullable Object columnKey) {
        if (columnKey == null) {
            return false;
        }
        for (Map<C, V> map : this.backingMap.values()) {
            if (Maps.safeContainsKey(map, columnKey)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsRow(@Nullable Object rowKey) {
        return rowKey != null && Maps.safeContainsKey(this.backingMap, rowKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsValue(@Nullable Object value) {
        return value != null && super.containsValue(value);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
        if (rowKey == null || columnKey == null) {
            return null;
        }
        return (V) super.get(rowKey, columnKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    @Override // com.google.common.collect.Table
    public int size() {
        int size = 0;
        for (Map<C, V> map : this.backingMap.values()) {
            size += map.size();
        }
        return size;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public void clear() {
        this.backingMap.clear();
    }

    private Map<C, V> getOrCreate(R rowKey) {
        Map<C, V> map = this.backingMap.get(rowKey);
        if (map != null) {
            return map;
        }
        Map<C, V> map2 = (Map) this.factory.get();
        this.backingMap.put(rowKey, map2);
        return map2;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public V put(R rowKey, C columnKey, V value) {
        Preconditions.checkNotNull(rowKey);
        Preconditions.checkNotNull(columnKey);
        Preconditions.checkNotNull(value);
        return getOrCreate(rowKey).put(columnKey, value);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public V remove(@Nullable Object rowKey, @Nullable Object columnKey) {
        Map<C, V> map;
        if (rowKey == null || columnKey == null || (map = (Map) Maps.safeGet(this.backingMap, rowKey)) == null) {
            return null;
        }
        V value = map.remove(columnKey);
        if (map.isEmpty()) {
            this.backingMap.remove(rowKey);
        }
        return value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Map<R, V> removeColumn(Object column) {
        Map<R, V> output = new LinkedHashMap<>();
        Iterator<Map.Entry<R, Map<C, V>>> iterator = this.backingMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<R, Map<C, V>> entry = iterator.next();
            V value = entry.getValue().remove(column);
            if (value != null) {
                output.put(entry.getKey(), value);
                if (entry.getValue().isEmpty()) {
                    iterator.remove();
                }
            }
        }
        return output;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean containsMapping(Object rowKey, Object columnKey, Object value) {
        return value != null && value.equals(get(rowKey, columnKey));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean removeMapping(Object rowKey, Object columnKey, Object value) {
        if (!containsMapping(rowKey, columnKey, value)) {
            return false;
        }
        remove(rowKey, columnKey);
        return true;
    }

    private abstract class TableSet<T> extends Sets.ImprovedAbstractSet<T> {
        private TableSet() {
        }

        public boolean isEmpty() {
            return StandardTable.this.backingMap.isEmpty();
        }

        public void clear() {
            StandardTable.this.backingMap.clear();
        }
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractTable
    public Iterator<Table.Cell<R, C, V>> cellIterator() {
        return new CellIterator();
    }

    private class CellIterator implements Iterator<Table.Cell<R, C, V>> {
        Iterator<Map.Entry<C, V>> columnIterator;
        Map.Entry<R, Map<C, V>> rowEntry;
        final Iterator<Map.Entry<R, Map<C, V>>> rowIterator;

        private CellIterator() {
            this.rowIterator = StandardTable.this.backingMap.entrySet().iterator();
            this.columnIterator = Iterators.emptyModifiableIterator();
        }

        public boolean hasNext() {
            return this.rowIterator.hasNext() || this.columnIterator.hasNext();
        }

        @Override // java.util.Iterator
        public Table.Cell<R, C, V> next() {
            if (!this.columnIterator.hasNext()) {
                this.rowEntry = this.rowIterator.next();
                this.columnIterator = this.rowEntry.getValue().entrySet().iterator();
            }
            Map.Entry<C, V> columnEntry = this.columnIterator.next();
            return Tables.immutableCell(this.rowEntry.getKey(), columnEntry.getKey(), columnEntry.getValue());
        }

        public void remove() {
            this.columnIterator.remove();
            if (this.rowEntry.getValue().isEmpty()) {
                this.rowIterator.remove();
            }
        }
    }

    @Override // com.google.common.collect.Table
    public Map<C, V> row(R rowKey) {
        return new Row(rowKey);
    }

    /* access modifiers changed from: package-private */
    public class Row extends Maps.ImprovedAbstractMap<C, V> {
        Map<C, V> backingRowMap;
        final R rowKey;

        Row(R rowKey2) {
            this.rowKey = (R) Preconditions.checkNotNull(rowKey2);
        }

        /* access modifiers changed from: package-private */
        public Map<C, V> backingRowMap() {
            Map<C, V> map = this.backingRowMap;
            if (map != null && (!map.isEmpty() || !StandardTable.this.backingMap.containsKey(this.rowKey))) {
                return this.backingRowMap;
            }
            Map<C, V> computeBackingRowMap = computeBackingRowMap();
            this.backingRowMap = computeBackingRowMap;
            return computeBackingRowMap;
        }

        /* access modifiers changed from: package-private */
        public Map<C, V> computeBackingRowMap() {
            return StandardTable.this.backingMap.get(this.rowKey);
        }

        /* access modifiers changed from: package-private */
        public void maintainEmptyInvariant() {
            if (backingRowMap() != null && this.backingRowMap.isEmpty()) {
                StandardTable.this.backingMap.remove(this.rowKey);
                this.backingRowMap = null;
            }
        }

        public boolean containsKey(Object key) {
            Map<C, V> backingRowMap2 = backingRowMap();
            return (key == null || backingRowMap2 == null || !Maps.safeContainsKey(backingRowMap2, key)) ? false : true;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            Map<C, V> backingRowMap2 = backingRowMap();
            if (key == null || backingRowMap2 == null) {
                return null;
            }
            return (V) Maps.safeGet(backingRowMap2, key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(C key, V value) {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(value);
            Map<C, V> map = this.backingRowMap;
            return (map == null || map.isEmpty()) ? (V) StandardTable.this.put(this.rowKey, key, value) : this.backingRowMap.put(key, value);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object key) {
            Map<C, V> backingRowMap2 = backingRowMap();
            if (backingRowMap2 == null) {
                return null;
            }
            V result = (V) Maps.safeRemove(backingRowMap2, key);
            maintainEmptyInvariant();
            return result;
        }

        public void clear() {
            Map<C, V> backingRowMap2 = backingRowMap();
            if (backingRowMap2 != null) {
                backingRowMap2.clear();
            }
            maintainEmptyInvariant();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Set<Map.Entry<C, V>> createEntrySet() {
            return new RowEntrySet();
        }

        private final class RowEntrySet extends Maps.EntrySet<C, V> {
            private RowEntrySet() {
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.Maps.EntrySet
            public Map<C, V> map() {
                return Row.this;
            }

            @Override // com.google.common.collect.Maps.EntrySet
            public int size() {
                Map<C, V> map = Row.this.backingRowMap();
                if (map == null) {
                    return 0;
                }
                return map.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<C, V>> iterator() {
                Map<C, V> map = Row.this.backingRowMap();
                if (map == null) {
                    return Iterators.emptyModifiableIterator();
                }
                final Iterator<Map.Entry<C, V>> iterator = map.entrySet().iterator();
                return new Iterator<Map.Entry<C, V>>() {
                    /* class com.google.common.collect.StandardTable.Row.RowEntrySet.AnonymousClass1 */

                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override // java.util.Iterator
                    public Map.Entry<C, V> next() {
                        final Map.Entry<C, V> entry = (Map.Entry) iterator.next();
                        return new ForwardingMapEntry<C, V>() {
                            /* class com.google.common.collect.StandardTable.Row.RowEntrySet.AnonymousClass1.AnonymousClass1 */

                            /* access modifiers changed from: protected */
                            @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingMapEntry
                            public Map.Entry<C, V> delegate() {
                                return entry;
                            }

                            /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.collect.StandardTable$Row$RowEntrySet$1$1 */
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // java.util.Map.Entry, com.google.common.collect.ForwardingMapEntry
                            public V setValue(V value) {
                                return (V) super.setValue(Preconditions.checkNotNull(value));
                            }

                            @Override // com.google.common.collect.ForwardingMapEntry
                            public boolean equals(Object object) {
                                return standardEquals(object);
                            }
                        };
                    }

                    public void remove() {
                        iterator.remove();
                        Row.this.maintainEmptyInvariant();
                    }
                };
            }
        }
    }

    @Override // com.google.common.collect.Table
    public Map<R, V> column(C columnKey) {
        return new Column(columnKey);
    }

    /* access modifiers changed from: private */
    public class Column extends Maps.ImprovedAbstractMap<R, V> {
        final C columnKey;

        Column(C columnKey2) {
            this.columnKey = (C) Preconditions.checkNotNull(columnKey2);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(R key, V value) {
            return (V) StandardTable.this.put(key, this.columnKey, value);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            return (V) StandardTable.this.get(key, this.columnKey);
        }

        public boolean containsKey(Object key) {
            return StandardTable.this.contains(key, this.columnKey);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object key) {
            return (V) StandardTable.this.remove(key, this.columnKey);
        }

        /* access modifiers changed from: package-private */
        public boolean removeFromColumnIf(Predicate<? super Map.Entry<R, V>> predicate) {
            boolean changed = false;
            Iterator<Map.Entry<R, Map<C, V>>> iterator = StandardTable.this.backingMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<R, Map<C, V>> entry = iterator.next();
                Map<C, V> map = entry.getValue();
                V value = map.get(this.columnKey);
                if (value != null && predicate.apply(Maps.immutableEntry(entry.getKey(), value))) {
                    map.remove(this.columnKey);
                    changed = true;
                    if (map.isEmpty()) {
                        iterator.remove();
                    }
                }
            }
            return changed;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Set<Map.Entry<R, V>> createEntrySet() {
            return new EntrySet();
        }

        private class EntrySet extends Sets.ImprovedAbstractSet<Map.Entry<R, V>> {
            private EntrySet() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<R, V>> iterator() {
                return new EntrySetIterator();
            }

            public int size() {
                int size = 0;
                for (Map<C, V> map : StandardTable.this.backingMap.values()) {
                    if (map.containsKey(Column.this.columnKey)) {
                        size++;
                    }
                }
                return size;
            }

            public boolean isEmpty() {
                return !StandardTable.this.containsColumn(Column.this.columnKey);
            }

            public void clear() {
                Column.this.removeFromColumnIf(Predicates.alwaysTrue());
            }

            public boolean contains(Object o) {
                if (!(o instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry<?, ?> entry = (Map.Entry) o;
                return StandardTable.this.containsMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
            }

            public boolean remove(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry<?, ?> entry = (Map.Entry) obj;
                return StandardTable.this.removeMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> c) {
                return Column.this.removeFromColumnIf(Predicates.not(Predicates.in(c)));
            }
        }

        private class EntrySetIterator extends AbstractIterator<Map.Entry<R, V>> {
            final Iterator<Map.Entry<R, Map<C, V>>> iterator;

            private EntrySetIterator() {
                this.iterator = StandardTable.this.backingMap.entrySet().iterator();
            }

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIterator
            public Map.Entry<R, V> computeNext() {
                while (this.iterator.hasNext()) {
                    final Map.Entry<R, Map<C, V>> entry = this.iterator.next();
                    if (entry.getValue().containsKey(Column.this.columnKey)) {
                        return new AbstractMapEntry<R, V>() {
                            /* class com.google.common.collect.StandardTable.Column.EntrySetIterator.AnonymousClass1 */

                            @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                            public R getKey() {
                                return (R) entry.getKey();
                            }

                            @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                            public V getValue() {
                                return (V) ((Map) entry.getValue()).get(Column.this.columnKey);
                            }

                            /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.util.Map */
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                            public V setValue(V value) {
                                return (V) ((Map) entry.getValue()).put(Column.this.columnKey, Preconditions.checkNotNull(value));
                            }
                        };
                    }
                }
                return (Map.Entry) endOfData();
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Set<R> createKeySet() {
            return new KeySet();
        }

        private class KeySet extends Maps.KeySet<R, V> {
            KeySet() {
                super(Column.this);
            }

            @Override // com.google.common.collect.Maps.KeySet
            public boolean contains(Object obj) {
                return StandardTable.this.contains(obj, Column.this.columnKey);
            }

            @Override // com.google.common.collect.Maps.KeySet
            public boolean remove(Object obj) {
                return StandardTable.this.remove(obj, Column.this.columnKey) != null;
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> c) {
                return Column.this.removeFromColumnIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c))));
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Collection<V> createValues() {
            return new Values();
        }

        private class Values extends Maps.Values<R, V> {
            Values() {
                super(Column.this);
            }

            @Override // com.google.common.collect.Maps.Values
            public boolean remove(Object obj) {
                return obj != null && Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.equalTo(obj)));
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
            public boolean removeAll(Collection<?> c) {
                return Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.in(c)));
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
            public boolean retainAll(Collection<?> c) {
                return Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c))));
            }
        }
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Set<R> rowKeySet() {
        return rowMap().keySet();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Set<C> columnKeySet() {
        Set<C> result = this.columnKeySet;
        if (result != null) {
            return result;
        }
        ColumnKeySet columnKeySet2 = new ColumnKeySet();
        this.columnKeySet = columnKeySet2;
        return columnKeySet2;
    }

    /* access modifiers changed from: private */
    public class ColumnKeySet extends StandardTable<R, C, V>.TableSet {
        private ColumnKeySet() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<C> iterator() {
            return StandardTable.this.createColumnKeyIterator();
        }

        public int size() {
            return Iterators.size(iterator());
        }

        public boolean remove(Object obj) {
            if (obj == null) {
                return false;
            }
            boolean changed = false;
            Iterator<Map<C, V>> iterator = StandardTable.this.backingMap.values().iterator();
            while (iterator.hasNext()) {
                Map<C, V> map = iterator.next();
                if (map.keySet().remove(obj)) {
                    changed = true;
                    if (map.isEmpty()) {
                        iterator.remove();
                    }
                }
            }
            return changed;
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean removeAll(Collection<?> c) {
            Preconditions.checkNotNull(c);
            boolean changed = false;
            Iterator<Map<C, V>> iterator = StandardTable.this.backingMap.values().iterator();
            while (iterator.hasNext()) {
                Map<C, V> map = iterator.next();
                if (Iterators.removeAll(map.keySet().iterator(), c)) {
                    changed = true;
                    if (map.isEmpty()) {
                        iterator.remove();
                    }
                }
            }
            return changed;
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            Preconditions.checkNotNull(c);
            boolean changed = false;
            Iterator<Map<C, V>> iterator = StandardTable.this.backingMap.values().iterator();
            while (iterator.hasNext()) {
                Map<C, V> map = iterator.next();
                if (map.keySet().retainAll(c)) {
                    changed = true;
                    if (map.isEmpty()) {
                        iterator.remove();
                    }
                }
            }
            return changed;
        }

        public boolean contains(Object obj) {
            return StandardTable.this.containsColumn(obj);
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<C> createColumnKeyIterator() {
        return new ColumnKeyIterator();
    }

    /* access modifiers changed from: private */
    public class ColumnKeyIterator extends AbstractIterator<C> {
        Iterator<Map.Entry<C, V>> entryIterator;
        final Iterator<Map<C, V>> mapIterator;
        final Map<C, V> seen;

        private ColumnKeyIterator() {
            this.seen = (Map) StandardTable.this.factory.get();
            this.mapIterator = StandardTable.this.backingMap.values().iterator();
            this.entryIterator = Iterators.emptyIterator();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        public C computeNext() {
            while (true) {
                if (this.entryIterator.hasNext()) {
                    Map.Entry<C, V> entry = this.entryIterator.next();
                    if (!this.seen.containsKey(entry.getKey())) {
                        this.seen.put(entry.getKey(), entry.getValue());
                        return entry.getKey();
                    }
                } else if (!this.mapIterator.hasNext()) {
                    return (C) endOfData();
                } else {
                    this.entryIterator = this.mapIterator.next().entrySet().iterator();
                }
            }
        }
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Collection<V> values() {
        return super.values();
    }

    @Override // com.google.common.collect.Table
    public Map<R, Map<C, V>> rowMap() {
        Map<R, Map<C, V>> result = this.rowMap;
        if (result != null) {
            return result;
        }
        Map<R, Map<C, V>> createRowMap = createRowMap();
        this.rowMap = createRowMap;
        return createRowMap;
    }

    /* access modifiers changed from: package-private */
    public Map<R, Map<C, V>> createRowMap() {
        return new RowMap();
    }

    /* access modifiers changed from: package-private */
    public class RowMap extends Maps.ImprovedAbstractMap<R, Map<C, V>> {
        RowMap() {
        }

        public boolean containsKey(Object key) {
            return StandardTable.this.containsRow(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Map<C, V> get(Object key) {
            if (StandardTable.this.containsRow(key)) {
                return StandardTable.this.row(key);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Map<C, V> remove(Object key) {
            if (key == null) {
                return null;
            }
            return StandardTable.this.backingMap.remove(key);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Set<Map.Entry<R, Map<C, V>>> createEntrySet() {
            return new EntrySet();
        }

        /* access modifiers changed from: package-private */
        public class EntrySet extends StandardTable<R, C, V>.TableSet {
            EntrySet() {
                super();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<R, Map<C, V>>> iterator() {
                return Maps.asMapEntryIterator(StandardTable.this.backingMap.keySet(), new Function<R, Map<C, V>>() {
                    /* class com.google.common.collect.StandardTable.RowMap.EntrySet.AnonymousClass1 */

                    @Override // com.google.common.base.Function
                    public Map<C, V> apply(R rowKey) {
                        return StandardTable.this.row(rowKey);
                    }
                });
            }

            public int size() {
                return StandardTable.this.backingMap.size();
            }

            public boolean contains(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry<?, ?> entry = (Map.Entry) obj;
                if (entry.getKey() == null || !(entry.getValue() instanceof Map) || !Collections2.safeContains(StandardTable.this.backingMap.entrySet(), entry)) {
                    return false;
                }
                return true;
            }

            public boolean remove(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry<?, ?> entry = (Map.Entry) obj;
                if (entry.getKey() == null || !(entry.getValue() instanceof Map) || !StandardTable.this.backingMap.entrySet().remove(entry)) {
                    return false;
                }
                return true;
            }
        }
    }

    @Override // com.google.common.collect.Table
    public Map<C, Map<R, V>> columnMap() {
        StandardTable<R, C, V>.ColumnMap result = this.columnMap;
        if (result != null) {
            return result;
        }
        StandardTable<R, C, V>.ColumnMap columnMap2 = new ColumnMap();
        this.columnMap = columnMap2;
        return columnMap2;
    }

    /* access modifiers changed from: private */
    public class ColumnMap extends Maps.ImprovedAbstractMap<C, Map<R, V>> {
        private ColumnMap() {
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Map<R, V> get(Object key) {
            if (StandardTable.this.containsColumn(key)) {
                return StandardTable.this.column(key);
            }
            return null;
        }

        public boolean containsKey(Object key) {
            return StandardTable.this.containsColumn(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Map<R, V> remove(Object key) {
            if (StandardTable.this.containsColumn(key)) {
                return StandardTable.this.removeColumn(key);
            }
            return null;
        }

        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Set<Map.Entry<C, Map<R, V>>> createEntrySet() {
            return new ColumnMapEntrySet();
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.Maps.ImprovedAbstractMap
        public Set<C> keySet() {
            return StandardTable.this.columnKeySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Collection<Map<R, V>> createValues() {
            return new ColumnMapValues();
        }

        /* access modifiers changed from: package-private */
        public class ColumnMapEntrySet extends StandardTable<R, C, V>.TableSet {
            ColumnMapEntrySet() {
                super();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<C, Map<R, V>>> iterator() {
                return Maps.asMapEntryIterator(StandardTable.this.columnKeySet(), new Function<C, Map<R, V>>() {
                    /* class com.google.common.collect.StandardTable.ColumnMap.ColumnMapEntrySet.AnonymousClass1 */

                    @Override // com.google.common.base.Function
                    public Map<R, V> apply(C columnKey) {
                        return StandardTable.this.column(columnKey);
                    }
                });
            }

            public int size() {
                return StandardTable.this.columnKeySet().size();
            }

            public boolean contains(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry<?, ?> entry = (Map.Entry) obj;
                if (!StandardTable.this.containsColumn(entry.getKey())) {
                    return false;
                }
                return ColumnMap.this.get(entry.getKey()).equals(entry.getValue());
            }

            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                StandardTable.this.removeColumn(((Map.Entry) obj).getKey());
                return true;
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.AbstractSet, java.util.Set
            public boolean removeAll(Collection<?> c) {
                Preconditions.checkNotNull(c);
                return Sets.removeAllImpl(this, c.iterator());
            }

            /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: com.google.common.collect.StandardTable */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> c) {
                Preconditions.checkNotNull(c);
                boolean changed = false;
                Iterator it = Lists.newArrayList(StandardTable.this.columnKeySet().iterator()).iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (!c.contains(Maps.immutableEntry(next, StandardTable.this.column(next)))) {
                        StandardTable.this.removeColumn(next);
                        changed = true;
                    }
                }
                return changed;
            }
        }

        private class ColumnMapValues extends Maps.Values<C, Map<R, V>> {
            ColumnMapValues() {
                super(ColumnMap.this);
            }

            @Override // com.google.common.collect.Maps.Values
            public boolean remove(Object obj) {
                for (Map.Entry<C, Map<R, V>> entry : ColumnMap.this.entrySet()) {
                    if (entry.getValue().equals(obj)) {
                        StandardTable.this.removeColumn(entry.getKey());
                        return true;
                    }
                }
                return false;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: com.google.common.collect.StandardTable */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
            public boolean removeAll(Collection<?> c) {
                Preconditions.checkNotNull(c);
                boolean changed = false;
                Iterator it = Lists.newArrayList(StandardTable.this.columnKeySet().iterator()).iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (c.contains(StandardTable.this.column(next))) {
                        StandardTable.this.removeColumn(next);
                        changed = true;
                    }
                }
                return changed;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: com.google.common.collect.StandardTable */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
            public boolean retainAll(Collection<?> c) {
                Preconditions.checkNotNull(c);
                boolean changed = false;
                Iterator it = Lists.newArrayList(StandardTable.this.columnKeySet().iterator()).iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (!c.contains(StandardTable.this.column(next))) {
                        StandardTable.this.removeColumn(next);
                        changed = true;
                    }
                }
                return changed;
            }
        }
    }
}
