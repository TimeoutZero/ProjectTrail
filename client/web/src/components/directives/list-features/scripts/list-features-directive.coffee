'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.directives'

  # =============================================
  # ListFeature
  # =============================================
  .directive 'listFeatures', [ () ->
    restrict: 'A'
    replace : yes
    scope:
      list          : "="
      onClickItem   : "="
    controller : ['$scope'
      ($scope) ->
        $scope.defaultImgUrl = 'img/main/img/general/default-placeholder-picture.png'

        $scope.imageCss =
          'background-size' : 'cover'
          width             : '70px'
          height            : '70px'
          display           : 'block'
          float             : 'left'

        $scope.getItemImage = (item) ->
          return item.img or $scope.defaultImgUrl

        $scope.clickItemCallback = (item) ->
          if _.isFunction($scope.onClickItem) then $scope.onClickItem(item)

    ]
    template:"""
      <div class="row margin-top-default">
        <div class="col-md-12">
          <ul class="feature-list">
            <li class="panel panel-default feature-item" ng-repeat="item in list" >
              <div class="panel-body">
                <div class="feature-content" ng-click="clickItemCallback(item)">
                  <img class="feature-item-image" ng-style="imageCss" ng-src="{{ getItemImage(item) }}">

                  <div class="feature-details">
                    <h4 ng-bind="item.name" class="no-margin feature-item-name"></h4>
                    <p ng-bind="item.description | uppercase" class="feature-item-description"></p>
                  </div>
                </div>

                <button class="settings-button">Settings</button>
              </div>
            </li>
          </ul>
        </div>
      </div>
    """
  ]
