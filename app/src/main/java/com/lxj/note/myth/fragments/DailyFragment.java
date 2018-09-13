package com.lxj.note.myth.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.note.myth.R;
import com.lxj.note.myth.RxBus.Event;
import com.lxj.note.myth.RxBus.RxBus;
import com.lxj.note.myth.adapter.QuickAdapter;
import com.lxj.note.myth.dialogs.DutyInfoDialogFragment;
import com.lxj.note.myth.ui.MainActivity;
import com.lxj.note.myth.utils.DbServices;
import com.lxj.note.myth.utils.SmoothCheckBox;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import rx.functions.Action1;
import test.greenDAO.bean.Duty;

/**
 * Created by castl on 2016/5/18.
 * 用于显示每天的课程
 */
public class DailyFragment extends RxFragment {


    private RxBus _rxBus;
    private RecyclerView Rv_duty;
    private QuickAdapter qadapter;
    private View rootView;
    private List<Duty> datas;
    private DbServices dbservices;


    public static final String TYPE = "TYPE";
    private String mytype;

    public DailyFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        DailyFragment dailyFragment = new DailyFragment();
        dailyFragment.setArguments(args);
        return dailyFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbservices = DbServices.getInstance(getContext());
        mytype = getArguments().getString(TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_dailyduty, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _rxBus = ((MainActivity) getActivity()).getRxBusSingleton();
        Rv_duty = (RecyclerView) rootView.findViewById(R.id.rv_duty);
        Rv_duty.setLayoutManager(new LinearLayoutManager(getContext()));
        //查询数据库
        datas = DbServices.getInstance(getContext()).queryNote(mytype);
        //实例化Adapter
        qadapter = new QuickAdapter(getContext(), datas);
        //设置动画
        qadapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        Rv_duty.setItemAnimator(new DefaultItemAnimator());

        Rv_duty.setAdapter(qadapter);
        qadapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Duty duty = (Duty) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.scb:
                        SmoothCheckBox scb = (SmoothCheckBox) view;
                        scb.setChecked(!scb.isChecked(), true);
                        duty.setStatus(scb.isChecked());
                        dbservices.saveNote(duty);
                        break;
                }

            }
        });
        qadapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                DutyInfoDialogFragment infodialog = new DutyInfoDialogFragment().newInstance(qadapter.getItem(i));
                infodialog.show(getActivity().getFragmentManager(), "aboutDialog");
            }
        });
        qadapter.setOnRecyclerViewItemLongClickListener(new BaseQuickAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int i) {
                dbservices.deleteNote(qadapter.getItem(i).getId());  //删除数据库中的数据
                qadapter.remove(i);
                return true;
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onStart() {
        super.onStart();
        _rxBus.toObserverable()
                .compose(this.bindToLifecycle())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object event) {
                        if (event instanceof Event.AddEvent) {
                            //如果 传来的 新增事件 和当前 查询结果类型一致 则直接往里面填充
                            if (((Event.AddEvent) event).getMduty().getType() == mytype) {
                                qadapter.add(0, ((Event.AddEvent) event).getMduty());

                            }
                        }
                    }
                });
    }
}
