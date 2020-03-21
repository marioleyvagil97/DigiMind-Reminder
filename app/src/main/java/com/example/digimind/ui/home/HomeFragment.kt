package com.example.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.digimind.R
import com.example.digimind.recordatorio
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.vista_recordatorios.view.*


class HomeFragment : Fragment() {

    private var adaptador:AdaptadorRecordatorios? = null

    companion object{
        var recordatorios = ArrayList<recordatorio>()
        var first = true
    }


    private lateinit var homeViewModel: HomeViewModel



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if(first){
            cargarRecordatorios()
            first = false
        }

        adaptador = AdaptadorRecordatorios(root.context,recordatorios)
        root.gridView.adapter=adaptador

        return root
    }

    fun cargarRecordatorios(){
        recordatorios.add(recordatorio("Entrenar", arrayListOf("Diario"),"15:00"))
        recordatorios.add(recordatorio("Tutoria", arrayListOf("Martes, Jueves"),"18:00"))
        recordatorios.add(recordatorio("Reunion", arrayListOf("Viernes"),"09:00"))
    }

    private class AdaptadorRecordatorios: BaseAdapter {

        var recordatorios = ArrayList<recordatorio>()
        var contexto: Context? = null

        constructor(contexto:Context,recordatorios: ArrayList<recordatorio>){
            this.contexto = contexto
            this.recordatorios=recordatorios
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var recor = recordatorios[position]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.vista_recordatorios,null)

            vista.recordatorioNombre.text = recor.nombre
            vista.recordatorioFrecu.text = recor.frecuencia.toString()
            vista.recordatorioHora.text = recor.hora

            return vista
        }

        override fun getItem(position: Int): Any {
           return  recordatorios[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return recordatorios.size
        }


    }

}
