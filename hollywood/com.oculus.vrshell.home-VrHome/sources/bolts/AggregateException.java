package bolts;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AggregateException extends Exception {
    private static final String DEFAULT_MESSAGE = "There were multiple errors.";
    private static final long serialVersionUID = 1;
    private List<Throwable> innerThrowables;

    public AggregateException(String detailMessage, Throwable[] innerThrowables2) {
        this(detailMessage, Arrays.asList(innerThrowables2));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AggregateException(String detailMessage, List<? extends Throwable> innerThrowables2) {
        super(detailMessage, (innerThrowables2 == null || innerThrowables2.size() <= 0) ? null : (Throwable) innerThrowables2.get(0));
        this.innerThrowables = Collections.unmodifiableList(innerThrowables2);
    }

    public AggregateException(List<? extends Throwable> innerThrowables2) {
        this(DEFAULT_MESSAGE, innerThrowables2);
    }

    public List<Throwable> getInnerThrowables() {
        return this.innerThrowables;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream err) {
        super.printStackTrace(err);
        int currentIndex = -1;
        for (Throwable throwable : this.innerThrowables) {
            err.append("\n");
            err.append("  Inner throwable #");
            currentIndex++;
            err.append((CharSequence) Integer.toString(currentIndex));
            err.append(": ");
            throwable.printStackTrace(err);
            err.append("\n");
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter err) {
        super.printStackTrace(err);
        int currentIndex = -1;
        for (Throwable throwable : this.innerThrowables) {
            err.append("\n");
            err.append("  Inner throwable #");
            currentIndex++;
            err.append((CharSequence) Integer.toString(currentIndex));
            err.append(": ");
            throwable.printStackTrace(err);
            err.append("\n");
        }
    }

    @Deprecated
    public List<Exception> getErrors() {
        List<Exception> errors = new ArrayList<>();
        if (this.innerThrowables != null) {
            for (Throwable cause : this.innerThrowables) {
                if (cause instanceof Exception) {
                    errors.add((Exception) cause);
                } else {
                    errors.add(new Exception(cause));
                }
            }
        }
        return errors;
    }

    @Deprecated
    public Throwable[] getCauses() {
        return (Throwable[]) this.innerThrowables.toArray(new Throwable[this.innerThrowables.size()]);
    }
}
