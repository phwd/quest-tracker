package X;

import java.io.File;
import java.io.FileFilter;

/* renamed from: X.1XO  reason: invalid class name */
public class AnonymousClass1XO implements FileFilter {
    public final /* synthetic */ File A00;

    public AnonymousClass1XO(File file) {
        this.A00 = file;
    }

    public final boolean accept(File file) {
        return file.equals(this.A00);
    }
}
