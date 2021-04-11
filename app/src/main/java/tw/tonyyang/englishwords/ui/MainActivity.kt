package tw.tonyyang.englishwords.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import tw.tonyyang.englishwords.ui.exam.ExamFragment
import tw.tonyyang.englishwords.R
import tw.tonyyang.englishwords.ui.category.CategoryFragment
import tw.tonyyang.englishwords.databinding.ActivityMainBinding
import tw.tonyyang.englishwords.init
import tw.tonyyang.englishwords.ui.importer.ImporterFragment
import tw.tonyyang.englishwords.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val tabLayoutLabels by lazy {
        listOf(
                getString(R.string.tab_label_importer),
                getString(R.string.tab_label_vocabularies),
                getString(R.string.tab_label_exam)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.toolbar.toolbar.init()
        setSupportActionBar(binding.toolbar.toolbar)
        binding.viewPager.adapter = LabelPagerAdapter(this, tabLayoutLabels.size)
        TabLayoutMediator(binding.tabsLayout, binding.viewPager) { tab, position ->
            tab.text = tabLayoutLabels.getOrNull(position)
        }.attach()
    }

    private class LabelPagerAdapter(
            fragmentActivity: FragmentActivity,
            private val numOfTabs: Int
    ) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = numOfTabs

        override fun createFragment(position: Int): Fragment = when (position) {
            TAB_IMPORTER -> ImporterFragment.newInstance()
            TAB_WORD_LIST_M1 -> CategoryFragment()
            TAB_EXAM -> ExamFragment()
            else -> throw RuntimeException("Could not get fragment.")
        }
    }

    companion object {
        private const val TAB_IMPORTER = 0
        private const val TAB_WORD_LIST_M1 = 1
        private const val TAB_EXAM = 2
    }
}