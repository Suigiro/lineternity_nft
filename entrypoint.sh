#!/bin/sh

# Copyright 2004-2020 L2J Server
# L2J Server is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
# L2J Server is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
# You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/.

#Configurações GERAIS
JAVA_XMS=${JAVA_XMS:-"512m"}
JAVA_XMX=${JAVA_XMX:-"2g"}
IP_ADDRESS=${IP_ADDRESS:-"127.0.0.1"}
DATABASE_USER=${DATABASE_USER:-"root"}
DATABASE_PASS=${DATABASE_PASS:-"z5fOEm03j"}
DATABASE_ADDRESS=${DATABASE_ADDRESS:-"mariadb"}
DATABASE_PORT=${DATABASE_PORT:-"3306"}
LAN_ADDRESS=${LAN_ADDRESS:-"10.0.0.0"}
LAN_SUBNET=${LAN_SUBNET:-"10.0.0.0/8"}
RATE_XP=${RATE_XP:-"1"}
RATE_SP=${RATE_SP:-"1"}
QUEST_MULTIPLIER_XP=${QUEST_MULTIPLIER_XP:-"1"}
QUEST_MULTIPLIER_SP=${QUEST_MULTIPLIER_SP:-"1"}
QUEST_MULTIPLIER_REWARD=${QUEST_MULTIPLIER_REWARD:-"1"}
VITALITY_SYSTEM=${VITALITY_SYSTEM:-"True"}
AUTO_LEARN_SKILLS=${AUTO_LEARN_SKILLS:-"False"}
MAX_FREIGHT_SLOTS=${MAX_FREIGHT_SLOTS:-"200"}
DWARF_RECIPE_LIMIT=${DWARF_RECIPE_LIMIT:-"50"}
COMM_RECIPE_LIMIT=${COMM_RECIPE_LIMIT:-"50"}
CRAFTING_SPEED_MULTIPLIER=${CRAFTING_SPEED_MULTIPLIER:-"1"}
FREE_TELEPORTING=${FREE_TELEPORTING:-"False"}
STARTING_ADENA=${STARTING_ADENA:-"0"}
STARTING_LEVEL=${STARTING_LEVEL:-"1"}
STARTING_SP=${STARTING_SP:-"0"}
ALLOW_MANOR=${ALLOW_MANOR:-"True"}
SERVER_DEBUG=${SERVER_DEBUG:-"False"}
MAX_ONLINE_USERS=${MAX_ONLINE_USERS:-"500"}
MAX_WAREHOUSE_SLOTS_DWARF=${MAX_WAREHOUSE_SLOTS_DWARF:-"120"}
MAX_WAREHOUSE_SLOTS_NO_DWARF=${MAX_WAREHOUSE_SLOTS_NO_DWARF:-"100"}
MAX_WAREHOUSE_SLOTS_CLAN=${MAX_WAREHOUSE_SLOTS_CLAN:-"200"}
PET_XP_RATE=${PET_XP_RATE:-"1"}
ITEM_SPOIL_MULTIPLIER=${ITEM_SPOIL_MULTIPLIER:-"1"}
ITEM_DROP_MULTIPLIER=${ITEM_DROP_MULTIPLIER:-"1"}
ALT_WEIGHT_LIMIT=${ALT_WEIGHT_LIMIT:-"1"}
RUN_SPEED_BOOST=${RUN_SPEED_BOOST:-"0"}
RATE_ADENA=${RATE_ADENA:-"1"}
ADMIN_RIGHTS=${ADMIN_RIGHTS:-"False"}
COORD_SYNC=${COORD_SYNC:-"-1"}
FORCE_GEODATA=${FORCE_GEODATA:-"False"}
HELLBOUND_ACCESS=${HELLBOUND_ACCESS:-"False"}
TVT_ENABLED=${TVT_ENABLED:-"False"}
SAVE_GM_SPAWN_ON_CUSTOM=${SAVE_GM_SPAWN_ON_CUSTOM:-"False"}
CUSTOM_SPAWNLIST_TABLE=${CUSTOM_SPAWNLIST_TABLE:-"False"}
CUSTOM_NPC_DATA=${CUSTOM_NPC_DATA:-"False"}
CUSTOM_TELEPORT_TABLE=${CUSTOM_TELEPORT_TABLE:-"False"}
CUSTOM_NPC_BUFFER_TABLES=${CUSTOM_NPC_BUFFER_TABLES:-"False"}
CUSTOM_SKILLS_LOAD=${CUSTOM_SKILLS_LOAD:-"False"}
CUSTOM_ITEMS_LOAD=${CUSTOM_ITEMS_LOAD:-"False"}
CUSTOM_MULTISELL_LOAD=${CUSTOM_MULTISELL_LOAD:-"False"}
CUSTOM_BUYLIST_LOAD=${CUSTOM_BUYLIST_LOAD:-"False"}
BUFFER_SERVICE=${BUFFER_SERVICE:-"False"}
BUFFER_SERVICE_COOLDOWN=${BUFFER_SERVICE_COOLDOWN:-"60"}
BUFFER_SERVICE_MAX_LISTS=${BUFFER_SERVICE_MAX_LISTS:-"5"}
BUFFER_DEBUG=${BUFFER_DEBUG:-"False"}
BUFFER_SERVICE_VOICED=${BUFFER_SERVICE_VOICED:-"False"}
BUFFER_SERVICE_VOICED_COMMAND=${BUFFER_SERVICE_VOICED_COMMAND:-"bufferservice"}
BUFFER_SERVICE_VOICED_NAME=${BUFFER_SERVICE_VOICED_NAME:-"Voiced"}
BUFFER_SERVICE_VOICED_REQUIRED_ITEM=${BUFFER_SERVICE_VOICED_REQUIRED_ITEM:-"0"}

