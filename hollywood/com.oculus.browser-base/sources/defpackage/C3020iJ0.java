package defpackage;

import android.hardware.Camera;

/* renamed from: iJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3020iJ0 implements Camera.ErrorCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3361kJ0 f10129a;

    public C3020iJ0(C3361kJ0 kj0) {
        this.f10129a = kj0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onError(int r3, android.hardware.Camera r4) {
        /*
            r2 = this;
            r4 = 2
            if (r3 == r4) goto L_0x0016
            r4 = 100
            if (r3 == r4) goto L_0x0016
            switch(r3) {
                case 1000: goto L_0x0012;
                case 1001: goto L_0x000e;
                case 1002: goto L_0x0016;
                default: goto L_0x000a;
            }
        L_0x000a:
            r3 = 2131953288(0x7f130688, float:1.9543043E38)
            goto L_0x0019
        L_0x000e:
            r3 = 2131953283(0x7f130683, float:1.9543033E38)
            goto L_0x0019
        L_0x0012:
            r3 = 2131953290(0x7f13068a, float:1.9543047E38)
            goto L_0x0019
        L_0x0016:
            r3 = 2131953289(0x7f130689, float:1.9543045E38)
        L_0x0019:
            kJ0 r4 = r2.f10129a
            cm r4 = r4.g
            if (r4 == 0) goto L_0x0027
            r4.b()
            kJ0 r4 = r2.f10129a
            r0 = 0
            r4.g = r0
        L_0x0027:
            kJ0 r4 = r2.f10129a
            android.content.Context r4 = r4.f10272a
            android.content.res.Resources r4 = r4.getResources()
            java.lang.String r3 = r4.getString(r3)
            kJ0 r4 = r2.f10129a
            android.view.View r0 = r4.i
            r1 = 2131428487(0x7f0b0487, float:1.847862E38)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r0.setText(r3)
            android.widget.FrameLayout r3 = r4.b
            r3.removeAllViews()
            android.widget.FrameLayout r3 = r4.b
            android.view.View r4 = r4.i
            r3.addView(r4)
            return
            switch-data {1000->0x0012, 1001->0x000e, 1002->0x0016, }
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3020iJ0.onError(int, android.hardware.Camera):void");
    }
}
