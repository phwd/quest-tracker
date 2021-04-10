package defpackage;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: hb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2888hb1 extends AbstractC0500Ie {
    public final /* synthetic */ String i;
    public final /* synthetic */ C4766sb1 j;

    public C2888hb1(C4766sb1 sb1, String str) {
        this.j = sb1;
        this.i = str;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Throwable th;
        FileInputStream fileInputStream;
        IOException e;
        StringBuilder i2 = AbstractC2531fV.i("Starting to fetch tab list for ");
        i2.append(this.i);
        AbstractC1220Ua0.d("tabmodel", i2.toString(), new Object[0]);
        File file = new File(this.j.b(), this.i);
        FileInputStream fileInputStream2 = null;
        if (!file.exists()) {
            AbstractC1220Ua0.d("tabmodel", "State file does not exist.", new Object[0]);
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[((int) file.length())];
                fileInputStream.read(bArr);
                O21.a(fileInputStream);
                AbstractC1220Ua0.d("tabmodel", "Finished fetching tab list.", new Object[0]);
                return new DataInputStream(new ByteArrayInputStream(bArr));
            } catch (IOException e2) {
                e = e2;
                try {
                    AbstractC1220Ua0.a("tabmodel", "Could not read state file.", e);
                    O21.a(fileInputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    O21.a(fileInputStream2);
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            fileInputStream = null;
            AbstractC1220Ua0.a("tabmodel", "Could not read state file.", e);
            O21.a(fileInputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            O21.a(fileInputStream2);
            throw th;
        }
    }
}
