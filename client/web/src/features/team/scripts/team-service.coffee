
'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # TeamService
  # =============================================
  .service 'TeamService', [ 'APP_BASE_URL', '$http'
    (APP_BASE_URL, $http) ->

      list: () ->
        $http
          url           : APP_BASE_URL + 'team/me'
          method        : 'GET'

      create: (data) ->
        $http
          url           : APP_BASE_URL + 'team'
          method        : 'POST'
          data          : data

      get: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.id}"
          method        : 'GET'
          data          : data

      update: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.id}"
          method        : 'PUT'
          data          : data

      delete: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.id}"
          method        : 'DELETE'


  ]