package com.example.wanandroid_k_m_j.actionFlow

class ActionNode : Node {

    private var nodeId: Int
    private var action: Action
    private var callback: ActionCallback? = null

    constructor(nodeId: Int, action: Action) {
        this.nodeId = nodeId
        this.action = action
    }

    override fun getNodeId(): Int {
        return nodeId
    }

    override fun onComplete() {
        callback?.onActionComplete()
    }

    fun setActionCallback(callback: ActionCallback) {
        this.callback = callback
        action.doAction(this)
    }

    interface ActionCallback {
        fun onActionComplete()
    }
}