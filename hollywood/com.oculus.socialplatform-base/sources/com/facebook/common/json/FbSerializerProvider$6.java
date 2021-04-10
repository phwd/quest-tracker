package com.facebook.common.json;

import X.AbstractC02120i3;
import X.AbstractC02280iQ;
import X.AbstractC02300iS;
import X.AnonymousClass0iU;
import X.C02310iT;
import X.C03620oC;
import X.C03750oT;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class FbSerializerProvider$6 extends JsonSerializer<Map> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(Map map, AbstractC02300iS r9, AbstractC02120i3 r10) throws IOException, C03620oC {
        Map map2 = map;
        r9.A0I();
        for (Object obj : map2.keySet()) {
            if (obj instanceof String) {
                r9.A0R((String) obj);
            } else if (obj instanceof Enum) {
                AnonymousClass0iU A00 = r9.A0A().A00();
                StringWriter stringWriter = new StringWriter();
                AbstractC02300iS A01 = A00.A01(stringWriter, new C03750oT(AnonymousClass0iU.A00(), stringWriter, false));
                A01.A09(r9.A0A());
                A01.A0C(obj);
                A01.flush();
                AbstractC02280iQ A03 = A00.A03(stringWriter.toString());
                String A0Q = A03.A0Q();
                if (A0Q == null || A03.A0j() != null) {
                    StringBuilder sb = new StringBuilder("Tried to use json as map key, but it is not a string: ");
                    sb.append(stringWriter);
                    throw new C02310iT(sb.toString());
                }
                r9.A0R(A0Q);
            } else {
                StringBuilder sb2 = new StringBuilder("Non-string, non-enum key (");
                sb2.append(obj.getClass());
                sb2.append(") found in map.");
                throw new C02310iT(sb2.toString());
            }
            r9.A0C(map2.get(obj));
        }
        r9.A0F();
    }
}
