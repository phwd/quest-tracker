package X;

import java.io.File;

public final class VT extends C0971pn {
    public File A00;
    public final int A01;
    public final /* synthetic */ BZ A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VT(BZ bz, AnonymousClass2N r4) {
        super(bz, r4);
        this.A02 = bz;
        this.A00 = new File(bz.A02.getApplicationInfo().nativeLibraryDir);
        this.A01 = bz.A00;
    }
}
