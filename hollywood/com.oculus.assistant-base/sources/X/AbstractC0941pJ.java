package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.pJ  reason: case insensitive filesystem */
public abstract class AbstractC0941pJ {
    public final boolean A00(Intent intent, Context context) {
        if (!(this instanceof YA)) {
            throw new UnsupportedOperationException();
        }
        YA ya = (YA) this;
        AbstractC0943pL pLVar = ya.A00;
        if (pLVar instanceof X2) {
            intent = X2.A00((X2) pLVar, intent, context, null, AbstractC0943pL.A04(intent, context));
        } else if (!(pLVar instanceof X3)) {
            X8 x8 = (X8) pLVar;
            List A04 = AbstractC0943pL.A04(intent, context);
            Iterator it = A04.iterator();
            boolean z = false;
            while (it.hasNext()) {
                ActivityInfo activityInfo = (ActivityInfo) it.next();
                if (!X8.A02(x8, intent, context, activityInfo, activityInfo.permission)) {
                    z = true;
                    it.remove();
                }
            }
            intent = X8.A01(x8, intent, A04, z);
        } else {
            X3 x3 = (X3) pLVar;
            C0199Jm.A02(intent, context, null, x3.A00);
            if (intent.getComponent() == null || !intent.getComponent().getPackageName().equals(context.getPackageName())) {
                intent = X3.A00(x3, intent, context, AbstractC0943pL.A04(intent, context));
            }
        }
        if (intent == null) {
            return false;
        }
        if (context != null) {
            List list = ya.A01;
            if (!list.isEmpty()) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    it2.next();
                }
            }
        }
        context.startActivity(intent);
        return true;
    }
}
