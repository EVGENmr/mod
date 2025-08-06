@echo off
echo gotovo
md C:\modification
md C:\modification\mod

bitsadmin /transfer "DownloadTask" https://github.com/EVGENmr/mod/raw/refs/heads/master/mod.zip C:\modification\mod\mod.zip
powershell -Command "Expand-Archive -Path 'C:\modification\mod\mod.zip' -DestinationPath 'C:\modification\mod' -Force"
del "C:\modification\mod\mod.zip"

bitsadmin /transfer "DownloadTask" https://archive.org/download/jdk-8u181/jdk-8u181-windows-x64.exe C:\modification\JDK.exe
bitsadmin /transfer "DownloadTask" https://download.jetbrains.com/idea/ideaIC-2024.2.2.exe?_gl=1*mjqyai*_gcl_au*MTg1NTAzOTAyMC4xNzU0NDY4MzA2*FPAU*MTg1NTAzOTAyMC4xNzU0NDY4MzA2*_ga*MTU3Njk0OTg4OS4xNzI3MTExMzAw*_ga_9J976DJZ68*czE3NTQ0ODAyNTkkbzUkZzEkdDE3NTQ0ODAzNjckajU5JGwwJGgw C:\modification\IDEA.exe

:: start /wait "C:\modification\JDK.exe" /quiet INSTALL_DIR="C:\Program Files\Java\jdk-8u181"
:: start /wait "C:\modification\IDEA.exe" /S

setx JAVA_HOME "C:\Program Files\Java\jdk-8u181" /M
setx PATH "%PATH%;%JAVA_HOME%\bin" /M

:: Очистка временных файлов
:: del "C:\modification\JDK.exe"
:: del "C:\modification\IDEA.exe"
echo gotovo
pause