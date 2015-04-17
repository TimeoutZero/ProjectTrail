'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ToolController
  # =============================================
  .controller 'FormTeamController', [ '$rootScope', '$scope', '$mdDialog', 'TeamService',
    ($rootScope, $scope, $mdDialog, TeamService) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.team       = {}
      $scope.modalScope = null

      # =============================================
      # Methods
      # =============================================

      $scope.processSuccessModalResult = (saved) ->
        if saved
          team = _.omit($scope.modalScope.team, ['users', '$$hashKey'])

          if team.id
            promise = TeamService.update(team)
          else
            promise = TeamService.create(team)

          promise.success -> $scope.$emit 'updateTeamList'

      # =============================================
      # Aux Methods
      # =============================================
      $scope.hideModal = (isSaving)->
        $mdDialog.hide(isSaving)

      # =============================================
      # Initialize
      # =============================================
      $scope.$on 'openFormDialog', (eventObj, currentTeam) ->
        $scope.team            = currentTeam or {}
        $scope.modalScope      = $rootScope.$new()
        $scope.modalScope.team = angular.copy($scope.team)

        $mdDialog.show(
          templateUrl   : 'views/features/team/views/form-team-view.html'
          scope         : $scope.modalScope
        ).then($scope.processSuccessModalResult)


      return @

  ]