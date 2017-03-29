package adv.gvs.com.music;

import android.support.v4.app.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private FragmentTransaction ft;
    private FragmentManager fm;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button1.performClick();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                turnToFragment(MylistFragmrnt.class, PlayFragment.class, MineFragmrnt.class);
                break;
            case R.id.button2:
                turnToFragment(PlayFragment.class, MylistFragmrnt.class, MineFragmrnt.class);
                break;
            case R.id.button3:
                turnToFragment(MineFragmrnt.class, PlayFragment.class, MylistFragmrnt.class);
                break;
        }

    }

    public void turnToFragment(Class<? extends Fragment> toFragmentClass, Class<? extends Fragment>... fromFragmentClass) {
        //被切换的Fragment标签
            Fragment fromFragment0 = fm.findFragmentByTag(fromFragmentClass[0].getSimpleName());
            Fragment fromFragment1 = fm.findFragmentByTag(fromFragmentClass[1].getSimpleName());
        //切换到的Fragment标签
        String toTag = toFragmentClass.getSimpleName();
        //查找切换的Fragment
        Fragment toFragment = fm.findFragmentByTag(toTag);
        //如果要切换到的Fragment不存在，则创建
        if (toFragment == null) {
            try {
                toFragment = toFragmentClass.newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (fromFragment0 == null) {
            try {
                fromFragment0 = fromFragmentClass[0].newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if ( fromFragment1== null) {
            try {
                fromFragment1 = fromFragmentClass[1].newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //Fragment事务,要重新赋值,否则会报错
        ft = fm.beginTransaction();
        //设置Fragment切换效果
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
        /**
         * 如果要切换到的Fragment没有被Fragment事务添加，则隐藏被切换的Fragment，添加要切换的Fragment
         * 否则，则隐藏被切换的Fragment，显示要切换的Fragment
         */
        if (!toFragment.isAdded()) {
            ft.hide(fromFragment0).hide(fromFragment1).add(R.id.fragment, toFragment, toTag);
        } else {
            ft.hide(fromFragment0).hide(fromFragment1).show(toFragment);
        }
        //  ft.commitAllowingStateLoss()和commit()的区别就是要不要检查（mStateSaved）已经保存,mNoTransactionsBecause已经存在
        //不保留状态提交事务,不检查
        ft.commit();
    }
}
