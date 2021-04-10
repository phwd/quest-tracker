package bolts;

public class TaskCompletionSource<TResult> {
    public final Task<TResult> task = new Task<>();

    public void setCancelled() {
        if (!this.task.trySetCancelled()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public void setError(Exception exc) {
        if (!this.task.trySetError(exc)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }

    public void setResult(TResult tresult) {
        if (!this.task.trySetResult(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public boolean trySetCancelled() {
        return this.task.trySetCancelled();
    }

    public boolean trySetError(Exception exc) {
        return this.task.trySetError(exc);
    }

    public boolean trySetResult(TResult tresult) {
        return this.task.trySetResult(tresult);
    }

    public Task<TResult> getTask() {
        return this.task;
    }
}
