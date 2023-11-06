import sys

from flask import Flask, jsonify
from flask_restx import Api, Resource
from entity import db, Product
from services import ProductService

app = Flask(__name__)

POSTGRES = {
    'user': 'report',
    'pw': '123456',
    'db': 'report_db',
    'host': 'localhost',
    'port': '5432',
}

app.config['DEBUG'] = True
# app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://%(user)s:\
# %(pw)s@%(host)s:%(port)s/%(db)s' % POSTGRES
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://report:123456@127.0.0.1:5432/report_db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True
db.init_app(app)

@app.route('/v1/api/report/product/', methods=['GET'])
def product_report():
    service = ProductService()
    return jsonify(service.product_report())


if __name__ == "__main__":
    if "createdb" in sys.argv:
        with app.app_context():
            db.create_all()
        print("Database created!")

    elif "seeddb" in sys.argv:
        with app.app_context():
            p1 = Product(name="205 nguyen duy trinh", amount="100.10")
            db.session.add(p1)
            p2 = Product(name="200 nguyen duy trinh", amount="1111.10")
            db.session.add(p2)
            db.session.commit()
        print("Database seeded!")

    else:
        app.run(debug=True)
