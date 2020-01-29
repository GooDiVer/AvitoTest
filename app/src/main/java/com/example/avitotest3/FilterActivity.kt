package com.example.avitotest3

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity :  ListActivity() {

    companion object {
        const val ARG_RESULT = "SERVICE_ARG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val adapter = FilterServicesAdapter(this, getServicesModelList())

        listAdapter = adapter

        filterButton.setOnClickListener{
            view ->
            returnResult(adapter)
        }


    }

    private fun returnResult(adapter: FilterServicesAdapter) {
        val returnIntent = Intent()

        val servicesNameList:ArrayList<String> = ArrayList()

        for(x in adapter.servicesList) {
            if (x.selected)
                servicesNameList.add(x.name)
        }

        returnIntent.putStringArrayListExtra(ARG_RESULT, servicesNameList)

        setResult(MapsActivity.REQUEST_CODE, returnIntent)

        finish()
    }


    fun getServicesModelList(): List<ServiceModel> {
        var services: ArrayList<ServiceModel> = ArrayList()
        services.add(getServiceModel("a"))
        services.add(getServiceModel("b"))
        services.add(getServiceModel("c"))
        return services
    }

    fun getServiceModel(name: String): ServiceModel {
        return ServiceModel(name, false)
    }

}