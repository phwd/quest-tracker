package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.EnumC05930lf;
import java.io.IOException;

public class JdkDeserializers$StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    public static final JdkDeserializers$StackTraceElementDeserializer A00 = new JdkDeserializers$StackTraceElementDeserializer();

    public JdkDeserializers$StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final StackTraceElement A09(AnonymousClass0aT r8, AbstractC02570aK r9) throws IOException, C05910ld {
        EnumC05930lf A0G = r8.A0G();
        if (A0G == EnumC05930lf.START_OBJECT) {
            String str = "";
            String str2 = str;
            String str3 = str;
            int i = -1;
            while (true) {
                EnumC05930lf A0H = r8.A0H();
                if (A0H == EnumC05930lf.END_OBJECT) {
                    return new StackTraceElement(str, str2, str3, i);
                }
                String A0O = r8.A0O();
                if ("className".equals(A0O)) {
                    str = r8.A0P();
                } else if ("fileName".equals(A0O)) {
                    str3 = r8.A0P();
                } else if ("lineNumber".equals(A0O)) {
                    if (A0H.isNumeric()) {
                        i = r8.A06();
                    } else {
                        throw AnonymousClass0aG.A00(r8, "Non-numeric token (" + A0H + ") for property 'lineNumber'");
                    }
                } else if ("methodName".equals(A0O)) {
                    str2 = r8.A0P();
                } else if (!"nativeMethod".equals(A0O)) {
                    A0N(r8, r9, this._valueClass, A0O);
                }
            }
        } else {
            throw r9.A0C(this._valueClass, A0G);
        }
    }
}
