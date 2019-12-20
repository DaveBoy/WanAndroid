package com.daveboy.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

open class BaseViewModel:ViewModel(),KoinComponent