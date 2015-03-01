package com.defch.wikimages.adptr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.defch.wikimages.R;
import com.defch.wikimages.model.ImageModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DiegoFranco on 3/1/15.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private static final String TAG = "ImageAdapterMD";
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    private RecyclerView mRecyclerView;
    private Context context;
    private ArrayList<ImageModel> imageModels;

    public ImageAdapter(Context context, ArrayList<ImageModel> imageModelArrayList, RecyclerView mRecyclerView) {
        this.imageModels = imageModelArrayList;
        this.context = context;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_image, viewGroup, false);
        v.setOnClickListener(clickView);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    View.OnClickListener clickView = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int itemPosition = mRecyclerView.getChildPosition(v);
            ImageModel model = imageModels.get(itemPosition);
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle(R.string.title_msg);
            dialog.setMessage(context.getResources().getString(R.string.page_id) + model.getPageId() + "\n" +
                    context.getResources().getString(R.string.title) + model.getTitle() + "\n" +
                    context.getResources().getString(R.string.index) + model.getIndex() + "\n" +
                    context.getResources().getString(R.string.width) + model.getThumbnailModel().getWidth() + "\n" +
                    context.getResources().getString(R.string.height) + model.getThumbnailModel().getHeight() + "\n" +
                    context.getResources().getString(R.string.source) + model.getThumbnailModel().getSource());
            dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    };

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        ImageModel imageModel = imageModels.get(pos);
        if(imageModel != null) {
            viewHolder.mTextView.setText(imageModel.getTitle());
            if(imageModel.getThumbnailModel() != null) {
                Picasso.with(context).load(imageModel.getThumbnailModel().getSource()).error(R.mipmap.ic_wmfnr).resize(WIDTH, HEIGHT).into(viewHolder.mImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i(TAG, "image load success");
                    }

                    @Override
                    public void onError() {
                        Log.i(TAG, "ERROR failed image");
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView mImageView;
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView)v.findViewById(R.id.row_imageview);
            mTextView = (TextView)v.findViewById(R.id.row_textview);
        }
    }

}
