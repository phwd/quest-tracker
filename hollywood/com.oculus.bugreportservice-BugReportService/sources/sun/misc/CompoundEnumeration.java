package sun.misc;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class CompoundEnumeration implements Enumeration {
    private Enumeration[] enums;
    private int index = 0;

    public CompoundEnumeration(Enumeration[] enumerationArr) {
        this.enums = enumerationArr;
    }

    private boolean next() {
        while (true) {
            int i = this.index;
            Enumeration[] enumerationArr = this.enums;
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
    public Object nextElement() {
        if (next()) {
            return this.enums[this.index].nextElement();
        }
        throw new NoSuchElementException();
    }
}
