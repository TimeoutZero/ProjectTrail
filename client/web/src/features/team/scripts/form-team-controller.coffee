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
      $scope.team = {}

      # =============================================
      # Methods
      # =============================================


      # =============================================
      # Aux Methods
      # =============================================
      $scope.hideModal = (isSaving)->
        $mdDialog.hide(isSaving)

      # =============================================
      # Initialize
      # =============================================
      $scope.$on 'openFormDialog', ->
        promise = $mdDialog.show
          templateUrl : 'views/features/team/views/form-team-view.html'
          scope       : $scope

        promise.then (saved) ->
          if saved then TeamService.create($scope.team)

      return @

  ]