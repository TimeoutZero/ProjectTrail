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
        templateUrl : 'views/features/team/views/list-team-view.html'
        controller  : 'ListTeamController'
        data        :
          restrict  : no
      )


      # Create Team
      # ==============================
      .state('team.create'
        url         : '/create'
        templateUrl : 'views/features/team/views/form-team.html'
        controller  : 'FormTeamController'
        data        :
          restrict  : no
      )


      # Edit Team
      # ==============================
      .state('team.edit'
        url         : '/edit/:id'
        templateUrl : 'views/features/team/views/form-team.html'
        controller  : 'FormTeamController'
        data        :
          restrict  : no
      )



  ])
