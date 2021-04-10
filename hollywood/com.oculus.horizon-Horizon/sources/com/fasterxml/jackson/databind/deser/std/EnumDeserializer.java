package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass07O;
import X.AnonymousClass0jg;
import X.C06330mu;
import X.C06360mx;
import java.io.IOException;
import java.lang.reflect.Method;

public final class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    public static final long serialVersionUID = -5893263645879532318L;
    public final C06360mx<?> _resolver;

    public static class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        public static final long serialVersionUID = -7775129435872564122L;
        public final Class<?> _enumClass;
        public final Method _factory;
        public final Class<?> _inputType;

        public FactoryBasedDeserializer(Class<?> cls, AnonymousClass07O r3, Class<?> cls2) {
            super(Enum.class);
            this._enumClass = cls;
            this._factory = r3.A00;
            this._inputType = cls2;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final Object A09(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
            Object obj;
            Class<?> cls = this._inputType;
            if (cls == null) {
                obj = r6.A0e();
            } else if (cls == Integer.class) {
                obj = Integer.valueOf(r6.A0C());
            } else if (cls == Long.class) {
                obj = Long.valueOf(r6.A0D());
            } else {
                throw null;
            }
            try {
                return this._factory.invoke(this._enumClass, obj);
            } catch (Exception e) {
                C06330mu.A03(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    public EnumDeserializer(C06360mx<?> r2) {
        super(Enum.class);
        this._resolver = r2;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x0027 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r1v1. Raw type applied. Possible types: java.lang.Enum<?> */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        if (r1 != null) goto L_0x006b;
     */
    /* renamed from: A00 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Enum<?> A09(X.AbstractC04100gp r4, X.AbstractC04020gg r5) throws java.io.IOException, X.AnonymousClass0jg {
        /*
        // Method dump skipped, instructions count: 108
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.EnumDeserializer.A09(X.0gp, X.0gg):java.lang.Enum");
    }
}
