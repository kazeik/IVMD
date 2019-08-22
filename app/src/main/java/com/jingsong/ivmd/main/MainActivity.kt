package com.jingsong.ivmd.main


import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import android.widget.RadioGroup
import com.jingsong.ivmd.R
import com.jingsong.ivmd.data.DataFragment
import com.jingsong.ivmd.mvp.MVPBaseActivity
import com.jingsong.ivmd.video.VideoFragment
import com.jingsong.ivmd.warning.WarningFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : MVPBaseActivity<MainContract.View, MainPresenter>(), MainContract.View, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(p0: Int) {
        when (p0) {
            0 -> rb_zixuan.isChecked = true
            1 -> rb_hanqing.isChecked = true
            2 -> rb_jiaoyi.isChecked = true
        }
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when (p1) {
            R.id.rb_zixuan -> viewpager.currentItem = 0
            R.id.rb_hanqing -> viewpager.currentItem = 1
            R.id.rb_jiaoyi -> viewpager.currentItem = 2
        }
    }

    private var firstTime: Long = 0
    override fun getLayoutView(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        val adapter = FtPagerAdapter(supportFragmentManager, arrayListOf<Fragment>(DataFragment(), WarningFragment(),VideoFragment()))
        viewpager.adapter = adapter
        viewpager.offscreenPageLimit = 4
        viewpager.addOnPageChangeListener(this)
        gr_bottom.setOnCheckedChangeListener(this)
    }

    override fun onKeyDown(paramInt: Int, paramKeyEvent: KeyEvent): Boolean {
        if (paramInt == KeyEvent.KEYCODE_BACK && paramKeyEvent.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                toast(R.string.msg_app_exit)
                firstTime = System.currentTimeMillis()
            } else {
                myApplicaton?.exitApp()
                finish()
            }
            return true
        }
        return super.onKeyDown(paramInt, paramKeyEvent)
    }
}
