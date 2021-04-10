package com.facebook.common.json;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AnonymousClass006;
import X.AnonymousClass0It;
import X.C01210Vd;
import X.C02180iD;
import X.C02290iR;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import java.io.IOException;

public class ImmutableMapDeserializer<K, V> extends JsonDeserializer<ImmutableMap<K, V>> {
    public JsonDeserializer<K> A00;
    public JsonDeserializer<V> A01;
    public boolean A02 = false;
    public final AbstractC02190iF A03;
    public final Class A04;

    public ImmutableMapDeserializer(AbstractC02190iF r5) {
        boolean z = false;
        Class<?> cls = r5.A06(0)._class;
        this.A04 = cls;
        Preconditions.checkArgument((cls == String.class || Enum.class.isAssignableFrom(cls)) ? true : z, "Map keys must be a String or an enum.");
        this.A03 = r5.A06(1);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException, C03620oC {
        EnumC03640oE A0i;
        C01210Vd r5 = (C01210Vd) r7.A0N();
        if (!r7.A0o() || (A0i = r7.A0i()) == EnumC03640oE.VALUE_NULL) {
            r7.A0h();
            return RegularImmutableMap.A03;
        } else if (A0i == EnumC03640oE.START_OBJECT) {
            if (!this.A02) {
                Class cls = this.A04;
                if (cls != String.class) {
                    this.A00 = r5.A08(r8, cls);
                }
                this.A02 = true;
            }
            if (this.A01 == null) {
                this.A01 = r5.A07(r8, this.A03);
            }
            ImmutableMap.Builder A042 = ImmutableMap.A04();
            while (AnonymousClass0It.A00(r7) != EnumC03640oE.END_OBJECT) {
                if (r7.A0i() == EnumC03640oE.FIELD_NAME) {
                    String A0l = r7.A0l();
                    r7.A0j();
                    V A0A = this.A01.A0A(r7, r8);
                    if (A0A != null) {
                        if (this.A00 != null) {
                            AbstractC02280iQ A032 = r5.A00().A03(AnonymousClass006.A09("\"", A0l, "\""));
                            A032.A0j();
                            try {
                                A042.put(this.A00.A0A(A032, r8), A0A);
                            } catch (C02180iD unused) {
                            }
                        } else {
                            A042.put(A0l, A0A);
                        }
                    }
                }
            }
            return A042.build();
        } else {
            throw new C02290iR("Failed to deserialize to a map - missing start_object token", r7.A0V());
        }
    }
}
