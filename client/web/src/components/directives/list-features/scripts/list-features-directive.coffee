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
      list                : "="
      onClickItem         : "="
      onClickDeleteButton : "="
      onClickEditButton   : "="
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
          return item.image or $scope.defaultImgUrl

        $scope.clickItemCallback = (item) ->
          _.isFunction($scope.onClickItem) and $scope.onClickItem(item)

        $scope.clickEditButton = (item) ->
          _.isFunction($scope.onClickEditButton) and $scope.onClickEditButton(item)

        $scope.clickDeleteButton = (item) ->
          _.isFunction($scope.onClickDeleteButton) and $scope.onClickDeleteButton(item)

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

                <div class="action-buttons-container btn-group-vertical">
                  <button class="btn btn-primary" ng-click="clickEditButton(item)" title="Edit">
                    <span class="glyphicon glyphicon-pencil"></span>
                  </button>
                  <button class="btn btn-danger" ng-click="clickDeleteButton(item)" title="Delete">
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    """
  ]
