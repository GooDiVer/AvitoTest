package com.example.avitotest3

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilterServicesAdapter(val context: Activity, val servicesList: List<ServiceModel>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return servicesList.size
    }

    override fun getItem(position: Int): Any {
        return servicesList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = null
        val inflater: LayoutInflater = context.layoutInflater
        view = inflater.inflate(R.layout.service_raw, null)

        val viewHolder = ViewHolder()
        viewHolder.checkBox = view.findViewById(R.id.checkbox)
        viewHolder.serviceLabel = view.findViewById(R.id.serviceLabel)

        viewHolder.serviceLabel.text = servicesList[position].name;


        viewHolder.checkBox.setOnCheckedChangeListener {
            buttonView, isChecked ->
            var serviceModel: ServiceModel = viewHolder.checkBox.getTag() as ServiceModel
            serviceModel.selected = isChecked
        }

        view.tag = viewHolder
        viewHolder.checkBox.tag = servicesList[position]
        return view

    }

    companion object {
        class ViewHolder {
            lateinit var serviceLabel: TextView
            lateinit var checkBox: CheckBox
        }
    }
}