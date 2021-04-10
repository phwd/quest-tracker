package X;

import android.util.Pair;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

public final class XY implements RU {
    public C0236Xf A00 = null;
    public final String A01;
    public final String A02;
    public final List<S1> A03 = new ArrayList();
    public final Set<C0139Ru> A04 = new HashSet();
    public final Set<Pair<Long, Ra>> A05 = new HashSet();

    @Override // X.RU
    public final void deleteOldUserData(int i) {
    }

    @Override // X.RU
    public final boolean isFetchNeeded() {
        return false;
    }

    @Override // X.RU
    public final boolean isValid() {
        return true;
    }

    @Override // X.RU
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return false;
    }

    @Override // X.RU
    public final RW getLatestHandle() {
        StringBuilder sb;
        String str;
        int i;
        if (this.A00 == null) {
            String str2 = this.A02;
            if (str2 == null || str2.isEmpty() || str2.equals("0")) {
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
            File[] listFiles = new File(sb.toString()).listFiles(new Ry(this));
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
                this.A00 = new C0236Xf(str3);
            }
        }
        return this.A00;
    }

    @Override // X.RU
    @Nullable
    public final Re getNewOverridesTableIfExists() {
        new File(AnonymousClass06.A04(this.A01, "mc_overrides.json"));
        return null;
    }

    @Override // X.RU
    public final void logExposure(String str, String str2, String str3) {
        this.A04.add(new C0139Ru(str, str2, str3));
    }

    @Override // X.RU
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
        List<S1> list = this.A03;
        synchronized (list) {
            list.add(new S1(str, str2, str3, str4, str5, str6));
        }
    }

    public XY(File file, String str) {
        this.A01 = AnonymousClass06.A04(file.getAbsolutePath(), "/mobileconfig/");
        this.A02 = str;
    }

    @Override // X.RU
    public final String syncFetchReason() {
        return "MobileConfigJavaManager: No sync fetch was needed";
    }

    @Override // X.RU
    public final boolean updateConfigs() {
        return false;
    }
}
