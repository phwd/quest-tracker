package defpackage;

import android.app.Activity;
import android.graphics.Bitmap;
import com.oculus.browser.R;
import org.chromium.chrome.browser.share.screenshot.EditorScreenshotTask;
import org.chromium.chrome.browser.share.screenshot.ScreenshotShareSheetDialog;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: gP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2691gP0 {

    /* renamed from: a  reason: collision with root package name */
    public static int f9996a;
    public final Activity b;
    public final Tab c;
    public final ScreenshotShareSheetDialog d;
    public final View$OnLayoutChangeListenerC5940zU0 e;
    public final HZ f;
    public EditorScreenshotTask g;
    public Bitmap h;

    public C2691gP0(Activity activity, Tab tab, View$OnLayoutChangeListenerC5940zU0 zu0, AbstractC4448qj qjVar, HZ hz) {
        EditorScreenshotTask editorScreenshotTask = new EditorScreenshotTask(activity, qjVar);
        ScreenshotShareSheetDialog screenshotShareSheetDialog = new ScreenshotShareSheetDialog();
        this.b = activity;
        this.c = tab;
        this.d = screenshotShareSheetDialog;
        this.e = zu0;
        this.g = editorScreenshotTask;
        this.f = hz;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
        // Method dump skipped, instructions count: 139
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2691gP0.a():void");
    }

    public final void b(boolean z, Runnable runnable) {
        C5305vl0 vl0 = new C5305vl0(this.c, R.string.f53010_resource_name_obfuscated_RES_2131952618, new C2520fP0(this, z, runnable));
        vl0.b();
        this.f.b(new C2349eP0(this, vl0, runnable, z));
    }

    public final void c() {
        this.f.c().a(this.b, this.h, this.c, this.e);
        this.h = null;
    }

    public void d() {
        Activity activity = this.b;
        ScreenshotShareSheetDialog screenshotShareSheetDialog = this.d;
        Bitmap bitmap = this.h;
        Tab tab = this.c;
        View$OnLayoutChangeListenerC5940zU0 zu0 = this.e;
        C2179dP0 dp0 = new C2179dP0(this);
        KS Y = ((AbstractActivityC3892nS) activity).Y();
        screenshotShareSheetDialog.N0 = bitmap;
        screenshotShareSheetDialog.Q0 = dp0;
        screenshotShareSheetDialog.O0 = tab;
        screenshotShareSheetDialog.P0 = zu0;
        screenshotShareSheetDialog.k1(Y, null);
        this.h = null;
    }
}
