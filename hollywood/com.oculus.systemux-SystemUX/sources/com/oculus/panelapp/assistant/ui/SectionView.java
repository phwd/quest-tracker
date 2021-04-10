package com.oculus.panelapp.assistant.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.flexbox.FlexboxLayout;
import com.oculus.panelapp.assistant.R;

public class SectionView extends FrameLayout {
    private TextView mHeader;
    private OnItemClickedListener mItemClickedListener;
    private FlexboxLayout mItems;

    public interface OnItemClickedListener {
        void onItemClicked(SectionItem sectionItem);
    }

    public static class SectionItem {
        private final String mId;
        private final String mLabel;

        public SectionItem(String str, String str2) {
            this.mId = str;
            this.mLabel = str2;
        }

        public String getId() {
            return this.mId;
        }

        public String getLabel() {
            return this.mLabel;
        }
    }

    public SectionView(Context context) {
        super(context);
        init(context);
    }

    public SectionView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SectionView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        addView((ViewGroup) LayoutInflater.from(context).inflate(R.layout.section_view, (ViewGroup) this, false));
        this.mHeader = (TextView) findViewById(R.id.header);
        this.mItems = (FlexboxLayout) findViewById(R.id.items);
    }

    public void addSectionItem(SectionItem sectionItem) {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.suggestion, (ViewGroup) this, false);
        textView.setText(sectionItem.getLabel());
        textView.setTag(sectionItem);
        textView.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.assistant.ui.$$Lambda$SectionView$CRNtKhB1qKk_CzTNtq_rYhBhA */

            public final void onClick(View view) {
                SectionView.this.lambda$addSectionItem$37$SectionView(view);
            }
        });
        this.mItems.addView(textView);
    }

    public /* synthetic */ void lambda$addSectionItem$37$SectionView(View view) {
        OnItemClickedListener onItemClickedListener = this.mItemClickedListener;
        if (onItemClickedListener != null) {
            onItemClickedListener.onItemClicked((SectionItem) view.getTag());
        }
    }

    public void setHeader(String str) {
        this.mHeader.setText(str);
    }

    public void setItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.mItemClickedListener = onItemClickedListener;
    }
}
