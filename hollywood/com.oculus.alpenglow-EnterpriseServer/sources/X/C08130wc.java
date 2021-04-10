package X;

import android.content.pm.PackageInfo;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0wc  reason: invalid class name and case insensitive filesystem */
public final class C08130wc {
    public final String A00;
    @Nullable
    public volatile PackageInfo A01;
    public volatile Integer A02 = AnonymousClass007.A00;

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("FbnsPackageInfo{mPackageName='");
        sb.append(this.A00);
        sb.append('\'');
        sb.append(", mPackageStatus=");
        Integer num = this.A02;
        if (num != null) {
            switch (num.intValue()) {
                case 1:
                    str = "NOT_INSTALLED";
                    break;
                case 2:
                    str = "DISABLED";
                    break;
                case 3:
                    str = "UNSUPPORTED";
                    break;
                case 4:
                    str = "INSTALLED";
                    break;
                case 5:
                    str = "NOT_TRUSTED";
                    break;
                case 6:
                    str = "TRUSTED";
                    break;
                default:
                    str = "UNKNOWN";
                    break;
            }
        } else {
            str = "null";
        }
        sb.append(str);
        sb.append(", mPackageInfo=");
        sb.append(this.A01);
        sb.append('}');
        return sb.toString();
    }

    public C08130wc(String str) {
        this.A00 = str;
    }
}
