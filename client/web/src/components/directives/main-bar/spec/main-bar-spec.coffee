'use strict'

# =============================================
# LoginController
# =============================================
describe 'Directive: MainBar', () ->

  # =============================================
  # Import modules
  # =============================================
  beforeEach module('builds/dev/views/components/directives/main-bar/views/main-bar.html')
  beforeEach module('ProjectTrailApp.scripts')
  beforeEach module('stateMock')

  # =============================================
  # Variables
  # =============================================
  $scope       = null
  $state       = null
  $compile     = null
  isolateScope = null

  # =============================================
  # Inject dependencies
  # =============================================
  beforeEach inject (_$rootScope_, _$state_, _$compile_) ->
    $scope       = _$rootScope_.$new()
    $state       = _$state_
    $compile     = _$compile_

    elem = angular.element("<div main-bar></div>")
    elem = $compile(elem)($scope)
    $scope.$digest()
    isolateScope = elem.isolateScope()

    return

  # =============================================
  # Tests
  # =============================================
  describe 'Should change the page name in the sub-bar', ->

    iit 'When the $state changes', ->

        # inject ($timeout) ->
          console.log 'a'
          # spyOn($scope, 'changeSelectedItem')

          # $state.current =
          #   name: 'team.list'
          # $state.expectTransitionTo 'tool.list'
          # $scope.state = $state

          # $state.go 'tool.list'
          # $timeout.flush(1)

          # expect($scope.state.current.name).toEqual 'tool.list'
          # expect($scope.changeSelectedItem).toHaveBeenCalled()
          # expect($scope.curentItem).toEqual {name: 'tool.list'}
