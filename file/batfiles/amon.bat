@echo off
set long=50000
set period=20
if '%1' neq '' (set long=%1)
if '%2' neq '' (set period=%2)
adb shell monkey -p com.android.launcherWT  --ignore-crashes --ignore-timeouts --throttle %period% --pct-syskeys 0 -v %long%