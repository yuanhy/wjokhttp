/*
 * Copyright (C) 20015 MaiNaEr All rights reserved
 */
package com.mainaer.wjokhttp.controller;

import android.os.Environment;

import com.mainaer.wjokhttp.model.DownRequest;
import com.mainaer.wjokhttp.url.URLConst;
import com.mainaer.wjoklib.okhttp.IUrl;
import com.mainaer.wjoklib.okhttp.OKDownLoadController;

import java.io.File;

/**
 * 类/接口描述
 *
 * @author wangjian
 * @date 2016/3/28.
 */
public class DownController extends OKDownLoadController<DownController.DownLoadListener> {

    public DownController(DownLoadListener l) {
        super(l);
    }

    public void downLoad(){
        DownTask downTask = new DownTask();
        downTask.load(new DownRequest(), File.class);
    }

    private class DownTask extends BaseDownLoadTask<DownRequest>{
        @Override
        protected void inProgress(float progress, long total) {
            mListener.onDownLoadProgress(progress, total);
        }

        @Override
        protected String getFileDir() {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }

        @Override
        protected String getFileName() {
            return System.currentTimeMillis()+".apk";
        }

        @Override
        protected IUrl getUrl() {
            return URLConst.getListUrl();
        }

        @Override
        protected void onSuccess(File file) {
            mListener.onDownLoadSuccess(file);
        }

        @Override
        protected void onError(String error) {

        }
    }

    public interface DownLoadListener{
        void onDownLoadProgress(float progress, long total);
        void onDownLoadSuccess(File file);
    }


}