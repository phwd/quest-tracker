package bolts;

import bolts.Task;

public class UnobservedErrorNotifier {
    public Task<?> task;

    public void setObserved() {
        this.task = null;
    }

    public void finalize() throws Throwable {
        Task.UnobservedExceptionHandler unobservedExceptionHandler;
        try {
            Task<?> task2 = this.task;
            if (!(task2 == null || (unobservedExceptionHandler = Task.unobservedExceptionHandler) == null)) {
                unobservedExceptionHandler.unobservedException(task2, new UnobservedTaskException(task2.getError()));
            }
        } finally {
            super.finalize();
        }
    }

    public UnobservedErrorNotifier(Task<?> task2) {
        this.task = task2;
    }
}
