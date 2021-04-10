package defpackage;

import android.util.Property;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: cl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2064cl1 extends Property {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ToolbarPhone f9628a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2064cl1(ToolbarPhone toolbarPhone, Class cls, String str) {
        super(cls, str);
        this.f9628a = toolbarPhone;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((ToolbarPhone) obj).v0);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        ToolbarPhone toolbarPhone = (ToolbarPhone) obj;
        ToolbarPhone toolbarPhone2 = this.f9628a;
        toolbarPhone2.v0 = ((Float) obj2).floatValue();
        toolbarPhone2.B0();
        toolbarPhone2.A0();
    }
}
