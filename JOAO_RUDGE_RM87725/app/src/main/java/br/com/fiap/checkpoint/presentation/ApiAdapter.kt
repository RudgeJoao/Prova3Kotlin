package br.com.fiap.checkpoint.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.checkpoint.R
import br.com.fiap.checkpoint.domain.CountryModel

class ApiAdapter(var c: Context, var list: List<CountryModel>): RecyclerView.Adapter<ApiAdapter.ViewHolder>() {

    inner class ViewHolder( v : View) : RecyclerView.ViewHolder(v){
        var name = v.findViewById<TextView>(R.id.name)
        var continent = v.findViewById<TextView>(R.id.continent)
        var capital = v.findViewById<TextView>(R.id.capital)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(c).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].nome.abreviado
        holder.continent.text = list[position].localizacao.regiao.nome
        holder.capital.text = list[position].governo.capital.nome
    }

}

