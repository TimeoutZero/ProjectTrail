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
      .state('tool'
        url         : '/tool'
        templateUrl : 'views/features/tool/views/tool-view.html'
        controller  : 'ToolController'
        abstract    : yes
        data        :
          restrict  : no
      )


      # List - Form
      # ==============================
      .state('tool.list'
        url         : '/:teamId/list'
        views:
          '' :
            templateUrl : 'views/features/tool/views/list-tool-view.html'
            controller  : 'ListToolController'
          'form-view@tool.list' :
            controller  : 'FormToolController'
        data        :
          restrict  : no
      )



  ])
