package com.leah.myapplication.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.leah.myapplication.MainActivity
import com.leah.myapplication.R
import com.leah.myapplication.ui.onboarding.screens.FirstScreen.FirstScreenFragment
import com.leah.myapplication.ui.onboarding.screens.SecondScreen.SecondSCreenFragment
import com.leah.myapplication.ui.onboarding.screens.ThirdScreen.ThirdScreenFragment

class OnboardingActivity : AppCompatActivity() {

            private lateinit var pageOne: ImageView
            private lateinit var pageTwo: ImageView
            private lateinit var pageThree: ImageView
            private lateinit var pageFour: ImageView
            private lateinit var viewPager: ViewPager2
            private lateinit var skipTextButton: TextView

            private lateinit var nextTextButton: TextView
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                // //returns splash screen object which can optionally be used to customize animation or keep splash screen on screen for a longer duration.
                val splashScreen = installSplashScreen()
                setContentView(R.layout.activity_onboarding)
                viewPager = findViewById<ViewPager2>(R.id.viewPager)
                pageOne= findViewById<ImageView>(R.id.viewOnee)
                pageTwo= findViewById<ImageView>(R.id.viewTwo)
                pageThree= findViewById<ImageView>(R.id.viewThree)
                skipTextButton = findViewById(R.id.skip)
                skipTextButton.isVisible = true
                nextTextButton = findViewById(R.id.next)
                nextTextButton.isVisible = false

                nextTextButton.setOnClickListener {
                    Toast.makeText(
                        this, "GOOD FOOD GOOOD MOOOD.. ",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent =  Intent(this@OnboardingActivity,
                        MainActivity::class.java// MainActivity::class.java
                    )
                    startActivity(intent);
                }

                val fragmentList = arrayListOf<Fragment>(
                    FirstScreenFragment(),
                    SecondSCreenFragment(),
                    ThirdScreenFragment()
                )
                val adapter = ViewPagerAdapter(
                    fragmentList,
                    supportFragmentManager,
                    lifecycle
                )
                viewPager.adapter = adapter
                viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        changeColor()
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    }
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }
                    override fun onPageScrollStateChanged(state: Int) {
                        super.onPageScrollStateChanged(state)
                        changeColor()
                    }
                })

                skipTextButton.setOnClickListener {
                    skipButton()
                }
            }
            fun skipButton(){
                viewPager.setCurrentItem(3)
            }

            fun nxtButton(){

            }
            fun changeColor(){
                nxtButton()
                when(viewPager.currentItem){

                    0-> {
                        pageOne.setBackgroundColor(applicationContext.resources.getColor(R.color.pink_shade))
                        pageTwo.setBackgroundColor(applicationContext.resources.getColor(R.color.light_gray))
                        pageThree.setBackgroundColor(applicationContext.resources.getColor(R.color.light_gray))
                        skipTextButton.isVisible = true
                        nextTextButton.isVisible = false
                    }
                    1->{
                        pageOne.setBackgroundColor(applicationContext.resources.getColor(R.color.light_gray))
                        pageTwo.setBackgroundColor(applicationContext.resources.getColor(R.color.pink_shade))
                        pageThree.setBackgroundColor(applicationContext.resources.getColor(R.color.light_gray))
                        skipTextButton.isVisible = true
                        nextTextButton.isVisible = false
                    }
                    2->{
                        pageOne.setBackgroundColor(applicationContext.resources.getColor(R.color.light_gray))
                        pageTwo.setBackgroundColor(applicationContext.resources.getColor(R.color.light_gray))
                        pageThree.setBackgroundColor(applicationContext.resources.getColor(R.color.pink_shade))
                        skipTextButton.isVisible = false
                        nextTextButton.isVisible = true
                    }

                }
            }
        }