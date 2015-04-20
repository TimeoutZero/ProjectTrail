'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ToolController
  # =============================================
  .controller 'FormToolController',  [ '$rootScope', '$scope', '$mdDialog', 'TeamService', 'ToolService',
    ($rootScope, $scope, $mdDialog, TeamService, ToolService) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.tool       = {}
      $scope.modalScope = null

      # =============================================
      # Methods
      # =============================================

      $scope.processSuccessModalResult = (saved) ->
        if saved
          tool = _.omit($scope.modalScope.tool, ['users', '$$hashKey'])
          _(tool).extend teamId: $scope.$parent.team.id

          if tool.id
            promise = ToolService.update(tool)
          else
            promise = ToolService.create(tool)

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
        $scope.tool            = currentTool or {}
        $scope.modalScope      = $rootScope.$new()
        $scope.modalScope.tool = angular.copy($scope.tool)

        $mdDialog.show(
          templateUrl   : 'views/features/tool/views/form-tool-view.html'
          scope         : $scope.modalScope
        ).then($scope.processSuccessModalResult)


      return @

  ]