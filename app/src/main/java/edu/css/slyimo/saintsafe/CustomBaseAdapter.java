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

public class CustomBaseAdapter extends BaseAdapter {
    private static ArrayList<GetUsers> findArrayList;

    private LayoutInflater mInflater;

    public CustomBaseAdapter(Context context, ArrayList<GetUsers> result) {
        findArrayList = result;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return findArrayList.size();
    }

    public Object getItem(int position) {
        return findArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.main_view, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.name);

            holder.txtPhone = (TextView) convertView.findViewById(R.id.phone);

            holder.imageIcon=(ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtName.setText(findArrayList.get(position).getName());

        holder.txtPhone.setText(findArrayList.get(position).getPhone());

        holder.imageIcon.setImageResource(findArrayList.get(position).getIcon());

        return convertView;
    }

    static class ViewHolder {
        TextView txtName;
        TextView txtPhone;
        ImageView imageIcon;
    }
}