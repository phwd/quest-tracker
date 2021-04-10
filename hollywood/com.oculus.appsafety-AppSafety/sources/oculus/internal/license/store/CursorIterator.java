package oculus.internal.license.store;

import android.database.Cursor;
import java.io.Closeable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class CursorIterator<T> implements Iterator, Closeable {
    Cursor cursor;
    Function<Cursor, ? extends T> f;

    public CursorIterator(Cursor cursor2, Function<Cursor, ? extends T> f2) {
        this.cursor = cursor2;
        this.f = f2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return !this.cursor.isAfterLast() && !this.cursor.isLast();
    }

    @Override // java.util.Iterator
    public T next() {
        if (this.cursor.moveToNext()) {
            return (T) this.f.apply(this.cursor);
        }
        throw new NoSuchElementException("end of cursor");
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("not supported");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.cursor.close();
    }
}
