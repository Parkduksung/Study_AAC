package com.work.studyaac.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.work.studyaac.R
import com.work.studyaac.viewmodel.DataBindingViewModel

class DataBindingActivity : AppCompatActivity() {


    // 이거하는이유는 Binding classes are generated automatically by the library. 이 이유때문.

    //---- 내부 ----
    // 이 binding class 는 viewBinding class(추상) 를 상속받고 viewBinding class 는 BaseObservable class 를 상속받고 viewBinding 을 구현한다.
    // viewBinding 이라는 interface 는 views 에 binds 해주는 것 layout xml field 에 있는.
    // BaseObservable class 는 object 라는 클래스를 상속받았고 observable 를 구현한다.
    private lateinit var binding: ActivityDatabindingBinding

    private val viewModel by lazy { ViewModelProvider(this).get(DataBindingViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Utility class to create {@link ViewDataBinding} from layouts.
        // DataBindingUtil 이란 클래스는 ViewDataBinding 을 layouts 으로부터 생성하는데 필요한거고 setContentView 를 통해 어떤 Activity(NOTNULL) 에 어떤 Layout 을 연결하여
        // 만들것인지 결정하는 구문이다. 관련된 View
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding)

        // 이 엑티비티와 layout 의 수명주기가 같아짐.
        binding.lifecycleOwner = this
        binding.vm = viewModel

    }
}