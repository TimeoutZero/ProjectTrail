echo "##############################################################"
echo "###################### RUN DEV VERSION  ######################"
echo "##############################################################"

cd .. && mvn clean tomcat7:run -Dspring.profiles.active=dev