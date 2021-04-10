package X;

import java.io.InputStream;

/* renamed from: X.ps  reason: case insensitive filesystem */
public final class C0976ps extends KT {
    public int A00;
    public final InputStream A01;
    public final /* synthetic */ C0977pt A02;

    public C0976ps(C0977pt ptVar) {
        this.A02 = ptVar;
        InputStream inputStream = ptVar.A01.getInputStream(ptVar.A00);
        try {
            this.A01 = ptVar.A03.A00.getCompressedInputStream(inputStream);
            if (!A00()) {
                close();
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }
}
