package X;

import com.facebook.assistant.clientplatform.keyboard.TranscriptionFeedback;
import com.facebook.assistant.oacr.OacrUtil;
import com.facebook.hyperthrift.HyperThriftBase;
import com.facebook.proxygen.HTTPRequestHandler;
import com.oculus.assistant.service.AssistantService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: X.xS  reason: case insensitive filesystem */
public final /* synthetic */ class C1334xS implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1334xS(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        FileInputStream fileInputStream;
        C00608k A0A = this.A00.A05.A0A();
        byte[] bArr = new byte[0];
        try {
            C00598j r0 = A0A.A09;
            File file = r0.A05;
            String str = r0.A07;
            try {
                new FileInputStream(new File(file, str));
                try {
                    fileInputStream = new FileInputStream(new File(file, str));
                } catch (IOException unused) {
                    fileInputStream = null;
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (fileInputStream != null) {
                    byte[] bArr2 = new byte[HTTPRequestHandler.SMALL_REQUESTS_BODY_BUFFER_SIZE];
                    while (true) {
                        int read = fileInputStream.read(bArr2);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    if (bArr.length == 0) {
                        C0139Dd.A0A("KeyboardAssistant", "Failed to retrieve last audio stream");
                        return;
                    }
                    C0754ge geVar = new C0754ge();
                    geVar.A02(0, bArr);
                    C00598j r2 = A0A.A09;
                    geVar.A02(1, r2.A08);
                    geVar.A02(2, r2.A02);
                    geVar.A02(3, "audio/raw");
                    geVar.A02(4, Integer.valueOf(A0A.A00));
                    geVar.A02(5, 1);
                    Object[] A03 = geVar.A03();
                    HyperThriftBase.Builder.A01(A03, 1);
                    TranscriptionFeedback transcriptionFeedback = new TranscriptionFeedback();
                    transcriptionFeedback.A02("com.facebook.assistant.clientplatform.keyboard.TranscriptionFeedback", A03);
                    A0A.A03.A01(new C0815iI(Integer.toString(102), OacrUtil.serialize("com.facebook.assistant.clientplatform.keyboard.TranscriptionFeedback", transcriptionFeedback)));
                    return;
                }
                throw null;
            } catch (IOException unused2) {
            }
        } catch (IOException e) {
            C0139Dd.A0L("KeyboardAssistant", "Failed to load audio file %s", e);
        }
    }
}
