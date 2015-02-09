'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.directives'

  # =============================================
  # mainMenu
  # =============================================
  .directive 'mainBar', [ () ->
    restrict: 'AE'
    replace : yes
    scope: {}
    controller  : ['$scope', '$filter', '$rootScope', '$state',
      ($scope, $filter, $rootScope, $state) ->

        # =============================================
        # Attributes
        # =============================================
        $scope.currentParentState = null
        $scope.isOpen             = no

        $scope.menuItems = [
          { name: 'Tool', mainState: 'tool.list', parentState: 'tool' }
        ]

        # =============================================
        # Methods
        # =============================================
        $scope.changeSelectedItem = () =>
          states = $state.current.name.split('.')
          $scope.currentParentState = states[0] or $state.current.name

        # =============================================
        # Events
        # =============================================
        $scope.changeSelectedItem()


    ]
    templateUrl: 'views/components/directives/main-bar/views/main-bar.html'
  ]
