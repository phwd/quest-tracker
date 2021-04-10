package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0KT  reason: invalid class name */
public final class AnonymousClass0KT extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;

    public AnonymousClass0KT() {
        super(Float.class);
    }

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r5) throws Exception {
        double parseDouble;
        if ("2.2250738585072012e-308".equals(str)) {
            parseDouble = Double.MIN_VALUE;
        } else {
            parseDouble = Double.parseDouble(str);
        }
        return Float.valueOf((float) parseDouble);
    }
}
