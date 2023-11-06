package br.com.fiap.checkpoint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.checkpoint.databinding.ActivityMainBinding
import br.com.fiap.checkpoint.domain.CountryModel
import br.com.fiap.checkpoint.presentation.ApiAdapter
import br.com.fiap.checkpoint.shared.DependencyFactory
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    private lateinit var binding:ActivityMainBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val recyclerView = view.findViewById<RecyclerView>(R.id.countriesRV)
        recyclerView.layoutManager = LinearLayoutManager(context)

        lateinit var adapter: ApiAdapter

        val service = DependencyFactory.createPostService()
        val call: Call<List<CountryModel>> = service.getCountries()

        call.enqueue(object : Callback<List<CountryModel>> {
            override fun onResponse(call: Call<List<CountryModel>>, response: Response<List<CountryModel>>) {
                view?.let {
                    Snackbar.make(it, "Consulta com sucesso", Snackbar.LENGTH_SHORT).show()
                }

                var data = response.body()!!
                adapter = ApiAdapter(context!!, data)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                view?.let {
                    Snackbar.make(it, "Ocorreu um erro ao buscar os países", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}