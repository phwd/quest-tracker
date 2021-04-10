package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

/* renamed from: X.Of  reason: case insensitive filesystem */
public abstract class AbstractC0266Of {
    public final JsonDeserializer A09(AbstractC1022qr qrVar, C00323q r20, O4 o4) {
        AbstractC1024qt A07;
        JsonDeserializer collectionDeserializer;
        O4 o42 = o4;
        AbstractC1033r6 r6Var = (AbstractC1033r6) this;
        AbstractC1024qt A04 = r20.A04();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) A04._valueHandler;
        AnonymousClass2I r8 = qrVar._config;
        PR pr = (PR) A04._typeHandler;
        if (pr == null) {
            pr = r6Var.A0A(r8, A04);
        }
        Iterator it = new Q2(r6Var._factoryConfig._additionalDeserializers).iterator();
        while (true) {
            if (it.hasNext()) {
                collectionDeserializer = ((Og) it.next()).A1v(r20, r8, o42, pr, jsonDeserializer);
                if (collectionDeserializer != null) {
                    break;
                }
            } else {
                Class cls = r20._class;
                if (jsonDeserializer != null || !EnumSet.class.isAssignableFrom(cls)) {
                    if (r20._class.isInterface() || r20.A0G()) {
                        Class cls2 = (Class) AbstractC1033r6.A00.get(r20._class.getName());
                        if (cls2 == null || (A07 = r8._base._typeFactory.A07(r20, cls2)) == null) {
                            StringBuilder sb = new StringBuilder("Can not find a deserializer for non-concrete Collection type ");
                            sb.append(r20);
                            throw new IllegalArgumentException(sb.toString());
                        }
                        C1047rM rMVar = (C1047rM) r8._base._classIntrospector;
                        o42 = C1047rM.A00(A07);
                        if (o42 == null) {
                            o42 = C1046rL.A00(C1047rM.A01(rMVar, r8, A07, r8, false));
                        }
                    } else {
                        A07 = r20;
                    }
                    Ok A0C = r6Var.A0C(qrVar, o42);
                    if (!A0C.A07() && A07._class == ArrayBlockingQueue.class) {
                        return new ArrayBlockingQueueDeserializer(A07, jsonDeserializer, pr, A0C, null);
                    }
                    if (A04._class == String.class) {
                        collectionDeserializer = new StringCollectionDeserializer(A07, A0C, null, jsonDeserializer);
                    } else {
                        collectionDeserializer = new CollectionDeserializer(A07, jsonDeserializer, pr, A0C, null);
                    }
                } else {
                    collectionDeserializer = new EnumSetDeserializer(A04, null);
                }
            }
        }
        OU ou = r6Var._factoryConfig;
        if (!ou.A00()) {
            return collectionDeserializer;
        }
        Iterator it2 = new Q2(ou._modifiers).iterator();
        while (it2.hasNext()) {
            it2.next();
        }
        return collectionDeserializer;
    }

    public final PR A0A(AnonymousClass2I r6, AbstractC1024qt qtVar) {
        PT A00;
        C1043rI rIVar = ((C1046rL) r6.A02(r6.A03(qtVar._class))).A09;
        AbstractC1020qp A01 = r6.A01();
        if (!(A01 instanceof Rw)) {
            A00 = null;
        } else {
            A00 = Rw.A00((Rw) A01, r6, rIVar);
        }
        Collection collection = null;
        if (A00 == null) {
            A00 = r6._base._typeResolverBuilder;
            if (A00 == null) {
                return null;
            }
        } else {
            collection = r6._subtypeResolver.A01(rIVar, r6, A01);
        }
        if (A00.A2M() == null && qtVar.A0G()) {
            O2[] o2Arr = ((AbstractC1033r6) this)._factoryConfig._abstractTypeResolvers;
            if (o2Arr.length > 0) {
                Iterator it = new Q2(o2Arr).iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
            Class cls = qtVar._class;
            if (cls != cls) {
                A00.A1e(cls);
            }
        }
        return A00.A1N(r6, qtVar, collection);
    }
}
