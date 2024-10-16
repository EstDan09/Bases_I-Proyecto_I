import cx_Oracle

# Database cnx
dsn_tns = cx_Oracle.makedsn("127.0.0.1", "1521", sid="Proyecto1BD")
connection = cx_Oracle.connect(user="AP", password="donotgiveaway", dsn=dsn_tns)
cursor = connection.cursor()

# prueba
id_nationality = 2
cursor.execute("SELECT get_nationality_name(:id) FROM dual", id=id_nationality)


result = cursor.fetchone()[0]
print(f'prueba {id_nationality}: {result}')

# Cerrar cnx
cursor.close()
connection.close()
