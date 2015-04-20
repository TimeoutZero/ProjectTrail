'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ToolController
  # =============================================
  .controller 'ListToolController', [ '$rootScope', '$scope', '$stateParams', '$state', 'ToolService', 'TeamService', '$q', '$mdToast', '$mdDialog',
    ($rootScope, $scope, $stateParams, $state, ToolService, TeamService, $q, $mdToast, $mdDialog) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.team        = {}
      $scope.tools       = []
      $scope.currentTool = null

      # =============================================
      # Methods
      # =============================================
      $scope.onClickItem = (tool) ->
        $state.go 'action.list', { teamId: $scope.team.id, toolId : tool.id }

      $scope.getAllData = (data) =>

        onSuccess = (data) ->
          $scope.team  = data[0].data
          $scope.tools = data[1].data

        onError = (errorData) ->
          alert errorData?.message

        $q.all([
          TeamService.get  id     : $stateParams.id
          ToolService.list teamId : $stateParams.id
        ]).then(onSuccess, onError)



      $scope.openFormDialog = (item) ->
        $scope.currentTool = item
        $scope.$broadcast 'openFormDialog', $scope.currentTool

      $scope.showDeleteDialog = (item) ->
        confirm = $mdDialog.confirm()
          .title('Confirm')
          .content('Are you sure about delete this item?')
          .ariaLabel('Tools')
          .ok('Yes, delete!')
          .cancel('no')

        $mdDialog.show(confirm).then( -> $scope.deleteItem(item) )

      $scope.deleteItem = (item) ->
        item    = _({teamId: $scope.team.id}).extend item
        promise = ToolService.delete(item)

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
      $scope.$on 'updateToolList', $scope.getAllData

      # =============================================
      # Initialize
      # =============================================
      $scope.initialize()

      return @

  ]