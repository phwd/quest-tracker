package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04520qa;
import X.AbstractC05440vj;
import X.AnonymousClass0C8;
import X.AnonymousClass0p6;
import X.C02180iD;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.LinkedListMultimap;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MultimapDeserializer extends JsonDeserializer<AbstractC05440vj<?, ?>> implements AbstractC04230pb {
    public static final List<String> A05 = ImmutableList.A07("copyOf", "create");
    public final JsonDeserializer<?> A00;
    public final AnonymousClass0p6 A01;
    public final AbstractC04520qa A02;
    public final AnonymousClass0C8 A03;
    public final Method A04;

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final AbstractC05440vj<?, ?> A0A(AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException, C03620oC {
        Object A0l;
        Object A0A;
        LinkedListMultimap linkedListMultimap = new LinkedListMultimap();
        while (r7.A0j() != EnumC03640oE.END_OBJECT) {
            AnonymousClass0p6 r1 = this.A01;
            if (r1 != null) {
                A0l = r1.A00(r7.A0l(), r8);
            } else {
                A0l = r7.A0l();
            }
            r7.A0j();
            EnumC03640oE r2 = EnumC03640oE.START_ARRAY;
            if (r7.A0i() != r2) {
                StringBuilder sb = new StringBuilder("Expecting ");
                sb.append(r2);
                sb.append(", found ");
                sb.append(r7.A0i());
                throw new C02180iD(sb.toString(), r7.A0V());
            }
            while (r7.A0j() != EnumC03640oE.END_ARRAY) {
                AbstractC04520qa r12 = this.A02;
                if (r12 != null) {
                    A0A = this.A00.A0B(r7, r8, r12);
                } else {
                    A0A = this.A00.A0A(r7, r8);
                }
                LinkedListMultimap.A00(linkedListMultimap, A0l, A0A, null);
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
            throw new C02180iD(obj, e);
        } catch (IllegalArgumentException e2) {
            e = e2;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Could not map to ");
            sb3.append(this.A03);
            String obj2 = sb3.toString();
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new C02180iD(obj2, e);
        } catch (IllegalAccessException e3) {
            e = e3;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Could not map to ");
            sb4.append(this.A03);
            String obj3 = sb4.toString();
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new C02180iD(obj3, e);
        }
    }

    @Override // X.AbstractC04230pb
    public final JsonDeserializer<?> A2O(AbstractC02210iH r7, AbstractC02220iI r8) throws C02180iD {
        AnonymousClass0p6 r2 = this.A01;
        if (r2 == null) {
            r2 = r7.A0H(this.A03.A05());
        }
        JsonDeserializer<?> jsonDeserializer = this.A00;
        if (jsonDeserializer == null) {
            jsonDeserializer = r7.A09(this.A03.A04(), r8);
        }
        AbstractC04520qa r3 = this.A02;
        if (!(r3 == null || r8 == null)) {
            r3 = r3.A04(r8);
        }
        return new MultimapDeserializer(this.A03, r2, r3, jsonDeserializer, this.A04);
    }

    public MultimapDeserializer(AnonymousClass0C8 r1, AnonymousClass0p6 r2, AbstractC04520qa r3, JsonDeserializer<?> jsonDeserializer, Method method) {
        this.A03 = r1;
        this.A01 = r2;
        this.A02 = r3;
        this.A00 = jsonDeserializer;
        this.A04 = method;
    }
}
