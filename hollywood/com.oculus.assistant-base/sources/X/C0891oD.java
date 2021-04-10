package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: X.oD  reason: case insensitive filesystem */
public final class C0891oD implements AbstractC0162Fh {
    public C0888o7 A00 = null;
    public final String A01;
    public final String A02;
    public final List A03 = new ArrayList();
    public final Set A04 = new HashSet();
    public final Set A05 = new HashSet();

    @Override // X.AbstractC0162Fh
    public final AbstractC0168Ft getNewOverridesTable() {
        return null;
    }

    @Override // X.AbstractC0162Fh
    public final boolean isFetchNeeded() {
        return false;
    }

    @Override // X.AbstractC0162Fh
    public final boolean isValid() {
        return true;
    }

    @Override // X.AbstractC0162Fh
    public final boolean tryUpdateConfigsSynchronously(int i) {
        return false;
    }

    @Override // X.AbstractC0162Fh
    public final AbstractC0163Fj getLatestHandle() {
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
            File[] listFiles = new File(sb.toString()).listFiles(new G7(this));
            String str3 = OacrConstants.AUTO_SPEECH_DOMAIN;
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
                this.A00 = new C0888o7(str3);
            }
        }
        return this.A00;
    }

    @Override // X.AbstractC0162Fh
    public final AbstractC0168Ft getNewOverridesTableIfExists() {
        new File(AnonymousClass08.A04(this.A01, "mc_overrides.json"));
        return null;
    }

    @Override // X.AbstractC0162Fh
    public final void logExposure(String str, String str2, String str3) {
        this.A04.add(new G4(str, str2, str3));
    }

    @Override // X.AbstractC0162Fh
    public final void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
        List list = this.A03;
        synchronized (list) {
            list.add(new G8(str, str2, str3, str4, str5, str6));
        }
    }

    public C0891oD(File file, String str) {
        this.A01 = AnonymousClass08.A04(file.getAbsolutePath(), "/mobileconfig/");
        this.A02 = str;
    }

    @Override // X.AbstractC0162Fh
    public final String syncFetchReason() {
        return "MobileConfigJavaManager: No sync fetch was needed";
    }
}
