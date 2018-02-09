call mvn --version

timeout 3

echo "start install..."
echo "install b2cVerify1.0.0.4.jar"

call mvn install:install-file -DgroupId=b2cVerify -DartifactId=b2cVerify -Dversion=1.0.0.4 -Dpackaging=jar -Dfile=b2cVerify1.0.0.4.jar -DgeneratePom=true


echo "install success!"

pause