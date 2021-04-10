package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nullable;

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
    public final class CellSet extends ImmutableSet<Table.Cell<R, C, V>> {
        private CellSet() {
        }

        public int size() {
            return RegularImmutableTable.this.size();
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public UnmodifiableIterator<Table.Cell<R, C, V>> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public ImmutableList<Table.Cell<R, C, V>> createAsList() {
            return new ImmutableAsList<Table.Cell<R, C, V>>() {
                /* class com.google.common.collect.RegularImmutableTable.CellSet.AnonymousClass1 */

                @Override // java.util.List
                public Table.Cell<R, C, V> get(int index) {
                    return RegularImmutableTable.this.getCell(index);
                }

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.ImmutableAsList
                public ImmutableCollection<Table.Cell<R, C, V>> delegateCollection() {
                    return CellSet.this;
                }
            };
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean contains(@Nullable Object object) {
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

    static <R, C, V> RegularImmutableTable<R, C, V> forCells(List<Table.Cell<R, C, V>> cells, @Nullable final Comparator<? super R> rowComparator, @Nullable final Comparator<? super C> columnComparator) {
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
                    Comparator comparator = rowComparator;
                    if (comparator == null) {
                        rowCompare = 0;
                    } else {
                        rowCompare = comparator.compare(cell1.getRowKey(), cell2.getRowKey());
                    }
                    if (rowCompare != 0) {
                        return rowCompare;
                    }
                    Comparator comparator2 = columnComparator;
                    if (comparator2 == null) {
                        return 0;
                    }
                    return comparator2.compare(cell1.getColumnKey(), cell2.getColumnKey());
                }
            });
        }
        return forCellsInternal(cells, rowComparator, columnComparator);
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forCells(Iterable<Table.Cell<R, C, V>> cells) {
        return forCellsInternal(cells, null, null);
    }

    private static final <R, C, V> RegularImmutableTable<R, C, V> forCellsInternal(Iterable<Table.Cell<R, C, V>> cells, @Nullable Comparator<? super R> rowComparator, @Nullable Comparator<? super C> columnComparator) {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        ImmutableSet.Builder builder2 = ImmutableSet.builder();
        ImmutableList<Table.Cell<R, C, V>> cellList = ImmutableList.copyOf(cells);
        UnmodifiableIterator<Table.Cell<R, C, V>> it = cellList.iterator();
        while (it.hasNext()) {
            Table.Cell<R, C, V> cell = it.next();
            builder.add((Object) cell.getRowKey());
            builder2.add((Object) cell.getColumnKey());
        }
        ImmutableSet<R> rowSpace = builder.build();
        if (rowComparator != null) {
            List<R> rowList = Lists.newArrayList(rowSpace);
            Collections.sort(rowList, rowComparator);
            rowSpace = ImmutableSet.copyOf((Collection) rowList);
        }
        ImmutableSet<C> columnSpace = builder2.build();
        if (columnComparator != null) {
            List<C> columnList = Lists.newArrayList(columnSpace);
            Collections.sort(columnList, columnComparator);
            columnSpace = ImmutableSet.copyOf((Collection) columnList);
        }
        if (((long) cellList.size()) > (((long) rowSpace.size()) * ((long) columnSpace.size())) / 2) {
            return new DenseImmutableTable(cellList, rowSpace, columnSpace);
        }
        return new SparseImmutableTable(cellList, rowSpace, columnSpace);
    }
}
