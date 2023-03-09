package com.example.wanandroid_k_m_j.actionFlow

import android.util.SparseArray

/**
 * 顺序执行task
 * 首页一系列弹窗
 */
class ActionFlow(private var flowNodes: SparseArray<ActionNode>) {

    private var callback: FlowCallback? = null

    fun start() {
        startWithNode(flowNodes.keyAt(0))
    }

    fun remove(nodeId: Int) {
        flowNodes.remove(nodeId)
    }

    fun setFlowCallback(callback: FlowCallback?) {
        this.callback = callback
    }

    private fun startWithNode(nodeId: Int) {
        callback?.onFlowStart()
        val startIndex = flowNodes.indexOfKey(nodeId)
        if (startIndex == -1) {
            callback?.onFlowFinish()
            return
        }
        val node = flowNodes.valueAt(startIndex)
        if (node != null) {
            callback?.onNodeChanged(startIndex)
        } else {
            callback?.onFlowFinish()
            return
        }
        node.setActionCallback(object : ActionNode.ActionCallback {
            override fun onActionComplete() {
                findAndExecuteNextNodeIfexist(startIndex)
            }
        })
    }

    private fun findAndExecuteNextNodeIfexist(startIndex: Int) {
        val nextIndex = startIndex + 1
        var nextNode: ActionNode? = null
        try {
            nextNode = flowNodes.valueAt(nextIndex)
        } catch (e: Exception) {
            callback?.onFlowFinish()
        }
        if (nextNode != null) {
            callback?.onNodeChanged(nextNode.getNodeId())
        } else {
            callback?.onFlowFinish()
            return
        }
        nextNode.setActionCallback(object : ActionNode.ActionCallback {
            override fun onActionComplete() {
                findAndExecuteNextNodeIfexist(nextIndex)
            }
        })
    }

    companion object {
        class Builder {
            private var flowNodes: SparseArray<ActionNode> = SparseArray()
            private var callback: FlowCallback? = null

            fun withNode(node: ActionNode): Builder {
                flowNodes.append(node.getNodeId(), node)
                return this
            }

            fun setFlowCallback(callback: FlowCallback): Builder {
                this.callback = callback
                return this
            }

            fun create(): ActionFlow {
                val flow = ActionFlow(flowNodes)
                flow.setFlowCallback(callback)
                return flow
            }
        }
    }

    interface FlowCallback {
        fun onFlowStart()
        fun onNodeChanged(nodeId: Int)
        fun onFlowFinish()
    }
}