package X;

import android.media.ExifInterface;
import java.io.FileDescriptor;
import java.io.IOException;

/* renamed from: X.1ip  reason: invalid class name and case insensitive filesystem */
public class C09541ip extends AbstractC09741jf<AnonymousClass0PZ> {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.LocalExifThumbnailProducer$1";
    public final /* synthetic */ AnonymousClass1j0 A00;
    public final /* synthetic */ AnonymousClass1kA A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C09541ip(AnonymousClass1j0 r2, AbstractC10011kf r3, AnonymousClass1l6 r4, C10161kv r5, AnonymousClass1kA r6) {
        super(r3, r4, r5, "LocalExifThumbnailProducer");
        this.A00 = r2;
        this.A01 = r6;
    }

    public static final ExifInterface A00(FileDescriptor fileDescriptor) throws IOException {
        return new ExifInterface(fileDescriptor);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007e  */
    @Override // X.AnonymousClass0IL
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A06() throws java.lang.Exception {
        /*
        // Method dump skipped, instructions count: 302
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09541ip.A06():java.lang.Object");
    }
}
