@echo off
echo Building Java/Kotlin to Godot JAR...
call gradlew build
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Build failed! Godot will not start.
    pause
    exit /b %ERRORLEVEL%
)

echo Build SUCCESS! Launching Godot...
"C:\Users\ADMIN\Downloads\godot-kotlin-jvm_editor_windows_x86_64_debug_0.14.3-4.5.1\godot.windows.editor.x86_64.jvm.0.14.3.exe"
