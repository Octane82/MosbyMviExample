package com.everlapp.mosbymviexample.mvi

import com.everlapp.mosbymviexample.domain.HelloWorldViewState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable


interface HelloWorldView : MvpView {

    /**
     * Emits button clicks as Observables
     */
    fun sayHelloWorldIntent() : Observable<Unit>


    /**
     * Render the state in the UI
     */
    fun render(state: HelloWorldViewState)


}