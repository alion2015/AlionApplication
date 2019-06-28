@echo off
if '%1'=='restart' (goto restart)
if '%1'=='kmonkey' (goto kmonkey)
if '%1'=='log' (goto log)
if '%1'=='rml' (goto rml)
if '%1'=='monkey' (goto monkey)
if '%1'=='apl' (goto apl)
if '%1'=='focus' (goto focus)
if '%1'=='xy' (goto xy)
if '%1'=='mn' (goto monitor)
if '%1'=='kapp' (goto kapp)
if '%1'=='vers' (goto version)

echo focus    :ResumedActivity of phone
echo vers     :version code of inputmethod
echo kmonkey  *a    :kill monkey
echo rml rml    :rm data of wecarnews
echo monkey  *a *b    :monkey for wecarnews long=a period=b
echo xy *a   :adb to xiaoyao a is index that start with 0
echo mn    :start UI monitor
echo kapp *a    :1/2/3 kill filemanager/maintenance/wecarnews
echo log log  *a    :logcat to a
echo apl    :pull databases of launcher to desktop
echo restart    :replace launcher and restart phone

:demo
goto break

:version
adb shell pm dump com.android.inputmethod.pinyin | findstr “versionName” 
goto break

:restart
adb shell rm -rf data/data/com.android.launcherWT*
adb shell rm data/app/com.android.launcherWT-1.apk
adb push D:\Launcher\launcherWT\launcherWT-release.apk data/app/com.android.launcherWT-1.apk
adb shell stop
adb shell start
goto break

:kmonkey
if '%2'=='' (
adb shell ps|findstr monkey
) else (
adb shell kill %2
)
goto break

:log
if '%2'=='' (
adb logcat> D:\adblog.txt
) else (
adb logcat> D:\%2.txt
)
goto break

:rml
adb shell rm -rf data/data/com.tencent.wecarnews*
goto break

:monkey
set long=50000
set period=20
if '%2' neq '' (set long=%1)
if '%3' neq '' (set period=%2)
adb shell monkey -p com.tencent.wecarnews  --ignore-crashes --ignore-timeouts --throttle %period% --pct-syskeys 0 -v %long%
goto break

:apl
adb pull data/data/com.android.launcherWT/databases C:\Users\lenovo\Desktop
goto break

:focus
adb shell dumpsys activity | findstr mFocusedActivity
adb shell dumpsys activity | findstr ResumedActivity
adb shell dumpsys window | findstr mCurrentFocus
goto break

:xy
set index=0
if '%2' neq '' (set index=%2)
adb connect 127.0.0.1:215%index%3
goto break

:monitor
start /d "D:\androidSDK\tools\bin" uiautomatorviewer.bat
goto break

:kapp
if '%2'=='1' (
adb shell am force-stop com.wucl.filemanager
) else (
if '%2'=='2' (
adb shell am force-stop com.wt.maintenance)
) else (
if '%2'=='3' (
adb shell am force-stop com.tencent.wecarnews)
)
goto break

:break