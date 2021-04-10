package X;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;

/* renamed from: X.0Hg  reason: invalid class name */
public class AnonymousClass0Hg extends C04320hN {
    public File A00;
    public final int A01;
    public final /* synthetic */ AnonymousClass04I A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Hg(AnonymousClass04I r3, AnonymousClass08f r4) throws IOException {
        super(r3, r4);
        this.A02 = r3;
        this.A00 = new File(r3.A03.getApplicationInfo().nativeLibraryDir);
        this.A01 = r3.A00;
    }

    @Override // X.C04320hN
    public final boolean A02(ZipEntry zipEntry, String str) {
        zipEntry.getName();
        AnonymousClass04I r1 = this.A02;
        if (str.equals(((AnonymousClass0HU) r1).A00)) {
            ((AnonymousClass0HU) r1).A00 = null;
        } else if ((this.A01 & 1) != 0) {
            File file = new File(this.A00, str);
            if (!file.isFile() || file.length() != zipEntry.getSize()) {
                return true;
            }
            return false;
        }
        return true;
    }
}
