package com.oculus.util.task;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0VC;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import bolts.CancellationToken;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import javax.inject.Provider;

@Dependencies({})
public class TaskDelayFactory {
    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_util_task_TaskDelayFactory_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(11, r1);
    }

    @AutoGeneratedAccessMethod
    public static final TaskDelayFactory _UL__ULSEP_com_oculus_util_task_TaskDelayFactory_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (TaskDelayFactory) AnonymousClass1TK.A00(11, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final TaskDelayFactory _UL__ULSEP_com_oculus_util_task_TaskDelayFactory_ULSEP_FACTORY_METHOD(AnonymousClass0lg r0, Object obj) {
        return new TaskDelayFactory();
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_util_task_TaskDelayFactory_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(11, r1);
    }

    public Task<Void> runTaskWithTimeoutAsync(final TaskCompletionSource<Void> taskCompletionSource, long j) {
        return Task.whenAnyResult(Arrays.asList(taskCompletionSource.task, Task.delay(j))).onSuccessTask(new Continuation<Task<Void>, Task<Void>>() {
            /* class com.oculus.util.task.TaskDelayFactory.AnonymousClass1 */

            @Override // bolts.Continuation
            public Task<Void> then(Task<Task<Void>> task) {
                Task<TResult> task2 = taskCompletionSource.task;
                if (task2 == null || !task2.isCompleted() || task2.isCancelled()) {
                    return Task.forError(new TimeoutException());
                }
                return task2;
            }
        });
    }

    public Task<Void> delay(long j) {
        return Task.delay(j);
    }

    public Task<Void> delay(long j, CancellationToken cancellationToken) {
        return Task.delay(j, cancellationToken);
    }
}
