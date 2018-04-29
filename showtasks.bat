call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openwebbrowser
echo.
echo runcrud.bat has errors
go to fail

:openwebbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.