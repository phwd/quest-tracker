package X;

import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_interfaces_MobileConfigServiceHelperInterface_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfigservice_listener_MobileConfigChangeRegistry_ULSEP_BINDING_ID"})
@ApplicationScoped
/* renamed from: X.1ao  reason: invalid class name */
public final class AnonymousClass1ao {
    public static volatile AnonymousClass1ao A0D;
    public AnonymousClass1an A00;
    public Map<String, AnonymousClass1am> A01 = new HashMap();
    public boolean A02 = true;
    public final Context A03;
    public final AnonymousClass1ar A04;
    public final AnonymousClass1ar A05;
    public final C09311at A06;
    public final C09311at A07;
    public final AnonymousClass0Rp A08;
    public final AnonymousClass1aV A09;
    public final Object A0A = new Object();
    public final Set<String> A0B = new HashSet();
    public final AtomicBoolean A0C = new AtomicBoolean(false);

    public final synchronized void A03() {
        if (this.A0C.get()) {
            AnonymousClass0NO.A09("MobileConfigAccessor", "Service currently upgrading");
        } else {
            AnonymousClass0NO.A09("MobileConfigAccessor", "trying to update sessionless configs");
            this.A07.updateConfigs();
            if (this.A08.A5A()) {
                AnonymousClass0NO.A09("MobileConfigAccessor", "trying to update sessionbased configs");
                this.A06.updateConfigs();
            } else {
                AnonymousClass0NO.A08("MobileConfigAccessor", "Not logged in yet cannot update sessionbased configs");
            }
        }
    }

    public final synchronized boolean A04(String str, boolean z, AnonymousClass0Rh r10) {
        AnonymousClass1ar r0;
        if (this.A0C.get()) {
            AnonymousClass0NO.A08("MobileConfigAccessor", "Service currently upgrading");
            if (r10.A02) {
                r10.A00 = AnonymousClass0Rj.DEFAULT__SERVICE_NOT_FOUND;
            }
        } else {
            AnonymousClass1am r4 = this.A01.get(str);
            if (r4 == null) {
                AnonymousClass0NO.A0E("MobileConfigAccessor", "Did not find config param %s", str);
                if (r10.A02) {
                    r10.A00 = AnonymousClass0Rj.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                }
            } else {
                boolean z2 = r4.A08;
                if (!z2 && !this.A08.A5A()) {
                    AnonymousClass0NO.A0E("MobileConfigAccessor", "Trying to get sessionbased value before login %s", str);
                }
                if (z2) {
                    r0 = this.A05;
                } else {
                    r0 = this.A04;
                }
                long A032 = r4.A03();
                AnonymousClass0Rg A062 = r0.A06(A032);
                if (this.A02) {
                    this.A00.A00(r4, A062);
                }
                return A062.A3B(A032, z, r10);
            }
        }
        return z;
    }

    public static void A00(AnonymousClass1ao r2, String str) {
        synchronized (r2.A0A) {
            r2.A0B.add(str);
        }
    }

