package X;

import android.os.Bundle;
import java.io.File;
import javax.annotation.Nullable;
import retrofit.client.Defaults;

public final class H8 {
    public final int A00;
    public final File A01;
    public final Integer A02;
    @Nullable
    public final String A03;
    public final String A04;
    @Nullable
    public final String A05;
    @Nullable
    public final String A06;
    public final String A07;
    @Nullable
    public final String A08;
    public final String A09;
    public final boolean A0A;

    public final <T> T A00(H7<T> h7) {
        h7.A4N("uploader_class", this.A09);
        h7.A4N("flexible_sampling_updater", this.A06);
        h7.A4N("privacy_policy", this.A05);
        h7.A4N("thread_handler_factory", this.A07);
        h7.A4N("upload_job_instrumentation", this.A08);
        h7.A4N("priority_dir", this.A01.getAbsolutePath());
        h7.A4M("network_priority", this.A02.intValue());
        h7.A4N("marauder_tier", this.A04);
        h7.A4M("multi_batch_payload_size", this.A00);
        h7.A4M("non_sticky_handling", this.A0A ? 1 : 0);
        h7.A4N("batch_payload_iterator_factory", this.A03);
        return h7.A5P();
    }

    public H8(Bundle bundle) throws Gc {
        String str;
        C0253Yb yb = new C0253Yb(bundle);
        String A2t = yb.A2t("uploader_class", null);
        if (A2t != null) {
            String A2t2 = yb.A2t("flexible_sampling_updater", null);
            String A2t3 = yb.A2t("privacy_policy", null);
            String A2t4 = yb.A2t("thread_handler_factory", null);
            String A2t5 = yb.A2t("upload_job_instrumentation", null);
            String A2t6 = yb.A2t("priority_dir", null);
            if (A2t6 != null) {
                int A2X = yb.A2X("network_priority", 0);
                String A2t7 = yb.A2t("marauder_tier", null);
                if (A2t7 != null) {
                    int A2X2 = yb.A2X("multi_batch_payload_size", Defaults.READ_TIMEOUT_MILLIS);
                    this.A09 = A2t;
                    this.A06 = A2t2;
                    this.A05 = A2t3;
                    this.A07 = A2t4;
                    this.A08 = A2t5;
                    this.A01 = new File(A2t6);
                    this.A02 = AnonymousClass07.A00(2)[A2X];
                    this.A04 = A2t7;
                    this.A00 = A2X2;
                    this.A0A = yb.A2X("non_sticky_handling", 0) == 1;
                    this.A03 = yb.A2t("batch_payload_iterator_factory", null);
                    return;
                }
                str = "marauder_tier is null or empty";
            } else {
                str = "priority_dir is null or empty";
            }
        } else {
            str = "uploader_class is null or empty";
        }
        throw new Gc(str);
    }

    public H8(File file, G6 g6) {
        String str;
        Class<? extends AbstractC0090Hb> cls = g6.A00;
        if (cls != null) {
            this.A09 = cls.getName();
            this.A06 = null;
            this.A05 = null;
            this.A07 = g6.A01.getName();
            this.A08 = null;
            this.A01 = file;
            Integer num = g6.A02;
            if (num != null) {
                this.A02 = num;
                String str2 = g6.A03;
                if (str2 != null) {
                    this.A04 = str2;
                    this.A00 = Defaults.READ_TIMEOUT_MILLIS;
                    this.A0A = false;
                    this.A03 = null;
                    return;
                }
                str = "marauderTier required";
            } else {
                str = "networkPriority required";
            }
        } else {
            str = "uploader required";
        }
        throw new IllegalArgumentException(str);
    }
}
