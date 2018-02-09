call mvn --version

timeout 3

echo "start install..."
echo "install icbc_2.0.0.5.jar"

call mvn install:install-file -DgroupId=icbc -DartifactId=icbc -Dversion=2.0.0.5 -Dpackaging=jar -Dfile=icbc_2.0.0.5.jar -DgeneratePom=true


echo "install success!"

pause