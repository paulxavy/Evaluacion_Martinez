@echo off
cd .\build\install\Martinez-Evaluacion
start java -Dserver.port=8080 -classpath lib/* com.distribuida.servidor
start java -Dserver.port=8081 -classpath lib/* com.distribuida.servidor 
start java -Dserver.port=8082 -classpath lib/* com.distribuida.servidor 
start java -Dserver.port=8083 -classpath lib/* com.distribuida.servidor 
