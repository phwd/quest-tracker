package X;

import java.io.Closeable;
import java.util.zip.ZipFile;

public abstract class KU implements Closeable {
    public final KR A00() {
        if (this instanceof C0977pt) {
            return new KR(((C0977pt) this).A02);
        }
        if (!(this instanceof C0971pn)) {
            return new KR(((C0967pj) this).A00);
        }
        return new KR(((C0971pn) this).A01());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        ZipFile zipFile;
        if (this instanceof C0977pt) {
            zipFile = ((C0977pt) this).A01;
        } else if (this instanceof C0971pn) {
            zipFile = ((C0971pn) this).A01;
        } else {
            return;
        }
        zipFile.close();
    }
}
