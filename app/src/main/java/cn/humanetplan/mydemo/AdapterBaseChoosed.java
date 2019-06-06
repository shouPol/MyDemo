package cn.humanetplan.mydemo;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

/**
 *  2019/6/6.
 */

public abstract class AdapterBaseChoosed<T> extends AdapterBase<T> {
    private int selectPosition = -1;
    private String typeName;

    private Context context;


    //不带复选框
    public AdapterBaseChoosed(Context context, String typeName, int layoutId) {
        super(context, layoutId);
        this.context = context;
        this.typeName = typeName;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, T t, final int position) {
        TextView text = (TextView) viewHolder.getView(R.id.textViewName);

        String name = Utils.getAttributeValue(t, typeName);
        text.setText(name);

        if (position == selectPosition) {
            text.setTextColor(Color.WHITE);
            viewHolder.getViewGroup().setBackgroundColor(context.getResources().getColor(R.color.green_light));
        } else {
            text.setTextColor(context.getResources().getColor(R.color.item_content_new));
            viewHolder.getViewGroup().setBackgroundColor(context.getResources().getColor(R.color.white));
        }
    }
}
