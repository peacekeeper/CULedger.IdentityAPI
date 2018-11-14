FROM ubuntu:18.04
MAINTAINER Markus Sabadello <markus@danubetech.com>

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

RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 68DB5E88
RUN add-apt-repository "deb https://repo.sovrin.org/sdk/deb xenial stable"
RUN apt-get update
RUN apt-get install -y libindy=1.6.6

# copy sovrin files

ADD ./sovrin /opt/sovrin
RUN tar -xzf /opt/sovrin/indy_client.tgz -C /root

# install sovtoken and vcx

RUN dpkg -i /opt/sovrin/libsovtoken_0.9.35.4_amd64.deb
RUN dpkg -i /opt/sovrin/libvcx_0.1.22084524-98d291b_amd64.deb
RUN apt-get install -f

# install vcx wrapper

RUN mvn install:install-file -Dfile=/opt/sovrin/com.evernym-vcx_1.0.0-07-11-2018T08-42-1.0.0.jar -DpomFile=/opt/sovrin/com.evernym-vcx_1.0.0-07-11-2018T08-42-1.0.0.xml

# copy api files

ADD . /opt/api

# build api

RUN cd /opt/api && mvn install package -N -DskipTests

# clean up

RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# variables

ENV variable_key=variable_value

# done

EXPOSE 8080

RUN chmod a+rx /opt/api/docker/run.sh
CMD "/opt/api/docker/run.sh"
