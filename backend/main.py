import cx_Oracle

dsn_tns = cx_Oracle.makedsn("127.0.0.1", "1521", sid="Proyecto1BD")
connection = cx_Oracle.connect(user="AP", password="donotgiveaway", dsn=dsn_tns)
cursor = connection.cursor()

# Check the connection
print("Successfully connected to Oracle Database!")
cursor.close()
connection.close()