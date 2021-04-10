package X;

import com.facebook.acra.ErrorReporter;
import javax.inject.Provider;

/* renamed from: X.0pu  reason: invalid class name and case insensitive filesystem */
public class C06800pu implements Provider<ErrorReporter> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // javax.inject.Provider
    public final ErrorReporter get() {
        return ErrorReporter.getInstance();
    }
}
