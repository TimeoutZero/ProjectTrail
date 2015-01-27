'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ListStepController
  # =============================================
  .controller 'ListStepController', [ '$rootScope', '$scope', '$state', '$stateParams', '$mdSidenav', 'StepNavigationFactory', 'WizardHandler',
    ($rootScope, $scope, $state, $stateParams, $mdSidenav, StepNavigationFactory, WizardHandler) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.team        = _.findWhere( $rootScope.teams     , id: parseInt($stateParams.teamId)   )
      $scope.tool        = _.findWhere( $scope.team?.tools   , id: parseInt($stateParams.toolId)   )
      $scope.action      = _.findWhere( $scope.tool?.actions , id: parseInt($stateParams.actionId) )
      $scope.steps       = $scope.action?.steps

      $scope.currentStep = null

      $scope.viewUtils =
        editMode        : no
      # =============================================
      # Methods
      # =============================================
      $scope.toogleLeftNav = ->
        $mdSidenav('left-nav').toggle()

      $scope.editCurrentStep = ->
        $state.go 'step.edit',
          teamId   : $scope.team.id
          toolId   : $scope.tool.id
          actionId : $scope.action.id
          id       : $scope.getCurrentStep().id


      # =============================================
      # Aux Methods
      # =============================================
      $scope.getCurrentStep = ->
        currentStepIndex = WizardHandler.wizard('step-wizard').currentStepNumber() - 1
        return $scope.steps[currentStepIndex]

      # =============================================
      # Initialize
      # =============================================
      return @

  ]