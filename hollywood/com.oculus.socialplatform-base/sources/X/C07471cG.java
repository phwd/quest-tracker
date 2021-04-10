package X;

import java.util.Arrays;
import java.util.List;

/* renamed from: X.1cG  reason: invalid class name and case insensitive filesystem */
public final class C07471cG<Data, ResourceType, Transcode> {
    public final AnonymousClass06o<List<Throwable>> A00;
    public final String A01;
    public final List<? extends AnonymousClass1dL<Data, ResourceType, Transcode>> A02;

    public final String toString() {
        StringBuilder sb = new StringBuilder("LoadPath{decodePaths=");
        sb.append(Arrays.toString(this.A02.toArray()));
        sb.append('}');
        return sb.toString();
    }

    public C07471cG(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<AnonymousClass1dL<Data, ResourceType, Transcode>> list, AnonymousClass06o<List<Throwable>> r8) {
        this.A00 = r8;
        if (!list.isEmpty()) {
            this.A02 = list;
            StringBuilder sb = new StringBuilder("Failed LoadPath{");
            sb.append(cls.getSimpleName());
            sb.append("->");
            sb.append(cls2.getSimpleName());
            sb.append("->");
            sb.append(cls3.getSimpleName());
            sb.append("}");
            this.A01 = sb.toString();
            return;
        }
        throw new IllegalArgumentException("Must not be empty.");
    }
}
