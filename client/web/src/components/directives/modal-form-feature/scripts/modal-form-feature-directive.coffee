'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.directives'

  # =============================================
  # formFeature
  # =============================================
  .directive 'modalFormFeature', [ () ->
    restrict: 'A'
    replace : yes
    scope:
      feature : "="
    controller : ['$scope', '$mdDialog', ($scope, $mdDialog) ->

      $scope.hideModal = (isSaving)->
        $mdDialog.hide(isSaving)
    ]

    template:"""
      <md-dialog aria-label="Modal" class="myo-modal modal-lg">
        <md-content>

          <md-input-container flex="" class="md-default-theme">
            <label for="feature-name">Name</label>
            <input
              ng-model="feature.name"
              tabindex="0"
              id="feature-name"
              aria-invalid="false">
          </md-input-container>

          <md-input-container flex="" class="md-default-theme">
            <label for="description">Description</label>
            <input
              ng-model="feature.description"
              tabindex="0"
              id="description"
              aria-invalid="false">
          </md-input-container>

          <md-input-container flex="" class="md-default-theme">
            <label for="img-url">Image URL</label>
            <input
              ng-model="feature.image"
              tabindex="0"
              id="img-url"
              aria-invalid="false">
          </md-input-container>

        </md-content>

        <div style="display: block; clear: both;">
         <md-button class="md-default" ng-click="hideModal(false)" tabindex="1">
            Cancel
          </md-button>

          <md-button class="md-primary" ng-click="hideModal(true)" tabindex="2">
            Save
          </md-button>
        </div>

      </md-dialog>
    """
  ]
