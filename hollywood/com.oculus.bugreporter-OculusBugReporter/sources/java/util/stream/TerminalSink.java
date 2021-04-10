package java.util.stream;

import java.util.function.Supplier;

/* access modifiers changed from: package-private */
public interface TerminalSink<T, R> extends Sink<T>, Supplier<R> {
}
