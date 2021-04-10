package X;

import java.io.File;
import java.io.FileFilter;
import java.util.Comparator;
import java.util.Iterator;
import javax.annotation.Nullable;

public abstract class ZA extends Fo {
    @Nullable
    public FileFilter A00;
    @Nullable
    public Comparator<File> A01;

    public abstract Fo A01(File file);

    @Override // X.Fo
    public final Iterator<Fo> A00() {
        return new Fm(this, new Fp(super.A00, this.A00, this.A01).iterator());
    }

    public ZA(File file) {
        super(file);
    }
}
