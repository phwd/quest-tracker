package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* renamed from: X.1j9  reason: invalid class name */
public abstract class AnonymousClass1j9 implements AnonymousClass1j8<AnonymousClass0PZ> {
    public final AnonymousClass0JW A00;
    public final Executor A01;

    public final AnonymousClass0PZ A00(InputStream inputStream, int i) throws IOException {
        AbstractC00820Ju r2;
        if (i <= 0) {
            try {
                r2 = AbstractC00820Ju.A01(this.A00.A6R(inputStream), AbstractC00820Ju.A04);
            } catch (Throwable th) {
                AnonymousClass0IX.A01(inputStream);
                AbstractC00820Ju.A03(null);
                throw th;
            }
        } else {
            r2 = AbstractC00820Ju.A01(this.A00.A6S(inputStream, i), AbstractC00820Ju.A04);
        }
        AnonymousClass0PZ r0 = new AnonymousClass0PZ(r2);
        AnonymousClass0IX.A01(inputStream);
        AbstractC00820Ju.A03(r2);
        return r0;
    }

    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AnonymousClass0PZ> consumer, ProducerContext producerContext) {
        AnonymousClass1l6 r4 = producerContext.A05;
        AnonymousClass1kA r7 = producerContext.A07;
        producerContext.A06("local", "fetch");
        C09631jA r1 = new C09631jA(this, consumer, r4, producerContext, A01(), r7, r4, producerContext);
        producerContext.A04(new C09641jB(this, r1));
        this.A01.execute(r1);
    }

    public final String A01() {
        if (this instanceof C09611ix) {
            return "QualifiedResourceFetchProducer";
        }
        if (this instanceof C09581it) {
            return "LocalResourceFetchProducer";
        }
        if (this instanceof C09601iw) {
            return "LocalFileFetchProducer";
        }
        if (this instanceof AnonymousClass1iz) {
            return "LocalContentUriThumbnailFetchProducer";
        }
        if (this instanceof C09551iq) {
            return "LocalContentUriFetchProducer";
        }
        if (!(this instanceof C09571is)) {
            return "DataFetchProducer";
        }
        return "LocalAssetFetchProducer";
    }

    public AnonymousClass1j9(Executor executor, AnonymousClass0JW r2) {
        this.A01 = executor;
        this.A00 = r2;
    }
}
