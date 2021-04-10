package X;

import java.io.Serializable;

/* renamed from: X.yh  reason: case insensitive filesystem */
public abstract class AbstractC1403yh implements Serializable, AbstractC0513bA {
    public final int arity;

    public AbstractC1403yh(int i) {
        this.arity = i;
    }

    public final String toString() {
        String obj = getClass().getGenericInterfaces()[0].toString();
        if (obj.startsWith("kotlin.jvm.functions.")) {
            obj = obj.substring(21);
        }
        C0514bB.A01(obj, "Reflection.renderLambdaToString(this)");
        return obj;
    }
}
