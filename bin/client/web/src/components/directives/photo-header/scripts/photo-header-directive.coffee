'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.directives'

  # =============================================
  # photoHeader
  # =============================================
  .directive 'photoHeader', [ () ->
    restrict: 'A'
    replace : yes
    scope:
      title    : "@"
      subTitle : "@"
      photos   : "="

    controller: [ '$scope'
      ($scope) ->

        itemSize       = 100 / $scope.photos.length + '%'
        $scope.cssItem =
          'backgroun-size' : 'cover'
          width            : itemSize
          height           : '200px'
    ]

    template:"""
      <div class="photo-header well">
        <ul class="photo-header-list">
          <li class="photo-header-item" ng-repeat="photo in photos">
            <img ng-src="{{photo}}" ng-style="cssItem">
          </li>
        </ul>
        <h3 ng-bind="title" class="photo-header-title"></h3>
      </div>
    """
  ]
