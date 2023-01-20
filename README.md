## Запуск и функционал
1. Собрать проект
2. Запустить docker-compose
3. Получить токен черз запрос POST http://localhost/api/auth/login (Пустое тело запроса, даст права неавторизованного пользователя)
4. Использовать accessToken из тело ответа для авторизации в новостном API (В postman Authorization - Bearer Token, через  curl --header "Authorization: Bearer accessToken" -X GET 'http://localhost:8080/api/news')
5. Доступные конечные точки
- GET api/news - все новости с пагинацией
- GET api/news/filter?filter=&value= параметр filter={"topic", "source"}, значения фильтра value={Тема или источник новости}
- GET api/news/topics - список всех тем новостей
- GET api/sources - список всех источников новостей
6. Статистичиская выгрузка происходит раз в 10 секунд по пути /info_by_source 

### ТЗ. Spring-boot приложение для отображения новостных данных.

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
