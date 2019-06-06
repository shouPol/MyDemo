package cn.humanetplan.mydemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.List;

public class PopwindowSingleChoose<T> extends PopupWindow implements View.OnClickListener {

    private ListView lv_pums;

    private Context context;

    private int windowWidth = 0;
    private int selectPos = 0;//选择的位置
    String names = "";//选中的文本
    private DataChoosedListner listner;//选择回调事假

    //设置选择回调
    public void setListner(DataChoosedListner listner) {
        this.listner = listner;
    }

    private AdapterBaseChoosed mAdapter;//通用选择adapter
    private String typeName; //要显示的内容在实体中对应的属性名称
    private List<T> dataList;//下拉显示的数据源

    //设置选择位置
    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
        if (mAdapter != null) {
            mAdapter.setSelectPosition(selectPos);
        }
    }

    //设置弹出宽度
    public void setWindowWidth(int width) {
        this.windowWidth = width;
        this.setWidth(windowWidth);
    }

    //设置数据源
    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
        initAdapter();
    }


    public PopwindowSingleChoose(final Context context, int width, String typeName) {

        this(context, null, width, typeName);


    }

    public PopwindowSingleChoose(final Context context, List<T> list, int width, String typeName) {
        super(context);
        this.context = context;
        this.windowWidth = width;
        this.typeName = typeName;
        this.dataList = list;


        final View view = View.inflate(context, R.layout.pop_sigle_choose, null);
        lv_pums = (ListView) view.findViewById(R.id.lv_pums);

        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
//        this.setOutsideTouchable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//		this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //此处是设置点击其他地方隐藏窗口
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.ll_content).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

        initAdapter();

    }

    /**
     * 初始化数据
     */
    private void initAdapter() {

        if (dataList == null) {
            return;
        }

        mAdapter = new AdapterBaseChoosed(context, typeName, R.layout.item_listview_single_text) {
        };
        mAdapter.setDatas(dataList);
        names = Utils.getAttributeValue(dataList.get(selectPos), typeName);

        lv_pums.setAdapter(mAdapter);
        mAdapter.setSelectPosition(selectPos);
        lv_pums.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                names = Utils.getAttributeValue(dataList.get(position), typeName);
                selectPos = position;
                mAdapter.setSelectPosition(selectPos);

                if (listner != null) {
                    listner.onDataChoosed(names, selectPos);
                    dismiss();
                }
            }
        });


    }

    @Override
    public void onClick(View v) {


    }

    /**
     * 回调接口
     *
     * @author Administrator
     */
    public interface DataChoosedListner {
        void onDataChoosed(String names, int pos);
    }


}