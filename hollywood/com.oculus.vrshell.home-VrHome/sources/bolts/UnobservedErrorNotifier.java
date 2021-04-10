package bolts;

import bolts.Task;

/* access modifiers changed from: package-private */
public class UnobservedErrorNotifier {
    private Task<?> task;

    public UnobservedErrorNotifier(Task<?> task2) {
        this.task = task2;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        Task.UnobservedExceptionHandler ueh;
        try {
            Task faultedTask = this.task;
            if (!(faultedTask == null || (ueh = Task.getUnobservedExceptionHandler()) == null)) {
                ueh.unobservedException(faultedTask, new UnobservedTaskException(faultedTask.getError()));
            }
        } finally {
            super.finalize();
        }
    }

    public void setObserved() {
        this.task = null;
    }
}
