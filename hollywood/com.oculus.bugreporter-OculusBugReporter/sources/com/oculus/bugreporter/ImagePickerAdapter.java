package com.oculus.bugreporter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerViewHolder> {
    private static final int MAX_IMAGES_TO_DISPLAY = 5;
    private final Context mContext;
    private List<ImageData> mDataset = new ArrayList();
    private ImageSelectedCallback mImageSelectedCallback;

    public static class ImageData {
        public String name;
        public String path;
    }

    public interface ImageSelectedCallback {
        void onImageSelected(ImageData imageData);
    }

    public static class ImagePickerViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImage;
        public TextView mTitle;
        public View mView;

        public ImagePickerViewHolder(View view) {
            super(view);
            this.mView = view;
            this.mTitle = (TextView) view.findViewById(R.id.title);
            this.mImage = (ImageView) view.findViewById(R.id.image);
        }
    }

    public ImagePickerAdapter(Context context) {
        this.mContext = context;
        fetchData();
    }

    public void setImageSelectedCallback(ImageSelectedCallback callback) {
        this.mImageSelectedCallback = callback;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public ImagePickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImagePickerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_with_text_view, parent, false));
    }

    public void onBindViewHolder(ImagePickerViewHolder holder, final int position) {
        ImageData data = this.mDataset.get(position);
        holder.mTitle.setText(data.name);
        holder.mImage.setImageURI(Uri.fromFile(new File(data.path)));
        holder.mImage.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.bugreporter.ImagePickerAdapter.AnonymousClass1 */

            public void onClick(View v) {
                if (ImagePickerAdapter.this.mImageSelectedCallback != null) {
                    ImagePickerAdapter.this.mImageSelectedCallback.onImageSelected((ImageData) ImagePickerAdapter.this.mDataset.get(position));
                }
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDataset.size();
    }

    private void fetchData() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "title"}, null, null, "date_added" + " DESC");
        int i = 0;
        while (i < 5 && cursor.moveToNext()) {
            i++;
            ImageData data = new ImageData();
            data.path = cursor.getString(0);
            data.name = cursor.getString(1);
            this.mDataset.add(data);
        }
        cursor.close();
    }
}
