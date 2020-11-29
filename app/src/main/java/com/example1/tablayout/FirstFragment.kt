package com.example1.tablayout

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment(),NewDialog.Callbacks {
    private lateinit var doingRecyclerView: RecyclerView
    private var adapter: DoingAdapter? = null
    var toDolist: To_DoList? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val doingListViewModel: DoingListViewModel by lazy {
        ViewModelProviders.of(this).get(DoingListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        doingRecyclerView = view.findViewById(R.id.doing_recycler_view) as RecyclerView
        doingRecyclerView.layoutManager = LinearLayoutManager(context)

        doingRecyclerView.adapter = adapter
        return view
    }

    private fun updateUI(to_Dolist: List<To_DoList>) {

        adapter = DoingAdapter(to_Dolist)
        doingRecyclerView.adapter = adapter

    }

    private inner class DoingHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val detailsTextView: TextView = itemView.findViewById(R.id.details)
        val dateTextView: TextView = itemView.findViewById(R.id.date)
        val enter: Button = itemView.findViewById(R.id.enter)

        init {
            enter.setOnClickListener(this)
        }

        private lateinit var toDolist: To_DoList
        fun bind(toDolist: To_DoList) {
            this.toDolist = toDolist
            titleTextView.text = this.toDolist.title
            detailsTextView.text = this.toDolist.details
            dateTextView.text=this.toDolist.date.toString()


        }

        override fun onClick(v: View?) {
            this.toDolist.status=1
            doingListViewModel.doingList.observe(
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
            val view = layoutInflater.inflate(R.layout.fragment_first, parent, false)

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
        doingListViewModel.doingList.observe(
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
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.do_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_do -> {
                NewDialog().apply {
                    setTargetFragment(this@FirstFragment, 0)
                    show(this@FirstFragment.requireFragmentManager(), "Input")
                }
     true
            }
            else -> return super.onOptionsItemSelected(item)
        }


    }


    override fun doingAdd(doing: To_DoList) {
        doingListViewModel.addTo_DoList(doing)
    }
}




