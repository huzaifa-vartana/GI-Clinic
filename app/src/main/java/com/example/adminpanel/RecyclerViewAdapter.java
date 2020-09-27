package com.example.adminpanel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "view";

    //vars
    public ArrayList<String> mNames = new ArrayList<>();
    public ArrayList<String> mImageUrls = new ArrayList<>();
    public ArrayList<String> episode = new ArrayList<>();
    public ArrayList<String> mVideoUrls = new ArrayList<>();
    public Context mContext;
    String c = "Category";
    String status;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> ImageUrls, ArrayList<String> videourls, ArrayList<String> episodes, String s) {
        mNames = names;
        mImageUrls = ImageUrls;
        mVideoUrls = videourls;
        mContext = context;
        episode = episodes;
        status = s;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        //Log.d(TAG, String.valueOf(mImageUrls.size()));
//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImageUrls.get(position))
//                .into(holder.image);
        Picasso.get().load(mImageUrls.get(position)).into(holder.image);


        //holder.name.setText(mNames.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mVideoUrls.contains(c)) {
//                    Toast.makeText(mContext,episode.get(position),0).show();
//                    Intent browserIntent3 = new Intent(mContext, defaultView.class);
//                    browserIntent3.putExtra("episodeLink", mVideoUrls.get(position));
//                    mContext.startActivity(browserIntent3);
//                    Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();

//                    Intent browserIntent3 = new Intent(mContext, defaultView.class);
//                    browserIntent3.putExtra("dramaName", mNames.get(position));
//                    mContext.startActivity(browserIntent3);
                    Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();


                } else {
                    //Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
                    Log.d(TAG, String.valueOf(mVideoUrls.get(position)));
                    Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();

//                    mInterstitialAd.show();
//                    mInterstitialAd.setAdListener(new AdListener() {
//                        @Override
//                        public void onAdClosed() {
//                            super.onAdClosed();
//
//                            Intent browserIntent3 = new Intent(mContext, videoPlayer.class);
//                            browserIntent3.putExtra("episodeLink", mVideoUrls.get(position));
//                            mContext.startActivity(browserIntent3);
//                            Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
//                            mInterstitialAd.loadAd(new AdRequest.Builder().build());
//
//                        }
//                    });


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        //TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            //  name = itemView.findViewById(R.id.name);
        }
    }
}
