package com.alion.demo;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.assist.AssistContent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.wutong.ContentObserver.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;


/**
 * Created by lenovo on 2018/10/24.
 */

public class TestActivity extends AppCompatActivity {
    private String TAG = "frameWorkBaseActivity__";
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        Log.d(TAG, "onCreateView: (View parent, String name, Context context, AttributeSet attrs) ");
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.d(TAG, "onCreateView: (String name, Context context, AttributeSet attrs) ");
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public void openOptionsMenu() {
        Log.d(TAG, "openOptionsMenu: enter");
        super.openOptionsMenu();
        Log.d(TAG, "openOptionsMenu: exit");
    }

    @Override
    public void openContextMenu(View view) {
        Log.d(TAG, "openContextMenu: enter");
        super.openContextMenu(view);
        Log.d(TAG, "openContextMenu: exit");
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        Log.d(TAG, "overridePendingTransition: enter");
        super.overridePendingTransition(enterAnim, exitAnim);
        Log.d(TAG, "overridePendingTransition: exit");
    }

    @Override
    public FileInputStream openFileInput(String name) throws FileNotFoundException {
        Log.d(TAG, "openFileInput: enter");
        return super.openFileInput(name);
    }

    @Override
    public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
        Log.d(TAG, "openFileOutput: ");
        return super.openFileOutput(name, mode);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        Log.d(TAG, "openOrCreateDatabase: ");
        return super.openOrCreateDatabase(name, mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        Log.d(TAG, "openOrCreateDatabase: ");
        return super.openOrCreateDatabase(name, mode, factory, errorHandler);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: enter");
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: exit");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: enter");
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: exit");
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        Log.d(TAG, "onCreatePanelMenu: ");
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: ");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onLowMemory() {
        Log.d(TAG, "onLowMemory: enter");
        super.onLowMemory();
        Log.d(TAG, "onLowMemory: exit");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: enter");
        super.onPause();
        Log.d(TAG, "onPause: exit");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent: enter");
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: exit");
    }

    @Override
    public void onStateNotSaved() {
        Log.d(TAG, "onStateNotSaved: enter");
        super.onStateNotSaved();
        Log.d(TAG, "onStateNotSaved: exit");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: enter");
        super.onResume();
        Log.d(TAG, "onResume: exit");
    }

    @Override
    protected void onResumeFragments() {
        Log.d(TAG, "onResumeFragments: enter");
        super.onResumeFragments();
        Log.d(TAG, "onResumeFragments: exit");
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        Log.d(TAG, "onPreparePanel: ");
        return super.onPreparePanel(featureId, view, menu);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        Log.d(TAG, "onPrepareOptionsPanel: ");
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: enter");
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: exit");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: enter");
        super.onStart();
        Log.d(TAG, "onStart: exit");
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Log.d(TAG, "onRetainCustomNonConfigurationInstance: ");
        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        Log.d(TAG, "onAttachFragment: ");
        super.onAttachFragment(fragment);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: ");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        Log.d(TAG, "onCreate: enter");
        super.onCreate(savedInstanceState, persistentState);
        Log.d(TAG, "onCreate: exit");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: enter");
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: exit");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.d(TAG, "onRestoreInstanceState: enter");
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.d(TAG, "onRestoreInstanceState: exit");
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        Log.d(TAG, "onPostCreate: enter");
        super.onPostCreate(savedInstanceState, persistentState);
        Log.d(TAG, "onPostCreate: exit");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: enter");
        super.onRestart();
        Log.d(TAG, "onRestart: exit");
    }

    @Override
    public void onLocalVoiceInteractionStarted() {
        Log.d(TAG, "onLocalVoiceInteractionStarted: ");
        super.onLocalVoiceInteractionStarted();
    }

    @Override
    public void onLocalVoiceInteractionStopped() {
        Log.d(TAG, "onLocalVoiceInteractionStopped: enter");
        super.onLocalVoiceInteractionStopped();
        Log.d(TAG, "onLocalVoiceInteractionStopped: exit");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.d(TAG, "onSaveInstanceState: enter");
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState: exit");
    }

    @Override
    protected void onUserLeaveHint() {
        Log.d(TAG, "onUserLeaveHint: enter");
        super.onUserLeaveHint();
        Log.d(TAG, "onUserLeaveHint: exit");
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        Log.d(TAG, "onCreateThumbnail: ");
        return super.onCreateThumbnail(outBitmap, canvas);
    }

    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        Log.d(TAG, "onCreateDescription: ");
        return super.onCreateDescription();
    }

    @Override
    public void onProvideAssistData(Bundle data) {
        Log.d(TAG, "onProvideAssistData: enter");
        super.onProvideAssistData(data);
        Log.d(TAG, "onProvideAssistData: exit");
    }

    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        Log.d(TAG, "onProvideAssistContent: enter");
        super.onProvideAssistContent(outContent);
        Log.d(TAG, "onProvideAssistContent: exit");
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        Log.d(TAG, "onProvideKeyboardShortcuts: enter");
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
        Log.d(TAG, "onProvideKeyboardShortcuts: exit");
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        Log.d(TAG, "onMultiWindowModeChanged: enter");
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        Log.d(TAG, "onMultiWindowModeChanged: exit");
    }


    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        Log.d(TAG, "onPictureInPictureModeChanged: enter");
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        Log.d(TAG, "onPictureInPictureModeChanged: exit");
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d(TAG, "onTrimMemory: (int level)enter");
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory: (int level)exit");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        Log.d(TAG, "onAttachFragment: enter");
        super.onAttachFragment(fragment);
        Log.d(TAG, "onAttachFragment: exit");
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyLongPress: ");
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyUp: (int keyCode, KeyEvent event)");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        Log.d(TAG, "onKeyMultiple: (int keyCode, int repeatCount, KeyEvent event) ");
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyShortcut: (int keyCode, KeyEvent event) ");
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: (MotionEvent event) ");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        Log.d(TAG, "onTrackballEvent: ");
        return super.onTrackballEvent(event);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        Log.d(TAG, "onGenericMotionEvent: ");
        return super.onGenericMotionEvent(event);
    }

    @Override
    public void onUserInteraction() {
        Log.d(TAG, "onUserInteraction: enter");
        super.onUserInteraction();
        Log.d(TAG, "onUserInteraction: exit");
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        Log.d(TAG, "onWindowAttributesChanged: enter");
        super.onWindowAttributesChanged(params);
        Log.d(TAG, "onWindowAttributesChanged: exit");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.d(TAG, "onWindowFocusChanged: enter");
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged: exit");
    }

    @Override
    public void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow: enter");
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: exit");
    }

    @Override
    public void onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow: enter");
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: exit");
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        Log.d(TAG, "onCreatePanelView: ");
        return super.onCreatePanelView(featureId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(TAG, "onPrepareOptionsMenu: ");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigateUp() {
        Log.d(TAG, "onNavigateUp: ");
        return super.onNavigateUp();
    }

    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        Log.d(TAG, "onNavigateUpFromChild: ");
        return super.onNavigateUpFromChild(child);
    }

    @Override
    public void onCreateNavigateUpTaskStack(android.app.TaskStackBuilder builder) {
        Log.d(TAG, "onCreateNavigateUpTaskStack: enter");
        super.onCreateNavigateUpTaskStack(builder);
        Log.d(TAG, "onCreateNavigateUpTaskStack: exit");
    }

    @Override
    public void onPrepareNavigateUpTaskStack(android.app.TaskStackBuilder builder) {
        Log.d(TAG, "onPrepareNavigateUpTaskStack: enter");
        super.onPrepareNavigateUpTaskStack(builder);
        Log.d(TAG, "onPrepareNavigateUpTaskStack: exit");
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        Log.d(TAG, "onOptionsMenuClosed: enter");
        super.onOptionsMenuClosed(menu);
        Log.d(TAG, "onOptionsMenuClosed: exit");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.d(TAG, "onCreateContextMenu: enter");
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d(TAG, "onCreateContextMenu: exit");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d(TAG, "onContextItemSelected: ");
        return super.onContextItemSelected(item);
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        Log.d(TAG, "onContextMenuClosed: enter");
        super.onContextMenuClosed(menu);
        Log.d(TAG, "onContextMenuClosed: exit");
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Log.d(TAG, "onCreateDialog: ");
        return super.onCreateDialog(id);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        Log.d(TAG, "onCreateDialog: ");
        return super.onCreateDialog(id, args);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        Log.d(TAG, "onPrepareDialog: enter");
        super.onPrepareDialog(id, dialog);
        Log.d(TAG, "onPrepareDialog: exit");
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
        Log.d(TAG, "onPrepareDialog: enter");
        super.onPrepareDialog(id, dialog, args);
        Log.d(TAG, "onPrepareDialog: exit");
    }

    @Override
    public boolean onSearchRequested( @Nullable SearchEvent searchEvent) {
        Log.d(TAG, "onSearchRequested: ");
        return super.onSearchRequested(searchEvent);
    }

    @Override
    public boolean onSearchRequested() {
        Log.d(TAG, "onSearchRequested: ");
        return super.onSearchRequested();
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, @StyleRes int resid, boolean first) {
        Log.d(TAG, "onApplyThemeResource: enter");
        super.onApplyThemeResource(theme, resid, first);
        Log.d(TAG, "onApplyThemeResource: exit");
    }

    @Override
    public Uri onProvideReferrer() {
        Log.d(TAG, "onProvideReferrer: ");
        return super.onProvideReferrer();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        Log.d(TAG, "onActivityReenter: enter");
        super.onActivityReenter(resultCode, data);
        Log.d(TAG, "onActivityReenter: exit");
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        Log.d(TAG, "onChildTitleChanged: enter");
        super.onChildTitleChanged(childActivity, title);
        Log.d(TAG, "onChildTitleChanged: exit");
    }

    @Override
    public void onVisibleBehindCanceled() {
        Log.d(TAG, "onVisibleBehindCanceled: enter");
        super.onVisibleBehindCanceled();
        Log.d(TAG, "onVisibleBehindCanceled: exit");
    }

    @Override
    public void onEnterAnimationComplete() {
        Log.d(TAG, "onEnterAnimationComplete: enter");
        super.onEnterAnimationComplete();
        Log.d(TAG, "onEnterAnimationComplete: exit");
    }

    @Nullable
    @Override
    public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback) {
        Log.d(TAG, "onWindowStartingActionMode: ");
        return super.onWindowStartingActionMode(callback);
    }

    @Nullable
    @Override
    public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback, int type) {
        Log.d(TAG, "onWindowStartingActionMode: ");
        return super.onWindowStartingActionMode(callback, type);
    }

    @Override
    public void onActionModeStarted(android.view.ActionMode mode) {
        Log.d(TAG, "onActionModeStarted: enter");
        super.onActionModeStarted(mode);
        Log.d(TAG, "onActionModeStarted: exit");
    }

    @Override
    public void onActionModeFinished(android.view.ActionMode mode) {
        Log.d(TAG, "onActionModeFinished: enter");
        super.onActionModeFinished(mode);
        Log.d(TAG, "onActionModeFinished: exit");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: enter");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        findViewById(R.id.testbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(false);
            }
        });
        Log.d(TAG, "onCreate: exit");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onPostCreate: enter");
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, "onPostCreate: exit");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged: enter");
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: exit");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: enter");
        super.onStop();
        Log.d(TAG, "onStop: exit");
    }

    @Override
    protected void onPostResume() {
        Log.d(TAG, "onPostResume: enter");
        super.onPostResume();
        Log.d(TAG, "onPostResume: exit");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: enter");
        super.onDestroy();
        Log.d(TAG, "onDestroy: exit");
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        Log.d(TAG, "onTitleChanged: enter");
        super.onTitleChanged(title, color);
        Log.e(TAG, "onTitleChanged: exit");
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {
        Log.d(TAG, "onSupportActionModeStarted: enter");
        super.onSupportActionModeStarted(mode);
        Log.d(TAG, "onSupportActionModeStarted: exit");
    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {
        Log.d(TAG, "onSupportActionModeFinished: enter");
        super.onSupportActionModeFinished(mode);
        Log.d(TAG, "onSupportActionModeFinished: exit");
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        Log.d(TAG, "onWindowStartingSupportActionMode: ");
        return super.onWindowStartingSupportActionMode(callback);
    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder builder) {
        Log.d(TAG, "onCreateSupportNavigateUpTaskStack: enter");
        super.onCreateSupportNavigateUpTaskStack(builder);
        Log.d(TAG, "onCreateSupportNavigateUpTaskStack: exit");
    }

    @Override
    public void onPrepareSupportNavigateUpTaskStack(TaskStackBuilder builder) {
        Log.d(TAG, "onPrepareSupportNavigateUpTaskStack: enter");
        super.onPrepareSupportNavigateUpTaskStack(builder);
        Log.d(TAG, "onPrepareSupportNavigateUpTaskStack: exit");
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.d(TAG, "onSupportNavigateUp: ");
        return super.onSupportNavigateUp();
    }

    @Override
    public void onContentChanged() {
        Log.d(TAG, "onContentChanged: enter");
        super.onContentChanged();
        Log.e(TAG, "onContentChanged: exit");
    }

    @Override
    public void onSupportContentChanged() {
        Log.d(TAG, "onSupportContentChanged: enter");
        super.onSupportContentChanged();
        Log.d(TAG, "onSupportContentChanged: exit");
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Log.d(TAG, "onMenuOpened: ");
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        Log.d(TAG, "onPanelClosed: enter");
        super.onPanelClosed(featureId, menu);
        Log.d(TAG, "onPanelClosed: exit");
    }
}
