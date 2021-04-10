package X;

import java.io.File;
import java.io.IOException;

/* renamed from: X.sC  reason: case insensitive filesystem */
public class C0493sC extends C0488ry {
    public File A00;
    public final int A01;
    public final /* synthetic */ C0489rz A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0493sC(C0489rz rzVar, C0502sV sVVar) throws IOException {
        super(rzVar, sVVar);
        this.A02 = rzVar;
        this.A00 = new File(rzVar.A02.getApplicationInfo().nativeLibraryDir);
        this.A01 = rzVar.A00;
    }
}
