Feature: BellyFeature
  Scenario: Eaten many cukes and many hour, and growl
    Given I have eaten 12 cukes
    When I wait 3 hour
    Then my belly should growl

  Scenario: Eaten many hours but cukes is the minimal, and not growl
    Given I have eaten 10 cukes
    When I wait 4 hour
    Then my belly should not growl

  Scenario: Eaten many cukes but hour is the minimal, and growl
    Given I have eaten 12 cukes
    When I wait 2 hour
    Then my belly should growl

  Scenario: Cukes under the required, and not growl
    Given I have eaten 8 cukes
    When I wait 3 hour
    Then My belly should not growl

  Scenario: Hour under the required, and not growl
    Given I have eaten 12 cukes
    When I wait 1 hour
    Then My belly should not growl

  Scenario: Cukes and hour are under the minimal required, and not growl
    Given I have eaten 6 cukes
    When I wait 1 hour
    Then My belly should not growl