FROM centos:7
LABEL Description="This is a base image, which provides a selenium environment"

ARG user=jenkins
ARG group=jenkins
ARG uid=1341
ARG gid=1341
RUN getent group ${gid} || groupadd -g ${gid} ${group}
RUN useradd -c "Jenkins user" -d /home/${user} -u ${uid} -g ${gid} -m ${user}

VOLUME /selenium


ARG JAVA_VERSION=1.8.0
ENV JAVA_HOME /usr/lib/jvm/java

RUN yum-config-manager --enable cr && \
    yum -y --setopt=tsflags=nodocs update && \
    yum -y install java-${JAVA_VERSION}-openjdk-devel wget && \
    yum -y clean all && \
    rm -rf /var/cache/yum

RUN yum -y install unzip

ARG MAVEN_VERSION=3.6.3
ENV MAVEN_URL https://mirror.netcologne.de/apache.org/

ENV MAVEN_PKG ${MAVEN_URL}/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz
ENV MAVEN_HOME /opt/apache-maven-${MAVEN_VERSION}
ENV PATH /usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:${MAVEN_HOME}/bin

WORKDIR /opt

RUN curl ${MAVEN_PKG} | tar xz

RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm && \
    yum localinstall google-chrome-stable_current_x86_64.rpm -y  && \
    rm -rf google-chrome-stable_current_x86_64.rpm
    

# Set up Chromedriver Environment variables
ENV CHROMEDRIVER_VERSION 86.0.4240.22
#ENV CHROMEDRIVER_DIR /chromedriver
#RUN mkdir -p $CHROMEDRIVER_DIR

    

# Download and install Chromedriver
#RUN wget -q --continue -P $CHROMEDRIVER_DIR "http://chromedriver.storage.googleapis.com/$CHROMEDRIVER_VERSION/chromedriver_linux64.zip"
#RUN unzip $CHROMEDRIVER_DIR/chromedriver* -d $CHROMEDRIVER_DIR

# Put Chromedriver into the PATH
#ENV PATH $CHROMEDRIVER_DIR:$PATH

#RUN chmod a+x $CHROMEDRIVER_DIR/chromedriver*

# Download and install Chromedriver
#RUN CHROMEVER=$(google-chrome --product-version | grep -o "[^\.]*\.[^\.]*\.[^\.]*") && \
#    DRIVERVER=$(curl -s "https://chromedriver.storage.googleapis.com/LATEST_RELEASE_$CHROMEVER") && \
#    wget -q --continue -P $CHROMEDRIVER_DIR "http://chromedriver.storage.googleapis.com/$DRIVERVER/chromedriver_linux64.zip" && \
#    unzip $CHROMEDRIVER_DIR/chromedriver* -d $CHROMEDRIVER_DIR

# Put Chromedriver into the PATH
#ENV PATH $CHROMEDRIVER_DIR:$PATH

#RUN chmod a+x $CHROMEDRIVER_DIR/chromedriver*

#RUN echo "$PATH"



RUN mkdir -p /chromedriver
RUN cd /chromedriver
RUN wget http://chromedriver.storage.googleapis.com/$CHROMEDRIVER_VERSION/chromedriver_linux64.zip
RUN unzip chromedriver_linux64
RUN chmod a+x chromedriver

RUN echo "net.ipv6.conf.all.disable_ipv6 = 1" >> /etc/sysctl.conf && \
    echo "net.ipv6.conf.default.disable_ipv6 = 1" >> /etc/sysctl.conf


RUN usermod -a -G root ${user}