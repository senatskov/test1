# test1
Ќаписать WEB-сервис, который слушает опреедленный URL и принимает на него XML-запросы, отправленные в теле POST-запроса.
≈сть два вида сообщений

1) –егистраци€ нового клиента
<?xml version="1.0" encoding="UTF-8"?>
<request>
	<request-type>CREATE-AGT</request-type>
	<extra name="login">user1</extra>
	<extra name="password">qwerty</extra>
</request>

ѕри этом сервер:
1. провер€ет, зарегистрирован ли уже такой клиент
2. регистрирует клиента в системе с нулевым балансом

ќтветное сообщение:
<?xml version="1.0" encoding="UTF-8"?>
<response>
  <result-code>0</result-code>
</response>

¬озможные ответы:
0 - все ќ 
1 - пользователь с таким логином уже существует
2 - техническа€ ошибка

2) «апрос клиентом своего баланса
<?xml version="1.0" encoding="UTF-8"?>
<request>
	<request-type>GET-BALANCE</request-type>
	<extra name="login">user1</extra>
	<extra name="password">qwerty</extra>
</request>

—ервер свер€ет логин и пароль, и если они верны - возвращает баланс клиента:
<?xml version="1.0" encoding="UTF-8"?>
<response>
  <result-code>0</result-code>
  <extra name="balance">100.00</extra>
</response>

¬озможные ответы:
0 - все ќ 
2 - техническа€ ошибка
3 - пользователь не существует
4 - пароль не верен