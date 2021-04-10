package X;

import android.util.Base64;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.model.DataUrlLoader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1az  reason: invalid class name and case insensitive filesystem */
public final class C06761az<Data> implements AbstractC07051bX<Data> {
    public Data A00;
    public final DataUrlLoader.DataDecoder<Data> A01;
    public final String A02;

    @Override // X.AbstractC07051bX
    public final void cancel() {
    }

    @Override // X.AbstractC07051bX
    public final void A26() {
        try {
            this.A00.close();
        } catch (IOException unused) {
        }
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<Data> A3h() {
        return InputStream.class;
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.LOCAL;
    }

    @Override // X.AbstractC07051bX
    public final void A6H(@NonNull AnonymousClass1cY r6, @NonNull AnonymousClass1Ry<? super Data> r7) {
        try {
            String str = this.A02;
            if (str.startsWith("data:image")) {
                int indexOf = str.indexOf(44);
                if (indexOf == -1) {
                    throw new IllegalArgumentException("Missing comma in data URL.");
                } else if (str.substring(0, indexOf).endsWith(";base64")) {
                    Data data = (Data) new ByteArrayInputStream(Base64.decode(str.substring(indexOf + 1), 0));
                    this.A00 = data;
                    r7.A6x(data);
                } else {
                    throw new IllegalArgumentException("Not a base64 image data URL.");
                }
            } else {
                throw new IllegalArgumentException("Not a valid image data URL.");
            }
        } catch (IllegalArgumentException e) {
            r7.A7F(e);
        }
    }

    public C06761az(String str, DataUrlLoader.DataDecoder<Data> dataDecoder) {
        this.A02 = str;
        this.A01 = dataDecoder;
    }
}