#Configuracoes SERVER
SERVER_NAMES_PATH="/opt/l2j/server/auth/servece in $DATABASE_ADDRESS:$DATABASE_PORT"
sleep 5s

STATUS=$(nc -z $DATABASE_ADDRESS $DATABASE_PORT; echo $?)
while [ "$STATUS" != 0 ]
do
    sleep 3s
    STATUS=$(nc -z $DATABASE_ADDRESS $DATABASE_PORT; echo $?)
done

# ---------------------------------------------------------------------------
# Database Installation
# ---------------------------------------------------------------------------
echo "Definindo database..."
DATABASE=$(mysql -h "$DATABASE_ADDRESS" -P "$DATABASE_PORT" -u "$DATABASE_USER" -p "$DATABASE_PASS" -e "SHOW DATABASES" | grep acis)
if [ "$DATABASE" != "acis" ]; then        
    mysql -h $DATABASE_ADDRESS -P $DATABASE_PORT -u "$DATABASE_USER" -p"$DATABASE_PASS" -e "DROP DATABASE IF EXISTS acis";
    mysql -h $DATABASE_ADDRESS -P $DATABASE_PORT -u "$DATABASE_USER" -p"$DATABASE_PASS" -e "CREATE DATABASE IF NOT EXISTS acis CHARACTER SET utf8 COLLATE utf8_general_ci";        
    mysql -h $DATABASE_ADDRESS -P $DATABASE_PORT -u "$DATABASE_USER" -p"$DATABASE_PASS" -e "CREATE OR REPLACE USER 'l2j'@'%' IDENTIFIED BY 'lineternity';";    
    mysql -h $DATABASE_ADDRESS -P $DATABASE_PORT -u "$DATABASE_USER" -p"$DATABASE_PASS" -e "GRANT ALL PRIVILEGES ON *.* TO 'l2j'@'%' IDENTIFIED BY 'lineternity';";    
    mysql -h $DATABASE_ADDRESS -P $DATABASE_PORT -u "$DATABASE_USER" -p"$DATABASE_PASS" -e "FLUSH PRIVILEGES;";
    
    echo "Verificando estrura da pasta server"
    ls /opt/l2j/server
    echo "Verificando estrutura da pasta tools"
    ls /opt/l2j/server/tools
    echo "Verificando estrutura da pasta sql"
    ls /opt/l2j/server/sql
    echo "Exibir conteudo de database_installer_docker.sh"
    cat /opt/l2j/server/tools/database_installer_docker.sh
    echo "Clonando pasta SQL"
    cp -rp /opt/l2j/server/sql /opt/l2j/server/tools/
    echo "Validando pasta SQL"
    ls /opt/l2j/server/tools
    echo "Dando permissoes"
    chmod 775 -Rv /opt/l2j/server/tools
    echo "Dando permissao de execucao"
    chmod +x /opt/l2j/server/tools/database_installer_docker.sh
    echo "Executou database_installer_docker.sh"
    cd /opt/l2j/server/tools/
    sh database_installer_docker.sh
    # java -jar /opt/l2j/server/cli/l2jcli.jar db install -sql /opt/l2j/server/login/sql -u l2j -p l2jserver2019 -m FULL -t LOGIN -c -mods -url jdbc:mariadb://$DATABASE_ADDRESS:$DATABASE_PORT
    # java -jar /opt/l2j/server/cli/l2jcli.jar db install -sql /opt/l2j/server/game/sql -u l2j -p l2jserver2019 -m FULL -t GAME -c -mods -url jdbc:mariadb://$DATABASE_ADDRESS:$DATABASE_PORT
    #java -jar /opt/l2j/server/cli/l2jcli.jar account create -u l2j -p l2j -a 8 -url jdbc:mariadb://mariadb:3306
    echo "finalizado"
