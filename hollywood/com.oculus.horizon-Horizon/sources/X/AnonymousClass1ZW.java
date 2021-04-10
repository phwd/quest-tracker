package X;

import android.net.Uri;
import com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator;
import java.io.File;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.1ZW  reason: invalid class name */
public class AnonymousClass1ZW implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.mobileconfigservice.service.MobileConfigServiceImpl$1";
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass1ZV A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ String A03;
    public final /* synthetic */ String A04;
    public final /* synthetic */ String A05;

    public AnonymousClass1ZW(AnonymousClass1ZV r1, String str, int i, String str2, String str3, String str4) {
        this.A01 = r1;
        this.A02 = str;
        this.A00 = i;
        this.A04 = str2;
        this.A05 = str3;
        this.A03 = str4;
    }

    public final void run() {
        String A012;
        String num;
        String num2;
        try {
            AnonymousClass1ZX r8 = new AnonymousClass1ZX(this.A02, this.A00);
            AnonymousClass1ZV r3 = this.A01;
            String str = this.A04;
            String str2 = this.A05;
            MobileConfigAppAwareAccessorDecorator mobileConfigAppAwareAccessorDecorator = (MobileConfigAppAwareAccessorDecorator) ((AnonymousClass1aT) AnonymousClass0J2.A03(0, 348, r3.A00));
            synchronized (mobileConfigAppAwareAccessorDecorator) {
                if (str2 == null) {
                    str2 = r8.A01;
                }
                String str3 = r8.A01;
                MobileConfigAppAwareAccessorDecorator.A04(mobileConfigAppAwareAccessorDecorator, str3, str2, r8.A00);
                AnonymousClass1ao r6 = mobileConfigAppAwareAccessorDecorator.mAccessor;
                A012 = r6.A01(str3, str);
                if (A012.equals("NEEDS_REINIT")) {
                    synchronized (r6) {
                        File filesDir = r6.A03.getApplicationContext().getFilesDir();
                        if (AnonymousClass1aW.A08(filesDir, "")) {
                            num = Integer.toString(AnonymousClass1aW.A00(filesDir, ""));
                        } else {
                            num = Integer.toString(AnonymousClass1aW.A00(filesDir, "") + 1);
                        }
                        AnonymousClass0Rp r9 = r6.A08;
                        AnonymousClass0RX A25 = r9.A25("", num);
                        AnonymousClass1aV r2 = r6.A09;
                        A25.registerConfigChangeListener(r2);
                        AnonymousClass1ar r11 = r6.A05;
                        A25.registerConfigChangeListener(r11);
                        A25.saveCurrentParamsMapToDisk();
                        boolean A5A = r9.A5A();
                        AnonymousClass0RX r10 = null;
                        if (A5A) {
                            String userId = r9.getUserId();
                            if (AnonymousClass1aW.A08(filesDir, userId)) {
                                num2 = Integer.toString(AnonymousClass1aW.A00(filesDir, userId));
                            } else {
                                num2 = Integer.toString(AnonymousClass1aW.A00(filesDir, userId) + 1);
                            }
                            r10 = r9.A25(userId, num2);
                            r10.registerConfigChangeListener(r2);
                            r10.registerConfigChangeListener(r6.A04);
                        } else {
                            AnonymousClass0NO.A08("MobileConfigAccessor", "reInitializeManagers: device is not logged in, session-based manager not initialized!");
                        }
                        Map<String, AnonymousClass1am> A022 = AnonymousClass1am.A02(AnonymousClass1aW.A01(filesDir));
                        if (A022.isEmpty()) {
                            AnonymousClass0NO.A08("MobileConfigAccessor", "reInitializeManagers: failed to read params info from disk");
                            A012 = "params_map_parse_fail";
                        } else {
                            AtomicBoolean atomicBoolean = r6.A0C;
                            atomicBoolean.set(true);
                            AnonymousClass1ar.A03(r11);
                            r6.A07.A01(A25, r11);
                            AnonymousClass1aW.A05(filesDir, "");
                            if (A5A) {
                                AnonymousClass1ar r1 = r6.A04;
                                AnonymousClass1ar.A03(r1);
                                r6.A06.A01(r10, r1);
                                AnonymousClass1aW.A05(filesDir, r9.getUserId());
                            }
                            r6.A01 = A022;
                            atomicBoolean.set(false);
                            r9.A8T(new AnonymousClass1bW(r6));
                            A012 = "success";
                        }
                    }
                }
            }
            if (A012.equals("success")) {
                AnonymousClass1ZV.A00(r3, AnonymousClass1aU.A0B, this.A03);
                return;
            }
            AnonymousClass0NO.A0E("MobileConfigServiceImpl", "error while trying to subscribe: %s", A012);
            AnonymousClass1ZV.A00(r3, Uri.withAppendedPath(AnonymousClass1aU.A02, A012), this.A03);
        } catch (Exception e) {
            AnonymousClass0NO.A0H("MobileConfigServiceImpl", e, "exception while trying to subscribe");
            AnonymousClass1ZV.A00(this.A01, AnonymousClass1aU.A08, this.A03);
        }
    }
}
