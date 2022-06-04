#!/bin/sh
java -Djava.util.logging.config.file=config/console.cfg -cp '/opt/l2j/server/auth/libs/*' net.sf.l2j.gsregistering.GameServerRegisterDocker
