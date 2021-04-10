package android.icu.util;

import java.util.NoSuchElementException;

public class UResourceBundleIterator {
    private UResourceBundle bundle;
    private int index = 0;
    private int size = 0;

    public UResourceBundleIterator(UResourceBundle bndl) {
        this.bundle = bndl;
        this.size = this.bundle.getSize();
    }

    public UResourceBundle next() throws NoSuchElementException {
        int i = this.index;
        if (i < this.size) {
            UResourceBundle uResourceBundle = this.bundle;
            this.index = i + 1;
            return uResourceBundle.get(i);
        }
        throw new NoSuchElementException();
    }

    public String nextString() throws NoSuchElementException, UResourceTypeMismatchException {
        int i = this.index;
        if (i < this.size) {
            UResourceBundle uResourceBundle = this.bundle;
            this.index = i + 1;
            return uResourceBundle.getString(i);
        }
        throw new NoSuchElementException();
    }

    public void reset() {
        this.index = 0;
    }

    public boolean hasNext() {
        return this.index < this.size;
    }
}
