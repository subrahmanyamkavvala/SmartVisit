package smartvisit.com.smartvisit.utils;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.List;

import smartvisit.com.smartvisit.R;
import smartvisit.com.smartvisit.db.VisitorsCheckIn;

/**
 * Created by subbu on 11/3/16.
 */
public class VisitorAdapter extends BaseAdapter {
    String TAG = "ConferenceAdapter";
    LayoutInflater inflater;
    Activity context;
    private List<VisitorsCheckIn> data;

    public VisitorAdapter(Activity context, List<VisitorsCheckIn> data) {
        super();
        this.context = context;
        this.data = data;
        inflater = context.getLayoutInflater();

    }

    @Override
    public int getCount() {

        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView vis_user;
        TextView vis_tomeet;
        TextView vis_checkintime;
        ImageView vis_profilePic;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        VisitorsCheckIn item = data.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_visitor, null);
            holder = new ViewHolder();
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        render(convertView, holder, item);

        return convertView;
    }

    private void render(View convertView, ViewHolder holder, VisitorsCheckIn item) {
       // Log.d(TAG, "render :conference Name :" + item.vis_company);

        holder.vis_user = (TextView) convertView.findViewById(R.id.vis_User);
        holder.vis_tomeet = (TextView) convertView.findViewById(R.id.vis_tomeet);
        holder.vis_checkintime= (TextView) convertView.findViewById(R.id.vis_checkintime);
        holder.vis_profilePic= (ImageView) convertView.findViewById(R.id.vis_profilepic);


        holder.vis_user.setText(item.vis_fullname);
        holder.vis_tomeet.setText(item.vis_tomeet);
        holder.vis_checkintime.setText(Utils.getSimpleDateFormat(item.vis_indate));

        if (item.vis_url_image!=null){

            Log.d(TAG,"vis_url_image"+item.vis_url_image);


            ImageSize targetSize = new ImageSize(80, 50); // result Bitmap will be fit to this size
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(item.vis_url_image,holder.vis_profilePic);

        }

    }


}
