'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # FormActionController
  # =============================================
  .controller 'FormActionController',  [ '$rootScope', '$scope', '$mdDialog', 'TeamService', 'ToolService', 'ActionService', '$stateParams',
    ($rootScope, $scope, $mdDialog, TeamService, ToolService, ActionService, $stateParams) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.action       = {}
      $scope.modalScope = null

      # =============================================
      # Methods
      # =============================================

      $scope.processSuccessModalResult = (saved) ->
        if saved
          action = _.omit($scope.modalScope.action, ['users', '$$hashKey'])
          _(action).extend
            teamId: $stateParams.teamId
            toolId: $stateParams.toolId

          if action.id
            promise = ActionService.update(action)
          else
            promise = ActionService.create(action)

          promise.success -> $scope.$emit 'updateToolList'

      # =============================================
      # Aux Methods
      # =============================================
      $scope.hideModal = (isSaving)->
        $mdDialog.hide(isSaving)

      # =============================================
      # Initialize
      # =============================================
      $scope.$on 'openFormDialog', (eventObj, currentTool) ->
        $scope.action            = currentTool or {}
        $scope.modalScope        = $rootScope.$new()
        $scope.modalScope.action = angular.copy($scope.action)

        $mdDialog.show(
          templateUrl   : 'views/features/action/views/form-action-view.html'
          scope         : $scope.modalScope
        ).then($scope.processSuccessModalResult)


      return @

  ]