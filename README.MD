# test1
�������� WEB-������, ������� ������� ������������ URL � ��������� �� ���� XML-�������, ������������ � ���� POST-�������.
���� ��� ���� ���������

1) ����������� ������ �������
<?xml version="1.0" encoding="UTF-8"?>
<request>
	<request-type>CREATE-AGT</request-type>
	<extra name="login">user1</extra>
	<extra name="password">qwerty</extra>
</request>

��� ���� ������:
1. ���������, ��������������� �� ��� ����� ������
2. ������������ ������� � ������� � ������� ��������

�������� ���������:
<?xml version="1.0" encoding="UTF-8"?>
<response>
  <result-code>0</result-code>
</response>

��������� ������:
0 - ��� ��
1 - ������������ � ����� ������� ��� ����������
2 - ����������� ������

2) ������ �������� ������ �������
<?xml version="1.0" encoding="UTF-8"?>
<request>
	<request-type>GET-BALANCE</request-type>
	<extra name="login">user1</extra>
	<extra name="password">qwerty</extra>
</request>

������ ������� ����� � ������, � ���� ��� ����� - ���������� ������ �������:
<?xml version="1.0" encoding="UTF-8"?>
<response>
  <result-code>0</result-code>
  <extra name="balance">100.00</extra>
</response>

��������� ������:
0 - ��� ��
2 - ����������� ������
3 - ������������ �� ����������
4 - ������ �� �����