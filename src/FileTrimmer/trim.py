#!/usr/bin/env python
import psycopg2
import os

# make an attempt to connect to the DB, fail silently if we can't.
try:
    conn = psycopg2.connect("dbname='postgres' user='postgres' host='localhost' password='password'")
except:
    exit()

cur = conn.cursor()
# change to the relevant schema
cur.execute("SET search_path TO {}".format("file_system"))

cur.execute('SELECT * from file_details where expiration_time::TIME <= now()::TIME and file_access::INTEGER != 2::INTEGER ;')

records = cur.fetchall()

for file in records:
    try:
        # Update file location
        os.remove("/home/server/Server/Files/{}".format(file[2]))
        cur.execute("UPDATE file_details SET file_access = 2 WHERE file_id ={}".format(file[0]))
        conn.commit()
        print "Updated Status & Removed File By Id Of {}".format(file[0])
    except:
        print "Failed to remove file {}.".format(file[0])
        pass




