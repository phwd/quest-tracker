package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.speech.SpeechRecognizer;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.components.policy.CombinedPolicyProvider;
import org.chromium.content.browser.SpeechRecognitionImpl;

/* renamed from: Mq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0772Mq implements Runnable {
    public final C1321Vq F;
    public final AbstractC3083ik G;

    public RunnableC0772Mq(C1321Vq vq, AbstractC3083ik ikVar) {
        this.F = vq;
        this.G = ikVar;
    }

    public void run() {
        C1321Vq vq = this.F;
        AbstractC3083ik ikVar = this.G;
        Objects.requireNonNull(vq);
        ikVar.I();
        Object obj = ThreadUtils.f10596a;
        if (!vq.g) {
            AppHooks appHooks = AppHooks.get();
            CombinedPolicyProvider a2 = CombinedPolicyProvider.a();
            Objects.requireNonNull(appHooks);
            K9 k9 = new K9(ContextUtils.getApplicationContext());
            a2.d.add(k9);
            a2.e.add(null);
            k9.b = a2.d.size() - 1;
            k9.f9891a = a2;
            k9.c.registerReceiver(k9.d, new IntentFilter("android.intent.action.APPLICATION_RESTRICTIONS_CHANGED"), null, new Handler(ThreadUtils.c()));
            if (a2.b != 0) {
                k9.b();
            }
            Context applicationContext = ContextUtils.getApplicationContext();
            if (SpeechRecognizer.isRecognitionAvailable(applicationContext)) {
                for (ResolveInfo resolveInfo : applicationContext.getPackageManager().queryIntentServices(new Intent("android.speech.RecognitionService"), 4)) {
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    if (serviceInfo.packageName.equals("com.google.android.googlequicksearchbox") && AbstractC4652ru0.a(applicationContext, serviceInfo.packageName) >= 300207030) {
                        SpeechRecognitionImpl.f10917a = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                        return;
                    }
                }
            }
        }
    }
}
