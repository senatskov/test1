# test1
Написать WEB-сервис, который слушает опреедленный URL и принимает на него XML-запросы, отправленные в теле POST-запроса.
Есть два вида сообщений

1) Регистрация нового клиента
<?xml version="1.0" encoding="UTF-8"?>
<request>
	<request-type>CREATE-AGT</request-type>
	<extra name="login">user1</extra>
	<extra name="password">qwerty</extra>
</request>

При этом сервер:
1. проверяет, зарегистрирован ли уже такой клиент
2. регистрирует клиента в системе с нулевым балансом

Ответное сообщение:
<?xml version="1.0" encoding="UTF-8"?>
<response>
  <result-code>0</result-code>
</response>

Возможные ответы:
0 - все ОК
1 - пользователь с таким логином уже существует
2 - техническая ошибка

2) Запрос клиентом своего баланса
<?xml version="1.0" encoding="UTF-8"?>
<request>
	<request-type>GET-BALANCE</request-type>
	<extra name="login">user1</extra>
	<extra name="password">qwerty</extra>
</request>

Сервер сверяет логин и пароль, и если они верны - возвращает баланс клиента:
<?xml version="1.0" encoding="UTF-8"?>
<response>
  <result-code>0</result-code>
  <extra name="balance">100.00</extra>
</response>

Возможные ответы:
0 - все ОК
2 - техническая ошибка
3 - пользователь не существует
4 - пароль не верен