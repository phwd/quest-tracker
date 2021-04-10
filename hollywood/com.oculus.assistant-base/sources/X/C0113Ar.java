package X;

/* renamed from: X.Ar  reason: case insensitive filesystem */
public final class C0113Ar {
    public int A00;
    public long A01;
    public long A02;
    public long A03;

    public final String toString() {
        StringBuilder sb = new StringBuilder("AssistantMessageMetrics{mPostTimeInMs=");
        sb.append(this.A03);
        sb.append(", mBroadcastTimeInMs=");
        sb.append(this.A01);
        sb.append(", mNumSubscribers=");
        sb.append(this.A00);
        sb.append(", mElapsedTimeFromPostToBroadcast=");
        sb.append(this.A02);
        sb.append('}');
        return sb.toString();
    }
}
