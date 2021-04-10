package X;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* renamed from: X.0N2  reason: invalid class name */
public class AnonymousClass0N2 extends AbstractC08180w8 {
    @Override // X.AbstractC08180w8
    public final Socket A01(C08510wh r6, C08610wt r7, C08090vs r8) {
        for (C01250Mm r4 : r6.A04) {
            List<Reference<C08090vs>> list = r4.A0B;
            if (!(list.size() >= r4.A00 || !r7.equals(r4.A0D.A02) || r4.A0A || r4.A07 == null || r4 == r8.A01())) {
                if (r8.A03 == null && r8.A02.A0B.size() == 1) {
                    Reference<C08090vs> reference = r8.A02.A0B.get(0);
                    Socket A00 = C08090vs.A00(r8, true, false, false);
                    r8.A02 = r4;
                    list.add(reference);
                    return A00;
                }
                throw new IllegalStateException();
            }
        }
        return null;
    }

    @Override // X.AbstractC08180w8
    public final C01250Mm A02(C08510wh r6, C08610wt r7, C08090vs r8) {
        for (C01250Mm r3 : r6.A04) {
            List<Reference<C08090vs>> list = r3.A0B;
            if (list.size() < r3.A00 && r7.equals(r3.A0D.A02) && !r3.A0A) {
                if (r8.A02 == null) {
                    r8.A02 = r3;
                    list.add(new C08100vt(r8, r8.A06));
                    return r3;
                }
                throw new IllegalStateException();
            }
        }
        return null;
    }

    @Override // X.AbstractC08180w8
    public final void A04(C08510wh r3, C01250Mm r4) {
        if (!r3.A00) {
            r3.A00 = true;
            C08510wh.A06.execute(r3.A03);
        }
        r3.A04.add(r4);
    }

    @Override // X.AbstractC08180w8
    public final void A05(C08490wf r8, SSLSocket sSLSocket, boolean z) {
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        String[] strArr = r8.A02;
        if (strArr != null) {
            enabledCipherSuites = (String[]) C08160w5.A0B(strArr, sSLSocket.getEnabledCipherSuites());
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        String[] strArr2 = r8.A03;
        if (strArr2 != null) {
            enabledProtocols = (String[]) C08160w5.A0B(strArr2, sSLSocket.getEnabledProtocols());
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        if (z) {
            String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
            int length = supportedCipherSuites.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (!C08160w5.A09(supportedCipherSuites[i], "TLS_FALLBACK_SCSV")) {
                    i++;
                } else if (i != -1) {
                    int length2 = enabledCipherSuites.length;
                    int i2 = length2 + 1;
                    String[] strArr3 = new String[i2];
                    System.arraycopy(enabledCipherSuites, 0, strArr3, 0, length2);
                    strArr3[i2 - 1] = "TLS_FALLBACK_SCSV";
                    enabledCipherSuites = strArr3;
                }
            }
        }
        C08500wg r0 = new C08500wg(r8);
        r0.A00(enabledCipherSuites);
        r0.A01(enabledProtocols);
        C08490wf r1 = new C08490wf(r0);
        String[] strArr4 = r1.A03;
        if (strArr4 != null) {
            sSLSocket.setEnabledProtocols(strArr4);
        }
        String[] strArr5 = r1.A02;
        if (strArr5 != null) {
            sSLSocket.setEnabledCipherSuites(strArr5);
        }
    }

    @Override // X.AbstractC08180w8
    public final void A06(C08420wY r5, String str) {
        String str2;
        String substring;
        int indexOf = str.indexOf(":", 1);
        if (indexOf != -1) {
            str2 = str.substring(0, indexOf);
            substring = str.substring(indexOf + 1);
        } else {
            str2 = "";
            if (str.startsWith(":")) {
                substring = str.substring(1);
            } else {
                r5.A02(str2, str);
                return;
            }
        }
        r5.A02(str2, substring);
    }

    @Override // X.AbstractC08180w8
    public final boolean A08(C08510wh r2, C01250Mm r3) {
        if (r3.A0A || r2.A01 == 0) {
            r2.A04.remove(r3);
            return true;
        }
        r2.notifyAll();
        return false;
    }

    @Override // X.AbstractC08180w8
    public final int A00(C08230wD r2) {
        return r2.A00;
    }

    @Override // X.AbstractC08180w8
    public final C08130vw A03(C08510wh r2) {
        return r2.A05;
    }

    @Override // X.AbstractC08180w8
    public final void A07(C08420wY r1, String str, String str2) {
        r1.A02(str, str2);
    }
}
