package com.work.studyaac.lifecycle.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

object LiveDataExt {
    fun <T1, T2, T3, R> combineLatest(
        source1: LiveData<out T1>,
        source2: LiveData<out T2>,
        source3: LiveData<out T3>,
        combine: Function3<T1?, T2?, T3?, R>
    ): LiveData<R> {
        val mediator = MediatorLiveData<R>()
        val combineFunc: Function0<*> = {
            mediator.postValue(combine.apply(source1.value, source2.value, source3.value))
            null
        }
        mediator.addSource(source1, Observer { s: T1 -> combineFunc.invoke() } as Observer<T1>)
        mediator.addSource(source2, Observer { s: T2 -> combineFunc.invoke() } as Observer<T2>)
        mediator.addSource(source3, Observer { s: T3 -> combineFunc.invoke() } as Observer<T3>)
        return mediator
    }

    interface Function3<T1, T2, T3, R> {
        fun apply(t1: T1, t2: T2, t3: T3): R
    }
}