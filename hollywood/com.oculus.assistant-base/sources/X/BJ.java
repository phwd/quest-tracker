package X;

import android.text.TextUtils;
import java.util.ArrayList;

public enum BJ {
    UNKNOWN(0),
    ALOHA_AND_ALIKE(1),
    MESSENGER(2),
    MESSENGER_M(3),
    MESSENGER_M_INVOKED(4),
    EXPERIMENTAL(5),
    WORKCHAT_MEMORY(6),
    OCULUS(7),
    WORK_ASSIST(8),
    BLUE(9),
    STELLA(10),
    SPARK_TOOLS(11),
    MILAN(12);
    
    public final short mValue;

    public static String[] names() {
        ArrayList arrayList = new ArrayList();
        for (BJ bj : values()) {
            arrayList.add(bj.name());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* access modifiers changed from: public */
    BJ(int i) {
        this.mValue = (short) i;
    }

    public static BJ valueOrDefault(String str, BJ bj) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return valueOf(str);
            }
            return bj;
        } catch (IllegalArgumentException unused) {
            return bj;
        }
    }

    public short value() {
        return this.mValue;
    }
}
