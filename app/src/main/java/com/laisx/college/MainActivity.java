package com.laisx.college;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by laisixiang on 2015/9/28.
 */
public class MainActivity extends TabActivity{

    private TabHost tabHost;
    private TabHost.TabSpec inout;
    private TabHost.TabSpec advertise;
    private TabHost.TabSpec phone;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_acitivity_main);
        initTab();
    }

    private void initTab(){
        tabHost = this.getTabHost();

        inout = tabHost.newTabSpec("inout");
        advertise = tabHost.newTabSpec("advertise");
        phone = tabHost.newTabSpec("phone");

        inout.setIndicator(createContent("�ִ�", R.drawable.selector_inout));
        advertise.setIndicator(createContent("�������", R.drawable.selector_advertise));
        phone.setIndicator(createContent("��ϵ��", R.drawable.selector_phone));

        inout.setContent(new Intent(this, InoutAcitivity.class));
        advertise.setContent(new Intent(this, AdvertiseActivity.class));
        phone.setContent(new Intent(this, PhoneActivity.class));

        tabHost.addTab(inout);
        tabHost.addTab(advertise);
        tabHost.addTab(phone);
        tabHost.setCurrentTab(0);   //����Ĭ��ҳ��

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                tabChanged(tabId);
            }
        });
    }


    /**
     * ��׽Tab�ı仯��setCurrentTabByTag�ɷ���setcurrentTab����ʱ�����ԣ�
     * @param tabId ��ǰѡ�� tab �� Id �� inout\advertise\phone
     */
    private void tabChanged(String tabId) {
        if (tabId.equals("inout")) {
            tabHost.setCurrentTabByTag("�ִ�");
        } else if (tabId.equals("advertise")) {
            tabHost.setCurrentTabByTag("�������");
        } else if (tabId.equals("phone")) {
            tabHost.setCurrentTabByTag("��ϵ��");
        }
    }


    /**
     * �� tab �е�ͼƬ�����ַ�װ��һ��view��������
     * TODO xml���滹�ж���û��
     * @param text
     * @param resid
     * @return
     */
    private View createContent(String text, int resid) {
        View view = LayoutInflater.from(this).inflate(R.layout.temp_main_tabwidget, null, false);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.img_name);
        tv_name.setText(text);
        iv_icon.setBackgroundResource(resid);
        return view;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("Laisx","getDown");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }

    }

}
