cd /Users/dilmi/Documents/EI_Repo/product-ei/migration/migration-service;
mvn clean install;
rm /Users/dilmi/Desktop/migrator/node1/wso2ei-6.6.0-SNAPSHOT/dropins/org.wso2.carbon.ei.migration-6.6.0-SNAPSHOT.jar;
cp /Users/dilmi/Documents/EI_Repo/product-ei/migration/migration-service/target/org.wso2.carbon.ei.migration-6.6.0-SNAPSHOT.jar /Users/dilmi/Desktop/migrator/node1/wso2ei-6.6.0-SNAPSHOT/dropins/;
cd /Users/dilmi/Desktop/migrator/node1/wso2ei-6.6.0-SNAPSHOT/bin;
./integrator.sh -Dmigrate.from.product.version=ei650 -debug 5005;
