@echo off
if '%1'=='' (
adb shell ps|findstr monkey
) else (
adb shell kill %1
)