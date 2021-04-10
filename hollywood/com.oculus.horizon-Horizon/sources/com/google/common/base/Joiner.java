package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.IOException;
import java.util.Iterator;

@GwtCompatible
public class Joiner {
    public final String separator;

    public Joiner(String str) {
        if (str != null) {
            this.separator = str;
            return;
        }
        throw null;
    }

    public final String join(Iterable<?> iterable) {
        CharSequence charSequence;
        Iterator<?> it = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        try {
            if (it.hasNext()) {
                Object next = it.next();
                if (next != null) {
                    if (next instanceof CharSequence) {
                        charSequence = (CharSequence) next;
                    } else {
                        charSequence = next.toString();
                    }
                    while (true) {
                        sb.append(charSequence);
                        if (!it.hasNext()) {
                            break;
                        }
                        sb.append((CharSequence) this.separator);
                        Object next2 = it.next();
                        if (next2 == null) {
                            throw null;
                        } else if (next2 instanceof CharSequence) {
                            charSequence = (CharSequence) next2;
                        } else {
                            charSequence = next2.toString();
                        }
                    }
                } else {
                    throw null;
                }
            }
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
