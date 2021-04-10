package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public class AsyncListDiffer<T> {
    private static final Executor sMainThreadExecutor = new MainThreadExecutor();
    final AsyncDifferConfig<T> mConfig;
    @Nullable
    private List<T> mList;
    final Executor mMainThreadExecutor;
    int mMaxScheduledGeneration;
    @NonNull
    private List<T> mReadOnlyList;
    private final ListUpdateCallback mUpdateCallback;

    private static class MainThreadExecutor implements Executor {
        final Handler mHandler = new Handler(Looper.getMainLooper());

        MainThreadExecutor() {
        }

        public void execute(@NonNull Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    public AsyncListDiffer(@NonNull RecyclerView.Adapter adapter, @NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this(new AdapterListUpdateCallback(adapter), new AsyncDifferConfig.Builder(itemCallback).build());
    }

    public AsyncListDiffer(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mReadOnlyList = Collections.emptyList();
        this.mUpdateCallback = listUpdateCallback;
        this.mConfig = asyncDifferConfig;
        if (asyncDifferConfig.getMainThreadExecutor() != null) {
            this.mMainThreadExecutor = asyncDifferConfig.getMainThreadExecutor();
        } else {
            this.mMainThreadExecutor = sMainThreadExecutor;
        }
    }

    @NonNull
    public List<T> getCurrentList() {
        return this.mReadOnlyList;
    }

    public void submitList(@Nullable final List<T> list) {
        final int i = this.mMaxScheduledGeneration + 1;
        this.mMaxScheduledGeneration = i;
        final List<T> list2 = this.mList;
        if (list != list2) {
            if (list == null) {
                int size = list2.size();
                this.mList = null;
                this.mReadOnlyList = Collections.emptyList();
                this.mUpdateCallback.onRemoved(0, size);
            } else if (list2 == null) {
                this.mList = list;
                this.mReadOnlyList = Collections.unmodifiableList(list);
                this.mUpdateCallback.onInserted(0, list.size());
            } else {
                this.mConfig.getBackgroundThreadExecutor().execute(new Runnable() {
                    /* class androidx.recyclerview.widget.AsyncListDiffer.AnonymousClass1 */

                    public void run() {
                        final DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                            /* class androidx.recyclerview.widget.AsyncListDiffer.AnonymousClass1.AnonymousClass1 */

                            @Override // androidx.recyclerview.widget.DiffUtil.Callback
                            public int getOldListSize() {
                                return list2.size();
                            }

                            @Override // androidx.recyclerview.widget.DiffUtil.Callback
                            public int getNewListSize() {
                                return list.size();
                            }

                            @Override // androidx.recyclerview.widget.DiffUtil.Callback
                            public boolean areItemsTheSame(int i, int i2) {
                                Object obj = list2.get(i);
                                Object obj2 = list.get(i2);
                                if (obj == null || obj2 == null) {
                                    return obj == null && obj2 == null;
                                }
                                return AsyncListDiffer.this.mConfig.getDiffCallback().areItemsTheSame(obj, obj2);
                            }

                            @Override // androidx.recyclerview.widget.DiffUtil.Callback
                            public boolean areContentsTheSame(int i, int i2) {
                                Object obj = list2.get(i);
                                Object obj2 = list.get(i2);
                                if (obj != null && obj2 != null) {
                                    return AsyncListDiffer.this.mConfig.getDiffCallback().areContentsTheSame(obj, obj2);
                                }
                                if (obj == null && obj2 == null) {
                                    return true;
                                }
                                throw new AssertionError();
                            }

                            @Override // androidx.recyclerview.widget.DiffUtil.Callback
                            @Nullable
                            public Object getChangePayload(int i, int i2) {
                                Object obj = list2.get(i);
                                Object obj2 = list.get(i2);
                                if (obj != null && obj2 != null) {
                                    return AsyncListDiffer.this.mConfig.getDiffCallback().getChangePayload(obj, obj2);
                                }
                                throw new AssertionError();
                            }
                        });
                        AsyncListDiffer.this.mMainThreadExecutor.execute(new Runnable() {
                            /* class androidx.recyclerview.widget.AsyncListDiffer.AnonymousClass1.AnonymousClass2 */

                            public void run() {
                                if (AsyncListDiffer.this.mMaxScheduledGeneration == i) {
                                    AsyncListDiffer.this.latchList(list, calculateDiff);
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void latchList(@NonNull List<T> list, @NonNull DiffUtil.DiffResult diffResult) {
        this.mList = list;
        this.mReadOnlyList = Collections.unmodifiableList(list);
        diffResult.dispatchUpdatesTo(this.mUpdateCallback);
    }
}
