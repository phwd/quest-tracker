package android.support.v4.content;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader<D> {

    public interface OnLoadCompleteListener<D> {
    }

    public boolean cancelLoad() {
        throw null;
    }

    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        throw null;
    }

    public void reset() {
        throw null;
    }

    public final void startLoading() {
        throw null;
    }

    public void stopLoading() {
        throw null;
    }
}
