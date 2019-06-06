package cn.humanetplan.mydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.humanetplan.mydemo.Bean.DataType;
import cn.humanetplan.mydemo.Bean.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_type, tv_person;

    private List<Person> personList=new ArrayList<>();//负责人数据
    private List<DataType> typeList=new ArrayList<>();//类别数据

    PopwindowSingleChoose personPop, typePop;//popupwindow
    //选择负责人的回调
    private PopwindowSingleChoose.DataChoosedListner personChooseListener = new PopwindowSingleChoose.DataChoosedListner() {
        @Override
        public void onDataChoosed(String names, int pos) {
        tv_person.setText(names);

        }
    };
    //选择类型的回调
    private PopwindowSingleChoose.DataChoosedListner typeChooseListener = new PopwindowSingleChoose.DataChoosedListner() {
        @Override
        public void onDataChoosed(String names, int pos) {

        tv_type.setText(names);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitViews();
        InitData();
        InitListeners();

    }


    private void InitViews() {
        tv_person = findViewById(R.id.tv_person);
        tv_type = findViewById(R.id.tv_type);

    }

    private void InitListeners() {
        tv_person.setOnClickListener(this);
        tv_type.setOnClickListener(this);
    }


    private void InitData() {
        for (int i=0;i<6;i++){
            Person p=new Person((int)Math.random()*10+16,"人员"+(i+1));
            personList.add(p);

            DataType type=new DataType("类别"+(i+1),"type"+(i*10+1));
            typeList.add(type);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_person:
                showPop("person");
                break;
            case R.id.tv_type:
                showPop("type");
                break;
        }
    }

    private void showPop(String river) {
        PopwindowSingleChoose pop = null;
        switch (river) {
            case "person":
                if (personPop == null) {

                    personPop = new PopwindowSingleChoose(this, personList, tv_person.getWidth(), "name");
                    personPop.setListner(personChooseListener);
                }
                if (personPop.isShowing()) {
                    personPop.dismiss();
                } else {
                    personPop.showAsDropDown(tv_person, 0, 0);
                }
                break;

            case "type":

                if (typePop == null) {
                    typePop = new PopwindowSingleChoose(this, typeList, tv_type.getWidth(), "typeName");
                    typePop.setListner(typeChooseListener);
                }

                if (typePop.isShowing()) {
                    typePop.dismiss();
                } else {
                    typePop.showAsDropDown(tv_type, 0, 0);
                }
                break;


        }
    }

}
