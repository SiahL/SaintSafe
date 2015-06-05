package edu.css.slyimo.saintsafe;

/**
 * Created by siah on 4/28/15.
 */
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCustomBaseAdapter extends BaseAdapter {
    private static ArrayList<GetReportTypes> searchArrayList;

    private LayoutInflater mInflater;

    public MyCustomBaseAdapter(Context context, ArrayList<GetReportTypes> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.report_view, null);
            holder = new ViewHolder();
            holder.txtReportTitle = (TextView) convertView.findViewById(R.id.reportTitle);

            holder.imageIcon = (ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtReportTitle.setText(searchArrayList.get(position).getreportTitle());


        holder.imageIcon.setImageResource(searchArrayList.get(position).getIcon());


        return convertView;
    }

    static class ViewHolder {
        TextView txtReportTitle;
        ImageView imageIcon;
    }
}


