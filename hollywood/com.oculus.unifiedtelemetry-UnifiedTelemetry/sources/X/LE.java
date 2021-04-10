package X;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.List;
import javax.net.ssl.SSLSocket;

public class LE extends AbstractC0355dc {
    @Override // X.AbstractC0355dc
    public final Socket A01(e1 e1Var, eB eBVar, C0350dM dMVar) {
        for (L6 l6 : e1Var.A04) {
            List<Reference<C0350dM>> list = l6.A0B;
            if (!(list.size() >= l6.A00 || !eBVar.equals(l6.A0D.A02) || l6.A0A || l6.A07 == null || l6 == dMVar.A01())) {
                if (dMVar.A03 == null && dMVar.A02.A0B.size() == 1) {
                    Reference<C0350dM> reference = dMVar.A02.A0B.get(0);
                    Socket A00 = C0350dM.A00(dMVar, true, false, false);
                    dMVar.A02 = l6;
                    list.add(reference);
                    return A00;
                }
                throw new IllegalStateException();
            }
        }
        return null;
    }

    @Override // X.AbstractC0355dc
    public final L6 A02(e1 e1Var, eB eBVar, C0350dM dMVar) {
        for (L6 l6 : e1Var.A04) {
            List<Reference<C0350dM>> list = l6.A0B;
            if (list.size() < l6.A00 && eBVar.equals(l6.A0D.A02) && !l6.A0A) {
                if (dMVar.A02 == null) {
                    dMVar.A02 = l6;
                    list.add(new C0351dN(dMVar, dMVar.A06));
                    return l6;
                }
                throw new IllegalStateException();
            }
        }
        return null;
    }

    @Override // X.AbstractC0355dc
    public final void A04(e1 e1Var, L6 l6) {
        if (!e1Var.A00) {
            e1Var.A00 = true;
            e1.A06.execute(e1Var.A03);
        }
        e1Var.A04.add(l6);
    }

    @Override // X.AbstractC0355dc
    public final void A05(C0376dz dzVar, SSLSocket sSLSocket, boolean z) {
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        String[] strArr = dzVar.A02;
        if (strArr != null) {
            enabledCipherSuites = (String[]) dZ.A0B(strArr, sSLSocket.getEnabledCipherSuites());
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        String[] strArr2 = dzVar.A03;
        if (strArr2 != null) {
            enabledProtocols = (String[]) dZ.A0B(strArr2, sSLSocket.getEnabledProtocols());
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
                } else if (!dZ.A09(supportedCipherSuites[i], "TLS_FALLBACK_SCSV")) {
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
        e0 e0Var = new e0(dzVar);
        e0Var.A00(enabledCipherSuites);
        e0Var.A01(enabledProtocols);
        C0376dz dzVar2 = new C0376dz(e0Var);
        String[] strArr4 = dzVar2.A03;
        if (strArr4 != null) {
            sSLSocket.setEnabledProtocols(strArr4);
        }
        String[] strArr5 = dzVar2.A02;
        if (strArr5 != null) {
            sSLSocket.setEnabledCipherSuites(strArr5);
        }
    }

    @Override // X.AbstractC0355dc
    public final void A06(C0370dt dtVar, String str) {
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
                dtVar.A02(str2, str);
                return;
            }
        }
        dtVar.A02(str2, substring);
    }

    @Override // X.AbstractC0355dc
    public final boolean A08(e1 e1Var, L6 l6) {
        if (l6.A0A || e1Var.A01 == 0) {
            e1Var.A04.remove(l6);
            return true;
        }
        e1Var.notifyAll();
        return false;
    }

    @Override // X.AbstractC0355dc
    public final int A00(C0360dh dhVar) {
        return dhVar.A00;
    }

    @Override // X.AbstractC0355dc
    public final dQ A03(e1 e1Var) {
        return e1Var.A05;
    }

    @Override // X.AbstractC0355dc
    public final void A07(C0370dt dtVar, String str, String str2) {
        dtVar.A02(str, str2);
    }
}
