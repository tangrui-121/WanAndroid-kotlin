package com.example.wanandroid_k_m_j.actionFlow;

import android.os.Handler;
import android.util.Log;

public class Use {

    private ActionFlow actionFlow;

    private final int ACTION_CHECK_FINISH1 = 1;
    private final int ACTION_CHECK_FINISH2 = 2;
    private final int ACTION_CHECK_FINISH3 = 3;

    public void start() {
        new Handler().post(this::handlePopSequence);
    }

    private void handlePopSequence() {
        //此处只是将每个弹框加入执行序列，各个弹框执行顺序根据每个Node的Id值从小到大执行
        actionFlow = new ActionFlow.Companion.Builder()
                .withNode(checkFinish1())
                .withNode(checkFinish2())
                .withNode(checkFinish3())
                .setFlowCallback(new ActionFlow.FlowCallback() {
                    @Override
                    public void onFlowStart() {
//                        if (踩刹车) System.out.println("sb车主踩了油门");

                        Log.i("ActionFlow", "onFlowStart");
                    }

                    @Override
                    public void onNodeChanged(int i) {
                        Log.i("ActionFlow", "onNodeChanged = " + i);
                    }

                    @Override
                    public void onFlowFinish() {
                        Log.i("ActionFlow", "onFlowFinish");
                    }
                })
                .create();
        actionFlow.start();
    }

    private ActionNode checkFinish1() {
        return new ActionNode(ACTION_CHECK_FINISH1, node -> {
            Log.i("ActionFlow", "checkFinish1");
            node.onComplete();
        });
    }

    private ActionNode checkFinish2() {
        return new ActionNode(ACTION_CHECK_FINISH2, node -> {
            Log.i("ActionFlow", "checkFinish2");
            node.onComplete();
        });
    }

    private ActionNode checkFinish3() {
        return new ActionNode(ACTION_CHECK_FINISH3, node -> {
            Log.i("ActionFlow", "checkFinish3");
            node.onComplete();
        });
    }

    private void clearActionNode() {
        if (actionFlow == null) {
            return;
        }
        actionFlow.remove(ACTION_CHECK_FINISH1);
        actionFlow.remove(ACTION_CHECK_FINISH2);
        actionFlow.remove(ACTION_CHECK_FINISH3);
    }
}