package com.example.utilsdk.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.httpsdk.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by admin on 2018-10-08.
 */

public class ImageLoaderManager {
    private static final int THREAD_COUNT = 4;
    private static final int PROPRITY = 2;
    private static final int DISK_CACHE_SIZE = 50*1024;
    private static final int CONNECTION_TIME_OUT = 5*1000;
    private static final int READ_TIME_OUT = 30*1000;

    private static ImageLoader mImageLoader = null;
    private static ImageLoaderManager mInstance = null;

    private static ImageLoaderManager getInstance(Context context){
        if(mInstance == null){
            synchronized (ImageLoaderManager.class){
                if(mInstance == null){
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    private ImageLoaderManager(Context context){
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(THREAD_COUNT)
                .threadPriority(Thread.NORM_PRIORITY-PROPRITY)
                .denyCacheImageMultipleSizesInMemory()//防止缓存多套
                .memoryCache(new WeakMemoryCache())//使用若引用
                .diskCacheSize(DISK_CACHE_SIZE)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//md5命名文件
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(getDefaultOptions())//默认图片加载option
                .imageDownloader(new BaseImageDownloader(context,CONNECTION_TIME_OUT,READ_TIME_OUT)) //图片下载器
                .writeDebugLogs()//写日志
                .build();
        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();
    }

    private DisplayImageOptions getDefaultOptions(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error)//图片地址为空的时候
                .showImageOnFail(R.drawable.xadsdk_img_error)//图片下载失败的时候显示的图片
                .cacheInMemory(true)//设置图片可以缓存在内存
                .cacheOnDisk(true)//设置图片可以缓存在硬盘
                .bitmapConfig(Bitmap.Config.RGB_565)//使用的解码类型
                .decodingOptions(new BitmapFactory.Options())//图片解码配置
                .build();
        return options;
    }

    public void displayImage(ImageView imageView, String url,
                             DisplayImageOptions options,
                             ImageLoadingListener listener){
        if(mImageLoader != null){
            mImageLoader.displayImage(url,imageView,options,listener);
        }
    }

    public void display(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener listener){
        displayImage(imageView,url,options,listener);
    }
    public void display(ImageView imageView, String url, ImageLoadingListener listener){
        displayImage(imageView,url,null,listener);
    }

    public void display(ImageView imageView, String url){
        displayImage(imageView,url,null,null);
    }


}
