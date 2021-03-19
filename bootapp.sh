#!/bin/bash
echo 'Acessando diretório da aplicação...'
# Por favor ajustar o diretório da aplicação API
cd ~/Downloads/prova-tecnica/prova-tecnica-api-master
echo 'Iniciando a aplicação API'
exec mvn clean package & wait
echo "Processo concluído..."
cd target/
exec java -jar prova-tecnica-api-2.1.5.RELEASE.jar --log-file appstart.txt & wait
echo "Aplicação Iniciada"
