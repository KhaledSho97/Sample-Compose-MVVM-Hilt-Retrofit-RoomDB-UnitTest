package com.khaled_sho.testmedicalapp.core.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {

     fun computation (): CoroutineDispatcher=Dispatchers.Default
     fun io ():CoroutineDispatcher=Dispatchers.IO
     fun main ():CoroutineDispatcher=Dispatchers.Main

}