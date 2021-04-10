package defpackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: dj1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2229dj1 {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f9801a;
    public String b;
    public List c;

    public C2229dj1(byte[] bArr) {
        this.f9801a = bArr;
    }

    public static C2229dj1 a(String str, List list) {
        Collections.sort(list, new C2058cj1());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(str);
        dataOutputStream.writeInt(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            byte[] bArr = (byte[]) it.next();
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
        }
        dataOutputStream.flush();
        return new C2229dj1(byteArrayOutputStream.toByteArray(), str, list);
    }

    public final void b() {
        if (this.b == null) {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(this.f9801a));
            this.b = dataInputStream.readUTF();
            int readInt = dataInputStream.readInt();
            this.c = new ArrayList(readInt);
            for (int i = 0; i < readInt; i++) {
                int readInt2 = dataInputStream.readInt();
                byte[] bArr = new byte[readInt2];
                if (dataInputStream.read(bArr) == readInt2) {
                    this.c.add(bArr);
                } else {
                    throw new IllegalStateException("Could not read fingerprint");
                }
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2229dj1.class != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.f9801a, ((C2229dj1) obj).f9801a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f9801a);
    }

    public C2229dj1(byte[] bArr, String str, List list) {
        this.f9801a = bArr;
        this.b = str;
        this.c = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            byte[] bArr2 = (byte[]) it.next();
            this.c.add(Arrays.copyOf(bArr2, bArr2.length));
        }
    }
}
