package X;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* renamed from: X.1oS  reason: invalid class name */
public class AnonymousClass1oS implements Callable<AnonymousClass1qQ> {
    public final /* synthetic */ AnonymousClass1kC A00;
    public final /* synthetic */ AnonymousClass1oQ A01;
    public final /* synthetic */ AtomicBoolean A02;

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    @Nullable
    public final AnonymousClass1qQ call() throws Exception {
        Throwable th;
        String A002;
        AnonymousClass1oH r2;
        if (!this.A02.get()) {
            AnonymousClass1oQ r7 = this.A01;
            AnonymousClass1oT r0 = r7.A02;
            AnonymousClass1kC r5 = this.A00;
            AnonymousClass1qQ A003 = r0.A00(r5);
            if (A003 == null) {
                try {
                    AnonymousClass1oR r9 = r7.A00;
                    AnonymousClass1oW A004 = AnonymousClass1oW.A00();
                    A004.A00 = r5;
                    try {
                        synchronized (r9.A09) {
                            try {
                                ArrayList arrayList = new ArrayList(1);
                                if (r5.A55()) {
                                    A002 = r5.A4c();
                                } else {
                                    A002 = AnonymousClass1kB.A00(r5);
                                }
                                arrayList.add(A002);
                                int i = 0;
                                String str = null;
                                r2 = null;
                                while (true) {
                                    if (i >= arrayList.size()) {
                                        r9.A0A.remove(str);
                                        break;
                                    }
                                    str = (String) arrayList.get(i);
                                    r2 = r9.A05.A4I(str, r5);
                                    if (r2 == null) {
                                        i++;
                                    } else if (str != null) {
                                        r9.A0A.add(str);
                                    } else {
                                        throw null;
                                    }
                                }
                            } catch (UnsupportedEncodingException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        A004.A01();
                        if (r2 != null) {
                            File file = r2.A00;
                            FileInputStream fileInputStream = new FileInputStream(file);
                            try {
                                C10021qp A005 = r7.A03.A00(fileInputStream, (int) file.length());
                                fileInputStream.close();
                                try {
                                    AnonymousClass1qa A012 = AnonymousClass1qa.A01(A005, AnonymousClass1qa.A04);
                                    try {
                                        A003 = new AnonymousClass1qQ(A012);
                                    } finally {
                                        if (A012 != null) {
                                            A012.close();
                                        }
                                    }
                                } catch (Exception unused) {
                                    return null;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                fileInputStream.close();
                                throw th;
                            }
                        }
                    } catch (IOException e2) {
                        A004.A01 = e2;
                        A004.A01();
                    } catch (Throwable th3) {
                        th = th3;
                        A004.A01();
                        throw th;
                    }
                    return null;
                } catch (IOException e3) {
                    C01080Kb.A03(AnonymousClass1oQ.class, e3, "Exception reading from cache for %s", r5.A4c());
                    throw e3;
                }
            }
            if (!Thread.interrupted()) {
                return A003;
            }
            A003.close();
            throw new InterruptedException();
        }
        throw new CancellationException();
    }

    public AnonymousClass1oS(AnonymousClass1oQ r1, AtomicBoolean atomicBoolean, AnonymousClass1kC r3) {
        this.A01 = r1;
        this.A02 = atomicBoolean;
        this.A00 = r3;
    }
}
