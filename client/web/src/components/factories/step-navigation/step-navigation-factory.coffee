
'use strict'

# =============================================
# Module
# =============================================
angular.module 'ProjectTrailApp.factories'

  # =============================================
  # StepNavigation
  # =============================================
  .factory 'StepNavigationFactory', [ '$parse',
    ($parse) ->
      @steps            = []
      @previousStepExp  = 'i - 1'
      @nextStepExp      = 'i + 1'


      getStep = (steps, currentStep, exp ) ->
        for step, i in steps when step.id is currentStep.id then return steps[$parse(exp)()]


      return {
        setSteps : (steps) =>
          @steps = steps

        hasPreviousStep : (currentStep, steps) =>
          steps or= @steps
          step    = getStep(steps, currentStep, @previousStepExp)
          return angular.isDefined(step)

        hasNextStep : (currentStep, steps) =>
          steps or= @steps
          step    = getStep(steps, currentStep, @nextStepExp )
          return angular.isDefined(step)

        getPreviousStep: (currentStep, steps) =>
          steps or= @steps
          getStep(steps, currentStep, @previousStepExp)

        getNextStep: (currentStep, steps) =>
          steps or= @steps
          getStep(steps, currentStep, @nextStepExp )
      }
  ]