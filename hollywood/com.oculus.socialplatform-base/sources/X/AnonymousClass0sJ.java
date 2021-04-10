package X;

import com.facebook.acra.ErrorReporter;
import javax.inject.Provider;

/* renamed from: X.0sJ  reason: invalid class name */
public class AnonymousClass0sJ implements Provider<ErrorReporter> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // javax.inject.Provider
    public final ErrorReporter get() {
        return ErrorReporter.getInstance();
    }
}
