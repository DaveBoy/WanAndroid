package com.daveboy.base.core

import com.daveboy.base.util.parseErrorString

class QuestException:Exception {
    val summary:String?
    constructor(msg:String?):super(msg){
        this.summary=msg
    }
    constructor(throwable:Throwable?):super(throwable){
        this.summary=throwable.parseErrorString()
    }
}