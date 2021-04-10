package X;

import java.util.List;

/* renamed from: X.1dL  reason: invalid class name */
public final class AnonymousClass1dL<DataType, ResourceType, Transcode> {
    public final AnonymousClass06o<List<Throwable>> A00;
    public final AbstractC08801fP<ResourceType, Transcode> A01;
    public final String A02;
    public final List<? extends AnonymousClass1dN<DataType, ResourceType>> A03;
    public final Class<DataType> A04;

    public final String toString() {
        StringBuilder sb = new StringBuilder("DecodePath{ dataClass=");
        sb.append(this.A04);
        sb.append(", decoders=");
        sb.append(this.A03);
        sb.append(", transcoder=");
        sb.append(this.A01);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass1dL(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends AnonymousClass1dN<DataType, ResourceType>> list, AbstractC08801fP<ResourceType, Transcode> r8, AnonymousClass06o<List<Throwable>> r9) {
        this.A04 = cls;
        this.A03 = list;
        this.A01 = r8;
        this.A00 = r9;
        StringBuilder sb = new StringBuilder("Failed DecodePath{");
        sb.append(cls.getSimpleName());
        sb.append("->");
        sb.append(cls2.getSimpleName());
        sb.append("->");
        sb.append(cls3.getSimpleName());
        sb.append("}");
        this.A02 = sb.toString();
    }
}
