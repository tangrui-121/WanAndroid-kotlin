package com.example.wanandroid_k_m_j.weight;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wanandroid_k_m_j.R;

public class DialogBuilder {

    private Context mContext;
    private View mLayout;
    private LayoutInflater mInflater;
    private Display mDisplay;

    private LinearLayout mContent;
    private TextView sureButton;
    private TextView cancelButton;

    private View.OnClickListener sureClickListener, cancelClickListener;

    private boolean mCancelable = false;

    private Dialog mDialog;
    private TextView tvMessage;
    private TextView tvTitle;
    private RecyclerView rvDialogdata;
    private ScrollView slContent;

    public static DialogBuilder newBuilder(Context context) {
        return new DialogBuilder(context);
    }

    public DialogBuilder(Context context) {
        this(context, R.layout.base_dialog);
    }

    public static DialogBuilder newBuilder(Context context, int layoutRes) {
        return new DialogBuilder(context, layoutRes);
    }

    public DialogBuilder(Context context, int layoutRes) {
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (mInflater != null) {
            mLayout = mInflater.inflate(layoutRes, null);
        }
        tvTitle = mLayout.findViewById(R.id.tv_title);
        tvMessage = this.mLayout.findViewById(R.id.tv_msg);
        cancelButton = mLayout.findViewById(R.id.tv_no);
        rvDialogdata = mLayout.findViewById(R.id.rv_dialog_data);
        sureButton = mLayout.findViewById(R.id.tv_yes);
        slContent = mLayout.findViewById(R.id.sl_dialog_content);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            mDisplay = windowManager.getDefaultDisplay();
        }
    }

    public DialogBuilder cancelable(Boolean cancelable) {
        this.mCancelable = cancelable;
        return this;
    }

    public DialogBuilder title(int title) {
        if (null != tvTitle) {
            tvTitle.setText(title);
        }
        return this;
    }

    public DialogBuilder title(CharSequence title) {
        if (null != tvTitle) {
            tvTitle.setText(title);
        }
        return this;
    }

    public DialogBuilder titleVisble(int v) {
        if (null != tvTitle) {
            tvTitle.setVisibility(v);
        }
        return this;
    }

    public DialogBuilder titleColor(int color) {
        if (null != tvTitle) {
            tvTitle.setTextColor(mContext.getResources().getColor(color));
        }
        return this;
    }

    public DialogBuilder titleSize(float size) {
        if (null != tvTitle) {
            tvTitle.setTextSize(size);
        }
        return this;
    }

    public DialogBuilder showTitle(boolean show) {
        if (null != tvTitle) {
            tvTitle.setVisibility(show ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public DialogBuilder message(CharSequence message) {
        if (null != tvMessage) {
            tvMessage.setText(message);
        }
        return this;
    }

    public DialogBuilder message(int message) {
        if (null != tvMessage) {
            tvMessage.setText(message);
        }
        return this;
    }

    public DialogBuilder messageGravit(int gravity) {
        if (null != tvMessage) {
            tvMessage.setGravity(gravity);
        }
        return this;
    }

    public DialogBuilder messageColor(int color) {
        if (null != tvMessage) {
            tvMessage.setTextColor(mContext.getResources().getColor(color));
        }
        return this;
    }

    public DialogBuilder messageSize(float size) {
        if (null != tvMessage) {
            tvMessage.setTextSize(size);
        }
        return this;
    }

    public DialogBuilder contentView(View v) {
        if (null == mContent) {
            if (null != mLayout.findViewById(R.id.ll_dialog_content)) {
                mContent = (LinearLayout) mLayout.findViewById(R.id.ll_dialog_content);
            }
        }
        if (null != mContent) {
            mContent.removeAllViews();
            mContent.addView(v);
        }
        return this;
    }

    public DialogBuilder contentView(int res) {
        if (null == mContent) {
            if (null != mLayout.findViewById(R.id.ll_dialog_content)) {
                mContent = (LinearLayout) mLayout.findViewById(R.id.ll_dialog_content);
            }
        }
        if (null != mContent) {
            mContent.removeAllViews();

            if (mInflater != null) {
                View v = mInflater.inflate(res, null);
                // View v  = View.inflate(mContext,res,mContent);
                mContent.addView(v);
            }
        }
        return this;
    }

    public DialogBuilder contentView(View v, LinearLayout.LayoutParams params) {
        if (null == mContent) {
            if (null != mLayout.findViewById(R.id.ll_dialog_content)) {
                mContent = (LinearLayout) mLayout.findViewById(R.id.ll_dialog_content);
            }
        }
        if (null != mContent) {
            mContent.removeAllViews();
            mContent.addView(v, params);
        }
        return this;
    }

    public DialogBuilder sureText(CharSequence str) {
        if (null != sureButton) {
            sureButton.setText(str);
        }
        return this;
    }

    public DialogBuilder sureTextColor(int color) {
        if (null != sureButton) {
            sureButton.setTextColor(mContext.getResources().getColor(color));
        }
        return this;
    }

    public DialogBuilder cancelText(CharSequence str) {
        if (null != cancelButton) {
            cancelButton.setText(str);
        }
        return this;
    }

    public DialogBuilder cancelTextColor(int color) {
        if (null != cancelButton) {
            cancelButton.setTextColor(mContext.getResources().getColor(color));
        }
        return this;
    }

    public DialogBuilder showSureButton(boolean isShow) {
        if (null != sureButton) {
            sureButton.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public DialogBuilder showCancelButton(boolean isShow) {
        if (null != cancelButton) {
            cancelButton.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public DialogBuilder adapter(BaseQuickAdapter adapter) {
        if (null != slContent) {
            slContent.setVisibility(View.GONE);
        }
        if (null != rvDialogdata) {
            rvDialogdata.setVisibility(View.VISIBLE);
            rvDialogdata.setLayoutManager(new LinearLayoutManager(mContext));
            rvDialogdata.setAdapter(adapter);
        }
        return this;
    }

    public DialogBuilder onSureClickListener(View.OnClickListener listener) {
        this.sureClickListener = listener;
        return this;
    }

    public DialogBuilder onCancelClickListener(View.OnClickListener listener) {
        this.cancelClickListener = listener;
        return this;
    }

    /**
     * OnSureClickUnDismissListener
     * 作用。。。
     */
    public interface OnSureClickUnDismissListener {
        void onClick(Dialog dialog);
    }

    private OnSureClickUnDismissListener unDismissListener;

    public DialogBuilder setOnSureClickUnDismissListener(OnSureClickUnDismissListener listener) {
        this.unDismissListener = listener;
        return this;
    }

    public void show() {
        if (mContext instanceof Activity && !((Activity) mContext).isFinishing()) {
            this.builder().show();
        }
    }

    public Dialog builder() {
        mDialog = new Dialog(mContext, R.style.base_DialogStyle);
        mDialog.setContentView(mLayout);

        Window dialogWindow = mDialog.getWindow();
        if (dialogWindow != null) {
            dialogWindow.setGravity(Gravity.CENTER);
            dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);//不出现黑棱角
            //设置宽度
            WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
            int displayHeight = mDisplay.getHeight();
            int displayWidth = mDisplay.getWidth();
            int rootWidth = displayWidth > displayHeight ? displayHeight : displayWidth;
            params.width = (int) (rootWidth * 0.80);
            dialogWindow.setAttributes(params);
        }
        mDialog.setCancelable(mCancelable);

        // 添加点击事件
        if (null != mLayout.findViewById(R.id.tv_yes)) {

            mLayout.findViewById(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (unDismissListener != null) {
                        unDismissListener.onClick(mDialog);
                        return;
                    }

                    if (sureClickListener != null) {
                        sureClickListener.onClick(v);
                    }
                    mDialog.dismiss();
                }
            });
        }
        if (null != mLayout.findViewById(R.id.tv_no)) {
            mLayout.findViewById(R.id.tv_no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cancelClickListener != null) {
                        cancelClickListener.onClick(v);
                    }
                    mDialog.dismiss();
                }
            });
        }

        return mDialog;
    }
}