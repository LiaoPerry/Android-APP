package com.wellmember.app.tool

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.wellmember.app.R
import com.wellmember.app.tool.banner.BaseBannerAdapter

class BannerViewPager<T> : RelativeLayout, LifecycleObserver {

    private var mContext: Context = context
    private lateinit var mIndicatorLayout: LinearLayout
    private lateinit var mViewPager: ViewPager2
    private var mBannerPagerAdapter: BaseBannerAdapter<T, *>? = null
    private val onPageChangeCallback: ViewPager2.OnPageChangeCallback? = null
    private var mCompositePageTransformer: CompositePageTransformer? = null
    private var mMarginPageTransformer: MarginPageTransformer? = null
    private var mOnPageClickListener: BaseBannerAdapter.OnPageClickListener? = null

    private var lastPosition = 0
    private var listSize = 0

    private val mHandler: Handler = Handler()
    private val runnable: Runnable = object : Runnable {
        override fun run() {
            val currentItem = mViewPager.currentItem
            if (isLooper) {
                mViewPager.currentItem = currentItem + 1
            } else {
                if (currentItem == listSize - 1) {
                    mViewPager.setCurrentItem(0, false)
                } else {
                    mViewPager.currentItem = currentItem + 1
                }
            }
            mHandler.postDelayed(this, interval)
        }
    }

    private var interval = 3000L

    private var isAutoPlay = false

    private var isLooper = false

    private var isShowIndicator = false

    private var pageMargin = 0

    private var revealWidth = -1

    private var offscreenPageLimit = 3

    private var indicatorMargin = dpToPx(5)

    private var normalImage = R.drawable.shape_dot

    private var checkedImage = R.drawable.shape_dot_selected


    private val mOnPageChangeCallback: ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            val realPosition: Int = mBannerPagerAdapter!!.getRealPosition(position)
            onPageChangeCallback?.onPageScrolled(realPosition, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val realPosition: Int = mBannerPagerAdapter!!.getRealPosition(position)
            onPageChangeCallback?.onPageSelected(realPosition)
            setIndicatorDots(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            onPageChangeCallback?.onPageScrollStateChanged(state)
        }
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
        mCompositePageTransformer = CompositePageTransformer()
        mViewPager.setPageTransformer(mCompositePageTransformer)
    }

    private fun initView() {
        inflate(context, R.layout.bvp_layout, this)
        mViewPager = findViewById(R.id.vp_main)
        mIndicatorLayout = findViewById(R.id.bvp_layout_indicator)
    }


    private fun initBannerData(list: List<T>) {
        if (list.isNotEmpty()) {
            initIndicatorDots(list)
            setupViewPager(list)
        }
    }

    private fun initIndicatorDots(list: List<T>) {
        mIndicatorLayout.removeAllViews()
        if (isShowIndicator && listSize > 1) {
            for (i in list.indices) {
                val imageView = ImageView(mContext)
                if (i == 0) imageView.setBackgroundResource(checkedImage)
                else imageView.setBackgroundResource(normalImage)
                //为指示点添加间距
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(
                    indicatorMargin,
                    indicatorMargin,
                    indicatorMargin,
                    indicatorMargin
                )
                imageView.layoutParams = layoutParams
                mIndicatorLayout.addView(imageView)
            }
        }
    }

    private fun setIndicatorDots(position: Int) {
        if (isShowIndicator && listSize > 1) {

            val current = position % listSize
            val last = lastPosition % listSize
            mIndicatorLayout.getChildAt(current).setBackgroundResource(checkedImage)
            mIndicatorLayout.getChildAt(last).setBackgroundResource(normalImage)
            lastPosition = position
        }
    }

