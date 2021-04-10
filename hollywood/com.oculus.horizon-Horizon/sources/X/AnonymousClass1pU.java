package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* renamed from: X.1pU  reason: invalid class name */
public abstract class AnonymousClass1pU implements AnonymousClass1pP<AnonymousClass1qQ> {
    public final AnonymousClass1pV A00;
    public final Executor A01;

    /* JADX INFO: finally extract failed */
    public final AnonymousClass1qQ A00(InputStream inputStream, int i) throws IOException {
        AnonymousClass1qa r2;
        AnonymousClass1qa r22 = null;
        if (i <= 0) {
            try {
                AnonymousClass1pV r5 = this.A00;
                AbstractC10131rK r4 = r5.A01;
                C09991ql r3 = new C09991ql(r4, r4.A00[0]);
                try {
                    r5.A00.A00(inputStream, r3);
                    C10021qp A002 = r3.A00();
                    r3.close();
                    r2 = AnonymousClass1qa.A01(A002, AnonymousClass1qa.A04);
                } catch (Throwable th) {
                    r3.close();
                    throw th;
                }
            } catch (Throwable th2) {
                AnonymousClass0KJ.A01(inputStream);
                if (0 != 0) {
                    r22.close();
                }
                throw th2;
            }
        } else {
            r2 = AnonymousClass1qa.A01(this.A00.A00(inputStream, i), AnonymousClass1qa.A04);
        }
        AnonymousClass1qQ r0 = new AnonymousClass1qQ(r2);
        AnonymousClass0KJ.A01(inputStream);
        if (r2 != null) {
            r2.close();
        }
        return r0;
    }

    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        AnonymousClass1qE r4 = producerContext.A05;
        C09811pd r7 = producerContext.A07;
        producerContext.A06("local", "fetch");
        AnonymousClass1pT r1 = new AnonymousClass1pT(this, consumer, r4, producerContext, A01(), r7, r4, producerContext);
        producerContext.A04(new AnonymousClass1pS(this, r1));
        this.A01.execute(r1);
    }

    public final String A01() {
        if (this instanceof C09521kn) {
            return "QualifiedResourceFetchProducer";
        }
        if (this instanceof AnonymousClass1lC) {
            return "LocalResourceFetchProducer";
        }
        if (this instanceof C09511km) {
            return "LocalFileFetchProducer";
        }
        if (this instanceof AnonymousClass1p0) {
            return "LocalContentUriThumbnailFetchProducer";
        }
        if (this instanceof C09491ka) {
            return "LocalContentUriFetchProducer";
        }
        if (!(this instanceof AnonymousClass1lB)) {
            return "DataFetchProducer";
        }
        return "LocalAssetFetchProducer";
    }

    public AnonymousClass1pU(Executor executor, AnonymousClass1pV r2) {
        this.A01 = executor;
        this.A00 = r2;
    }
}
