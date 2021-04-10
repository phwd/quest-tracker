package X;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Fo {
    public static final Iterator<Fo> A01 = new ArrayList(0).iterator();
    public final File A00;

    public Fo(File file) {
        this.A00 = file;
    }

    public Iterator<Fo> A00() {
        return A01;
    }
}
