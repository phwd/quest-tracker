package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.C0681fG;
import X.C1025qv;
import X.NX;
import X.O5;
import X.OD;
import X.PR;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.LinkedListMultimap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MultimapDeserializer extends JsonDeserializer implements AbstractC0264Od {
    public static final List A05 = ImmutableList.A03();
    public final JsonDeserializer A00;
    public final OD A01;
    public final PR A02;
    public final C0681fG A03;
    public final Method A04;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object A0b;
        Object A0C;
        LinkedListMultimap linkedListMultimap = new LinkedListMultimap();
        while (qiVar.A0o() != NX.END_OBJECT) {
            OD od = this.A01;
            if (od != null) {
                A0b = od.A00(qiVar.A0b(), qrVar);
            } else {
                A0b = qiVar.A0b();
            }
            qiVar.A0o();
            NX nx = NX.START_ARRAY;
            if (qiVar.A0U() != nx) {
                StringBuilder sb = new StringBuilder("Expecting ");
                sb.append(nx);
                sb.append(", found ");
                sb.append(qiVar.A0U());
                throw new C1025qv(sb.toString(), qiVar.A0R());
            }
            while (qiVar.A0o() != NX.END_ARRAY) {
                PR pr = this.A02;
                if (pr != null) {
                    A0C = this.A00.A09(qiVar, qrVar, pr);
                } else {
                    A0C = this.A00.A0C(qiVar, qrVar);
                }
                linkedListMultimap.A4Y(A0b, A0C);
            }
        }
        Method method = this.A04;
        if (method == null) {
            return linkedListMultimap;
        }
        try {
            return method.invoke(null, linkedListMultimap);
        } catch (InvocationTargetException e) {
            e = e;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Could not map to ");
            sb2.append(this.A03);
            String obj = sb2.toString();
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new C1025qv(obj, e);
        } catch (IllegalArgumentException e2) {
            e = e2;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Could not map to ");
            sb3.append(this.A03);
            String obj2 = sb3.toString();
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new C1025qv(obj2, e);
        } catch (IllegalAccessException e3) {
            e = e3;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Could not map to ");
            sb4.append(this.A03);
            String obj3 = sb4.toString();
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new C1025qv(obj3, e);
        }
    }

    @Override // X.AbstractC0264Od
    public final JsonDeserializer A1X(AbstractC1022qr qrVar, O5 o5) {
        OD od = this.A01;
        if (od == null) {
            od = qrVar.A0D(this.A03.A05());
        }
        JsonDeserializer jsonDeserializer = this.A00;
        if (jsonDeserializer == null) {
            jsonDeserializer = qrVar.A08(this.A03.A04(), o5);
        }
        PR pr = this.A02;
        if (!(pr == null || o5 == null)) {
            pr = pr.A03(o5);
        }
        return new MultimapDeserializer(this.A03, od, pr, jsonDeserializer, this.A04);
    }

    public MultimapDeserializer(C0681fG fGVar, OD od, PR pr, JsonDeserializer jsonDeserializer, Method method) {
        this.A03 = fGVar;
        this.A01 = od;
        this.A02 = pr;
        this.A00 = jsonDeserializer;
        this.A04 = method;
    }
}
