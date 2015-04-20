'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ListActionController
  # =============================================
  .controller 'ListActionController', [ '$rootScope', '$scope', '$stateParams', '$state', 'ToolService', 'TeamService', '$q', '$mdToast', '$mdDialog', 'ActionService',
    ($rootScope, $scope, $stateParams, $state, ToolService, TeamService, $q, $mdToast, $mdDialog, ActionService) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.team       = {}
      $scope.tool       = {}
      $scope.actions    = {}
      $scope.currentAction = null

      # =============================================
      # Methods
      # =============================================
      $scope.onClickItem = (action) ->
        $state.go 'step.list', { teamId: $stateParams.teamId, toolId : $stateParams.toolId }

      $scope.getAllData = (data) =>

        onSuccess = (data) ->
          $scope.team    = data[0].data
          $scope.tool    = data[1].data
          $scope.actions = data[2].data

        onError = (errorData) ->
          $mdToast.show( $mdToast.simple().content(errorData?.message) )

        $q.all([
          TeamService.get    id     : $stateParams.teamId
          ToolService.get    teamId : $stateParams.teamId, id: $stateParams.toolId
          ActionService.list teamId : $stateParams.teamId, toolId: $stateParams.toolId
        ]).then(onSuccess, onError)



      $scope.openFormDialog = (item) ->
        $scope.currentAction = item
        $scope.$broadcast 'openFormDialog', $scope.currentAction

      $scope.showDeleteDialog = (item) ->
        confirm = $mdDialog.confirm()
          .title('Confirm')
          .content('Are you sure about delete this item?')
          .ariaLabel('Tools')
          .ok('Yes, delete!')
          .cancel('no')

        $mdDialog.show(confirm).then( -> $scope.deleteItem(item) )

      $scope.deleteItem = (item) ->
        additionalInformation = teamId: $stateParams.teamId, toolId: $stateParams.toolId
        item                  = _(additionalInformation).extend item
        promise               = ActionService.delete(item)

        promise.success ->
          $mdToast.show( $mdToast.simple().content('Deleted') )
          $scope.getAllData()

      $scope.initialize = ->
        $scope.getAllData()

      # =============================================
      # Aux Methods
      # =============================================

      # =============================================
      # Events
      # =============================================
      $scope.$on 'updateActionList', $scope.getAllData

      # =============================================
      # Initialize
      # =============================================
      $scope.initialize()

      return @

  ]