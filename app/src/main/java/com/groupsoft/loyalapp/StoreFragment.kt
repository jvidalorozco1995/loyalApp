package com.groupsoft.loyalapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.groupsoft.loyalapp.adapters.StoreAdapter
import com.groupsoft.loyalapp.models.Store

class StoreFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater?.inflate(R.layout.store_fragment,
                container, false)

        val rv = view.findViewById<RecyclerView>(R.id.store_recyclerview)
        rv.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        val stores = ArrayList<Store>()
        stores.add(Store("Don jediondo", "Además de las delicias de la tierrita le ojrecemos todo un abanico de servicios pa’ que escoja",resources.getDrawable(R.drawable.arroz)))
        stores.add(Store("Subway", " cadena de restaurantes de comida rápida especializada en la elaboración de sándwich submarino y bocadillos, ensaladas y pizza por ración.",resources.getDrawable(R.drawable.coffe)))
        stores.add(Store("Tostao", "En TOSTAO’ Café & Pan servimos lo mejor de nuestros cafetales. Te brindamos TOSTAO’, un café de altura",resources.getDrawable(R.drawable.pizza)))
        stores.add(Store("Dominos Pizza", "Domino's Pizza es una empresa estadounidense de restaurantes de comida rápida, especializada en la elaboración de pizzas.",resources.getDrawable(R.drawable.arroz)))
        stores.add(Store("El corral", "Hamburguesas El Corral es una marca dedicada a la producción, elaboración y comercialización de alimentos",resources.getDrawable(R.drawable.coffe)))
        stores.add(Store("Presto", "Tres décadas apostando a la mejor hamburguesa colombiana. ¡Ven y conoce el sabor inigualable que te alegra el día!",resources.getDrawable(R.drawable.pizza)))

        var adapter = StoreAdapter(stores)
        rv.adapter = adapter

        // Inflate the layout for this fragment
        return view
    }



    companion object {
        fun newInstance(): StoreFragment = StoreFragment()
    }
}
