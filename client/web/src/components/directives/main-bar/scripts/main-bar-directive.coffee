'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.directives'

  # =============================================
  # mainMenu
  # =============================================
  .directive 'mainBar', ['$filter', '$rootScope', '$state', '$timeout', '$window', '$interval', ($filter, $rootScope, $state, $timeout, $window, $interval) ->
    restrict    : 'AE'
    replace     : yes
    scope       : {}
    templateUrl : 'views/components/directives/main-bar/views/main-bar.html'
    link        : (scope, element, attrs) ->

      # =============================================
      # Attributes
      # =============================================
      scrollTimeout            = null
      scope.currentParentState = null
      scope.isOpen             = no
      scope.state              = $state

      scope.menuItems = [
        { name: 'Team list', mainState: 'team.list', parentState: 'team' }
        { name: 'Tool list', mainState: 'tool.list', parentState: 'tool' }
      ]

      # =============================================
      # Methods
      # =============================================
      scope.changeSelectedItem = () =>
        states                    = $state.current.name.split('.')
        scope.currentParentState = states[0] or $state.current.name
        scope.currentItem        = _.findWhere scope.menuItems, {mainState: $state.current.name}

      # =============================================
      # Watcher & Events
      # =============================================
      $timeout ->
          scope.$watch 'state.current.name', ->
            scope.changeSelectedItem()
        , 0

      scope.$on '$destroy', ->
        $timeout.cancel scrollTimeout
        scrollTimeout = null

      $(document).scroll ->
        unless scrollTimeout
          scrollTimeout = $timeout(->
            # Clear $timeout
            $timeout.cancel scrollTimeout
            scrollTimeout = null

            # Add/Remove class
            scrollTop     = $(window).scrollTop()
            scrollTrigger = 50
            element.addClass('main-bar-minimized')    if scrollTop > scrollTrigger
            element.removeClass('main-bar-minimized') if scrollTop < scrollTrigger
          , 50)
  ]
