package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: eX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2364eX extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2535fX f9856a;
    public final Activity b;
    public final String c;

    public C2364eX(C2535fX fXVar, Activity activity, String str) {
        this.f9856a = fXVar;
        this.b = activity;
        this.c = str;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2535fX fXVar = this.f9856a;
        Activity activity = this.b;
        RunnableC0165Cr cr = (RunnableC0165Cr) obj;
        Objects.requireNonNull(fXVar);
        StringBuilder sb = new StringBuilder();
        sb.append("Feedback data: ");
        Objects.requireNonNull(cr);
        Object obj2 = ThreadUtils.f10596a;
        cr.K = null;
        Bundle bundle = new Bundle();
        C4569rP rPVar = new C4569rP(bundle);
        AbstractC0417Gv.a(cr.H, rPVar);
        AbstractC0417Gv.a(cr.I, rPVar);
        sb.append(bundle);
        sb.toString();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://support.google.com/chrome/topic/6069782"));
        intent.putExtra("com.android.browser.application_id", activity.getPackageName());
        intent.putExtra("create_new_tab", true);
        intent.setPackage(activity.getPackageName());
        activity.startActivity(intent);
    }
}
