Spring-boot приложение для отображения новостных данных.

#### WEB сервис
Необходимо реализовать функциональность REST сервиса в котом будет реализовано:
1. API для получения данных из БД:
    1. Обрабатываемых источниках
    2. Тематиках новостей
    3. Новостях (всех, всех по источнику, всех по тематике) с функционалом пагинации
2. Безобасность через API токен клиента с правами на все методы API (без регистрации)
3. Реализовать статистическую выгрузку, запускающуюся по `cron`-рассписанию. Используя многопоточность, для каждого источника составить `csv` формата `Тема,Количество новостей`. Файл называть названием источника.
4. Миграции для базы данных

Написать `docker-compose` в котором будет запускаться `postgresql` и приложение

