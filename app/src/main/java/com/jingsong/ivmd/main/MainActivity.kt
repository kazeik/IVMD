package com.jingsong.ivmd.main


import android.support.v7.widget.GridLayoutManager
import android.view.KeyEvent
import com.jingsong.ivmd.R
import com.jingsong.ivmd.carwarning.CarWarningActivity
import com.jingsong.ivmd.data.DataActivity
import com.jingsong.ivmd.mvp.MVPBaseActivity
import com.jingsong.ivmd.template.TemplateActivity
import com.jingsong.ivmd.video.VideoActivity
import com.jingsong.ivmd.warning.WarningActivity
import com.jingsong.patient.iter.OnItemEventListener
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : MVPBaseActivity<MainContract.View, MainPresenter>(), MainContract.View,
    OnItemEventListener {
    override fun onItemEvent(pos: Int) {
        when (pos) {
            0 -> startActivity<DataActivity>()
            1 -> startActivity<TemplateActivity>()
            2 -> startActivity<WarningActivity>()
            3 -> startActivity<CarWarningActivity>()
            4 -> startActivity<VideoActivity>()
        }
    }

    private var firstTime: Long = 0
    override fun getLayoutView(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
//        val adapter = FtPagerAdapter(
//            supportFragmentManager,
//            arrayListOf<Fragment>(
//                DataFragment(),
//                TemplateFragment(),
//                WarningFragment(),
//                VideoFragment()
//            )
//        )
//        viewpager.adapter = adapter
//        viewpager.offscreenPageLimit = 4
//        viewpager.addOnPageChangeListener(this)
//        gr_bottom.setOnCheckedChangeListener(this)

        val itemarr = resources.getStringArray(R.array.mainstr)
        val resArr: IntArray = intArrayOf(
            R.drawable.ic_my_device,
            R.drawable.ic_my_info,
            R.drawable.ic_my_refresh,
            R.drawable.ic_my_device,
            R.drawable.ic_my_info
        )
        val adapter: MainAdapter<String> by lazy { MainAdapter<String>(resArr) }
        rcvList.layoutManager = GridLayoutManager(this, 4)
        rcvList.adapter = adapter
        adapter.setDataEntityList(itemarr.toList())
        adapter.listener = this
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
