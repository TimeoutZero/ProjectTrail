'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # FormStepController
  # =============================================
  .controller 'FormStepController', [ '$rootScope', '$scope', '$state', '$stateParams'
    ($rootScope, $scope, $state, $stateParams) ->

      # =============================================
      # Attributes
      # =============================================

      $scope.team     = _.findWhere( $rootScope.teams     , id: parseInt($stateParams.teamId)   )
      $scope.tool     = _.findWhere( $scope.team?.tools   , id: parseInt($stateParams.toolId)   )
      $scope.action   = _.findWhere( $scope.tool?.actions , id: parseInt($stateParams.actionId) )
      $scope.step     = _.findWhere( $scope.action?.steps , id: parseInt($stateParams.id)       )

      # =============================================
      # Methods
      # =============================================


      # =============================================
      # Aux Methods
      # =============================================


      # =============================================
      # Initialize
      # =============================================


      return @

  ]