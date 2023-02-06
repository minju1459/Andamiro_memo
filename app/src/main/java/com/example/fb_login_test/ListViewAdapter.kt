package com.example.fb_login_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter (val List: MutableList<DataModel>):BaseAdapter(){
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(p0: Int): Any {
        return List[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertview=p1
        if(convertview==null){
            convertview=LayoutInflater.from(p2?.context).inflate(R.layout.list_view_item,p2,false)
        }

       val date= convertview?.findViewById<TextView>(R.id.listview_Data)
       val memo= convertview?.findViewById<TextView>(R.id.listview_memotext)

        date!!.text=List[p0].date
        memo!!.text=List[p0].memo

        return convertview!!

    }

}