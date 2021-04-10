package X;

import com.facebook.gk.store.NamesFileContent;
import com.facebook.gk.store.StateFileContent;
import java.io.File;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class OL {
    public final Y9 A00;
    public final OE<NamesFileContent> A01;
    public final OE<StateFileContent> A02;
    public final OK A03;
    public final File A04;

    public OL(Y9 y9, File file) {
        OE<StateFileContent> oe = new OE<>(new Y6(), file, "gk_state");
        OE<NamesFileContent> oe2 = new OE<>(new Y7(), file, "gk_names");
        this.A00 = y9;
        this.A02 = oe;
        this.A01 = oe2;
        this.A04 = file;
        this.A03 = new OK(new File(file, "file_lock"));
    }
}
