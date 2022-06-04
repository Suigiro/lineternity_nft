# Copyright 2004-2020 L2J Server
# L2J Server is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
# L2J Server is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
# You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/.

FROM openjdk:16-alpine

LABEL maintainer="Lineternity"

COPY entrypoint.sh /entrypoint.sh

#BRANCH do GIT
ARG branch=main


#####
# Ant
#####

# Preparation

ENV ANT_VERSION 1.10.12
ENV ANT_HOME /etc/ant-${ANT_VERSION}

# Installation

RUN cd /tmp \
    && wget https://dlcdn.apache.org/ant/binaries/apache-ant-${ANT_VERSION}-bin.tar.gz \
    && mkdir ant-${ANT_VERSION} \
    && tar -zxvf apache-ant-${ANT_VERSION}-bin.tar.gz \
    && mv apache-ant-${ANT_VERSION} ${ANT_HOME} \
    && rm apache-ant-${ANT_VERSION}-bin.tar.gz \
    && rm -rf ant-${ANT_VERSION} \
    && rm -rf ${ANT_HOME}/manual \
    && unset ANT_VERSION
ENV PATH ${PATH}:${ANT_HOME}/bin

RUN apk update \
    && apk add --no-cache micro mariadb-client git \
    && mkdir -p /opt/l2j/server && mkdir -p /opt/l2j/target && cd /opt/l2j/target/ \
    && git clone --branch $branch --single-branch https://github.com/kazuyabr/lineternity_nft.git lineternity \
    && cd /opt/l2j/target/lineternity/aCis_datapack && chmod 755 ./ && ant build \
    && cd /opt/l2j/target/lineternity/aCis_gameserver && chmod 755 ./ && ant dist \
    && cp -avR /opt/l2j/target/lineternity/aCis_datapack/build/* /opt/l2j/server/ \
    && cp -avR /opt/l2j/target/lineternity/aCis_gameserver/build/dist/* /opt/l2j/server/ \
    && rm -rf /opt/l2j/target/ && apk del git \
    && chmod +x /opt/l2j/server/game/*.sh /opt/l2j/server/auth/*.sh /entrypoint.sh


WORKDIR /opt/l2j/server

EXPOSE 7777 2106

ENTRYPOINT [ "/entrypoint.sh" ]