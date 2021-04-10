package libcore.util;

import java.lang.ref.Reference;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class CollectionUtils {
    private CollectionUtils() {
    }

    public static <T> Iterable<T> dereferenceIterable(final Iterable<? extends Reference<T>> iterable, final boolean trim) {
        return new Iterable<T>() {
            /* class libcore.util.CollectionUtils.AnonymousClass1 */

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    /* class libcore.util.CollectionUtils.AnonymousClass1.AnonymousClass1 */
                    private final Iterator<? extends Reference<T>> delegate = Iterable.this.iterator();
                    private T next;
                    private boolean removeIsOkay;

                    private void computeNext() {
                        this.removeIsOkay = false;
                        while (this.next == null && this.delegate.hasNext()) {
                            this.next = (T) ((Reference) this.delegate.next()).get();
                            if (trim && this.next == null) {
                                this.delegate.remove();
                            }
                        }
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        computeNext();
                        return this.next != null;
                    }

                    @Override // java.util.Iterator
                    public T next() {
                        if (hasNext()) {
                            T result = this.next;
                            this.removeIsOkay = true;
                            this.next = null;
                            return result;
                        }
                        throw new IllegalStateException();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        if (this.removeIsOkay) {
                            this.delegate.remove();
                            return;
                        }
                        throw new IllegalStateException();
                    }
                };
            }
        };
    }

    public static <T> void removeDuplicates(List<T> list, Comparator<? super T> comparator) {
        Collections.sort(list, comparator);
        int j = 1;
        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(list.get(j - 1), list.get(i)) != 0) {
                list.set(j, list.get(i));
                j++;
            }
        }
        if (j < list.size()) {
            list.subList(j, list.size()).clear();
        }
    }
}
