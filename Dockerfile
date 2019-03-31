FROM ubuntu:16.04
LABEL MAINTAINER Konstantinos Chatzis <kachatzis@ece.auth.gr>

EXPOSE 5091

ENV DEBIAN_FRONTEND noninteractive
ENV JAVA_HOME       /usr/lib/jvm/java-8-oracle
ENV LANG            en_US.UTF-8
ENV LC_ALL          en_US.UTF-8

RUN apt-get update && \
    apt-get install -y --no-install-recommends locales && \
    locale-gen en_US.UTF-8 && \
    apt-get dist-upgrade -y && \
    apt-get --purge remove openjdk* && \
    echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections && \
    echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" > /etc/apt/sources.list.d/webupd8team-java-trusty.list && \
    apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EEA14886 && \
    apt-get update && \
    apt-get install -y --no-install-recommends oracle-java8-installer oracle-java8-set-default && \
    apt-get clean all \
    apt-get install -y vnc4server \
    echo $VNC_PASS >/tmp/file \
    echo $VNC_PASS >>/tmp/file \
    vncpasswd </tmp/file >/tmp/vncpasswd.1 2>/tmp/vncpasswd.2 \
    vncserver -geometry 800x600

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

COPY ./zazu-softpos/pos.jar /app/pos.jar

# Define default command.
CMD ["java -jar /app/pos.jar"]