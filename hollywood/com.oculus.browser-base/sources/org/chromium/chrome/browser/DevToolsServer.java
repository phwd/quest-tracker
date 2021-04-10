package org.chromium.chrome.browser;

import J.N;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DevToolsServer {

    /* renamed from: a  reason: collision with root package name */
    public long f10602a;

    public DevToolsServer(String str) {
        this.f10602a = N.M7TBtHQi(this, str);
    }

    public static boolean checkDebugPermission(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(ContextUtils.getApplicationContext().getPackageName());
        sb.append(".permission.DEBUG");
        return AbstractC3153j7.a(ContextUtils.getApplicationContext(), sb.toString(), i, i2) == 0;
    }
}