fi

# ---------------------------------------------------------------------------
# Registrando servidor
# ---------------------------------------------------------------------------
cd /opt/l2j/server/game/config
sh ../../auth/RegisterGameServerDocker.sh


# ---------------------------------------------------------------------------
# Log folders
# ---------------------------------------------------------------------------

LF="/opt/l2j/server/auth/log"
if test -d "$LF"; then
    echo "Login log folder server exists"
else
    mkdir $LF
fi

GF="/opt/l2j/server/game/log"
if test -d "$GF"; then
    echo "Game log folder server exists"
else
    mkdir $GF
fi

# ---------------------------------------------------------------------------
# Database
# ---------------------------------------------------------------------------

sed -i "s#jdbc:mariadb://localhost:3307/lineternity#jdbc:mariadb://mariadb:3306/acis#g" /opt/l2j/server/auth/config/loginserver.properties
sed -i "s#Login = root#Login = l2j#g" /opt/l2j/server/auth/config/loginserver.properties
sed -i "s#Password = #Password = lineternity#g" /opt/l2j/server/auth/config/loginserver.properties
sed -i "s#jdbc:mariadb://localhost:3307/lineternity#jdbc:mariadb://mariadb:3306/acis#g" /opt/l2j/server/game/config/server.properties
sed -i "s#Login = root#Login = l2j#g" /opt/l2j/server/game/config/server.properties
sed -i "s#Password = #Password = lineternity#g" /opt/l2j/server/game/config/server.properties

# ---------------------------------------------------------------------------
# Login and Gameserver start
# ---------------------------------------------------------------------------

cd /opt/l2j/server/auth/
chmod +x /opt/l2j/server/auth/LoginServer_loop.sh
sh LoginServer_loop.sh

sed -i "s#Xms512m#Xms$JAVA_XMS#g" /opt/l2j/server/game/GameServer_loop.sh
sed -i "s#Xmx2g#Xmx$JAVA_XMX#g" /opt/l2j/server/game/GameServer_loop.sh

cd /opt/l2j/server/game/
chmod +x /opt/l2j/server/game/GameServer_loop.sh
sh GameServer_loop.sh

#Temp
echo "Waiting the server log"

sleep 5s

tail -n 500 -f /opt/l2j/server/auth/log/login.log
tail -n 500 -f /opt/l2j/server/game/log/server.log
