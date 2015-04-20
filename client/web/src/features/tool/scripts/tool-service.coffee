
'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ToolService
  # =============================================
  .service 'ToolService', [ 'APP_BASE_URL', '$http'
    (APP_BASE_URL, $http) ->

      list: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.teamId}/tool"
          method        : 'GET'

      create: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.teamId}/tool"
          method        : 'POST'
          data          : data

      get: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.teamId}/tool/#{data.id}"
          method        : 'GET'
          data          : data

      update: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.teamId}/tool/#{data.id}"
          method        : 'PUT'
          data          : data

      delete: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.teamId}/tool/#{data.id}"
          method        : 'DELETE'
  ]