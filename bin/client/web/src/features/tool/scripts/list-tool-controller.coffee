'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ToolController
  # =============================================
  .controller 'ListToolController', [ '$rootScope', '$scope', '$stateParams', '$state',
    ($rootScope, $scope, $stateParams, $state) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.team  = _.findWhere( $rootScope.teams, id : parseInt($stateParams.id, 10) )
      $scope.tools = $scope.team?.tools


      # =============================================
      # Methods
      # =============================================
      $scope.seeActions = (tool) -> $state.go 'action.list', { teamId: $scope.team.id, toolId : tool.id }

      # =============================================
      # Aux Methods
      # =============================================


      # =============================================
      # Initialize
      # =============================================


      return @

  ]