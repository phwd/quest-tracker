package defpackage;

import android.util.Property;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: fl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2576fl1 extends Property {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ToolbarPhone f9947a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2576fl1(ToolbarPhone toolbarPhone, Class cls, String str) {
        super(cls, str);
        this.f9947a = toolbarPhone;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((ToolbarPhone) obj).h1);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        ToolbarPhone toolbarPhone = (ToolbarPhone) obj;
        this.f9947a.h1 = ((Float) obj2).floatValue();
        this.f9947a.s0();
    }
}
