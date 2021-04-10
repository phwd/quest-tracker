package X;

/* renamed from: X.1yx  reason: invalid class name and case insensitive filesystem */
public class C11481yx implements AbstractC11041xS {
    public final /* synthetic */ C11281ya A00;

    public C11481yx(C11281ya r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC11041xS
    public final void A4p(byte[] bArr, int i, long j) {
        C11281ya r3 = this.A00;
        C11241xx r1 = r3.A05;
        if (r1 != null) {
            r1.A00();
            r3.A05 = null;
            r3.A0B.A5O(19, "recording_start_audio_finished");
        }
        if (r3.A0F) {
            r3.A02.A4p(bArr, i, j);
        }
    }
}
