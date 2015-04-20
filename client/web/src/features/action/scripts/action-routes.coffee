'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp'

  # =============================================
  # App Config
  # =============================================
  .config( ['$stateProvider', '$urlRouterProvider', ($stateProvider, $urlRouterProvider) ->

    # States
    # =============================================
    $stateProvider


      # Tool
      # ==============================
      .state('action'
        url         : '/action'
        templateUrl : 'views/features/tool/views/tool-view.html'
        controller  : 'ActionController'
        abstract    : yes
        data        :
          restrict  : no
      )


      # List - Form
      # ==============================
      .state('action.list'
        url         : '/:teamId/:toolId/list'
        views:
          '' :
            templateUrl : 'views/features/action/views/list-action-view.html'
            controller  : 'ListActionController'
          'form-view@action.list' :
            controller  : 'FormActionController'
        data        :
          restrict  : no
      )



  ])
