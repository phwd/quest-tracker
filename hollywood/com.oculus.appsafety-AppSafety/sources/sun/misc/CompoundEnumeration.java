package sun.misc;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class CompoundEnumeration<E> implements Enumeration<E> {
    private Enumeration<E>[] enums;
    private int index = 0;

    public CompoundEnumeration(Enumeration<E>[] enums2) {
        this.enums = enums2;
    }

    private boolean next() {
        while (true) {
            int i = this.index;
            Enumeration<E>[] enumerationArr = this.enums;
            if (i >= enumerationArr.length) {
                return false;
            }
            if (enumerationArr[i] != null && enumerationArr[i].hasMoreElements()) {
                return true;
            }
            this.index++;
        }
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return next();
    }

    @Override // java.util.Enumeration
    public E nextElement() {
        if (next()) {
            return this.enums[this.index].nextElement();
        }
        throw new NoSuchElementException();
    }
}
