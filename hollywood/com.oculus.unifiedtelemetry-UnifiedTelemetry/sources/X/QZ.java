package X;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public final class QZ extends RuntimeException {
    public static String A00(@Nullable String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "Failure to provision.";
        }
        sb.append(str);
        sb.append("\n");
        List<Object> list = QY.A00.get();
        ArrayList<QW> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i += 2) {
            arrayList.add(new QW((QX) list.get(i), (C0475qE) list.get(i + 1)));
        }
        Collections.reverse(arrayList);
        for (QW qw : arrayList) {
            QX qx = qw.A00;
            if (qx == QX.INSTANCE_GET) {
                str2 = " while trying to get instance of ";
            } else if (qx == QX.INJECT_COMPONENT) {
                str2 = " while trying to inject component of ";
            } else {
                str2 = " while trying to get provider of ";
            }
            sb.append(str2);
            sb.append(qw.A01);
            sb.append("\n");
        }
        sb.append("If this is an instrumentation/screenshot test then you likely need to pass the ");
        sb.append("relevant DI module to the test rule, see https://fburl.com/wiki/24nviijj for ");
        sb.append("more details.\n");
        return sb.toString();
    }

    public QZ() {
        super(A00(null));
    }

    public QZ(String str) {
        super(A00(str));
    }
}
