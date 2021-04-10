package X;

import android.os.Bundle;
import java.io.File;
import javax.annotation.Nullable;
import retrofit.client.Defaults;

/* renamed from: X.0HX  reason: invalid class name */
public final class AnonymousClass0HX {
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

    public AnonymousClass0HX(Bundle bundle) throws C00860Gv {
        String str;
        AnonymousClass0q9 r3 = new AnonymousClass0q9(bundle);
        String A4R = r3.A4R("uploader_class", null);
        if (A4R != null) {
            String A4R2 = r3.A4R("flexible_sampling_updater", null);
            String A4R3 = r3.A4R("privacy_policy", null);
            String A4R4 = r3.A4R("thread_handler_factory", null);
            String A4R5 = r3.A4R("upload_job_instrumentation", null);
            String A4R6 = r3.A4R("priority_dir", null);
            if (A4R6 != null) {
                int A3e = r3.A3e("network_priority", 0);
                String A4R7 = r3.A4R("marauder_tier", null);
                if (A4R7 != null) {
                    int A3e2 = r3.A3e("multi_batch_payload_size", Defaults.READ_TIMEOUT_MILLIS);
                    this.A09 = A4R;
                    this.A06 = A4R2;
                    this.A05 = A4R3;
                    this.A07 = A4R4;
                    this.A08 = A4R5;
                    this.A01 = new File(A4R6);
                    this.A02 = AnonymousClass007.A00(2)[A3e];
                    this.A04 = A4R7;
                    this.A00 = A3e2;
                    this.A0A = r3.A3e("non_sticky_handling", 0) == 1;
                    this.A03 = r3.A4R("batch_payload_iterator_factory", null);
                    return;
                }
                str = "marauder_tier is null or empty";
            } else {
                str = "priority_dir is null or empty";
            }
        } else {
            str = "uploader_class is null or empty";
        }
        throw new C00860Gv(str);
    }
}
