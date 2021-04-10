package X;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* renamed from: X.1kK  reason: invalid class name */
public class AnonymousClass1kK implements Callable<AnonymousClass0PZ> {
    public final /* synthetic */ AnonymousClass0H3 A00;
    public final /* synthetic */ C09901kJ A01;
    public final /* synthetic */ AtomicBoolean A02;

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX INFO: finally extract failed */
    @Override // java.util.concurrent.Callable
    @Nullable
    public final AnonymousClass0PZ call() throws Exception {
        AnonymousClass1m2 r2;
        AnonymousClass0JV A6S;
        if (!this.A02.get()) {
            C09901kJ r6 = this.A01;
            AnonymousClass1kI r0 = r6.A03;
            AnonymousClass0H3 r5 = this.A00;
            AnonymousClass0PZ A002 = r0.A00(r5);
            if (A002 == null) {
                try {
                    AnonymousClass1lR r9 = r6.A00;
                    AnonymousClass1lK A003 = AnonymousClass1lK.A00();
                    A003.A00 = r5;
                    try {
                        synchronized (r9.A09) {
                            List<String> A012 = AnonymousClass0H4.A01(r5);
                            int i = 0;
                            String str = null;
                            r2 = null;
                            while (i < A012.size() && (r2 = r9.A05.A4n((str = A012.get(i)), r5)) == null) {
                                i++;
                            }
                            if (r2 == null) {
                                r9.A0A.remove(str);
                            } else if (str != null) {
                                r9.A0A.add(str);
                            } else {
                                throw null;
                            }
                        }
                        A003.A01();
                    } catch (IOException e) {
                        A003.A01 = e;
                        A003.A01();
                        r2 = null;
                    } catch (Throwable th) {
                        A003.A01();
                        throw th;
                    }
                    if (r2 == null) {
                        A6S = null;
                    } else {
                        File file = r2.A00;
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            A6S = r6.A01.A6S(fileInputStream, (int) file.length());
                        } finally {
                            fileInputStream.close();
                        }
                    }
                    if (A6S == null) {
                        return null;
                    }
                    try {
                        AbstractC00820Ju A013 = AbstractC00820Ju.A01(A6S, AbstractC00820Ju.A04);
                        try {
                            A002 = new AnonymousClass0PZ(A013);
                        } finally {
                            AbstractC00820Ju.A03(A013);
                        }
                    } catch (Exception unused) {
                        return null;
                    }
                } catch (IOException e2) {
                    AnonymousClass0J5.A03(C09901kJ.class, e2, "Exception reading from cache for %s", r5.A5D());
                    throw e2;
                }
            }
            if (!Thread.interrupted()) {
                return A002;
            }
            A002.close();
            throw new InterruptedException();
        }
        throw new CancellationException();
    }

    public AnonymousClass1kK(C09901kJ r1, AtomicBoolean atomicBoolean, AnonymousClass0H3 r3) {
        this.A01 = r1;
        this.A02 = atomicBoolean;
        this.A00 = r3;
    }
}
