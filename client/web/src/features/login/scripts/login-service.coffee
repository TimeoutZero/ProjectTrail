'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.services'

  # =============================================
  # LoginService
  # =============================================
  .service 'LoginService', ['$http', 'APP_BASE_URL', ($http, APP_BASE_URL) ->

    return {

      login: (data) ->
        $http
          url         : APP_BASE_URL + 'login/authenticate'
          method      : 'POST'
          data        : data
          headers     :
            "Content-type": "application/x-www-form-urlencoded"

      loginWithFacebook: (data) ->
         $http
          url         : APP_BASE_URL + 'login'
          method      : 'POST'
          data        : data

      signUp: (data) ->
        $http
          url         : APP_BASE_URL + 'email-fila'
          method      : 'POST'
          data        : data

      loggout: ->
        $http
          url         : APP_BASE_URL + 'loggout'
          method      : 'POST'
    }

  ]
