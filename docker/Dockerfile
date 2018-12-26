FROM ubuntu:18.04
MAINTAINER Markus Sabadello <markus@danubetech.com>

ARG VCX_INSTITUTION_LOGO_URL="https://culedger.s3.amazonaws.com/Connect.Me/CULedger-ConnectMe-Logo-256x256.png"
ARG VCX_INSTITUTION_NAME="testcu"
ARG VCX_INSTITUTION_DID="SsPVi4HpA8jJx7wcTqCEQ4"
ARG VCX_CREDENTIAL_NAME="myCUID"
ARG VCX_DID_MAPPER="memory"

USER root

RUN apt-get -y update

RUN apt-get install -y --no-install-recommends openjdk-11-jdk maven

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64/
ENV PATH $JAVA_HOME/bin:$PATH

# install dependencies

RUN apt-get install -y --no-install-recommends gnupg dirmngr software-properties-common apt-transport-https
RUN apt-get install -y --no-install-recommends libssl-dev libsqlite3-dev libsodium-dev libzmq3-dev

# prepare maven

RUN mvn dependency:get -DgroupId=org.springframework.boot -DartifactId=spring-boot-maven-plugin -Dversion=2.1.0.RELEASE
RUN mvn dependency:get -DgroupId=org.springframework.boot -DartifactId=spring-boot-starter-web -Dversion=1.5.9.RELEASE
RUN mvn dependency:get -DgroupId=org.springframework.boot -DartifactId=spring-boot-starter-tomcat -Dversion=1.5.9.RELEASE

# install libindy

RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 68DB5E88
RUN add-apt-repository "deb https://repo.sovrin.org/sdk/deb xenial stable"
RUN apt-get update
RUN apt-get install -y libindy=1.6.8

# copy sovrin files

ADD ./sovrin /opt/sovrin
RUN tar -xzf /opt/sovrin/indy_client.tgz -C /root

# install sovtoken and vcx

RUN dpkg -i /opt/sovrin/libsovtoken_0.9.6_amd64.deb
RUN dpkg -i /opt/sovrin/libvcx_0.1.27328536-fb7b7b6_amd64.deb
RUN apt-get install -f

# install vcx wrapper

RUN mvn install:install-file -Dfile=/opt/sovrin/com.evernym-vcx_1.0.0-07-12-2018T08-51-1.0.0.jar -DpomFile=/opt/sovrin/com.evernym-vcx_1.0.0-07-12-2018T08-51-1.0.0.xml

# copy api files

ADD . /opt/api

# build api

RUN cd /opt/api && mvn install package -N -DskipTests

# clean up

RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# variables

ENV variable_key=variable_value

# configure

RUN cd /opt/sovrin && sed -i "s,--vcx_institution_logo_url--,${VCX_INSTITUTION_LOGO_URL},g" ./vcxconfig.json
ENV VCX_INSTITUTION_NAME ${VCX_INSTITUTION_NAME}
ENV VCX_INSTITUTION_DID ${VCX_INSTITUTION_DID}
ENV VCX_CREDENTIAL_NAME ${VCX_CREDENTIAL_NAME}
ENV VCX_DID_MAPPER ${VCX_DID_MAPPER}

# done

EXPOSE 8080

RUN chmod a+rx /opt/api/docker/run.sh
CMD "/opt/api/docker/run.sh"
