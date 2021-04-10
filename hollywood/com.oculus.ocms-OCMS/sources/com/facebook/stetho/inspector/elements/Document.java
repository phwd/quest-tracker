package com.facebook.stetho.inspector.elements;

import android.os.SystemClock;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ArrayListAccumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.ShadowDocument;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.helper.ThreadBoundProxy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class Document extends ThreadBoundProxy {
    private AttributeListAccumulator mCachedAttributeAccumulator;
    private ChildEventingList mCachedChildEventingList;
    private ArrayListAccumulator<Object> mCachedChildrenAccumulator;
    private final Queue<Object> mCachedUpdateQueue = new ArrayDeque();
    private DocumentProvider mDocumentProvider;
    private final DocumentProviderFactory mFactory;
    private final ObjectIdMapper mObjectIdMapper = new DocumentObjectIdMapper();
    @GuardedBy("this")
    private int mReferenceCounter = 0;
    private ShadowDocument mShadowDocument;
    private UpdateListenerCollection mUpdateListeners = new UpdateListenerCollection();

    public interface UpdateListener {
        void onAttributeModified(Object obj, String str, String str2);

        void onAttributeRemoved(Object obj, String str);

        void onChildNodeInserted(DocumentView documentView, Object obj, int i, int i2, Accumulator<Object> accumulator);

        void onChildNodeRemoved(int i, int i2);

        void onInspectRequested(Object obj);
    }

    public Document(DocumentProviderFactory documentProviderFactory) {
        super(documentProviderFactory);
        this.mFactory = documentProviderFactory;
    }

    public synchronized void addRef() {
        int i = this.mReferenceCounter;
        this.mReferenceCounter = i + 1;
        if (i == 0) {
            init();
        }
    }

    public synchronized void release() {
        if (this.mReferenceCounter > 0) {
            int i = this.mReferenceCounter - 1;
            this.mReferenceCounter = i;
            if (i == 0) {
                cleanUp();
            }
        }
    }

    private void init() {
        this.mDocumentProvider = this.mFactory.create();
        this.mDocumentProvider.postAndWait(new Runnable() {
            /* class com.facebook.stetho.inspector.elements.Document.AnonymousClass1 */

            public void run() {
                Document document = Document.this;
                document.mShadowDocument = new ShadowDocument(document.mDocumentProvider.getRootElement());
                Document.this.createShadowDocumentUpdate().commit();
                Document.this.mDocumentProvider.setListener(new ProviderListener());
            }
        });
    }

    private void cleanUp() {
        this.mDocumentProvider.postAndWait(new Runnable() {
            /* class com.facebook.stetho.inspector.elements.Document.AnonymousClass2 */

            public void run() {
                Document.this.mDocumentProvider.setListener(null);
                Document.this.mShadowDocument = null;
                Document.this.mObjectIdMapper.clear();
                Document.this.mDocumentProvider.dispose();
                Document.this.mDocumentProvider = null;
            }
        });
        this.mUpdateListeners.clear();
    }

    public void addUpdateListener(UpdateListener updateListener) {
        this.mUpdateListeners.add(updateListener);
    }

    public void removeUpdateListener(UpdateListener updateListener) {
        this.mUpdateListeners.remove(updateListener);
    }

    @Nullable
    public NodeDescriptor getNodeDescriptor(Object obj) {
        verifyThreadAccess();
        return this.mDocumentProvider.getNodeDescriptor(obj);
    }

    public void highlightElement(Object obj, int i) {
        verifyThreadAccess();
        this.mDocumentProvider.highlightElement(obj, i);
    }

    public void hideHighlight() {
        verifyThreadAccess();
        this.mDocumentProvider.hideHighlight();
    }

    public void setInspectModeEnabled(boolean z) {
        verifyThreadAccess();
        this.mDocumentProvider.setInspectModeEnabled(z);
    }

    @Nullable
    public Integer getNodeIdForElement(Object obj) {
        return this.mObjectIdMapper.getIdForObject(obj);
    }

    @Nullable
    public Object getElementForNodeId(int i) {
        return this.mObjectIdMapper.getObjectForId(i);
    }

    public void setAttributesAsText(Object obj, String str) {
        verifyThreadAccess();
        this.mDocumentProvider.setAttributesAsText(obj, str);
    }

    public void getElementStyleRuleNames(Object obj, StyleRuleNameAccumulator styleRuleNameAccumulator) {
        getNodeDescriptor(obj).getStyleRuleNames(obj, styleRuleNameAccumulator);
    }

    public void getElementStyles(Object obj, String str, StyleAccumulator styleAccumulator) {
        getNodeDescriptor(obj).getStyles(obj, str, styleAccumulator);
    }

    public void setElementStyle(Object obj, String str, String str2, String str3) {
        getNodeDescriptor(obj).setStyle(obj, str, str2, str3);
    }

    public void getElementComputedStyles(Object obj, ComputedStyleAccumulator computedStyleAccumulator) {
        getNodeDescriptor(obj).getComputedStyles(obj, computedStyleAccumulator);
    }

    public DocumentView getDocumentView() {
        verifyThreadAccess();
        return this.mShadowDocument;
    }

    public Object getRootElement() {
        verifyThreadAccess();
        Object rootElement = this.mDocumentProvider.getRootElement();
        if (rootElement == null) {
            throw new IllegalStateException();
        } else if (rootElement == this.mShadowDocument.getRootElement()) {
            return rootElement;
        } else {
            throw new IllegalStateException();
        }
    }

    public void findMatchingElements(String str, Accumulator<Integer> accumulator) {
        verifyThreadAccess();
        findMatches(this.mDocumentProvider.getRootElement(), Pattern.compile(Pattern.quote(str), 2), accumulator);
    }

    private void findMatches(Object obj, Pattern pattern, Accumulator<Integer> accumulator) {
        ElementInfo elementInfo = this.mShadowDocument.getElementInfo(obj);
        int size = elementInfo.children.size();
        for (int i = 0; i < size; i++) {
            Object obj2 = elementInfo.children.get(i);
            if (doesElementMatch(obj2, pattern)) {
                accumulator.store(this.mObjectIdMapper.getIdForObject(obj2));
            }
            findMatches(obj2, pattern, accumulator);
        }
    }

    private boolean doesElementMatch(Object obj, Pattern pattern) {
        AttributeListAccumulator acquireCachedAttributeAccumulator = acquireCachedAttributeAccumulator();
        NodeDescriptor nodeDescriptor = this.mDocumentProvider.getNodeDescriptor(obj);
        nodeDescriptor.getAttributes(obj, acquireCachedAttributeAccumulator);
        int size = acquireCachedAttributeAccumulator.size();
        for (int i = 0; i < size; i++) {
            if (pattern.matcher((CharSequence) acquireCachedAttributeAccumulator.get(i)).find()) {
                releaseCachedAttributeAccumulator(acquireCachedAttributeAccumulator);
                return true;
            }
        }
        releaseCachedAttributeAccumulator(acquireCachedAttributeAccumulator);
        return pattern.matcher(nodeDescriptor.getNodeName(obj)).find();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ChildEventingList acquireChildEventingList(Object obj, DocumentView documentView) {
        ChildEventingList childEventingList = this.mCachedChildEventingList;
        if (childEventingList == null) {
            childEventingList = new ChildEventingList();
        }
        this.mCachedChildEventingList = null;
        childEventingList.acquire(obj, documentView);
        return childEventingList;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void releaseChildEventingList(ChildEventingList childEventingList) {
        childEventingList.release();
        if (this.mCachedChildEventingList == null) {
            this.mCachedChildEventingList = childEventingList;
        }
    }

    private AttributeListAccumulator acquireCachedAttributeAccumulator() {
        AttributeListAccumulator attributeListAccumulator = this.mCachedAttributeAccumulator;
        if (attributeListAccumulator == null) {
            attributeListAccumulator = new AttributeListAccumulator();
        }
        this.mCachedChildrenAccumulator = null;
        return attributeListAccumulator;
    }

    private void releaseCachedAttributeAccumulator(AttributeListAccumulator attributeListAccumulator) {
        attributeListAccumulator.clear();
        if (this.mCachedAttributeAccumulator == null) {
            this.mCachedAttributeAccumulator = attributeListAccumulator;
        }
    }

    private ArrayListAccumulator<Object> acquireChildrenAccumulator() {
        ArrayListAccumulator<Object> arrayListAccumulator = this.mCachedChildrenAccumulator;
        if (arrayListAccumulator == null) {
            arrayListAccumulator = new ArrayListAccumulator<>();
        }
        this.mCachedChildrenAccumulator = null;
        return arrayListAccumulator;
    }

    private void releaseChildrenAccumulator(ArrayListAccumulator<Object> arrayListAccumulator) {
        arrayListAccumulator.clear();
        if (this.mCachedChildrenAccumulator == null) {
            this.mCachedChildrenAccumulator = arrayListAccumulator;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ShadowDocument.Update createShadowDocumentUpdate() {
        verifyThreadAccess();
        if (this.mDocumentProvider.getRootElement() == this.mShadowDocument.getRootElement()) {
            ArrayListAccumulator<Object> acquireChildrenAccumulator = acquireChildrenAccumulator();
            ShadowDocument.UpdateBuilder beginUpdate = this.mShadowDocument.beginUpdate();
            this.mCachedUpdateQueue.add(this.mDocumentProvider.getRootElement());
            while (!this.mCachedUpdateQueue.isEmpty()) {
                Object remove = this.mCachedUpdateQueue.remove();
                NodeDescriptor nodeDescriptor = this.mDocumentProvider.getNodeDescriptor(remove);
                this.mObjectIdMapper.putObject(remove);
                nodeDescriptor.getChildren(remove, acquireChildrenAccumulator);
                int size = acquireChildrenAccumulator.size();
                int i = 0;
                while (i < size) {
                    Object obj = acquireChildrenAccumulator.get(i);
                    if (obj != null) {
                        this.mCachedUpdateQueue.add(obj);
                    } else {
                        LogUtil.e("%s.getChildren() emitted a null child at position %s for element %s", nodeDescriptor.getClass().getName(), Integer.toString(i), remove);
                        acquireChildrenAccumulator.remove(i);
                        i--;
                        size--;
                    }
                    i++;
                }
                beginUpdate.setElementChildren(remove, acquireChildrenAccumulator);
                acquireChildrenAccumulator.clear();
            }
            releaseChildrenAccumulator(acquireChildrenAccumulator);
            return beginUpdate.build();
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateTree() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        ShadowDocument.Update createShadowDocumentUpdate = createShadowDocumentUpdate();
        boolean isEmpty = createShadowDocumentUpdate.isEmpty();
        if (isEmpty) {
            createShadowDocumentUpdate.abandon();
        } else {
            applyDocumentUpdate(createShadowDocumentUpdate);
        }
        long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
        Object[] objArr = new Object[2];
        objArr[0] = Long.toString(elapsedRealtime2);
        objArr[1] = isEmpty ? " (no changes)" : "";
        LogUtil.d("Document.updateTree() completed in %s ms%s", objArr);
    }

    private void applyDocumentUpdate(final ShadowDocument.Update update) {
        final ArrayList arrayList = new ArrayList();
        update.getGarbageElements(new Accumulator<Object>() {
            /* class com.facebook.stetho.inspector.elements.Document.AnonymousClass3 */

            @Override // com.facebook.stetho.common.Accumulator
            public void store(Object obj) {
                Integer num = (Integer) Util.throwIfNull(Document.this.mObjectIdMapper.getIdForObject(obj));
                if (update.getElementInfo(obj).parentElement == null) {
                    Document.this.mUpdateListeners.onChildNodeRemoved(Document.this.mObjectIdMapper.getIdForObject(Document.this.mShadowDocument.getElementInfo(obj).parentElement).intValue(), num.intValue());
                }
                arrayList.add(num);
            }
        });
        Collections.sort(arrayList);
        update.getChangedElements(new Accumulator<Object>() {
            /* class com.facebook.stetho.inspector.elements.Document.AnonymousClass4 */

            @Override // com.facebook.stetho.common.Accumulator
            public void store(Object obj) {
                ElementInfo elementInfo;
                Integer num = (Integer) Util.throwIfNull(Document.this.mObjectIdMapper.getIdForObject(obj));
                if (Collections.binarySearch(arrayList, num) < 0 && (elementInfo = Document.this.mShadowDocument.getElementInfo(obj)) != null && update.getElementInfo(obj).parentElement != elementInfo.parentElement) {
                    Document.this.mUpdateListeners.onChildNodeRemoved(Document.this.mObjectIdMapper.getIdForObject(elementInfo.parentElement).intValue(), num.intValue());
                }
            }
        });
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.mObjectIdMapper.removeObjectById(((Integer) arrayList.get(i)).intValue());
        }
        update.getChangedElements(new Accumulator<Object>() {
            /* class com.facebook.stetho.inspector.elements.Document.AnonymousClass5 */
            private Accumulator<Object> insertedElements = new Accumulator<Object>() {
                /* class com.facebook.stetho.inspector.elements.Document.AnonymousClass5.AnonymousClass1 */

                @Override // com.facebook.stetho.common.Accumulator
                public void store(Object obj) {
                    if (update.isElementChanged(obj)) {
                        AnonymousClass5.this.listenerInsertedElements.add(obj);
                    }
                }
            };
            private final HashSet<Object> listenerInsertedElements = new HashSet<>();

            @Override // com.facebook.stetho.common.Accumulator
            public void store(Object obj) {
                List<Object> list;
                ElementInfo elementInfo;
                if (Document.this.mObjectIdMapper.containsObject(obj) && !this.listenerInsertedElements.contains(obj)) {
                    ElementInfo elementInfo2 = Document.this.mShadowDocument.getElementInfo(obj);
                    ElementInfo elementInfo3 = update.getElementInfo(obj);
                    if (elementInfo2 != null) {
                        list = elementInfo2.children;
                    } else {
                        list = Collections.emptyList();
                    }
                    List<Object> list2 = elementInfo3.children;
                    ChildEventingList acquireChildEventingList = Document.this.acquireChildEventingList(obj, update);
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        Object obj2 = list.get(i);
                        if (Document.this.mObjectIdMapper.containsObject(obj2) && ((elementInfo = update.getElementInfo(obj2)) == null || elementInfo.parentElement == obj)) {
                            acquireChildEventingList.add(obj2);
                        }
                    }
                    Document.updateListenerChildren(acquireChildEventingList, list2, this.insertedElements);
                    Document.this.releaseChildEventingList(acquireChildEventingList);
                }
            }
        });
        update.commit();
    }

    /* access modifiers changed from: private */
    public static void updateListenerChildren(ChildEventingList childEventingList, List<Object> list, Accumulator<Object> accumulator) {
        int i = 0;
        while (i <= childEventingList.size()) {
            if (i == childEventingList.size()) {
                if (i != list.size()) {
                    childEventingList.addWithEvent(i, list.get(i), accumulator);
                } else {
                    return;
                }
            } else if (i == list.size()) {
                childEventingList.removeWithEvent(i);
            } else {
                Object obj = childEventingList.get(i);
                Object obj2 = list.get(i);
                if (obj != obj2) {
                    int indexOf = childEventingList.indexOf(obj2);
                    if (indexOf == -1) {
                        childEventingList.addWithEvent(i, obj2, accumulator);
                    } else {
                        childEventingList.removeWithEvent(indexOf);
                        childEventingList.addWithEvent(i, obj2, accumulator);
                    }
                }
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    public final class ChildEventingList extends ArrayList<Object> {
        private DocumentView mDocumentView;
        private Object mParentElement;
        private int mParentNodeId;

        private ChildEventingList() {
            this.mParentElement = null;
            this.mParentNodeId = -1;
        }

        public void acquire(Object obj, DocumentView documentView) {
            int i;
            this.mParentElement = obj;
            if (this.mParentElement == null) {
                i = -1;
            } else {
                i = Document.this.mObjectIdMapper.getIdForObject(this.mParentElement).intValue();
            }
            this.mParentNodeId = i;
            this.mDocumentView = documentView;
        }

        public void release() {
            clear();
            this.mParentElement = null;
            this.mParentNodeId = -1;
            this.mDocumentView = null;
        }

        public void addWithEvent(int i, Object obj, Accumulator<Object> accumulator) {
            int i2;
            Object obj2 = i == 0 ? null : get(i - 1);
            if (obj2 == null) {
                i2 = -1;
            } else {
                i2 = Document.this.mObjectIdMapper.getIdForObject(obj2).intValue();
            }
            add(i, obj);
            Document.this.mUpdateListeners.onChildNodeInserted(this.mDocumentView, obj, this.mParentNodeId, i2, accumulator);
        }

        public void removeWithEvent(int i) {
            Document.this.mUpdateListeners.onChildNodeRemoved(this.mParentNodeId, Document.this.mObjectIdMapper.getIdForObject(remove(i)).intValue());
        }
    }

    /* access modifiers changed from: private */
    public class UpdateListenerCollection implements UpdateListener {
        private final List<UpdateListener> mListeners = new ArrayList();
        private volatile UpdateListener[] mListenersSnapshot;

        public UpdateListenerCollection() {
        }

        public synchronized void add(UpdateListener updateListener) {
            this.mListeners.add(updateListener);
            this.mListenersSnapshot = null;
        }

        public synchronized void remove(UpdateListener updateListener) {
            this.mListeners.remove(updateListener);
            this.mListenersSnapshot = null;
        }

        public synchronized void clear() {
            this.mListeners.clear();
            this.mListenersSnapshot = null;
        }

        private UpdateListener[] getListenersSnapshot() {
            while (true) {
                UpdateListener[] updateListenerArr = this.mListenersSnapshot;
                if (updateListenerArr != null) {
                    return updateListenerArr;
                }
                synchronized (this) {
                    if (this.mListenersSnapshot == null) {
                        this.mListenersSnapshot = (UpdateListener[]) this.mListeners.toArray(new UpdateListener[this.mListeners.size()]);
                        return this.mListenersSnapshot;
                    }
                }
            }
        }

        @Override // com.facebook.stetho.inspector.elements.Document.UpdateListener
        public void onAttributeModified(Object obj, String str, String str2) {
            for (UpdateListener updateListener : getListenersSnapshot()) {
                updateListener.onAttributeModified(obj, str, str2);
            }
        }

        @Override // com.facebook.stetho.inspector.elements.Document.UpdateListener
        public void onAttributeRemoved(Object obj, String str) {
            for (UpdateListener updateListener : getListenersSnapshot()) {
                updateListener.onAttributeRemoved(obj, str);
            }
        }

        @Override // com.facebook.stetho.inspector.elements.Document.UpdateListener
        public void onInspectRequested(Object obj) {
            for (UpdateListener updateListener : getListenersSnapshot()) {
                updateListener.onInspectRequested(obj);
            }
        }

        @Override // com.facebook.stetho.inspector.elements.Document.UpdateListener
        public void onChildNodeRemoved(int i, int i2) {
            for (UpdateListener updateListener : getListenersSnapshot()) {
                updateListener.onChildNodeRemoved(i, i2);
            }
        }

        @Override // com.facebook.stetho.inspector.elements.Document.UpdateListener
        public void onChildNodeInserted(DocumentView documentView, Object obj, int i, int i2, Accumulator<Object> accumulator) {
            for (UpdateListener updateListener : getListenersSnapshot()) {
                updateListener.onChildNodeInserted(documentView, obj, i, i2, accumulator);
            }
        }
    }

    private final class DocumentObjectIdMapper extends ObjectIdMapper {
        private DocumentObjectIdMapper() {
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.stetho.inspector.helper.ObjectIdMapper
        public void onMapped(Object obj, int i) {
            Document.this.verifyThreadAccess();
            Document.this.mDocumentProvider.getNodeDescriptor(obj).hook(obj);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.stetho.inspector.helper.ObjectIdMapper
        public void onUnmapped(Object obj, int i) {
            Document.this.verifyThreadAccess();
            Document.this.mDocumentProvider.getNodeDescriptor(obj).unhook(obj);
        }
    }

    private final class ProviderListener implements DocumentProviderListener {
        private ProviderListener() {
        }

        @Override // com.facebook.stetho.inspector.elements.DocumentProviderListener
        public void onPossiblyChanged() {
            Document.this.updateTree();
        }

        @Override // com.facebook.stetho.inspector.elements.DocumentProviderListener
        public void onAttributeModified(Object obj, String str, String str2) {
            Document.this.verifyThreadAccess();
            Document.this.mUpdateListeners.onAttributeModified(obj, str, str2);
        }

        @Override // com.facebook.stetho.inspector.elements.DocumentProviderListener
        public void onAttributeRemoved(Object obj, String str) {
            Document.this.verifyThreadAccess();
            Document.this.mUpdateListeners.onAttributeRemoved(obj, str);
        }

        @Override // com.facebook.stetho.inspector.elements.DocumentProviderListener
        public void onInspectRequested(Object obj) {
            Document.this.verifyThreadAccess();
            Document.this.mUpdateListeners.onInspectRequested(obj);
        }
    }

    public static final class AttributeListAccumulator extends ArrayList<String> implements AttributeAccumulator {
        @Override // com.facebook.stetho.inspector.elements.AttributeAccumulator
        public void store(String str, String str2) {
            add(str);
            add(str2);
        }
    }
}
