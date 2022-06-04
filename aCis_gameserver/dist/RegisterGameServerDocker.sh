#!/bin/sh
java -Djava.util.logging.config.file=config/console.cfg -cp './libs/*' net.sf.l2j.gsregistering.GameServerRegisterDocker
