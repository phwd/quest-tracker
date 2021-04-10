package com.oculus.android.exoplayer2.source;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.PlayerMessage;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.source.ShuffleOrder;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class DynamicConcatenatingMediaSource extends CompositeMediaSource<MediaSourceHolder> implements PlayerMessage.Target {
    private static final int MSG_ADD = 0;
    private static final int MSG_ADD_MULTIPLE = 1;
    private static final int MSG_MOVE = 3;
    private static final int MSG_ON_COMPLETION = 4;
    private static final int MSG_REMOVE = 2;
    private final List<DeferredMediaPeriod> deferredMediaPeriods;
    private final boolean isAtomic;
    private MediaSource.Listener listener;
    private final Map<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final List<MediaSourceHolder> mediaSourceHolders;
    private final List<MediaSource> mediaSourcesPublic;
    private int periodCount;
    private ExoPlayer player;
    private boolean preventListenerNotification;
    private final MediaSourceHolder query;
    private ShuffleOrder shuffleOrder;
    private int windowCount;

    public DynamicConcatenatingMediaSource() {
        this(false, new ShuffleOrder.DefaultShuffleOrder(0));
    }

    public DynamicConcatenatingMediaSource(boolean z) {
        this(z, new ShuffleOrder.DefaultShuffleOrder(0));
    }

    public DynamicConcatenatingMediaSource(boolean z, ShuffleOrder shuffleOrder2) {
        this.shuffleOrder = shuffleOrder2;
        this.mediaSourceByMediaPeriod = new IdentityHashMap();
        this.mediaSourcesPublic = new ArrayList();
        this.mediaSourceHolders = new ArrayList();
        this.deferredMediaPeriods = new ArrayList(1);
        this.query = new MediaSourceHolder(null, null, -1, -1, -1);
        this.isAtomic = z;
    }

    public synchronized void addMediaSource(MediaSource mediaSource) {
        addMediaSource(this.mediaSourcesPublic.size(), mediaSource, null);
    }

    public synchronized void addMediaSource(MediaSource mediaSource, @Nullable Runnable runnable) {
        addMediaSource(this.mediaSourcesPublic.size(), mediaSource, runnable);
    }

    public synchronized void addMediaSource(int i, MediaSource mediaSource) {
        addMediaSource(i, mediaSource, null);
    }

    public synchronized void addMediaSource(int i, MediaSource mediaSource, @Nullable Runnable runnable) {
        Assertions.checkNotNull(mediaSource);
        Assertions.checkArgument(!this.mediaSourcesPublic.contains(mediaSource));
        this.mediaSourcesPublic.add(i, mediaSource);
        if (this.player != null) {
            this.player.createMessage(this).setType(0).setPayload(new MessageData(i, mediaSource, runnable)).send();
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public synchronized void addMediaSources(Collection<MediaSource> collection) {
        addMediaSources(this.mediaSourcesPublic.size(), collection, null);
    }

    public synchronized void addMediaSources(Collection<MediaSource> collection, @Nullable Runnable runnable) {
        addMediaSources(this.mediaSourcesPublic.size(), collection, runnable);
    }

    public synchronized void addMediaSources(int i, Collection<MediaSource> collection) {
        addMediaSources(i, collection, null);
    }

    public synchronized void addMediaSources(int i, Collection<MediaSource> collection, @Nullable Runnable runnable) {
        Iterator<MediaSource> it = collection.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            MediaSource next = it.next();
            Assertions.checkNotNull(next);
            if (this.mediaSourcesPublic.contains(next)) {
                z = false;
            }
            Assertions.checkArgument(z);
        }
        this.mediaSourcesPublic.addAll(i, collection);
        if (this.player != null && !collection.isEmpty()) {
            this.player.createMessage(this).setType(1).setPayload(new MessageData(i, collection, runnable)).send();
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public synchronized void removeMediaSource(int i) {
        removeMediaSource(i, null);
    }

    public synchronized void removeMediaSource(int i, @Nullable Runnable runnable) {
        this.mediaSourcesPublic.remove(i);
        if (this.player != null) {
            this.player.createMessage(this).setType(2).setPayload(new MessageData(i, null, runnable)).send();
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public synchronized void moveMediaSource(int i, int i2) {
        moveMediaSource(i, i2, null);
    }

    public synchronized void moveMediaSource(int i, int i2, @Nullable Runnable runnable) {
        if (i != i2) {
            this.mediaSourcesPublic.add(i2, this.mediaSourcesPublic.remove(i));
            if (this.player != null) {
                this.player.createMessage(this).setType(3).setPayload(new MessageData(i, Integer.valueOf(i2), runnable)).send();
            } else if (runnable != null) {
                runnable.run();
            }
        }
    }

    public synchronized int getSize() {
        return this.mediaSourcesPublic.size();
    }

    public synchronized MediaSource getMediaSource(int i) {
        return this.mediaSourcesPublic.get(i);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public synchronized void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener2) {
        super.prepareSource(exoPlayer, z, listener2);
        this.player = exoPlayer;
        this.listener = listener2;
        this.preventListenerNotification = true;
        this.shuffleOrder = this.shuffleOrder.cloneAndInsert(0, this.mediaSourcesPublic.size());
        addMediaSourcesInternal(0, this.mediaSourcesPublic);
        this.preventListenerNotification = false;
        maybeNotifyListener(null);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        MediaPeriod mediaPeriod;
        MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(findMediaSourceHolderByPeriodIndex(mediaPeriodId.periodIndex));
        MediaSource.MediaPeriodId copyWithPeriodIndex = mediaPeriodId.copyWithPeriodIndex(mediaPeriodId.periodIndex - mediaSourceHolder.firstPeriodIndexInChild);
        if (!mediaSourceHolder.isPrepared) {
            mediaPeriod = new DeferredMediaPeriod(mediaSourceHolder.mediaSource, copyWithPeriodIndex, allocator);
            this.deferredMediaPeriods.add((DeferredMediaPeriod) mediaPeriod);
        } else {
            mediaPeriod = mediaSourceHolder.mediaSource.createPeriod(copyWithPeriodIndex, allocator);
        }
        this.mediaSourceByMediaPeriod.put(mediaPeriod, mediaSourceHolder);
        mediaSourceHolder.activeMediaPeriods++;
        return mediaPeriod;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder remove = this.mediaSourceByMediaPeriod.remove(mediaPeriod);
        if (mediaPeriod instanceof DeferredMediaPeriod) {
            this.deferredMediaPeriods.remove(mediaPeriod);
            ((DeferredMediaPeriod) mediaPeriod).releasePeriod();
        } else {
            remove.mediaSource.releasePeriod(mediaPeriod);
        }
        remove.activeMediaPeriods--;
        if (remove.activeMediaPeriods == 0 && remove.isRemoved) {
            releaseChildSource(remove);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void releaseSource() {
        super.releaseSource();
        this.mediaSourceHolders.clear();
        this.player = null;
        this.listener = null;
        this.shuffleOrder = this.shuffleOrder.cloneAndClear();
        this.windowCount = 0;
        this.periodCount = 0;
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(MediaSourceHolder mediaSourceHolder, MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
        updateMediaSourceInternal(mediaSourceHolder, timeline);
    }

    @Override // com.oculus.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        EventDispatcher eventDispatcher;
        if (i == 4) {
            ((EventDispatcher) obj).dispatchEvent();
            return;
        }
        this.preventListenerNotification = true;
        if (i == 0) {
            MessageData messageData = (MessageData) obj;
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(messageData.index, 1);
            addMediaSourceInternal(messageData.index, messageData.customData);
            eventDispatcher = messageData.actionOnCompletion;
        } else if (i == 1) {
            MessageData messageData2 = (MessageData) obj;
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(messageData2.index, messageData2.customData.size());
            addMediaSourcesInternal(messageData2.index, messageData2.customData);
            eventDispatcher = messageData2.actionOnCompletion;
        } else if (i == 2) {
            MessageData messageData3 = (MessageData) obj;
            this.shuffleOrder = this.shuffleOrder.cloneAndRemove(messageData3.index);
            removeMediaSourceInternal(messageData3.index);
            eventDispatcher = messageData3.actionOnCompletion;
        } else if (i == 3) {
            MessageData messageData4 = (MessageData) obj;
            this.shuffleOrder = this.shuffleOrder.cloneAndRemove(messageData4.index);
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(messageData4.customData.intValue(), 1);
            moveMediaSourceInternal(messageData4.index, messageData4.customData.intValue());
            eventDispatcher = messageData4.actionOnCompletion;
        } else {
            throw new IllegalStateException();
        }
        this.preventListenerNotification = false;
        maybeNotifyListener(eventDispatcher);
    }

    private void maybeNotifyListener(@Nullable EventDispatcher eventDispatcher) {
        if (!this.preventListenerNotification) {
            this.listener.onSourceInfoRefreshed(this, new ConcatenatedTimeline(this.mediaSourceHolders, this.windowCount, this.periodCount, this.shuffleOrder, this.isAtomic), null);
            if (eventDispatcher != null) {
                this.player.createMessage(this).setType(4).setPayload(eventDispatcher).send();
            }
        }
    }

    private void addMediaSourceInternal(int i, MediaSource mediaSource) {
        MediaSourceHolder mediaSourceHolder;
        DeferredTimeline deferredTimeline = new DeferredTimeline();
        if (i > 0) {
            MediaSourceHolder mediaSourceHolder2 = this.mediaSourceHolders.get(i - 1);
            mediaSourceHolder = new MediaSourceHolder(mediaSource, deferredTimeline, i, mediaSourceHolder2.firstWindowIndexInChild + mediaSourceHolder2.timeline.getWindowCount(), mediaSourceHolder2.firstPeriodIndexInChild + mediaSourceHolder2.timeline.getPeriodCount());
        } else {
            mediaSourceHolder = new MediaSourceHolder(mediaSource, deferredTimeline, 0, 0, 0);
        }
        correctOffsets(i, 1, deferredTimeline.getWindowCount(), deferredTimeline.getPeriodCount());
        this.mediaSourceHolders.add(i, mediaSourceHolder);
        prepareChildSource(mediaSourceHolder, mediaSourceHolder.mediaSource);
    }

    private void addMediaSourcesInternal(int i, Collection<MediaSource> collection) {
        for (MediaSource mediaSource : collection) {
            addMediaSourceInternal(i, mediaSource);
            i++;
        }
    }

    private void updateMediaSourceInternal(MediaSourceHolder mediaSourceHolder, Timeline timeline) {
        if (mediaSourceHolder != null) {
            DeferredTimeline deferredTimeline = mediaSourceHolder.timeline;
            if (deferredTimeline.getTimeline() != timeline) {
                int windowCount2 = timeline.getWindowCount() - deferredTimeline.getWindowCount();
                int periodCount2 = timeline.getPeriodCount() - deferredTimeline.getPeriodCount();
                if (!(windowCount2 == 0 && periodCount2 == 0)) {
                    correctOffsets(mediaSourceHolder.childIndex + 1, 0, windowCount2, periodCount2);
                }
                mediaSourceHolder.timeline = deferredTimeline.cloneWithNewTimeline(timeline);
                if (!mediaSourceHolder.isPrepared) {
                    for (int size = this.deferredMediaPeriods.size() - 1; size >= 0; size--) {
                        if (this.deferredMediaPeriods.get(size).mediaSource == mediaSourceHolder.mediaSource) {
                            this.deferredMediaPeriods.get(size).createPeriod();
                            this.deferredMediaPeriods.remove(size);
                        }
                    }
                }
                mediaSourceHolder.isPrepared = true;
                maybeNotifyListener(null);
                return;
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    private void removeMediaSourceInternal(int i) {
        MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i);
        this.mediaSourceHolders.remove(i);
        DeferredTimeline deferredTimeline = mediaSourceHolder.timeline;
        correctOffsets(i, -1, -deferredTimeline.getWindowCount(), -deferredTimeline.getPeriodCount());
        mediaSourceHolder.isRemoved = true;
        if (mediaSourceHolder.activeMediaPeriods == 0) {
            releaseChildSource(mediaSourceHolder);
        }
    }

    private void moveMediaSourceInternal(int i, int i2) {
        int min = Math.min(i, i2);
        int max = Math.max(i, i2);
        int i3 = this.mediaSourceHolders.get(min).firstWindowIndexInChild;
        int i4 = this.mediaSourceHolders.get(min).firstPeriodIndexInChild;
        List<MediaSourceHolder> list = this.mediaSourceHolders;
        list.add(i2, list.remove(i));
        while (min <= max) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(min);
            mediaSourceHolder.firstWindowIndexInChild = i3;
            mediaSourceHolder.firstPeriodIndexInChild = i4;
            i3 += mediaSourceHolder.timeline.getWindowCount();
            i4 += mediaSourceHolder.timeline.getPeriodCount();
            min++;
        }
    }

    private void correctOffsets(int i, int i2, int i3, int i4) {
        this.windowCount += i3;
        this.periodCount += i4;
        while (i < this.mediaSourceHolders.size()) {
            this.mediaSourceHolders.get(i).childIndex += i2;
            this.mediaSourceHolders.get(i).firstWindowIndexInChild += i3;
            this.mediaSourceHolders.get(i).firstPeriodIndexInChild += i4;
            i++;
        }
    }

    private int findMediaSourceHolderByPeriodIndex(int i) {
        MediaSourceHolder mediaSourceHolder = this.query;
        mediaSourceHolder.firstPeriodIndexInChild = i;
        int binarySearch = Collections.binarySearch(this.mediaSourceHolders, mediaSourceHolder);
        if (binarySearch < 0) {
            return (-binarySearch) - 2;
        }
        while (binarySearch < this.mediaSourceHolders.size() - 1) {
            int i2 = binarySearch + 1;
            if (this.mediaSourceHolders.get(i2).firstPeriodIndexInChild != i) {
                break;
            }
            binarySearch = i2;
        }
        return binarySearch;
    }

    /* access modifiers changed from: package-private */
    public static final class MediaSourceHolder implements Comparable<MediaSourceHolder> {
        public int activeMediaPeriods;
        public int childIndex;
        public int firstPeriodIndexInChild;
        public int firstWindowIndexInChild;
        public boolean isPrepared;
        public boolean isRemoved;
        public final MediaSource mediaSource;
        public DeferredTimeline timeline;
        public final int uid = System.identityHashCode(this);

        public MediaSourceHolder(MediaSource mediaSource2, DeferredTimeline deferredTimeline, int i, int i2, int i3) {
            this.mediaSource = mediaSource2;
            this.timeline = deferredTimeline;
            this.childIndex = i;
            this.firstWindowIndexInChild = i2;
            this.firstPeriodIndexInChild = i3;
        }

        public int compareTo(@NonNull MediaSourceHolder mediaSourceHolder) {
            return this.firstPeriodIndexInChild - mediaSourceHolder.firstPeriodIndexInChild;
        }
    }

    /* access modifiers changed from: private */
    public static final class EventDispatcher {
        public final Handler eventHandler;
        public final Runnable runnable;

        public EventDispatcher(Runnable runnable2) {
            Looper looper;
            this.runnable = runnable2;
            if (Looper.myLooper() != null) {
                looper = Looper.myLooper();
            } else {
                looper = Looper.getMainLooper();
            }
            this.eventHandler = new Handler(looper);
        }

        public void dispatchEvent() {
            this.eventHandler.post(this.runnable);
        }
    }

    /* access modifiers changed from: private */
    public static final class MessageData<T> {
        @Nullable
        public final EventDispatcher actionOnCompletion;
        public final T customData;
        public final int index;

        public MessageData(int i, T t, @Nullable Runnable runnable) {
            this.index = i;
            this.actionOnCompletion = runnable != null ? new EventDispatcher(runnable) : null;
            this.customData = t;
        }
    }

    /* access modifiers changed from: private */
    public static final class ConcatenatedTimeline extends AbstractConcatenatedTimeline {
        private final SparseIntArray childIndexByUid = new SparseIntArray();
        private final int[] firstPeriodInChildIndices;
        private final int[] firstWindowInChildIndices;
        private final int periodCount;
        private final Timeline[] timelines;
        private final int[] uids;
        private final int windowCount;

        public ConcatenatedTimeline(Collection<MediaSourceHolder> collection, int i, int i2, ShuffleOrder shuffleOrder, boolean z) {
            super(z, shuffleOrder);
            this.windowCount = i;
            this.periodCount = i2;
            int size = collection.size();
            this.firstPeriodInChildIndices = new int[size];
            this.firstWindowInChildIndices = new int[size];
            this.timelines = new Timeline[size];
            this.uids = new int[size];
            int i3 = 0;
            for (MediaSourceHolder mediaSourceHolder : collection) {
                this.timelines[i3] = mediaSourceHolder.timeline;
                this.firstPeriodInChildIndices[i3] = mediaSourceHolder.firstPeriodIndexInChild;
                this.firstWindowInChildIndices[i3] = mediaSourceHolder.firstWindowIndexInChild;
                this.uids[i3] = mediaSourceHolder.uid;
                this.childIndexByUid.put(this.uids[i3], i3);
                i3++;
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByPeriodIndex(int i) {
            return Util.binarySearchFloor(this.firstPeriodInChildIndices, i + 1, false, false);
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByWindowIndex(int i) {
            return Util.binarySearchFloor(this.firstWindowInChildIndices, i + 1, false, false);
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByChildUid(Object obj) {
            if (!(obj instanceof Integer)) {
                return -1;
            }
            int i = this.childIndexByUid.get(((Integer) obj).intValue(), -1);
            if (i == -1) {
                return -1;
            }
            return i;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public Timeline getTimelineByChildIndex(int i) {
            return this.timelines[i];
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getFirstPeriodIndexByChildIndex(int i) {
            return this.firstPeriodInChildIndices[i];
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getFirstWindowIndexByChildIndex(int i) {
            return this.firstWindowInChildIndices[i];
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public Object getChildUidByChildIndex(int i) {
            return Integer.valueOf(this.uids[i]);
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public int getWindowCount() {
            return this.windowCount;
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return this.periodCount;
        }
    }

    /* access modifiers changed from: private */
    public static final class DeferredTimeline extends ForwardingTimeline {
        private static final Object DUMMY_ID = new Object();
        private static final DummyTimeline dummyTimeline = new DummyTimeline();
        private static final Timeline.Period period = new Timeline.Period();
        private final Object replacedId;

        public DeferredTimeline() {
            this(dummyTimeline, null);
        }

        private DeferredTimeline(Timeline timeline, Object obj) {
            super(timeline);
            this.replacedId = obj;
        }

        public DeferredTimeline cloneWithNewTimeline(Timeline timeline) {
            return new DeferredTimeline(timeline, (this.replacedId != null || timeline.getPeriodCount() <= 0) ? this.replacedId : timeline.getPeriod(0, period, true).uid);
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        @Override // com.oculus.android.exoplayer2.Timeline, com.oculus.android.exoplayer2.source.ForwardingTimeline
        public Timeline.Period getPeriod(int i, Timeline.Period period2, boolean z) {
            this.timeline.getPeriod(i, period2, z);
            if (Util.areEqual(period2.uid, this.replacedId)) {
                period2.uid = DUMMY_ID;
            }
            return period2;
        }

        @Override // com.oculus.android.exoplayer2.Timeline, com.oculus.android.exoplayer2.source.ForwardingTimeline
        public int getIndexOfPeriod(Object obj) {
            Timeline timeline = this.timeline;
            if (DUMMY_ID.equals(obj)) {
                obj = this.replacedId;
            }
            return timeline.getIndexOfPeriod(obj);
        }
    }

    private static final class DummyTimeline extends Timeline {
        @Override // com.oculus.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            return obj == null ? 0 : -1;
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return 1;
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public int getWindowCount() {
            return 1;
        }

        private DummyTimeline() {
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
            return window.set(null, C.TIME_UNSET, C.TIME_UNSET, false, true, 0, C.TIME_UNSET, 0, 0, 0);
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            return period.set(null, null, 0, C.TIME_UNSET, C.TIME_UNSET);
        }
    }
}
