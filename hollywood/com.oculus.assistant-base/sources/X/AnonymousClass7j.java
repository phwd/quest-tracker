package X;

import java.io.File;

/* renamed from: X.7j  reason: invalid class name */
public final class AnonymousClass7j {
    public final int A00;
    public final File A01;
    public final Integer A02;
    public final String A03;
    public final String A04;
    public final String A05;
    public final String A06;
    public final String A07;
    public final String A08;
    public final String A09;
    public final boolean A0A;

    public final Object A00(AnonymousClass7i r3) {
        r3.A4a("uploader_class", this.A09);
        r3.A4a("flexible_sampling_updater", this.A06);
        r3.A4a("privacy_policy", this.A05);
        r3.A4a("thread_handler_factory", this.A07);
        r3.A4a("upload_job_instrumentation", this.A08);
        r3.A4a("priority_dir", this.A01.getAbsolutePath());
        r3.A4Z("network_priority", this.A02.intValue());
        r3.A4a("marauder_tier", this.A04);
        r3.A4Z("multi_batch_payload_size", this.A00);
        r3.A4Z("non_sticky_handling", this.A0A ? 1 : 0);
        r3.A4a("batch_payload_iterator_factory", this.A03);
        return r3.A59();
    }

    public AnonymousClass7j(AnonymousClass7h r12) {
        String A2x = r12.A2x("uploader_class", null);
        if (A2x != null) {
            String A2x2 = r12.A2x("flexible_sampling_updater", null);
            String A2x3 = r12.A2x("privacy_policy", null);
            String A2x4 = r12.A2x("thread_handler_factory", null);
            String A2x5 = r12.A2x("upload_job_instrumentation", null);
            String A2x6 = r12.A2x("priority_dir", null);
            if (A2x6 != null) {
                int A2U = r12.A2U("network_priority", 0);
                String A2x7 = r12.A2x("marauder_tier", null);
                if (A2x7 != null) {
                    int A2U2 = r12.A2U("multi_batch_payload_size", 20000);
                    this.A09 = A2x;
                    this.A06 = A2x2;
                    this.A05 = A2x3;
                    this.A07 = A2x4;
                    this.A08 = A2x5;
                    this.A01 = new File(A2x6);
                    this.A02 = AnonymousClass09.A00(2)[A2U];
                    this.A04 = A2x7;
                    this.A00 = A2U2;
                    this.A0A = r12.A2U("non_sticky_handling", 0) == 1;
                    this.A03 = r12.A2x("batch_payload_iterator_factory", null);
                    return;
                }
                throw new AnonymousClass7D("marauder_tier is null or empty");
            }
            throw new AnonymousClass7D("priority_dir is null or empty");
        }
        throw new AnonymousClass7D("uploader_class is null or empty");
    }
}
