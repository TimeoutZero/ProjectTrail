'use strict'


# =============================================
# Modules
# =============================================
angular.module 'ProjectTrailApp.controllers' , []
angular.module 'ProjectTrailApp.filters'     , []
angular.module 'ProjectTrailApp.factories'   , []
angular.module 'ProjectTrailApp.services'    , []
angular.module 'ProjectTrailApp.constants'   , []
angular.module 'ProjectTrailApp.directives'  , []


# =============================================
# Scripts Module
# =============================================
angular.module 'ProjectTrailApp.scripts'     , [
  'ProjectTrailApp.controllers'
  'ProjectTrailApp.constants'
  'ProjectTrailApp.filters'
  'ProjectTrailApp.factories'
  'ProjectTrailApp.services'
  'ProjectTrailApp.directives'
]


# =============================================
# Main Module
# =============================================
angular.module('ProjectTrailApp', [
  'ngSanitize'
  'QuickList'
  'ui.router'
  'ui.bootstrap'
  'btford.markdown'
  'ProjectTrailApp.scripts'
])


  # =============================================
  # Initialize
  # =============================================
  .run([ () ->

    # Import underscore-string to underscore
    # =================================
    _.mixin(_.string.exports())

    # Change Moment relative time
    moment.lang 'pt-br',
      relativeTime :
        future: "em %s"
        past:   "%s atrás"
        s:  "segundos"
        m:  "um minuto"
        mm: "%d minutos"
        h:  "uma hora"
        hh: "%d horas"
        d:  "um dia"
        dd: "%d dias"
        M:  "um mês"
        MM: "%d meses"
        y:  "um ano"
        yy: "%d anos"

    moment.lang('pt-br')

  ])

  # =============================================
  # httpProvider Config
  # =============================================
  .config( ['$httpProvider', 'markdownConverterProvider', ($httpProvider, markdownConverterProvider) ->

    # Customize $httpProvider
    # =============================================
    # $httpProvider.defaults.transformRequest  = (data) -> if data then $.param(data) else data
    # $httpProvider.defaults.headers.post      = "Content-Type": 'application/json'

    markdownConverterProvider.config({ extensions: ['github'] })

  ])





