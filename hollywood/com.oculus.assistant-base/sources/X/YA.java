package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import java.util.Iterator;
import java.util.List;

public final class YA extends AbstractC0941pJ {
    public final AbstractC0943pL A00;
    public final List A01;

    public final void A01(Intent intent, Context context) {
        AbstractC0943pL pLVar = this.A00;
        if (pLVar instanceof X2) {
            intent = X2.A00((X2) pLVar, intent, context, null, AbstractC0943pL.A05(intent, context));
        } else if (!(pLVar instanceof X3)) {
            X8 x8 = (X8) pLVar;
            List A05 = AbstractC0943pL.A05(intent, context);
            Iterator it = A05.iterator();
            boolean z = false;
            while (it.hasNext()) {
                ServiceInfo serviceInfo = (ServiceInfo) it.next();
                if (!X8.A02(x8, intent, context, serviceInfo, serviceInfo.permission)) {
                    z = true;
                    it.remove();
                }
            }
            intent = X8.A01(x8, intent, A05, z);
        } else {
            X3 x3 = (X3) pLVar;
            C0199Jm.A02(intent, context, null, x3.A00);
            if (intent.getComponent() == null || !intent.getComponent().getPackageName().equals(context.getPackageName())) {
                intent = X3.A00(x3, intent, context, AbstractC0943pL.A05(intent, context));
            }
        }
        if (intent != null) {
            context.startService(intent);
        }
    }

    public YA(AbstractC0943pL pLVar, List list) {
        this.A00 = pLVar;
        this.A01 = list;
    }
}
