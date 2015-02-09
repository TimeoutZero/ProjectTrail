'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # IListWizardController
  # =============================================
  .controller 'IListWizardController', [ '$rootScope', '$scope', '$state', '$stateParams', '$mdSidenav', 'StepNavigationFactory', 'WizardHandler',
    ($rootScope, $scope, $state, $stateParams, $mdSidenav, StepNavigationFactory, WizardHandler) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.team     = _.findWhere( $rootScope.teams     , id: parseInt($stateParams.teamId)   )
      $scope.tool     = _.findWhere( $scope.team?.tools   , id: parseInt($stateParams.toolId)   )
      $scope.action   = _.findWhere( $scope.tool?.actions , id: parseInt($stateParams.actionId) )
      $scope.steps    = $scope.action?.steps
      $scope.step     = _.findWhere( $scope.steps         , id: parseInt($stateParams.id)       )

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