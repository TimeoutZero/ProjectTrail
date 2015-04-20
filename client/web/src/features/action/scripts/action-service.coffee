
'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ActionService
  # =============================================
  .service 'ActionService', [ 'APP_BASE_URL', '$http'
    (APP_BASE_URL, $http) ->

      list: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.teamId}/tool/#{data.toolId}"
          method        : 'GET'

      create: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.teamId}/tool/#{data.toolId}"
          method        : 'POST'
          data          : data

      get: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.teamId}/tool/#{data.toolId}/#{data.id}"
          method        : 'GET'
          data          : data

      update: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.teamId}/tool/#{data.toolId}/#{data.id}"
          method        : 'PUT'
          data          : data

      delete: (data) ->
        $http
          url           : APP_BASE_URL + "team/#{data.teamId}/tool/#{data.toolId}/#{data.id}"
          method        : 'DELETE'
  ]