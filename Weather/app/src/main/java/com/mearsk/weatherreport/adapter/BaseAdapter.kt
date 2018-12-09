package com.mearsk.weatherreport.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

abstract class BaseAdapter<T, VH : BaseAdapter.BaseViewHolder> constructor(dataList: ArrayList<T>) : RecyclerView.Adapter<VH>() {

    private val mDataList: ArrayList<T> = dataList

    fun addAndUpdate(dataList: ArrayList<T>) {
        val startPos: Int = mDataList.size
        mDataList.addAll(dataList)
        val endPos: Int = mDataList.size
        refresh(startPos, endPos)
    }

    fun removeAndUpdate(dataList: ArrayList<T>) {
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun removeAllViews() {
        mDataList.clear()
        notifyDataSetChanged()
    }

    fun refresh() {
        if (itemCount > 0) {
            refresh(0, itemCount)
        }
    }

    private fun refresh(startPos: Int, endPos: Int) {
        notifyItemRangeChanged(startPos, endPos)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    protected fun getItem(position: Int): T {
        return mDataList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return (if (getItem(position) == null) 0 else System.identityHashCode(getItem(position))).toLong()
    }

    open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            ButterKnife.bind(this, itemView)
        }
    }

    fun getDataList(): ArrayList<T> {
        return mDataList
    }
}