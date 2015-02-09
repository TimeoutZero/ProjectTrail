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


      # Team
      # ==============================
      .state('team'
        url         : '/team'
        templateUrl : 'views/features/team/views/team-view.html'
        controller  : 'TeamController'
        abstract    : yes
        data        :
          restrict  : no
      )


      # List Teams
      # ==============================
      .state('team.list'
        url         : '/list'
        views:
          '' :
            templateUrl : 'views/features/team/views/list-team-view.html'
            controller  : 'ListTeamController'
          'form-view@team.list' :
            controller  : 'FormTeamController'
        data        :
          restrict  : no
      )



  ])
