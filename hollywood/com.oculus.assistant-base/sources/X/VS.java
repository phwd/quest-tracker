package X;

import android.content.Context;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public abstract class VS extends C0965ph {
    public String A00;
    public String[] A01;
    public final Context A02;
    public final Map A03 = new HashMap();

    public static void A00(File file, byte b) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            randomAccessFile.seek(0);
            randomAccessFile.write(b);
            randomAccessFile.setLength(randomAccessFile.getFilePointer());
            randomAccessFile.getFD().sync();
            randomAccessFile.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    public final KU A08() {
        if (this instanceof AnonymousClass2M) {
            AnonymousClass2M r1 = (AnonymousClass2M) this;
            return new C0977pt(r1, r1);
        } else if (!(this instanceof AnonymousClass2O)) {
            AnonymousClass2N r12 = (AnonymousClass2N) this;
            if (!(r12 instanceof BZ)) {
                return new C0971pn(r12, r12);
            }
            BZ bz = (BZ) r12;
            return new VT(bz, bz);
        } else {
            AnonymousClass2O r13 = (AnonymousClass2O) this;
            return new C0967pj(r13, r13);
        }
    }

    public VS(Context context, String str) {
        super(new File(AnonymousClass08.A05(context.getApplicationInfo().dataDir, "/", str)), 1);
        this.A02 = context;
    }
}
