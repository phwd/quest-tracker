package sun.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class ResourceBundleEnumeration implements Enumeration {
    Enumeration enumeration;
    Iterator iterator;
    String next = null;
    Set set;

    public ResourceBundleEnumeration(Set set2, Enumeration enumeration2) {
        this.set = set2;
        this.iterator = set2.iterator();
        this.enumeration = enumeration2;
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        if (this.next == null) {
            if (this.iterator.hasNext()) {
                this.next = (String) this.iterator.next();
            } else if (this.enumeration != null) {
                while (this.next == null && this.enumeration.hasMoreElements()) {
                    this.next = (String) this.enumeration.nextElement();
                    if (this.set.contains(this.next)) {
                        this.next = null;
                    }
                }
            }
        }
        return this.next != null;
    }

    @Override // java.util.Enumeration
    public String nextElement() {
        if (hasMoreElements()) {
            String str = this.next;
            this.next = null;
            return str;
        }
        throw new NoSuchElementException();
    }
}
