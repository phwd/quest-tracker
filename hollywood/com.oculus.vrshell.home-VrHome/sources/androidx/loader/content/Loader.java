package androidx.loader.content;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader<D> {
    private boolean mAbandoned;
    private boolean mContentChanged;
    private int mId;
    private OnLoadCompleteListener<D> mListener;
    private boolean mProcessingChange;
    private boolean mReset;
    private boolean mStarted;

    public interface OnLoadCompleteListener<D> {
    }

    public void unregisterListener(OnLoadCompleteListener<D> listener) {
        if (this.mListener == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.mListener != listener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.mListener = null;
        }
    }

    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        onStartLoading();
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
    }

    public boolean cancelLoad() {
        return onCancelLoad();
    }

    /* access modifiers changed from: protected */
    public boolean onCancelLoad() {
        return false;
    }

    public void stopLoading() {
        this.mStarted = false;
        onStopLoading();
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
    }

    public void abandon() {
        this.mAbandoned = true;
        onAbandon();
    }

    /* access modifiers changed from: protected */
    public void onAbandon() {
    }

    public void reset() {
        onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    /* access modifiers changed from: protected */
    public void onReset() {
    }

    public String dataToString(D data) {
        StringBuilder sb = new StringBuilder(64);
        if (data == null) {
            sb.append("null");
        } else {
            sb.append(data.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(data)));
            sb.append("}");
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" id=");
        sb.append(this.mId);
        sb.append("}");
        return sb.toString();
    }

    @Deprecated
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.print(prefix);
        writer.print("mId=");
        writer.print(this.mId);
        writer.print(" mListener=");
        writer.println(this.mListener);
        if (this.mStarted || this.mContentChanged || this.mProcessingChange) {
            writer.print(prefix);
            writer.print("mStarted=");
            writer.print(this.mStarted);
            writer.print(" mContentChanged=");
            writer.print(this.mContentChanged);
            writer.print(" mProcessingChange=");
            writer.println(this.mProcessingChange);
        }
        if (this.mAbandoned || this.mReset) {
            writer.print(prefix);
            writer.print("mAbandoned=");
            writer.print(this.mAbandoned);
            writer.print(" mReset=");
            writer.println(this.mReset);
        }
    }
}
