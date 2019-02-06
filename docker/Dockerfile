FROM ubuntu:18.04
MAINTAINER Markus Sabadello <markus@danubetech.com>

ARG VCX_INSTITUTION_DID_SEED=""
ARG VCX_GENESIS_PATH="pool_transactions_sandbox_genesis"
ARG VCX_INSTITUTION_LOGO_URL="https://culedger.s3.amazonaws.com/Connect.Me/CULedger-ConnectMe-Logo-256x256.png"
ARG VCX_INSTITUTION_NAME="CULedger"
ARG VCX_CREDENTIAL_NAME="myCUID"
ARG VCX_CREDENTIAL_VALUE="Test CU"
ARG VCX_TIMEOUT_CONNECTIONINVITE="600"
ARG VCX_TIMEOUT_CREDENTIALOFFER="300"
ARG VCX_TIMEOUT_CREDENTIALSEND="120"
ARG VCX_TIMEOUT_PROOFREQUEST="300"
ARG VCX_SCHEMA_ID
ARG VCX_CREDDEF_ID

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

# install sovtoken and vcx

RUN dpkg -i /opt/sovrin/libsovtoken_0.9.6_amd64.deb
RUN dpkg -i /opt/sovrin/libvcx_0.1.27328536-fb7b7b6_amd64.deb
RUN apt-get install -f

# install indy wrapper

RUN mvn install:install-file -Dfile=/opt/sovrin/indy-1.6.8.jar -DpomFile=/opt/sovrin/indy-1.6.8.xml

# install vcx wrapper

RUN mvn install:install-file -Dfile=/opt/sovrin/com.evernym-vcx_1.0.0-07-12-2018T08-51-1.0.0.jar -DpomFile=/opt/sovrin/com.evernym-vcx_1.0.0-07-12-2018T08-51-1.0.0.xml

# copy api files

ADD . /opt/culedger-identityapi

# build api

RUN cd /opt/culedger-identityapi && mvn install package -N -DskipTests

# state

RUN mkdir /opt/culedger-identityapi-state && mkdir /opt/culedger-identityapi-state/.indy_client/ && mkdir /opt/culedger-identityapi-state/memberdidmapper/
RUN tar -xzf /opt/sovrin/indy_client.tgz -C /opt/culedger-identityapi-state/
RUN ln -s /opt/culedger-identityapi-state/.indy_client /root/.indy_client
RUN ln -s /opt/culedger-identityapi-state/memberdidmapper /opt/culedger-identityapi/

# clean up

RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# configure

ENV VCX_INSTITUTION_DID_SEED ${VCX_INSTITUTION_DID_SEED}
ENV VCX_GENESIS_PATH ${VCX_GENESIS_PATH}
ENV VCX_INSTITUTION_LOGO_URL ${VCX_INSTITUTION_LOGO_URL}
ENV VCX_INSTITUTION_NAME ${VCX_INSTITUTION_NAME}
ENV VCX_CREDENTIAL_NAME ${VCX_CREDENTIAL_NAME}
ENV VCX_CREDENTIAL_VALUE ${VCX_CREDENTIAL_VALUE}
ENV VCX_TIMEOUT_CONNECTIONINVITE ${VCX_TIMEOUT_CONNECTIONINVITE}
ENV VCX_TIMEOUT_CREDENTIALOFFER ${VCX_TIMEOUT_CREDENTIALOFFER}
ENV VCX_TIMEOUT_CREDENTIALSEND ${VCX_TIMEOUT_CREDENTIALSEND}
ENV VCX_TIMEOUT_PROOFREQUEST ${VCX_TIMEOUT_PROOFREQUEST}
ENV VCX_SCHEMA_ID ${VCX_SCHEMA_ID}
ENV VCX_CREDDEF_ID ${VCX_CREDDEF_ID}

# done

EXPOSE 8080

RUN chmod a+rx /opt/culedger-identityapi/docker/run.sh
RUN chmod a+rx /opt/culedger-identityapi/docker/provision.sh
CMD "/opt/culedger-identityapi/docker/run.sh"
