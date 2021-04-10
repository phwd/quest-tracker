package X;

import java.io.File;
import java.io.FileFilter;
import java.util.Comparator;
import java.util.Iterator;
import javax.annotation.Nullable;

/* renamed from: X.0qO  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC06960qO extends AnonymousClass0G3 {
    @Nullable
    public FileFilter A00;
    @Nullable
    public Comparator<File> A01;

    public abstract AnonymousClass0G3 A01(File file);

    @Override // X.AnonymousClass0G3
    public final Iterator<AnonymousClass0G3> A00() {
        return new AnonymousClass0G1(this, new AnonymousClass0G4(super.A00, this.A00, this.A01).iterator());
    }

    public AbstractC06960qO(File file) {
        super(file);
    }
}
