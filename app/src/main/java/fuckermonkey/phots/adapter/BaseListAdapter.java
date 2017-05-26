package fuckermonkey.phots.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by xiaowu on 2016/8/4.
 */
public class BaseListAdapter<T extends Object> extends RecyclerView.Adapter {
    protected Context mContext;
    protected int mScreenWidth;

    public void setScreenWidth(int width) {
        mScreenWidth = width;
    }

    protected ArrayList<T> mDataList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public void setDataList(Collection<T> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<T> list) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
//            notifyDataSetChanged();
        }
    }

    public void addAll(int index, Collection<T> list) {
        this.mDataList.addAll(index, list);
//        notifyItemRangeChanged(index, list.size() - 1);
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.add(item)) {
            notifyItemInserted(lastIndex);
        }
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    public void removeHeadData(int count) {
        for (int i = 0; i < count; i++) {
            this.mDataList.remove(0);
        }
    }
}

