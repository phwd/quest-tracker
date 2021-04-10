package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible
public abstract class RegularImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {
    /* access modifiers changed from: package-private */
    public abstract Table.Cell<R, C, V> getCell(int i);

    /* access modifiers changed from: package-private */
    public abstract V getValue(int i);

    RegularImmutableTable() {
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.ImmutableTable, com.google.common.collect.ImmutableTable
    public final ImmutableSet<Table.Cell<R, C, V>> createCellSet() {
        return isEmpty() ? ImmutableSet.of() : new CellSet();
    }

    /* access modifiers changed from: private */
    public final class CellSet extends IndexedImmutableSet<Table.Cell<R, C, V>> {
        private CellSet() {
        }

        public int size() {
            return RegularImmutableTable.this.size();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.IndexedImmutableSet
        public Table.Cell<R, C, V> get(int index) {
            return RegularImmutableTable.this.getCell(index);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean contains(@NullableDecl Object object) {
            if (!(object instanceof Table.Cell)) {
                return false;
            }
            Table.Cell<?, ?, ?> cell = (Table.Cell) object;
            Object value = RegularImmutableTable.this.get(cell.getRowKey(), cell.getColumnKey());
            if (value == null || !value.equals(cell.getValue())) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.ImmutableTable, com.google.common.collect.ImmutableTable
    public final ImmutableCollection<V> createValues() {
        return isEmpty() ? ImmutableList.of() : new Values();
    }

    /* access modifiers changed from: private */
    public final class Values extends ImmutableList<V> {
        private Values() {
        }

        public int size() {
            return RegularImmutableTable.this.size();
        }

        @Override // java.util.List
        public V get(int index) {
            return (V) RegularImmutableTable.this.getValue(index);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forCells(List<Table.Cell<R, C, V>> cells, @NullableDecl final Comparator<? super R> rowComparator, @NullableDecl final Comparator<? super C> columnComparator) {
        Preconditions.checkNotNull(cells);
        if (!(rowComparator == null && columnComparator == null)) {
            Collections.sort(cells, new Comparator<Table.Cell<R, C, V>>() {
                /* class com.google.common.collect.RegularImmutableTable.AnonymousClass1 */

                @Override // java.util.Comparator
                public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                    return compare((Table.Cell) ((Table.Cell) obj), (Table.Cell) ((Table.Cell) obj2));
                }

                public int compare(Table.Cell<R, C, V> cell1, Table.Cell<R, C, V> cell2) {
                    int rowCompare;
                    int rowCompare2 = 0;
                    if (rowComparator == null) {
                        rowCompare = 0;
                    } else {
                        rowCompare = rowComparator.compare(cell1.getRowKey(), cell2.getRowKey());
                    }
                    if (rowCompare != 0) {
                        return rowCompare;
                    }
                    if (columnComparator != null) {
                        rowCompare2 = columnComparator.compare(cell1.getColumnKey(), cell2.getColumnKey());
                    }
                    return rowCompare2;
                }
            });
        }
        return forCellsInternal(cells, rowComparator, columnComparator);
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forCells(Iterable<Table.Cell<R, C, V>> cells) {
        return forCellsInternal(cells, null, null);
    }

    private static <R, C, V> RegularImmutableTable<R, C, V> forCellsInternal(Iterable<Table.Cell<R, C, V>> cells, @NullableDecl Comparator<? super R> rowComparator, @NullableDecl Comparator<? super C> columnComparator) {
        ImmutableSet<R> rowSpace;
        ImmutableSet<C> columnSpace;
        Set<R> rowSpaceBuilder = new LinkedHashSet<>();
        Set<C> columnSpaceBuilder = new LinkedHashSet<>();
        ImmutableList<Table.Cell<R, C, V>> cellList = ImmutableList.copyOf(cells);
        for (Table.Cell<R, C, V> cell : cells) {
            rowSpaceBuilder.add(cell.getRowKey());
            columnSpaceBuilder.add(cell.getColumnKey());
        }
        if (rowComparator == null) {
            rowSpace = ImmutableSet.copyOf((Collection) rowSpaceBuilder);
        } else {
            rowSpace = ImmutableSet.copyOf((Collection) ImmutableList.sortedCopyOf(rowComparator, rowSpaceBuilder));
        }
        if (columnComparator == null) {
            columnSpace = ImmutableSet.copyOf((Collection) columnSpaceBuilder);
        } else {
            columnSpace = ImmutableSet.copyOf((Collection) ImmutableList.sortedCopyOf(columnComparator, columnSpaceBuilder));
        }
        return forOrderedComponents(cellList, rowSpace, columnSpace);
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forOrderedComponents(ImmutableList<Table.Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
        if (((long) cellList.size()) > (((long) rowSpace.size()) * ((long) columnSpace.size())) / 2) {
            return new DenseImmutableTable(cellList, rowSpace, columnSpace);
        }
        return new SparseImmutableTable(cellList, rowSpace, columnSpace);
    }
}
