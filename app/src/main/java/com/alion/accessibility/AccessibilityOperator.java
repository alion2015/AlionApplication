package com.alion.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;


import java.util.LinkedList;
import java.util.List;

import static com.alion.accessibility.AccessibilitySampleService.PARENTCLASSID;

/**
 * Created by alion on 2019/1/1.
 */

@TargetApi(16)
public class AccessibilityOperator {


    private Context mContext;
    private static AccessibilityOperator mInstance = new AccessibilityOperator();
    private AccessibilityService mAccessibilityService;

    private AccessibilityOperator() {
    }

    public static AccessibilityOperator getInstance() {
        return mInstance;
    }

    public void init(Context context) {
        mContext = context;
    }

    public void updateEvent(AccessibilityService service) {
        if (service != null) {
            mAccessibilityService = service;
        }
    }

    public boolean isServiceRunning() {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Short.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : services) {
            if (info.service.getClassName().equals(mContext.getPackageName() + ".AccessibilitySampleService")) {
                return true;
            }
        }
        return false;
    }

    public AccessibilityNodeInfo getRootNodeInfo() {
        AccessibilityNodeInfo nodeInfo = null;
        if (Build.VERSION.SDK_INT >= 16) {
            // 建议使用getRootInActiveWindow，这样不依赖当前的事件类型
            if (mAccessibilityService != null) {
                nodeInfo = mAccessibilityService.getRootInActiveWindow();
            }
        }
        return nodeInfo;
    }

    /**
     * 根据Text搜索所有符合条件的节点, 模糊搜索方式
     */
    public List<AccessibilityNodeInfo> findNodesByText(String text) {
        AccessibilityNodeInfo nodeInfo = getRootNodeInfo();
        if (nodeInfo != null) {
           return nodeInfo.findAccessibilityNodeInfosByText(text);
        }
        return null;
    }

    /**
     * 根据View的ID搜索符合条件的节点,精确搜索方式;
     * 这个只适用于自己写的界面，因为ID可能重复
     * api要求18及以上
     * @param viewId
     */
    public List<AccessibilityNodeInfo> findNodesById(String viewId) {
        AccessibilityNodeInfo nodeInfo = getRootNodeInfo();
        if (nodeInfo != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                return nodeInfo.findAccessibilityNodeInfosByViewId(viewId);
            }
        }
        return null;
    }

    public boolean clickByText(String text) {
        List<AccessibilityNodeInfo> nis = findNodesByText(text);
        if(null!=nis&& nis.size()>0) {
            AccessibilityNodeInfo item = nis.get(0);
            while (null!=item && !item.performAction(AccessibilityNodeInfo.ACTION_CLICK)){
                item=item.getParent();
            }
            return (null!=item)? false: true;
        }else{
            return  false;
        }
    }

    /**
     * 根据View的ID搜索符合条件的节点,精确搜索方式;
     * 这个只适用于自己写的界面，因为ID可能重复
     * api要求18及以上
     * @param viewId
     * @return 是否点击成功
     */
    public boolean clickById(String viewId) {
        return performClick(findNodesById(viewId));
    }

    private boolean performClick(List<AccessibilityNodeInfo> nodeInfos) {
        if (nodeInfos != null && !nodeInfos.isEmpty()) {
            AccessibilityNodeInfo node;
            for (int i = 0; i < nodeInfos.size(); i++) {
                node = nodeInfos.get(i);
                // 进行模拟点击
                if (node.isEnabled()) {
                    return node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
        return false;
    }

    public AccessibilityNodeInfo getNodeByClass(String classname){
        List<AccessibilityNodeInfo> recy = findNodesById(PARENTCLASSID);
        for(int i=0;i<recy.size();i++){
            AccessibilityNodeInfo item = recy.get(i);
            Log.d("alionlog", "getNodeByClass: "+item.getClassName().toString());
            if(item.getClassName().toString().contains(classname)){
                return item;
            }
        }
        return null;
    }

    public AccessibilityNodeInfo getNodeByClass(AccessibilityNodeInfo root, String classname) {
        /*if (root == null || (root.getClassName().toString().equals(classname)&&root.isScrollable())) return root;
        AccessibilityNodeInfo bew = null;
        for (int i = 0; i < root.getChildCount(); i++) {
            AccessibilityNodeInfo child = root.getChild(i);
            if(null!=child) {
                bew = getNodeByClass(child, classname);
                if (null != bew && bew.getClassName().toString().equals(classname)&&root.isScrollable()) {
                    return bew;
                }
            }
        }
        return bew;*/
        List<AccessibilityNodeInfo> all = new LinkedList<>();
        getAllNotes(all,root);
        for(int i = 0; i < all.size(); i++){
            AccessibilityNodeInfo child = all.get(i);
            if(child.getClassName().toString().equals(classname) && child.isScrollable()) {
                return child;
            }
        }
        return null;
    }

    private void getAllNotes(List<AccessibilityNodeInfo> all, AccessibilityNodeInfo root) {
        if(root==null )return ;
        all.add(root);
        for(int i = 0; i < root.getChildCount(); i++){
            AccessibilityNodeInfo child = root.getChild(i);
            getAllNotes(all,child);
        }
    }

    public boolean clickBackKey() {
        return performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }

    private boolean performGlobalAction(int action) {
        return mAccessibilityService.performGlobalAction(action);
    }
}
