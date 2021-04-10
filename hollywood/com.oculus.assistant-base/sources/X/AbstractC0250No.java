package X;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.IdentityHashMap;

/* renamed from: X.No  reason: case insensitive filesystem */
public abstract class AbstractC0250No extends AbstractC1031r2 implements Serializable {
    public static final long serialVersionUID = 1;
    public transient ArrayList A00;
    public transient IdentityHashMap A01;

    public final void A0H(AbstractC1012qg qgVar, Object obj) {
        JsonSerializer jsonSerializer;
        C1015qj qjVar;
        String str;
        JsonRootName jsonRootName;
        OE oe;
        boolean z = false;
        if (obj == null) {
            jsonSerializer = this._nullValueSerializer;
        } else {
            Class<?> cls = obj.getClass();
            jsonSerializer = A0B(cls, true, null);
            AnonymousClass2H r2 = this._config;
            String str2 = r2._rootName;
            if (str2 == null) {
                z = r2.A06(EnumC1030r1.WRAP_ROOT_VALUE);
                if (z) {
                    qgVar.A0C();
                    QH qh = this._rootNames;
                    AnonymousClass2H r7 = this._config;
                    synchronized (qh) {
                        C0297Pt pt = new C0297Pt(cls);
                        QA qa = qh._rootNames;
                        if (qa == null) {
                            qh._rootNames = new QA(20, 200);
                        } else {
                            qjVar = (C1015qj) qa.get(pt);
                            if (qjVar != null) {
                            }
                        }
                        O4 A02 = r7.A02(r7.A03(cls));
                        AbstractC1020qp A012 = r7.A01();
                        C1043rI rIVar = ((C1046rL) A02).A09;
                        if (!(!(A012 instanceof Rw) || (jsonRootName = (JsonRootName) rIVar.A0L(JsonRootName.class)) == null || (oe = new OE(jsonRootName.value())) == null)) {
                            str = oe._simpleName;
                            if (str.length() > 0) {
                                qjVar = new C1015qj(str);
                                qh._rootNames.put(pt, qjVar);
                            }
                        }
                        str = cls.getSimpleName();
                        qjVar = new C1015qj(str);
                        qh._rootNames.put(pt, qjVar);
                    }
                    qgVar.A0J(qjVar);
                }
            } else if (str2.length() != 0) {
                qgVar.A0C();
                qgVar.A0M(str2);
                z = true;
            }
        }
        try {
            jsonSerializer.A0B(obj, qgVar, this);
            if (z) {
                qgVar.A09();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                message = AnonymousClass08.A05("[no message for ", e2.getClass().getName(), "]");
            }
            throw new C1025qv(message, e2);
        }
    }

    public final AbstractC0250No A0G(AnonymousClass2H r2, AbstractC0285Ph ph) {
        if (!(this instanceof C00172j)) {
            return new C00162i(this, r2, ph);
        }
        return new C00172j(this, r2, ph);
    }

    public AbstractC0250No() {
    }

    public AbstractC0250No(AbstractC1031r2 r2Var, AnonymousClass2H r2, AbstractC0285Ph ph) {
        super(r2Var, r2, ph);
    }
}
