@echo off
if '%1'=='' (
adb logcat> D:\adblogcat\adblog.txt
) else (
adb logcat> D:\adblogcat\%1.txt
)