package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Date;

@JacksonStdImpl
/* renamed from: X.Cd  reason: case insensitive filesystem */
public final class C0070Cd extends WN {
    public static final long serialVersionUID = 1;

    public C0070Cd() {
        super(Date.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws IllegalArgumentException, C0223Wj {
        return wn.A0I(str);
    }
}
