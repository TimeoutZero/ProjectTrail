'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp'

  # =============================================
  # ToolController
  # =============================================
  .run  [ '$rootScope',
    ($rootScope) ->

      # =============================================
      # Attributes
      # =============================================
      $rootScope.teams = [
        {
          id  :  parseInt(_.uniqueId())
          name: 'Holmes'
          img : 'img/main/img/mocks/holmes.jpg'
          description: 'Sistema de gestão inteligente de documentos'
          tools: [
            {
              id :  parseInt(_.uniqueId())
              img: 'img/main/img/mocks/bamboo-logo.jpg'
              name: 'Bamboo'
              description: 'It connects issues, commits, test results, and deploys so the whole picture is available to your entire product'
              actions : [
                {
                  id   : parseInt(_.uniqueId())
                  name : 'Deploy em QA'
                  description : 'Publicar versão de desenvolvimento no server de QA'
                }
              ]
            }
            { id:  parseInt(_.uniqueId()), img: 'img/main/img/mocks/docker-logo.jpg', name: 'Docker', description: 'Docker is an open platform for developers and sysadmins to build, ship, and run distributed applications, whether on laptops, data center VMs, or the cloud' }
            {
              id          :  parseInt(_.uniqueId())
              img         : 'img/main/img/mocks/js-logo.jpeg'
              name        : 'JS Scripts'
              description : 'Some scripts as helpers'
              actions : [
                {
                  id   : parseInt(_.uniqueId())
                  name : 'Reindexar Documentos'
                  description : 'Script pra chamar a API de reindexação via demanda'
                }
              ]
            }
          ]
        }

        {
          id  :  parseInt(_.uniqueId())
          name: 'PegaMetro'
          # img : 'img/main/img/mocks/pegametro-logo.png'
          description: 'Linhas de trem e metrô em tempo real'
          tools: [
            {
              id:  parseInt(_.uniqueId())
              img: 'img/main/img/mocks/aws-logo.png'
              name: 'AWS'
              description: 'Web Services'
              actions : [
                {
                  id:  parseInt(_.uniqueId())
                  name : 'Deploy'
                  description : 'Deploy the production version'
                }
              ]
            }
            { id:  parseInt(_.uniqueId()), img: 'img/main/img/mocks/trello-logo.png', name: 'Trello'   , description: 'Kanban for tasks' }
          ]
        }
      ]


      # =============================================
      # Methods
      # =============================================


      # =============================================
      # Aux Methods
      # =============================================


      # =============================================
      # Initialize
      # =============================================

  ]