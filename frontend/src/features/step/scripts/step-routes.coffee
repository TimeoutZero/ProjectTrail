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


      # step
      # ==============================
      .state('step'
        url         : '/:teamId/:toolId/:actionId/'
        templateUrl : 'views/features/step/views/step-view.html'
        controller  : 'StepController'
        abstract    : yes
        data        :
          restrict  : no
      )


      # List
      # ==============================
      .state('step.list'
        url         : 'list'
        templateUrl : 'views/features/step/views/list-step-view.html'
        controller  : 'ListStepController'
        data        :
          restrict  : no
      )


      # step
      # ==============================
      .state('step.create'
        url         : 'create'
        templateUrl : 'views/features/step/views/form-step.html'
        controller  : 'FormStepController'
        data        :
          restrict  : no
      )


      # step
      # ==============================
      .state('step.edit'
        url         : ':id/edit/'
        templateUrl : 'views/features/step/views/form-step.html'
        controller  : 'FormStepController'
        data        :
          restrict  : no
      )



  ])
