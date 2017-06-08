package fuckermonkey.phots.ui.activity

import android.content.Intent
import android.view.View
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import fuckermonkey.phots.R
import fuckermonkey.phots.adapter.TabFragmentAdapter
import fuckermonkey.phots.model.TypeFragmentData
import fuckermonkey.phots.ui.base.BaseActivity
import fuckermonkey.phots.ui.fragment.TypeFragment

class MainTabActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    val INDEX_GIRL = 0
    val INDEX_MILITARY = 1
    val INDEX_COMIC = 2
    val INDEX_PET = 3
    val INDEX_PHOTOGRAPH = 4

    var mTabLayout: TabLayout? = null
    var mViewPager: ViewPager? = null
    var mTabFragmentAdapter: TabFragmentAdapter? = null

    var mGirlTypeList: ArrayList<String>? = null  //美女
    var mMilitaryTypeList: ArrayList<String>? = null //军事
    var mComicTypeList: ArrayList<String>? = null //动漫
    var mPeyTypeList: ArrayList<String>? = null //宠物
    var mPhotographTypeList: ArrayList<String>? = null //宠物
    var mTagList: ArrayList<String>? = null //摄影


    var mFragmentListMap: HashMap<String, ArrayList<Fragment>>? = null
    var mTypeFragmentDataListMap: HashMap<String, ArrayList<TypeFragmentData>>? = null


    override fun getLayoutId(): Int {
        return R.layout.activity_main_tab_view
    }

    override fun initActivity() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        mTagList = ArrayList()
        mGirlTypeList = ArrayList()
        mMilitaryTypeList = ArrayList()
        mComicTypeList = ArrayList()
        mPeyTypeList = ArrayList()
        mPhotographTypeList = ArrayList()

        mFragmentListMap = HashMap()
        mTypeFragmentDataListMap = HashMap()

        setupTypeData()
        setupViewPager()
    }

    override fun initView() {
        mTabLayout = findViewById(R.id.tab_view) as TabLayout
        mViewPager = findViewById(R.id.pager_view) as ViewPager
    }

    override fun setViewListener() {

    }


    override fun OnClick(v: View?) {
        super.OnClick(v)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_tab, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_girl) {
            updateViewPager(mGirlTypeList!!, INDEX_GIRL)
        } else if (id == R.id.nav_military) {
            updateViewPager(mMilitaryTypeList!!, INDEX_MILITARY)
        } else if (id == R.id.nav_comic) {
            updateViewPager(mComicTypeList!!, INDEX_COMIC)
        } else if (id == R.id.nav_pet) {
            updateViewPager(mPeyTypeList!!, INDEX_PET)
        } else if (id == R.id.nav_photograph) {
            updateViewPager(mPhotographTypeList!!, INDEX_PHOTOGRAPH)
        } else if (id == R.id.nav_theme_setting) {
            val intent = Intent()
            intent.setClass(this, SetThemeActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_browse_setting) {
            val intent = Intent()
            intent.setClass(this, SetListColumnActivity::class.java)
            startActivity(intent)
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun updateViewPager(typeList: ArrayList<String>, tagIndex: Int) {
        var fragmentList = mFragmentListMap!!.get(mTagList!!.get(tagIndex))
        var typeFragmentDataList: ArrayList<TypeFragmentData>? = null
        if (fragmentList == null) {
            fragmentList = ArrayList()
            typeFragmentDataList = ArrayList<TypeFragmentData>()
            for (t in typeList!!.iterator()) {
                val typeFragment = TypeFragment(mTagList!!.get(tagIndex), t)
                fragmentList!!.add(typeFragment)
                val tfd = TypeFragmentData(mTagList!!.get(tagIndex), t)
                typeFragmentDataList!!.add(tfd)
            }
            mFragmentListMap!!.put(mTagList!!.get(tagIndex), fragmentList!!)
            mTypeFragmentDataListMap!!.put(mTagList!!.get(tagIndex), typeFragmentDataList!!)
        }
        typeFragmentDataList = mTypeFragmentDataListMap!!.get(mTagList!!.get(tagIndex))
        mTabFragmentAdapter!!.updateData(fragmentList, typeFragmentDataList!!, typeList!!)
    }

    fun setupViewPager() {
        mTabLayout!!.tabMode = TabLayout.MODE_SCROLLABLE

        var fragmentList = ArrayList<Fragment>()
        var typeFragmentDataList = ArrayList<TypeFragmentData>()
        for (t in mGirlTypeList!!.iterator()) {
            val typeFragment = TypeFragment(mTagList!!.get(INDEX_GIRL), t)
            val tfd = TypeFragmentData(mTagList!!.get(INDEX_GIRL), t)
            fragmentList!!.add(typeFragment)
            typeFragmentDataList.add(tfd)
        }

        mFragmentListMap!!.put(mTagList!!.get(INDEX_GIRL), fragmentList!!)
        mTypeFragmentDataListMap!!.put(mTagList!!.get(INDEX_GIRL), typeFragmentDataList)

        mTabFragmentAdapter = TabFragmentAdapter(supportFragmentManager, fragmentList!!, mGirlTypeList!!)
        mViewPager!!.adapter = mTabFragmentAdapter
        mTabLayout!!.setupWithViewPager(mViewPager)
    }

    fun setupTypeData() {
        mTagList!!.add("美女")
        mTagList!!.add("军事")
        mTagList!!.add("动漫")
        mTagList!!.add("宠物")
        mTagList!!.add("摄影")

        mGirlTypeList!!.add("小清新")
        mGirlTypeList!!.add("网络美女")
        mGirlTypeList!!.add("宅男女神")
        mGirlTypeList!!.add("唯美")
        mGirlTypeList!!.add("长腿")
        mGirlTypeList!!.add("写真")
        mGirlTypeList!!.add("气质")
        mGirlTypeList!!.add("古典美女")
        mGirlTypeList!!.add("嫩萝莉")
        mGirlTypeList!!.add("长发")
        mGirlTypeList!!.add("cosplay")
        mGirlTypeList!!.add("可爱")
        mGirlTypeList!!.add("车模")

        mMilitaryTypeList!!.add("海军")
        mMilitaryTypeList!!.add("空军")
        mMilitaryTypeList!!.add("航母")
        mMilitaryTypeList!!.add("枪械")
        mMilitaryTypeList!!.add("二战")

        mComicTypeList!!.add("手绘")
        mComicTypeList!!.add("日本动漫")
        mComicTypeList!!.add("名侦探柯南")
        mComicTypeList!!.add("火影忍者")
        mComicTypeList!!.add("场景")
        mComicTypeList!!.add("妖精的尾巴")
        mComicTypeList!!.add("海贼王")
        mComicTypeList!!.add("宫崎骏")
        mComicTypeList!!.add("圣斗士")
        mComicTypeList!!.add("七龙珠")
        mComicTypeList!!.add("动漫美少女")
        mComicTypeList!!.add("古风")

        mPeyTypeList!!.add("萌宠")
        mPeyTypeList!!.add("狗狗")
        mPeyTypeList!!.add("喵星人")
        mPeyTypeList!!.add("猫叔")
        mPeyTypeList!!.add("哈士奇")
        mPeyTypeList!!.add("宠物鼠")
        mPeyTypeList!!.add("金毛")
        mPeyTypeList!!.add("摩萨耶")
        mPeyTypeList!!.add("博美")
        mPeyTypeList!!.add("泰迪")
        mPeyTypeList!!.add("博美")
        mPeyTypeList!!.add("萌货")

        mPhotographTypeList!!.add("风景")
        mPhotographTypeList!!.add("静物")
        mPhotographTypeList!!.add("人像")
        mPhotographTypeList!!.add("国外摄影")
        mPhotographTypeList!!.add("lomo")
        mPhotographTypeList!!.add("光影")
        mPhotographTypeList!!.add("国家地理")
        mPhotographTypeList!!.add("创意摄影")
        mPhotographTypeList!!.add("人文纪实")
        mPhotographTypeList!!.add("水下摄影")
        mPhotographTypeList!!.add("时尚")
        mPhotographTypeList!!.add("儿童摄影")
        mPhotographTypeList!!.add("生态摄影")
    }
}
