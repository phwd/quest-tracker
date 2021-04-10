package X;

import java.io.File;
import java.io.IOException;

public final class Z7 extends Fz<File> {
    public static Fz A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.Fz
    public final AbstractC0081Fy A03(File file) {
        File file2 = file;
        try {
            return new Z8(this, file2);
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder("Unexpected error, failed to create file: ");
            sb.append(file2);
            throw new G8(sb.toString(), e);
        }
    }
}
