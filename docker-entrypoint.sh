SIMULATION_NAME=${SIMULATION_NAME:-com/maletsky/webtours/simulation/WebToursSimulation}
JAVA_OPTS="-Xmx1024M -Xms1024M -Xss1M -XX:+UseParallelGC -XX:MaxRAMPercentage=95 -XX:TieredStopAtLevel=1 -Xverify:none"
exec java ${JAVA_OPTS} -cp /usr/local/app/bin/*.jar io.gatling.app.Gatling -s ${SIMULATION_NAME}