package X;

import android.content.ContentResolver;
import android.media.ExifInterface;
import android.os.Build;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.concurrent.Executor;

/* renamed from: X.1oz  reason: invalid class name and case insensitive filesystem */
public final class C09791oz implements AnonymousClass1p8<AnonymousClass1qQ> {
    public final AnonymousClass1pV A00;
    public final ContentResolver A01;
    public final Executor A02;

    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        AnonymousClass1qE r4 = producerContext.A05;
        C09811pd r6 = producerContext.A07;
        producerContext.A06("local", "exif");
        C09921qP r1 = new C09921qP(this, consumer, r4, producerContext, r6);
        producerContext.A04(new AnonymousClass1p4(this, r1));
        this.A02.execute(r1);
    }

    public static final ExifInterface A00(FileDescriptor fileDescriptor) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new ExifInterface(fileDescriptor);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d  */
    @javax.annotation.Nullable
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.media.ExifInterface A01(android.net.Uri r11) {
        /*
        // Method dump skipped, instructions count: 147
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09791oz.A01(android.net.Uri):android.media.ExifInterface");
    }

    public C09791oz(Executor executor, AnonymousClass1pV r2, ContentResolver contentResolver) {
        this.A02 = executor;
        this.A00 = r2;
        this.A01 = contentResolver;
    }
}
