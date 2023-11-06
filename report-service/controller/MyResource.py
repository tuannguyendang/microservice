from flask import Flask
from flask_restx import Api, Resource

app = Flask(__name__)

app.config.SWAGGER_UI_OAUTH_CLIENT_ID = 'MyClientId'
app.config.SWAGGER_UI_OAUTH_REALM = '-'
app.config.SWAGGER_UI_OAUTH_APP_NAME = 'Demo'
app.config.SWAGGER_UI_DOC_EXPANSION = 'list'
app.config.SWAGGER_UI_OPERATION_ID = True
app.config.SWAGGER_UI_REQUEST_DURATION = True

api = Api(
    app,
    title=app.config.SWAGGER_UI_OAUTH_APP_NAME,
    security={'OAuth2': ['read', 'write']},
    authorizations={
        'OAuth2': {
            'type': 'oauth2',
            'flow': 'implicit',
            'authorizationUrl': 'https://idp.example.com/authorize?audience=https://app.example.com',
            'clientId': app.config.SWAGGER_UI_OAUTH_CLIENT_ID,
            'scopes': {
                'openid': 'Get ID token',
                'profile': 'Get identity',
            }
        }
    }
)


@api.route('/my-resource/<id>')
@api.doc(params={"id": "id"})
class MyResource(Resource):
    def get(self, id):
        return {}

    @api.response(403, 'Not Authorized')
    def post(self, id):
        api.abort(403)


if __name__ == '__main__':
    app.run(debug=True)
