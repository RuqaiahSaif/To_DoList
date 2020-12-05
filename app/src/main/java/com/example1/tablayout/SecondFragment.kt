package com.example1.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    private lateinit var doingRecyclerView1: RecyclerView
    private var adapter1: DoingAdapter? = null
    private lateinit var toDolist: To_DoList
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val doingListViewModel: DoingListViewModel by lazy {
        ViewModelProviders.of(this).get(DoingListViewModel::class.java)
    }
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
        val view = inflater.inflate(R.layout.doing_list, container, false)
        doingRecyclerView1 = view.findViewById(R.id.doing_recycler_view) as RecyclerView
        doingRecyclerView1.layoutManager = LinearLayoutManager(context)

        doingRecyclerView1.adapter = adapter1
        return view
    }
    private fun updateUI(to_Dolist: List<To_DoList>) {

        adapter1 = DoingAdapter(to_Dolist)
        doingRecyclerView1.adapter = adapter1

    }
    private inner class DoingHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val titleTextView1: TextView = itemView.findViewById(R.id.title1)
        val detailsTextView1: TextView = itemView.findViewById(R.id.details1)
        val dateTextView: TextView = itemView.findViewById(R.id.date)
        val enter1: Button = itemView.findViewById(R.id.enter1)

        init {
            enter1.setOnClickListener(this)
        }

        private lateinit var toDolist: To_DoList
        fun bind(toDolist: To_DoList) {
            this.toDolist = toDolist
            titleTextView1.text = this.toDolist.title
            detailsTextView1.text = this.toDolist.details
            dateTextView.text=this.toDolist.date.toString()

        }

        override fun onClick(v: View?) {
            this.toDolist.status=2
            doingListViewModel.updateTo_DoList(this.toDolist)
            doingListViewModel.ProgressList.observe(
                viewLifecycleOwner,
                Observer { to_DoLists ->
                    to_DoLists?.let {
                        updateUI(to_DoLists)
                    }
                })  }
    }


    private inner class DoingAdapter(var doings: List<To_DoList>) :
        RecyclerView.Adapter<DoingHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : DoingHolder {
            val view = layoutInflater.inflate(R.layout.fragment_second, parent, false)

            return DoingHolder(view)
        }


        override fun getItemCount(): Int {


            return doings.size
        }


        override fun onBindViewHolder(holder: DoingHolder, position: Int) {
            val doing = doings[position]
            holder.bind(doing)
        }


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doingListViewModel.ProgressList.observe(
            viewLifecycleOwner,
            Observer { to_DoLists ->
                to_DoLists?.let {
                    updateUI(to_DoLists)
                }

            })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}