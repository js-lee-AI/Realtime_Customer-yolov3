from flask import Blueprint, request, render_template, flash, redirect, url_for
from flask import current_app as app
import pymysql
import sys
import os
# sys.path.append('..')
# 추가할 모듈이 있다면 추가
connect = pymysql.connect(host='localhost',user='Lee',password='123',db='a',charset='utf8',autocommit=True)
cursor = connect.cursor(pymysql.cursors.DictCursor)
main = Blueprint('main', __name__, url_prefix='/')


@main.route('/main', methods=['GET'])
def index():
    # /main/index.html은 사실 /project_name/app/templates/main/index.html을 가리킵니다.
    sql = "SELECT num FROM person;"
    cursor.execute(sql)
    testData = cursor.fetchall()[0]['num']
    return render_template('/main/index.html',testDataHtml=testData)

