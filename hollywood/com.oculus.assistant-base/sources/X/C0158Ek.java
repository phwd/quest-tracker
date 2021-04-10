package X;

import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: X.Ek  reason: case insensitive filesystem */
public final class C0158Ek {
    public final C0855ko A00;
    public final HashMap A01 = new HashMap(64);

    public final synchronized En A00(String str) {
        En en;
        String[] list;
        HashMap hashMap = this.A01;
        en = (En) hashMap.get(str);
        if (en == null) {
            try {
                C0855ko koVar = this.A00;
                AbstractC0223Ku A002 = SystraceMessage.A00("MetadataDeserializer.readEntireNamespace");
                A002.A00("firstTypeClassName", str);
                A002.A01();
                try {
                    String parent = new File(str.replace(".", "/")).getParent();
                    if (parent == null) {
                        list = new String[0];
                    } else {
                        list = koVar.A00.list(parent);
                    }
                    int i = 0;
                    int i2 = 0;
                    for (String str2 : list) {
                        if (str2.endsWith(".meta")) {
                            AbstractC0223Ku A003 = SystraceMessage.A00("MetadataDeserializer.parse");
                            A003.A00("structGroup", str2);
                            A003.A01();
                            try {
                                Ej ej = new Ej(new DataInputStream(koVar.A00.open(AnonymousClass08.A05(parent, "/", str2))), hashMap);
                                i2++;
                                try {
                                    DataInputStream dataInputStream = ej.A02;
                                    ej.A00 = dataInputStream.readBoolean();
                                    int readShort = dataInputStream.readShort();
                                    ej.A01 = new String[readShort];
                                    for (int i3 = 0; i3 < readShort; i3++) {
                                        ej.A01[i3] = dataInputStream.readUTF();
                                    }
                                    short readShort2 = dataInputStream.readShort();
                                    for (int i4 = 0; i4 < readShort2; i4++) {
                                        int readShort3 = dataInputStream.readShort();
                                        Em[] emArr = new Em[readShort3];
                                        for (int i5 = 0; i5 < readShort3; i5++) {
                                            short readShort4 = dataInputStream.readShort();
                                            String str3 = null;
                                            if (ej.A00) {
                                                str3 = dataInputStream.readUTF();
                                            }
                                            emArr[i5] = new Em(readShort4, str3, Ej.A00(ej));
                                        }
                                        ej.A03.put(ej.A01[i4], new En(emArr));
                                    }
                                    i += readShort2;
                                    ej.close();
                                } catch (Throwable unused) {
                                }
                            } finally {
                                Systrace.A00(4);
                            }
                        }
                    }
                    C0139Dd.A0H("MetadataDeserializer", "Read %d types from %d groups", Integer.valueOf(i), Integer.valueOf(i2));
                    Systrace.A00(4);
                    en = (En) hashMap.get(str);
                    if (en == null) {
                        throw new RuntimeException(AnonymousClass08.A04("No type found for ", str));
                    }
                } finally {
                    Systrace.A00(4);
                }
            } catch (IOException e) {
                throw new RuntimeException(AnonymousClass08.A04("Failed to find ", str), e);
            }
        }
        return en;
        throw th;
    }

    public C0158Ek(C0855ko koVar) {
        this.A00 = koVar;
    }
}
