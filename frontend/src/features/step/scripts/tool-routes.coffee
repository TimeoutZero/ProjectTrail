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


      # List
      # ==============================
      .state('tool.list'
        url         : '/list'
        templateUrl : 'views/features/tool/views/list-tool-view.html'
        controller  : 'ListToolController'
        data        :
          restrict  : no
      )


      # Tool
      # ==============================
      .state('tool.create'
        url         : '/create'
        templateUrl : 'views/features/tool/views/form-tool.html'
        controller  : 'FormToolController'
        data        :
          restrict  : no
      )


      # Tool
      # ==============================
      .state('tool.edit'
        url         : '/edit/:id'
        templateUrl : 'views/features/tool/views/form-tool.html'
        controller  : 'FormToolController'
        data        :
          restrict  : no
      )



  ])
