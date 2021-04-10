package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

/* renamed from: cZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2028cZ0 extends ContextWrapper {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ClassLoader f9614a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2028cZ0(Context context, ClassLoader classLoader) {
        super(context);
        this.f9614a = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.f9614a;
    }

    @Override // android.content.Context, android.content.ContextWrapper
    public Object getSystemService(String str) {
        Object systemService = super.getSystemService(str);
        return "layout_inflater".equals(str) ? ((LayoutInflater) systemService).cloneInContext(this) : systemService;
    }
}
