package com.example.proj2.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proj2.adapter.CelebrityAdapter
import com.example.proj2.databinding.FragmentCelebrityListBinding
import com.example.proj2.model.Celebrity
import com.example.proj2.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CelebrityListFragment : Fragment() {

    private var _binding: FragmentCelebrityListBinding? = null
    private val binding get() = _binding!!

    private lateinit var celebrityAdapter: CelebrityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCelebrityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

//        loadCelebrities("Cristiano")
        binding.searchButton.setOnClickListener {
            val searchText = binding.searchEditText.text.toString()
            if (searchText.isNotEmpty()) {
                loadCelebrities(searchText)
                hideKeyboard()
            } else {
                Toast.makeText(context, "Введите имя для поиска", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun hideKeyboard() {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

        val currentFocusedView = activity?.currentFocus ?: return
        inputMethodManager?.hideSoftInputFromWindow(currentFocusedView.windowToken, 0)
    }

    private fun setupRecyclerView() {
        celebrityAdapter = CelebrityAdapter()
        binding.celebrityRecyclerView.apply {
            adapter = celebrityAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun loadCelebrities(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.apiService.searchCelebrities(name).execute()
            if (response.isSuccessful) {

                Log.d("PersonListFragment", "Success: ${response.body()}")
                val celebrities = response.body()
                withContext(Dispatchers.Main) {

                    celebrities?.let { celebrityAdapter.submitList(it) }
                }


            }else {
                // Логируем случай неуспешного ответа
                Log.e("PersonListFragment", "Error: ${response.errorBody()?.string()}")
                Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
            }
        }
        binding.searchButton.setOnClickListener {
            val searchText = binding.searchEditText.text.toString()
            if (searchText.isNotEmpty()) {
                loadCelebrities(searchText)
            } else {
                Toast.makeText(context, "Введите имя для поиска", Toast.LENGTH_SHORT).show()
            }
        }



    }
}