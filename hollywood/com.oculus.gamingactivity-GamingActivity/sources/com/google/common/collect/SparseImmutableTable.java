package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.Immutable;
import java.util.LinkedHashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
@Immutable(containerOf = {"R", "C", "V"})
@GwtCompatible
public final class SparseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    static final ImmutableTable<Object, Object, Object> EMPTY = new SparseImmutableTable(ImmutableList.of(), ImmutableSet.of(), ImmutableSet.of());
    private final int[] cellColumnInRowIndices;
    private final int[] cellRowIndices;
    private final ImmutableMap<C, ImmutableMap<R, V>> columnMap;
    private final ImmutableMap<R, ImmutableMap<C, V>> rowMap;

    /* JADX DEBUG: Multi-variable search result rejected for r18v0, resolved type: java.util.LinkedHashMap */
    /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: java.util.LinkedHashMap */
    /* JADX DEBUG: Multi-variable search result rejected for r15v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    SparseImmutableTable(ImmutableList<Table.Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
        Map<R, Integer> rowIndex = Maps.indexMap(rowSpace);
        Map<R, Map<C, V>> rows = Maps.newLinkedHashMap();
        UnmodifiableIterator<R> it = rowSpace.iterator();
        while (it.hasNext()) {
            rows.put(it.next(), new LinkedHashMap<>());
        }
        Map<C, Map<R, V>> columns = Maps.newLinkedHashMap();
        UnmodifiableIterator<C> it2 = columnSpace.iterator();
        while (it2.hasNext()) {
            columns.put(it2.next(), new LinkedHashMap<>());
        }
        int[] cellRowIndices2 = new int[cellList.size()];
        int[] cellColumnInRowIndices2 = new int[cellList.size()];
        for (int i = 0; i < cellList.size(); i++) {
            Table.Cell<R, C, V> cell = cellList.get(i);
            R rowKey = cell.getRowKey();
            C columnKey = cell.getColumnKey();
            V value = cell.getValue();
            cellRowIndices2[i] = rowIndex.get(rowKey).intValue();
            Map<C, V> thisRow = rows.get(rowKey);
            cellColumnInRowIndices2[i] = thisRow.size();
            V oldValue = thisRow.put(columnKey, value);
            if (oldValue != null) {
                throw new IllegalArgumentException("Duplicate value for row=" + ((Object) rowKey) + ", column=" + ((Object) columnKey) + ": " + ((Object) value) + ", " + ((Object) oldValue));
            }
            columns.get(columnKey).put(rowKey, value);
        }
        this.cellRowIndices = cellRowIndices2;
        this.cellColumnInRowIndices = cellColumnInRowIndices2;
        ImmutableMap.Builder<R, ImmutableMap<C, V>> rowBuilder = new ImmutableMap.Builder<>(rows.size());
        for (Map.Entry<R, Map<C, V>> row : rows.entrySet()) {
            rowBuilder.put(row.getKey(), ImmutableMap.copyOf(row.getValue()));
        }
        this.rowMap = rowBuilder.build();
        ImmutableMap.Builder<C, ImmutableMap<R, V>> columnBuilder = new ImmutableMap.Builder<>(columns.size());
        for (Map.Entry<C, Map<R, V>> col : columns.entrySet()) {
            columnBuilder.put(col.getKey(), ImmutableMap.copyOf(col.getValue()));
        }
        this.columnMap = columnBuilder.build();
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.copyOf(this.columnMap);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.copyOf(this.rowMap);
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.cellRowIndices.length;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.RegularImmutableTable
    public Table.Cell<R, C, V> getCell(int index) {
        Map.Entry<R, ImmutableMap<C, V>> rowEntry = this.rowMap.entrySet().asList().get(this.cellRowIndices[index]);
        Map.Entry<C, V> colEntry = rowEntry.getValue().entrySet().asList().get(this.cellColumnInRowIndices[index]);
        return cellOf(rowEntry.getKey(), colEntry.getKey(), colEntry.getValue());
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.RegularImmutableTable
    public V getValue(int index) {
        int rowIndex = this.cellRowIndices[index];
        return this.rowMap.values().asList().get(rowIndex).values().asList().get(this.cellColumnInRowIndices[index]);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableTable
    public ImmutableTable.SerializedForm createSerializedForm() {
        Map<C, Integer> columnKeyToIndex = Maps.indexMap(columnKeySet());
        int[] cellColumnIndices = new int[cellSet().size()];
        int i = 0;
        UnmodifiableIterator<Table.Cell<R, C, V>> it = cellSet().iterator();
        while (it.hasNext()) {
            cellColumnIndices[i] = columnKeyToIndex.get(it.next().getColumnKey()).intValue();
            i++;
        }
        return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, cellColumnIndices);
    }
}
