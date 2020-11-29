package com.example1.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.reflect.KMutableProperty1

lateinit var tabLayout: TabLayout
lateinit var tabviewpager: ViewPager2
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout=findViewById(R.id.tabs)
        tabviewpager=findViewById(R.id.pager)
        tabviewpager.adapter = object : FragmentStateAdapter(this) {

            override fun createFragment(position: Int): Fragment {
                return when (position){
                    0 -> FirstFragment.newInstance("","")
                    1 -> SecondFragment.newInstance("","")
                    2 -> ThirdFragment.newInstance("","")

                else -> {
                    FirstFragment.newInstance("","")
                }
                }
            }
            override fun getItemCount(): Int {
                return 3
            }

        }

        TabLayoutMediator(tabLayout,tabviewpager){ tab, position ->
            tab.text= when(position){
                0 -> "To_Do"
                1 -> "Progress"
                2 -> "Done"
                else -> null
            }

        }.attach()

        }

    }
