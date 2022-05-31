# Copyright 2004-2020 L2J Server
# L2J Server is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
# L2J Server is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
# You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/.

FROM openjdk:16-alpine3.13

LABEL maintainer="Lineternity" \
    version="0.0.0.1" \
    website="lineternity.jogatinando.com.br"

COPY entrypoint.sh /entrypoint.sh

ARG branch_gs=develop
ARG branch_dp=develop

# install apache ant
ENV ANT_VERSION 1.10.1
ENV ANT_DOWNLOAD_URL http://archive.apache.org/dist/ant/binaries/apache-ant-$ANT_VERSION-bin.tar.gz
ENV ANT_HOME /usr/share/ant
ENV PATH $PATH:$ANT_HOME/bin

# download and extract apache ant
RUN apk --update add --no-cache curl -f#L $ANT_DOWNLOAD_URL | bsdtar -C /usr/share/ -xf- \
    && ln -s /usr/share/apache-ant-$ANT_VERSION /usr/share/ant 


RUN apk --update add  --no-cache mariadb-client git \
    && mkdir -p /opt/l2j/server && mkdir -p /opt/l2j/target && cd /opt/l2j/target/ \
    && git clone --branch main --single-branch https://git@github.com:kazuyabr/lineternity_nft.git lineternity \
    && cd /opt/l2j/target/lineternity/aCis_datapack && chmod 755 && ./ant build \
    && cd /opt/l2j/target/lineternity/aCis_gameserver && chmod 755 && ./ant dist \
    && cp -rp /opt/l2j/target/lineternity/aCis_datapack/build/ /opt/l2j/server/ \
    && cp -rp /opt/l2j/target/lineternity/aCis_gameserver/build/dist/ /opt/l2j/server/ \
    && rm -rf /opt/l2j/target/ && apk del ant git \
    && chmod +x /opt/l2j/server/game/*.sh /opt/l2j/server/auth/*.sh /entrypoint.sh


WORKDIR /opt/l2j/server

EXPOSE 7777 2106

ENTRYPOINT [ "/entrypoint.sh" ]