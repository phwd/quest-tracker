package defpackage;

import android.util.Property;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: el1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2405el1 extends Property {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ToolbarPhone f9876a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2405el1(ToolbarPhone toolbarPhone, Class cls, String str) {
        super(cls, str);
        this.f9876a = toolbarPhone;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((ToolbarPhone) obj).s0);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        ((ToolbarPhone) obj).s0 = ((Float) obj2).floatValue();
        ToolbarPhone toolbarPhone = this.f9876a;
        toolbarPhone.F.onResult(new RunnableC2235dl1(toolbarPhone));
    }
}
