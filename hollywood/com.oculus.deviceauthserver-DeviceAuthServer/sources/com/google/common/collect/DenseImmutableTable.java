package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Table;
import java.lang.reflect.Array;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* access modifiers changed from: package-private */
@GwtCompatible
@Immutable
public final class DenseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    private final int[] columnCounts = new int[this.columnKeyToIndex.size()];
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableMap<C, Map<R, V>> columnMap;
    private final int[] iterationOrderColumn;
    private final int[] iterationOrderRow;
    private final int[] rowCounts = new int[this.rowKeyToIndex.size()];
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableMap<R, Map<C, V>> rowMap;
    private final V[][] values;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    private static <E> ImmutableMap<E, Integer> makeIndex(ImmutableSet<E> set) {
        ImmutableMap.Builder<E, Integer> indexBuilder = ImmutableMap.builder();
        int i = 0;
        UnmodifiableIterator<E> it = set.iterator();
        while (it.hasNext()) {
            indexBuilder.put(it.next(), Integer.valueOf(i));
            i++;
        }
        return indexBuilder.build();
    }

    DenseImmutableTable(ImmutableList<Table.Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
        this.values = (V[][]) ((Object[][]) Array.newInstance(Object.class, rowSpace.size(), columnSpace.size()));
        this.rowKeyToIndex = makeIndex(rowSpace);
        this.columnKeyToIndex = makeIndex(columnSpace);
        int[] iterationOrderRow2 = new int[cellList.size()];
        int[] iterationOrderColumn2 = new int[cellList.size()];
        for (int i = 0; i < cellList.size(); i++) {
            Table.Cell<R, C, V> cell = cellList.get(i);
            R rowKey = cell.getRowKey();
            C columnKey = cell.getColumnKey();
            int rowIndex = this.rowKeyToIndex.get(rowKey).intValue();
            int columnIndex = this.columnKeyToIndex.get(columnKey).intValue();
            Preconditions.checkArgument(this.values[rowIndex][columnIndex] == null, "duplicate key: (%s, %s)", rowKey, columnKey);
            this.values[rowIndex][columnIndex] = cell.getValue();
            int[] iArr = this.rowCounts;
            iArr[rowIndex] = iArr[rowIndex] + 1;
            int[] iArr2 = this.columnCounts;
            iArr2[columnIndex] = iArr2[columnIndex] + 1;
            iterationOrderRow2[i] = rowIndex;
            iterationOrderColumn2[i] = columnIndex;
        }
        this.iterationOrderRow = iterationOrderRow2;
        this.iterationOrderColumn = iterationOrderColumn2;
        this.rowMap = new RowMap();
        this.columnMap = new ColumnMap();
    }

    /* access modifiers changed from: private */
    public static abstract class ImmutableArrayMap<K, V> extends ImmutableMap<K, V> {
        private final int size;

        /* access modifiers changed from: package-private */
        @Nullable
        public abstract V getValue(int i);

        /* access modifiers changed from: package-private */
        public abstract ImmutableMap<K, Integer> keyToIndex();

        ImmutableArrayMap(int size2) {
            this.size = size2;
        }

        private boolean isFull() {
            return this.size == keyToIndex().size();
        }

        /* access modifiers changed from: package-private */
        public K getKey(int index) {
            return keyToIndex().keySet().asList().get(index);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return isFull() ? keyToIndex().keySet() : super.createKeySet();
        }

        public int size() {
            return this.size;
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public V get(@Nullable Object key) {
            Integer keyIndex = keyToIndex().get(key);
            if (keyIndex == null) {
                return null;
            }
            return getValue(keyIndex.intValue());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
            return new ImmutableMapEntrySet<K, V>() {
                /* class com.google.common.collect.DenseImmutableTable.ImmutableArrayMap.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.ImmutableMapEntrySet
                public ImmutableMap<K, V> map() {
                    return ImmutableArrayMap.this;
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
                public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                    return new AbstractIterator<Map.Entry<K, V>>() {
                        /* class com.google.common.collect.DenseImmutableTable.ImmutableArrayMap.AnonymousClass1.AnonymousClass1 */
                        private int index = -1;
                        private final int maxIndex = ImmutableArrayMap.this.keyToIndex().size();

                        /* access modifiers changed from: protected */
                        @Override // com.google.common.collect.AbstractIterator
                        public Map.Entry<K, V> computeNext() {
                            int i = this.index;
                            while (true) {
                                this.index = i + 1;
                                if (this.index >= this.maxIndex) {
                                    return (Map.Entry) endOfData();
                                }
                                Object value = ImmutableArrayMap.this.getValue(this.index);
                                if (value != null) {
                                    return Maps.immutableEntry(ImmutableArrayMap.this.getKey(this.index), value);
                                }
                                i = this.index;
                            }
                        }
                    };
                }
            };
        }
    }

    /* access modifiers changed from: private */
    public final class Row extends ImmutableArrayMap<C, V> {
        private final int rowIndex;

        Row(int rowIndex2) {
            super(DenseImmutableTable.this.rowCounts[rowIndex2]);
            this.rowIndex = rowIndex2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public V getValue(int keyIndex) {
            return (V) DenseImmutableTable.this.values[this.rowIndex][keyIndex];
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public final class Column extends ImmutableArrayMap<R, V> {
        private final int columnIndex;

        Column(int columnIndex2) {
            super(DenseImmutableTable.this.columnCounts[columnIndex2]);
            this.columnIndex = columnIndex2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public V getValue(int keyIndex) {
            return (V) DenseImmutableTable.this.values[keyIndex][this.columnIndex];
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return true;
        }
    }

    private final class RowMap extends ImmutableArrayMap<R, Map<C, V>> {
        private RowMap() {
            super(DenseImmutableTable.this.rowCounts.length);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public Map<C, V> getValue(int keyIndex) {
            return new Row(keyIndex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return false;
        }
    }

    private final class ColumnMap extends ImmutableArrayMap<C, Map<R, V>> {
        private ColumnMap() {
            super(DenseImmutableTable.this.columnCounts.length);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public Map<R, V> getValue(int keyIndex) {
            return new Column(keyIndex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return false;
        }
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return this.columnMap;
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return this.rowMap;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        if (rowIndex == null || columnIndex == null) {
            return null;
        }
        return this.values[rowIndex.intValue()][columnIndex.intValue()];
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.iterationOrderRow.length;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.RegularImmutableTable
    public Table.Cell<R, C, V> getCell(int index) {
        int rowIndex = this.iterationOrderRow[index];
        int columnIndex = this.iterationOrderColumn[index];
        return cellOf(rowKeySet().asList().get(rowIndex), columnKeySet().asList().get(columnIndex), this.values[rowIndex][columnIndex]);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.RegularImmutableTable
    public V getValue(int index) {
        return this.values[this.iterationOrderRow[index]][this.iterationOrderColumn[index]];
    }
}