    private fun setupViewPager(list: List<T>) {
        if (mBannerPagerAdapter == null) {
            throw NullPointerException("You must set adapter for BannerViewPager")
        }

        if (revealWidth != -1) {
            val recyclerView = mViewPager.getChildAt(0) as RecyclerView
            recyclerView.setPadding(pageMargin + revealWidth, 0, pageMargin + revealWidth, 0)
            recyclerView.clipToPadding = false
        }

        mBannerPagerAdapter!!.isCanLoop = isLooper
        mBannerPagerAdapter!!.pageClickListener = mOnPageClickListener
        mViewPager.adapter = mBannerPagerAdapter
        resetCurrentItem()

        mViewPager.unregisterOnPageChangeCallback(mOnPageChangeCallback)
        mViewPager.registerOnPageChangeCallback(mOnPageChangeCallback)
        mViewPager.offscreenPageLimit = offscreenPageLimit
        startTimer()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(owner: LifecycleOwner?) {
        startTimer()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(owner: LifecycleOwner?) {
        stopTimer()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                stopTimer()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_OUTSIDE -> {
                startTimer()
            }
            else -> {
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun startTimer() {
        if (isAutoPlay && mBannerPagerAdapter != null && listSize > 1) {
            stopTimer()
            mHandler.postDelayed(runnable, interval)
        }
    }


    private fun stopTimer() {
        mHandler.removeCallbacks(runnable)
    }


    fun setLifecycleRegistry(lifecycleRegistry: Lifecycle): BannerViewPager<T> {
        lifecycleRegistry.addObserver(this)
        return this
    }

    fun setAutoPlay(autoPlay: Boolean): BannerViewPager<T> {
        isAutoPlay = autoPlay
        return this
    }

    fun setCanLoop(canLoop: Boolean): BannerViewPager<T> {
        isLooper = canLoop
        return this
    }

    fun setAdapter(adapter: BaseBannerAdapter<T, *>?): BannerViewPager<T> {
        mBannerPagerAdapter = adapter
        return this
    }

    fun setInterval(int: Int): BannerViewPager<T> {
        interval = int * 1000L
        return this
    }

    fun setOffscreenPageLimit(int: Int): BannerViewPager<T> {
        offscreenPageLimit = int
        return this
    }

    fun setCanShowIndicator(bool: Boolean): BannerViewPager<T> {
        isShowIndicator = bool
        return this
    }

    fun setPageTransformer(transformer: ViewPager2.PageTransformer): BannerViewPager<T> {
        mViewPager.setPageTransformer(transformer)
        return this
    }

    fun addPageTransformer(transformer: ViewPager2.PageTransformer): BannerViewPager<T> {
        mCompositePageTransformer?.addTransformer(transformer)
        return this
    }

    fun removeTransformer(transformer: ViewPager2.PageTransformer) {
        mCompositePageTransformer?.removeTransformer(transformer)
    }


    fun removeMarginPageTransformer() {
        if (mMarginPageTransformer != null) {
            mCompositePageTransformer?.removeTransformer(mMarginPageTransformer!!)
        }
    }

    fun setPageMargin(margin: Int): BannerViewPager<T> {
        pageMargin = dpToPx(margin)
        removeMarginPageTransformer()
        mMarginPageTransformer = MarginPageTransformer(pageMargin)
        mCompositePageTransformer?.addTransformer(mMarginPageTransformer!!)
        return this
    }

    fun setRevealWidth(int: Int): BannerViewPager<T> {
        revealWidth = dpToPx(int)
        return this
    }

    fun setOnPageClickListener(onPageClickListener: BaseBannerAdapter.OnPageClickListener): BannerViewPager<T> {
        mOnPageClickListener = onPageClickListener
        return this
    }

    fun setIndicatorMargin(margin: Int): BannerViewPager<T> {
        indicatorMargin = margin
        return this
    }

    fun setIndicatorSliderColor(
        @DrawableRes normal: Int,
        @DrawableRes checked: Int
    ): BannerViewPager<T> {
        normalImage = normal
        checkedImage = checked
        return this
    }

    fun create(data: List<T>) {
        if (mBannerPagerAdapter == null) {
            throw NullPointerException("You must set adapter for BannerViewPager")
        }
        listSize = data.size
        mBannerPagerAdapter!!.setData(data)
        initBannerData(data)
    }

    fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        if (isLooper && listSize > 1) {
            val currentItem = mViewPager.currentItem
            val realPosition: Int = mBannerPagerAdapter!!.getRealPosition(currentItem)
            if (currentItem != item) {
                if (item == 0 && realPosition == listSize - 1) {
                    mViewPager.setCurrentItem(currentItem + 1, smoothScroll)
                } else if (realPosition == 0 && item == listSize - 1) {
                    mViewPager.setCurrentItem(currentItem - 1, smoothScroll)
                } else {
                    mViewPager.setCurrentItem(currentItem + (item - realPosition), smoothScroll)
                }
            }
        } else {
            mViewPager.setCurrentItem(item, smoothScroll)
        }
    }


    fun refreshData(list: List<T>) {
        if (mBannerPagerAdapter != null && list.isNotEmpty()) {
            stopTimer()
            listSize = list.size
            mBannerPagerAdapter!!.setData(list)
            mBannerPagerAdapter!!.notifyDataSetChanged()
            resetCurrentItem()
            initIndicatorDots(list)
            startTimer()
        }
    }


    private fun dpToPx(dip: Int): Int {
        return (0.5f + dip * Resources.getSystem().displayMetrics.density).toInt()
    }

    private fun resetCurrentItem() {
        if (listSize > 1 && isLooper) {
            lastPosition = Int.MAX_VALUE / 2 - ((Int.MAX_VALUE / 2) % listSize)
            mViewPager.setCurrentItem(lastPosition, false)
        } else {
            mViewPager.setCurrentItem(0, false)
        }
    }
}

