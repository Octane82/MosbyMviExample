package com.everlapp.mosbymviexample.mvi

import com.everlapp.mosbymviexample.domain.GetHelloWorldTextUseCase
import com.everlapp.mosbymviexample.domain.HelloWorldViewState
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit



class HelloWorldPresenter : MviBasePresenter<HelloWorldView, HelloWorldViewState>() {

    override fun bindIntents() {
        val helloWorldState: Observable<HelloWorldViewState> = intent(HelloWorldView::sayHelloWorldIntent)
            .subscribeOn(Schedulers.io())
            .debounce(400, TimeUnit.MILLISECONDS)
            .switchMap { GetHelloWorldTextUseCase.getHelloWorldText() }
            .doOnNext { Timber.d("Received new state: " + it) }
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(helloWorldState, HelloWorldView::render)
    }
}