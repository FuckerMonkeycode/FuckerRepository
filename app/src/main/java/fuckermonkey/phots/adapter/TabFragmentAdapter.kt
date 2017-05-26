package fuckermonkey.phots.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup
import fuckermonkey.phots.model.TypeFragmentData
import fuckermonkey.phots.ui.fragment.TypeFragment

/**
 * Created by xuxiaowu on 2017/5/25.
 */
class TabFragmentAdapter(fm: FragmentManager?, fragmentList: List<Fragment>, titleList: List<String>) : FragmentPagerAdapter(fm) {
    var mFragmentList: List<Fragment>? = null
    var mTitleList: List<String>? = null
    var mTypeFragmentDataList: List<TypeFragmentData>? = null

    init {
        mFragmentList = fragmentList
        mTitleList = titleList
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList!!.get(position)
    }

    override fun getCount(): Int {
        return mTitleList!!.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitleList!!.get(position)
    }

    override fun getItemPosition(obj: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val oldTypeFragment = super.instantiateItem(container, position) as TypeFragment

        if (mTypeFragmentDataList == null) return oldTypeFragment

        val typeFragmentData = mTypeFragmentDataList!!.get(position)

        if (oldTypeFragment.mFlag.equals(typeFragmentData.flag) && oldTypeFragment.mTag.equals(typeFragmentData.tag)) {
            return oldTypeFragment
        } else {
            oldTypeFragment.reloadData(typeFragmentData.tag!!, typeFragmentData.flag!!)
            return oldTypeFragment
        }
    }

    fun updateData(fragmentList: List<Fragment>, typeFragmentDataList: List<TypeFragmentData>, titleList: List<String>) {
        mFragmentList = fragmentList
        mTypeFragmentDataList = typeFragmentDataList
        mTitleList = titleList
        notifyDataSetChanged()
    }

}