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


      # Action
      # ==============================
      .state('action'
        url         : '/action'
        templateUrl : 'views/features/action/views/action-view.html'
        controller  : 'ActionController'
        abstract    : yes
        data        :
          restrict  : no
      )


      # List
      # ==============================
      .state('action.list'
        url         : '/:teamId/:toolId/list'
        templateUrl : 'views/features/action/views/list-action-view.html'
        controller  : 'ListActionController'
        data        :
          restrict  : no
      )


      # action
      # ==============================
      .state('action.create'
        url         : '/create'
        templateUrl : 'views/features/action/views/form-action.html'
        controller  : 'FormActionController'
        data        :
          restrict  : no
      )


      # action
      # ==============================
      .state('action.edit'
        url         : '/edit/:id'
        templateUrl : 'views/features/action/views/form-action.html'
        controller  : 'FormActionController'
        data        :
          restrict  : no
      )



  ])
