package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC00010j;
import X.AbstractC0122Rd;
import X.AnonymousClass0Y;
import X.AnonymousClass1T;
import X.AnonymousClass1V;
import X.AnonymousClass6c;
import X.AnonymousClass7F;
import X.AnonymousClass9r;
import X.RZ;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

@JacksonStdImpl
public final class MapDeserializer extends ContainerDeserializerBase<Map<Object, Object>> implements AnonymousClass1V, AbstractC00010j {
    public static final long serialVersionUID = -3378654289961736240L;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final boolean _hasDefaultCreator;
    public final HashSet<String> _ignorableProperties;
    public final AnonymousClass6c _keyDeserializer;
    public final RZ _mapType;
    public final AnonymousClass1T _propertyBasedCreator;
    public boolean _standardStringKey;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass0Y _valueInstantiator;
    public final AnonymousClass7F _valueTypeDeserializer;

    /* JADX WARN: Incorrect return type in method signature: (LX/Rn;LX/Rd;)Ljava/util/Map<Ljava/lang/Object;Ljava/lang/Object;>; */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        if (r0.contains(r1) == false) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r6.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        r0 = r4.A01.get(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        if (r0 == null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        if (r2.A02(r0.A00(r6, r7)) == false) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        r6.A04();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r4.A00(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        r4.A00(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0061, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0062, code lost:
        r1 = r5._mapType._class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0068, code lost:
        if ((r2 instanceof java.lang.reflect.InvocationTargetException) != false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0070, code lost:
        r2 = r2.getCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0077, code lost:
        if ((r2 instanceof java.lang.Error) != false) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0081, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0086, code lost:
        throw X.RW.A01(r2, r1, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0013, code lost:
        if (r1 == X.AnonymousClass9p.START_OBJECT) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0015, code lost:
        r1 = r6.A04();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001b, code lost:
        if (r1 != X.AnonymousClass9p.FIELD_NAME) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001d, code lost:
        r1 = r6.A08();
        r6.A04();
        r0 = r5._ignorableProperties;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        if (r0 == null) goto L_0x0032;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A00(X.Rn r6, X.AbstractC0122Rd r7) throws java.io.IOException, X.AnonymousClass9r {
        /*
        // Method dump skipped, instructions count: 173
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.MapDeserializer.A00(X.Rn, X.Rd):void");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        A00(rn, rd);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
