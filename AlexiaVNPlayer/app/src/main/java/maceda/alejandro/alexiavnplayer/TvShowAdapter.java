package maceda.alejandro.alexiavnplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;



public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    List<TvShow> TvShowList;
    Context context;
    RecyclerView mRecyclerView;


    public TvShowAdapter(List<TvShow>TvShowList)
    {
        this.TvShowList = TvShowList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int  position) {
        final TvShow tvShow = TvShowList.get(position);

        holder.textTvShow.setText(tvShow.getTvshow());
        //holder.imgTvShow.setImageResource(tvShow.getImgTvshow());
        Picasso.get().load(new File(tvShow.getImgTvshow())).noFade().fit().into(holder.imgTvShow);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(context,"The position is:"+position + "NAME: " + holder.textTvShow.getText(),Toast.LENGTH_SHORT).show();
            //    Toast.makeText(context, "Opening ... " + holder.textTvShow.getText(),Toast.LENGTH_SHORT).show();
                start_alexavn( tvShow.getTvshow_id() , tvShow.getTvshow(), tvShow.getTvshow_path(), tvShow.getTvshow_file(), tvShow.getTvshow_savefile(), tvShow.getTvshow_line(), tvShow.getTvshow_username(), tvShow.getImgTvshow());

            }
        });

        holder.clean_recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(context,"The position is:"+position + "NAME: " + holder.textTvShow.getText(),Toast.LENGTH_SHORT).show();
               // Toast.makeText(context, "Deleting ... " + holder.textTvShow.getText(),Toast.LENGTH_SHORT).show();
                deleteRecent(tvShow.getTvshow_id(), position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return TvShowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgTvShow;
        TextView textTvShow;
        ImageButton clean_recent;
        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);
            imgTvShow = (ImageView)itemView.findViewById(R.id.imgTvshow);
            textTvShow = (TextView)itemView.findViewById(R.id.textTvshow);
            clean_recent = (ImageButton) itemView.findViewById(R.id.clean_recent_ib);
            cv = (CardView)itemView.findViewById(R.id.cv);
        }

    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
    }

    private void start_alexavn(long m_recent_id, String m_name, String m_path, String m_filename, String m_savefile, int m_line, String m_username, String m_image) {
        //show_toast(textSize);

        Intent alexavn = new Intent (context, alexavn.class);
        alexavn.putExtra("vnname", m_name);
        alexavn.putExtra("path", m_path);
        alexavn.putExtra("file", m_filename);
        alexavn.putExtra("savefile", m_filename);
        alexavn.putExtra("line", m_line);
        alexavn.putExtra("username", m_username);
        alexavn.putExtra("recent_id", m_recent_id);
        alexavn.putExtra("image", m_image);

        // alexavn.putExtra("textSize", Integer.valueOf(textSize));
        alexavn.putExtra("textSize", 16);
        alexavn.putExtra("start", 1);
        // alexavn.putExtra("theme", themeId);
        context.startActivity(alexavn);

    }
    private void deleteRecent( final long rowID, final int pos)
    {

        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setTitle(R.string.confirmTitle);
        alert.setMessage(R.string.confirmMessage);

        alert.setPositiveButton(R.string.delete_btn,
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int button)
                    {
                        final DatabaseConnector dbConnector =
                                new DatabaseConnector(context);

                        AsyncTask<Long, Object, Object> deleteTask =
                                new AsyncTask<Long, Object, Object>()
                                {
                                    @Override
                                    protected Object doInBackground(Long... params)
                                    {
                                         dbConnector.deleteRecent(params[0]);
                                        return null;
                                    }

                                    @Override
                                    protected void onPostExecute(Object result)
                                    {

                                        TvShowList.remove(pos);
                                        mRecyclerView.getAdapter().notifyDataSetChanged();

                                        dbConnector.close();



                                    }
                                };

                        deleteTask.execute(new Long[] { rowID });
                    }
                }
        );

        alert.setNegativeButton(R.string.cancel_btn, null).show();
    }


}