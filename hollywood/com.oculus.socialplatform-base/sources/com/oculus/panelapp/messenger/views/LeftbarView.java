package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1BE;
import X.AnonymousClass1CG;
import X.AnonymousClass2NC;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewStub;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.navbar.ProfileType;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNav;
import com.oculus.common.socialtablet.navbar.SocialTabletType;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.MailboxListener;
import com.oculus.panelapp.messenger.api.MessengerAPIManager;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.api.ThreadListener;
import com.oculus.panelapp.messenger.api.models.DraftThread;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerViewV2Binding;
import com.oculus.panelapp.messenger.views.LeftbarView;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeftbarView {
    public static final String TAG = LoggingUtil.tag(LeftbarView.class);
    public static final int THREAD_BATCH_SIZE = 5;
    public ThreadListAdapter mAdapter;
    public AnytimeTabletMessengerViewV2Binding mBinding;
    public boolean mCanShowNullState;
    public MessengerAPIManager.DraftThreadListener mCreateThreadListener;
    public Handler mHandler;
    public boolean mIsDraftingThread = false;
    public boolean mLastScrollAtEndOfList;
    public MailboxListener mMailboxListener;
    public MessengerPanelApp mPanelApp;
    public ThreadListener mThreadListener;

    public /* synthetic */ void lambda$new$1$LeftbarView() {
        this.mCanShowNullState = true;
        maybeShowThreadListNullState();
    }

    private void hideThreadListNullState() {
        View view = this.mBinding.threadListNullStateViewStub.A00;
        if (view != null && view.getVisibility() != 8) {
            this.mBinding.threadListNullStateViewStub.A00.setVisibility(8);
        }
    }

    private void initializeRecyclerView() {
        OCRecyclerView oCRecyclerView = this.mBinding.threadList;
        oCRecyclerView.mHasFixedSize = true;
        oCRecyclerView.addOnScrollListener(new AnonymousClass1CG() {
            /* class com.oculus.panelapp.messenger.views.LeftbarView.AnonymousClass5 */

            @Override // X.AnonymousClass1CG
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (((LinearLayoutManager) recyclerView.mLayout).findLastCompletelyVisibleItemPosition() == LeftbarView.this.mAdapter.getItemCount() - 1) {
                    LeftbarView leftbarView = LeftbarView.this;
                    if (!leftbarView.mLastScrollAtEndOfList) {
                        leftbarView.mLastScrollAtEndOfList = true;
                        leftbarView.mPanelApp.getAPIManager().mCurrentAPI.updateThreadCount(LeftbarView.this.mAdapter.getItemCount() + 5);
                        return;
                    }
                }
                LeftbarView.this.mLastScrollAtEndOfList = false;
            }
        });
    }

    private void initializeThreadCreate() {
        OCButton oCButton = this.mBinding.threadCreate;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$LeftbarView$bTpWmrMhl_Zcj1C5nsar0edRSA2 */

            public final void onClick(View view) {
                LeftbarView.this.lambda$initializeThreadCreate$2$LeftbarView(view);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeShowThreadListNullState() {
        if (!this.mCanShowNullState) {
            return;
        }
        if (this.mAdapter.getItemCount() == 0) {
            showThreadListNullState();
        } else {
            hideThreadListNullState();
        }
    }

    private void showThreadListNullState() {
        AnonymousClass2NC r0 = this.mBinding.threadListNullStateViewStub;
        View view = r0.A00;
        if (view != null) {
            view.setVisibility(0);
        } else {
            r0.A02.inflate();
        }
    }

    public void destroy() {
        this.mPanelApp.getAPIManager().removeMailboxListener(this.mMailboxListener);
        this.mPanelApp.getAPIManager().removeCurrentThreadListener(this.mThreadListener);
        this.mPanelApp.getAPIManager().removeDraftThreadListener(this.mCreateThreadListener);
        this.mHandler.removeCallbacksAndMessages(null);
        AnytimeTabletMessengerViewV2Binding anytimeTabletMessengerViewV2Binding = this.mBinding;
        OCButton oCButton = anytimeTabletMessengerViewV2Binding.threadCreate;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
        anytimeTabletMessengerViewV2Binding.leftnav.destroy();
    }

    public /* synthetic */ void lambda$initializeThreadCreate$2$LeftbarView(View view) {
        this.mPanelApp.getAPIManager().updateDraftThread(new DraftThread(this.mPanelApp));
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_THREAD_CREATE, SurfaceType.THREAD_VIEW);
    }

    public LeftbarView(MessengerPanelApp messengerPanelApp, AnytimeTabletMessengerViewV2Binding anytimeTabletMessengerViewV2Binding, Context context, MessengerViewModel messengerViewModel) {
        ProfileType profileType;
        this.mPanelApp = messengerPanelApp;
        this.mBinding = anytimeTabletMessengerViewV2Binding;
        initializeRecyclerView();
        SocialTabletSideNav socialTabletSideNav = anytimeTabletMessengerViewV2Binding.leftnav;
        SocialTabletType socialTabletType = SocialTabletType.CHAT;
        if (this.mPanelApp.getAPIManager().mCurrentAPI.getType() == MessengerAPIType.OC_CHATS) {
            profileType = ProfileType.OCULUS;
        } else {
            profileType = ProfileType.FACEBOOK;
        }
        socialTabletSideNav.initialize(messengerPanelApp, socialTabletType, profileType);
        initializeThreadCreate();
        ThreadListAdapter threadListAdapter = new ThreadListAdapter(messengerPanelApp);
        this.mAdapter = threadListAdapter;
        threadListAdapter.setHasStableIds(true);
        threadListAdapter.registerAdapterDataObserver(new AnonymousClass1BE() {
            /* class com.oculus.panelapp.messenger.views.LeftbarView.AnonymousClass1 */

            @Override // X.AnonymousClass1BE
            public void onItemRangeChanged(int i, int i2) {
                super.onItemRangeChanged(i, i2);
                int findFirstVisibleItemPosition = ((LinearLayoutManager) LeftbarView.this.mBinding.threadList.mLayout).findFirstVisibleItemPosition();
                if (LeftbarView.this.mAdapter.getItemCount() != 0 && findFirstVisibleItemPosition < 2 && i == 0) {
                    LeftbarView.this.mBinding.threadList.smoothScrollToPosition(0);
                }
            }
        });
        this.mBinding.threadList.setAdapter(this.mAdapter);
        AnonymousClass2NC r2 = this.mBinding.threadListNullStateViewStub;
        $$Lambda$LeftbarView$3zP0pwPfaAXk_2duTgdr1poxaQ2 r1 = new ViewStub.OnInflateListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$LeftbarView$3zP0pwPfaAXk_2duTgdr1poxaQ2 */

            public final void onInflate(ViewStub viewStub, View view) {
                LeftbarView.lambda$new$0(MessengerViewModel.this, viewStub, view);
            }
        };
        if (r2.A02 != null) {
            r2.A01 = r1;
        }
        Handler handler = new Handler(context.getMainLooper());
        this.mHandler = handler;
        handler.postDelayed(new Runnable() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$LeftbarView$rI_Lmlac8Gp8OlEliQlSMqe6q0Y2 */

            public final void run() {
                LeftbarView.this.lambda$new$1$LeftbarView();
            }
        }, 1000);
        this.mMailboxListener = new MailboxListener() {
            /* class com.oculus.panelapp.messenger.views.LeftbarView.AnonymousClass2 */

            public static /* synthetic */ ExistingThreadAdapterItem lambda$onMailboxUpdate$0(MessengerThread messengerThread) {
                return new ExistingThreadAdapterItem(messengerThread);
            }

            public /* synthetic */ void lambda$onMailboxUpdate$1$LeftbarView$2() {
                LeftbarView.this.maybeShowThreadListNullState();
            }

            @Override // com.oculus.panelapp.messenger.api.MailboxListener
            public void onMailboxUpdate(List<MessengerThread> list) {
                AbstractList abstractList = (AbstractList) list.stream().map($$Lambda$LeftbarView$2$gwujBor2IBWcIcPbQQ3pI8eyqr02.INSTANCE).collect(Collectors.toCollection($$Lambda$OGSS2qx6njxlnp0dnKb4lA3jnw82.INSTANCE));
                DraftThread draftThread = LeftbarView.this.mPanelApp.getAPIManager().mDraftThread;
                if (draftThread != null) {
                    abstractList.add(0, new DraftThreadAdapterItem(draftThread));
                }
                LeftbarView.this.mAdapter.submitList(abstractList, new Runnable() {
                    /* class com.oculus.panelapp.messenger.views.$$Lambda$LeftbarView$2$I8UtK7DvLwp2s32y7q5uKAKcU0Q2 */

                    public final void run() {
                        LeftbarView.AnonymousClass2.this.lambda$onMailboxUpdate$1$LeftbarView$2();
                    }
                });
            }
        };
        this.mThreadListener = new ThreadListener() {
            /* class com.oculus.panelapp.messenger.views.LeftbarView.AnonymousClass3 */

            @Override // com.oculus.panelapp.messenger.api.ThreadListener
            public void onThreadUpdate(MessengerThread messengerThread, List<MessengerMessage> list) {
                LeftbarView.this.mAdapter.notifyDataSetChanged();
                LeftbarView.this.maybeShowThreadListNullState();
            }
        };
        this.mCreateThreadListener = new MessengerAPIManager.DraftThreadListener() {
            /* class com.oculus.panelapp.messenger.views.LeftbarView.AnonymousClass4 */

            @Override // com.oculus.panelapp.messenger.api.MessengerAPIManager.DraftThreadListener
            public void onDraftThreadUpdate(DraftThread draftThread) {
                boolean z = false;
                LeftbarView leftbarView = LeftbarView.this;
                if (draftThread != null) {
                    if (!leftbarView.mIsDraftingThread) {
                        ArrayList arrayList = new ArrayList(leftbarView.mAdapter.mDiffer.A03);
                        arrayList.add(0, new DraftThreadAdapterItem(draftThread));
                        LeftbarView.this.mAdapter.submitList(arrayList, new Runnable() {
                            /* class com.oculus.panelapp.messenger.views.$$Lambda$LeftbarView$4$roWAAEMPWJMwpitBNRhaCWh_5U2 */

                            public final void run() {
                                LeftbarView.AnonymousClass4.this.lambda$onDraftThreadUpdate$0$LeftbarView$4();
                            }
                        });
                    }
                } else if (leftbarView.mIsDraftingThread) {
                    ArrayList arrayList2 = new ArrayList(leftbarView.mAdapter.mDiffer.A03);
                    arrayList2.remove(0);
                    LeftbarView.this.mAdapter.submitList(arrayList2, new Runnable() {
                        /* class com.oculus.panelapp.messenger.views.$$Lambda$LeftbarView$4$OHERD0yr2KIQUQqt0a9IpOeBJQ2 */

                        public final void run() {
                            LeftbarView.AnonymousClass4.this.lambda$onDraftThreadUpdate$1$LeftbarView$4();
                        }
                    });
                }
                LeftbarView.this.mAdapter.notifyDataSetChanged();
                LeftbarView leftbarView2 = LeftbarView.this;
                if (draftThread != null) {
                    z = true;
                }
                leftbarView2.mIsDraftingThread = z;
            }

            public /* synthetic */ void lambda$onDraftThreadUpdate$0$LeftbarView$4() {
                LeftbarView.this.mBinding.threadList.scrollToPosition(0);
                LeftbarView.this.maybeShowThreadListNullState();
            }

            public /* synthetic */ void lambda$onDraftThreadUpdate$1$LeftbarView$4() {
                LeftbarView.this.maybeShowThreadListNullState();
            }
        };
        this.mPanelApp.getAPIManager().registerMailboxListener(this.mMailboxListener);
        this.mPanelApp.getAPIManager().registerCurrentThreadListener(this.mThreadListener);
        this.mPanelApp.getAPIManager().registerDraftThreadListener(this.mCreateThreadListener);
    }
}
