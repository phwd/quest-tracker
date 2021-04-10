package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.lang.reflect.Method;
import java.util.Deque;
import java.util.logging.Level;

/* renamed from: X.tm  reason: case insensitive filesystem */
public final class C1146tm implements Cloneable {
    public boolean A00;
    public final C0548bl A01;
    public final C0551bo A02;
    public final C1136tc A03;
    public final boolean A04;

    public final void A02(AbstractC0528bR bRVar) {
        Object obj;
        synchronized (this) {
            if (!this.A00) {
                this.A00 = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        C0595ce ceVar = C0595ce.A01;
        if (ceVar instanceof C1117tF) {
            C0592cb cbVar = ((C1117tF) ceVar).A00;
            Method method = cbVar.A00;
            obj = null;
            if (method != null) {
                try {
                    Object invoke = method.invoke(null, new Object[0]);
                    cbVar.A01.invoke(invoke, "response.body().close()");
                    obj = invoke;
                } catch (Exception unused) {
                }
            }
        } else if (C0595ce.A00.isLoggable(Level.FINE)) {
            obj = new Throwable("response.body().close()");
        } else {
            obj = null;
        }
        this.A03.A00 = obj;
        C0538bb bbVar = this.A01.A0H;
        C1158ty tyVar = new C1158ty(this, bRVar);
        synchronized (bbVar) {
            Deque deque = bbVar.A02;
            if (deque.size() >= 64 || C0538bb.A00(bbVar, tyVar) >= 5) {
                bbVar.A01.add(tyVar);
            } else {
                deque.add(tyVar);
                C0538bb.A01(bbVar).execute(tyVar);
            }
        }
    }

    public final String A00() {
        C0544bh bhVar = this.A02.A03;
        C0543bg bgVar = new C0543bg();
        if (bgVar.A02(bhVar, "/...") != AnonymousClass09.A00) {
            bgVar = null;
        }
        int length = OacrConstants.AUTO_SPEECH_DOMAIN.length();
        bgVar.A03 = C0544bh.A02(OacrConstants.AUTO_SPEECH_DOMAIN, 0, length, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        bgVar.A02 = C0544bh.A02(OacrConstants.AUTO_SPEECH_DOMAIN, 0, length, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        return bgVar.A03().toString();
    }

    public final void A01() {
        AbstractC0571cA cAVar;
        C1141th thVar;
        C1136tc tcVar = this.A03;
        tcVar.A04 = true;
        c9 c9Var = tcVar.A01;
        if (c9Var != null) {
            synchronized (c9Var.A08) {
                c9Var.A04 = true;
                cAVar = c9Var.A03;
                thVar = c9Var.A02;
            }
            if (cAVar != null) {
                cAVar.cancel();
            } else if (thVar != null) {
                C0561by.A07(thVar.A03);
            }
        }
    }

    @Override // java.lang.Object
    public final Object clone() {
        return new C1146tm(this.A01, this.A02, this.A04);
    }

    public C1146tm(C0548bl blVar, C0551bo boVar, boolean z) {
        this.A01 = blVar;
        this.A02 = boVar;
        this.A04 = z;
        this.A03 = new C1136tc(blVar, z);
    }
}
