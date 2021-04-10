package X;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0Rb  reason: invalid class name */
public final class AnonymousClass0Rb extends RuntimeException {
    public static String A00(@Nullable String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "Failure to provision.";
        }
        sb.append(str);
        sb.append("\n");
        List<Object> list = AnonymousClass0Ra.A00.get();
        ArrayList<AnonymousClass0RY> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i += 2) {
            arrayList.add(new AnonymousClass0RY((AnonymousClass0RZ) list.get(i), (AnonymousClass14P) list.get(i + 1)));
        }
        Collections.reverse(arrayList);
        for (AnonymousClass0RY r2 : arrayList) {
            AnonymousClass0RZ r1 = r2.A00;
            if (r1 == AnonymousClass0RZ.INSTANCE_GET) {
                str2 = " while trying to get instance of ";
            } else if (r1 == AnonymousClass0RZ.INJECT_COMPONENT) {
                str2 = " while trying to inject component of ";
            } else {
                str2 = " while trying to get provider of ";
            }
            sb.append(str2);
            sb.append(r2.A01);
            sb.append("\n");
        }
        sb.append("If this is an instrumentation/screenshot test then you likely need to pass the ");
        sb.append("relevant DI module to the test rule, see https://fburl.com/wiki/24nviijj for ");
        sb.append("more details.\n");
        return sb.toString();
    }

    public AnonymousClass0Rb() {
        super(A00(null));
    }

    public AnonymousClass0Rb(String str) {
        super(A00(str));
    }
}
