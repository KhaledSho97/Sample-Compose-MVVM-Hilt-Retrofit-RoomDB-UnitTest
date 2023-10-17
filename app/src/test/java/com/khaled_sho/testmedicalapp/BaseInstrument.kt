package com.khaled_sho.testmedicalapp

import androidx.compose.foundation.ExperimentalFoundationApi
import org.junit.Before

@ExperimentalFoundationApi
abstract class BaseInstrument {
    @Before
    abstract fun setUp()
}