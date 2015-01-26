'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ListStepController
  # =============================================
  .controller 'ListStepController', [ '$rootScope', '$scope', '$state', '$stateParams', '$mdSidenav', 'StepNavigationFactory',
    ($rootScope, $scope, $state, $stateParams, $mdSidenav, StepNavigationFactory) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.team        = _.findWhere( $rootScope.teams     , id: parseInt($stateParams.teamId)   )
      $scope.tool        = _.findWhere( $scope.team?.tools   , id: parseInt($stateParams.toolId)   )
      $scope.action      = _.findWhere( $scope.tool?.actions , id: parseInt($stateParams.actionId) )
      $scope.steps       = $scope.action?.steps

      $scope.viewUtils =
        editMode        : no
      # =============================================
      # Methods
      # =============================================
      $scope.toogleLeftNav = ->
        $mdSidenav('left-nav').toggle()

      # =============================================
      # Aux Methods
      # =============================================


      # =============================================
      # Initialize
      # =============================================
      return @

  ]