package defpackage;

import android.content.Context;
import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* renamed from: js0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3279js0 extends AbstractC2425es0 implements AbstractC0865Oe {
    public static final Object b = new Object();
    public static C3279js0 c;
    public AbstractC2032cb d;

    public C3279js0() {
        super(new C3108is0(ContextUtils.getApplicationContext()));
    }

    public static boolean f(Context context, long j) {
        long max = Math.max(0L, j);
        return AbstractC2898hf.b().b(context, TaskInfo.a(71300, max, max).a());
    }

    @Override // defpackage.AbstractC0865Oe
    public boolean a(Context context, C2046cf1 cf1) {
        AbstractC2032cb cbVar = this.d;
        if (cbVar != null) {
            cbVar.b(false);
            this.d = null;
        }
        return false;
    }

    @Override // defpackage.AbstractC0865Oe
    public boolean b(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        C2938hs0 hs0 = new C2938hs0(this, ne);
        hs0.d(AbstractC2032cb.b);
        this.d = hs0;
        return false;
    }

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
    }

    public C3279js0(Context context) {
        super(new C3108is0(context));
    }
}
