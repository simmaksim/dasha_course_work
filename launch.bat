echo off
for /f %%i in ('docker-machine ip default') do set HOST=%%i
set URL=%HOST%:8888
start chrome %URL%