    public final String A01(String str, String str2) {
        String str3;
        Object[] objArr;
        String str4;
        File filesDir = this.A03.getApplicationContext().getFilesDir();
        if (AnonymousClass1aW.A09(filesDir, str)) {
            try {
                String A002 = AnonymousClass1am.A00(AnonymousClass1aW.A04(AnonymousClass1aW.A02(filesDir, str)));
                String A003 = AnonymousClass1am.A00(str2.split("\n", 2)[0]);
                A00(this, str);
                if (A002.equals(A003) && AnonymousClass1aW.A06(filesDir)) {
                    return "success";
                }
            } catch (IOException e) {
                AnonymousClass0NO.A0B("MobileConfigAccessor", "Error reading params map on disk during saveParamsMap", e);
            }
        }
        if (AnonymousClass1am.A00(str2).isEmpty()) {
            str3 = "MobileConfigAccessor";
            objArr = new Object[]{str};
            str4 = "subscribe: got invalid params map content for %s";
        } else {
            new File(AnonymousClass006.A05(filesDir.getAbsolutePath(), "/mobileconfig/params_maps")).mkdirs();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(AnonymousClass1aW.A02(filesDir, str)));
                try {
                    fileOutputStream.write(str2.getBytes(), 0, str2.length());
                    fileOutputStream.close();
                    A00(this, str);
                    return "NEEDS_REINIT";
                } catch (Throwable unused) {
                }
            } catch (IOException e2) {
                AnonymousClass0NO.A0B("MobileConfigFilesOnDiskUtils", "IOException while trying to copy params map", e2);
                str3 = "MobileConfigAccessor";
                objArr = new Object[]{str};
                str4 = "subscribe: failed write paramsMap content to disk for %s";
            }
        }
        AnonymousClass0NO.A0E(str3, str4, objArr);
        return "failed_to_save";
        throw th;
    }

    public final synchronized List<String[]> A02(String[] strArr) {
        ArrayList arrayList;
        String str;
        String str2;
        Object[] objArr;
        AnonymousClass1ar r2;
        boolean z;
        long j;
        double d;
        arrayList = new ArrayList();
        if (this.A0C.get()) {
            AnonymousClass0NO.A09("MobileConfigAccessor", "Service currently upgrading");
        } else {
            int length = strArr.length;
            char c = 0;
            int i = 0;
            while (i < length) {
                String str3 = strArr[i];
                String[] split = str3.split(":", 3);
                String str4 = "";
                String str5 = str4;
                int length2 = split.length;
                if (length2 == 3) {
                    str4 = split[2];
                } else if (length2 != 2) {
                    str = "MobileConfigAccessor";
                    str2 = "Invalid query string received %s";
                    objArr = new Object[1];
                    objArr[c] = str3;
                    AnonymousClass0NO.A0E(str, str2, objArr);
                    i++;
                    c = 0;
                }
                String A072 = AnonymousClass006.A07(split[c], ":", split[1]);
                AnonymousClass1am r15 = this.A01.get(A072);
                if (r15 == null) {
                    Object[] objArr2 = new Object[1];
                    objArr2[c] = A072;
                    AnonymousClass0NO.A0E("MobileConfigAccessor", "Did not find config param %s", objArr2);
                } else {
                    boolean z2 = r15.A08;
                    if (!z2 && !this.A08.A5A()) {
                        Object[] objArr3 = new Object[1];
                        objArr3[c] = A072;
                        AnonymousClass0NO.A0E("MobileConfigAccessor", "Trying to get sessionbased value before login %s", objArr3);
                    }
                    int i2 = r15.A04;
                    if (z2) {
                        r2 = this.A05;
                    } else {
                        r2 = this.A04;
                    }
                    long A032 = r15.A03();
                    AnonymousClass0Rg A062 = r2.A06(A032);
                    if (!(A062 instanceof AnonymousClass1b2)) {
                        str = "MobileConfigAccessor";
                        str2 = "Couldn't find a valid context for %s";
                        objArr = new Object[]{A072};
                        AnonymousClass0NO.A0E(str, str2, objArr);
                    } else {
                        AnonymousClass1b2 r3 = (AnonymousClass1b2) A062;
                        if (this.A02) {
                            this.A00.A00(r15, r3);
                        }
                        AnonymousClass0Rh A002 = AnonymousClass0Rh.A00(new AnonymousClass0Rh());
                        A002.A01 = true;
                        AnonymousClass0Rh A003 = AnonymousClass0Rh.A00(A002);
                        A003.A03 = true;
                        AnonymousClass0Rh A012 = A003.A01();
                        if (i2 == 1) {
                            if (str4.isEmpty()) {
                                z = false;
                            } else {
                                z = Boolean.parseBoolean(str4);
                            }
                            str5 = Boolean.toString(r3.A3B(A032, z, A012));
                        } else if (i2 == 2) {
                            if (str4.isEmpty()) {
                                j = 0;
                            } else {
                                j = Long.parseLong(str4);
                            }
                            str5 = Long.toString(r3.A3m(A032, j, A012));
                        } else if (i2 == 3) {
                            str5 = r3.A4T(A032, str4, A012);
                        } else if (i2 == 4) {
                            if (str4.isEmpty()) {
                                d = OVRMediaServiceManager.SCREENSHOT_SHORTCUT_DELAY;
                            } else {
                                d = Double.parseDouble(str4);
                            }
                            str5 = Double.toString(r3.A3J(A032, d, A012));
                        }
                        String[] strArr2 = new String[AnonymousClass1b5.values().length];
                        strArr2[AnonymousClass1b5.CONFIG_PARAM_NAME.ordinal()] = A072;
                        strArr2[AnonymousClass1b5.TYPE.ordinal()] = Integer.toString(i2);
                        strArr2[AnonymousClass1b5.VALUE.ordinal()] = str5;
                        strArr2[AnonymousClass1b5.LOGGING_ID.ordinal()] = r3.A06(A032);
                        strArr2[AnonymousClass1b5.IS_SESSIONLESS.ordinal()] = Boolean.toString(z2);
                        strArr2[AnonymousClass1b5.QUERY_STRING.ordinal()] = str3;
                        strArr2[AnonymousClass1b5.SOURCE.ordinal()] = Integer.toString(A012.A00.getSource());
                        arrayList.add(strArr2);
                    }
                }
                i++;
                c = 0;
            }
        }
        return arrayList;
    }

    @Inject
    public AnonymousClass1ao(AnonymousClass0Rp r6, @ForAppContext Context context, AnonymousClass1aV r8) {
        this.A03 = context;
        this.A09 = r8;
        this.A08 = r6;
        this.A06 = new C09311at();
        this.A07 = new C09311at();
        AnonymousClass1Ae r2 = new AnonymousClass1Ae(this.A06);
        r2.A00.A01 = new AnonymousClass1bL(this);
        this.A04 = r2.A00();
        this.A05 = new AnonymousClass1Ae(this.A07).A00();
        this.A00 = new AnonymousClass1an(this.A06, this.A07);
        boolean A5A = this.A08.A5A();
        File filesDir = context.getApplicationContext().getFilesDir();
        if (AnonymousClass1aW.A09(filesDir, "") && AnonymousClass1aW.A06(filesDir)) {
            this.A07.A01(this.A08.A25("", Integer.toString(AnonymousClass1aW.A00(filesDir, ""))), this.A05);
            this.A07.registerConfigChangeListener(this.A09);
            this.A07.registerConfigChangeListener(this.A05);
            if (A5A) {
                String userId = this.A08.getUserId();
                this.A06.A01(this.A08.A25(userId, Integer.toString(AnonymousClass1aW.A00(filesDir, userId))), this.A04);
                this.A06.registerConfigChangeListener(this.A09);
                this.A06.registerConfigChangeListener(this.A04);
            }
            String A012 = AnonymousClass1aW.A01(filesDir);
            if (!A012.isEmpty()) {
                this.A01 = AnonymousClass1am.A02(A012);
            } else {
                AnonymousClass0NO.A08("MobileConfigAccessor", "Unable to construct paramsInfo at construction");
            }
        }
    }
}
