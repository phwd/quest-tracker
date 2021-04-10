package X;

import android.util.Pair;
import com.oculus.util.constants.OculusConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.12p  reason: invalid class name */
public final class AnonymousClass12p implements AnonymousClass0ST {
    public C02910b4 A00 = null;
    public final String A01;
    public final String A02;
    public final List<AnonymousClass13s> A03 = new ArrayList();
    public final Set<AnonymousClass15H> A04 = new HashSet();
    public final Set<Pair<Long, AnonymousClass0Sn>> A05 = new HashSet();

    @Override // X.AnonymousClass0ST
    public final void deleteOldUserData(int i) {
    }

    @Override // X.AnonymousClass0ST
    public final boolean isFetchNeeded() {
        return false;
    }

    @Override // X.AnonymousClass0ST
    public final boolean isValid() {
        return true;
    }

    @Override // X.AnonymousClass0ST
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return false;
    }

    @Override // X.AnonymousClass0ST
    public final AnonymousClass0SV getLatestHandle() {
        StringBuilder sb;
        String str;
        int i;
        if (this.A00 == null) {
            String str2 = this.A02;
            if (str2 == null || str2.isEmpty() || str2.equals(OculusConstants.DEFAULT_ENTERPRISE_USER_ID)) {
                sb = new StringBuilder();
                sb.append(this.A01);
                str = "sessionless.data/";
            } else {
                sb = new StringBuilder();
                sb.append(this.A01);
                sb.append(str2);
                str = ".data/";
            }
            sb.append(str);
            File[] listFiles = new File(sb.toString()).listFiles(new AnonymousClass13w(this));
            String str3 = "";
            if (listFiles != null) {
                int i2 = -1;
                for (File file : listFiles) {
                    String name = file.getName();
                    try {
                        i = Integer.parseInt(name.substring(0, name.length() - 8));
                    } catch (NumberFormatException unused) {
                        i = -1;
                    }
                    if (i > i2) {
                        str3 = file.getAbsolutePath();
                        i2 = i;
                    }
                }
            }
            if (!str3.isEmpty()) {
                this.A00 = new C02910b4(str3);
            }
        }
        return this.A00;
    }

    @Override // X.AnonymousClass0ST
    @Nullable
    public final AnonymousClass0Sr getNewOverridesTableIfExists() {
        new File(AnonymousClass006.A05(this.A01, "mc_overrides.json"));
        return null;
    }

    @Override // X.AnonymousClass0ST
    public final void logExposure(String str, String str2, String str3) {
        this.A04.add(new AnonymousClass15H(str, str2, str3));
    }

    @Override // X.AnonymousClass0ST
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
        List<AnonymousClass13s> list = this.A03;
        synchronized (list) {
            list.add(new AnonymousClass13s(str, str2, str3, str4, str5, str6));
        }
    }

    public AnonymousClass12p(File file, String str) {
        this.A01 = AnonymousClass006.A05(file.getAbsolutePath(), "/mobileconfig/");
        this.A02 = str;
    }

    @Override // X.AnonymousClass0ST
    public final String syncFetchReason() {
        return "MobileConfigJavaManager: No sync fetch was needed";
    }

    @Override // X.AnonymousClass0ST
    public final boolean updateConfigs() {
        return false;
    }
}
