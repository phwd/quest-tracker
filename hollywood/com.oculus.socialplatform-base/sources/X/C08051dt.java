package X;

import java.io.File;

/* renamed from: X.1dt  reason: invalid class name and case insensitive filesystem */
public class C08051dt {
    public final AbstractC08061du A00;
    public volatile AbstractC08081dw A01;

    public final AbstractC08081dw A00() {
        File file;
        if (this.A01 == null) {
            synchronized (this) {
                if (this.A01 == null) {
                    AbstractC08061du r4 = this.A00;
                    C08041ds r2 = r4.A01;
                    File cacheDir = r2.A00.getCacheDir();
                    if (cacheDir == null) {
                        cacheDir = null;
                    } else {
                        String str = r2.A01;
                        if (str != null) {
                            file = new File(cacheDir, str);
                            AnonymousClass1nY r22 = null;
                            if (file != null && (file.mkdirs() || (file.exists() && file.isDirectory()))) {
                                r22 = new AnonymousClass1nY(file, r4.A00);
                            }
                            this.A01 = r22;
                        }
                    }
                    file = cacheDir;
                    AnonymousClass1nY r222 = null;
                    r222 = new AnonymousClass1nY(file, r4.A00);
                    this.A01 = r222;
                }
                if (this.A01 == null) {
                    this.A01 = new C08071dv();
                }
            }
        }
        return this.A01;
    }

    public C08051dt(AbstractC08061du r1) {
        this.A00 = r1;
    }
}
