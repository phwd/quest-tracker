package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Beta
@GwtCompatible(emulated = true)
public final class ArrayTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    private final V[][] array;
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableList<C> columnList;
    private transient ArrayTable<R, C, V>.ColumnMap columnMap;
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableList<R> rowList;
    private transient ArrayTable<R, C, V>.RowMap rowMap;

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.AbstractTable
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Iterable<? extends R> rowKeys, Iterable<? extends C> columnKeys) {
        return new ArrayTable<>(rowKeys, columnKeys);
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Table<R, C, V> table) {
        if (table instanceof ArrayTable) {
            return new ArrayTable<>((ArrayTable) table);
        }
        return new ArrayTable<>(table);
    }

    private ArrayTable(Iterable<? extends R> rowKeys, Iterable<? extends C> columnKeys) {
        this.rowList = ImmutableList.copyOf(rowKeys);
        this.columnList = ImmutableList.copyOf(columnKeys);
        Preconditions.checkArgument(!this.rowList.isEmpty());
        Preconditions.checkArgument(!this.columnList.isEmpty());
        this.rowKeyToIndex = index(this.rowList);
        this.columnKeyToIndex = index(this.columnList);
        this.array = (V[][]) ((Object[][]) Array.newInstance(Object.class, this.rowList.size(), this.columnList.size()));
        eraseAll();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    private static <E> ImmutableMap<E, Integer> index(List<E> list) {
        ImmutableMap.Builder<E, Integer> columnBuilder = ImmutableMap.builder();
        for (int i = 0; i < list.size(); i++) {
            columnBuilder.put(list.get(i), Integer.valueOf(i));
        }
        return columnBuilder.build();
    }

    private ArrayTable(Table<R, C, V> table) {
        this(table.rowKeySet(), table.columnKeySet());
        putAll(table);
    }

    private ArrayTable(ArrayTable<R, C, V> table) {
        this.rowList = table.rowList;
        this.columnList = table.columnList;
        this.rowKeyToIndex = table.rowKeyToIndex;
        this.columnKeyToIndex = table.columnKeyToIndex;
        V[][] copy = (V[][]) ((Object[][]) Array.newInstance(Object.class, this.rowList.size(), this.columnList.size()));
        this.array = copy;
        eraseAll();
        for (int i = 0; i < this.rowList.size(); i++) {
            V[][] vArr = table.array;
            System.arraycopy(vArr[i], 0, copy[i], 0, vArr[i].length);
        }
    }

    private static abstract class ArrayMap<K, V> extends Maps.ImprovedAbstractMap<K, V> {
        private final ImmutableMap<K, Integer> keyIndex;

        /* access modifiers changed from: package-private */
        public abstract String getKeyRole();

        /* access modifiers changed from: package-private */
        @Nullable
        public abstract V getValue(int i);

        /* access modifiers changed from: package-private */
        @Nullable
        public abstract V setValue(int i, V v);

        private ArrayMap(ImmutableMap<K, Integer> keyIndex2) {
            this.keyIndex = keyIndex2;
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.Maps.ImprovedAbstractMap
        public Set<K> keySet() {
            return this.keyIndex.keySet();
        }

        /* access modifiers changed from: package-private */
        public K getKey(int index) {
            return this.keyIndex.keySet().asList().get(index);
        }

        public int size() {
            return this.keyIndex.size();
        }

        public boolean isEmpty() {
            return this.keyIndex.isEmpty();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Set<Map.Entry<K, V>> createEntrySet() {
            return new Maps.EntrySet<K, V>() {
                /* class com.google.common.collect.ArrayTable.ArrayMap.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, V> map() {
                    return ArrayMap.this;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                public Iterator<Map.Entry<K, V>> iterator() {
                    return new AbstractIndexedListIterator<Map.Entry<K, V>>(size()) {
                        /* class com.google.common.collect.ArrayTable.ArrayMap.AnonymousClass1.AnonymousClass1 */

                        /* access modifiers changed from: protected */
                        @Override // com.google.common.collect.AbstractIndexedListIterator
                        public Map.Entry<K, V> get(final int index) {
                            return new AbstractMapEntry<K, V>() {
                                /* class com.google.common.collect.ArrayTable.ArrayMap.AnonymousClass1.AnonymousClass1.AnonymousClass1 */

                                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                                public K getKey() {
                                    return (K) ArrayMap.this.getKey(index);
                                }

                                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                                public V getValue() {
                                    return (V) ArrayMap.this.getValue(index);
                                }

                                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                                public V setValue(V value) {
                                    return (V) ArrayMap.this.setValue(index, value);
                                }
                            };
                        }
                    };
                }
            };
        }

        public boolean containsKey(@Nullable Object key) {
            return this.keyIndex.containsKey(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(@Nullable Object key) {
            Integer index = this.keyIndex.get(key);
            if (index == null) {
                return null;
            }
            return getValue(index.intValue());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K key, V value) {
            Integer index = this.keyIndex.get(key);
            if (index != null) {
                return setValue(index.intValue(), value);
            }
            throw new IllegalArgumentException(getKeyRole() + " " + ((Object) key) + " not in " + this.keyIndex.keySet());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object key) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }
    }

    public ImmutableList<R> rowKeyList() {
        return this.rowList;
    }

    public ImmutableList<C> columnKeyList() {
        return this.columnList;
    }

    public V at(int rowIndex, int columnIndex) {
        Preconditions.checkElementIndex(rowIndex, this.rowList.size());
        Preconditions.checkElementIndex(columnIndex, this.columnList.size());
        return this.array[rowIndex][columnIndex];
    }

    public V set(int rowIndex, int columnIndex, @Nullable V value) {
        Preconditions.checkElementIndex(rowIndex, this.rowList.size());
        Preconditions.checkElementIndex(columnIndex, this.columnList.size());
        V[][] vArr = this.array;
        V oldValue = vArr[rowIndex][columnIndex];
        vArr[rowIndex][columnIndex] = value;
        return oldValue;
    }

    @GwtIncompatible("reflection")
    public V[][] toArray(Class<V> valueClass) {
        V[][] copy = (V[][]) ((Object[][]) Array.newInstance((Class<?>) valueClass, this.rowList.size(), this.columnList.size()));
        for (int i = 0; i < this.rowList.size(); i++) {
            V[][] vArr = this.array;
            System.arraycopy(vArr[i], 0, copy[i], 0, vArr[i].length);
        }
        return copy;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public void eraseAll() {
        for (V[] row : this.array) {
            Arrays.fill(row, (Object) null);
        }
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
        return containsRow(rowKey) && containsColumn(columnKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsColumn(@Nullable Object columnKey) {
        return this.columnKeyToIndex.containsKey(columnKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsRow(@Nullable Object rowKey) {
        return this.rowKeyToIndex.containsKey(rowKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsValue(@Nullable Object value) {
        V[][] vArr = this.array;
        for (V[] row : vArr) {
            for (V element : row) {
                if (Objects.equal(value, element)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        if (rowIndex == null || columnIndex == null) {
            return null;
        }
        return at(rowIndex.intValue(), columnIndex.intValue());
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean isEmpty() {
        return false;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public V put(R rowKey, C columnKey, @Nullable V value) {
        Preconditions.checkNotNull(rowKey);
        Preconditions.checkNotNull(columnKey);
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        Preconditions.checkArgument(rowIndex != null, "Row %s not in %s", rowKey, this.rowList);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        Preconditions.checkArgument(columnIndex != null, "Column %s not in %s", columnKey, this.columnList);
        return set(rowIndex.intValue(), columnIndex.intValue(), value);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        super.putAll(table);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @Deprecated
    public V remove(Object rowKey, Object columnKey) {
        throw new UnsupportedOperationException();
    }

    public V erase(@Nullable Object rowKey, @Nullable Object columnKey) {
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        if (rowIndex == null || columnIndex == null) {
            return null;
        }
        return set(rowIndex.intValue(), columnIndex.intValue(), null);
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.rowList.size() * this.columnList.size();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractTable
    public Iterator<Table.Cell<R, C, V>> cellIterator() {
        return new AbstractIndexedListIterator<Table.Cell<R, C, V>>(size()) {
            /* class com.google.common.collect.ArrayTable.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIndexedListIterator
            public Table.Cell<R, C, V> get(final int index) {
                return new Tables.AbstractCell<R, C, V>() {
                    /* class com.google.common.collect.ArrayTable.AnonymousClass1.AnonymousClass1 */
                    final int columnIndex = (index % ArrayTable.this.columnList.size());
                    final int rowIndex = (index / ArrayTable.this.columnList.size());

                    @Override // com.google.common.collect.Table.Cell
                    public R getRowKey() {
                        return (R) ArrayTable.this.rowList.get(this.rowIndex);
                    }

                    @Override // com.google.common.collect.Table.Cell
                    public C getColumnKey() {
                        return (C) ArrayTable.this.columnList.get(this.columnIndex);
                    }

                    @Override // com.google.common.collect.Table.Cell
                    public V getValue() {
                        return (V) ArrayTable.this.at(this.rowIndex, this.columnIndex);
                    }
                };
            }
        };
    }

    @Override // com.google.common.collect.Table
    public Map<R, V> column(C columnKey) {
        Preconditions.checkNotNull(columnKey);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        return columnIndex == null ? ImmutableMap.of() : new Column(columnIndex.intValue());
    }

    /* access modifiers changed from: private */
    public class Column extends ArrayMap<R, V> {
        final int columnIndex;

        Column(int columnIndex2) {
            super(ArrayTable.this.rowKeyToIndex);
            this.columnIndex = columnIndex2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public String getKeyRole() {
            return "Row";
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public V getValue(int index) {
            return (V) ArrayTable.this.at(index, this.columnIndex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public V setValue(int index, V newValue) {
            return (V) ArrayTable.this.set(index, this.columnIndex, newValue);
        }
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public ImmutableSet<C> columnKeySet() {
        return this.columnKeyToIndex.keySet();
    }

    @Override // com.google.common.collect.Table
    public Map<C, Map<R, V>> columnMap() {
        ArrayTable<R, C, V>.ColumnMap map = this.columnMap;
        if (map != null) {
            return map;
        }
        ArrayTable<R, C, V>.ColumnMap columnMap2 = new ColumnMap();
        this.columnMap = columnMap2;
        return columnMap2;
    }

    private class ColumnMap extends ArrayMap<C, Map<R, V>> {
        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.ArrayTable.ArrayMap
        public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
            return put(obj, (Map) ((Map) obj2));
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public /* bridge */ /* synthetic */ Object setValue(int i, Object obj) {
            return setValue(i, (Map) ((Map) obj));
        }

        private ColumnMap() {
            super(ArrayTable.this.columnKeyToIndex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public String getKeyRole() {
            return "Column";
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public Map<R, V> getValue(int index) {
            return new Column(index);
        }

        /* access modifiers changed from: package-private */
        public Map<R, V> setValue(int index, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }

        public Map<R, V> put(C c, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.common.collect.Table
    public Map<C, V> row(R rowKey) {
        Preconditions.checkNotNull(rowKey);
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        return rowIndex == null ? ImmutableMap.of() : new Row(rowIndex.intValue());
    }

    /* access modifiers changed from: private */
    public class Row extends ArrayMap<C, V> {
        final int rowIndex;

        Row(int rowIndex2) {
            super(ArrayTable.this.columnKeyToIndex);
            this.rowIndex = rowIndex2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public String getKeyRole() {
            return "Column";
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public V getValue(int index) {
            return (V) ArrayTable.this.at(this.rowIndex, index);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public V setValue(int index, V newValue) {
            return (V) ArrayTable.this.set(this.rowIndex, index, newValue);
        }
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public ImmutableSet<R> rowKeySet() {
        return this.rowKeyToIndex.keySet();
    }

    @Override // com.google.common.collect.Table
    public Map<R, Map<C, V>> rowMap() {
        ArrayTable<R, C, V>.RowMap map = this.rowMap;
        if (map != null) {
            return map;
        }
        ArrayTable<R, C, V>.RowMap rowMap2 = new RowMap();
        this.rowMap = rowMap2;
        return rowMap2;
    }

    private class RowMap extends ArrayMap<R, Map<C, V>> {
        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.ArrayTable.ArrayMap
        public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
            return put(obj, (Map) ((Map) obj2));
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public /* bridge */ /* synthetic */ Object setValue(int i, Object obj) {
            return setValue(i, (Map) ((Map) obj));
        }

        private RowMap() {
            super(ArrayTable.this.rowKeyToIndex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public String getKeyRole() {
            return "Row";
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public Map<C, V> getValue(int index) {
            return new Row(index);
        }

        /* access modifiers changed from: package-private */
        public Map<C, V> setValue(int index, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }

        public Map<C, V> put(R r, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Collection<V> values() {
        return super.values();
    }
}
