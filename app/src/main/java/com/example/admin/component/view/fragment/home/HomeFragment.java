package com.example.admin.component.view.fragment.home;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.component.R;
import com.example.admin.component.adapter.CourseAdapter;
import com.example.admin.component.module.recommand.BaseRecommandModel;
import com.example.admin.component.network.http.RequestCenter;
import com.example.admin.component.view.fragment.BaseFragment;
import com.example.utilsdk.okhttp.listener.DisposeDataListener;

import static android.content.ContentValues.TAG;

/**
 * *******************************************************
 *
 * @文件名称：HomeFragment.java
 * @文件作者：renzhiqiang
 * @创建时间：2015年10月2日 下午2:56:50
 * @文件描述：完全是普通的Fragment
 * @修改历史：2015年10月2日创建初始版本 ********************************************************
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final int REQUEST_QRCODE = 0x01;
    /**
     * UI
     */
    private View mContentView;
    private ListView mListView;
    private TextView mQRCodeView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;

    private BaseRecommandModel mRecommandData;
    private CourseAdapter mAdapter;


    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mContentView;
    }

    private void initView() {
        mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);
        mCategoryView = (TextView) mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = (TextView) mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = (ListView) mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        mLoadingView = (ImageView) mContentView.findViewById(R.id.loading_view);

        //启动loading view
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    //发送首页列表数据请求
    private void requestRecommandData() {
        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.e(TAG,"onSuccess:"+responseObj.toString());
                mRecommandData = (BaseRecommandModel) responseObj;
                //更新UI
                showSuccessView();

            }

            @Override
            public void onFailure(Object reasonObj) {
                //显示请求失败View
//                showErrorView();
                Log.e(TAG,"onFailure:"+reasonObj.toString());
            }
        });
    }

//    //显示请求成功UI
    private void showSuccessView() {
        if (mRecommandData.data.list != null && mRecommandData.data.list.size() > 0) {
            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            //为listview添加头
           // mListView.addHeaderView(new HomeHeaderLayout(mContext, mRecommandData.data.head));
            mAdapter = new CourseAdapter(mContext, mRecommandData.data.list);
            mListView.setAdapter(mAdapter);
//            mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(AbsListView view, int scrollState) {
//                }
//
//                @Override
//                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                    mAdapter.updateAdInScrollView();
//                }
//            });
        } else {
            showErrorView();
        }
    }

    private void showErrorView() {
    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.qrcode_view:
//                if (hasPermission(Constant.HARDWEAR_CAMERA_PERMISSION)) {
//                    doOpenCamera();
//                } else {
//                    requestPermission(Constant.HARDWEAR_CAMERA_CODE, Constant.HARDWEAR_CAMERA_PERMISSION);
//                }
//                break;
//            case R.id.category_view:
//                //与我交谈
//                Intent intent2 = new Intent(Intent.ACTION_VIEW, Util.createQQUrl("277451977"));
//                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent2);
//                break;
//            case R.id.search_view:
//                Intent searchIntent = new Intent(mContext, SearchActivity.class);
//                mContext.startActivity(searchIntent);
//                break;
//        }
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//       RecommandBodyValue value = (RecommandBodyValue) mAdapter.getItem(position - mListView.getHeaderViewsCount());
//        if (value.type != 0) {
//            Intent intent = new Intent(mContext, PhotoViewActivity.class);
//            intent.putStringArrayListExtra(PhotoViewActivity.PHOTO_LIST, value.url);
//            startActivity(intent);
//        }
//    }
//
//    @Override
//    public void doOpenCamera() {
//        Intent intent = new Intent(mContext, CaptureActivity.class);
//        startActivityForResult(intent, REQUEST_QRCODE);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case REQUEST_QRCODE:
//                if (resultCode == Activity.RESULT_OK) {
//                    String code = data.getStringExtra("SCAN_RESULT");
//                    if (code.contains("http") || code.contains("https")) {
//                        Intent intent = new Intent(mContext, AdBrowserActivity.class);
//                        intent.putExtra(AdBrowserActivity.KEY_URL, code);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(mContext, code, Toast.LENGTH_SHORT).show();
//                    }
//                }
//                break;
//        }
//    }

}