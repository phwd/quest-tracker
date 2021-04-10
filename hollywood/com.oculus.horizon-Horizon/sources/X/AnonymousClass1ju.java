package X;

import android.os.SystemClock;
import java.io.IOException;
import java.util.regex.Pattern;

/* renamed from: X.1ju  reason: invalid class name */
public class AnonymousClass1ju implements AbstractC08570wn {
    public final /* synthetic */ AnonymousClass1k2 A00;
    public final /* synthetic */ C09481jv A01;
    public final /* synthetic */ AnonymousClass1pX A02;

    public AnonymousClass1ju(C09481jv r1, AnonymousClass1k2 r2, AnonymousClass1pX r3) {
        this.A01 = r1;
        this.A00 = r2;
        this.A02 = r3;
    }

    @Override // X.AbstractC08570wn
    public final void onFailure(AnonymousClass0Mz r3, IOException iOException) {
        AnonymousClass1pX r1 = this.A02;
        if (r3.A03.A04) {
            r1.A00();
        } else {
            r1.A02(iOException);
        }
    }

    @Override // X.AbstractC08570wn
    public final void onResponse(AnonymousClass0Mz r12, C08220wC r13) throws IOException {
        AnonymousClass1jz r2;
        AnonymousClass1k2 r4 = this.A00;
        r4.A01 = SystemClock.elapsedRealtime();
        AbstractC08210wB r3 = r13.A0B;
        if (r3 == null) {
            StringBuilder sb = new StringBuilder("Response body null: ");
            sb.append(r13);
            IOException iOException = new IOException(sb.toString());
            AnonymousClass1pX r1 = this.A02;
            if (r12.A03.A04) {
                r1.A00();
            } else {
                r1.A02(iOException);
            }
        } else {
            try {
                int i = r13.A01;
                if (i < 200 || i >= 300) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Unexpected HTTP code ");
                    sb2.append(r13);
                    IOException iOException2 = new IOException(sb2.toString());
                    AnonymousClass1pX r14 = this.A02;
                    if (r12.A03.A04) {
                        r14.A00();
                    } else {
                        r14.A02(iOException2);
                    }
                    r3.close();
                }
                String A002 = r13.A00("Content-Range");
                if (A002 != null) {
                    Pattern pattern = AnonymousClass1jz.A02;
                    if (pattern == null) {
                        pattern = Pattern.compile("[-/ ]");
                        AnonymousClass1jz.A02 = pattern;
                    }
                    try {
                        String[] split = pattern.split(A002);
                        boolean z = false;
                        if (split.length == 4) {
                            z = true;
                        }
                        AnonymousClass0KU.A01(Boolean.valueOf(z));
                        AnonymousClass0KU.A01(Boolean.valueOf(split[0].equals("bytes")));
                        int parseInt = Integer.parseInt(split[1]);
                        int parseInt2 = Integer.parseInt(split[2]);
                        int parseInt3 = Integer.parseInt(split[3]);
                        boolean z2 = false;
                        if (parseInt2 > parseInt) {
                            z2 = true;
                        }
                        AnonymousClass0KU.A01(Boolean.valueOf(z2));
                        boolean z3 = false;
                        if (parseInt3 > parseInt2) {
                            z3 = true;
                        }
                        AnonymousClass0KU.A01(Boolean.valueOf(z3));
                        if (parseInt2 < parseInt3 - 1) {
                            r2 = new AnonymousClass1jz(parseInt, parseInt2);
                        } else {
                            r2 = new AnonymousClass1jz(parseInt, Integer.MAX_VALUE);
                        }
                        if (!(r2.A00 == 0 && r2.A01 == Integer.MAX_VALUE)) {
                            ((AnonymousClass1k1) r4).A02 = r2;
                            ((AnonymousClass1k1) r4).A00 = 8;
                        }
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(String.format(null, "Invalid Content-Range header value: \"%s\"", A002), e);
                    }
                }
                long A003 = r3.A00();
                if (A003 < 0) {
                    A003 = 0;
                }
                this.A02.A01(r3.A03().A4q(), (int) A003);
                r3.close();
            } catch (Exception e2) {
                AnonymousClass1pX r15 = this.A02;
                if (r12.A03.A04) {
                    r15.A00();
                } else {
                    r15.A02(e2);
                }
            } catch (Throwable th) {
                r3.close();
                throw th;
            }
        }
    }
}
