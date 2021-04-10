package X;

import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* renamed from: X.1b2  reason: invalid class name and case insensitive filesystem */
public class C06791b2 implements AbstractC06781b1<ParcelFileDescriptor> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC06781b1
    public final void A28(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        parcelFileDescriptor.close();
    }

    @Override // X.AbstractC06781b1
    public final Class<ParcelFileDescriptor> A3h() {
        return ParcelFileDescriptor.class;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC06781b1
    public final ParcelFileDescriptor A8J(File file) throws FileNotFoundException {
        return ParcelFileDescriptor.open(file, 268435456);
    }
}
