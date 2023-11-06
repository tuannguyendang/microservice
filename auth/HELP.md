# Authorization server

### Reference Documentation
authority table can remove because we are changed with new table authorities

### Testing
clientId:123456
user:123456
grant_type: password


localhost:8763/auth-server/oauth/token
Wrong user or password
{
"error": "invalid_grant",
"error_description": "Bad credentials"
}

wrong client or password
403