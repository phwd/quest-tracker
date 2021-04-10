package X;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1b7  reason: invalid class name and case insensitive filesystem */
public class C06841b7 implements AbstractC06781b1<InputStream> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC06781b1
    public final void A28(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    @Override // X.AbstractC06781b1
    public final Class<InputStream> A3h() {
        return InputStream.class;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC06781b1
    public final InputStream A8J(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }
}
