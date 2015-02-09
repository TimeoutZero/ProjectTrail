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
    controller  : ['$scope', '$filter', '$rootScope', '$state', '$timeout',
      ($scope, $filter, $rootScope, $state, $timeout) ->

        # =============================================
        # Attributes
        # =============================================
        $scope.currentParentState = null
        $scope.isOpen             = no
        $scope.state              = $state

        $scope.menuItems = [
          { name: 'Team list', mainState: 'team.list', parentState: 'team' }
          { name: 'Tool list', mainState: 'tool.list', parentState: 'tool' }
        ]

        # =============================================
        # Methods
        # =============================================
        $scope.changeSelectedItem = () =>
          states                    = $state.current.name.split('.')
          $scope.currentParentState = states[0] or $state.current.name
          $scope.currentItem        = _.findWhere $scope.menuItems, {mainState: $state.current.name}

        # =============================================
        # Watcher
        # =============================================
        $timeout ->
            $scope.$watch 'state.current.name', ->
              $scope.changeSelectedItem()
          , 0

    ]
    templateUrl: 'views/components/directives/main-bar/views/main-bar.html'
  ]
