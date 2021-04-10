package java.util.stream;

import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.AbstractShortCircuitTask;

/* access modifiers changed from: package-private */
public abstract class AbstractShortCircuitTask<P_IN, P_OUT, R, K extends AbstractShortCircuitTask<P_IN, P_OUT, R, K>> extends AbstractTask<P_IN, P_OUT, R, K> {
    protected volatile boolean canceled;
    protected final AtomicReference<R> sharedResult;

    /* access modifiers changed from: protected */
    public abstract R getEmptyResult();

    protected AbstractShortCircuitTask(PipelineHelper<P_OUT> helper, Spliterator<P_IN> spliterator) {
        super(helper, spliterator);
        this.sharedResult = new AtomicReference<>(null);
    }

    protected AbstractShortCircuitTask(K parent, Spliterator<P_IN> spliterator) {
        super(parent, spliterator);
        this.sharedResult = parent.sharedResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.stream.AbstractShortCircuitTask] */
    @Override // java.util.concurrent.CountedCompleter, java.util.stream.AbstractTask
    public void compute() {
        Object obj;
        Spliterator<P_IN> ls;
        K taskToFork;
        AbstractShortCircuitTask<P_IN, P_OUT, R, K> abstractShortCircuitTask;
        Spliterator<P_IN> rs = this.spliterator;
        long sizeEstimate = rs.estimateSize();
        long sizeThreshold = getTargetSize(sizeEstimate);
        boolean forkRight = false;
        AbstractShortCircuitTask<P_IN, P_OUT, R, K> abstractShortCircuitTask2 = this;
        AtomicReference<R> sr = this.sharedResult;
        while (true) {
            R r = sr.get();
            obj = r;
            if (r != null) {
                break;
            } else if (abstractShortCircuitTask2.taskCanceled()) {
                obj = abstractShortCircuitTask2.getEmptyResult();
                break;
            } else if (sizeEstimate <= sizeThreshold || (ls = rs.trySplit()) == null) {
                obj = abstractShortCircuitTask2.doLeaf();
            } else {
                AbstractShortCircuitTask<P_IN, P_OUT, R, K> abstractShortCircuitTask3 = (AbstractShortCircuitTask) abstractShortCircuitTask2.makeChild(ls);
                ((AbstractShortCircuitTask) abstractShortCircuitTask2).leftChild = abstractShortCircuitTask3;
                AbstractShortCircuitTask<P_IN, P_OUT, R, K> abstractShortCircuitTask4 = (AbstractShortCircuitTask) abstractShortCircuitTask2.makeChild(rs);
                ((AbstractShortCircuitTask) abstractShortCircuitTask2).rightChild = abstractShortCircuitTask4;
                abstractShortCircuitTask2.setPendingCount(1);
                if (forkRight) {
                    forkRight = false;
                    rs = ls;
                    abstractShortCircuitTask = abstractShortCircuitTask3;
                    taskToFork = abstractShortCircuitTask4;
                } else {
                    forkRight = true;
                    abstractShortCircuitTask = abstractShortCircuitTask4;
                    taskToFork = abstractShortCircuitTask3;
                }
                taskToFork.fork();
                sizeEstimate = rs.estimateSize();
                abstractShortCircuitTask2 = abstractShortCircuitTask;
            }
        }
        abstractShortCircuitTask2.setLocalResult(obj);
        abstractShortCircuitTask2.tryComplete();
    }

    /* access modifiers changed from: protected */
    public void shortCircuit(R result) {
        if (result != null) {
            this.sharedResult.compareAndSet(null, result);
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.util.stream.AbstractTask
    public void setLocalResult(R localResult) {
        if (!isRoot()) {
            super.setLocalResult(localResult);
        } else if (localResult != null) {
            this.sharedResult.compareAndSet(null, localResult);
        }
    }

    @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter, java.util.stream.AbstractTask
    public R getRawResult() {
        return getLocalResult();
    }

    @Override // java.util.stream.AbstractTask
    public R getLocalResult() {
        if (!isRoot()) {
            return (R) super.getLocalResult();
        }
        R answer = this.sharedResult.get();
        return answer == null ? getEmptyResult() : answer;
    }

    /* access modifiers changed from: protected */
    public void cancel() {
        this.canceled = true;
    }

    /* access modifiers changed from: protected */
    public boolean taskCanceled() {
        boolean cancel = this.canceled;
        if (!cancel) {
            AbstractShortCircuitTask abstractShortCircuitTask = (AbstractShortCircuitTask) getParent();
            while (!cancel && abstractShortCircuitTask != null) {
                cancel = abstractShortCircuitTask.canceled;
                abstractShortCircuitTask = (AbstractShortCircuitTask) abstractShortCircuitTask.getParent();
            }
        }
        return cancel;
    }

    /* access modifiers changed from: protected */
    public void cancelLaterNodes() {
        K node = this;
        for (AbstractShortCircuitTask<P_IN, P_OUT, R, K> abstractShortCircuitTask = (AbstractShortCircuitTask) getParent(); abstractShortCircuitTask != null; abstractShortCircuitTask = (AbstractShortCircuitTask) abstractShortCircuitTask.getParent()) {
            if (abstractShortCircuitTask.leftChild == node) {
                AbstractShortCircuitTask abstractShortCircuitTask2 = (AbstractShortCircuitTask) abstractShortCircuitTask.rightChild;
                if (!abstractShortCircuitTask2.canceled) {
                    abstractShortCircuitTask2.cancel();
                }
            }
            node = abstractShortCircuitTask;
        }
    }
}
