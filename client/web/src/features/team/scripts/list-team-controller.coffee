'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ToolController
  # =============================================
  .controller 'ListTeamController', [ '$rootScope', '$scope', '$state', '$mdBottomSheet',
    ($rootScope, $scope, $state, $mdBottomSheet) ->

      # =============================================
      # Attributes
      # =============================================
      $scope.teams       = $rootScope.teams
      $scope.currentTeam = null

      # =============================================
      # Methods
      # =============================================
      $scope.onClickItem = (item) ->
        $state.go 'tool.list', id : item.id

      $scope.showSettings = (item) ->
        $scope.currentTeam = item

        $mdBottomSheet.show
          templateUrl: 'views/components/other/feature-bottom-sheet/feature-bottom-sheet.html'

      $scope.openFormDialog = (item) ->
        $scope.$broadcast 'openFormDialog', $scope.currentTeam

      # =============================================
      # Aux Methods
      # =============================================


      # =============================================
      # Initialize
      # =============================================


      return @

  ]