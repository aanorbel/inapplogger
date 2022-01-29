package com.velmurugan.inapplogger

interface Subject {
    fun register(observer: MessageObserver)
    fun unRegister(observer: MessageObserver)
    fun notifyMessage(msg: String, tag: String?)

}


interface MessageObserver {
    fun update(tag: String?)
}