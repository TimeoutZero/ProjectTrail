'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ListActionController
  # =============================================
  .controller 'ListActionController', [ '$rootScope', '$scope', '$state', '$stateParams',
    ($rootScope, $scope, $state, $stateParams) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.team     = _.findWhere( $rootScope.teams,   id: parseInt($stateParams.teamId) )
      $scope.tool     = _.findWhere( $scope.team?.tools, id: parseInt($stateParams.toolId) )
      $scope.actions  = $scope.tool?.actions


      # =============================================
      # Methods
      # =============================================
      $scope.seeSteps = (action) ->
        $state.go 'step.list' , {
          teamId   : $scope.team.id
          toolId   : $scope.tool.id
          actionId : action.id
        }

      # =============================================
      # Aux Methods
      # =============================================


      # =============================================
      # Initialize
      # =============================================


      return @

  ]