package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Table;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

/* access modifiers changed from: package-private */
@GwtCompatible
@Immutable
public final class SparseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    private final ImmutableMap<C, Map<R, V>> columnMap;
    private final int[] iterationOrderColumn;
    private final int[] iterationOrderRow;
    private final ImmutableMap<R, Map<C, V>> rowMap;

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.HashMap */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.LinkedHashMap */
    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.util.LinkedHashMap */
    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX DEBUG: Multi-variable search result rejected for r6v5, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    SparseImmutableTable(ImmutableList<Table.Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
        Map<R, Integer> rowIndex = Maps.newHashMap();
        Map<R, Map<C, V>> rows = Maps.newLinkedHashMap();
        UnmodifiableIterator<R> it = rowSpace.iterator();
        while (it.hasNext()) {
            R row = it.next();
            rowIndex.put(row, Integer.valueOf(rows.size()));
            rows.put(row, new LinkedHashMap<>());
        }
        Map<C, Map<R, V>> columns = Maps.newLinkedHashMap();
        UnmodifiableIterator<C> it2 = columnSpace.iterator();
        while (it2.hasNext()) {
            columns.put(it2.next(), new LinkedHashMap<>());
        }
        int[] iterationOrderRow2 = new int[cellList.size()];
        int[] iterationOrderColumn2 = new int[cellList.size()];
        for (int i = 0; i < cellList.size(); i++) {
            Table.Cell<R, C, V> cell = cellList.get(i);
            R rowKey = cell.getRowKey();
            C columnKey = cell.getColumnKey();
            V value = cell.getValue();
            iterationOrderRow2[i] = rowIndex.get(rowKey).intValue();
            Map<C, V> thisRow = rows.get(rowKey);
            iterationOrderColumn2[i] = thisRow.size();
            V oldValue = thisRow.put(columnKey, value);
            if (oldValue == null) {
                columns.get(columnKey).put(rowKey, value);
            } else {
                throw new IllegalArgumentException("Duplicate value for row=" + ((Object) rowKey) + ", column=" + ((Object) columnKey) + ": " + ((Object) value) + ", " + ((Object) oldValue));
            }
        }
        this.iterationOrderRow = iterationOrderRow2;
        this.iterationOrderColumn = iterationOrderColumn2;
        ImmutableMap.Builder<R, Map<C, V>> rowBuilder = ImmutableMap.builder();
        for (Map.Entry<R, Map<C, V>> row2 : rows.entrySet()) {
            rowBuilder.put(row2.getKey(), ImmutableMap.copyOf(row2.getValue()));
        }
        this.rowMap = rowBuilder.build();
        ImmutableMap.Builder<C, Map<R, V>> columnBuilder = ImmutableMap.builder();
        for (Map.Entry<C, Map<R, V>> col : columns.entrySet()) {
            columnBuilder.put(col.getKey(), ImmutableMap.copyOf(col.getValue()));
        }
        this.columnMap = columnBuilder.build();
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return this.columnMap;
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return this.rowMap;
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.iterationOrderRow.length;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.RegularImmutableTable
    public Table.Cell<R, C, V> getCell(int index) {
        Map.Entry<R, Map<C, V>> rowEntry = this.rowMap.entrySet().asList().get(this.iterationOrderRow[index]);
        Map.Entry<C, V> colEntry = ((ImmutableMap) rowEntry.getValue()).entrySet().asList().get(this.iterationOrderColumn[index]);
        return cellOf(rowEntry.getKey(), colEntry.getKey(), colEntry.getValue());
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.RegularImmutableTable
    public V getValue(int index) {
        int rowIndex = this.iterationOrderRow[index];
        return ((ImmutableMap) this.rowMap.values().asList().get(rowIndex)).values().asList().get(this.iterationOrderColumn[index]);
    }
}
