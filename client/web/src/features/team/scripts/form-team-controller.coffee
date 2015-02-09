'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.controllers'

  # =============================================
  # ToolController
  # =============================================
  .controller 'FormTeamController', [ '$rootScope', '$scope', '$mdDialog',
    ($rootScope, $scope, $mdDialog) ->

      # =============================================
      # Attributes
      # =============================================


      # =============================================
      # Methods
      # =============================================


      # =============================================
      # Aux Methods
      # =============================================


      # =============================================
      # Initialize
      # =============================================
      $scope.$on 'openFormDialog', ->
        $mdDialog.show
          templateUrl : 'views/features/team/views/form-team-view.html'

      return @

  ]