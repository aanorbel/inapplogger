package com.velmurugan.inapplogger

interface Subject {
    fun register(observer: MessageObserver)
    fun unRegister(observer: MessageObserver)
    fun notifyMessage(msg: String)

}


interface MessageObserver {
    fun update()
}