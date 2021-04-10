package defpackage;

/* renamed from: Sk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1126Sk extends C1309Vk {

    /* renamed from: J  reason: collision with root package name */
    public final int f8910J;
    public final int K;

    public C1126Sk(byte[] bArr, int i, int i2) {
        super(bArr);
        AbstractC1248Uk.b(i, i + i2, bArr.length);
        this.f8910J = i;
        this.K = i2;
    }

    @Override // defpackage.C1309Vk, defpackage.AbstractC1248Uk
    public byte a(int i) {
        int i2 = this.K;
        if (((i2 - (i + 1)) | i) >= 0) {
            return this.I[this.f8910J + i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(AbstractC2531fV.s(22, "Index < 0: ", i));
        }
        throw new ArrayIndexOutOfBoundsException(AbstractC2531fV.t(40, "Index > length: ", i, ", ", i2));
    }

    @Override // defpackage.C1309Vk, defpackage.AbstractC1248Uk
    public void e(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.I, this.f8910J + i, bArr, i2, i3);
    }

    @Override // defpackage.C1309Vk, defpackage.AbstractC1248Uk
    public byte f(int i) {
        return this.I[this.f8910J + i];
    }

    @Override // defpackage.C1309Vk
    public int o() {
        return this.f8910J;
    }

    @Override // defpackage.C1309Vk, defpackage.AbstractC1248Uk
    public int size() {
        return this.K;
    }
}
