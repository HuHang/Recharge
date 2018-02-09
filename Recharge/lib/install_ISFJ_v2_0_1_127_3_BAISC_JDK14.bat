call mvn --version

timeout 3

echo "start install..."
echo "install ISFJ_v2_0_1_127_3_BAISC_JDK14.jar"

call mvn install:install-file -DgroupId=ISFJ_v2_0_1_127_3_BAISC_JDK -DartifactId=ISFJ_v2_0_1_127_3_BAISC_JDK -Dversion=1.4 -Dpackaging=jar -Dfile=b2cVerify1.0.0.4.jar -DgeneratePom=true


echo "install success!"

pause